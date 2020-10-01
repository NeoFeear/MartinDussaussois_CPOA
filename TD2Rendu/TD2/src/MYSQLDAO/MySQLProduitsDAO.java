package MYSQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.ProduitsDAO;
import Modele.Metier.Produits;
import Vue.Connexion;

public class MySQLProduitsDAO implements ProduitsDAO
{

	private static MySQLProduitsDAO Instance;
	
	private MySQLProduitsDAO() {}
	
	public static MySQLProduitsDAO getInstance() 
	{
		if (Instance == null) 
		{
			Instance = new MySQLProduitsDAO();
		}
		return Instance;
	}

	@Override
	public Produits getById(int id) 
	{
		Produits produits = null;
		try 
		{
			Connection laConnexion = Connexion.getInstance().getMaConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("select * from Produit where id_produit=?");
			requete.setInt(1, id);
			ResultSet res = requete.executeQuery();
			if (res.next()) 
			{
				System.out.println(res.getInt("id_produit"));
				System.out.println(res.getString("nom"));
				System.out.println(res.getString("description"));
				System.out.println(res.getDouble("tarif"));
				System.out.println(res.getString("visuel"));
				System.out.println(res.getInt("id_categorie"));
			}
		}
		catch (SQLException sqle) 
        {
            System.out.println("Pb dans select " + sqle.getMessage());
        }
		
		return produits;
	}

	@Override
	public boolean create(Produits objet) throws SQLException {
		Connection laConnexion = Connexion.getInstance().getMaConnexion();
    	PreparedStatement requete = laConnexion.prepareStatement("insert into Produit (nom,description,tarif,visuel,id_categorie) values (?,?,?,?,?)");
    	requete.setString	(1, objet.getNom());
    	requete.setString	(2, objet.getDescription());
    	requete.setDouble	(3, objet.getTarif());
    	requete.setString	(4, objet.getVisuel());
    	requete.setInt		(5, objet.getId_categorie());
    	int nbLignes = requete.executeUpdate();
        
        return nbLignes==1;
	}

	@Override
	public boolean update(Produits objet) throws SQLException {
		Connection laConnexion = Connexion.getInstance().getMaConnexion();
    	PreparedStatement requete = laConnexion.prepareStatement("UPDATE Produit SET nom=?,description=?,tarif=?,visuel=?,id_categorie=? where id_produit=?");
    	requete.setString	(1, objet.getNom());
    	requete.setString	(2, objet.getDescription());
    	requete.setDouble	(3, objet.getTarif());
    	requete.setString	(4, objet.getVisuel());
    	requete.setInt		(5, objet.getId_categorie());
    	requete.setInt		(6, objet.getId());
    	int nbLignes = requete.executeUpdate();
        
        return nbLignes==1;
	}

	@Override
	public boolean delete(Produits objet) throws SQLException
	{
		Connection laConnexion = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("delete from Produit where id_produit=?");
		
		requete.setInt(1, objet.getId());
		int nbLignes = requete.executeUpdate();
			
		return nbLignes==1;
	}

	public ArrayList<Produits> findAll() throws SQLException 
	{
    	ArrayList<Produits> donnees = new ArrayList<Produits>();
		Connection laConnexion = Connexion.getInstance().getMaConnexion();
        Statement requete = laConnexion.createStatement();
        ResultSet res = requete.executeQuery("select * from Produit");
        while (res.next()) 
        {            
            Produits txt = new Produits(res.getInt("id_produit"), res.getString("nom"), res.getString("description"), res.getDouble("tarif"), 
            		res.getString("visuel"), res.getInt("id_categorie"));  
            donnees.add(txt);
        }  
        
		return donnees;
	}
}
