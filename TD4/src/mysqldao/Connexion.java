package mysqldao;

import java.sql.*;

public class Connexion {
public static Connexion instance;
	
	private Connection maConnexion;
	
	private void creeConnexion() {
		String url = "jdbc:mysql://localhost:3306/martin776u_cpoa";
		String login = "root";
		String mdp = "";	
		url += "?serverTimezone=Europe/Paris";

		try {
			maConnexion = DriverManager.getConnection(url, login, mdp);
			//System.out.println("Connexion effectuee !");
		} 
		catch (SQLException sqle) 
		{
			System.out.println("Erreur connexion" + sqle.getMessage());
		}
	}

	public static Connexion getInstance() {
        if (instance == null) 
        {
            instance = new Connexion();
        }
        return instance;
    }
	
	public Connection getMaConnexion() throws SQLException
	{
		if (this.maConnexion.isClosed()) {
			creeConnexion();
		}
		return this.maConnexion;
	}
	
	private Connexion()
	{
		creeConnexion();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*private static Connexion instance;
	
	private Connection maConnexion;
	
	public static Connection creeConnexion() {
		Properties accesBdd = new Properties();
		File fBdd = new File("config/bdd.properties");
		try {
			FileInputStream source = new FileInputStream(fBdd);
			accesBdd.loadFromXML(source);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		String url = accesBdd.getProperty("url");
		String login = accesBdd.getProperty("login");
		String pwd = accesBdd.getProperty("pass");
		Connection maConnexion = null;
		try {
			maConnexion = DriverManager.getConnection(url, login, pwd);
		} catch (SQLException sqle) {
			System.out.println("Erreur connexion" + sqle.getMessage());
		}
		return maConnexion;
	}
	public static Connexion getInstance() {
        if (instance == null) 
        {
            instance = new Connexion();
        }
        return instance;
    }
	
	public Connection getMaConnexion() throws SQLException, InvalidPropertiesFormatException, IOException
	{
		if (this.maConnexion.isClosed()) {
			creeConnexion();
		}
		return this.maConnexion;
	}*/
}
