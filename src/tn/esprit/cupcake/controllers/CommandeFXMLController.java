/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.cupcake.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alaa
 */
public class CommandeFXMLController implements Initializable {

	/**
	 * Initializes the controller class.
	 */
	@FXML
	private Button retourbtn;
	@FXML 
	private Button listeCommandes;
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}	
	
	public void RetourPanier(ActionEvent event) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("/tn/esprit/cupcake/views/Panierajouter.fxml"));
                        Scene newScene= new Scene(root);
                        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        window.setScene(newScene);
                        window.show();
	}
	public void afficherListeCommandes(ActionEvent event) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("/tn/esprit/cupcake/views/ListeCommandeFXML.fxml"));
                        Scene newScene= new Scene(root);
                        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        window.setScene(newScene);
                        window.show();
	}
}
