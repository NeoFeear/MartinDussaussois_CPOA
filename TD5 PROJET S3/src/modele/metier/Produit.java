package modele.metier;

public class Produit {
	private int id_produit;
	private String nom;
	private String description;
	private double tarif;
	private String visuel;
	private Categorie categorie;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produit other = (Produit) obj;
		if (id_produit != other.id_produit)
			return false;
		return true;
	}

	public Produit(int id_produit, String nom, String description, double tarif, String visuel, Categorie categorie) {
		super();
		this.setId(id_produit);
		this.setNom(nom);
		this.setDescription(description);
		this.setTarif(tarif);
		this.setVisuel(visuel);
		this.setCategorie(categorie);
	}
	
	public Produit(String nom, String description, double tarif, Categorie categorie) {
		super();
		this.setNom(nom);
		this.setDescription(description);
		this.setTarif(tarif);
		this.setCategorie(categorie);
	}

	public int getId() {
		return this.id_produit;
	}

	public void setId(int id_produit) {
		this.id_produit = id_produit;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getTarif() {
		return this.tarif;
	}

	public void setTarif(double tarif) {
		this.tarif = tarif;
	}

	public String getVisuel() {
		return this.visuel;
	}

	public void setVisuel(String visuel) {
		this.visuel = visuel;
	}

	public Categorie getCategorie() {
		return this.categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	@Override
	public String toString() {
		return nom + " (" + categorie + "), " + tarif + " euros";
	}
}