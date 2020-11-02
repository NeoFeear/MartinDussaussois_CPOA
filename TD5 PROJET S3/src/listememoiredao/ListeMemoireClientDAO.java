package listememoiredao;

import java.util.ArrayList;
import java.util.List;

import modele.metier.Client;
import dao.ClientDAO;

public class ListeMemoireClientDAO implements ClientDAO 
{
	private static ListeMemoireClientDAO instance;
	private List<Client> donnees;

	public static ListeMemoireClientDAO getInstance() {

		if (instance == null) 
		{
			instance = new ListeMemoireClientDAO();
		}

		return instance;
	}

	private ListeMemoireClientDAO() {

		this.donnees = new ArrayList<Client>();

		this.donnees.add(new Client(1, "LAROCHE", "Pierre", "pl@ul.fr", "toto", "12", "rue des �tudiants", "57990", "Metz", "France"));
		this.donnees.add(new Client(2, "MARTIN", "Florian", "fm@ul.fr", "yoyo", "20", "domaine de largantier", "57155", "Marly", "France"));
		this.donnees.add(new Client(3, "DUSSAUSSOIS", "Tom", "td@ul.fr", "azer", "15", "rue des champs", "57070", "Metz", "France"));
	
	}

	@Override
	public boolean create(Client objet) {

		objet.setId(1);
		// Ne fonctionne que si l'objet métier est bien fait...
		while (this.donnees.contains(objet)) {

			objet.setId(objet.getId() + 1);
		}
		boolean ok = this.donnees.add(objet);
		
		return ok;
	}

	@Override
	public boolean update(Client objet) {
		
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'un client inexistant");
		} 
		else 
		{
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
	public boolean delete(Client objet) {

		Client supprime;
		
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) 
		{
			throw new IllegalArgumentException("Tentative de suppression d'une categorie inexistante");
		} 
		else 
		{
			supprime = this.donnees.remove(idx);
		}
		
		return objet.equals(supprime);
	}

	@Override
	public Client getById(int id) {
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(new Client(id, "nom", "prenom", "ex@ul.fr", "toto", "20", "adresse", "57000", "Metz", "France"));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucun client ne poss�de cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}

	@Override
	public ArrayList<Client> findAll() {
		return (ArrayList<Client>) this.donnees;
	}
}

