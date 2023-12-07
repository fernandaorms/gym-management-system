package src.javafxmvc.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.time.LocalDate;

import src.javafxmvc.model.domain.Aluno;
import src.javafxmvc.grpc_server.EquipeServiceGrpc;
import src.javafxmvc.grpc_server.Mensagem.VerificarCupomRequest;
import src.javafxmvc.grpc_server.Mensagem.VerificarCupomResponse;
import src.javafxmvc.model.dao.AlunoDAO;
import src.javafxmvc.model.domain.Pagamento;
import src.javafxmvc.model.dao.PagamentoDAO;
import src.javafxmvc.model.dao.PlanoDAO;
import src.javafxmvc.model.database.Database;
import src.javafxmvc.model.database.DatabaseFactory;

public class AnchorPanePagamentoDialogController implements Initializable {
    @FXML
    private ComboBox<Aluno> comboBoxAlunos;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Label labelValor;
    @FXML
    private Label labelDesconto;
    @FXML
    private TextField textFieldCupom;
    @FXML
    private Label labelTitulo;

    //labels de erro
    @FXML
    private Label labelErroAluno;
    @FXML
    private Label labelErroData;
    @FXML
    private Label labelErroCupom;

    private List<Aluno> listAlunos = new ArrayList<>();
    private ObservableList<Aluno> observableListAlunos;
    private Stage dialogStage;
    private boolean buttonConfirmed = false;
    private Pagamento pagamento;
    protected boolean cupomAplicado = false;
    

    //Atributos para manipulação de Banco de Dados
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final PagamentoDAO pagamentoDAO = new PagamentoDAO();

    private AlunoDAO alunoDAO = new AlunoDAO();
    private PlanoDAO planoDAO = new PlanoDAO();
    
   
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        pagamentoDAO.setConnection(connection);
        alunoDAO.setConnection(connection);
        planoDAO.setConnection(connection);

        loadAlunos();
    }
    
    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setTitle(int button){
        switch(button){
            case 0:
                this.labelTitulo.setText("Registrar Pagamento");
                break;
            case 1:
                this.labelTitulo.setText("Alterar Pagamento");
                break;
        }
        
    }

    public Pagamento getFuncionario() {
        return this.pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
        Aluno alunoComboBox = alunoDAO.findById(pagamento.getAluno_id());
        comboBoxAlunos.getSelectionModel().select(alunoComboBox);
        datePicker.setValue(pagamento.getData());
        labelValor.setText(String.valueOf(pagamento.getValor()));
    }

    public boolean isButtonConfirmed() {
        return buttonConfirmed;
    }
    
    @FXML
    public void handleButtonConfirm() {
        cleanErrors();

        if (validateData()){
            Aluno aluno = comboBoxAlunos.getSelectionModel().getSelectedItem();
                                  
            //atualizando os pontos do aluno casa ele esteja apto ao desconto
            if(aluno.getPontos() >= 150){
                aluno.setPontos(aluno.getPontos() - 150);
                alunoDAO.update(aluno);
            }

            pagamento.setAluno_id(aluno.getIdAluno());
            pagamento.setData(datePicker.getValue());
            pagamento.setValor(Float.parseFloat(labelValor.getText()));            
            
            buttonConfirmed = true;
            dialogStage.close();
        }
    }

    public void handleButtonAplicarCupom(){
        String cupom = textFieldCupom.getText();

        ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", 12345).usePlaintext().build();
        EquipeServiceGrpc.EquipeServiceBlockingStub stub = EquipeServiceGrpc.newBlockingStub(channel);
        
        VerificarCupomRequest request = VerificarCupomRequest.newBuilder().setCupom(cupom).build();
        VerificarCupomResponse response = stub.verificarCupom(request);
        if(response.getValido()) {
            if(cupomAplicado){
                labelErroCupom.setText("Cupom já aplicado!");
            }else{
                float preco = Float.parseFloat(labelValor.getText());
                cupomAplicado = true;
                preco *= 0.95;
                labelValor.setText(String.valueOf(preco));
                labelErroCupom.setText("Cupom válido! Desconto de 5% aplicado");
                labelErroCupom.setStyle("-fx-text-fill: #3CB370;");
            }
        }else {
            if(cupomAplicado){
                cupomAplicado = false;
                float preco = Float.parseFloat(labelValor.getText());
                preco /= 0.95;
                labelValor.setText(String.valueOf(preco));
            }
            labelErroCupom.setText("Cupom inválido!");
            labelErroCupom.setStyle("-fx-text-fill: #FF2E2E;");
        }

        channel.shutdown();
    }

    public void handleButtonCancel(){
        getDialogStage().close();
    }

    public void loadAlunos(){
        listAlunos = alunoDAO.list();
        observableListAlunos = FXCollections.observableArrayList(listAlunos);
        comboBoxAlunos.setItems(observableListAlunos);
    }

    public void cleanErrors() {
        labelErroAluno.setText(null);
        labelErroData.setText(null);
    }

    public boolean validateData(){
        int qdtErros = 0;

        if (comboBoxAlunos.getSelectionModel().getSelectedItem() == null) {
            labelErroAluno.setText("Selecione um aluno");
            qdtErros++;
        }
        if (datePicker.getValue() == null) {
            labelErroData.setText("Selecione uma data de pagamento válida.");
            qdtErros++;
        } else if (datePicker.getValue().isAfter(LocalDate.now())) {
            labelErroData.setText("A data de pagamento não pode ser futura.");
            qdtErros++;
        }
        if(qdtErros > 0) return false;
        
        return true;
        
    }


    @FXML
    public void handleComboBoxAlunos() {
        Aluno alunoSelecionado = comboBoxAlunos.getSelectionModel().getSelectedItem();

        if (alunoSelecionado != null) {
            if(alunoSelecionado.getPontos() >= 150) {
                float preco = planoDAO.getPrice(alunoSelecionado.getPlano_id());
                preco *= 0.85;
                labelValor.setText(String.valueOf(preco));
                labelDesconto.setText("Desconto aplicado");
                labelDesconto.setStyle("-fx-text-fill: #3CB370;");
            }else {
                labelValor.setText(String.valueOf(planoDAO.getPrice(alunoSelecionado.getPlano_id())));
                labelDesconto.setText("Não foi possível aplicar desconto, quantidade de pontos insuficiente (" + alunoSelecionado.getPontos() + ")");
            }
        }
    }
    
    
}