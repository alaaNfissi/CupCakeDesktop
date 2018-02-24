package tn.esprit.cupcake.views;



import tn.esprit.cupcake.entities.Patisserie;
import java.io.IOException;
import java.net.URL;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import tn.esprit.cupcake.entities.Utilisateur;


public class CupCake extends Application {
    public static Utilisateur user = null;
	public static  Patisserie patisserie =null;
    @Override
    public void start(Stage stage) throws IOException{
           Parent root = FXMLLoader.load(getClass().getResource("/tn/esprit/cupcake/views/Authentification.fxml"));
        Scene scene = new Scene(root);
        URL url = this.getClass().getResource("/tn/esprit/cupcake/views/bootstrap3.css");
        if (url == null) {
            System.out.println("Resource not found. Aborting.");
            System.exit(-1);
        }
        String css = url.toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
       }
        
        
    
    public static void main(String[] args) {
        
        launch(args);
    }
    
}
