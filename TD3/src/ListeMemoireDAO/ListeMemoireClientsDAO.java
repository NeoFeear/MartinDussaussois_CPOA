package ListeMemoireDAO;

import java.util.ArrayList;
import java.util.List;

import DAO.ClientsDAO;
import Modele.Metier.Clients;

public class ListeMemoireClientsDAO implements ClientsDAO {

	private static ListeMemoireClientsDAO instance;

	private List<Clients> donnees;

	public static ListeMemoireClientsDAO getInstance() {

		if (instance == null) 
		{
			instance = new ListeMemoireClientsDAO();
		}

		return instance;
	}

	private ListeMemoireClientsDAO() {

		this.donnees = new ArrayList<Clients>();

		this.donnees.add(new Clients(1, "LAROCHE", "Pierre", "pl@ul.fr", "toto", 12, "rue des �tudiants", 57990, "Metz", "France"));
		this.donnees.add(new Clients(2, "DUSSAUSSOIS", "Tom", "td@ul.fr", "azer", 15, "rue des champs", 57070, "Metz", "France"));
	}


	@Override
	public boolean create(Clients objet) {

		objet.setId(3);
		// Ne fonctionne que si l'objet métier est bien fait...
		while (this.donnees.contains(objet)) {

			objet.setId(objet.getId() + 1);
		}
		boolean ok = this.donnees.add(objet);
		
		return ok;
	}

	@Override
	public boolean update(Clients objet) {
		
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'une categorie inexistante");
		} else {
			
			this.donnees.get(idx).setNom(objet.getNom());
			this.donnees.get(idx).setPrenom(objet.getPrenom());
			this.donnees.get(idx).setIdentifiant(objet.getIdentifiant());
			this.donnees.get(idx).setMdp(objet.getMdp());
			this.donnees.get(idx).setNum_adresse(objet.getNum_adresse());
			this.donnees.get(idx).setVoie_adresse(objet.getVoie_adresse());
			this.donnees.get(idx).setCp(objet.getCp());
			this.donnees.get(idx).setVille(objet.getVille());
			this.donnees.get(idx).setPays(objet.getPays());
		}
		
		return true;
	}

	@Override
	public boolean delete(Clients objet) {

		Clients supprime;
		
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'une categorie inexistante");
		} else {
			supprime = this.donnees.remove(idx);
		}
		
		return objet.equals(supprime);
	}

	@Override
	public Clients getById(int id) {
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(new Clients(id, "nom", "prenom", "ex@ul.fr", "toto", 20, "adresse", 57000, "Metz", "France"));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucune categorie ne poss�de cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}

	@Override
	public ArrayList<Clients> findAll() {
		return (ArrayList<Clients>) this.donnees;
	}
}
