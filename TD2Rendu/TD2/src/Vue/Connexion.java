package Vue;

import java.sql.*;

public class Connexion {
	
	public static Connexion instance;
	
	private Connection maConnexion;
	
	private void creeConnexion() {
		String url = "jdbc:mysql://localhost:3306/martin776u_cpoa_td1";
		String login = "root";
		String mdp = "";
		url += "?serverTimezone=Europe/Paris";

		try {
			maConnexion = DriverManager.getConnection(url, login, mdp);
			//System.out.println("Connexion effectuée !");
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
}
