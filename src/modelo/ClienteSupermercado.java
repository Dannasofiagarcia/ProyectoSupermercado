package modelo;

import java.io.Serializable;
import java.util.Collection;

import excepciones.ClientesSupermercadoException;

public class ClienteSupermercado implements Serializable {

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	private String nombre;
	private String apellido;
	private String codigo;
	private String correo;

	// -----------------------------------------------------------------
	// Relaciones
	// -----------------------------------------------------------------

	private ClienteSupermercado izquierda;
	private ClienteSupermercado derecha;

	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	public ClienteSupermercado(String nombre, String apellido, String codigo, String correo) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.codigo = codigo;
		this.correo = correo;
	}

	// -----------------------------------------------------------------
	// Metodos
	// -----------------------------------------------------------------

	/**
	 * Compara este contacto con otro
	 * 
	 * @param o es el otro contacto con el que se compara
	 * @return -1 si este contacto es menor al otro, 0 si son iguales y 1 si este
	 *         contacto es mayor al otro
	 */
	public int compareTo(Object o) {
		ClienteSupermercado otro = (ClienteSupermercado) o;
		return codigo.compareToIgnoreCase(otro.codigo);
	}

	/**
	 * Indica si este nodo es una hoja
	 * 
	 * @return true si este nodo es una hoja y false en caso contrario
	 */
	public boolean esHoja() {
		return izquierda == null && derecha == null;
	}

	/**
	 * Retorna el contacto que alfab�ticamente corresponde al menor contacto del
	 * �rbol que parte de este nodo
	 * 
	 * @return contacto con menor nombre
	 */
	public ClienteSupermercado darMenor() {
		return (izquierda == null) ? this : izquierda.darMenor();
	}

	/**
	 * Retorna el contacto que alfab�ticamente corresponde al mayor contacto del
	 * �rbol que parte de este nodo
	 * 
	 * @return contacto con mayor nombre
	 */
	public ClienteSupermercado darMayor() {
		return (derecha == null) ? this : derecha.darMayor();
	}

	/**
	 * Retorna la altura del �rbol de contactos que comienza en este nodo
	 * 
	 * @return altura del �rbol que comienza en este nodo
	 */
	public int darAltura() {
		if (esHoja())
			return 1;
		else {
			int a1 = (izquierda == null) ? 0 : izquierda.darAltura();
			int a2 = (derecha == null) ? 0 : derecha.darAltura();
			return 1 + Math.max(a1, a2);
		}
	}

	/**
	 * Retorna el n�mero de contactos que hay en el �rbol que comienza en este
	 * nodo
	 * 
	 * @return n�mero de contactos en el �rbol que comienza en este nodo
	 */
	public int darPeso() {
		int p1 = (izquierda == null) ? 0 : izquierda.darPeso();
		int p2 = (derecha == null) ? 0 : derecha.darPeso();
		return 1 + p1 + p2;
	}

	/**
	 * Retorna el n�mero de hojas que hay en el �rbol que comienza en este nodo
	 * 
	 * @return n�mero de hojas que hay en el �rbol que comienza en este nodo
	 */
	public int contarHojas() {
		if (esHoja())
			return 1;
		else {
			int h1 = (izquierda == null) ? 0 : izquierda.contarHojas();
			int h2 = (derecha == null) ? 0 : derecha.contarHojas();
			return h1 + h2;
		}
	}

	/**
	 * Inserta un nuevo contacto al �rbol que comienza en este nodo.
	 * 
	 * @param nuevo el el nuevo contacto que se va a insertar - nuevo != null
	 * @throws ContactoRepetidoException se lanza esta excepci�n si el contacto
	 *                                   que se quiere agregar ya est� en el
	 *                                   directorio
	 */
	public void insertar(ClienteSupermercado nuevo) throws ClientesSupermercadoException {
		if (compareTo(nuevo) == 0)
			throw new ClientesSupermercadoException();

		if (compareTo(nuevo) > 0) {
			// Debe agregar el nuevo contacto por el sub�rbol izquierdo
			if (izquierda == null)
				izquierda = nuevo;
			else
				izquierda.insertar(nuevo);
		} else {
			// Debe agregar el nuevo contacto por el sub�rbol derecho
			if (derecha == null)
				derecha = nuevo;
			else
				derecha.insertar(nuevo);
		}
	}

	/**
	 * Implementaci�n recursiva para localizar un contacto en el �rbol que
	 * comienza en este nodo
	 * 
	 * @param unNombre nombre que se va a buscar - unNombre != null
	 * @return contacto asociado al nombre. Si no lo encuentra retorna null;
	 */
	public ClienteSupermercado buscar(String codigo) {
		if (codigo.compareToIgnoreCase(codigo) == 0)
			return this;
		else if (codigo.compareToIgnoreCase(codigo) > 0)
			// condicion ? si es verdadero : si no lo es
			return (izquierda == null) ? null : izquierda.buscar(codigo);
		else
			return (derecha == null) ? null : derecha.buscar(codigo);
	}

	/**
	 * Elimina un contacto del �rbol que comienza en este nodo.
	 * 
	 * @param unNombre nombre del contacto que se va a eliminar - hay un contacto en
	 *                 el �rbol que se llama unNombre
	 * @return el �rbol de contactos despu�s de eliminar el contacto indicado
	 */
	public ClienteSupermercado eliminar(String codigo) {
		if (esHoja())
			// Tiene que ser el elemento que estamos buscando
			return null;
		if (nombre.compareToIgnoreCase(codigo) == 0) {
			if (izquierda == null)
				return derecha;
			if (derecha == null)
				return izquierda;
			// Localiza el menor contacto del sub�rbol derecho
			ClienteSupermercado sucesor = derecha.darMenor();
			// Elimina del sub�rbol derecho el elemento que acaba de localizar
			derecha = derecha.eliminar(sucesor.getCodigo());
			// Deja el elemento localizado en la ra�z del �rbol de respuesta
			sucesor.izquierda = izquierda;
			sucesor.derecha = derecha;
			return sucesor;
		} else if (nombre.compareToIgnoreCase(codigo) > 0)
			izquierda = izquierda.eliminar(codigo);
		else
			derecha = derecha.eliminar(codigo);
		return this;
	}

	/**
	 * Retorna una colecci�n con los nombres de todos los contactos, ordenados
	 * alfab�ticamente en orden ascendente
	 * 
	 * @param acumulado colecci�n donde se van agregando los nombres de los
	 *                  contactos ordenadamente
	 */

	// La idea del metodo inorden es primero revisar todos los lados izquierdos, una
	// vez hecho
	// agregar la posicion que estabamos revisando y ah� si revisar los lados
	// derechos

	public void inorden(Collection acumulado) {
		// Recorre en inorden el sub�rbol izquierdo
		if (izquierda != null)
			izquierda.inorden(acumulado);
		// Incluye en el recorrido el contacto de la ra�z
		acumulado.add(this);
		// Recorre en inorden el sub�rbol derecho
		if (derecha != null)
			derecha.inorden(acumulado);
	}

	/**
	 * Indica si el �rbol que comienza en este nodo es ordenado
	 * 
	 * @return true si el �rbol que comienza en este nodo es ordenado
	 */
	public boolean esOrdenado() {
		if (esHoja())
			return true;
		// Si la izquierda es null, mira la derecha
		else if (izquierda == null)
			return derecha.esOrdenado() && codigo.compareTo(derecha.darMenor().getCodigo()) < 0;
		// Si la derecha es null, mira la izquierda
		else if (derecha == null)
			return izquierda.esOrdenado() && codigo.compareTo(izquierda.darMayor().getCodigo()) > 0;
		// Si ninguno es null, mira ambos lados del arbol
		else
			return derecha.esOrdenado() && codigo.compareTo(derecha.darMenor().getCodigo()) < 0
					&& izquierda.esOrdenado() && codigo.compareTo(izquierda.darMayor().getCodigo()) > 0;
	}

	/**
	 * Cuenta el n�mero de veces que aparece un contacto con un nombre que se
	 * recibe como par�metro
	 * 
	 * @param nombreP nombre del contacto que se est� buscando - nombreP != null
	 * @return n�mero de contactos con un nombre dado
	 */
	public int contarOcurrencias(String codigo) {
		int cuantos = 0;
		if (codigo.equals(codigo))
			cuantos++;
		cuantos += (izquierda == null) ? 0 : izquierda.contarOcurrencias(codigo);
		cuantos += (derecha == null) ? 0 : derecha.contarOcurrencias(codigo);
		return cuantos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

}
