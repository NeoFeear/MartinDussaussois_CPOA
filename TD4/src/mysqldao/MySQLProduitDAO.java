package mysqldao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;

import dao.ProduitDAO;
import modele.metier.Produit;

public class MySQLProduitDAO implements ProduitDAO
{

	private static MySQLProduitDAO Instance;
	
	private MySQLProduitDAO() {}
	
	public static MySQLProduitDAO getInstance() 
	{
		if (Instance == null) 
		{
			Instance = new MySQLProduitDAO();
		}
		return Instance;
	}

	@Override
	public Produit getById(int id) throws SQLException, InvalidPropertiesFormatException, IOException 
	{
		Produit produits = null;
		Connection laConnexion = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Produit WHERE id_produit=?");
		requete.setInt(1, id);
		ResultSet res = requete.executeQuery();
		if (res.next()) 
		{ 
			produits = new Produit(id, res.getString("nom"), res.getString("description"), res.getDouble("tarif"), res.getString("visuel"), MySQLCategorieDAO.getInstance().getById(res.getInt("id_categorie")));
		}
		return produits;
	}

	@Override
	public boolean create(Produit objet) throws SQLException, InvalidPropertiesFormatException, IOException {
		Connection laConnexion = Connexion.getInstance().getMaConnexion();
    	PreparedStatement requete = laConnexion.prepareStatement("INSERT INTO Produit (nom,description,tarif,visuel,id_categorie) values (?,?,?,?,?)",  Statement.RETURN_GENERATED_KEYS);
    	requete.setString	(1, objet.getNom());
    	requete.setString	(2, objet.getDescription());
    	requete.setDouble	(3, objet.getTarif());
    	requete.setString	(4, objet.getVisuel());
    	requete.setInt		(5, objet.getCategorie().getId());
    	int nbLignes = requete.executeUpdate();
        
    	ResultSet res = requete.getGeneratedKeys();
        if(res.next()) {
      	  int id = res.getInt(1);
      	  objet.setId(id);
        }
        
        return nbLignes==1;
	}

	@Override
	public boolean update(Produit objet) throws SQLException, InvalidPropertiesFormatException, IOException {
		Connection laConnexion = Connexion.getInstance().getMaConnexion();
    	PreparedStatement requete = laConnexion.prepareStatement("UPDATE Produit SET nom=?,description=?,tarif=?,visuel=?,id_categorie=? where id_produit=?");
    	requete.setString	(1, objet.getNom());
    	requete.setString	(2, objet.getDescription());
    	requete.setDouble	(3, objet.getTarif());
    	requete.setString	(4, objet.getVisuel());
    	requete.setInt		(5, objet.getCategorie().getId());
    	requete.setInt		(6, objet.getId());
    	int nbLignes = requete.executeUpdate();
        
        return nbLignes==1;
	}

	@Override
	public boolean delete(Produit objet) throws SQLException, InvalidPropertiesFormatException, IOException
	{
		Connection laConnexion = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("DELETE FROM Produit where id_produit=?");
		
		requete.setInt(1, objet.getId());
		int nbLignes = requete.executeUpdate();
			
		return nbLignes==1;
	}

	public ArrayList<Produit> findAll() throws SQLException, IOException
	{
    	ArrayList<Produit> donnees = new ArrayList<Produit>();
		Connection laConnexion = Connexion.getInstance().getMaConnexion();
        Statement requete = laConnexion.createStatement();
        ResultSet res = requete.executeQuery("select * from Produit");
        while (res.next()) 
        {            
            Produit txt = new Produit(res.getInt("id_produit"), res.getString("nom"), res.getString("description"), res.getDouble("tarif"), 
            		res.getString("visuel"), MySQLCategorieDAO.getInstance().getById(res.getInt("id_categorie")));  
            donnees.add(txt);
        }  
        
		return donnees;
	}
}
