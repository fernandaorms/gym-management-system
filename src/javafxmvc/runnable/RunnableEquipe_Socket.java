package src.javafxmvc.runnable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class RunnableEquipe_Socket implements Runnable{
    private Socket socket;
    private Label labelEquipe;
    private List<String> listEquipe;
    
    
    public RunnableEquipe_Socket(Socket socket, Label labelEquipe) {
        this.socket = socket;
        this.labelEquipe = labelEquipe;
    }

    @Override
    public void run() {
        try{
            ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
            listEquipe = new ArrayList<>();
            listEquipe = (ArrayList<String>) entrada.readObject();
            while (true) {
                for (String nome : listEquipe) {
                    Platform.runLater(() -> labelEquipe.setText(nome));
                    Thread.sleep(3000);
                }
            }

        } catch (IOException | ClassNotFoundException | InterruptedException ioe) {
            System.out.println("Erro: " + ioe.toString());
        }
    }
    
}
