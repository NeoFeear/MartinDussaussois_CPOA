package Modele.Metier;

public class Clients {

	private int id;
	private String nom;
	private String prenom;
	private String identifiant;
	private String mdp;
	private int num_adresse;
	private String voie_adresse;
	private int cp;
	private String ville;
	private String pays;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Clients other = (Clients) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public Clients(int id, String nom, String prenom, String identifiant, String mdp, int num_adresse, String voie_adresse, int cp,
			String ville, String pays) 
	{
		super();
		this.setId(id);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setIdentifiant(identifiant);
		this.setMdp(mdp);
		this.setNum_adresse(num_adresse);
		this.setVoie_adresse(voie_adresse);
		this.setCp(cp);
		this.setVille(ville);
		this.setPays(pays);
	}
	
	public Clients(String nom, String prenom, String identifiant, String mdp, int num_adresse, String voie_adresse, int cp,
			String ville, String pays) 
	{
		super();
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setIdentifiant(identifiant);
		this.setMdp(mdp);
		this.setNum_adresse(num_adresse);
		this.setVoie_adresse(voie_adresse);
		this.setCp(cp);
		this.setVille(ville);
		this.setPays(pays);
	}
	
	public Clients(int id, String nom, String prenom, String identifiant) 
	{
		super();
		this.setId(id);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setIdentifiant(identifiant);
	}
	
	public Clients(int id) 
	{
		super();
		this.setId(id);
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getIdentifiant() {
		return identifiant;
	}
	
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	
	public String getMdp() {
		return mdp;
	}
	
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	public int getNum_adresse() {
		return num_adresse;
	}
	
	public void setNum_adresse(int num_adresse) {
		this.num_adresse = num_adresse;
	}
	
	public String getVoie_adresse() {
		return voie_adresse;
	}
	
	public void setVoie_adresse(String voie_adresse) {
		this.voie_adresse = voie_adresse;
	}
	public int getCp() {
		return cp;
	}
	
	public void setCp(int cp) {
		this.cp = cp;
	}
	
	public String getVille() {
		return ville;
	}
	
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	public String getPays() {
		return pays;
	}
	
	public void setPays(String pays) {
		this.pays = pays;
	}
}