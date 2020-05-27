package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import excepciones.CargoSupermercadoException;
import excepciones.ClientesSupermercadoException;
import excepciones.ProductoSupermercadoException;

public class Supermercado implements Serializable {

	static final long serialVersionUID = 42L;

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	private String nombre;
	private ClienteSupermercado clienteRaiz;
	private CargoSupermercado administrador;
	// private Farmacia farmacia;
	private ProductoSupermercado primerProducto;
	private ArrayList<PromocionSupermercado> promociones;

	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	public Supermercado(String nombre) {
		this.nombre = nombre;
		promociones = new ArrayList<PromocionSupermercado>();
		crearPromociones();
	}

	// -----------------------------------------------------------------
	// Metodos
	// -----------------------------------------------------------------

	// -----------------------------------------------------------------
	// Metodos clase cargo (arbol n-rio)
	// -----------------------------------------------------------------

	/**
	 * Agrega un nuevo cargo al organigrama de la empresa. Si el organigrama esta
	 * vacio se ignora el valor del parametro nCargoJefe.
	 * 
	 * @param nCargo     El nombre del nuevo cargo - nCargo!=null && nCargo!=""
	 * @param pago       El salario asociado con el nuevo cargo - pago>0
	 * @param nCargoJefe El nombre del cargo del que depende el nuevo cargo
	 * @throws OrganigramaException Si el cargo del que se depende no existe
	 * @throws OrganigramaException Si el nombre del nuevo cargo ya se encuentra
	 *                              asociado a otro cargo existente
	 */
	public void crearCargo(String nCargo, String nCargoJefe) throws CargoSupermercadoException {
		if (administrador == null) {
			administrador = new CargoSupermercado(nCargo, null);
		} else {
			CargoSupermercado padre = buscarCargo(nCargoJefe);
			if (padre == null)
				throw new CargoSupermercadoException("Cargo invï¿½lido");

			CargoSupermercado nodo = buscarCargo(nCargo);
			if (nodo != null)
				throw new CargoSupermercadoException("Cargo repetido");

			padre.agregarCargo(nCargo);
		}
	}

	/**
	 * Elimina el cargo con el nombre dado
	 * 
	 * @param nCargo El nombre del cargo a ser eliminado
	 * @throws CargoSupermercadoException Si no hay cargos en la empresa
	 * @throws CargoSupermercadoException Si el cargo a ser eliminado no existe
	 * @throws CargoSupermercadoException Si el cargo a ser eliminado no se
	 *                                    encuentra vacante
	 */
	public void eliminarCargo(String nCargo) throws CargoSupermercadoException {
		if (administrador == null)
			throw new CargoSupermercadoException("El cargo que desea eliminar es invalido");

		if (administrador.darNombreCargo().equalsIgnoreCase(nCargo)) {
			// Se quiere eliminar el cargo de la raiz
			if (administrador.esHoja() && administrador.estaVacante())
				administrador = null;
			else
				throw new CargoSupermercadoException("Imposible eliminar");
		} else {
			CargoSupermercado padre = administrador.buscarJefe(nCargo);
			if (padre == null)
				throw new CargoSupermercadoException("El cargo que desea eliminar es invalido");

			padre.eliminarCargo(nCargo);
		}
	}

	/**
	 * Contrata a la persona con la informaciï¿½n especificada
	 * 
	 * @param nombre El nombre de la persona - nombre!=null && nombre!=""
	 * @param codigo El cï¿½digo de identidad de la persona - ingreso!=null
	 * @param nCargo El nombre del cargo que va a ocupar la persona - nCargo!=null
	 * @throws OrganigramaException Si el cargo que va a ocupar la persona no existe
	 * @throws OrganigramaException Si el cï¿½digo ya se encuentra asociado con otra
	 *                              persona
	 */
	public void contratarPersona(String nombre, String codigo, String turno, String nCargo)
			throws CargoSupermercadoException {
		if (administrador == null)
			throw new CargoSupermercadoException("Cargo inexistente");

		CargoSupermercado cargo = buscarCargo(nCargo);
		if (cargo == null)
			throw new CargoSupermercadoException("Cargo inexistente...");

		EmpleadoSupermercado temp = buscarEmpleado(codigo);
		if (temp != null)
			throw new CargoSupermercadoException("Identidad repetida, recuerde que el código del empleado es único");

		cargo.contratar(nombre, codigo, turno);
	}

