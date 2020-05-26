package modelo;

public class ListaDeProductos {
	private String nombre;
	private int id;
	private ListaDeProductos siguiente;
	private ListaDeProductos anterior;

	public ListaDeProductos(String nombre, int id) {
		this.nombre = nombre;
		this.id = id;
	}

	/**
	 * este metodo permite obtener el nombre de la lsita de productos
	 * 
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * este metodo permite obtener el id de la lista de productos
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * este metodo permite obtener el siguiente elemento de la lista de productos
	 * 
	 * @return el siguiente elemento de la lista
	 */
	public ListaDeProductos getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(ListaDeProductos siguiente) {
		this.siguiente = siguiente;
	}

	/**
	 * este metodo permite obtener el anterior elemento de la lista de productos
	 * 
	 * @return el anterior elemento de la lista
	 */
	public ListaDeProductos getAnterior() {
		return anterior;
	}

	public void setAnterior(ListaDeProductos anterior) {
		this.anterior = anterior;
	}

}
