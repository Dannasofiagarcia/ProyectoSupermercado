package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Farmacia;
import model.Producto;

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

	@FXML
	private Label clock;

	private Stage stage;

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	public void generarInventario(ActionEvent event) {

	}

	@FXML
	public void buscarProducto(ActionEvent event) {

	}

	@FXML
	public void ordenarPorMarca(ActionEvent event) {

	}

	@FXML
	public void ordenarPorNombreDelProducto(ActionEvent event) {

	}

	@FXML
	public void ordenarPorFechaDeLlegada(ActionEvent event) {

	}

	@FXML
	public void ordenarPorCodigo(ActionEvent event) {

	}

	@FXML
	public void ordenarPorTipo(ActionEvent event) {

	}

	@FXML
	public void ordenarPorTiempoDeLlegada(ActionEvent event) {

	}

	private Node createPage(int pageIndex) {
		int fromIndex = pageIndex * rowsPerPage;
		int toIndex = Math.min(fromIndex + rowsPerPage, data.size());
		table.setItems(FXCollections.observableArrayList(data.subList(fromIndex, toIndex)));

		return table;
	}

	public void initialize() {

	}

}