	/**
	 * Despide el empleado con el cï¿½digo dado
	 * 
	 * @param idPersona El cï¿½digo de la persona a ser despedida
	 * @throws OrganigramaException Si empleado no existe
	 */
	public void despedirEmpleado(String idPersona) throws CargoSupermercadoException {
		if (administrador == null)
			throw new CargoSupermercadoException("Empleado inexistente...");

		CargoSupermercado cargo = buscarCargoEmpleado(idPersona);
		if (cargo == null)
			throw new CargoSupermercadoException("Empleado no encontrado...");

		cargo.despedir();
	}

	/**
	 * Busca el cargo del empleado con el cï¿½digo dado
	 * 
	 * @param idPersona El cï¿½digo del empleado del que se desea el cargo -
	 *                  idPersona!=null
	 * @return El cargo de la persona con el cï¿½digo dado. Si el empleado no existe
	 *         se retorna null
	 */
	public CargoSupermercado buscarCargoEmpleado(String idPersona) {
		return administrador == null ? null : administrador.buscarCargoEmpleado(idPersona);
	}

	/**
	 * Busca y retorna el empleado con el cï¿½digo dado
	 * 
	 * @param idPersona El cï¿½digo del empleado que se busca - idPersona != null
	 * @return el empleado buscado o null si no se encuentra
	 */
	public EmpleadoSupermercado buscarEmpleado(String idPersona) {
		return administrador == null ? null : administrador.buscarEmpleado(idPersona);
	}

	/**
	 * Retorna el nï¿½mero de integrantes del organigrama
	 * 
	 * @return El nï¿½mero de cargos de la empresa
	 */
	public int contarCargos() {
		return administrador == null ? 0 : administrador.darPeso();
	}

	/**
	 * Busca el cargo con el nombre dado
	 * 
	 * @param nCargo Nombre del Cargo
	 * @return El cargo con el nombre dado. Si el cargo no se encontrï¿½ se
	 *         retornï¿½ null
	 */
	public CargoSupermercado buscarCargo(String nCargo) {
		return administrador == null ? null : administrador.buscarCargo(nCargo);
	}

	/**
	 * Retorna la cabeza de la empresa
	 * 
	 * @return la cabeza de la empresa
	 */
	public CargoSupermercado darCabeza() {
		return administrador;
	}

	/**
	 * Retorna una lista con los nombres de todos los cargos de la empresa
	 * 
	 * @return Lista con todos los cargos de la empresa
	 */
	public Collection darListaCargos() {
		Collection lista = new ArrayList();
		if (administrador != null) {
			administrador.darListaCargos(lista);
		}
		return lista;
	}

	/**
	 * Retorna una lista con los nombres de todos los cargos que se encuentran <br>
	 * disponibles en la empresa
	 * 
	 * @return Lista con todos los cargos de la empresa que se encuentran vacantes
	 */
	public Collection darListaCargosDisponibles() {
		Collection lista = new ArrayList();
		if (administrador != null) {
			administrador.darListaCargosDisponibles(lista);
		}
		return lista;
	}

	// -----------------------------------------------------------------
	// Invariante
	// -----------------------------------------------------------------

