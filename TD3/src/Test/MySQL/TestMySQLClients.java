package Test.MySQL;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import MYSQLDAO.MySQLClientsDAO;
import Modele.Metier.Clients;

class TestMySQLClients {
	
	@Test
	public void TestGetByIdSQLOk() throws SQLException {
		Clients objet = MySQLClientsDAO.getInstance().getById(0);
		assertNotNull(objet);
	}
	
	@Test
	public void TestGetByIdSQLNotOk() throws SQLException {
		Clients objet = MySQLClientsDAO.getInstance().getById(-2);
		assertNull(objet);
	}
	
	@Test
	public void TestCreateSQLOk() throws SQLException {
		Clients objet = new Clients(0, "Create", "Duss", "td@ul.fr", "mdp", 5, "rue des etudiants", 5, "Metz", "France");
		MySQLClientsDAO.getInstance().create(objet);
		
		int id = objet.getId();
		
		Clients timide = MySQLClientsDAO.getInstance().getById(id);
		assertNotNull(timide);
	}
	
	/*@Test
	public void TestCreateSQLNotOk() throws SQLException {
		Clients objet = new Clients(1, "Tom", "Duss", "td@ul.fr", "mdp", 5, "rue des etudiants", 5, "Metz", "France");
		MySQLClientsDAO.getInstance().create(objet);
		
		int id = objet.getId();
		
		Clients timide = MySQLClientsDAO.getInstance().getById(id);
		assertNotNull(timide);
	}*/
	
	@Test
	public void TestUpdateSQLOk()  throws SQLException{
		Clients objet;
        objet = new Clients(1, "Update", "Duss", "td@ul.fr", "mdp", 5, "rue des etudiants", 5, "Metz", "France");
        int id = objet.getId();
        
        Clients oui = MySQLClientsDAO.getInstance().getById(id);
        assertEquals(oui, objet);
	}
	
	@Test
	public void TestUpdateSQLNotOk()  throws SQLException{
		Clients objet;
        objet = new Clients(-1, "Tom", "Duss", "td@ul.fr", "mdp", 5, "rue des etudiants", 5, "Metz", "France");
        int id = objet.getId();
        
        MySQLClientsDAO.getInstance().update(objet);
        
        Clients oui = MySQLClientsDAO.getInstance().getById(id);
        assertEquals(oui, objet);
	}
	
	@Test
	public void TestDeleteSQLOk() throws SQLException {
		int id = MySQLClientsDAO.getInstance().findAll().size();
		Clients oui = new Clients(3, "DUSSAUSSOIS", "Tom", "tom@ul.fr", "tomtom", 16, "rue des etudiants", 57000, "Metz", "France");
		MySQLClientsDAO.getInstance().create(oui);
		MySQLClientsDAO.getInstance().delete(oui);
		assertEquals(id, MySQLClientsDAO.getInstance().findAll().size());
	}
	
	@Test
	public void TestDeleteSQLNotOk() throws SQLException {
		int id = MySQLClientsDAO.getInstance().findAll().size();
		Clients oui = new Clients(-1, "Tom", "Duss", "td@ul.fr", "mdp", 5, "rue des etudiants", 5, "Metz", "France");
		MySQLClientsDAO.getInstance().delete(oui);
		assertEquals(id, MySQLClientsDAO.getInstance().findAll().size());
	}
	
	@Test
	public void TestClientsFindAllSQL()  throws SQLException{
		
		
	}

}
