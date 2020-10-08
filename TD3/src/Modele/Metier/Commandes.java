package Modele.Metier;

import java.time.LocalDate;

public class Commandes {
	private int id_com;
	private LocalDate date_com;
	private int id_client;

	public Commandes(int id_com, LocalDate date_com, int id_client) {
		super();
		this.id_com = id_com;
		this.date_com = date_com;
		this.id_client = id_client;
	}
	
	public Commandes(int id_com) {
		super();
		this.id_com = id_com;
	}

	public int getId_com() {
		return id_com;
	}

	public void setId_com(int id_com) {
		this.id_com = id_com;
	}

	public LocalDate getDate_com() {
		return date_com;
	}

	public void setDate_com(LocalDate date_com) {
		this.date_com = date_com;
	}

	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}

	@Override
	public String toString() {
		return "CommandeMetier [id_com=" + id_com + ", date_com=" + date_com + ", id_client=" + id_client + "]";
	}
}