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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Farmacia;

public class FarmaciaController {
	@FXML
	private Circle pupila1;

	@FXML
	private Circle pupila2;

	@FXML
	private Circle cara;
	@FXML
	private Circle ojo1;
	@FXML
	private Circle ojo2;
	@FXML
	private Arc boca;

	@FXML
	private Circle dec1;

	@FXML
	private Circle dec3;
	@FXML
	private Circle dec2;

	@FXML
	private Circle dec4;

	@FXML
	private Rectangle caja;

	@FXML
	private Rectangle cruz2;

	@FXML
	private Rectangle cruz1;
	@FXML
	private Label lblRectangulo;
	@FXML
	private Rectangle rectangulo;

	// RELOJ
	@FXML
	private Label clock;
	private LocalTime tiempoActual;
	private Stage stage;

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	void cambiarColor(MouseEvent event) {

	}

	@FXML
	private Pane pane;

	@FXML
	private BorderPane borderPane;

	private Farmacia farmacia;
	private AudioClip audio;

	public void cambiarTiempo() {
		tiempoActual = tiempoActual.plusSeconds(1);
		clock.setText(DateTimeFormatter.ofPattern("hh:mm:ss a").format(tiempoActual));
	}

	@FXML
	private void initialize() {

	}

	public void colorearVerde() {
		cara.setFill(Color.GREENYELLOW);
		ojo1.setFill(Color.WHITE);
		ojo2.setFill(Color.WHITE);
		boca.setFill(Color.CORAL);
		pupila1.setFill(Color.BLACK);
		pupila2.setFill(Color.BLACK);
		caja.setFill(Color.LIGHTPINK);
		cruz1.setFill(Color.MEDIUMTURQUOISE);
		cruz2.setFill(Color.MEDIUMTURQUOISE);
		dec1.setFill(Color.MEDIUMSPRINGGREEN);
		dec2.setFill(Color.MEDIUMSPRINGGREEN);
		dec3.setFill(Color.MEDIUMSPRINGGREEN);
		dec4.setFill(Color.MEDIUMSPRINGGREEN);

	}

	public void colorearVioleta() {
		cara.setFill(Color.BLUEVIOLET);
		ojo1.setFill(Color.WHITE);
		ojo2.setFill(Color.WHITE);
		pupila1.setFill(Color.BLACK);
		pupila2.setFill(Color.BLACK);
		boca.setFill(Color.YELLOW);
		caja.setFill(Color.PALEGREEN);
		cruz1.setFill(Color.YELLOW);
		cruz2.setFill(Color.YELLOW);
		dec1.setFill(Color.LIGHTSALMON);
		dec2.setFill(Color.LIGHTSALMON);
		dec3.setFill(Color.LIGHTSALMON);
		dec4.setFill(Color.LIGHTSALMON);
	}

	public void colorearAmarillo() {
		cara.setFill(Color.YELLOW);
		pupila1.setFill(Color.BLACK);
		pupila2.setFill(Color.BLACK);
		ojo1.setFill(Color.WHITE);
		ojo2.setFill(Color.WHITE);
		boca.setFill(Color.BLUEVIOLET);
		caja.setFill(Color.ORANGE);
		cruz1.setFill(Color.LIGHTCORAL);
		cruz2.setFill(Color.LIGHTCORAL);
		dec1.setFill(Color.STEELBLUE);
		dec2.setFill(Color.STEELBLUE);
		dec3.setFill(Color.STEELBLUE);
		dec4.setFill(Color.STEELBLUE);
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
