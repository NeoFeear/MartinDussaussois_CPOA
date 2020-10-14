package dao.daofactory;

import dao.*;
import mysqldao.*;

public class MySQLDAOFactory extends DAOFactory
{
	@Override
	public CategorieDAO getCategorieDAO() 
	{
		return MySQLCategorieDAO.getInstance();
	}

	@Override
	public ProduitDAO getProduitDAO() 
	{
		return MySQLProduitDAO.getInstance();
	}
}