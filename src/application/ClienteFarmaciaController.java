package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelo.ClienteFarmacia;
import modelo.Farmacia;
import threads.TiempoActualClienteThread;

public class ClienteFarmaciaController {

	// RELOJ
	@FXML
	private Label clock;
	private TiempoActualClienteThread tac;
	private LocalTime tiempoActual;

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
		Stage stage = new Stage();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Abrir archivo");
		fileChooser.showOpenDialog(stage);
	}

	@FXML
	void cargarArchivo(ActionEvent event) throws IOException {
		ruta.setText(miFarmacia.cargarArchivoYAgregarAlArbol());
		message.setVisible(true);
	}

	@FXML
	void buscarClientes2(ActionEvent event) {
		long time = System.currentTimeMillis();
		try {
			int id = Integer.parseInt(idCliente2.getText());
			showDataInScreen(miFarmacia.buscarPorClienteEspecial(id));
		} catch (NumberFormatException e) {
			Alert score = new Alert(AlertType.ERROR);
			score.setTitle("CLIENTES DE LA FARMACIA");
			score.initStyle(StageStyle.DECORATED);
			score.setContentText("Por favor introduzca un numero");
			score.show();
		} catch (NullPointerException npe) {
			Alert score = new Alert(AlertType.ERROR);
			score.setTitle("CLIENTES DE LA FARMACIA");
			score.initStyle(StageStyle.DECORATED);
			score.setContentText("Este cliente no fue escogido como cliente especial");
			score.show();
			lblCliente2.setDisable(false);
			cliente2Id.setDisable(false);
			cliente2Id.setText(idCliente2.getText());
		}
		timeCliente2.setText("" + (System.currentTimeMillis() - time));
		idCliente2.setText("");
	}

	@FXML
	void buscarClientes1(ActionEvent event) {
		long time = System.currentTimeMillis();
		try {
			int id = Integer.parseInt(idCliente1.getText());
			showDataInScreen(miFarmacia.buscarClientesEspeciales(id));
		} catch (NumberFormatException e) {
			Alert score = new Alert(AlertType.ERROR);
			score.setTitle("CLIENTES DE LA FARMACIA");
			score.initStyle(StageStyle.DECORATED);
			score.setContentText("Por favor introduzca un numero");
			score.show();
		} catch (NullPointerException npe) {
			Alert score = new Alert(AlertType.ERROR);
			score.setTitle("CLIENTES DE LA FARMACIA");
			score.initStyle(StageStyle.DECORATED);
			score.setContentText("El id no pertenece a ningun cliente");
			score.show();
			lblCliente1.setDisable(false);
			cliente1Id.setDisable(false);
			cliente1Id.setText(idCliente1.getText());
		}
		timeCliente1.setText("" + (System.currentTimeMillis() - time));
		idCliente1.setText("");
	}

	public void showDataInScreen(ClienteFarmacia p) {
		image.setImage(p.getFoto());
		id.setText(p.getId() + "");
		firstName.setText(p.getNombre());
		lastName.setText(p.getApellido());
		email.setText(p.getEmail());
		gender.setText(p.getGenero());
		country.setText(p.getPais());
		birthday.setText(p.getCumpleanios());
	}

	public void cambiarTiempo() {
		tiempoActual = tiempoActual.plusSeconds(1);
		clock.setText(DateTimeFormatter.ofPattern("hh:mm:ss a").format(tiempoActual));
	}

	@FXML
	void initialize() {
		miFarmacia = new Farmacia();
		lblCliente1.setDisable(true);
		cliente1Id.setDisable(true);
		lblCliente2.setDisable(true);
		cliente2Id.setDisable(true);
		message.setVisible(false);
		anchorPane.setStyle("-fx-background-color: #acdfe8;");
		pane2.setStyle("-fx-background-color: #d1e697;");
		tiempoActual = LocalTime.now();
		cambiarTiempo();
		tac = new TiempoActualClienteThread(this);
		tac.start();

	}

}
