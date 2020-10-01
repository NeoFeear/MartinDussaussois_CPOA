package DAO.DAOFactory;

import DAO.*;
import MYSQLDAO.*;

public class MySQLDAOFactory extends DAOFactory
{
	@Override
	public CategoriesDAO getCategoriesDAO() 
	{
		return MySQLCategoriesDAO.getInstance();
	}

	@Override
	public ProduitsDAO getProduitsDAO() 
	{
		return MySQLProduitsDAO.getInstance();
	}

	@Override
	public ClientsDAO getClientsDAO() 
	{
		return MySQLClientsDAO.getInstance();
	}
	
	public CommandesDAO getCommandesDAO()
	{
		return MySQLCommandesDAO.getInstance();
	}
}