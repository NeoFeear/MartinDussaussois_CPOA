package Vue;

import java.util.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import DAO.DAOFactory.DAOFactory;
import DAO.DAOFactory.DAOFactory.Persistance;
import MYSQLDAO.*;
import Modele.Metier.Commandes;

public class VueCommande 
{
	private static Persistance PERSISTANCE;

	private static final LocalDate LocalDate = null;
	java.util.Date date = new Date();
	
	public static void main(String[] args, Persistance p) throws InterruptedException, SQLException 
	{
		PERSISTANCE = p;
		System.out.println("\n---- COMMANDES ----");
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
				System.out.println(DAOFactory.getDAOFactory(PERSISTANCE).getCommandesDAO().findAll());

				System.out.println("\n--> Redirection dans 5 secondes ");
				TimeUnit.SECONDS.sleep(5);
				main(args,PERSISTANCE);
				break;
				
			case 2 : 
				Scanner add_int = new Scanner(System.in);
				MySQLCommandesDAO.getInstance();
				
				System.out.println("\nL'identifiant : ");
					int id_client = add_int.nextInt();


			    if (DAOFactory.getDAOFactory(PERSISTANCE).getCommandesDAO().create(new Commandes(1, LocalDate, id_client)) == true){
			        System.out.println("Commande ajoutee avec succes");
			    }
			    else{
			    	System.out.println("Impossible d'ajouter cette commande");
			    }

	            	System.out.println("\nAjout effectué !");   
	            	System.out.println("\n--> Redirection dans 2 secondes ");
	            	TimeUnit.SECONDS.sleep(2);
	            	Main.main(args);
	            	break;
			
			case 3 :
				Scanner modify_int = new Scanner(System.in);
				
				System.out.println(DAOFactory.getDAOFactory(PERSISTANCE).getCommandesDAO().findAll());

				
				System.out.println("\nL'ID de la commande à modifier : ");
					int id_com_mdf = modify_int.nextInt();
				System.out.println("\nPour le client : ");
					int id_client__mdf = modify_int.nextInt();
					
				Commandes objet = new Commandes(id_com_mdf, LocalDate, id_client__mdf);
				DAOFactory.getDAOFactory(PERSISTANCE).getCommandesDAO().update(objet);

	           	System.out.println("\nModification effectuée !");  
	           	
	           	System.out.println("\n--> Redirection dans 2 secondes ");
	           	TimeUnit.SECONDS.sleep(2);
	           	Main.main(args);
	           	break;
				
			case 4 :
				Scanner delete = new Scanner(System.in);
				
				System.out.println(DAOFactory.getDAOFactory(PERSISTANCE).getCommandesDAO().findAll());

				
				System.out.println("\nSaisissez la ligne que vous voulez supprimé avec l'ID correspondant : ");
				int id_com = delete.nextInt();
				
				Commandes objet2 = new Commandes(id_com);
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
}