package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;

import javafx.scene.image.Image;

public class Farmacia {

	private ClienteFarmacia raiz;
	private ClienteFarmacia primero;

	public static final String PATH = "data/data.csv";

	public final static String PATH_MARCAS = "data/marcas.txt";
	public final static String PATH_PRODUCTOS = "data/productos.txt";

	private int numeroDeProductos;
	private Producto primerProducto;
	private ListaDeMarcas primerMarca;
	private ListaDeProductos primerosProductos;

	public Farmacia(int number) {
		numeroDeProductos = number;
	}

	public Farmacia() {

	}

	public void agregarClienteAlArbol(ClienteFarmacia p) {
		agregarClienteAlArbol(p, raiz);
	}

	/**
	 * Este metodo permite agregar un cliente al arbol
	 * 
	 * @param cliente cliente nuevo
	 * @param actual  este cliente
	 */
	private void agregarClienteAlArbol(ClienteFarmacia cliente, ClienteFarmacia actual) {
		if (raiz == null) {
			raiz = cliente;
		} else {
			if (cliente.compareTo(actual) <= 0) {
				if (actual.getIzquierda() == null) {
					actual.setIzquierda(cliente);
				} else {
					agregarClienteAlArbol(cliente, actual.getIzquierda());
				}
			} else {
				if (actual.getDerecha() == null) {
					actual.setDerecha(cliente);
				} else {
					agregarClienteAlArbol(cliente, actual.getDerecha());
				}
			}

		}
	}

	/**
	 * Este metodo permite cargar el archivo donde está la informacion de los
	 * clientes y los agrega al arbol
	 * 
	 * @return un String con la ruta del archivo que contiene la informacion de los
	 *         clientes
	 * @throws IOException
	 */

