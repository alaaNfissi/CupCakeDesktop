/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.cupcake.views;

import tn.esprit.cupcake.entities.EtatCompte;
import tn.esprit.cupcake.entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import tn.esprit.cupcake.services.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author Alaa
 */
public class HomeFXMLController implements Initializable {

	/**
	 * Initializes the controller class.
	 */
	@FXML
	private Button logoutButton;
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}
	
	public void Logout(ActionEvent event) throws IOException, SQLException
	{
		UtilisateurService us = new UtilisateurService();
		//CupCake.user.setEtat_compte(EtatCompte.Inactif.ordinal());
		//us.changeEtatCompte(CupCake.user, EtatCompte.Inactif.ordinal());
		CupCake.user=null;
		System.out.println(CupCake.user);
		System.out.println("**************************************************************");
		//CupCake.user.toString();
		Parent root = FXMLLoader.load(getClass().getResource("/tn/esprit/cupcake/views/Authentification.fxml"));
                        Scene newScene= new Scene(root);
                        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        window.setScene(newScene);
                        window.show();
	}
	
}
