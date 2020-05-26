package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class CargoSupermercado implements Serializable {

	static final long serialVersionUID = 42L;

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
	// M�todos
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
	 * @return true si el cargo est� vacante o false en caso contrario
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
	 * @param nCargo El nuevo del nuevo cargo
	 * @param pago   El pago del nuevo cargo
	 */
	public void agregarCargo(String nombreCargo) {
		CargoSupermercado subalterno = new CargoSupermercado(nombreCargo, this);
		// Todos los cargos se encuentran en subalterno
		subalternos.add(subalterno);
	}

	/**
	 * Elimina el cargo con el nombre dado
	 * 
	 * @param nCargo El nombre del cargo a eliminar
	 * @throws OrganigramaException Si el cargo no es una hoja o no est� vacante
	 */
	public void eliminarCargo(String nCargo) {
		for (int i = 0; i < subalternos.size(); i++) {
			CargoSupermercado hijo = (CargoSupermercado) subalternos.get(i);
			if (hijo.darNombreCargo().equalsIgnoreCase(nCargo)) {
				if (hijo.esHoja() && hijo.estaVacante()) {
					subalternos.remove(i);
					return;
				}
//                else
//                    throw new OrganigramaException( "No eliminable" );
			}
		}
	}

	/**
	 * Asigna la persona con los datos especificados al cargo actual
	 * 
	 * @param idPersona El c�digo de la persona
	 * @param nombre    El nombre de la persona
	 * @param ingreso   La fecha de ingreso
	 * @throws OrganigramaException Si el cargo ya se encuentra ocupado
	 */
	public void contratar(String nombre, String codigo, String turno) {
//		if (asignado != null)
//			throw new OrganigramaException("Cargo no vacante...");

		asignado = new EmpleadoSupermercado(nombre, codigo, turno);
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
	 * Cuenta el n�mero de hojas que hay en el organigrama que comienza con este
	 * elemento
	 * 
	 * @return El n�mero de hojas
	 */
	public int contarHojas() {
		if (esHoja())
			return 1;
		else {
			int numHojasAcum = 0;
			for (int i = 0; i < subalternos.size(); i++) {
				CargoSupermercado hijo = (CargoSupermercado) subalternos.get(i);
				numHojasAcum += hijo.contarHojas();
			}
			return numHojasAcum;
		}
	}

	/**
	 * Calcula el peso del organigrama del que este elemento es ra�z
	 * 
	 * @return peso o n�mero de nodos
	 */
	public int darPeso() {
		if (esHoja())
			return 1;
		else {
			int pesoAcum = 1;
			for (int i = 0; i < subalternos.size(); i++) {
				CargoSupermercado hijo = (CargoSupermercado) subalternos.get(i);
				pesoAcum += hijo.darPeso();
			}
			return pesoAcum;
		}
	}

	/**
	 * Calcula la altura del organigrama del que este elemento es ra�z
	 * 
	 * @return altura del �rbol. altura >= 1
	 */
	public int darAltura() {
		if (esHoja())
			return 1;
		else {
			int max = 0;
			for (int i = 0; i < subalternos.size(); i++) {
				CargoSupermercado hijo = (CargoSupermercado) subalternos.get(i);
				int temp = hijo.darAltura();
				if (temp > max)
					max = temp;
			}
			return max + 1;
		}
	}

	/**
	 * Agrega a la lista que recibe como par�metro los nombres de los cargos que
	 * existen <br>
	 * en el sub�rbol que comienza en este punto del organigrama
	 * 
	 * @param lista La lista en la que se van a adicionar los cargos que existen en
	 *              el sub�rbol <br>
	 *              que comienza en este punto del organigrama
	 */
	public void darListaCargos(Collection lista) {
		lista.add(nombreCargo);
		for (int i = 0; i < subalternos.size(); i++) {
			CargoSupermercado hijo = (CargoSupermercado) subalternos.get(i);
			hijo.darListaCargos(lista);
		}
	}

	/**
	 * Agrega a la lista que recibe como par�metro los nombres de los cargos que se
	 * encuentran <br>
	 * vacantes en el sub�rbol que comienza en este punto del organigrama
	 * 
	 * @param lista La lista en la que se van a adicionar los cargos disponibles en
	 *              el sub�rbol <br>
	 *              que comienza en este punto del organigrama
	 */
	public void darListaCargosDisponibles(Collection lista) {
		if (asignado == null)
			lista.add(nombreCargo);
		for (int i = 0; i < subalternos.size(); i++) {
			CargoSupermercado hijo = (CargoSupermercado) subalternos.get(i);
			hijo.darListaCargosDisponibles(lista);
		}
	}

	// Metodo para contar el numero de vacantes sin utilizar un auxiliar

	public int contarVacantes() {
		int vacantes = 0;

		for (int i = 0; i < subalternos.size(); i++) {
			CargoSupermercado cargo = (CargoSupermercado) subalternos.get(i);
			if (cargo.estaVacante() == true)
				vacantes++;

			if (cargo.esHoja() == false)
				vacantes += cargo.contarVacantes();
		}
		return vacantes;
	}

	/**
	 * Busca un cargo en el organigrama que comienza en este elemento.
	 * 
	 * @param nCargo El nombre del cargo que se est� buscando
	 * @return El cargo con el nombre dado. Si no existe tal cargo en el <br>
	 *         organigrama que comienza en el elemento se retorna null.
	 */
	public CargoSupermercado buscarCargo(String nCargo) {
		if (nombreCargo.equalsIgnoreCase(nCargo)) {
			return this;
		} else {
			for (int i = 0; i < subalternos.size(); i++) {
				CargoSupermercado hijo = (CargoSupermercado) subalternos.get(i);
				CargoSupermercado temp = hijo.buscarCargo(nCargo);
				if (temp != null)
					return temp;
			}
			return null;
		}
	}

	/**
	 * Busca el cargo que ocupa un empleado en la empresa, dado el identificador del
	 * empleado
	 * 
	 * @param idEmpleado El c�digo del empleado del que se desea el cargo
	 * @return El cargo del empleado con el c�digo dado. Si no se encuentra el cargo
	 *         del empleado se retorna null
	 */
	public CargoSupermercado buscarCargoEmpleado(String idEmpleado) {
		if (asignado != null && asignado.getCodigo().equals(idEmpleado)) {
			return this;
		} else {
			for (int i = 0; i < subalternos.size(); i++) {
				CargoSupermercado hijo = (CargoSupermercado) subalternos.get(i);
				CargoSupermercado temp = hijo.buscarCargoEmpleado(idEmpleado);
				if (temp != null)
					return temp;
			}
			return null;
		}
	}

	/**
	 * Busca un empleado en el organigrama que comienza en este elemento.
	 * 
	 * @param idEmpleado El c�digo del empleado que se est� buscando
	 * @return El empleado con el c�digo dado. Si no se encuentra el empleado se
	 *         retorna null
	 */
	public EmpleadoSupermercado buscarEmpleado(String idEmpleado) {
		CargoSupermercado cargo = buscarCargoEmpleado(idEmpleado);
		return (cargo == null) ? null : cargo.asignado;
	}

	/**
	 * Busca el cargo del que depende el cargo con el nombre dado
	 * 
	 * @param nCargo El nombre del cargo del que se desea el cargo jefe
	 * @return El cargo del que depende el cargo actual. Si el cargo no es
	 *         encontrado se retorna null
	 */
	public CargoSupermercado buscarJefe(String nCargo) {
		CargoSupermercado cargo = buscarCargo(nCargo);
		return (cargo == null) ? null : cargo.jefe;
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
