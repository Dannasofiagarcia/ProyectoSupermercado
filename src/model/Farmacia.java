package model;

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
	 * @param n id del cliente que se esta buscando
	 * @return el cliente especial que se esta buscando
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
	 * @param horario           horario en que llega el producto
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

<<<<<<< HEAD

	/** metodo permite obtener el numero de productos de la farmacia
=======
=======
>>>>>>> 378bf6232147068a01892b3d89d03f21d5a95279
	/**
	 * Metodo permite obtener el numero de productos de la farmacia
	 * 
	 * @return
	 */
	public int getNumeroDeProductos() {
		return numeroDeProductos;
	}

	/**
	 * Metodo permite cambiar el numero de productos de la farmacia
	 * 
	 * @param numeroDeProductos el nuevo numero de productos
	 */
	public void setNumeroDeProductos(int numeroDeProductos) {
		this.numeroDeProductos = numeroDeProductos;
	}

	/**
	 * Este metodo permite obtener el primer producto de la farmacia
	 * 
	 * @return
	 */
	public Producto getPrimerProducto() {
		return primerProducto;
	}

	/**
	 * Este metodo permite obtener el primer producto de la farmacia
	 * 
	 * param primerProducto el nuevo primer producto
	 */
	public void setPrimerProducto(Producto primerProducto) {
		this.primerProducto = primerProducto;
	}

	/**
	 * Este metodo permite obtener el primer elemento de la lista de marcas
	 * 
	 * @return ListaDeMarcas la primera marca
	 */
	public ListaDeMarcas getPrimerasMarcas() {
		return primerMarca;
	}

	/**
	 * Este metodo permite modificar las primeras marcas
	 * 
	 * @param primerasMarcas la nueva primera marca
	 */

	public void setPrimerasMarcas(ListaDeMarcas primerasMarcas) {
		this.primerMarca = primerasMarcas;
	}

}
