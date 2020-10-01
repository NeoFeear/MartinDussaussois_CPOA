package Vue;

import java.sql.Connection;
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

public class VueClients 
{
	private static Persistance PERSISTANCE;
	
	public static void main(String[] args, Persistance p) throws InterruptedException, SQLException 
	{
		PERSISTANCE = p;
		System.out.println("\n---- CLIENTS ----");
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
				ArrayList<Clients> donnees = DAOFactory.getDAOFactory(PERSISTANCE).getClientsDAO().findAll();
                for (int i = 0; i < donnees.size(); i++) 
                {
                    System.out.println(donnees.get(i).getId() + donnees.get(i).getNom() + donnees.get(i).getPrenom() + donnees.get(i).getIdentifiant() + donnees.get(i).getMdp() + donnees.get(i).getNum_adresse() + donnees.get(i).getVoie_adresse() + donnees.get(i).getCp() + donnees.get(i).getVille() + donnees.get(i).getPays());
                }
				System.out.println("\n--> Redirection dans 5 secondes ");
				TimeUnit.SECONDS.sleep(5);
				main(args,PERSISTANCE);
				break;
				
			case 2 : 
				Scanner add = new Scanner(System.in);
				Scanner add_int = new Scanner(System.in);
				MySQLClientsDAO.getInstance();
				
				System.out.println("\nSaisissez le nom : ");
					String nom = add.nextLine();
				System.out.println("\nLe prénom : ");
					String prenom = add.nextLine();
				System.out.println("\nL'identifiant : ");
					String email = add.nextLine();
				System.out.println("\nLe mot de passe : ");
					String mdp = add.nextLine();
				System.out.println("\nLe numéro de l'adresse : ");
					int numero = add_int.nextInt();
				System.out.println("\nLa voie de l'adresse : ");
					String voie = add.nextLine();
				System.out.println("\nLe code postal : ");
					int cp = add_int.nextInt();
				System.out.println("\nLa ville : ");
					String ville = add.nextLine();
				System.out.println("\nEt le pays : ");
					String pays = add.nextLine();
				if(Doublons(nom, prenom, email) == true)
	    		{
	    			System.out.println("\nErreur de saisie, ce client existe déjà avec cette adresse e-mail \n");
	    			System.out.println("\n--> Redirection dans 2 secondes ");
	    	       	TimeUnit.SECONDS.sleep(2);
					main(args,PERSISTANCE);
	    		}
	    		else
	    		{
	    			try{
			            if (DAOFactory.getDAOFactory(PERSISTANCE).getClientsDAO().create(new Clients(1, nom, prenom, email, mdp, numero, voie, cp, ville, pays)) == true){
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
				
				ArrayList<Clients> donnees2 = DAOFactory.getDAOFactory(PERSISTANCE).getClientsDAO().findAll();
                for (int i = 0; i < donnees2.size(); i++) 
                {
                    System.out.println(donnees2.get(i).getId() + donnees2.get(i).getNom() + donnees2.get(i).getPrenom() + donnees2.get(i).getIdentifiant());
                }
				
				System.out.println("\nL'ID du client à modifier : ");
					int id_mdf = modify_int.nextInt();
				System.out.println("\nLe nouveau nom voulu : ");
					String nom_mdf = modify.nextLine();
				System.out.println("\nLe nouveau prénom voulu : ");
					String prenom_mdf = modify.nextLine();
				System.out.println("\nLa nouvelle adresse e-mail voulue : ");
					String email_mdf = modify.nextLine();
					
	    		Clients objet = new Clients(id_mdf, nom_mdf, prenom_mdf, email_mdf);
				DAOFactory.getDAOFactory(PERSISTANCE).getClientsDAO().update(objet);

	           	System.out.println("\nModification effectuée !");   
	           	System.out.println("\n--> Redirection dans 2 secondes ");
	           	TimeUnit.SECONDS.sleep(2);
	           	Main.main(args);
	           	break;
				
			case 4 :
				Scanner delete = new Scanner(System.in);
				
				ArrayList<Clients> donnees3 = DAOFactory.getDAOFactory(PERSISTANCE).getClientsDAO().findAll();
                for (int i = 0; i < donnees3.size(); i++) 
                {
                    System.out.println(donnees3.get(i).getId() + donnees3.get(i).getNom() + donnees3.get(i).getPrenom() + donnees3.get(i).getIdentifiant() + donnees3.get(i).getMdp() + donnees3.get(i).getNum_adresse() + donnees3.get(i).getVoie_adresse() + donnees3.get(i).getCp() + donnees3.get(i).getVille() + donnees3.get(i).getPays());
                }
				
				System.out.println("\nSaisissez la ligne que vous voulez supprimé avec l'ID correspondant : ");
				int id_client = delete.nextInt();
				
				Clients objet2 = new Clients(id_client);
				DAOFactory.getDAOFactory(PERSISTANCE).getClientsDAO().delete(objet2);
				
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
	
	public static boolean Doublons(String nom, String prenom, String mail)  throws SQLException
	{
		boolean verifier = false;
		Connection laConnexion = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("SELECT nom, prenom, identifiant FROM Client");
		ResultSet res = requete.executeQuery();
		while(res.next()) 
		{
			String nomS = res.getString("nom");
			String prenomS = res.getString("prenom");
			String identifiant = res.getString("identifiant");
			if (nomS.equals(nom) && prenomS.equals(prenom) && (identifiant.equals(mail)))
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
