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
import modele.metier.Produit;

public class CtrlProduit extends Stage implements Initializable, ChangeListener<Produit> {
	@FXML private TableView<Produit> tblProduit;
	@FXML private Button btnModifProd;
	@FXML private Button btnSupprProd;
	@FXML private Button btnAnnulerSelec;

	public static Produit prod;
	
	DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);

	public void refreshTable() throws Exception {
		tblProduit.setItems(FXCollections.observableArrayList(dao.getProduitDAO().findAll()));
		this.tblProduit.getSelectionModel().selectedItemProperty().addListener(this);
	}
	
	@SuppressWarnings("unchecked")
	public void initialize(URL location, ResourceBundle resources) {
		try {
			TableColumn<Produit, String> colID = new TableColumn<>("ID");
			colID.setCellValueFactory(new PropertyValueFactory<Produit, String>("id"));
	
			TableColumn<Produit, String> colNom = new TableColumn<>("Nom");
			colNom.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
	
			TableColumn<Produit, String> colTarif = new TableColumn<>("Tarif");
			colTarif.setCellValueFactory(new PropertyValueFactory<Produit, String>("tarif"));
	
			TableColumn<Produit, String> colCateg = new TableColumn<>("Categorie");
			colCateg.setCellValueFactory(new PropertyValueFactory<Produit, String>("categorie"));
	
			this.tblProduit.getColumns().setAll(colID, colNom, colTarif, colCateg);
	
			refreshTable(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void changed(ObservableValue<? extends Produit> observable, Produit oldValue, Produit newValue) {
		this.btnModifProd.setDisable(newValue == null);
		this.btnSupprProd.setDisable(newValue == null);
		this.btnAnnulerSelec.setDisable(newValue == null);
	}

	public void ajoutProduit() {
		try {
			URL fxmlURL = getClass().getResource("/vue/vue.ajout/produit_ajout.fxml");
			Stage ajoutStage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Node root = fxmlLoader.load();
			Scene scene = new Scene((VBox) root, 600, 400);
			ajoutStage.setScene(scene);
			ajoutStage.setTitle("Ajout Produit");
			ajoutStage.showAndWait();

			refreshTable();
			
			// ajoutStage.setX(ajoutStage.getX() + 800);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void modifProduit() {
		try {
			CtrlProduit.prod = tblProduit.getSelectionModel().getSelectedItem();

			URL fxmlURL = getClass().getResource("/vue/vue.modif/produit_modif.fxml");
			Stage ajoutStage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Node root = fxmlLoader.load();
			Scene scene = new Scene((VBox) root, 600, 400);
			ajoutStage.setScene(scene);
			ajoutStage.setTitle("Modification Produit");
			ajoutStage.showAndWait();
			
			refreshTable();

			// ajoutStage.setX(ajoutStage.getX() + 800);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void supprProduit() {
		try {
			Produit prod = this.tblProduit.getSelectionModel().getSelectedItem();
			dao.getProduitDAO().delete(prod);
			
			refreshTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void annulerSelection() {
		try {
			this.btnModifProd.setDisable(true);
			this.btnSupprProd.setDisable(true);
			this.btnAnnulerSelec.setDisable(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}