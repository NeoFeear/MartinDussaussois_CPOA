package Vue;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import DAO.DAOFactory.DAOFactory;
import DAO.DAOFactory.DAOFactory.Persistance;
import MYSQLDAO.*;
import Modele.Metier.Clients;
import Modele.Metier.Commandes;

public class VueCommande 
{
private static Persistance PERSISTANCE;
	
	public static void main(String[] args, Persistance p) throws InterruptedException, SQLException 
	{
		PERSISTANCE = p;
		System.out.println("\n---- Commande ----");
		System.out.println("\nSélectionner une option :");
		System.out.println("1 - Voir");
		System.out.println("2 - Ajouter");
		System.out.println("3 - Modifier");
		System.out.println("4 - Supprimer");
		System.out.println("5 - Retour au menu\n");
		
		try (Scanner number = new Scanner(System.in)) 
		{
			System.out.println("Entrez le chiffre correspondant à votre demande :");
			int choix = number.nextInt();
			switch(choix)
			{
			case 1 :
				ArrayList<Commandes> donnees = DAOFactory.getDAOFactory(PERSISTANCE).getCommandesDAO().findAll();
                for (int i = 0; i < donnees.size(); i++) 
                {
                    System.out.println(donnees.get(i).getId_commande() + donnees.get(i).getId_client());
                }
				System.out.println("\n--> Redirection dans 5 secondes ");
				TimeUnit.SECONDS.sleep(5);
				main(args,PERSISTANCE);
				break;
				
			case 2 : 
				Scanner add = new Scanner(System.in);
				Scanner add_int = new Scanner(System.in);
				MySQLCommandesDAO.getInstance();
				
				System.out.println("\nSaisissez le nom : ");
					String nom = add.nextLine();
				System.out.println("\nLe prénom : ");
					String prenom = add.nextLine();
				System.out.println("\nL'identifiant : ");
					String email = add.nextLine();

				if(Doublons(id_commande) == true)
	    		{
	    			System.out.println("\nErreur de saisie, cette commande existe déjà\n");
	    			System.out.println("\n--> Redirection dans 2 secondes ");
	    	       	TimeUnit.SECONDS.sleep(2);
					main(args,PERSISTANCE);
	    		}
	    		else
	    		{
	    			try{
			            if (DAOFactory.getDAOFactory(PERSISTANCE).getCommandesDAO().create(new Commandes(1, new Date(2020-5-1), 1)) == true){
			                System.out.println("Produit ajoute avec succes");
			            }
			            else{
			                System.out.println("Impossible d'ajouter ce produit");
			            }
			        } catch (SQLException sqle) {
			            System.out.println("Message d'erreur SQL:\n"+sqle.getMessage());
			        }
	    		}
				
	            	System.out.println("\nAjout effectué !");   
	            	System.out.println("\n--> Redirection dans 2 secondes ");
	            	TimeUnit.SECONDS.sleep(2);
	            	Main.main(args);
	            	break;
			
			case 3 :
				Scanner modify = new Scanner(System.in);
				Scanner modify_int = new Scanner(System.in);
				
				ArrayList<Commandes> donnees2 = DAOFactory.getDAOFactory(PERSISTANCE).getCommandesDAO().findAll();
                for (int i = 0; i < donnees2.size(); i++) 
                {
                	System.out.println(donnees2.get(i).getId_commande() + donnees2.get(i).getId_client());
                }
				
				System.out.println("\nL'ID du client à modifier : ");
					int id_mdf = modify_int.nextInt();
				System.out.println("\nLe nouveau nom voulu : ");
					String nom_mdf = modify.nextLine();
				System.out.println("\nLe nouveau prénom voulu : ");
					String prenom_mdf = modify.nextLine();
				System.out.println("\nLa nouvelle adresse e-mail voulue : ");
					String email_mdf = modify.nextLine();
					
					Commandes objet = new Commandes(new Date(2020-5-1), 1);
				DAOFactory.getDAOFactory(PERSISTANCE).getCommandesDAO().update(objet);

	           	System.out.println("\nModification effectuée !");   
	           	System.out.println("\n--> Redirection dans 2 secondes ");
	           	TimeUnit.SECONDS.sleep(2);
	           	Main.main(args);
	           	break;
				
			case 4 :
				Scanner delete = new Scanner(System.in);
				
				ArrayList<Commandes> donnees3 = DAOFactory.getDAOFactory(PERSISTANCE).getCommandesDAO().findAll();
                for (int i = 0; i < donnees3.size(); i++) 
                {
                    System.out.println(donnees3.get(i).getId_commande() + donnees3.get(i).getId_client());
                }
				
				System.out.println("\nSaisissez la ligne que vous voulez supprimé avec l'ID correspondant : ");
				int id_client = delete.nextInt();
				
				Commandes objet2 = new Commandes(id_client);
				DAOFactory.getDAOFactory(PERSISTANCE).getCommandesDAO().delete(objet2);
				
				System.out.println("\nSuppression effectuée !");
				
				System.out.println("\n--> Redirection dans 2 secondes \n");
				TimeUnit.SECONDS.sleep(2);
				Main.main(args);
				break;
				
			case 5 :
				Main.main(args);
				break;
			}
		}
	}
	
	public static boolean Doublons(int id_commande)  throws SQLException
	{
		boolean verifier = false;
		Connection laConnexion = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("SELECT id_commande FROM Commande");
		ResultSet res = requete.executeQuery();
		while(res.next()) 
		{
			String nomS = res.getString("id_commande");
			if (nomS.equals(id_commande))
			{
				verifier = true;
			}		
		}
		
		if (requete != null)
       		requete.close();
   		if (laConnexion != null)
       		laConnexion.close();

		return verifier;
	}
}
