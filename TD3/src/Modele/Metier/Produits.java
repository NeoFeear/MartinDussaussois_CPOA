package Modele.Metier;

public class Produits {
	private int id_produit;
    private String nom;
    private String description;
    private double tarif;
    private String visuel;
    private int id_categorie;
    
    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produits other = (Produits) obj;
		if (id_produit != other.id_produit)
			return false;
		return true;
	}
    
	public Produits(int id_produit, String nom, String description, double tarif, String visuel, int id_categorie) {
		super();
		this.setId(id_produit);
		this.setNom(nom);
		this.setDescription(description);
		this.setTarif(tarif);
		this.setVisuel(visuel);
		this.setId_categorie(id_categorie);
	}
	
	public Produits(String nom, String description, double tarif, String visuel, int id_categorie) {
		super();
		this.setNom(nom);
		this.setDescription(description);
		this.setTarif(tarif);
		this.setVisuel(visuel);
		this.setId_categorie(id_categorie);
	}
	
	public Produits(int id_produit) {
		super();
		this.setId(id_produit);
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
		if (nom==null || nom.trim().length()==0)
		{
			throw new IllegalArgumentException("Nom vide !");
		}
		this.nom = nom;
	}

	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		if (description==null || description.trim().length()==0)
		{
			throw new IllegalArgumentException("Description vide !");
		}
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
		if (visuel==null || visuel.trim().length()==0)
		{
			throw new IllegalArgumentException("Visuel vide !");
		}
		this.visuel = visuel;
	}
	
	public int getId_categorie() {
		return this.id_categorie;
	}
	public void setId_categorie(int id_categorie) {
		this.id_categorie = id_categorie;
	}
}
