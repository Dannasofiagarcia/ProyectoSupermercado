package model;

import javafx.scene.image.Image;

public class ClienteFarmacia implements Comparable<ClienteFarmacia> {
	private int id;
	private String nombre;
	private String apellido;
	private String email;
	private String genero;
	private String pais;
	private Image foto;
	private String cumpleanios;
	private ClienteFarmacia izquierda;
	private ClienteFarmacia derecha;
	private ClienteFarmacia siguiente;
	private ClienteFarmacia anterior;

	public ClienteFarmacia(int id, String nombre, String apellido, String email, String genero, String pais, Image foto,
			String cumpleanios) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.genero = genero;
		this.pais = pais;
		this.foto = foto;
		this.cumpleanios = cumpleanios;
	}

	/**
	 * Este metodo obtiene el id del cliente de la farmacia
	 * 
	 * @return int el id del cliente de la farmacia
	 */
	public int getId() {
		return id;
	}

	/**
	 * este metodo permite cambiar el id del cliente
	 * 
	 * @param id el nuevo id del cliente de la farmacia
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Este metodo permite obtener el nombre del Cliente de la farmacia
	 * 
	 * @return String retorna el nombre del cliente de la farmacia
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * este metodo permite cambiar el nombre del cliente
	 * 
	 * @param nombre el nuevo nombre del cliente de la farmacia
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Este metodo permite obtener el apellido del Cliente de la farmacia
	 * 
	 * @return String una cadena que representa el apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * Este metodo permite cambiar el apellido del cliente
	 * 
	 * @param apellido el nuevo apellido del cliente
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * Este metodo permite obtener el email del Cliente de la farmacia
	 * 
	 * @return una cadena que representa el email del cliente
	 */

	public String getEmail() {
		return email;
	}

	/**
	 * Este metodo permite cambiar el email del cliente
	 * 
	 * @param email el nuevo email del cliente
	 */

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Este metodo permite obtener el genero del Cliente de la farmacia
	 * 
	 * @return una cadena que representa el genero del cliente
	 */
	public String getGenero() {
		return genero;
	}

	/**
	 * Este metodo permite cambiar el genero del cliente
	 * 
	 * @param genero el nuevo genero del cliente
	 */

	public void setGenero(String genero) {
		this.genero = genero;
	}

	/**
	 * Este metodo permite obtener el pais del Cliente de la farmacia
	 * 
	 * @return una cadena que representa el pais del cliente
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * Este metodo permite cambiar el país del cliente
	 * 
	 * @param pais el nuevo apellido del cliente
	 */

	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * Este metodo permite obtener una imagen del Cliente de la farmacia
	 * 
	 * @return Image una imagén que representa a este cliente
	 */
	public Image getFoto() {
		return foto;
	}

	/**
	 * Este metodo permite cambiar el país del cliente
	 * 
	 * @param foto la nueva foto del cliente
	 */

	public void setFoto(Image foto) {
		this.foto = foto;
	}

	/**
	 * Este metodo permite obtener la fecha de cumpleanios del Cliente de la
	 * farmacia
	 * 
	 * @return una cadena que representa la fecha de cumpleaÃ±os
	 */
	public String getCumpleanios() {
		return cumpleanios;
	}

	public void setCumpleanios(String cumpleanios) {
		this.cumpleanios = cumpleanios;
	}

	/**
	 * Este metodo permite obtener el cliente que estÃ¡ a la izquierda de este
	 * cliente
	 * 
	 * @return El cliente a la izquierda de este
	 */
	public ClienteFarmacia getIzquierda() {
		return izquierda;
	}

	public void setIzquierda(ClienteFarmacia izquierda) {
		this.izquierda = izquierda;
	}

	/**
	 * Este metodo permite obtener el cliente que esta a la derecha de este cliente
	 * 
	 * @return ClienteFarmacia El cliente a la derecha de este
	 */
	public ClienteFarmacia getDerecha() {
		return derecha;
	}

	public void setDerecha(ClienteFarmacia derecha) {
		this.derecha = derecha;
	}

	/**
	 * Este metodo permite obtener el cliente que esta siguiente de este cliente
	 * 
	 * @return ClienteFarmacia El cliente siguiente de este
	 */
	public ClienteFarmacia getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(ClienteFarmacia siguiente) {
		this.siguiente = siguiente;
	}

	/**
	 * Este metodo permite obtener el cliente que esta anterior de este cliente
	 * 
	 * @return ClienteFarmacia El cliente anterior de este
	 */
	public ClienteFarmacia getAnterior() {
		return anterior;
	}

	public void setAnterior(ClienteFarmacia anterior) {
		this.anterior = anterior;
	}

	/**
	 * Este metodo permite comparar a dos clientes tomando como criterio su id
	 * return -1 si este contacto es menor al otro, 0 si son iguales y 1 si este
	 * contacto es mayor al otro
	 * 
	 * @return int
	 */
	@Override
	public int compareTo(ClienteFarmacia c) {
		int comparation;

		if (id < c.id) {
			comparation = -1;
		} else if (id > c.id) {
			comparation = 1;
		} else {
			comparation = 0;
		}

		return comparation;
	}
}
