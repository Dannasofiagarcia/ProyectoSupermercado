package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

//import excepciones.CargoSupermercadoException;
//import excepciones.ClientesSupermercadoException;
//import excepciones.ProductoSupermercadoException;

public class Supermercado implements Serializable {

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
	// Metodos clase cargo (árbol n-rio)
	// -----------------------------------------------------------------

	/**
	 * Retorna la cabeza de la empresa
	 * 
	 * @return la cabeza de la empresa
	 */
	public CargoSupermercado darCabeza() {
		return administrador;
	}

	// -----------------------------------------------------------------
	// Metodos clientes farmacia (árbol binario)
	// -----------------------------------------------------------------

	/**
	 * Agrega un contacto al directorio <br>
	 * <b>post: </b>El contacto ha sido agregado al directorio.
	 * 
	 * @param nom   nombre del contacto - nom != null
	 * @param tel   teléfono del contacto
	 * @param dir   dirección del contacto
	 * @param email dirección electrónica del contacto
	 * @throws ContactoRepetidoException cuando ya existe un contacto con ese nombre
	 */
	public void agregarClienteSupermercado(String nombre, String apellido, String codigo, String correo) {
		ClienteSupermercado c = new ClienteSupermercado(nombre, apellido, codigo, correo);
		if (clienteRaiz == null)
			clienteRaiz = c;
		else {
			// falta hacer el método
			// clienteRaiz.insertar(c);
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
	 * @return lista de contactos ordenada por orden alfabético. Si la lista está
	 *         vacía retorna null
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
	 * Retorna la altura del árbol de contactos
	 * 
	 * @return La altura del árbol de contactos
	 */
	private int darAltura() {
		return clienteRaiz == null ? 0 : clienteRaiz.darAltura();
	}

	/**
	 * Retorna el menor contacto del directorio, teniendo en cuenta el orden
	 * alfabético de los nombres
	 * 
	 * @return El contacto con menor nombre del directorio o null si el directorio
	 *         es vacío
	 */
	private ClienteSupermercado darMenor() {
		return clienteRaiz == null ? null : clienteRaiz.darMenor();
	}

	/**
	 * Retorna el mayor contacto del directorio, teniendo en cuenta el orden
	 * alfabético de los nombres
	 * 
	 * @return El contacto con mayor nombre del directorio o null si el directorio
	 *         es vacío
	 */
	private ClienteSupermercado darMayor() {
		return clienteRaiz == null ? null : clienteRaiz.darMayor();
	}

	/**
	 * Retorna el número de hojas que tiene el árbol de contactos
	 * 
	 * @return Número de hojas que tiene el árbol de contactos
	 */
	private int contarHojas() {
		return clienteRaiz == null ? 0 : clienteRaiz.contarHojas();
	}

	/**
	 * Verifica que el árbol binario esté ordenado
	 */
	private boolean esOrdenado() {
		return clienteRaiz == null ? true : clienteRaiz.esOrdenado();
	}

	/**
	 * Retorna el número de contactos que están en el directorio
	 * 
	 * @return número de contactos presentes en el árbol
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
	 * @return Se retornó la lista de vuelos
	 */

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
