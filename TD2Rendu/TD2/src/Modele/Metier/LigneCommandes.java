package Modele.Metier;

public class LigneCommandes {
	private int id_commande;
	private int id_produit;
	private int quantite;
	private float tarif;
	
	public LigneCommandes(int id_commande, int id_produit, int quantite, float tarif) 
	{
		super();
		this.setId_commande(id_commande);
		this.setId_produit(id_produit);
		this.setQuantite(quantite);
		this.setTarif(tarif);
	}
	
	public int getId_commande() {
		return this.id_commande;
	}

	public void setId_commande(int id_commande) {
		this.id_commande = id_commande;
	}

	public int getId_produit() {
		return this.id_produit;
	}

	public void setId_produit(int id_produit) {
		this.id_produit = id_produit;
	}

	public int getQuantite() {
		return this.quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public float getTarif() {
		return this.tarif;
	}

	public void setTarif(float tarif) {
		this.tarif = tarif;
	}
}
