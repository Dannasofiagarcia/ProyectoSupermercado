package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.*;

public class ClienteFarmaciaController {

	// RELOJ
	@FXML
	private Label clock;

	@FXML
	private AnchorPane anchorPane;
	@FXML
	private ResourceBundle resources;

	@FXML
	private Pane pane;
	@FXML
	private Pane pane2;
	@FXML
	private Label lblCliente1;

	@FXML
	private URL location;

	@FXML
	private TextField ruta;

	@FXML
	private Label message;

	@FXML
	private TextField idCliente1;

	@FXML
	private Label cliente1Id;

	@FXML
	private Label timeCliente1;

	@FXML
	private TextField idCliente2;

	@FXML
	private Label cliente2Id;
	@FXML
	private Label lblCliente2;
	@FXML
	private Label timeCliente2;

	@FXML
	private ImageView image;

	@FXML
	private Label data;

	@FXML
	private Label id;

	@FXML
	private Label firstName;

	@FXML
	private Label lastName;

	@FXML
	private Label email;

	@FXML
	private Label gender;

	@FXML
	private Label country;

	@FXML
	private Label birthday;

	private Farmacia miFarmacia;
	private Stage stage;

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	void exportarArchivo(ActionEvent event) {

	}

	@FXML
	void cargarArchivo(ActionEvent event) throws IOException {

	}

	@FXML
	void buscarClientes2(ActionEvent event) {

	}

	@FXML
	void buscarClientes1(ActionEvent event) {

	}

	public void showDataInScreen(ClienteFarmacia p) {

	}

	public void cambiarTiempo() {
	}

	@FXML
	void initialize() {

	}

}
