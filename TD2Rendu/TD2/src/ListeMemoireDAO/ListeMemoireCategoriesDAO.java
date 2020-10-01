package ListeMemoireDAO;

import java.util.ArrayList;
import java.util.List;

import DAO.CategoriesDAO;
import Modele.Metier.Categories;

public class ListeMemoireCategoriesDAO implements CategoriesDAO 
{

	private static ListeMemoireCategoriesDAO instance;

	private List<Categories> donnees;

	public static ListeMemoireCategoriesDAO getInstance() {

		if (instance == null) 
		{
			instance = new ListeMemoireCategoriesDAO();
		}

		return instance;
	}

	private ListeMemoireCategoriesDAO() {

		this.donnees = new ArrayList<Categories>();

		this.donnees.add(new Categories(1, "Pulls", "pulls.png"));
		this.donnees.add(new Categories(2, "Bonnets", "bonnets.png"));
	}


	@Override
	public boolean create(Categories objet) {

		objet.setId(3);
		// Ne fonctionne que si l'objet métier est bien fait...
		while (this.donnees.contains(objet)) {

			objet.setId(objet.getId() + 1);
		}
		boolean ok = this.donnees.add(objet);
		
		return ok;
	}

	@Override
	public boolean update(Categories objet) {
		
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'une categorie inexistante");
		} else {
			
			this.donnees.get(idx).setTitre(objet.getTitre());
			this.donnees.get(idx).setVisuel(objet.getVisuel());
		}
		
		return true;
	}

	@Override
	public boolean delete(Categories objet) {

		Categories supprime;
		
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
	public Categories getById(int id) {
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(new Categories(id, "test", "test.png"));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucune categorie ne poss�de cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}

	@Override
	public ArrayList<Categories> findAll() {
		return (ArrayList<Categories>) this.donnees;
	}
}
