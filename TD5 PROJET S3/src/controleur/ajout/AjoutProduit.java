package controleur.ajout;

import java.net.URL;
import java.util.ResourceBundle;
import dao.daofactory.DAOFactory;
import dao.daofactory.DAOFactory.Persistance;
import javafx.collections.FXCollections;
import javafx.fxml.*;
import javafx.scene.control.*;
import modele.metier.Categorie;
import modele.metier.Produit;

public class AjoutProduit implements Initializable
{
	@FXML private TextField txt_nom;
	@FXML private TextArea txt_description;
	@FXML private TextField txt_tarif;
	@FXML private TextField txt_visuel;
	@FXML private ChoiceBox<Categorie> cbx_categorie;
	@FXML private Label labelAff;
	
	DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
	
	@Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
			this.cbx_categorie.setItems(FXCollections.observableArrayList(dao.getCategorieDAO().findAll()));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	public void btnAjout() throws Exception
	{
		String nom = txt_nom.getText();
        String desc = txt_description.getText();

        double tarif;
        if (txt_tarif.getText().trim().length()>0)
            tarif = Double.parseDouble(txt_tarif.getText());
        else
            tarif = 0;
        
        String visuel = txt_visuel.getText();
        Categorie categ = cbx_categorie.getValue();
        
        boolean nom_vide = (nom == null || nom.trim().length() == 0);
        boolean desc_vide = (desc == null || desc.trim().length() == 0);
        boolean tarif_vide = (tarif == 0);
        boolean visuel_vide = (visuel == null || visuel.trim().length() == 0);
        boolean categ_vide = (categ == null);
        
    	this.labelAff.setStyle("-fx-text-fill: red;");
    	if (nom_vide || desc_vide || tarif_vide || visuel_vide || categ_vide)
            this.labelAff.setText("Un ou plusieurs champs sont vides");
    	
        /*else if (tarif non numerique)
        	this.labelAff.setText("Le tarif n'est pas au format numerique");*/
        	
        else
        {
        	dao.getProduitDAO().create(new Produit(1, nom, desc, tarif, visuel, categ));
        	Produit produit = new Produit(nom, desc, tarif, categ);
        	this.labelAff.setStyle("-fx-text-fill: black;");
        	this.labelAff.setText(produit.toString());
        }
	}
}
