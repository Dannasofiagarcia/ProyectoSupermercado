package model;

import java.io.Serializable;

public class ProductoSupermercado implements Serializable {
	static final long serialVersionUID = 42L;

	// Para los productos del supermercado utilizaremos una lista doblemente
	// enlazada

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	private String codigo;
	private String nombre;
	private double precio;
	private String ubicacion;

	// -----------------------------------------------------------------
	// Relaciones
	// -----------------------------------------------------------------

	private ProductoSupermercado siguiente;
	private ProductoSupermercado anterior;

	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	public ProductoSupermercado(String codigo, String nombre, double precio, String ubicacion) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.ubicacion = ubicacion;
	}

	// -----------------------------------------------------------------
	// Metodos
	// -----------------------------------------------------------------

	/**
	 * Inserta el vuelo después del actual. <br>
	 * <b>post: </b> Se insertó el vuelo especificado después del vuelo actual. <br>
	 * 
	 * @param vuelo El vuelo a insertar - vuelo!=null
	 */
	public void insertarDespues(ProductoSupermercado producto) {
		producto.siguiente = siguiente;
		if (siguiente != null)
			siguiente.anterior = producto;
		producto.anterior = this;
		siguiente = producto;
	}

	/**
	 * Inserta el vuelo antes del actual. <br>
	 * <b>post: </b> Se insertó el vuelo especificado antes del vuelo actual. <br>
	 * 
	 * @param vuelo El vuelo a insertar - vuelo!=null
	 */
	public void insertarAntes(ProductoSupermercado producto) {
		if (anterior != null)
			anterior.siguiente = producto;

		producto.anterior = anterior;
		producto.siguiente = this;
		anterior = producto;
	}

	// -----------------------------------------------------------------
	// Getter y setter
	// -----------------------------------------------------------------

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ProductoSupermercado getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(ProductoSupermercado siguiente) {
		this.siguiente = siguiente;
	}

	public ProductoSupermercado getAnterior() {
		return anterior;
	}

	public void setAnterior(ProductoSupermercado anterior) {
		this.anterior = anterior;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	@Override
	public String toString() {
		return nombre + "," + codigo + "," + precio + "," + ubicacion;
	}

}
