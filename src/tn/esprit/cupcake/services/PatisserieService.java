/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.cupcake.services;

import tn.esprit.cupcake.entities.Patisserie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.cupcake.utils.CupCakeDBConnection;

/**
 *
 * @author Alaa
 */
public class PatisserieService {
	Connection con = CupCakeDBConnection.getInstance().getConnection();
    private Statement stmt;
	 public PatisserieService() {
        try {
            if (con != null) {
                stmt = con.createStatement();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
	public Patisserie searchById(int id) throws SQLException
	{
		String req="select * from patisserie where id_patisserie =?";
		PreparedStatement pre=con.prepareStatement(req);
		pre.setInt(1,id);
		ResultSet rs= pre.executeQuery();
		while(rs.next())
		{
			Patisserie patisserie=new Patisserie();
			patisserie.setId_patisserie(rs.getInt("id_patisserie"));
			patisserie.setLibelle_patisserie(rs.getString("libelle_patisserie"));
			patisserie.setAdresse_patisserie(rs.getString("adresse_patisserie"));
			patisserie.setDate_creation(rs.getString("date_creation"));
			patisserie.setSpecialite(rs.getString("specialite"));
			patisserie.setCompte_facebook(rs.getString("compte_facebook"));
			patisserie.setCompte_instagram(rs.getString("compte_instagram"));
			patisserie.setDescription(rs.getString("description"));
			patisserie.setImage(rs.getString("image"));
			return patisserie;
		}
		return null;
	}
	
	public String LibellePatisserie(int id_patisserie) throws SQLException
	{
		String req= "Select libelle_patisserie as libellePatisserie from patisserie where id_patisserie =?";
		PreparedStatement pre= con.prepareStatement(req);
		pre.setInt(1,id_patisserie);
		ResultSet rs = pre.executeQuery();
		while(rs.next())
		{
			return rs.getString("libellePatisserie");
		}
		return "";
	}
	
	public Patisserie searchByIdPatissier(long id_patissier) throws SQLException
	{
		String req ="select * from patisserie where id_utilisateur = ?";
		PreparedStatement pre = con.prepareStatement(req);
		pre.setLong(1, id_patissier);
		ResultSet rs = pre.executeQuery();
		while(rs.next())
		{
			Patisserie patisserie = new Patisserie();
			patisserie.setId_patisserie(rs.getInt("id_patisserie"));
			patisserie.setLibelle_patisserie(rs.getString("libelle_patisserie"));
			patisserie.setAdresse_patisserie(rs.getString("adresse_patisserie"));
			patisserie.setDate_creation(rs.getString("date_creation"));
			patisserie.setSpecialite(rs.getString("specialite"));
			patisserie.setCompte_facebook(rs.getString("compte_facebook"));
			patisserie.setCompte_instagram(rs.getString("compte_instagram"));
			patisserie.setDescription(rs.getString("description"));
			patisserie.setImage(rs.getString("image"));
			patisserie.setPatissier(rs.getLong("id_utilisateur"));
			return patisserie;
		}
		return null;
	}
}
