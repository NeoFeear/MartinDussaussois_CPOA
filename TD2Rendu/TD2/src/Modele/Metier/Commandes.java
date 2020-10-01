package Modele.Metier;

import java.sql.Date;

public class Commandes {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_commande;
		return result;
	}

	private int id_commande;
	private Date date;
	private int id_client;
	
	public Commandes(int id_commande, Date date, int id_client) {
		super();
		this.id_commande = id_commande;
		this.date = date;
		this.id_client = id_client;
	}
	
	public Commandes(Date date, int id_client) {
		super();
		this.date = date;
		this.id_client = id_client;
	}
	
	public Commandes(int id) 
	{
		super();
		this.setId_commande(id);
	}
	
	public int getId_commande() {
		return id_commande;
	}
	
	public void setId_commande(int id_commande) {
		this.id_commande = id_commande;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getId_client() {
		return id_client;
	}
	
	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
}