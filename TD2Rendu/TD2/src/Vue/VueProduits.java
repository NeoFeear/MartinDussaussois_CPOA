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
import Modele.Metier.Produits;

public class VueProduits 
{
	private static Persistance PERSISTANCE;
	
	public static void main(String[] args, Persistance p) throws InterruptedException, SQLException 
	{
		PERSISTANCE = p;
		System.out.println("\n---- PRODUITS ----");
		System.out.println("\nSélectionner une option :");
		System.out.println("1 - Voir");
		System.out.println("2 - Ajouter");
		System.out.println("3 - Modifier");
		System.out.println("4 - Supprimer");
		System.out.println("5 - Retour au menu\n");
		
		try (Scanner number = new Scanner(System.in)) 
		{
			System.out.println("Entrez le chiffre correspondant à  votre demande :");
			int choix = number.nextInt();
			switch(choix)
			{
			case 1 :
				ArrayList<Produits> donnees = DAOFactory.getDAOFactory(PERSISTANCE).getProduitsDAO().findAll();
				for (int i = 0; i < donnees.size(); i++) 
		        {
					System.out.println(donnees.get(i).getId() + donnees.get(i).getNom() + donnees.get(i).getDescription() + donnees.get(i).getTarif() + donnees.get(i).getVisuel() + + donnees.get(i).getId_categorie());
				}
				System.out.println("\n--> Redirection dans 5 secondes ");
				TimeUnit.SECONDS.sleep(5);
				main(args,PERSISTANCE);
				break;
				
			case 2 :
				Scanner add = new Scanner(System.in);
				Scanner add_int = new Scanner(System.in); //Pourquoi ? Changement de type ? -> Tarif
				MySQLProduitsDAO.getInstance();
				
				System.out.println("\nSaisissez le nom voulu : ");
					String nom = add.nextLine();
				System.out.println("\nLa description voulue : ");
					String description = add.nextLine();
				System.out.println("\nLe tarif voulu : ");
					float tarif = add_int.nextFloat();
				System.out.println("\nLe visuel voulu : ");
					String visuel = add.nextLine();
				System.out.println("\nLa catégorie voulue : ");
					int categorie = add_int.nextInt();
				if(Doublons(nom) == true)
	    			{
	    				System.out.println("\nErreur de saisie, ce produit existe déjà ");
	    				System.out.println("\n--> Redirection dans 2 secondes ");
	    	       		TimeUnit.SECONDS.sleep(2);
	    				main(args,PERSISTANCE);
	    			}
	    			else
	    			{
						try{
				            if (DAOFactory.getDAOFactory(PERSISTANCE).getProduitsDAO().create(new Produits(1, nom, description, tarif, visuel, categorie)) == true){
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
				
				ArrayList<Produits> donnees2 = DAOFactory.getDAOFactory(PERSISTANCE).getProduitsDAO().findAll();
				for (int i = 0; i < donnees2.size(); i++) 
		        {
					System.out.println(donnees2.get(i).getId() + donnees2.get(i).getNom() + donnees2.get(i).getDescription() + donnees2.get(i).getTarif() + donnees2.get(i).getVisuel() + + donnees2.get(i).getId_categorie());
				}
				
				System.out.println("\nL'ID du produit à modifier : ");
					int id_mdf = modify_int.nextInt();
				System.out.println("\nLe nouveau nom voulu : ");
					String nom_mdf = modify.nextLine();
				System.out.println("\nLa nouvelle description voulue : ");
					String description_mdf = modify.nextLine();
				System.out.println("\nLe nouveau tarif voulu : ");
					float tarif_mdf = modify_int.nextFloat();
				System.out.println("\nLe nouveau fichier visuel voulu : ");
					String visuel_mdf = modify.nextLine();
				System.out.println("\nL'ID catégorie correspondant : ");
					int idcateg_mdf = modify_int.nextInt();
				

				Produits objet = new Produits(id_mdf, nom_mdf, description_mdf, tarif_mdf, visuel_mdf, idcateg_mdf);
				DAOFactory.getDAOFactory(PERSISTANCE).getProduitsDAO().update(objet);

					
				System.out.println("\nModification effectuée !");
				System.out.println("\n--> Redirection dans 2 secondes ");
				TimeUnit.SECONDS.sleep(2);
				Main.main(args);
				break;
				
			case 4 :
				Scanner delete = new Scanner(System.in);
				
				ArrayList<Produits> donnees3 = DAOFactory.getDAOFactory(PERSISTANCE).getProduitsDAO().findAll();
				for (int i = 0; i < donnees3.size(); i++) 
		        {
					System.out.println(donnees3.get(i).getId() + donnees3.get(i).getNom() + donnees3.get(i).getDescription() + donnees3.get(i).getTarif() + donnees3.get(i).getVisuel() + + donnees3.get(i).getId_categorie());
				}
				
				System.out.println("\nSaisissez la ligne que vous voulez supprimer avec l'ID correspondant : ");
				int id_produit = delete.nextInt();
				
				Produits objet2 = new Produits(id_produit);
				DAOFactory.getDAOFactory(PERSISTANCE).getProduitsDAO().delete(objet2);
				
				System.out.println("\nSuppression effectuée !");
				
				System.out.println("\n--> Redirection dans 2 secondes ");
				TimeUnit.SECONDS.sleep(2);
				Main.main(args);
				break;
				
			case 5 :
				Main.main(args);
				break;
			}
		}
	}
	
	public static boolean Doublons(String nom) throws SQLException
	{
		boolean verifier = false;
		Connection laConnexion = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("SELECT nom FROM Produit");
		ResultSet res = requete.executeQuery();
		while(res.next()) 
		{
			String nomS = res.getString("nom");
			if (nomS.equals(nom))
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
