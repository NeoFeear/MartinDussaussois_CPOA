package mysqldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.ClientDAO;
import modele.metier.Client;

public class MySQLClientDAO implements ClientDAO
{

	private static MySQLClientDAO Instance;
	
	private MySQLClientDAO() {}
	
	public static MySQLClientDAO getInstance() 
	{
		if (Instance == null) {
			Instance = new MySQLClientDAO();
		}

		return Instance;
	}

	@Override
	public Client getById(int id) 
	{
		Client clients = null;
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
				System.out.println(res.getString("adr_numero"));
				System.out.println(res.getString("adr_voie"));
				System.out.println(res.getString("adr_code_postal"));
				System.out.println(res.getString("adr_ville"));
				System.out.println(res.getString("adr_pays"));
				clients = new Client(id, res.getString("nom"), res.getString("prenom"), res.getString("identifiant"), res.getString("mot_de_passe"), res.getString("adr_numero"), res.getString("adr_voie"), res.getString("adr_code_postal"), res.getString("adr_ville"), res.getString("adr_pays"));
			}
		}
		catch (SQLException sqle) 
        {
            System.out.println("Pb dans select " + sqle.getMessage());
        }
		
		return clients;
	}

	@Override
	public boolean create(Client objet) throws SQLException 
	{
		Connection laConnexion = Connexion.getInstance().getMaConnexion();
    	PreparedStatement requete = laConnexion.prepareStatement("insert into Client (nom,prenom,identifiant,mot_de_passe,adr_numero,adr_voie,adr_code_postal,adr_ville,adr_pays) values (?,?,?,?,?,?,?,?,?)",  Statement.RETURN_GENERATED_KEYS);
    	requete.setString	(1, objet.getNom());
    	requete.setString	(2, objet.getPrenom());
    	requete.setString	(3, objet.getIdentifiant());
    	requete.setString	(4, objet.getMdp());
    	requete.setString	(5, objet.getNum_adresse());
    	requete.setString	(6, objet.getVoie_adresse());
    	requete.setString	(7, objet.getCp());
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
	public boolean update(Client objet) throws SQLException 
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
	public boolean delete(Client objet) throws SQLException 
	{
		Connection laConnexion = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("delete from Client where id_client=?");
		
		requete.setInt(1, objet.getId());
		int nbLignes = requete.executeUpdate();
		
		return nbLignes==1;
	}

	@Override
	public ArrayList<Client> findAll() throws SQLException 
	{
		ArrayList<Client> donnees = new ArrayList<Client>();
		Connection laConnexion = Connexion.getInstance().getMaConnexion();
        Statement requete = laConnexion.createStatement();
        ResultSet res = requete.executeQuery("select * from Client");
        while (res.next()) 
        {   
            Client txt = new Client(res.getInt("id_client"), res.getString("nom"), res.getString("prenom"), res.getString("identifiant"), res.getString("mot_de_passe"),
            		res.getString("adr_numero"), res.getString("adr_voie"), res.getString("adr_code_postal"), res.getString("adr_ville"), res.getString("adr_pays"));  
            donnees.add(txt);
        }

		return donnees;
	}
}