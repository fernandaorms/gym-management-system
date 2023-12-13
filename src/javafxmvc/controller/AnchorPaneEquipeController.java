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
import src.javafxmvc.runnable.RunnableEquipe;
import src.javafxmvc.runnable.RunnableEquipe_Socket;

public class AnchorPaneEquipeController implements Initializable{
    @FXML 
    private ImageView imageViewLogo;
    @FXML
    private Label labelEquipe;
    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        loadImages();

        System.out.println("Conectando no servidor");

        RunnableEquipe equipe = new RunnableEquipe(labelEquipe);
        Thread thread = new Thread(equipe);
        thread.setName("Thread Equipe");
        thread.start();
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