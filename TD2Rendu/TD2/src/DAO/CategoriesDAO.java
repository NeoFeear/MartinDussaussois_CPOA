package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import Modele.Metier.Categories;

public interface CategoriesDAO extends DAO<Categories>
{
	public Categories getById(int id);
	public boolean create(Categories objet) throws SQLException;
	public boolean update(Categories objet) throws SQLException;
	public boolean delete(Categories objet) throws SQLException;
	ArrayList<Categories> findAll() throws SQLException;
}
