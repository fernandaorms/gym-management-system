package src.javafxmvc.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.Connection;

import src.javafxmvc.Main;
import src.javafxmvc.model.dao.FuncionarioDAO;
import src.javafxmvc.model.database.Database;
import src.javafxmvc.model.database.DatabaseFactory;
import src.javafxmvc.model.domain.Funcionario;

public class VBoxLoginFuncController implements Initializable {

    @FXML
    private TextField textFieldUsuario;

    @FXML
    private PasswordField passwordFieldSenha;

    @FXML
    private Button buttonLogin;

    @FXML
    private Button buttonLink;

    @FXML
    private Label labelErroUsuario;

    @FXML
    private Label labelErroSenha;

    @FXML
    private Label labelNotFound;

    @FXML 
    private ImageView imageViewLogo;


    //Atributos para manipulação de Banco de Dados
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        funcionarioDAO.setConnection(connection);

        try {
            Image image = new Image(new FileInputStream("src/javafxmvc/images/logo-black.png"));
            imageViewLogo.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void login() throws IOException {
        cleanErrors();

        String usuario = textFieldUsuario.getText();
        String senha = passwordFieldSenha.getText();

        if(!usuario.isEmpty() && !senha.isEmpty()) {
            Funcionario funcionario = funcionarioDAO.validate(usuario, senha);
            
            if(funcionario != null) {
                switchToMainFunc();
            }
            else {
                labelNotFound.setText("Usuário não encontrado");
            }
        }
        else {
            if(usuario.isEmpty()) {
                labelErroUsuario.setText("O campo Usuário não pode estar vazio");
            }
            if(senha.isEmpty()) {
                labelErroSenha.setText("O campo Senha não pode estar vazio");
            }
        }
    }

    public void switchToLoginAdm() throws IOException {
        Main.setRoot("view/VBoxLoginAdm");
    }

    public void switchToMainFunc() throws IOException {
        Main.setRoot("view/AnchorPaneMainFunc");
    }

    public void cleanErrors() {
        labelErroUsuario.setText(null);
        labelErroSenha.setText(null);
        labelNotFound.setText(null);
    }
}
