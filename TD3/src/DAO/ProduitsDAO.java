package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import Modele.Metier.Produits;

public interface ProduitsDAO extends DAO<Produits> 
{
	public Produits getById(int id);
	public boolean create(Produits objet) throws SQLException;
	public boolean update(Produits objet) throws SQLException;
	public boolean delete(Produits objet) throws SQLException;
	ArrayList<Produits> findAll() throws SQLException;
}
