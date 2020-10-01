package ListeMemoireDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Modele.Metier.Commandes;
import DAO.*;

public class ListeMemoireCommandesDAO implements CommandesDAO
{

	private static ListeMemoireCommandesDAO instance;

	private List<Commandes> donnees;


	public static ListeMemoireCommandesDAO getInstance() {

		if (instance == null) 
		{
			instance = new ListeMemoireCommandesDAO();
		}

		return instance;
	}

	private ListeMemoireCommandesDAO() {

		this.donnees = new ArrayList<Commandes>();

		this.donnees.add(new Commandes(1, 2020-5-1, 1));
	}


	@Override
	public boolean create(Commandes objet) {

		objet.setId_commande(3);
		// Ne fonctionne que si l'objet métier est bien fait...
		while (this.donnees.contains(objet)) {

			objet.setId_commande(objet.getId_commande() + 1);
		}
		boolean ok = this.donnees.add(objet);
		
		return ok;
	}

	@Override
	public boolean update(Commandes objet) {
		
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'un produit inexistant");
		} else {
			
			this.donnees.set(idx, objet);
		}
		
		return true;
	}

	@Override
	public boolean delete(Commandes objet) {

		Commandes supprime;
		
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'une commande inexistante");
		} else {
			supprime = this.donnees.remove(idx);
		}
		
		return objet.equals(supprime);
	}

	@Override
	public Commandes getById(int id) {
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(new Commandes(1, 2020-5-1, 1));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucune commande ne poss�de cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}

	@Override
	public ArrayList<Commandes> findAll() {
		return (ArrayList<Commandes>) this.donnees;
	}
}
