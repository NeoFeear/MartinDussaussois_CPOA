package mysqldao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;

import dao.CategorieDAO;
import modele.metier.Categorie;

public class MySQLCategorieDAO implements CategorieDAO
{
	private static MySQLCategorieDAO Instance;
	
	private MySQLCategorieDAO() {}
	
	public static MySQLCategorieDAO getInstance() {

		if (Instance == null) 
		{
			Instance = new MySQLCategorieDAO();
		}
		return Instance;
	}

	@Override
	public Categorie getById(int id) throws SQLException, InvalidPropertiesFormatException, IOException
	{
		Categorie categories = null;
		Connection laConnexion = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Categorie where id_categorie=?");
		
		requete.setInt(1, id);
		ResultSet res = requete.executeQuery();
		
		if (res.next()) 
		{
			categories = new Categorie(id,res.getString("titre"), res.getString("visuel"));
		}
		return categories;
	}

	@Override
	public boolean create(Categorie objet) throws SQLException, InvalidPropertiesFormatException, IOException
	{
      Connection laConnexion = Connexion.getInstance().getMaConnexion();
      PreparedStatement requete = laConnexion.prepareStatement("INSERT INTO Categorie (titre,visuel) values (?,?)",  Statement.RETURN_GENERATED_KEYS);
        	
      requete.setString(1, objet.getTitre());
      requete.setString(2, objet.getVisuel());
      int nbLignes = requete.executeUpdate();
      
      ResultSet res = requete.getGeneratedKeys();
      if(res.next()) {
    	  int id = res.getInt(1);
    	  objet.setId(id);
      }
      
      return nbLignes==1;
	}

	@Override
	public boolean update(Categorie objet) throws SQLException, InvalidPropertiesFormatException, IOException
	{
		Connection laConnexion = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("UPDATE Categorie SET titre=?,visuel=? WHERE id_categorie=?");
    	requete.setString	(1, objet.getTitre());
    	requete.setString	(2, objet.getVisuel());
    	requete.setInt		(3, objet.getId());
    	int nbLignes = requete.executeUpdate();
        
        return nbLignes==1;
	}

	@Override
	public boolean delete(Categorie objet) throws SQLException, InvalidPropertiesFormatException, IOException
	{
		Connection laConnexion = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("DELETE FROM Categorie WHERE id_categorie=?");
		
		requete.setInt(1, objet.getId());
		int nbLignes = requete.executeUpdate();
			
		return nbLignes==1;
	}

	public ArrayList<Categorie> findAll() throws SQLException, InvalidPropertiesFormatException, IOException 
	{
		ArrayList<Categorie> donnees = new ArrayList<Categorie>();
		Connection laConnexion = Connexion.getInstance().getMaConnexion();
		Statement requete = laConnexion.createStatement();
        ResultSet res = requete.executeQuery("SELECT * FROM Categorie");
        while (res.next()) 
        {
        	Categorie txt = new Categorie(res.getInt("id_categorie"), res.getString("titre"), res.getString("visuel"));
            donnees.add(txt);
        }
		
		return donnees;
	}
}
