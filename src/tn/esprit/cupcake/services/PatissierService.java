/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.cupcake.services;

import tn.esprit.cupcake.entities.Patissier;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import tn.esprit.cupcake.utils.CupCakeDBConnection;


/**
 *
 * @author Basly
 */
public class PatissierService {
    Connection con = CupCakeDBConnection.getInstance().getConnection();
    private Statement stmt;

    public PatissierService() {
        try {
            if(con != null){
            stmt = con.createStatement();}
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void ajouterPatissier(Patissier patissier) throws SQLException {
        String req = "INSERT INTO utilisateur (id_utilisateur,password,email,pseudo,etat_compte,num_tel,nom,prenom,date_naissance,role) values(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setLong(1,patissier.getId_utilisateur());
        pre.setString(2,patissier.getPassword());
        pre.setString(3,patissier.getEmail());
        pre.setString(4,patissier.getPseudo());
        pre.setInt(5,patissier.getEtat_compte());
        pre.setInt(6,patissier.getNum_tel());
        pre.setString(7,patissier.getNom());
        pre.setString(8,patissier.getPrenom());
        pre.setString(9,patissier.getDate_naissance());
        pre.setInt(10,patissier.getRole());     
        pre.executeUpdate();
        System.out.println("Patissier ajouté");
    }
    
    public void modifierPatissier(Patissier patissier, int id) throws SQLException {
        String req = "UPDATE utilisateur SET password=?,email = ? ,pseudo = ? ,num_tel=?, nom=?, prenom=?, date_naissance=?  WHERE id_utilisateur = ?";
        PreparedStatement ste = con.prepareStatement(req);
        //Dans l'ordre
        ste.setString(1,patissier.getPassword());
        ste.setString(2, patissier.getEmail());
        ste.setString(3, patissier.getPseudo());
        ste.setInt(4, patissier.getNum_tel());
        ste.setString(5, patissier.getNom());
        ste.setString(6, patissier.getPrenom());
        ste.setString(7, patissier.getDate_naissance());
        ste.setInt(8, id);
        ste.executeUpdate();
        Alert alertSucc = new Alert(Alert.AlertType.CONFIRMATION);
        alertSucc.setTitle("Succés");
        alertSucc.setContentText("Opération effectuer avec succés");
        alertSucc.setHeaderText(null);
        alertSucc.show();
    }
    
     public long selectPatissierAjouter (Patissier patissier) throws SQLException {
     String req ="SELECT * FROM utilisateur where email=?";
     PreparedStatement ste = con.prepareStatement(req);
     ste.setString(1,patissier.getEmail()) ;
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                return rs.getLong("id_utilisateur") ;
            }
            return -1 ;
     }
     
     public List<Patissier> selectPatissier() throws SQLException {
        List<Patissier> listPatissier = new ArrayList<>();
        String req = "SELECT * FROM utilisateur where id_utilisateur=15";
        PreparedStatement ste = con.prepareStatement(req);
        ResultSet rs = ste.executeQuery();
        while (rs.next()) {
            listPatissier.add(new Patissier(
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("pseudo"),
                    rs.getInt("num_tel"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("date_naissance")
                    ));
        }
        return listPatissier;
    }
     public List<Patissier> selectPatissierEnAttente() throws SQLException {
        List<Patissier> listPatissier = new ArrayList<>();
        //ObservableList  <Patissier> listPatissier =FXCollections.observableArrayList() ;
        String req = "SELECT * FROM utilisateur where etat_compte=0";
        PreparedStatement ste = con.prepareStatement(req);
        ResultSet rs = ste.executeQuery();
        while (rs.next()) {
            listPatissier.add(new Patissier(
                    rs.getLong("id_utilisateur"),
                    rs.getString("email"),
                    rs.getInt("etat_compte"),
                    rs.getInt("num_tel"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("date_naissance")
                    ));
        }
        return listPatissier;
    }
    public void ConfirmerInscription (long id) throws SQLException {
        String req = "UPDATE utilisateur SET etat_compte=1  WHERE id_utilisateur = ?";
        PreparedStatement ste = con.prepareStatement(req);
        ste.setLong(1, id);
        ste.executeUpdate();
        Alert alertSucc = new Alert(Alert.AlertType.CONFIRMATION);
        alertSucc.setTitle("Succés");
        alertSucc.setContentText("Inscription confirmer");
        alertSucc.setHeaderText(null);
        alertSucc.show();
    }
        public  void supprimerPatissier(long id)throws SQLException  {
        String req = "DELETE FROM utilisateur WHERE id_utilisateur =?";
        PreparedStatement ste = con.prepareStatement(req);
        ste.setLong(1, id);
        ste.executeUpdate();
        Alert alertSucc = new Alert(Alert.AlertType.CONFIRMATION);
        alertSucc.setTitle("Succés");
        alertSucc.setContentText("Inscription supprimer");
        alertSucc.setHeaderText(null);
        alertSucc.show();
    }
     

}