	/**
	 * Verifica el invariante de la clase <br>
	 * los nombres de los cargos son ï¿½nicos <br>
	 * los cï¿½digos de identidad de los empleados son ï¿½nicos
	 */
	private void verificarInvariante() {
		Collection cargos = darListaCargos();
		Iterator it = cargos.iterator();

		while (it.hasNext()) {
			String nomCargo = (String) it.next();
			assert (contarOcurrenciasCargo(nomCargo) == 1) : "El nombre del cargo deberï¿½a ser ï¿½nico";

			CargoSupermercado c = buscarCargo(nomCargo);
			EmpleadoSupermercado e = c.darEmpleado();

			if (e != null) {
				assert (contarOcurrenciasCodigoEmpleado(
						e.getCodigo()) == 1) : "El cï¿½digo del empleado deberï¿½a ser ï¿½nico";
			}
		}
	}

	/**
	 * Cuenta las ocurrencias del cargo con el nombre dado en el organigrama
	 * 
	 * @param nomCargo El nombre del cargo del que se van a contar las ocurrencias
	 * @return El nï¿½mero de ocurrencias del cargo
	 */
	private int contarOcurrenciasCargo(String nomCargo) {
		int ocurrencias = 0;

		Collection cargos = darListaCargos();
		Iterator it = cargos.iterator();

		while (it.hasNext()) {
			String nCargo = (String) it.next();

			if (nCargo.equalsIgnoreCase(nomCargo)) {
				ocurrencias++;
			}
		}

		return ocurrencias;
	}

	/**
	 * Cuenta las ocurrencias del cï¿½digo dado en el organigrama
	 * 
	 * @param codigo El cï¿½digo del que se van a contar las ocurrencias
	 * @return El nï¿½mero de ocurrencias del cï¿½digo
	 */
	private int contarOcurrenciasCodigoEmpleado(String codigo) {
		int ocurrencias = 0;

		Collection cargos = darListaCargos();
		Iterator it = cargos.iterator();

		while (it.hasNext()) {
			String nCargo = (String) it.next();
			CargoSupermercado c = buscarCargo(nCargo);
			EmpleadoSupermercado e = c.darEmpleado();

			if (e != null && e.getCodigo().equalsIgnoreCase(codigo)) {
				ocurrencias++;
			}
		}

		return ocurrencias;
	}

	// -----------------------------------------------------------------
	// Metodos clientes farmacia (ï¿½rbol binario)
	// -----------------------------------------------------------------

	/**
	 * Agrega un contacto al directorio <br>
	 * <b>post: </b>El contacto ha sido agregado al directorio.
	 * 
	 * @param nom   nombre del contacto - nom != null
	 * @param tel   telefono del contacto
	 * @param dir   direccion del contacto
	 * @param email direccion electronica del contacto
	 * @throws ClientesSupermercadoException
	 * @throws ContactoRepetidoException     cuando ya existe un contacto con ese
	 *                                       nombre
	 */
	public void agregarClienteSupermercado(String nombre, String apellido, String codigo, String correo)
			throws ClientesSupermercadoException {
		ClienteSupermercado c = new ClienteSupermercado(nombre, apellido, codigo, correo);
		if (clienteRaiz == null)
			clienteRaiz = c;
		else {
			clienteRaiz.insertar(c);
		}
	}

	/**
	 * Elimina del directorio el contacto con el nombre indicado <br>
	 * <b>post: </b>El contacto ha sido eliminado del directorio <br>
	 * 
	 * @param nombre nombre del contacto a eliminar - existe en el directorio un
	 *               contacto con dicho nombre
	 */
	public void eliminarClienteSupermercado(String codigo) {
		clienteRaiz = clienteRaiz.eliminar(codigo);
	}

	/**
	 * Busca y retorna el contacto del nombre indicado. Si no lo encuentra retorna
	 * null.
	 * 
	 * @param nombre nombre del contacto a buscar - nombre != null
	 * @return contacto correspondiente al nombre, si no existe retorna null
	 */
	public ClienteSupermercado buscarCliente(String codigo) {
		return clienteRaiz == null ? null : clienteRaiz.buscar(codigo);
	}

