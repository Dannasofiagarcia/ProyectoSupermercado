package model;

import java.io.Serializable;

public class EmpleadoSupermercado implements Serializable {

	static final long serialVersionUID = 42L;

	// ATRIBUTOS

	private String nombre;
	private String codigo;
	private String turno;

	// CONSTRUCTOR

	public EmpleadoSupermercado(String nombre, String codigo, String turno) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
		this.turno = turno;
	}

	// METODOS

	// Getter y setter

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

}
