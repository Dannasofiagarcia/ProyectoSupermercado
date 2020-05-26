package modelo;

public class ListaDeMarcas {
	private String nombre;
	private int id;
	private ListaDeMarcas siguiente;
	private ListaDeMarcas anterior;

	public ListaDeMarcas(String nombre, int id) {
		this.nombre = nombre;
		this.id = id;
	}

	/**
	 * este metodo permite obtener el nombre de la lista de marcas
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
	 * Este metodo permite obtener el id de la lista de marcas
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
	 * este metodo permite obtener el objeto siguiente de este nodo
	 * 
	 * @return un objeto tipo ListaDeMarcas que está siguiente a este nodo
	 */
	public ListaDeMarcas getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(ListaDeMarcas siguiente) {
		this.siguiente = siguiente;
	}

	/**
	 * este metodo permite obtener el objeto anterior de este nodo
	 * 
	 * @return un objeto tipo ListaDeMarcas que está anterior a este nodo
	 */
	public ListaDeMarcas getAnterior() {
		return anterior;
	}

	public void setAnterior(ListaDeMarcas anterior) {
		this.anterior = anterior;
	}

}
