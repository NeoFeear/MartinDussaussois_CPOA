package Test.ListeMemoire;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ListeMemoireDAO.ListeMemoireCategoriesDAO;
import Modele.Metier.Categories;

class TestListeMemoireCategories {

		@Test
		public void TestGetByIdListeMemoireOk() {
			
		}
		
		@Test
		public void TestGetByIdListeMemoireNotOk() {
			
		}
		
		@Test
		public void TestCreateListeMemoireOk() {
			Categories objet = new Categories(5, "test", "test.png");
			ListeMemoireCategoriesDAO.getInstance().create(objet);
			int id = objet.getId();
			
			Categories test = ListeMemoireCategoriesDAO.getInstance().getById(id);
			assertNotNull(test);
		}
		
		/*@Test
		public void TestCreateListeMemoireNotOk() {
			
			
		}*/
		
		@Test
		public void TestUpdateListeMemoireOk() {
			Categories objet = new Categories(5, "test", "test.png");
			ListeMemoireCategoriesDAO.getInstance().update(objet);
			int id = objet.getId();
			Categories test = ListeMemoireCategoriesDAO.getInstance().getById(id);
			assertNotNull(test);
		}
		
		@Test
		public void TestUpdateListeMemoireNotOk() {
			try {
				Categories objet = new Categories(-1, "test", "test.png");
				ListeMemoireCategoriesDAO.getInstance().update(objet);
				fail("Modification d'une categorie inexistante");
			}catch(IllegalArgumentException e) {
				
			}
		}
		
		@Test
		public void TestDeleteListeMemoireOk() {
			int taille = ListeMemoireCategoriesDAO.getInstance().findAll().size();
			Categories objet = new Categories(1, "test", "test.png");
			ListeMemoireCategoriesDAO.getInstance().delete(objet);
			assertEquals(taille, ListeMemoireCategoriesDAO.getInstance().findAll().size());
		}
		
		@Test
		public void TestDeleteListeMemoireNotOk() {
			try {
				Categories objet = new Categories(-1, "test", "test.png");
				ListeMemoireCategoriesDAO.getInstance().delete(objet);
				fail("suppresion d'une categorie inexistante");
			}catch(IllegalArgumentException e) {
				
			}
		}
		
		@Test
		public void TestCategoriesFindAllListeMemoire() {
			Categories test1 = new Categories(1, "test", "test.png");
			Categories test2 = new Categories(2, "test2", "test2.png");
			assertTrue(ListeMemoireCategoriesDAO.getInstance().create(test1));
			assertTrue(ListeMemoireCategoriesDAO.getInstance().create(test2));
			
			int id1 = test1.getId();
			int id2 = test2.getId();
			
			assertEquals(test1.getId(), ListeMemoireCategoriesDAO.getInstance().getById(id1).getId());
			assertEquals(test2.getId(), ListeMemoireCategoriesDAO.getInstance().getById(id2).getId());
			
			assertNotEquals(test1.getId(), -1);
		}
	}