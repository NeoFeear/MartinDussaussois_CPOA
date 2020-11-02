package controleur;

import java.net.URL;
import java.util.ResourceBundle;
import dao.daofactory.DAOFactory;
import dao.daofactory.DAOFactory.Persistance;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.metier.Categorie;

public class CtrlCategorie extends Stage implements Initializable, ChangeListener<Categorie> {
	@FXML public TableView<Categorie> tblCategorie;
	@FXML private Button btnModifCateg;
	@FXML private Button btnSupprCateg;
	@FXML private Button btnAnnulerSelec;

	public static Categorie categ;
	
	DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);

	public void refreshTable() throws Exception {
		tblCategorie.setItems(FXCollections.observableArrayList(dao.getCategorieDAO().findAll()));
		this.tblCategorie.getSelectionModel().selectedItemProperty().addListener(this);
	}
	
	@SuppressWarnings("unchecked")
	public void initialize(URL location, ResourceBundle resources) {
		try {
			TableColumn<Categorie, String> colID = new TableColumn<>("ID");
			colID.setCellValueFactory(new PropertyValueFactory<Categorie, String>("id"));

			TableColumn<Categorie, String> colTitre = new TableColumn<>("Titre");
			colTitre.setCellValueFactory(new PropertyValueFactory<Categorie, String>("titre"));

			TableColumn<Categorie, String> colVisuel = new TableColumn<>("Visuel");
			colVisuel.setCellValueFactory(new PropertyValueFactory<Categorie, String>("visuel"));

			this.tblCategorie.getColumns().setAll(colID, colTitre, colVisuel);
			
			refreshTable();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void changed(ObservableValue<? extends Categorie> observable, Categorie oldValue, Categorie newValue) {
		this.btnModifCateg.setDisable(newValue == null);
		this.btnSupprCateg.setDisable(newValue == null);
		this.btnAnnulerSelec.setDisable(newValue == null);
	}

	public void ajoutCategorie() {
		try {
			URL fxmlURL = getClass().getResource("/vue/vue.ajout/categorie_ajout.fxml");
			Stage ajoutStage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Node root = fxmlLoader.load();
			Scene scene = new Scene((VBox) root, 600, 400);
			ajoutStage.setScene(scene);
			ajoutStage.setTitle("Ajout Categorie");
			ajoutStage.showAndWait();
			
			refreshTable();

			// ajoutStage.setX(ajoutStage.getX() + 800);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void modifCateg() {
		try {
			CtrlCategorie.categ = tblCategorie.getSelectionModel().getSelectedItem();

			URL fxmlURL = getClass().getResource("/vue/vue.modif/categorie_modif.fxml");
			Stage ajoutStage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Node root = fxmlLoader.load();
			Scene scene = new Scene((VBox) root, 600, 400);
			ajoutStage.setScene(scene);
			ajoutStage.setTitle("Modification Categorie");
			ajoutStage.showAndWait();
			
			refreshTable();

			// ajoutStage.setX(ajoutStage.getX() + 800);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void supprCategorie() {
		try {
			Categorie categ = this.tblCategorie.getSelectionModel().getSelectedItem();
			dao.getCategorieDAO().delete(categ);
			
			refreshTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void annulerSelection() {
		try {
			//tblCategorie.setItems(FXCollections.observableArrayList(DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE).getCategorieDAO().findAll()));
			this.btnModifCateg.setDisable(true);
			this.btnSupprCateg.setDisable(true);
			this.btnAnnulerSelec.setDisable(true);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}