	/**
	 * Retorna una lista ordenada con los nombres de los contactos
	 * 
	 * @return lista de contactos ordenada por orden alfabï¿½tico. Si la lista
	 *         estï¿½ vacï¿½a retorna null
	 */
	public Collection darListaClientes() {
		if (clienteRaiz == null)
			return null;
		else {
			Collection resp = new ArrayList();
			clienteRaiz.inorden(resp);
			return resp;
		}
	}

	/**
	 * Retorna la altura del ï¿½rbol de contactos
	 * 
	 * @return La altura del ï¿½rbol de contactos
	 */
	private int darAltura() {
		return clienteRaiz == null ? 0 : clienteRaiz.darAltura();
	}

	/**
	 * Retorna el menor contacto del directorio, teniendo en cuenta el orden
	 * alfabï¿½tico de los nombres
	 * 
	 * @return El contacto con menor nombre del directorio o null si el directorio
	 *         es vacï¿½o
	 */
	private ClienteSupermercado darMenor() {
		return clienteRaiz == null ? null : clienteRaiz.darMenor();
	}

	/**
	 * Retorna el mayor contacto del directorio, teniendo en cuenta el orden
	 * alfabï¿½tico de los nombres
	 * 
	 * @return El contacto con mayor nombre del directorio o null si el directorio
	 *         es vacï¿½o
	 */
	private ClienteSupermercado darMayor() {
		return clienteRaiz == null ? null : clienteRaiz.darMayor();
	}

	/**
	 * Retorna el nï¿½mero de hojas que tiene el ï¿½rbol de contactos
	 * 
	 * @return Nï¿½mero de hojas que tiene el ï¿½rbol de contactos
	 */
	private int contarHojas() {
		return clienteRaiz == null ? 0 : clienteRaiz.contarHojas();
	}

	/**
	 * Cuenta el nï¿½mero de contactos en el directorio que tienen el nombre dado
	 * como parï¿½metro <br>
	 * <b>pre: </b> El ï¿½rbol de contactos no es vacï¿½o
	 * 
	 * @param nombre nombre del contacto del cual se estï¿½ contando el nï¿½mero de
	 *               ocurrencias
	 */
	private int contarOcurrencias(String nombre) {
		return clienteRaiz.contarOcurrencias(nombre);
	}

	/**
	 * Verifica que el ï¿½rbol binario estï¿½ ordenado
	 */
	private boolean esOrdenado() {
		return clienteRaiz == null ? true : clienteRaiz.esOrdenado();
	}

	/**
	 * Retorna el nï¿½mero de contactos que estï¿½n en el directorio
	 * 
	 * @return nï¿½mero de contactos presentes en el ï¿½rbol
	 */
	private int darPeso() {
		return clienteRaiz == null ? 0 : clienteRaiz.darPeso();
	}

	// -----------------------------------------------------------------
	// Metodos productos supermercado (lista doblemente enlazada)
	// -----------------------------------------------------------------

	/**
	 * Retorna una lista con todos los vuelos que hay hacia esta ciudad
	 * 
	 * @return Se retornï¿½ la lista de vuelos
	 */
	public ArrayList<ProductoSupermercado> darProductosSupermercado() {
		ArrayList<ProductoSupermercado> productos = new ArrayList<ProductoSupermercado>();
		for (ProductoSupermercado p = primerProducto; p != null; p = p.getSiguiente())
			productos.add(p);
		return productos;
	}

	public ProductoSupermercado[] darArrayProductosSupermercado() {
		ProductoSupermercado[] losProductos;
		losProductos = new ProductoSupermercado[cantidadProductos()];
		int c = 0;
		ProductoSupermercado actual = primerProducto;
		while (actual != null) {
			losProductos[c] = actual;
			actual = actual.getSiguiente();
			c++;
		}
		return losProductos;
	}

	public int cantidadProductos() {
		int cantidad = 0;
		ProductoSupermercado actual = primerProducto;
		while (actual != null) {
			cantidad++;
			actual = actual.getSiguiente();
		}
		return cantidad;

	}

