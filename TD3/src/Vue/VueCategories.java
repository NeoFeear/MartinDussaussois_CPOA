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
import Modele.Metier.Categories;

public class VueCategories 
{
	private static Persistance PERSISTANCE;
	
	public static void main(String[] args, Persistance p) throws InterruptedException, SQLException 
	{
		PERSISTANCE = p;
		System.out.println("\n---- CATEGORIES ----");
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
				ArrayList<Categories> donnees = DAOFactory.getDAOFactory(PERSISTANCE).getCategoriesDAO().findAll();
				for (int i = 0; i < donnees.size(); i++) 
		        {
					System.out.println(donnees.get(i).getId() + donnees.get(i).getTitre() + donnees.get(i).getVisuel());
				}
				
				System.out.println("\n--> Redirection dans 5 secondes ");
				TimeUnit.SECONDS.sleep(5);
				main(args,PERSISTANCE);
				break;
				
			case 2 :
				Scanner add = new Scanner(System.in);
				MySQLCategoriesDAO.getInstance();
				
				System.out.println("\nSaisissez le titre voulu : ");
					String titre = add.nextLine();
				System.out.println("\nEt le visuel voulu : ");
					String visuel = add.nextLine();
					
				if(doublonCategories(titre) == true)
				{
    				System.out.println("\nErreur de saisie, cette catégorie existe déjà ");
    				System.out.println("\n--> Redirection dans 2 secondes ");
    	       		TimeUnit.SECONDS.sleep(2);
    				main(args,PERSISTANCE);
				}
				else
				{
					try{
			            if (DAOFactory.getDAOFactory(PERSISTANCE).getCategoriesDAO().create(new Categories(1, titre, visuel)) == true){
			                System.out.println("Produit ajoute avec succes");
			            }
			            else{
			                System.out.println("Impossible d'ajouter cette catégorie");
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
				
				ArrayList<Categories> donnees2 = DAOFactory.getDAOFactory(PERSISTANCE).getCategoriesDAO().findAll();
				for (int i = 0; i < donnees2.size(); i++) 
		        {
					System.out.println(donnees2.get(i).getId() + donnees2.get(i).getTitre() + donnees2.get(i).getVisuel());
				}
				
				System.out.println("\nL'ID de la catégorie à  modifier : ");
					int id_mdf = modify_int.nextInt();
				System.out.println("\nLe nouveau titre voulu : ");
					String titre_mdf = modify.nextLine();
				System.out.println("\nEt le nouveau fichier visuel voulu : ");
					String visuel_mdf = modify.nextLine();


				Categories objet = new Categories(id_mdf, titre_mdf, visuel_mdf);
				DAOFactory.getDAOFactory(PERSISTANCE).getCategoriesDAO().update(objet);
					
				System.out.println("\nModification effectuée !");
				
				System.out.println("\n--> Redirection dans 2 secondes ");
				TimeUnit.SECONDS.sleep(2);
				Main.main(args);
				break;
				
			case 4 :
				Scanner delete = new Scanner(System.in);
				
				ArrayList<Categories> donnees3 = DAOFactory.getDAOFactory(PERSISTANCE).getCategoriesDAO().findAll();
				for (int i = 0; i < donnees3.size(); i++) 
		        {
					System.out.println(donnees3.get(i).getId() + donnees3.get(i).getTitre() + donnees3.get(i).getVisuel());
				}
				
				System.out.println("\nSaisissez la ligne que vous voulez supprimé avec l'ID correspondant : ");
				int id_categorie = delete.nextInt();
				
				Categories objet2 = new Categories(id_categorie);
				DAOFactory.getDAOFactory(PERSISTANCE).getCategoriesDAO().delete(objet2);
				
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
	
	public static boolean doublonCategories(String titre) throws SQLException
	{
		boolean verifier = false;
		Connection laConnexion = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("SELECT titre, visuel FROM Categorie");
		ResultSet res = requete.executeQuery();
		while(res.next()) 
		{
			String title = res.getString("titre");
			String visu = res.getString("visuel");
			if (title.equals(titre) && visu.equals(visu)) {
				verifier = true;
			}
		}
		
		if (res != null)
			res.close();
        if (requete != null)
            requete.close();
        if (laConnexion != null)
            laConnexion.close();

		return verifier;
	}
}