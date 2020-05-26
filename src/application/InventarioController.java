package application;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelo.Farmacia;
import modelo.Producto;
import modelo.Tiempo;
import threads.TiempoActualInventarioThread;

public class InventarioController {

	private Farmacia s;
	private TableView<Producto> table;
	private ObservableList<Producto> data;
	private final static int rowsPerPage = 14;

	@FXML
	private GridPane gridPane;
	@FXML
	private Pagination paginas;
	@FXML
	private BorderPane borderPane;
	@FXML
	private ComboBox<String> opcionesDeBusqueda;
	@FXML
	private TextField numeroDeProductos;
	@FXML
	private Label tiempo;
	@FXML
	private TextField buscar;

	// THREAD RELOJ
	private LocalTime tiempoActual;
	private TiempoActualInventarioThread tat;
	@FXML
	private Label clock;

	private Stage stage;

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	public void generarInventario(ActionEvent event) {
		try {
			long start = System.currentTimeMillis();
			int n = Integer.parseInt(numeroDeProductos.getText());
			s = new Farmacia(n);
			s.generarInformacionDeLosProductosDeLaFarmacia();
			long endTime = (System.currentTimeMillis() - start) / 1000;
			tiempo.setText("Time: " + endTime);
			table = createTable();
			borderPane.setCenter(table);
			paginas.setPageFactory(this::createPage);
		} catch (NumberFormatException e) {
			Alert info = new Alert(AlertType.ERROR);
			info.setTitle("INVENTARIO");
			info.setHeaderText(null);
			info.initStyle(StageStyle.UTILITY);
			info.setContentText("Please introduce a number");
			info.show();
		}
	}

	@FXML
	public void buscarProducto(ActionEvent event) {
		String o = opcionesDeBusqueda.getValue();
		try {

			if (o.equals("Fecha de llegada")) {
				buscar.setPromptText("AAAA-MM-DD");
				String value = buscar.getText();
				long start = System.currentTimeMillis();
				Alert info = new Alert(AlertType.INFORMATION);
				info.setTitle("Informacion del producto");
				info.setHeaderText(null);
				info.initStyle(StageStyle.UTILITY);

				info.setContentText(s.buscarElProductoPorLaFechaDeLlegada(value));
				info.show();
				long endTime = (System.currentTimeMillis() - start) / 1000;
				tiempo.setText("Time: " + endTime);

			} else if (o.equals("Horario")) {
				long start = System.currentTimeMillis();
				buscar.setPromptText("hh:ss AM/PM");
				String value = buscar.getText();
				Alert info = new Alert(AlertType.INFORMATION);
				info.setTitle("Informacion del producto");
				info.setHeaderText(null);
				info.initStyle(StageStyle.UTILITY);
				info.setContentText(s.buscarProductoPorHorario(value));
				info.show();
				long endTime = (System.currentTimeMillis() - start) / 1000;
				tiempo.setText("Time: " + endTime);
			} else if (o.equals("Nombre de la marca")) {
				long start = System.currentTimeMillis();
				buscar.setPromptText("Nombre de la marca");
				String value = buscar.getText();

				Alert info = new Alert(AlertType.INFORMATION);
				info.setTitle("Informacion del producto");
				info.setHeaderText(null);
				info.initStyle(StageStyle.UTILITY);
				info.setContentText(s.buscarProductoPorLaMarca(value));
				info.show();
				long endTime = (System.currentTimeMillis() - start) / 1000;
				tiempo.setText("Time: " + endTime);
			} else if (o.equals("Informacion del producto")) {
				long start = System.currentTimeMillis();
				buscar.setPromptText("Codigo del producto");
				String value = buscar.getText();
				Alert info = new Alert(AlertType.INFORMATION);
				info.setTitle("Informacion del producto");
				info.setHeaderText(null);
				info.initStyle(StageStyle.UTILITY);
				info.setContentText(s.buscarProductoPorElCofigo(value));
				info.show();
				long endTime = (System.currentTimeMillis() - start) / 1000;
				tiempo.setText("Time: " + endTime);
			} else if (o.equals("Nombre del producto")) {
				long start = System.currentTimeMillis();
				buscar.setPromptText("Nombre del producto ");
				String value = buscar.getText();
				Alert info = new Alert(AlertType.INFORMATION);
				info.setTitle("Informacion del producto");
				info.setHeaderText(null);
				info.initStyle(StageStyle.UTILITY);
				info.setContentText(s.buscarProductoPorElNombre(value));
				info.show();
				long endTime = (System.currentTimeMillis() - start) / 1000;
				tiempo.setText("Time: " + endTime);
			} else if (o.equals("Tipo")) {
				long start = System.currentTimeMillis();
				buscar.setPromptText("Tipo");
				String value = buscar.getText();
				Alert info = new Alert(AlertType.INFORMATION);
				info.setTitle("Informacion del producto");
				info.setHeaderText(null);
				info.initStyle(StageStyle.UTILITY);
				info.setContentText(s.buscarProductoPorElTipo(value));
				info.show();
				long endTime = (System.currentTimeMillis() - start) / 1000;
				tiempo.setText("Time: " + endTime);
			}
		} catch (NumberFormatException e) {
			Alert info = new Alert(AlertType.ERROR);
			info.setTitle("Informacion del producto");
			info.setHeaderText(null);
			info.initStyle(StageStyle.UTILITY);
			info.setContentText("Please introduce a value valid");
			info.show();
		}

	}

