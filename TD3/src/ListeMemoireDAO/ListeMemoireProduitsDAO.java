package ListeMemoireDAO;

import java.util.ArrayList;
import java.util.List;

import DAO.ProduitsDAO;
import Modele.Metier.Produits;

public class ListeMemoireProduitsDAO implements ProduitsDAO 
{

	private static ListeMemoireProduitsDAO instance;

	private List<Produits> donnees;

	public static ListeMemoireProduitsDAO getInstance() {

		if (instance == null) 
		{
			instance = new ListeMemoireProduitsDAO();
		}

		return instance;
	}

	private ListeMemoireProduitsDAO() {

		this.donnees = new ArrayList<Produits>();

		this.donnees.add(new Produits(1, "Sonic", "Trop cool Sonic !", 41.5, "Sonic.png", 1));
	}


	@Override
	public boolean create(Produits objet) {

		objet.setId(3);
		// Ne fonctionne que si l'objet métier est bien fait...
		while (this.donnees.contains(objet)) {

			objet.setId(objet.getId() + 1);
		}
		boolean ok = this.donnees.add(objet);
		
		return ok;
	}

	@Override
	public boolean update(Produits objet) {
		
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'un produit inexistant");
		} else {
			
			this.donnees.get(idx).setNom(objet.getNom());
			this.donnees.get(idx).setDescription(objet.getDescription());
			this.donnees.get(idx).setTarif(objet.getTarif());
			this.donnees.get(idx).setVisuel(objet.getVisuel());
			this.donnees.get(idx).setId_categorie(objet.getId_categorie());
		}
		
		return true;
	}

	@Override
	public boolean delete(Produits objet) {

		Produits supprime;
		
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un produit inexistant");
		} else {
			supprime = this.donnees.remove(idx);
		}
		
		return objet.equals(supprime);
	}

	@Override
	public Produits getById(int id) {
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(new Produits(1, "Sonic", "Trop cool Sonic !", 41.5, "Sonic.png", 1));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucun produit ne poss�de cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}

	@Override
	public ArrayList<Produits> findAll() {
		return (ArrayList<Produits>) this.donnees;
	}
}
