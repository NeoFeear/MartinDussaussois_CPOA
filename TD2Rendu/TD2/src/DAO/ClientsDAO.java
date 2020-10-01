package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import Modele.Metier.Clients;

public interface ClientsDAO extends DAO<Clients>
{
	public Clients getById(int id);
	public boolean create(Clients objet) throws SQLException;
	public boolean update(Clients objet) throws SQLException;
	public boolean delete(Clients objet) throws SQLException;
	ArrayList<Clients> findAll() throws SQLException;
}
