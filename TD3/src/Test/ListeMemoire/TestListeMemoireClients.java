package Test.ListeMemoire;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ListeMemoireDAO.ListeMemoireClientsDAO;
import Modele.Metier.Clients;

class TestListeMemoireClients {

	@Test
	public void TestClientsGetByIdListeMemoire() {
		
	}
	
	@Test
	public void TestClientsCreateListeMemoire() {
		Clients objet;
		objet = new Clients(-5, "oui", "non", "peut-etre");
		
		assertTrue(ListeMemoireClientsDAO.getInstance().create(objet));
		
		assertNotEquals(objet.getId(), -5);
		
	}
	
	@Test
	public void TestClientsUpdateListeMemoire() {
		Clients objet;
		objet = new Clients(-5, "non", "oui", "en effet");
		
		ListeMemoireClientsDAO.getInstance().create(objet);
		
		int id = objet.getId();
		objet.setNom("");
		objet.setPrenom("");
		objet.setIdentifiant("");
		objet.setMdp("");
		objet.setNum_adresse(5);
		objet.setVoie_adresse("");
		objet.setCp(57070);
		objet.setVille("");
		objet.setPays("");
		
		assertTrue(ListeMemoireClientsDAO.getInstance().update(objet));
		
		assertEquals(objet.getNom(), ListeMemoireClientsDAO.getInstance().getById(id).getNom());
		
		assertNotEquals(objet.getNom(), "");
		assertNotEquals(objet.getPrenom(), "");
		assertNotEquals(objet.getIdentifiant(), "");
	}
	
	@Test
	public void TestClientsDeleteListeMemoire() {
		
	}
	
	
	@Test
	public void TestClientsFindAllListeMemoire() {
		
	}
}
