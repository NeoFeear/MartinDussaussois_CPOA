package DAO.DAOFactory;

import DAO.CategoriesDAO;
import DAO.ClientsDAO;
import DAO.CommandesDAO;
import DAO.ProduitsDAO;

public abstract class DAOFactory 
{
	public enum Persistance 
	{
		MYSQL, NO_SQL, LISTE_MEMOIRE, XML;
	}
	
	public static DAOFactory getDAOFactory(Persistance cible) 
	{
		DAOFactory daoF = null;
		
		switch(cible)
		{
			case MYSQL:
				daoF = new MySQLDAOFactory();
				break;
			case LISTE_MEMOIRE:
				daoF = new ListeMemoireDAOFactory();
				break;
			default:
				break;
		}
		
		return daoF;
	}

	public abstract CategoriesDAO getCategoriesDAO();
	public abstract ProduitsDAO getProduitsDAO();
	public abstract ClientsDAO getClientsDAO();
	public abstract CommandesDAO getCommandesDAO();
}