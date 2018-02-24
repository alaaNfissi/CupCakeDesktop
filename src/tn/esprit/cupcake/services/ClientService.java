/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.cupcake.services;

import tn.esprit.cupcake.entities.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import tn.esprit.cupcake.utils.CupCakeDBConnection;

/**
 *
 * @author Alaa
 */
public class ClientService {
	
		Connection con = CupCakeDBConnection.getInstance().getConnection();
		private Statement stmt;
	
	public Client findById(long id) throws SQLException
	{
		Client c =null;
		String req="SELECT * FROM `utilisateur` WHERE id_utilisateur = ?";
		PreparedStatement pre = con.prepareStatement(req);
		pre.setLong(1, id);
		ResultSet rs = pre.executeQuery();
		while(rs.next())
		{
				c.setPassword(rs.getString("password"));
				c.setEmail("email");
				c.setPseudo(rs.getString("pseudo"));
				c.setEtat_compte(rs.getInt("etat_compte"));
				c.setNum_tel(rs.getInt("num_tel"));
				c.setNom(rs.getString("nom"));
				c.setPrenom(rs.getString("prenom"));
				c.setDate_naissance(rs.getString("date_naissance"));
				c.setRole(rs.getInt("role"));
				c.setAdresse(rs.getString("adresse"));
				c.setSexe(rs.getString("sexe"));
				c.setImage("image");
		}
		return c;
	}
	    public void ajouterClient(Client client) throws SQLException {
        String req = "INSERT INTO utilisateur values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setLong(1, client.getId_utilisateur());
        pre.setString(2, client.getPassword());
        pre.setString(3, client.getEmail());
        pre.setString(4, client.getPseudo());
        pre.setInt(5, client.getEtat_compte());
        pre.setInt(6, client.getNum_tel());
        pre.setString(7, client.getNom());
        pre.setString(8, client.getPrenom());
        pre.setString(9, client.getDate_naissance());
        pre.setInt(10, client.getRole());
        pre.setString(11, client.getAdresse());
        pre.setString(12, client.getSexe());
        pre.setString(13, client.getImage());
        pre.executeUpdate();
        Alert alertSucc = new Alert(Alert.AlertType.CONFIRMATION);
        alertSucc.setTitle("Succés");
        alertSucc.setContentText("Opération effectuer avec succés");
        alertSucc.setHeaderText(null);
        alertSucc.show();
    }

    public void modifierClient(Client client, long id) throws SQLException {
        String req = "UPDATE utilisateur SET password=?,email = ? ,pseudo = ? ,num_tel=?, nom=?, prenom=?, date_naissance=?, adresse=?,sexe=? WHERE id_utilisateur = ?";
        PreparedStatement ste = con.prepareStatement(req);
        //Dans l'ordre
        ste.setString(1,client.getPassword());
        ste.setString(2, client.getEmail());
        ste.setString(3, client.getPseudo());
        ste.setInt(4, client.getNum_tel());
        ste.setString(5, client.getNom());
        ste.setString(6, client.getPrenom());
        ste.setString(7, client.getDate_naissance());
        ste.setString(8, client.getAdresse());
        ste.setString(9, client.getSexe());
        ste.setLong(10, id);
        ste.executeUpdate();
        Alert alertSucc = new Alert(Alert.AlertType.CONFIRMATION);
        alertSucc.setTitle("Succés");
        alertSucc.setContentText("Opération effectuer avec succés");
        alertSucc.setHeaderText(null);
        alertSucc.show();
    }

    public List<Client> selectClient() throws SQLException {
        List<Client> listClient = new ArrayList<>();
        String req = "SELECT * FROM utilisateur where id_utilisateur=12";
        PreparedStatement ste = con.prepareStatement(req);
        ResultSet rs = ste.executeQuery();
        while (rs.next()) {
            listClient.add(new Client(
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("pseudo"),
                    rs.getInt("num_tel"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("date_naissance"),
                    rs.getString("adresse"),
                    rs.getString("sexe"),
                    rs.getString("image")
                    ));
                    System.out.println("test1"+rs.getString("sexe"));
        }
        System.out.println("test1"+listClient.get(0));
        return listClient;

    }
	
}
