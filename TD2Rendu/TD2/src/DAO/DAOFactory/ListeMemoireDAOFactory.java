package DAO.DAOFactory;

import DAO.*;
import ListeMemoireDAO.*;

public class ListeMemoireDAOFactory extends DAOFactory 
{
	@Override
	public CategoriesDAO getCategoriesDAO() 
	{
		return ListeMemoireCategoriesDAO.getInstance();
	}

	@Override
	public ProduitsDAO getProduitsDAO() 
	{
		return ListeMemoireProduitsDAO.getInstance();
	}
	
	@Override
	public ClientsDAO getClientsDAO() 
	{
		return ListeMemoireClientsDAO.getInstance();
	}
	
	public CommandesDAO getCommandesDAO()
	{
		return ListeMemoireCommandesDAO.getInstance();
	}
}