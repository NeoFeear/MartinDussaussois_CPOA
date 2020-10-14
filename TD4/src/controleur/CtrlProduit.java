package controleur;

import java.net.URL;
import java.util.ResourceBundle;
import dao.daofactory.DAOFactory;
import dao.daofactory.DAOFactory.Persistance;
import javafx.collections.FXCollections;
import javafx.fxml.*;
import javafx.scene.control.*;
import modele.metier.Categorie;
import modele.metier.Produit;

public class CtrlProduit implements Initializable 
{
	@FXML private TextField txt_nom;
	@FXML private TextArea txt_description;
	@FXML private TextField txt_tarif;
	@FXML private ChoiceBox<Categorie> cbx_categorie;
	@FXML private Label labelAff;
	
	DAOFactory dao = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
	
	@Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
			this.cbx_categorie.setItems(FXCollections.observableArrayList(dao.getCategorieDAO().findAll()));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	public void creerModele() 
	{
		String nom = txt_nom.getText();
        String desc = txt_description.getText();

        double tarif;
        if (txt_tarif.getText().trim().length()>0)
            tarif = Double.parseDouble(txt_tarif.getText());
        else
            tarif = 0;

        Categorie categ = cbx_categorie.getValue();
        
        boolean nom_vide = (nom == null || nom.trim().length() == 0);
        boolean desc_vide = (desc == null || desc.trim().length() == 0);
        boolean tarif_vide = (tarif == 0);
        boolean categ_vide = (categ == null);
        
    	this.labelAff.setStyle("-fx-text-fill: red;");
	
    	if (nom_vide && desc_vide && tarif_vide && categ_vide)
            this.labelAff.setText("Tous les champs sont vides");
    	
    	else if (nom_vide && (tarif_vide || categ_vide))
            this.labelAff.setText("Un ou plusieurs champs sont vides");
    	
    	else if (desc_vide && (tarif_vide || categ_vide))
            this.labelAff.setText("Un ou plusieurs champs sont vides");
    	
    	else if (tarif_vide && (desc_vide || nom_vide || categ_vide))
            this.labelAff.setText("Un ou plusieurs champs sont vides");
    	
    	else if (categ_vide && (desc_vide || tarif_vide || nom_vide))
            this.labelAff.setText("Un ou plusieurs champs sont vides");
    		
    	else if (nom_vide && desc_vide)
            this.labelAff.setText("Nom et description vides");
    	
    	else if (nom_vide)
            this.labelAff.setText("Nom vide");
    	
        else if (desc_vide)
            this.labelAff.setText("Description vide");
    	
        /*else if (tarif non numerique)
        	this.labelAff.setText("Le tarif n'est pas au format numerique");*/
    	
        else if (categ_vide)
        	this.labelAff.setText("Veuillez selectionner une categorie");
        	
        else
        {
        	Produit produit = new Produit(nom, desc, tarif, categ);
        	this.labelAff.setStyle("-fx-text-fill: black;");
        	this.labelAff.setText(produit.toString());
        }
	}
}
