package Modele.Metier;

public class Categories {
	private int id;
	private String titre;
	private String visuel;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categories other = (Categories) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public Categories(int id, String titre, String visuel) 
	{
		super();
		this.setId(id);
		this.setTitre(titre);
		this.setVisuel(visuel);
	}

	public Categories(String titre, String visuel) 
	{
		super();
		this.setTitre(titre);
		this.setVisuel(visuel);
	}

	public Categories(int id) 
	{
		super();
		this.setId(id);
	}
	
	public Categories() 
	{
		super();
	}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String titre) {
		if (titre==null || titre.trim().length()==0)
		{
			throw new IllegalArgumentException("Titre vide !");
		}
		this.titre = titre;
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

}