package Test.MySQL;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import MYSQLDAO.MySQLProduitsDAO;
import Modele.Metier.Produits;

public class TestMySQLProduits {
	
	@Test
	public void TestGetByIdSQLOk() throws SQLException {
		Produits objet = MySQLProduitsDAO.getInstance().getById(2);
		assertNotNull(objet);
	}
	
	@Test
	public void TestGetByIdSQLNotOk() throws SQLException {
		Produits objet = MySQLProduitsDAO.getInstance().getById(-1);
		assertNull(objet);
	}
	
	@Test
	public void TestCreateSQLOk() throws SQLException {
		Produits objet = new Produits(1, "League of legends", "Ce jeu absorde ton ame", 0, "league.png", 5);
		MySQLProduitsDAO.getInstance().create(objet);
		int id = objet.getId();
		
		Produits test = MySQLProduitsDAO.getInstance().getById(id);
		assertNotNull(test);
	}
	
	@Test
	public void TestCreateSQLNotOk() throws SQLException {
		
	}
	
	@Test
	public void TestUpdateSQLOk() throws SQLException {
		Produits objet;
		objet = new Produits(3, "Dussaussois", "Mange des cookies ", 1, "tom.png", 4);
		int id = objet.getId();
		
		MySQLProduitsDAO.getInstance().update(objet);
		
		Produits test = MySQLProduitsDAO.getInstance().getById(id);
		assertEquals(test, objet);
	}
	
	@Test
	public void TestUpdateSQLNotOk() throws SQLException {
		Produits objet;
		objet = new Produits(-1, "Bonjour", "Comment vous allez", 5, "bonjour.png", 8);
		int id = objet.getId();
		
		MySQLProduitsDAO.getInstance().update(objet);
		
		Produits test = MySQLProduitsDAO.getInstance().getById(id);
		assertEquals(test, objet);
	}
	
	@Test
	public void TestDeleteSQLOk() throws SQLException {
		int id = MySQLProduitsDAO.getInstance().findAll().size();
		Produits oui = new Produits(13, "League", "ceci est un jeu", 51.3, "league.png", 3);
		MySQLProduitsDAO.getInstance().create(oui);
		MySQLProduitsDAO.getInstance().delete(oui);
		assertEquals(id, MySQLProduitsDAO.getInstance().findAll().size());
	}
	
	@Test
	public void TestDeleteSQLNotOk() throws SQLException {
		int id = MySQLProduitsDAO.getInstance().findAll().size();
		Produits oui = new Produits(-1, "Sonic", "Sonic le bg", 5, "sonic.png", 5);;
		MySQLProduitsDAO.getInstance().delete(oui);
		assertEquals(id, MySQLProduitsDAO.getInstance().findAll().size());
	}
	
	@Test
	public void TestCategoriesFindAllSQL() throws SQLException {
		
		
	}
}
