package MYSQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.ClientsDAO;
import Modele.Metier.Clients;
import Modele.Metier.Produits;
import Vue.Connexion;

public class MySQLClientsDAO implements ClientsDAO
{

	private static MySQLClientsDAO Instance;
	
	private MySQLClientsDAO() {}
	
	public static MySQLClientsDAO getInstance() 
	{
		if (Instance == null) {
			Instance = new MySQLClientsDAO();
		}

		return Instance;
	}

	@Override
	public Clients getById(int id) 
	{
		Clients clients = null;
		try 
		{
			Connection laConnexion = Connexion.getInstance().getMaConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("select * from Client where id_client=?");
			requete.setInt(1, id);
			ResultSet res = requete.executeQuery();
			if (res.next()) 
			{
				System.out.println(res.getInt("id_client"));
				System.out.println(res.getString("nom"));
				System.out.println(res.getString("prenom"));
				System.out.println(res.getString("identifiant"));
				System.out.println(res.getString("mot_de_passe"));
				System.out.println(res.getInt("adr_numero"));
				System.out.println(res.getString("adr_voie"));
				System.out.println(res.getInt("adr_code_postal"));
				System.out.println(res.getString("adr_ville"));
				System.out.println(res.getString("adr_pays"));
				clients = new Clients(id, res.getString("nom"), res.getString("prenom"), res.getString("identifiant"), res.getString("mot_de_passe"), res.getInt("adr_numero"), res.getString("adr_voie"), res.getInt("adr_code_postal"), res.getString("adr_ville"), res.getString("adr_pays"));
			}
		}
		catch (SQLException sqle) 
        {
            System.out.println("Pb dans select " + sqle.getMessage());
        }
		
		return clients;
	}

	@Override
	public boolean create(Clients objet) throws SQLException 
	{
		Connection laConnexion = Connexion.getInstance().getMaConnexion();
    	PreparedStatement requete = laConnexion.prepareStatement("insert into Client (nom,prenom,identifiant,mot_de_passe,adr_numero,adr_voie,adr_code_postal,adr_ville,adr_pays) values (?,?,?,?,?,?,?,?,?)",  Statement.RETURN_GENERATED_KEYS);
    	requete.setString	(1, objet.getNom());
    	requete.setString	(2, objet.getPrenom());
    	requete.setString	(3, objet.getIdentifiant());
    	requete.setString	(4, objet.getMdp());
    	requete.setInt		(5, objet.getNum_adresse());
    	requete.setString	(6, objet.getVoie_adresse());
    	requete.setInt		(7, objet.getCp());
    	requete.setString	(8, objet.getVille());
    	requete.setString	(9, objet.getPays());
    	int nbLignes = requete.executeUpdate();
        
    	ResultSet res = requete.getGeneratedKeys();
        if(res.next()) {
      	  int id = res.getInt(1);
      	  objet.setId(id);
        }
        
        return nbLignes==1;
	}

	@Override
	public boolean update(Clients objet) throws SQLException 
	{
		Connection laConnexion = Connexion.getInstance().getMaConnexion();
    	PreparedStatement requete = laConnexion.prepareStatement("update Client set nom=?,prenom=?, identifiant=? where id_client=?");
    	requete.setString	(1, objet.getNom());
    	requete.setString	(2, objet.getPrenom());
    	requete.setString	(3, objet.getIdentifiant());
    	requete.setInt		(4, objet.getId());
    	int nbLignes = requete.executeUpdate();
        
        return nbLignes==1;
	}

	@Override
	public boolean delete(Clients objet) throws SQLException 
	{
		Connection laConnexion = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("delete from Client where id_client=?");
		
		requete.setInt(1, objet.getId());
		int nbLignes = requete.executeUpdate();
		
		return nbLignes==1;
	}

	@Override
	public ArrayList<Clients> findAll() throws SQLException 
	{
		ArrayList<Clients> donnees = new ArrayList<Clients>();
		Connection laConnexion = Connexion.getInstance().getMaConnexion();
        Statement requete = laConnexion.createStatement();
        ResultSet res = requete.executeQuery("select * from Client");
        while (res.next()) 
        {   
            Clients txt = new Clients(res.getInt("id_client"), res.getString("nom"), res.getString("prenom"), res.getString("identifiant"), res.getString("mot_de_passe"),
            		res.getInt("adr_numero"), res.getString("adr_voie"), res.getInt("adr_code_postal"), res.getString("adr_ville"), res.getString("adr_pays"));  
            donnees.add(txt);
        }

		return donnees;
	}
}