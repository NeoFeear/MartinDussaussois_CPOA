package listememoiredao;

import java.util.ArrayList;
import java.util.List;

import dao.ProduitDAO;
import modele.metier.Produit;

public class ListeMemoireProduitDAO implements ProduitDAO 
{

	private static ListeMemoireProduitDAO instance;
	private List<Produit> donnees;

	public static ListeMemoireProduitDAO getInstance() {

		if (instance == null) 
		{
			instance = new ListeMemoireProduitDAO();
		}

		return instance;
	}

	private ListeMemoireProduitDAO() {

		this.donnees = new ArrayList<Produit>();

		this.donnees.add(new Produit(1, "Sonic", "Trop cool Sonic !", 41.5, "Sonic.png", ListeMemoireCategorieDAO.getInstance().getById(1)));
		this.donnees.add(new Produit(2, "Mario", "Trop cool Mario !", 22.1, "Mario.png", ListeMemoireCategorieDAO.getInstance().getById(2)));
	}


	@Override
	public boolean create(Produit objet) {

		objet.setId(1);
		// Ne fonctionne que si l'objet métier est bien fait...
		while (this.donnees.contains(objet)) {

			objet.setId(objet.getId() + 1);
		}
		boolean ok = this.donnees.add(objet);
		
		return ok;
	}

	@Override
	public boolean update(Produit objet) {
		
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'un produit inexistant");
		} 
		else 
		{
			this.donnees.get(idx).setNom(objet.getNom());
			this.donnees.get(idx).setDescription(objet.getDescription());
			this.donnees.get(idx).setTarif(objet.getTarif());
			this.donnees.get(idx).setVisuel(objet.getVisuel());
			this.donnees.get(idx).setCategorie(objet.getCategorie());
		}
		
		return true;
	}

	@Override
	public boolean delete(Produit objet) {

		Produit supprime;
		
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un produit inexistant");
		} 
		else 
		{
			supprime = this.donnees.remove(idx);
		}
		
		return objet.equals(supprime);
	}

	@Override
	public Produit getById(int id) {
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(new Produit(1, "Sonic", "Trop cool Sonic !", 41.5, "Sonic.png", ListeMemoireCategorieDAO.getInstance().getById(1)));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucun produit ne possede cet identifiant");
		} 
		else 
		{
			return this.donnees.get(idx);
		}
	}

	@Override
	public ArrayList<Produit> findAll() {
		return (ArrayList<Produit>) this.donnees;
	}

}
