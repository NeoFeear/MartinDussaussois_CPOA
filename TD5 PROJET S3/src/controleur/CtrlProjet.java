package controleur;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import dao.daofactory.DAOFactory;
import dao.daofactory.DAOFactory.Persistance;

public class CtrlProjet implements Initializable  
{	
	static DAOFactory daos;
	public static Persistance PERSISTANCE;
	
	@Override
    public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Démarrage !");
		menuButton.setText("MYSQL");
		PERSISTANCE = Persistance.MYSQL;
	}
	
	@FXML MenuButton menuButton;
	
	public void mysql() {
		System.out.println("MySQL !");
		menuButton.setText("MYSQL");
		PERSISTANCE = Persistance.MYSQL;
	}

	public void liste_memoire() {
		System.out.println("Liste Mémoire !");
		menuButton.setText("LISTE_MEMOIRE");
		PERSISTANCE = Persistance.LISTE_MEMOIRE;
	}
	
}
