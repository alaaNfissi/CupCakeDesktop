/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.cupcake.views;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import java.net.URL;
import tn.esprit.cupcake.APIs.Mail.Mail;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.User;
import com.sun.javafx.font.FontConstants;
import java.net.URL;
import tn.esprit.cupcake.entities.Client;
import tn.esprit.cupcake.entities.EtatCompte;
import tn.esprit.cupcake.entities.TypeRole;
import tn.esprit.cupcake.entities.Utilisateur;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import tn.esprit.cupcake.services.ClientService;
import tn.esprit.cupcake.services.UtilisateurService;




/**
 * FXML Controller class
 *
 * @author Alaa
 */
public class AuthentificationController implements Initializable {

	/**
	 * Initializes the controller class.
	 */
	@FXML
	private Button seConnecterButton;
	@FXML
	private Button facebookButton;
	@FXML 
	private TextField pseudoTextField;
	@FXML 
	private PasswordField passwordTexField;
	@FXML Label message;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}
	@FXML
	public void SeConnecter(ActionEvent event) throws IOException, SQLException {
		if(validateInputs()){
		UtilisateurService us = new UtilisateurService();
		String pseudo = pseudoTextField.getText();
		String password = passwordTexField.getText();
		System.out.println("*******test******");
		Utilisateur u = us.searchByPseudoPass(pseudo, password);
		System.out.println(u);
		System.out.println("OK !");
		if(u!=null)
		{
			u.setEtat_compte(EtatCompte.Actif.ordinal());
			if (u.getRole()==TypeRole.Patisssier.ordinal()) {
				CupCake.user=u;
				Parent root = FXMLLoader.load(getClass().getResource("HomeFXML.fxml"));
                        Scene newScene= new Scene(root);
                        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        window.setScene(newScene);
                        window.show();
			}
			else if (u.getRole()==TypeRole.Client.ordinal()) {
				Client c =new Client();
				ClientService cs =new ClientService();
				c=cs.findById(u.getId_utilisateur());
				CupCake.user=c;
				Parent root = FXMLLoader.load(getClass().getResource("HomeFXML.fxml"));
                        Scene newScene= new Scene(root);
                        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        window.setScene(newScene);
                        window.show();
			}
			else {
				CupCake.user=u;
				Parent root = FXMLLoader.load(getClass().getResource("HomeFXML.fxml"));
                        Scene newScene= new Scene(root);
                        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        window.setScene(newScene);
                        window.show();
			}
			Task sendingMessage = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    
                    // ClientAbonne conduct;
                    //conduct=cas.findById(Table_demande.getSelectionModel().getSelectedItem().getId_ut());
                    //String destinataire = conduct.getEmail();
                    Mail mail = new Mail();
                    String submail = "Authentification faite avec succès";
                    String contentmail = "L mail hetha bel CupCake :D hhhhhhhhhhhhh Votre authentification a été faite avec succès, bienvenue à CupCake "+u.getNom()+" "+u.getPrenom();
                    System.out.println("mail test");
					mail.sendMail("alaa.nfissi@gmail.com", "Nfissi2014", u.getEmail(), submail, contentmail);
					System.out.println("mail sent");
                    return null;
                }
            };
            new Thread(sendingMessage).start();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Votre authentification a été faite avec succès ;) ");
            alert.showAndWait();
		}
		}
	}
	
	public void seConnecterAvecFacebook(ActionEvent event) throws UnsupportedEncodingException, SQLException, IOException{
		String domain ="https://localhost";
		String appId = "563976137303485";
		String appSecret ="42e3ac786b4dfd94b4599aa2e33d61dc";
		//String appSecret ="42e3ac786b4dfd94b4599aa2e33d61dc";
		//String authUrl ="https://graph.facebook.com/me/access_token?client_id="+appId+"&redirect_uri="+URLEncoder.encode("http://"+appId+".appspot.com/signin_fb.do", "UTF-8")+"&scope=public_profile,email,user_birthday" ;
		String authUrl ="https://graph.facebook.com/oauth/authorize?type=user_agent&client_id="+appId+"&redirect_uri="+domain+"&scope=user_birthday,"
				+"user_religion_politics, user_relationships, user_relationship_details, user_hometown, user_location, user_likes,"
				+" user_education_history, user_work_history, user_website, user_events, user_photos, user_videos, user_friends, user_about_me,"
				+" user_status, user_games_activity, user_tagged_places, user_posts, rsvp_event, email, read_insights, publish_actions,"
				+" read_custom_friendlists, user_actions.video, user_actions.news, user_actions.fitness, user_actions.books, user_actions.music,"
				+" user_managed_groups, manage_pages, pages_manage_cta, pages_manage_instant_articles, pages_show_list, publish_pages,"
				+" read_page_mailboxes, ads_management, ads_read, business_management, pages_messaging, pages_messaging_phone_number,"
				+" pages_messaging_subscriptions, instagram_basic, instagram_manage_comments, instagram_manage_insights, public_profile";
               /* + "user_actions.books,user_actions.fitness,user_actions.music,user_actions.news,user_actions.video,user_activities,user_birthday,user_education_history,"
                + "user_events,user_photos,user_friends,user_games_activity,user_groups,user_hometown,user_interests,user_likes,user_location,user_photos,user_relationship_details,"
                + "user_relationships,user_religion_politics,user_status,user_tagged_places,user_videos,user_website,user_work_history,ads_management,ads_read,email,"
                + "manage_notifications,manage_pages,publish_actions,read_insights,read_mailbox,read_page_mailboxes,read_stream,rsvp_event" ;*/
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		//System.getProperties();
		//oauth
	
		WebDriver driver = new ChromeDriver();
		driver.get(authUrl);
		//driver.navigate().to(authUrl);
		//driver.navigate().to("https://localhost/");
		//"&client_secret="+appSecret+
		
		System.out.println("driver.getCurrentUrl();");
		String accessToken;
		String aToken ="EAAIA7u1TZAb0BABIBOg5b6PbNt0hbTbiat5SnDSFxZBjJZCdcbSpqQatMGKtKIOypUJBmy4jkzcZB5uX4ULMLfDUeZAziZAb7Sne8jlSrwqTFUnljXbZBLXPDmk0SE2IZAs2ZBZALwndojZCQQJWiZBOCsynQPXi7UGGsqJtZBfFZCRlMAugZDZD";
				/*FacebookClient fbClient1 = new DefaultFacebookClient(aToken);
		AccessToken exAccessToken = fbClient1.obtainExtendedAccessToken(appId,appSecret);
		System.out.println(exAccessToken.getAccessToken());
		System.out.println(exAccessToken.getExpires());*/
		while (true) {			
			if (!driver.getCurrentUrl().contains("facebook.com")) {
				//String url = driver.getCurrentUrl();
				//accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$l");
				//System.out.println(accessToken);
				String url = driver.getCurrentUrl();
				accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");
				System.out.println("test");
				
				
				driver.quit();
				
				//FacebookClient fbClient1 = new DefaultFacebookClient;
				FacebookClient fbClient = new DefaultFacebookClient(accessToken, Version.LATEST);
				String fields ="name,first_name,last_name,age_range,birthday,email,gender,address";
				User user = fbClient.fetchObject("me", User.class,Parameter.with("fields", fields));
				driver.quit();
				System.out.println(user.getName());
				System.out.println(user.toString());
				message.setText(user.getName()+" "+user.getFirstName()+" "+user.getLastName()+" "+user.getEmail()+" "+user.getBirthday()+" "+user.getId());
				UtilisateurService us =new UtilisateurService();
				/*try {
					int i =Integer.parseInt(user.getId());
					System.out.println(i);
					Utilisateur u = us.searchUserById(i);
				}catch (NumberFormatException e) {
				  //e.getStackTrace();
				  System.out.println("error");
			  }*/
					
				if(us.searchUserById(Long.parseLong(user.getId()))!=null)
				{
					Utilisateur u =us.searchUserById(Long.parseLong(user.getId()));
					u.setEtat_compte(EtatCompte.Actif.ordinal());
					if (u.getRole()==TypeRole.Patisssier.ordinal()) {
						CupCake.user=u;
						/*Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
								Scene newScene= new Scene(root);
								Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
								window.setScene(newScene);
								window.show();*/
					}
					else if (u.getRole()==TypeRole.Client.ordinal()) {
						Client c =new Client();
						ClientService cs =new ClientService();
						c=cs.findById(u.getId_utilisateur());
						CupCake.user=c;
						Parent root = FXMLLoader.load(getClass().getResource("HomeFXML.fxml"));
								Scene newScene= new Scene(root);
								Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
								window.setScene(newScene);
								window.show();
					}
					else {
						CupCake.user=u;
						Parent root = FXMLLoader.load(getClass().getResource("HomeFXML.fxml"));
								Scene newScene= new Scene(root);
								Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
								window.setScene(newScene);
								window.show();
					}
					
				}
				else
				{
					Utilisateur u=new Utilisateur(Long.parseLong(user.getId()),user.getId()+"cupcake", user.getEmail(), user.getFirstName().toLowerCase()+"_"+user.getLastName().toLowerCase(), EtatCompte.Actif.ordinal(), user.getLastName(), user.getFirstName(), user.getBirthday());
					CupCake.user=u;
					/*u.setId_utilisateur(Long.parseLong(user.getId()));
					u.setPassword(user.getId()+"cupcake");
					u.setEmail(user.getEmail());
					u.setPseudo(user.getFirstName()+"_"+user.getLastName());
					u.setEtat_compte(EtatCompte.Actif.ordinal());
					u.setNom(user.getLastName());
					u.setPrenom(user.getFirstName());
					u.setDate_naissance(user.getBirthday());*/
					us.ajouterUtilisateur(u);
					Parent root = FXMLLoader.load(getClass().getResource("PreInscriptionFXML.fxml"));
                        Scene newScene= new Scene(root);
                        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        window.setScene(newScene);
                        window.show();
				}
			  }
		}
	}
	
	private boolean validateInputs() {
        if (pseudoTextField.getText().isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veillez saisir votre Pseudo");
            alert1.setHeaderText(null);
            alert1.show();
            return false;
        }
		if(passwordTexField.getText().isEmpty())
		{
			Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veillez saisir votre Password");
            alert1.setHeaderText(null);
            alert1.show();
            return false;
		}
        return true;
    }
}
