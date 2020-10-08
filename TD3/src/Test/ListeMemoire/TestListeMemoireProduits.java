package Test.ListeMemoire;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ListeMemoireDAO.ListeMemoireProduitsDAO;
import Modele.Metier.Produits;

class TestListeMemoireProduits {
		
	@Test
	public void TestProduitsGetByIdListeMemoire() {
		
	}
	
	@Test
	public void TestProduitsCreateListeMemoire() {
		Produits objet;
		objet = new Produits(1 ,"", "", 15, "", 5);
		
		assertTrue(ListeMemoireProduitsDAO.getInstance().create(objet));
		
		assertNotEquals(objet.getId(), 1);
	}
	
	@Test
	public void TestProduitsUpdateListeMemoire() {
		Produits objet;
		objet = new Produits(1, "", "", 5, "", 2);
		ListeMemoireProduitsDAO.getInstance().create(objet);
		
		int id = objet.getId();
		objet.setNom("");
		objet.setDescription("");
		objet.setTarif(5);
		objet.setVisuel("");
		objet.setTarif(0);
		
		assertTrue(ListeMemoireProduitsDAO.getInstance().update(objet));
		
		assertEquals(objet.getNom(), ListeMemoireProduitsDAO.getInstance().getById(id).getNom());
		
		assertNotEquals(objet.getNom(), "");
		assertNotEquals(objet.getDescription(), "");
		assertNotEquals(objet.getTarif(), 5);
		assertNotEquals(objet.getVisuel(), "");
		assertNotEquals(objet.getTarif(), 8);
	}
	
	@Test
	public void TestProduitsDeleteListeMemoire() {
		Produits objet;
		objet = new Produits(1, "", "", 5, "", 1);
		
		assertTrue(ListeMemoireProduitsDAO.getInstance().create(objet));
		
		
		
	}
		
		
	@Test
	public void TestProduitsFindAllListeMemoire() {
		
	}
}
