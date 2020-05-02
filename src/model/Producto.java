package model;

import java.util.Random;

public class Producto implements Comparable<Producto> {
	
	private FechaDeLlegada fechaDeLlegada;
	private Tiempo horario;
	private String nombreDeLaMarca;
	private String codigoDelProducto;
	private String nombreDelProducto;
	private String tipo;
	private Producto siguiente;
	private Producto anterior;

	
	public Producto(FechaDeLlegada fechaDeLlegada, Tiempo horario, String nombreDeLaMarca,String codigoDelProducto, String nombreDelProducto) {
		Random random = new Random();
		this.fechaDeLlegada = fechaDeLlegada;
		this.horario = horario;
		this.nombreDeLaMarca = nombreDeLaMarca;
		this.codigoDelProducto = codigoDelProducto;
		this.nombreDelProducto = nombreDelProducto;
		this.tipo =  random.nextInt(20)+1+"";
	}
	/**
	 * Este metodo permite obtener la informacion de los productos
	 */
	
	public String toString() {
		String mensaje= " \n";
		mensaje+= "Fecha de llegada del producto: "+fechaDeLlegada.toString()+ "\n"+"Horario de la fecha de llegada: "+horario.toString()+"\n"+"Nombre de la marca del producto: "+getNombreDeLaMarca()+"\n"+"Código del producto: "+getCodigoDelProducto()+"\n"+"Nombre del Producto: "+getNombreDelProducto()+"\n"+"Tipo: "+getTipo();
		return mensaje;
	}

	/**
	 * este metodo permite obtener la fecha de llegad del producto
	 * @return
	 */
	public FechaDeLlegada getFechaDeLlegada() {
		return fechaDeLlegada;
	}
	public void setFechaDeLlegada(FechaDeLlegada fechaDeLlegada) {
		this.fechaDeLlegada = fechaDeLlegada;
	}
	/**
	 * este metodo permite obtener el horario de llegada del producto
	 * @return
	 */
	public Tiempo getHorario() {
		return horario;
	}
	public void setHorario(Tiempo horario) {
		this.horario = horario;
	}
	/**
	 * este metodo permite obtener el nombre de la marca del producto
	 * @return una cadena que contiene la marca del producto
	 */
	public String getNombreDeLaMarca() {
		return nombreDeLaMarca;
	}
	public void setNombreDeLaMarca(String nombreDeLaMarca) {
		this.nombreDeLaMarca = nombreDeLaMarca;
	}
	/** 
	 * este metodo permite obtener el codigo del producto
	 * @return unca cadena que contiene el codigo del producto
	 */
	public String getCodigoDelProducto() {
		return codigoDelProducto;
	}
	public void setCodigoDelProducto(String codigoDelProducto) {
		this.codigoDelProducto = codigoDelProducto;
	}
	/**
	 * este metodo permite obtene el nombre del producto
	 * @return una cadena con el nombre del producto 
	 */
	public String getNombreDelProducto() {
		return nombreDelProducto;
	}
	public void setNombreDelProducto(String nombreDelProducto) {
		this.nombreDelProducto = nombreDelProducto;
	}
	/**
	 * este metodo permite obtener el tipo de producto
	 * @return una cadena con el tipo de producto
	 */
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String boardingGate) {
		this.tipo = boardingGate;
	}
	/**
	 * este metodo permite obtener el producto siguiente de la lista
	 * @return el producto siguiente de este producto
	 */
	public Producto getSiguiente() {
		return siguiente;
	}
	public void setSiguiente(Producto siguiente) {
		this.siguiente = siguiente;
	}
	/**
	 * este metodo permite obtener el anterior de la lista de productos
	 * @return
	 */
	public Producto getAnterior() {
		return anterior;
	}
	public void setAnterior(Producto anterior) {
		this.anterior = anterior;
	}

	//COMPARA POR FECHA DE LLEGADA
	/**
	 * este metodo permite obtener que producto es mayor tomando como criterio la fecha de llegada
	 */
	@Override
	public int compareTo(Producto otroProducto) {
		
		int comparation = fechaDeLlegada.compareTo(otroProducto.fechaDeLlegada);
		return comparation;
	}


}
