package MYSQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.CategoriesDAO;
import Modele.Metier.Categories;
import Vue.Connexion;

public class MySQLCategoriesDAO implements CategoriesDAO
{

	private static MySQLCategoriesDAO Instance;
	
	private MySQLCategoriesDAO() {}
	
	public static MySQLCategoriesDAO getInstance() {

		if (Instance == null) 
		{
			Instance = new MySQLCategoriesDAO();
		}
		return Instance;
	}

	@Override
	public Categories getById(int id) 
	{
		Categories categories = null;
		try 
		{
			Connection laConnexion = Connexion.getInstance().getMaConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("select * from Categorie where id_categorie=?");
			requete.setInt(1, id);
			ResultSet res = requete.executeQuery();
			if (res.next()) 
			{
				System.out.println(res.getInt("id_categorie"));
				System.out.println(res.getString("titre"));
				System.out.println(res.getString("visuel"));
			}
		}
		catch (SQLException sqle) 
        {
            System.out.println("Pb dans select " + sqle.getMessage());
        }
		
		return categories;
	}

	@Override
	public boolean create(Categories objet) throws SQLException
	{
      Connection laConnexion = Connexion.getInstance().getMaConnexion();
      PreparedStatement requete = laConnexion.prepareStatement("insert into Categorie (titre,visuel) values (?,?)");
        	
      requete.setString(1, objet.getTitre());
      requete.setString(2, objet.getVisuel());
      int nbLignes = requete.executeUpdate();
      
      return nbLignes==1;
	}

	@Override
	public boolean update(Categories objet) throws SQLException
	{
		Connection laConnexion = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("update Categorie set titre=?,visuel=? where id_categorie=?");
    	requete.setString	(1, objet.getTitre());
    	requete.setString	(2, objet.getVisuel());
    	requete.setInt		(3, objet.getId());
    	int nbLignes = requete.executeUpdate();
        
        return nbLignes==1;
	}

	@Override
	public boolean delete(Categories objet) throws SQLException
	{
		Connection laConnexion = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("delete from Categorie where id_categorie=?");
		
		requete.setInt(1, objet.getId());
		int nbLignes = requete.executeUpdate();
			
		return nbLignes==1;
	}

	public ArrayList<Categories> findAll() throws SQLException 
	{
		ArrayList<Categories> donnees = new ArrayList<Categories>();
		Connection laConnexion = Connexion.getInstance().getMaConnexion();
		Statement requete = laConnexion.createStatement();
        ResultSet res = requete.executeQuery("SELECT * FROM Categorie");
        while (res.next()) 
        {
        	Categories txt = new Categories(res.getInt("id_categorie"), res.getString("titre"), res.getString("visuel"));
            donnees.add(txt);
        }
		
		return donnees;
	}
}
