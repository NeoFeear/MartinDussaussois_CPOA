package controleur.ajout;

import dao.daofactory.DAOFactory;
import dao.daofactory.DAOFactory.Persistance;
import javafx.fxml.*;
import javafx.scene.control.*;
import modele.metier.Categorie;

public class AjoutCategorie
{
	@FXML private TextField txt_titre;
	@FXML private TextField txt_visuel;
	@FXML private Label labelAff;
	
	DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
	
	public void btnAjout() throws Exception
	{
		String titre = txt_titre.getText();
        String visuel = txt_visuel.getText();
        
        boolean titre_vide = (titre == null || titre.trim().length() == 0);
        boolean visuel_vide = (visuel == null || visuel.trim().length() == 0);
        
    	this.labelAff.setStyle("-fx-text-fill: red;");
    	if (titre_vide && visuel_vide)
            this.labelAff.setText("Tous les champs sont vides");
        
    	else if (titre_vide)
    		this.labelAff.setText("Titre vide");
    	
    	else if (visuel_vide)
    		this.labelAff.setText("Visuel vide");
    	
        else
        {
        	Categorie categorie = new Categorie(titre, visuel);
        	dao.getCategorieDAO().create(categorie);
        	this.labelAff.setStyle("-fx-text-fill: black;");
        	this.labelAff.setText(categorie.toString());
        }
	}
}