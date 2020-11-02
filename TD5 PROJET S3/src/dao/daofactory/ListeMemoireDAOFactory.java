package dao.daofactory;

import dao.*;
import listememoiredao.*;

public class ListeMemoireDAOFactory extends DAOFactory 
{
	@Override
	public CategorieDAO getCategorieDAO() 
	{
		return ListeMemoireCategorieDAO.getInstance();
	}

	@Override
	public ProduitDAO getProduitDAO() 
	{
		return ListeMemoireProduitDAO.getInstance();
	}

	@Override
	public ClientDAO getClientDAO() 
	{
		return ListeMemoireClientDAO.getInstance();
	}
}