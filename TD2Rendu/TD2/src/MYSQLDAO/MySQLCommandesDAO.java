package MYSQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.CommandesDAO;
import Modele.Metier.Commandes;
import Vue.Connexion;

public class MySQLCommandesDAO implements CommandesDAO
{
	private static MySQLCommandesDAO Instance;

	public static MySQLCommandesDAO getInstance() 
	{
		if (Instance == null) {
			Instance = new MySQLCommandesDAO();
		}

		return Instance;
	}
	
	@Override
	public Commandes getById(int id) 
	{
		Commandes commandes = null;
		try 
		{
			Connection laConnexion = Connexion.getInstance().getMaConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("select * from Commande where id_commande=?");
			requete.setInt(1, id);
			ResultSet res = requete.executeQuery();
			if (res.next()) 
			{
				System.out.println(res.getInt("id_commande"));
				System.out.println(res.getDate("date_commande"));
				System.out.println(res.getInt("id_client"));
			}
		}
		catch (SQLException sqle) 
        {
            System.out.println("Pb dans select " + sqle.getMessage());
        }
		
		return commandes;
	}
	
	@Override
	public boolean create(Commandes objet) throws SQLException {
		Connection laConnexion = Connexion.getInstance().getMaConnexion();
    	PreparedStatement requete = laConnexion.prepareStatement("insert into Commande (date_commande, id_client) values (?,?)");
    	requete.setDate		(1, objet.getDate());
    	requete.setInt	(2, objet.getId_client());
    	int nbLignes = requete.executeUpdate();
        
        return nbLignes==1;
	}
	
	@Override
	public boolean update(Commandes objet) throws SQLException {
		Connection laConnexion = Connexion.getInstance().getMaConnexion();
    	PreparedStatement requete = laConnexion.prepareStatement("UPDATE Commande SET date_commande=?,id_client=? where id_commande=?");
    	requete.setDate		(1, objet.getDate());
    	requete.setInt		(2, objet.getId_client());
    	requete.setInt		(3, objet.getId_commande());
    	int nbLignes = requete.executeUpdate();
        
        return nbLignes==1;
	}

	@Override
	public boolean delete(Commandes objet) throws SQLException
	{
		Connection laConnexion = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("delete from Commande where id_commande=?");
		
		requete.setInt(1, objet.getId_commande());
		int nbLignes = requete.executeUpdate();
			
		return nbLignes==1;
	}

	@Override
	public ArrayList<Commandes> findAll() throws SQLException 
	{
    	ArrayList<String> donnees = new ArrayList<String>();
    	Connection laConnexion = Connexion.getInstance().getMaConnexion();
        Statement requete = laConnexion.createStatement();
        ResultSet res = requete.executeQuery("select * from Commande");
        while (res.next()) 
        {            
            String txt = ("\nCommande n°" + res.getInt("id_commande") + " à " + res.getDate("date_commande") + 
            		"\nClient n°" + res.getInt("id_client"));
            donnees.add(txt);
        }
        
        for (int i = 0; i < donnees.size(); i++) 
        {
			System.out.println(donnees.get(i));
		}
        
		return null;
	}
}
