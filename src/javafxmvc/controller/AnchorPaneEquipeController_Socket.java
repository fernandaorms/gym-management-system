package src.javafxmvc.controller;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import src.javafxmvc.runnable.RunnableEquipe_Socket;

public class AnchorPaneEquipeController_Socket implements Initializable{
    @FXML 
    private ImageView imageViewLogo;
    @FXML
    private Label labelEquipe;

    private Socket socket;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        loadImages();

        System.out.println("Conectando no servidor");

        try {
            socket = new Socket("127.0.0.1", 12345);
        } catch (IOException e) {
            e.printStackTrace();
        }

        RunnableEquipe_Socket equipe = new RunnableEquipe_Socket(socket, labelEquipe);
        Thread thread = new Thread(equipe);
        thread.setName("Thread Equipe");
        thread.start();

        try {
            DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
            saida.writeInt(3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadImages() {
        try {
            Image image = new Image(new FileInputStream("src/javafxmvc/images/logo-icon-brand.png"));
            imageViewLogo.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
