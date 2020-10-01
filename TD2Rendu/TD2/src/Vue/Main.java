package Vue;

import java.sql.SQLException;
import java.util.Scanner;

import DAO.DAOFactory.DAOFactory;
import DAO.DAOFactory.DAOFactory.Persistance;

public class Main 
{

	static DAOFactory daos;
	private static Scanner number2;
	private static Persistance PERSISTANCE;
	
	public static void main(String[] args) throws InterruptedException, SQLException 
	{
		System.out.println("\n---- PERSISTANCE ----");
		System.out.println("\nSelectionnez une persistance :");
        System.out.println("1 - MYSQL");
        System.out.println("2 - LISTE_MEMOIRE");
        
        try (Scanner number = new Scanner(System.in)) {
			number2 = new Scanner(System.in);
			
			System.out.println("Entrez le chiffre correspondant à votre demande :");
			int choix = number.nextInt();
			switch(choix)
			{
				case 1 :
					PERSISTANCE = Persistance.MYSQL;
					break;
				case 2 : 
					PERSISTANCE = Persistance.LISTE_MEMOIRE;
					break;
			}
			
			System.out.println("\n---- ACCUEIL ----");
			System.out.println("\nSélectionner une option :");
			System.out.println("1 - Catégories");
			System.out.println("2 - Produits");
			System.out.println("3 - Clients");
			System.out.println("4 - Quitter la console\n");
			
			System.out.println("Entrez le chiffre correspondant à votre demande :");
			int choix2 = number2.nextInt();
			switch(choix2)
			{
				case 1 :
					VueCategories.main(args,PERSISTANCE);
					break;
				case 2 : 
					VueProduits.main(args,PERSISTANCE);
					break;
				case 3 :
					VueClients.main(args,PERSISTANCE);
					break;
				case 4 :
					break;
			}
		}
	}
}