	public String cargarArchivoYAgregarAlArbol() throws IOException {
		File file = new File(PATH);
		FileReader fileReader = new FileReader(file);
		BufferedReader br = new BufferedReader(fileReader);
		String line = br.readLine();
		line = br.readLine();
		int times = 0;
		while (line != null) {
			String[] parts = line.split(",");
			URL url = new URL(parts[6]);
			URLConnection conn = url.openConnection();
			InputStream in = conn.getInputStream();
			Image img = new Image(in);
			ClienteFarmacia nParticipant = new ClienteFarmacia(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3],
					parts[4], parts[5], img, parts[7]);
			agregarClienteAlArbol(nParticipant);
			line = br.readLine();
			times++;
		}
		fileReader.close();
		br.close();
		escogerClientesAleatorios(times);
		return PATH;
	}

	/**
	 * Este metodo permite buscar un cliente especial tomando como referencia su id
	 * 
	 * @param id el id del cliente que se esta buscando
	 * @return El cliente que se esta buscando
	 */
	public ClienteFarmacia buscarClientesEspeciales(int id) {
		ClienteFarmacia s = new ClienteFarmacia(id, "", "", "", "", "", null, "");
		return buscarClientesEspeciales(raiz, s);
	}

	/**
	 * Este metodo permite buscar clientes especiales
	 * 
	 * @param actual
	 * @param agregar
	 * @return un cliente que se esta buscando
	 */
	private ClienteFarmacia buscarClientesEspeciales(ClienteFarmacia actual, ClienteFarmacia agregar) {
		if (actual != null) {
			if (agregar.compareTo(actual) < 0) {
				if (actual.getIzquierda() != null) {
					return buscarClientesEspeciales(actual.getIzquierda(), agregar);
				} else {
					return buscarClientesEspeciales(actual.getDerecha(), agregar);
				}
			} else if (agregar.compareTo(actual) > 0) {
				if (actual.getDerecha() != null) {
					return buscarClientesEspeciales(actual.getDerecha(), agregar);
				} else {
					return buscarClientesEspeciales(actual.getIzquierda(), agregar);
				}
			} else {
				return actual;
			}
		}
		return actual;
	}

	/**
	 * Este metodo permite escoger clientes aleatorios de la farmacia
	 * 
	 * @param size
	 */
	public void escogerClientesAleatorios(int size) {
		int[] p = new int[10];
		for (int i = 0; i < 10; i++) {
			int n = (int) (Math.random() * size) + 1;
			if (i > 0) {
				if (noEscoger(n, p)) {
					ClienteFarmacia s = buscarClientesEspeciales(n);
					agregandoClientesEspeciales(s);
					p[i] = n;
				}
			} else {
				p[i] = n;
			}

		}
	}

	public boolean noEscoger(int n, int[] a) {
		boolean flag = true;
		for (int i = 0; i < a.length; i++) {
			if (n == a[i]) {
				flag = false;
			}
		}
		return flag;
	}

	/**
	 * Este metodo permite agregar los clientes especiales a la farmacia
	 * 
	 * @param newOne el nuevo cliente que se va a agregar
	 */
	public void agregandoClientesEspeciales(ClienteFarmacia newOne) {
		if (primero == null) {
			primero = newOne;
		} else {
			ClienteFarmacia current = primero;
			while (current.getSiguiente() != null) {
				current = current.getSiguiente();
			}
			current.setSiguiente(newOne);
			ClienteFarmacia temp = current;
			current = current.getSiguiente();
			current.setAnterior(temp);
		}
	}

	/**
	 * Este metodo permite buscar un cliente especial
	 * 
	 * @param n id del cliente que se está buscando
	 * @return el cliente especial que se está buscando
	 */
	public ClienteFarmacia buscarPorClienteEspecial(int n) {
		ClienteFarmacia current = primero;
		ClienteFarmacia returned = null;
		boolean stop = false;
		while (current != null && !stop) {
			if (current.getId() == n) {
				stop = true;
				returned = current;
			} else {
				current = current.getSiguiente();
			}
		}

		return returned;
	}

	// Getters and Setters

	/**
	 * Este metodo permite obtener la raiz del arbol de clientes
	 * 
	 * @return el cliente raiz del arbol
	 */
	public ClienteFarmacia getRaiz() {
		return raiz;
	}

	public void setRaiz(ClienteFarmacia raiz) {
		this.raiz = raiz;
	}

	/**
	 * Este metodo permite obtener el primer Cliente de la lista enlazada
	 * 
	 * @return el primer cliente de la lista enlazada
	 */
	public ClienteFarmacia getPrimero() {
		return primero;
	}

	public void setPrimero(ClienteFarmacia primero) {
		this.primero = primero;
	}

	/**
	 * Este metodo permite inserta un producto en la lista enlazada
	 * 
	 * @param fechaDeLlegada    fecha de llegada del producto
	 * @param horario           horario en que llegó el producto
	 * @param nombreDeLaMarca   nombre de la marca del producto
	 * @param codigoDelProducto codigo del producto
	 * @param nombreDelProducto nombre del producto
	 */
	public void insertarProducto(FechaDeLlegada fechaDeLlegada, Tiempo horario, String nombreDeLaMarca,
			String codigoDelProducto, String nombreDelProducto) {
		int cont = 0;
		Producto nuevoProducto = new Producto(fechaDeLlegada, horario, nombreDeLaMarca, codigoDelProducto,
				nombreDelProducto);
		if (primerProducto == null) {
			primerProducto = nuevoProducto;
		} else {
			Producto actual = primerProducto;
			while (actual.getSiguiente() != null && cont < numeroDeProductos) {
				actual = actual.getSiguiente();
				cont++;
			}
			actual.setSiguiente(nuevoProducto);
			actual.getSiguiente().setAnterior(actual);
		}
	}

	/**
	 * Este metodo permite agregar una marca a la lista de marcas de los productos
	 * 
	 * @param nombre nombre de la marca
	 * @param id     identificador de la marca
	 */
	public void agregarALaListaDeMarcas(String nombre, int id) {
		ListaDeMarcas b = new ListaDeMarcas(nombre, id);
		if (primerMarca == null) {
			primerMarca = b;
		} else {
			ListaDeMarcas actual = primerMarca;
			while (actual.getSiguiente() != null) {
				actual = actual.getSiguiente();
			}
			actual.setSiguiente(b);
			actual.getSiguiente().setAnterior(actual);
		}
	}

	/**
	 * Este metodo permite agregar a la lista de nombres de productos un nuevo
	 * nombre
	 * 
	 * @param nombre nuevo nombre del producto
	 * @param id     identificador del nombre del producto
	 */
	public void agregarALaListaDeNombreDeProductos(String nombre, int id) {
		ListaDeProductos b = new ListaDeProductos(nombre, id);
		if (primerosProductos == null) {
			primerosProductos = b;
		} else {
			ListaDeProductos actual = primerosProductos;
			while (actual.getSiguiente() != null) {
				actual = actual.getSiguiente();
			}
			actual.setSiguiente(b);
			actual.getSiguiente().setAnterior(actual);
		}
	}

	// BUSCAR EN LA LISTA ENLAZADA

	/**
	 * Este metodo permite buscar en la lista enlazada de marcas
	 * 
	 * @param n el nombre de la marca que se esta buscando
	 * @return una cadena con el nombre de la marca
	 */
	public String buscarEnLaListaDeMarcas(int n) {
		ListaDeMarcas primera = primerMarca;
		ListaDeMarcas actual = primerMarca;
		while (actual != null) {
			if (actual.getId() == n) {
				primera = actual;
			}
			actual = actual.getSiguiente();
		}
		return primera.getNombre();
	}

	/**
	 * Este metodo permite buscar en la lista de nombres de productos
	 * 
	 * @param n el nombre del producto que se está buscando
	 * @return una cadena con el nombre del producto buscado
	 */

	public String buscarEnLaListaDeNombresDeProductos(int n) {
		ListaDeProductos primer = primerosProductos;
		ListaDeProductos actual = primerosProductos;
		while (actual != null) {
			if (actual.getId() == n) {
				primer = actual;
			}
			actual = actual.getSiguiente();
		}
		return primer.getNombre();
	}

	/**
	 * Este metodo permite buscar un producto dada la marca del mismo
	 * 
	 * @param marcaDelProducto la marca del producto
	 * @return una cadena que representa la informacion del producto
	 */
	public String buscarProductoPorLaMarca(String marcaDelProducto) {
		Producto anterior = null;
		Producto actual = primerProducto;
		while (actual != null && anterior == null) {
			if (actual.getNombreDeLaMarca().equalsIgnoreCase(marcaDelProducto)) {
				anterior = actual;
			}
			actual = actual.getSiguiente();
		}
		return anterior.toString();
	}

	/**
	 * este metodo permite buscar un producto dado su codigo
	 * 
	 * @param condigoDelProducto el codigo del producto a buscar
	 * @return una cadena que representa la informacion del producto
	 */
	public String buscarProductoPorElCofigo(String condigoDelProducto) {
		Producto anterior = null;
		Producto actual = primerProducto;
		while (actual != null && anterior == null) {
			if (actual.getCodigoDelProducto().equalsIgnoreCase(condigoDelProducto)) {
				anterior = actual;
			}
			actual = actual.getSiguiente();
		}
		return anterior.toString();
	}

	/**
	 * este metodo permite buscar un producto dado el tipo del mismo
	 * 
	 * @param tipo el tipo de producto
	 * @return una cadena con la informacion del producto buscado
	 */
	public String buscarProductoPorElTipo(String tipo) {
		int tipoProducto = Integer.parseInt(tipo);
		Producto anterior = null;
		Producto actual = primerProducto;
		while (actual != null && anterior == null) {
			int actualTipo = Integer.parseInt(actual.getTipo());
			if (actualTipo == tipoProducto) {
				anterior = actual;
			}
			actual = actual.getSiguiente();
		}
		return anterior.toString();
	}

	/**
	 * este metodo permite buscar un producto dado su nombre
	 * 
	 * @param nombreProducto nombre del producto buscado
	 * @return una cadena con la informacion del producto
	 */
	public String buscarProductoPorElNombre(String nombreProducto) {
		Producto anterior = null;
		Producto actual = primerProducto;
		while (actual != null && anterior == null) {
			if (actual.getNombreDelProducto().equalsIgnoreCase(nombreProducto)) {
				anterior = actual;
			}
			actual = actual.getSiguiente();
		}
		return anterior.toString();
	}

	/**
	 * este metodo permite buscar un producto dada su fecha de llegada
	 * 
	 * @param fechaDeLlegada fecha de llegada del producto
	 * @return una cadena con la informacion del producto
	 */
	public String buscarElProductoPorLaFechaDeLlegada(String fechaDeLlegada) {
		Producto anterior = null;
		Producto actual = primerProducto;
		while (actual != null && anterior == null) {
			if (actual.getFechaDeLlegada().toString().equalsIgnoreCase(fechaDeLlegada)) {
				anterior = actual;
			}
			actual = actual.getSiguiente();
		}
		return anterior.toString();
	}

	/**
	 * Este metodo permite buscar un producto dado su horario
	 * 
	 * @param n el horario del producto
	 * @return el podructo buscado
	 */
	public String buscarProductoPorHorario(String n) {
		Producto match = null;
		Producto current = primerProducto;
		while (current != null && match == null) {
			if (current.getHorario().toString().equalsIgnoreCase(n)) {
				match = current;
			}
			current = current.getSiguiente();
		}
		return match.toString();
	}
	// -----------------------------------------------------------------
	// CONTAR EL TAMAÑO DE LA LISTA ENLAZADA
	// -----------------------------------------------------------------

	/**
	 * Este metodo permite dar el tamaño de la lista enlazada de los nombres de los
	 * productos
	 * 
	 * @return un entero que representa el tamaño de la lista
	 */
	public int tamanioDeLaListaDeLosNombresDeLosProductos() {
		int size = 0;
		ListaDeProductos actual = primerosProductos;
		while (actual != null) {
			size++;
			actual = actual.getSiguiente();
		}
		return size;

	}

	/**
	 * Este metodo permite oobtener el tamaño de la lista de marcas
	 * 
	 * @return el tamaño de la lista de marcas
	 */
	public int tamanioDeLaListaDeLasMarcas() {
		int size = 0;
		ListaDeMarcas actual = primerMarca;
		while (actual != null) {
			size++;
			actual = actual.getSiguiente();
		}
		return size;

	}

	/**
	 * este metodo permite obtener el tamaño de la lista de los productos
	 * 
	 * @return un entero que representa el tamaño de la lista de los productos
	 */
	public int tamanioDeLaListaDeLosProductos() {
		int size = 0;
		Producto current = primerProducto;
		while (current != null) {
			size++;
			current = current.getSiguiente();
		}
		return size;

	}

	// -----------------------------------------------------------------
	// METODO PARA OBTENER LOS PRODUCTOS COMO ARRAY
	// -----------------------------------------------------------------
	/**
	 * Este metodo permite obtener los productos de la farmacia en un arreglo
	 * 
	 * @return un arrelgo de objetos de tipo Producto
	 */

	public Producto[] obtenerLosProductosComoArray() {
		Producto[] losProductos;
		losProductos = new Producto[numeroDeProductos];
		int c = 0;
		Producto actual = primerProducto;
		while (actual != null) {
			losProductos[c] = actual;
			actual = actual.getSiguiente();
			c++;
		}
		return losProductos;
	}

	// -----------------------------------------------------------------
	// Metodo para generar los datos de los productos
	// -----------------------------------------------------------------
	/**
	 * Este metodo permite generar la informacion de los productos a partir de las
	 * rutas donde está su informacion
	 */

	public void generarInformacionDeLosProductosDeLaFarmacia() {
		SecureRandom r = new SecureRandom();
		cargarInformacion(PATH_MARCAS);
		cargarInformacion(PATH_PRODUCTOS);
		String nombreDeLaMarca = "";
		String nombreDelProducto = "";
		String id = "";
		for (int i = 0; i < getNumeroDeProductos(); i++) {

			nombreDeLaMarca = buscarEnLaListaDeMarcas(r.nextInt(32));
			nombreDelProducto = buscarEnLaListaDeNombresDeProductos(r.nextInt(67));
			String extensionDelCodigo = nombreDeLaMarca.substring(0, 2); // generar codigo a partir del nombre del
																			// producto
			id = extensionDelCodigo + (r.nextInt(100000));// agregandole un numero random
			insertarProducto(new FechaDeLlegada(), new Tiempo(), nombreDeLaMarca, id, nombreDelProducto);

		}
		ordenamientoDeInsercionPorLaFechaDeLlegada();
	}

	/**
	 * Este método permite cargar la informacion de los productos de la farmacia
	 * 
	 * @param path es la ruta donde se encuentra el listado de productos y marcas
	 */

	public void cargarInformacion(String path) {
		try {
			BufferedReader lector = new BufferedReader(new FileReader(path));
			String linea = lector.readLine();
			int cont = 0;
			int cont2 = 0;
			while (linea != null) {
				if (path.equals(PATH_MARCAS)) {
					cont++;
					agregarALaListaDeMarcas(linea, cont);
				} else if (path.equals(PATH_PRODUCTOS)) {
					cont2++;
					agregarALaListaDeNombreDeProductos(linea, cont2);
				}
				linea = lector.readLine();
			}
			lector.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Este metodo permite obtener la informacion del producto
	 * 
	 * @return una cadena con la información del producto
	 */
	public String productosEnToString() {
		String list = "";
		Producto current = primerProducto;
		while (current != null) {
			list += current.toString();
			current = current.getSiguiente();
		}
		return list;
	}

	// -----------------------------------------------------------------
	// ORDENAMIENTOS DE LAS LISTAS
	// -----------------------------------------------------------------
	/**
	 * Este metodo permite ordenar la lista de productos por el nombre
	 */

	public void ordenarPorElNombreDeLaMarca() {
		if (primerProducto != null) {
			Producto temp = primerProducto;
			int contador1 = 0;
			int size = tamanioDeLaListaDeLasMarcas();
			while (temp != null) {
				Producto actual = primerProducto;
				int contador2 = 0;
				while (actual.getSiguiente() != null && contador2 < size - contador1) {
					if (actual.getNombreDeLaMarca().compareTo(actual.getSiguiente().getNombreDeLaMarca()) >= 0) {
						if (primerProducto == actual)
							primerProducto = actual.getSiguiente();
						Producto siguiente = actual.getSiguiente().getSiguiente();
						Producto anterior = actual.getAnterior();
						if (siguiente != null)
							siguiente.setAnterior(actual);
						if (anterior != null)
							anterior.setSiguiente(actual.getSiguiente());
						actual.getSiguiente().setSiguiente(actual);
						actual.getSiguiente().setAnterior(anterior);
						actual.setAnterior(actual.getSiguiente());
						actual.setSiguiente(siguiente);
					} else {
						actual = actual.getSiguiente();
					}
					contador2++;
				}
				contador1++;
				temp = temp.getSiguiente();
			}
		}
	}

	// MÉTODO BURBUJA PARA ORDENAR POR EL NOMBRE DEL PRODUCTO
	/**
	 * Este metodo permite ordenar la lista de productos con el metodo de burbuja
	 * tomando como criterio su nombre
	 */
	public void ordenarBurbujaPorElNombreDelProducto() {
		if (primerProducto != null) {
			Producto temporal = primerProducto;
			int contador1 = 0;
			int tamanio = tamanioDeLaListaDeLasMarcas();
			while (temporal != null) {
				Producto actual = primerProducto;
				int contador2 = 0;
				while (actual.getSiguiente() != null && contador2 < tamanio - contador1) {
					if (actual.getNombreDelProducto().compareTo(actual.getSiguiente().getNombreDelProducto()) > 0) {
						if (primerProducto == actual)
							primerProducto = actual.getSiguiente();
						Producto siguiente = actual.getSiguiente().getSiguiente();
						Producto anterior = actual.getAnterior();
						if (siguiente != null)
							siguiente.setAnterior(actual);
						if (anterior != null)
							anterior.setSiguiente(actual.getSiguiente());
						actual.getSiguiente().setSiguiente(actual);
						actual.getSiguiente().setAnterior(anterior);
						actual.setAnterior(actual.getSiguiente());
						actual.setSiguiente(siguiente);
					} else {
						actual = actual.getSiguiente();
					}
					contador2++;
				}
				contador1++;
				temporal = temporal.getSiguiente();
			}
		}
	}

	// ORDENAMIENTO POR INSERCION TOMANDO COMO CRITERIO EL TIPO DEL PRODUCTO
	/**
	 * Este metodo permite ordenar la lista de productos con el metodo de insercion
	 * tomando como criterio el tipo
	 */
	public void ordenamientoDeInsercionPorElTipoDeProducto() {
		if (primerProducto.getSiguiente() != null) {
			Producto actual = primerProducto.getSiguiente();
			while (actual != null) {
				Producto temporal = actual;
				while (temporal.getAnterior() != null) {
					if (Integer.parseInt(temporal.getTipo()) < Integer.parseInt(temporal.getAnterior().getTipo())) {
						if (temporal.getAnterior() == primerProducto)
							primerProducto = temporal;
						Producto siguiente = temporal.getSiguiente();
						Producto anterior = temporal.getAnterior().getAnterior();
						if (siguiente != null)
							siguiente.setAnterior(temporal.getAnterior());
						if (anterior != null)
							anterior.setSiguiente(temporal);
						temporal.setSiguiente(temporal.getAnterior());
						temporal.getAnterior().setAnterior(temporal);
						temporal.getAnterior().setSiguiente(siguiente);
						temporal.setAnterior(anterior);
					} else {
						temporal = temporal.getAnterior();
					}
				}
				actual = actual.getSiguiente();
			}
		}

	}

	// ORDENAMIENTO POR INSERCION TOMANDO COMO CRITERIO LA FECHA DE LLEGADA DEL
	// PRODUCTO
	/**
	 * Este metodo permite ordenar la lista de productos con el metodo de insercion
	 * tomando como criterio la fecha de llegada
	 */
	public void ordenamientoDeInsercionPorLaFechaDeLlegada() {
		if (primerProducto.getSiguiente() != null) {
			Producto actual = primerProducto.getSiguiente();
			while (actual != null) {
				Producto temporal = actual;
				while (temporal.getAnterior() != null) {
					if (temporal.getFechaDeLlegada().compareTo(temporal.getAnterior().getFechaDeLlegada()) < 0) {
						if (temporal.getAnterior() == primerProducto)
							primerProducto = temporal;
						Producto siguiente = temporal.getSiguiente();
						Producto anterior = temporal.getAnterior().getAnterior();
						if (siguiente != null)
							siguiente.setAnterior(temporal.getAnterior());
						if (anterior != null)
							anterior.setSiguiente(temporal);
						temporal.setSiguiente(temporal.getAnterior());
						temporal.getAnterior().setAnterior(temporal);
						temporal.getAnterior().setSiguiente(siguiente);
						temporal.setAnterior(anterior);
					} else {
						temporal = temporal.getAnterior();
					}
				}
				actual = actual.getSiguiente();
			}
		}

	}

	// ORDENAMIENTO INSERCION TOMANDO COMO CRITERIO EL HORARIO DE LA LLEGADA DEL
	// PRODUCTO
	/**
	 * Este metodo permite ordenar la lista de productos con el metodo de insercion
	 * tomando como criterio la hora de llegada
	 */
	public void ordenamientoInsercionPorHoraDeLlegada() {
		if (primerProducto.getSiguiente() != null) {
			TiempoComparator tiempo = new TiempoComparator();
			Producto actual = primerProducto.getSiguiente();
			while (actual != null) {
				Producto temporal = actual;
				while (temporal.getAnterior() != null) {
					if (tiempo.compare(temporal, temporal.getAnterior()) < 0) {
						if (temporal.getAnterior() == primerProducto)
							primerProducto = temporal;
						Producto siguiente = temporal.getSiguiente();
						Producto anterior = temporal.getAnterior().getAnterior();
						if (siguiente != null)
							siguiente.setAnterior(temporal.getAnterior());
						if (anterior != null)
							anterior.setSiguiente(temporal);
						temporal.setSiguiente(temporal.getAnterior());
						temporal.getAnterior().setAnterior(temporal);
						temporal.getAnterior().setSiguiente(siguiente);
						temporal.setAnterior(anterior);
					} else {
						temporal = temporal.getAnterior();
					}
				}
				actual = actual.getSiguiente();
			}
		}

	}

	// ORDENAMIENTO DE SELECCION TOMANDO COMO CRITERIO EL CODIGO DEL PRODUCTO
	/**
	 * Este metodo permite ordenar la lista de productos con el metodo de seleccion
	 * tomando como criterio el codigo
	 */
	public void ordenamientoDeSeleccionPorElCodigo() {
		Producto actual = primerProducto;
		while (actual != null) {
			Producto temporal = actual.getSiguiente();
			Producto min = actual;
			while (temporal != null) {
				if (temporal.getCodigoDelProducto().compareTo(min.getCodigoDelProducto()) <= 0) {
					min = temporal;
				}
				temporal = temporal.getSiguiente();
			}
			boolean primero = false;
			if (min != actual) {
				Producto siguiente1 = actual.getSiguiente();
				Producto anterior1 = actual.getAnterior();

				Producto siguiente2 = min.getSiguiente();
				Producto anterior2 = min.getAnterior();

				if (min == actual.getSiguiente()) {
					if (anterior1 != null)
						anterior1.setSiguiente(min);
					else {
						primerProducto = min;
						primero = true;
					}
					actual.setSiguiente(siguiente2);
					actual.setAnterior(min);
					if (siguiente2 != null)
						siguiente2.setAnterior(actual);
					min.setSiguiente(actual);
					min.setAnterior(anterior1);
				} else {
					if (siguiente1 != null)
						siguiente1.setAnterior(min);
					if (anterior1 != null)
						anterior1.setSiguiente(min);
					else {
						primerProducto = min;
						primero = true;
					}

					min.setSiguiente(siguiente1);
					min.setAnterior(anterior1);

					if (siguiente2 != null)
						siguiente2.setAnterior(actual);
					if (anterior2 != null)
						anterior2.setSiguiente(actual);

					actual.setSiguiente(siguiente2);
					actual.setAnterior(anterior2);
				}
				actual = min;
			}
			if (primero) {
				actual = primerProducto.getSiguiente();
			} else {
				actual = actual.getSiguiente();
			}
		}
	}

	/**
	 * este metodo permite obtener el numero de productos de la farmacia
	 * 
	 * @return
	 */
	public int getNumeroDeProductos() {
		return numeroDeProductos;
	}

	public void setNumeroDeProductos(int numeroDeProductos) {
		this.numeroDeProductos = numeroDeProductos;
	}

	/**
	 * este metodo permite obtener el primer producto de la farmacia
	 * 
	 * @return
	 */
	public Producto getPrimerProducto() {
		return primerProducto;
	}

	public void setPrimerProducto(Producto primerProducto) {
		this.primerProducto = primerProducto;
	}

	/**
	 * este metodo permite obtener el primer elemento de la lista de marcas
	 * 
	 * @return
	 */
	public ListaDeMarcas getPrimerasMarcas() {
		return primerMarca;
	}

	public void setPrimerasMarcas(ListaDeMarcas primerasMarcas) {
		this.primerMarca = primerasMarcas;
	}

}
