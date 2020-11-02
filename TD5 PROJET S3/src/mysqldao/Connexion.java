package mysqldao;

import java.sql.*;

public class Connexion {
public static Connexion instance;
	
	private Connection maConnexion;
	
	private void creeConnexion() {
		String url = "jdbc:mysql://localhost:3306/martin776u_cpoa";
		String login = "root";
		String mdp = "";	
		/*String url = "jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/dussauss4u_cpoa";
		String login = "dussauss4u_appli";
		String mdp = "Tomtom57";*/
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
}