	@FXML
	public void ordenarPorMarca(ActionEvent event) {
		long start = System.currentTimeMillis();
		s.ordenarPorElNombreDeLaMarca();
		long endTime = (System.currentTimeMillis() - start) / 1000;
		tiempo.setText("Time: " + endTime);
		table = createTable();
		paginas.setPageFactory(this::createPage);
	}

	@FXML
	public void ordenarPorNombreDelProducto(ActionEvent event) {
		long start = System.currentTimeMillis();
		s.ordenarBurbujaPorElNombreDelProducto();
		long endTime = (System.currentTimeMillis() - start) / 1000;
		tiempo.setText("Time: " + endTime);
		table = createTable();
		paginas.setPageFactory(this::createPage);
	}

	@FXML
	public void ordenarPorFechaDeLlegada(ActionEvent event) {
		long start = System.currentTimeMillis();
		s.ordenamientoDeInsercionPorLaFechaDeLlegada();
		long endTime = (System.currentTimeMillis() - start) / 1000;
		tiempo.setText("Time: " + endTime);
		table = createTable();
		paginas.setPageFactory(this::createPage);
	}

	@FXML
	public void ordenarPorCodigo(ActionEvent event) {
		long start = System.currentTimeMillis();
		s.ordenamientoDeSeleccionPorElCodigo();
		long endTime = (System.currentTimeMillis() - start) / 1000;
		tiempo.setText("Time: " + endTime);
		table = createTable();
		paginas.setPageFactory(this::createPage);
	}

	@FXML
	public void ordenarPorTipo(ActionEvent event) {
		long start = System.currentTimeMillis();
		s.ordenamientoDeInsercionPorElTipoDeProducto();
		long endTime = (System.currentTimeMillis() - start) / 1000;
		tiempo.setText("Time: " + endTime);
		table = createTable();
		paginas.setPageFactory(this::createPage);
	}

	@FXML
	public void ordenarPorTiempoDeLlegada(ActionEvent event) {
		long start = System.currentTimeMillis();
		s.ordenamientoInsercionPorHoraDeLlegada();
		long endTime = (System.currentTimeMillis() - start) / 1000;
		tiempo.setText("Time: " + endTime);
		table = createTable();
		paginas.setPageFactory(this::createPage);
	}

	@SuppressWarnings("unchecked")
	private TableView<Producto> createTable() {
		table = new TableView<Producto>();
		data = createData();
		table.setEditable(true);
		TableColumn<Producto, Date> date = new TableColumn<Producto, Date>("Fecha de llegada");
		date.setCellValueFactory(new PropertyValueFactory<Producto, Date>("fechaDeLlegada"));

		TableColumn<Producto, Tiempo> schedule = new TableColumn<Producto, Tiempo>("Horario");
		schedule.setCellValueFactory(new PropertyValueFactory<Producto, Tiempo>("horario"));

		TableColumn<Producto, String> nameAirline = new TableColumn<Producto, String>("Marca del producto");
		nameAirline.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombreDeLaMarca"));

		TableColumn<Producto, String> idAirline = new TableColumn<Producto, String>("Codigo");
		idAirline.setCellValueFactory(new PropertyValueFactory<Producto, String>("codigoDelProducto"));

		TableColumn<Producto, String> destinationCity = new TableColumn<Producto, String>("Nombre del producto");
		destinationCity.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombreDelProducto"));

		TableColumn<Producto, String> boardingGate = new TableColumn<Producto, String>("Tipo");
		boardingGate.setCellValueFactory(new PropertyValueFactory<Producto, String>("tipo"));

		table.setItems(data);
		table.getColumns().addAll(date, schedule, nameAirline, idAirline, destinationCity, boardingGate);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		return table;
	}

	private ObservableList<Producto> createData() {

		data = FXCollections.observableArrayList();

		data.addAll(s.obtenerLosProductosComoArray());
		return data;
	}

	private Node createPage(int pageIndex) {
		int fromIndex = pageIndex * rowsPerPage;
		int toIndex = Math.min(fromIndex + rowsPerPage, data.size());
		table.setItems(FXCollections.observableArrayList(data.subList(fromIndex, toIndex)));

		return table;
	}

	public void cambiarTiempo() {
		tiempoActual = tiempoActual.plusSeconds(1);
		clock.setText(DateTimeFormatter.ofPattern("hh:mm:ss a").format(tiempoActual));
	}

	public void initialize() {
		tiempoActual = LocalTime.now();
		cambiarTiempo();
		tat = new TiempoActualInventarioThread(this);
		tat.start();
		opcionesDeBusqueda.getItems().addAll("Fecha de llegada", "Horario", "Nombre de la marca", "Codigo del producto",
				"Nombre del producto", "Tipo");
		borderPane.setStyle("-fx-background-color: #acdfe8;");
		gridPane.setStyle("-fx-background-color: #d1e697;");
	}

}