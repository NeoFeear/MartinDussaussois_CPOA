package Test.MySQL;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import MYSQLDAO.MySQLCategoriesDAO;
import Modele.Metier.Categories;

public class TestMySQLCategories {
	
	@Test
	public void TestGetByIdSQLOk() throws SQLException {
		Categories objet = MySQLCategoriesDAO.getInstance().getById(2);
		assertNotNull(objet);
	}
	
	@Test
	public void TestGetByIdSQLNotOk() throws SQLException {
		Categories aller = MySQLCategoriesDAO.getInstance().getById(-1);
		assertNull(aller);
	}
	
	@Test
	public void TestCreateSQLOk() throws SQLException {
		Categories categorie = new Categories(1, "createok", "createok.png");
		MySQLCategoriesDAO.getInstance().create(categorie);
		int id = categorie.getId();
		
		Categories timide = MySQLCategoriesDAO.getInstance().getById(id);
		assertNotNull(timide);
	}
	
	/*@Test
	public void TestCreateSQLNotOk() throws SQLException {
		Categories categorie = new Categories(2, "createpasok", "createpasok.png");
		MySQLCategoriesDAO.getInstance().create(categorie);
		int id = categorie.getId();
		
		Categories trestimide = MySQLCategoriesDAO.getInstance().getById(id);
		assertNull(trestimide);
	}*/
	
	@Test
	public void TestUpdateSQLOk() throws SQLException {
		Categories objet;
        objet = new Categories(1, "test", "test.png");
        MySQLCategoriesDAO.getInstance().create(objet);       
        int id = objet.getId();
        
        objet.setTitre("updateok");
        objet.setVisuel("updateok.png");
        
        MySQLCategoriesDAO.getInstance().update(objet);
      
        assertEquals(objet.getTitre(), MySQLCategoriesDAO.getInstance().getById(id).getTitre());
        
        assertEquals(objet.getTitre(), "updateok");
        assertEquals(objet.getVisuel(), "updateok.png");
	}
	
	@Test
	public void TestUpdateSQLNotOk() throws SQLException {
		Categories objet;
        objet = new Categories(-1, "non", "non.png");
        int id = objet.getId();
        
        MySQLCategoriesDAO.getInstance().update(objet);
        
        Categories oui =  MySQLCategoriesDAO.getInstance().getById(id);
        assertNotEquals(oui, objet);

	}
	
	@Test
	public void TestDeleteSQLOk() throws SQLException {
		int id = MySQLCategoriesDAO.getInstance().findAll().size();
		Categories oui = new Categories(1, "deleteok", "deleteok.png");
		MySQLCategoriesDAO.getInstance().create(oui);
		MySQLCategoriesDAO.getInstance().delete(oui);
		assertEquals(id, MySQLCategoriesDAO.getInstance().findAll().size());
	}
	
	@Test
	public void TestDeleteSQLNotOk() throws SQLException {
		int id = MySQLCategoriesDAO.getInstance().findAll().size();
		Categories oui = new Categories(-1, "deletepasok", "deletepasok.png");
		MySQLCategoriesDAO.getInstance().delete(oui);
		assertEquals(id, MySQLCategoriesDAO.getInstance().findAll().size());
	}
	
	@Test
	public void TestCategoriesFindAllSQL() throws SQLException {
		
		
	}
}

