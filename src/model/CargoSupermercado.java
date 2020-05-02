package model;

import java.io.Serializable;
import java.util.ArrayList;

public class CargoSupermercado implements Serializable {

	// Para los cajeros del supermercado utilizamos un arbol n-rio

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	private String nombreCargo;

	// -----------------------------------------------------------------
	// Relaciones
	// -----------------------------------------------------------------

	private CargoSupermercado jefe;
	private EmpleadoSupermercado asignado;
	private ArrayList subalternos;

	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	public CargoSupermercado(String nombreCargo, CargoSupermercado cargoSuperior) {
		this.nombreCargo = nombreCargo;
		jefe = cargoSuperior;
		asignado = null;
		subalternos = new ArrayList();
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Retorna el nombre del cargo
	 * 
	 * @return El nombre del cargo
	 */
	public String darNombreCargo() {
		return nombreCargo;
	}

	/**
	 * Retorna el empleado que ocupa el cargo
	 * 
	 * @return El empleado que ocupa el cargo
	 */
	public EmpleadoSupermercado darEmpleado() {
		return asignado;
	}

	/**
	 * Indica si el cargo se encuentra vacante
	 * 
	 * @return true si el cargo está vacante o false en caso contrario
	 */
	public boolean estaVacante() {
		return asignado == null;
	}

	/**
	 * Retorna los subalternos del empleado.
	 * 
	 * @return La lista de subalternos
	 */
	public ArrayList darSubAlternos() {
		return subalternos;
	}

	/**
	 * Agrega un cargo a la lista de subalternos del cargo actual
	 * 
	 * @param nCargo      El nuevo del nuevo cargo
	 * @param nombreCargo El nombre del nuevo cargo
	 */
	public void agregarCargo(String nombreCargo) {
		CargoSupermercado subalterno = new CargoSupermercado(nombreCargo, this);
		// Todos los cargos se encuentran en subalterno
		subalternos.add(subalterno);
	}

	/**
	 * Elimina la referencia al empleado que ocupaba el cargo
	 */
	public void despedir() {
		asignado = null;
	}

	/**
	 * Indica si el elemento es una hoja o no
	 * 
	 * @return true si el elemento es una hoja, false en caso contrario
	 */
	public boolean esHoja() {
		return subalternos.size() == 0;
	}

	/**
	 * Retorna una cadena que identifica el cargo
	 * 
	 * @return La cadena que identifica el cargo
	 */
	@Override
	public String toString() {
		return nombreCargo;
	}

}
