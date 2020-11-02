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
import modele.metier.Client;

public class CtrlClient extends Stage implements Initializable, ChangeListener<Client> {
	@FXML private TableView<Client> tblClient;
	@FXML private Button btnModifClient;
	@FXML private Button btnSupprClient;
	@FXML private Button btnAnnulerSelec;

	public static Client client;
	
	DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);

	public void refreshTable() throws Exception {
		tblClient.setItems(FXCollections.observableArrayList(dao.getClientDAO().findAll()));
		this.tblClient.getSelectionModel().selectedItemProperty().addListener(this);
	}
	
	@SuppressWarnings("unchecked")
	public void initialize(URL location, ResourceBundle resources) {
		try {
		TableColumn<Client, String> colID = new TableColumn<>("ID");
		colID.setCellValueFactory(new PropertyValueFactory<Client, String>("id"));

		TableColumn<Client, String> colNom = new TableColumn<>("Nom");
		colNom.setCellValueFactory(new PropertyValueFactory<Client, String>("nom"));

		TableColumn<Client, String> colPrenom = new TableColumn<>("Prenom");
		colPrenom.setCellValueFactory(new PropertyValueFactory<Client, String>("prenom"));

		TableColumn<Client, String> colVille = new TableColumn<>("Ville");
		colVille.setCellValueFactory(new PropertyValueFactory<Client, String>("ville"));

		TableColumn<Client, String> colPays = new TableColumn<>("Pays");
		colPays.setCellValueFactory(new PropertyValueFactory<Client, String>("pays"));

		this.tblClient.getColumns().setAll(colID, colNom, colPrenom, colVille, colPays);

		refreshTable();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void changed(ObservableValue<? extends Client> observable, Client oldValue, Client newValue) {
		this.btnModifClient.setDisable(newValue == null);
		this.btnSupprClient.setDisable(newValue == null);
		this.btnAnnulerSelec.setDisable(newValue == null);
	}

	public void ajoutClient() {
		try {
			URL fxmlURL = getClass().getResource("/vue/vue.ajout/client_ajout.fxml");
			Stage ajoutStage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Node root = fxmlLoader.load();
			Scene scene = new Scene((VBox) root, 600, 400);
			ajoutStage.setScene(scene);
			ajoutStage.setTitle("Ajout Client");
			ajoutStage.showAndWait();

			refreshTable();
			
			// ajoutStage.setX(ajoutStage.getX() + 800);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void modifClient() {
		try {
			CtrlClient.client = tblClient.getSelectionModel().getSelectedItem();

			URL fxmlURL = getClass().getResource("/vue/vue.modif/client_modif.fxml");
			Stage ajoutStage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Node root = fxmlLoader.load();
			Scene scene = new Scene((VBox) root, 600, 400);
			ajoutStage.setScene(scene);
			ajoutStage.setTitle("Modification Client");
			ajoutStage.showAndWait();
			
			refreshTable();

			// ajoutStage.setX(ajoutStage.getX() + 800);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void supprClient() {
		try {
			Client client = this.tblClient.getSelectionModel().getSelectedItem();
			dao.getClientDAO().delete(client);
			
			refreshTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void annulerSelection() {
		try {
			this.btnModifClient.setDisable(true);
			this.btnSupprClient.setDisable(true);
			this.btnAnnulerSelec.setDisable(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}