package controleur.ajout;

import dao.daofactory.DAOFactory;
import dao.daofactory.DAOFactory.Persistance;
import javafx.fxml.*;
import javafx.scene.control.*;
import modele.metier.Client;

public class AjoutClient
{
	@FXML private TextField txt_id;
	@FXML private TextField txt_mdp;
	@FXML private TextField txt_nom;
	@FXML private TextField txt_prenom;
	@FXML private TextField txt_adr_num;
	@FXML private TextField txt_adr_rue;
	@FXML private TextField txt_adr_cp;
	@FXML private TextField txt_adr_ville;
	@FXML private TextField txt_adr_pays;
	@FXML private Label labelAff;
	
	DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
	
	public void btnAjout() throws Exception
	{
		String identifiant = txt_id.getText();
        String mdp = txt_mdp.getText();
        String nom = txt_nom.getText();
        String prenom = txt_prenom.getText();
        String adr_num = txt_adr_num.getText();
        String adr_rue = txt_adr_rue.getText();
        String adr_cp = txt_adr_cp.getText();
        String adr_ville = txt_adr_ville.getText();
        String adr_pays = txt_adr_pays.getText();
        
        boolean id_vide = (identifiant == null || identifiant.trim().length() == 0);
        boolean mdp_vide = (mdp == null || mdp.trim().length() == 0);
        boolean nom_vide = (nom == null || nom.trim().length() == 0);
        boolean prenom_vide = (prenom == null || prenom.trim().length() == 0);
        boolean adr_num_vide = (adr_num == null || adr_num.trim().length() == 0);
        boolean adr_rue_vide = (adr_rue == null || adr_rue.trim().length() == 0);
        boolean adr_cp_vide = (adr_cp == null || adr_cp.trim().length() == 0);
        boolean adr_ville_vide = (adr_ville == null || adr_ville.trim().length() == 0);
        boolean adr_pays_vide = (adr_pays == null || adr_pays.trim().length() == 0);
        
    	this.labelAff.setStyle("-fx-text-fill: red;");
    	if (id_vide || mdp_vide || nom_vide || prenom_vide || adr_num_vide || adr_rue_vide || adr_cp_vide || adr_ville_vide || adr_pays_vide)
            this.labelAff.setText("Un ou plusieur champs sont vides");

        else
        {
        	dao.getClientDAO().create(new Client(1, nom, prenom, identifiant, mdp, adr_num, adr_rue, adr_cp, adr_ville, adr_pays));
        	Client client = new Client(nom, prenom, identifiant, mdp, adr_num, adr_rue, adr_cp, adr_ville, adr_pays);
        	this.labelAff.setStyle("-fx-text-fill: black;");
        	this.labelAff.setText(client.toString());
        }
	}
}
