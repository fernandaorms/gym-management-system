package src.javafxmvc.runnable;

import java.util.List;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import javafx.application.Platform;
import javafx.scene.control.Label;
import src.javafxmvc.grpc_server.EquipeServiceGrpc;
import src.javafxmvc.grpc_server.Mensagem.InfoEquipeRequest;
import src.javafxmvc.grpc_server.Mensagem.InfoEquipeResponse;

public class RunnableEquipe implements Runnable{
    private Label labelEquipe;
    private final ManagedChannel channel;
    private final EquipeServiceGrpc.EquipeServiceBlockingStub stub;
    
    
    public RunnableEquipe(Label labelEquipe) {
        this.labelEquipe = labelEquipe;
        this.channel = ManagedChannelBuilder.forAddress("127.0.0.1", 12345).usePlaintext().build();
        this.stub = EquipeServiceGrpc.newBlockingStub(channel);
    }

    @Override
    public void run() {
        try {
            // Faz uma chamada gRPC para obter informações da equipe
            InfoEquipeRequest request = InfoEquipeRequest.newBuilder().setId(3).build();
            InfoEquipeResponse response = stub.obterEquipeInfo(request);
            List<String> listEquipe = response.getMembrosList();
            while (true) {
                for (String nome : listEquipe) {
                    Platform.runLater(() -> labelEquipe.setText(nome));
                    Thread.sleep(3000);
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fecha o canal de comunicação após a conclusão
            channel.shutdown();
        }
    }
    
}