	/**
	 * Agrega un nuevo vuelo a la ciudad dejando la lista ordenada ascendentemente
	 * por cï¿½digo. <br>
	 * <b>post: </b> Se agregï¿½ un vuelo a la ciudad. <br>
	 * 
	 * @param codigo  El cï¿½digo del vuelo - cï¿½digo >= 0 && no existe otro vuelo
	 *                con ese cï¿½digo
	 * @param fecha   La fecha en la que se realizarï¿½ el vuelo - fecha != null y
	 *                fecha tiene el formato yyyy-MM-dd
	 * @param horas   La hora de despegue - 0 <= hora < 24
	 * @param minutos Los minutos de la hora de despegue 0 <= hora < 60
	 * @throws AerolineaExcepcion
	 * @throws ParseException     Se lanza esta excepciï¿½n si la fecha y hora de
	 *                            despegue no es vï¿½lida
	 */
	public void agregarProductoSupermercado(String codigo, String nombre, double precio, String ubicacion)
			throws ParseException, ProductoSupermercadoException {
		if (buscarProductoSupermercado(codigo) != null) {
			throw new ProductoSupermercadoException(
					"El cï¿½digo ingresado ya existe. Cada producto tiene un cï¿½digo ï¿½nico");
		}
		ProductoSupermercado nuevo = new ProductoSupermercado(codigo, nombre, precio, ubicacion);
		if (primerProducto == null)
			primerProducto = nuevo;
		// Si el primer producto es mayor o igual
		else if (primerProducto.getCodigo().compareTo(codigo) >= 0) {
			primerProducto.insertarAntes(nuevo);
			primerProducto = nuevo;
		} else {
			// el temp0 se encarga de asignar
			ProductoSupermercado temp0 = null;
			// el temp1 se encarga de recorrer la lista
			ProductoSupermercado temp1 = primerProducto;
			while (temp1 != null && temp1.getCodigo().compareTo(codigo) < 0) {
				temp0 = temp1;
				temp1 = temp1.getSiguiente();
			}
			if (temp0 != null) {
				temp0.insertarDespues(nuevo);
			}
		}
	}

	/**
	 * Este mï¿½todo localiza y retorna un vuelo, dado su cï¿½digo
	 * 
	 * @param codigo El cï¿½digo que se estï¿½ buscando
	 * @return Se retornï¿½ el vuelo con el cï¿½digo pedido o null si no lo
	 *         encuentra
	 */
	public ProductoSupermercado buscarProductoSupermercado(String codigo) {
		for (ProductoSupermercado p = primerProducto; p != null; p = p.getSiguiente()) {
			if (p.getCodigo().equals(codigo))
				return p;
		}
		return null;
	}

