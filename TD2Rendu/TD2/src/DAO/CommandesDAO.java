package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import Modele.Metier.Commandes;

public interface CommandesDAO extends DAO<Commandes>
{
	public Commandes getById(int id);
	public boolean create(Commandes objet) throws SQLException;
	public boolean update(Commandes objet) throws SQLException;
	public boolean delete(Commandes objet) throws SQLException;
	ArrayList<Commandes> findAll() throws SQLException;
}
