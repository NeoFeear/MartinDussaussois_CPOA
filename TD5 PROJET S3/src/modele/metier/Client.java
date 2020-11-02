package modele.metier;

public class Client {
	private int id;
	private String nom;
	private String prenom;
	private String identifiant;
	private String mdp;
	private String num_adresse;
	private String voie_adresse;
	private String cp;
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
		Client other = (Client) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public Client(int id, String nom, String prenom, String identifiant, String mdp, String num_adresse, String voie_adresse, String cp,
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
	
	public Client(String nom, String prenom, String identifiant, String mdp, String num_adresse, String voie_adresse, String cp,
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
	
	public Client(int id, String nom, String prenom, String identifiant) 
	{
		super();
		this.setId(id);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setIdentifiant(identifiant);
	}
	
	public Client(int id) 
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
		if (nom==null || nom.trim().length()==0)
		{
			throw new IllegalArgumentException("Nom vide !");
		}
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) {
		if (prenom==null || prenom.trim().length()==0)
		{
			throw new IllegalArgumentException("Prenom vide !");
		}
		this.prenom = prenom;
	}

	public String getIdentifiant() {
		return identifiant;
	}
	
	public void setIdentifiant(String identifiant) {
		if (identifiant==null || identifiant.trim().length()==0)
		{
			throw new IllegalArgumentException("Identifiant vide !");
		}
		this.identifiant = identifiant;
	}
	
	public String getMdp() {
		return mdp;
	}
	
	public void setMdp(String mdp) {
		if (mdp==null || mdp.trim().length()==0)
		{
			throw new IllegalArgumentException("Mot de passe vide !");
		}
		this.mdp = mdp;
	}
	
	public String getNum_adresse() {
		return num_adresse;
	}
	
	public void setNum_adresse(String num_adresse) {
		if (num_adresse==null || num_adresse.trim().length()==0)
		{
			throw new IllegalArgumentException("N° vide !");
		}
		this.num_adresse = num_adresse;
	}
	
	public String getVoie_adresse() {
		return voie_adresse;
	}
	
	public void setVoie_adresse(String voie_adresse) {
		if (voie_adresse==null || voie_adresse.trim().length()==0)
		{
			throw new IllegalArgumentException("Rue vide !");
		}
		this.voie_adresse = voie_adresse;
	}
	public String getCp() {
		return cp;
	}
	
	public void setCp(String cp) {
		if (prenom==null || prenom.trim().length()==0)
		{
			throw new IllegalArgumentException("Code postal vide !");
		}
		this.cp = cp;
	}
	
	public String getVille() {
		return ville;
	}
	
	public void setVille(String ville) {
		if (ville==null || ville.trim().length()==0)
		{
			throw new IllegalArgumentException("Ville vide !");
		}
		this.ville = ville;
	}
	
	public String getPays() {
		return pays;
	}
	
	public void setPays(String pays) {
		if (pays==null || pays.trim().length()==0)
		{
			throw new IllegalArgumentException("Pays vide !");
		}
		this.pays = pays;
	}
	
	@Override
	public String toString() {
		return nom + " " + prenom + "\n" + num_adresse + " " + voie_adresse + " " + cp + " " + ville + " " + pays;
	}
}
