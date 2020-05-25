package application;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Farmacia;
import threads.BarThread;
import threads.TiempoActualFarmaciaThread;

public class FarmaciaController {

	@FXML
	private Rectangle carga;

	@FXML
	private Rectangle barraFondo;
	@FXML
	private Label lblRectangulo;
	@FXML
	private Rectangle rectangulo;

	// RELOJ
	@FXML
	private Label clock;
	private LocalTime tiempoActual;
	private TiempoActualFarmaciaThread taf;
	private Stage stage;

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	private Pane pane;

	@FXML
	private Pane paneOpciones;

	@FXML
	private BorderPane borderPane;

	@FXML
	private Label cargando;

	@FXML
	private Label paciencia;

	private Farmacia farmacia;
	private AudioClip audio;
	private BarThread barThread;

	public void cambiarTiempo() {
		tiempoActual = tiempoActual.plusSeconds(1);
		clock.setText(DateTimeFormatter.ofPattern("hh:mm:ss a").format(tiempoActual));
	}

	@FXML
	public void updateBar() {
		carga.setWidth(carga.getWidth() + 1);
		if (carga.getWidth() == 490) {
			barThread.desactivate();
			cargando.setVisible(false);
			paciencia.setVisible(false);
			barraFondo.setVisible(false);
			carga.setVisible(false);
			paneOpciones.setVisible(true);
		}
	}

	@FXML
	private void initialize() {
		farmacia = new Farmacia();
		tiempoActual = LocalTime.now();
		cambiarTiempo();
		taf = new TiempoActualFarmaciaThread(this);
		taf.start();
		paneOpciones.setVisible(false);
		barThread = new BarThread(this);
		barThread.start();

	}

	public void playMusic() {
		audio = new AudioClip(this.getClass().getResource("backgroundmusic.mp3").toString());
		audio.play();
	}

	@FXML
	void verInformacionDeLosClientes(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ClienteFarmacia.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			ClienteFarmaciaController cfc = new ClienteFarmaciaController();
			stage = new Stage();
			cfc.setStage(stage);
			stage.setTitle("Informacion de los clientes");
			stage.centerOnScreen();
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (IOException e) {

		}
	}

	@FXML
	void verInventario(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Inventario.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			InventarioController fc = new InventarioController();
			stage = new Stage();
			fc.setStage(stage);
			stage.setTitle("INVENTARIO");
			stage.centerOnScreen();
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(root1));
			stage.show();

		} catch (IOException e) {

		}
	}

}
