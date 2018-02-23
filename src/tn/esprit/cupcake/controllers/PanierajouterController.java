/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.cupcake.controllers;
import tn.esprit.cupcake.views.CupCake;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.DefaultListModel;
import tn.esprit.cupcake.entities.Panier;
import tn.esprit.cupcake.entities.Utilisateur;
import tn.esprit.cupcake.services.CommandeService;
import tn.esprit.cupcake.services.PanierService;
import tn.esprit.cupcake.services.PatisserieService;
import tn.esprit.cupcake.services.UtilisateurService;
import tn.esprit.cupcake.views.CupCake;

/**
 * FXML Controller class
 *
 * @author berber
 */
public class PanierajouterController implements Initializable {

    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private ListView list2;

    /**
     * Initializes the controller class.
     */
    DefaultListModel dim = new DefaultListModel();

    @FXML
    private ListView list1;
    @FXML
    private TextField textprix;
    @FXML
    private TextField textlibelle;
    @FXML
    private TextField textptixtotal;
	@FXML
	private Button commanderbtn;
	

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PanierService pn = new PanierService();
        List<String> list = new ArrayList<>();
        list = pn.afficheProduit();
        ObservableList<String> names = FXCollections.observableArrayList(list);
        list1.setItems(names);
        textptixtotal.setText("0");
        textprix.setText("0");
       
    }

    @FXML
    private void ajouterPanier(MouseEvent event) throws SQLException {

        list2.getItems().add((list1.getSelectionModel().getSelectedItem()));
         String selectedProduit = list1.getSelectionModel().getSelectedItem().toString();
        PanierService pn = new PanierService();
        int idProduit = pn.RecupProduitId(selectedProduit);
        Panier panier = new Panier(5,16 , 12, idProduit);
        pn.ajouterProduitPanier(panier);
        System.out.println(selectedProduit+" a été ajouter avec succés dans votre Panier");
        
        
        //textptixtotal.setText(Float.toString(Float.parseFloat(textprix.getText())+Float.parseFloat(textptixtotal.getText())));
        
        /*String selectedProduit2=list2.getSelectionModel().getSelectedItem().toString();
        float prix = pn.affichePrix(selectedProduit2);
        System.out.println("prix est "+prix);
        String a = textlibelle.getText();
        float b = Float.parseFloat(a);
        textptixtotal.setText(Float.toString(b+prix));*/
        
    }

    @FXML
    private void supprimerPanier(MouseEvent event) throws SQLException {
        String selectedProduit = list2.getSelectionModel().getSelectedItem().toString();
        list2.getItems().remove(list2.getSelectionModel().getSelectedItem());
        PanierService pn = new PanierService();
int idProduit = pn.RecupProduitId(selectedProduit);
 Panier panier = new Panier();
 pn.supprimerProduitPanier(idProduit);
 System.out.println(selectedProduit+" a été supprimer avec succés de votre Panier");

 
    }

    @FXML
    private void prixProduit(MouseEvent event) throws SQLException {
        
        String selectedProduit = list1.getSelectionModel().getSelectedItem().toString();
        PanierService pn1PanierService = new PanierService();
        float a = 0;
        try {
             a = pn1PanierService.affichePrix(selectedProduit);
             
        } catch (SQLException ex) {
            Logger.getLogger(PanierajouterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        textprix.setText(Float.toString(a)+"DT");
        
        String b = pn1PanierService.RecupLibelle(selectedProduit);
        textlibelle.setText(b);
       
    }

    public void versCommande(ActionEvent event) throws IOException, SQLException
	{
		PatisserieService ps=new PatisserieService();
		UtilisateurService us =new UtilisateurService();
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmer votre mode de payement");
		alert.setHeaderText("Choisissez un mode parmis ces deux .");
		alert.setContentText("Choose your option.");

		ButtonType buttonTypeOne = new ButtonType("à la livraison");
		ButtonType buttonTypeTwo = new ButtonType("En ligne");
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne){
			System.out.println("à la livraison");
			CommandeService cs = new CommandeService();
			System.out.println("**********************************************");
			cs.ajouterCommande(ps.searchById(1), us.searchUserById(12));
			Parent root = FXMLLoader.load(getClass().getResource("/tn/esprit/cupcake/views/CommandeFXML.fxml"));
                        Scene newScene= new Scene(root);
                        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        window.setScene(newScene);
                        window.show();
		} else if (result.get() == buttonTypeTwo) {
			System.out.println("En ligne");
		} else {
			Alert alert1 = new Alert(AlertType.ERROR);
			alert1.setTitle("Info !");
			alert1.setHeaderText("Pas de choix selectionné");
			alert1.setContentText("Vous devez choisir un mode de payement !");

			alert1.showAndWait();
		}
		
		
	}
	
	
    
    
    
    
    

}