	public void cargarProductos(String ruta) {
		try {
			FileReader archivo = new FileReader(ruta);
			BufferedReader reader = new BufferedReader(archivo);
			String mensaje = reader.readLine();

			while (mensaje != null) {
				String[] datos = mensaje.split(",");
				String nombre = datos[0];
				String codigo = datos[1];
				double precio = Double.parseDouble(datos[2]);
				String ubicacion = datos[3];
				try {
					agregarProductoSupermercado(codigo, nombre, precio, ubicacion);
				} catch (ParseException | ProductoSupermercadoException e) {
					e.printStackTrace();
				}
				mensaje = reader.readLine();
			}
			reader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void eliminarProducto(String codigo) throws ProductoSupermercadoException {
		if (primerProducto != null) {
			ProductoSupermercado aux = primerProducto;
			ProductoSupermercado ant = null;
			while (aux != null) {
				if (aux.getCodigo().equals(codigo)) {
					if (ant == null) {
						primerProducto = primerProducto.getSiguiente();
						aux.setSiguiente(null);
						aux = primerProducto;

					} else {
						ant.setSiguiente(aux.getSiguiente());
						aux.setSiguiente(null);
						aux = ant.getSiguiente();
					}
				} else {
					ant = aux;
					aux = aux.getSiguiente();
				}
			}
		}
	}

	public void guardarProductos(String ruta) throws IOException {
		String msg = "";
		FileWriter archivo = new FileWriter(ruta);
		BufferedWriter escritor = new BufferedWriter(archivo);
		ProductoSupermercado actual = primerProducto;
		while (actual != null) {
			msg = actual.toString();

			escritor.write(msg);
			escritor.newLine();

			escritor.flush();
			actual = actual.getSiguiente();
		}
		escritor.close();
	}

	// -----------------------------------------------------------------
	// Getter y setter
	// -----------------------------------------------------------------

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ClienteSupermercado getClienteRaiz() {
		return clienteRaiz;
	}

	public void setClienteRaiz(ClienteSupermercado clienteRaiz) {
		this.clienteRaiz = clienteRaiz;
	}

	public ProductoSupermercado getPrimerProducto() {
		return primerProducto;
	}

	public void setPrimerProducto(ProductoSupermercado primerProducto) {
		this.primerProducto = primerProducto;
	}

	public void agregarPromociones(PromocionSupermercado e) {
		promociones.add(e);
	}

	public void crearPromociones() {
		PromocionSupermercado promocion1 = new PromocionSupermercado("Descuento en leche de todo tipo", "000");
		PromocionSupermercado promocion2 = new PromocionSupermercado("Descuento en carnes y verduras", "001");
		PromocionSupermercado promocion3 = new PromocionSupermercado("Descuento en frutas", "002");
		PromocionSupermercado promocion4 = new PromocionSupermercado("Descuento del 10% con pago en efectivo", "003");
		PromocionSupermercado promocion5 = new PromocionSupermercado(
				"Descuento del 20% con pago tarjeta del supermercado", "004");
		PromocionSupermercado promocion6 = new PromocionSupermercado("Descuento en atunes y sardinas", "005");

		agregarPromociones(promocion1);
		agregarPromociones(promocion2);
		agregarPromociones(promocion3);
		agregarPromociones(promocion4);
		agregarPromociones(promocion5);
		agregarPromociones(promocion6);

	}

	public PromocionSupermercado[] metodoBurbuja() {
		PromocionSupermercado[] promocion = new PromocionSupermercado[promociones.size()];
		for (int i = 0; i < promociones.size(); i++) {
			promocion[i] = promociones.get(i);
		}

		int i;
		boolean flag = true;
		PromocionSupermercado temp;

		while (flag) {
			flag = false;
			for (i = 0; i < promocion.length - 1; i++) {
				if (promocion[i].getCodigo().compareTo(promocion[i + 1].getCodigo()) < 0) {
					temp = promocion[i];
					promocion[i] = promocion[i + 1];
					promocion[i + 1] = temp;
					flag = true;
				}
			}
		}
		return promocion;
	}

	public PromocionSupermercado busquedaBinariaCodigo(PromocionSupermercado[] arreglo, String codigo) {
		int inicio = 0;
		int fin = arreglo.length - 1;
		int pos;
		while (inicio <= fin) {
			pos = (inicio + fin) / 2;
			if (arreglo[pos].getCodigo().equals(codigo))
				return arreglo[pos];
			else if (arreglo[pos].getCodigo().compareTo(codigo) < 0) {
				inicio = pos + 1;
			} else {
				fin = pos - 1;
			}
		}
		return null;
	}

	public PromocionSupermercado busquedaBinariaPromocion(PromocionSupermercado[] arreglo, String promocion) {
		int inicio = 0;
		int fin = arreglo.length - 1;
		int pos;
		while (inicio <= fin) {
			pos = (inicio + fin) / 2;
			if (arreglo[pos].getPromocion().equals(promocion))
				return arreglo[pos];
			else if (arreglo[pos].getPromocion().compareTo(promocion) < 0) {
				inicio = pos + 1;
			} else {
				fin = pos - 1;
			}
		}
		return null;
	}
}
