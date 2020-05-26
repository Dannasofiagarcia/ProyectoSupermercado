package supermercado;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.Collection;

import org.junit.jupiter.api.Test;

import excepciones.CargoSupermercadoException;
import excepciones.ClientesSupermercadoException;
import modelo.CargoSupermercado;
import modelo.ClienteSupermercado;
import modelo.EmpleadoSupermercado;
import modelo.Supermercado;

public class SupermercadoTest {

	/**
	 * Es la clase donde se har�n las pruebas
	 */
	private Supermercado supermercado;

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Construye una supermercado y lo inicializa con algunos cargos
	 */
	private void setupEscenario1() {
		try {
			supermercado = new Supermercado("Supermercado");
			supermercado.crearCargo("Gerente General", "");
			supermercado.crearCargo("G. Comercial", "Gerente General");
			supermercado.crearCargo("Coordinador Ventas", "G. Comercial");
			supermercado.crearCargo("Vendedor 0", "Coordinador Ventas");
			supermercado.crearCargo("Vendedor 1", "Coordinador Ventas");
			supermercado.crearCargo("Vendedor 2", "Coordinador Ventas");
			supermercado.crearCargo("Vendedor 3", "Coordinador Ventas");
			supermercado.crearCargo("Vendedor 4", "Coordinador Ventas");
			supermercado.crearCargo("G. de Tecnolog�a", "Gerente General");
			supermercado.crearCargo("Arquitecto 1", "G. de Tecnolog�a");
			supermercado.crearCargo("Arquitecto 2", "G. de Tecnolog�a");
			supermercado.crearCargo("G. Financiero", "Gerente General");
			supermercado.crearCargo("Contador1", "G. Financiero");
			supermercado.crearCargo("Contador2", "G. Financiero");
			supermercado.crearCargo("Contador3", "G. Financiero");
		} catch (CargoSupermercadoException e) {

		}
	}

	/**
	 * Construye una supermercado y lo inicializa con algunos cargos y empleados
	 */
	private void setupEscenario2() {
		try {
			File archi = new File("./test/data/supermercado2.dat");
			if (archi.exists()) {
				archi.delete();
			}
			supermercado = new Supermercado("Supermercado");
			supermercado.crearCargo("Gerente General", "");
			supermercado.crearCargo("G. Comercial", "Gerente General");
			supermercado.crearCargo("Coordinador Ventas", "G. Comercial");
			supermercado.crearCargo("Vendedor 0", "Coordinador Ventas");
			supermercado.crearCargo("Vendedor 1", "Coordinador Ventas");
			supermercado.crearCargo("Vendedor 2", "Coordinador Ventas");
			supermercado.crearCargo("Vendedor 3", "Coordinador Ventas");
			supermercado.crearCargo("Vendedor 4", "Coordinador Ventas");
			supermercado.crearCargo("G. de Tecnolog�a", "Gerente General");
			supermercado.crearCargo("Arquitecto 1", "G. de Tecnolog�a");
			supermercado.crearCargo("Arquitecto 2", "G. de Tecnolog�a");
			supermercado.crearCargo("G. Financiero", "Gerente General");
			supermercado.crearCargo("Contador1", "G. Financiero");
			supermercado.crearCargo("Contador2", "G. Financiero");
			supermercado.crearCargo("Contador3", "G. Financiero");

			supermercado.contratarPersona("555", "Diana Ortiz", "Contador1", "");
			supermercado.contratarPersona("556", "Carlos Mendez", "Contador3", "");
			supermercado.contratarPersona("557", "Daniel Castro", "Gerente General", "");
			supermercado.contratarPersona("558", "Francisco Guiterrez", "Coordinador Ventas", "");
			supermercado.contratarPersona("559", "Pedro P�rez", "Vendedor 0", "");
			supermercado.contratarPersona("560", "Francisco Miguel", "Arquitecto 2", "");
		} catch (CargoSupermercadoException e) {

		}
	}

	/**
	 * Prueba el m�todo crearCargo. <br>
	 * <b> M�todos a probar: </b> <br>
	 * crearCargo, buscarCargo. <br>
	 * <b> Objetivo: </b> Probar que el m�todo crearCargo() se capaz de crear
	 * correctamente un cargo en la supermercado. <br>
	 * <b> Resultados esperados: </b> <br>
	 * 1. Al crear un cargo cuyo nombre no corresponda a otro cargo existente, al
	 * buscarlo �ste debe ser encontrado.
	 */

	@Test

	public void testCrearCargoOK() {
		setupEscenario1();

		try {
			supermercado.crearCargo("Vendedor puerta a puerta", "Gerente General");
			supermercado.crearCargo("Vendedor puerta a puerta2", "Vendedor 4");
			supermercado.crearCargo("Vendedor puerta a puerta3", "Contador2");
		} catch (CargoSupermercadoException e) {
			fail("No se debi� arrojar excepci�n");
		}

		CargoSupermercado c = supermercado.buscarCargo("Vendedor puerta a puerta");
		CargoSupermercado c2 = supermercado.buscarCargo("Vendedor puerta a puerta2");
		assertNotNull("No se encontr� el cargo reci�n creado", c);
		assertNotNull("No se encontr� el cargo reci�n creado", c2);
	}

	/**
	 * Verifica el m�todo eliminarCargo. <br>
	 * <b> M�todos a probar: </b> <br>
	 * eliminarCargo, buscarCargo. <br>
	 * <b> Objetivo: </b> Probar que el m�todo eliminarCargo() sea capaz de eliminar
	 * un cargo existente <br>
	 * en la supermercado. <br>
	 * <b> Resultados esperados: </b> <br>
	 * 1. Al eliminar un cargo existente, vacante y que no tenga cargos
	 * dependientes, �ste no debe ser encontrado al buscarlo. <br>
	 */
	@Test
	public void testEliminarCargoOK() {
		setupEscenario1();

		// Cargo existente
		try {
			supermercado.eliminarCargo("Contador3");
			supermercado.eliminarCargo("contador2");
			supermercado.eliminarCargo("contador1");

		} catch (CargoSupermercadoException e) {
			e.printStackTrace();
			fail("No se debi� arrojar excepci�n");

		}
		CargoSupermercado c = supermercado.buscarCargo("contador1");
		assertNull("El cargo no debi� encontrarse", c);

		c = supermercado.buscarCargo("contador3");
		assertNull("El cargo no debi� encontrarse", c);

		c = supermercado.buscarCargo("contador2");
		assertNull("El cargo no debi� encontrarse", c);

		assertEquals("El n�mero de empleados que se elimin� no es el correcto", 12,
				supermercado.darListaCargos().size());

	}

	/**
	 * Prueba el m�todo crearCargo. <br>
	 * <b> M�todos a probar: </b> <br>
	 * contratarPersona, buscarEmpleado. <br>
	 * <b> Objetivo: </b> Probar que el m�todo contratarPersona() se capaz de crear
	 * correctamente <br>
	 * una persona en el organigrama de la supermercado. <br>
	 * <b> Resultados esperados: </b> <br>
	 * 1. Al crear un empleado cuyo cargo exista, al buscarlo �ste debe ser
	 * encontrado.
	 */
	@Test
	public void testContratarPersonaOK() {
		setupEscenario1();

		try {
			supermercado.crearCargo("Vendedor puerta a puerta", "contador1");
			supermercado.contratarPersona("555", "Carlos Ochoa", "Vendedor Puerta a Puerta", "");
			supermercado.contratarPersona("557", "Marcela Mantilla", "Gerente General", "");
			supermercado.contratarPersona("558", "Paola Duque", "contador1", "");
		} catch (CargoSupermercadoException e) {
		}

		EmpleadoSupermercado e = supermercado.buscarEmpleado("555");
		EmpleadoSupermercado e2 = supermercado.buscarEmpleado("557");
		EmpleadoSupermercado e3 = supermercado.buscarEmpleado("558");
	}

	/**
	 * Verifica el m�todo buscarEmpleado. <br>
	 * <b> M�todos a probar: </b> <br>
	 * buscarEmpleado. <br>
	 * <b> Objetivo: </b> Probar que el m�todo buscarEmpleado() sea capaz de
	 * encontrar un empleado <br>
	 * existente en la supermercado <br>
	 * <b> Resultados esperados: </b> <br>
	 * 1. Al buscar un empleado existente en la supermercado, �ste debe ser
	 * encontrado. <br>
	 * 2. Al buscar un empleado que no pertenezca a la supermercado, �ste no debe
	 * ser encontrado. <br>
	 */
	@Test
	public void testBuscarEmpleado() {
		setupEscenario2();

		// Empleado existente
		EmpleadoSupermercado e = supermercado.buscarEmpleado("558");

		// Empleado existente
		e = supermercado.buscarEmpleado("560");

		// Empleado inexistente
		e = supermercado.buscarEmpleado("1");
	}

	/**
	 * Verifica el m�todo buscarCargoEmpleado. <br>
	 * <b> M�todos a probar: </b> <br>
	 * buscarCargoEmpleado. <br>
	 * <b> Objetivo: </b> Probar que el m�todo buscarCargoEmpleado() sea capaz de
	 * encontrar el cargo asociado <br>
	 * con un empleado existente en la supermercado <br>
	 * <b> Resultados esperados: </b> <br>
	 * 1. Al buscar el cargo de un empleado existente en la supermercado, �ste debe
	 * ser encontrado. <br>
	 * 2. Al buscar el cargo un empleado que no pertenezca a la supermercado, �ste
	 * no debe ser encontrado. <br>
	 */
	@Test
	public void testBuscarCargoEmpleado() {
		setupEscenario2();

		// Empleado existente
		CargoSupermercado c = supermercado.buscarCargoEmpleado("558");

		// Empleado existente
		c = supermercado.buscarCargoEmpleado("555");

		// Empleado inexistente
		c = supermercado.buscarCargoEmpleado("1000");
	}

	/**
	 * Verifica el m�todo darListaCargos. <br>
	 * <b> M�todos a probar: </b> <br>
	 * darListaCargos. <br>
	 * <b> Objetivo: </b> Probar que el m�todo darListaCargos() retorne
	 * correctamente la lista de cargos <br>
	 * de la supermercado <br>
	 * <b> Resultados esperados: </b> <br>
	 * 1. Se sabe que el la cantidad de cargos es X. Al pedir el la lista de cargos
	 * su tama�o debe ser X. <br>
	 */
	@Test
	public void testDarListaCargos() {
		setupEscenario1();

		Collection lista = supermercado.darListaCargos();
		assertEquals("El tama�o de la lista es incorrecto", 15, lista.size());

		setupEscenario2();

		lista = supermercado.darListaCargos();
	}

	/**
	 * Nombre de prueba
	 */
	private String nombre1;

	/**
	 * Nombre de prueba 2
	 */
	private String nombre2;

	/**
	 * Nombre de prueba 3
	 */
	private String nombre3;

	/**
	 * Crea un supermercado vac�o
	 */
	private void setupEscenario0() {
		supermercado = new Supermercado("super");
	}

	/**
	 * Crea un supermercado con 1 cliente
	 */
	private void setupEscenario10() {
		setupEscenario0();
		nombre1 = "Marta";
		try {
			supermercado.agregarClienteSupermercado(nombre1, "e", "56", "d@hotmail.com");
		} catch (ClientesSupermercadoException e) {
		}
	}

	/**
	 * Crea un supermercado con 3 clientes
	 */
	private void setupEscenario20() {
		setupEscenario1();
		try {
			nombre2 = "Alberto";
			supermercado.agregarClienteSupermercado(nombre2, "em", "53", "d3@hotmail.com");
			nombre3 = "Xiomara";
			supermercado.agregarClienteSupermercado(nombre3, "ee", "356", "dd@hotmail.com");
		} catch (ClientesSupermercadoException e) {
		}

	}

	/**
	 * Prueba la creaci�n correcta de un supermercado vac�o. <br>
	 * <b> M�todos a probar: </b> <br>
	 * supermercado. <br>
	 * <b> Objetivo: </b> Probar que el m�todo supermercado() crea el supermercado de
	 * forma correcta . <br>
	 * <b> Resultados esperados: </b> <br>
	 * 1. Al pedir crear un supermercado �ste debe estar vac�o.
	 */
	@Test
	public void testsupermercadoVacio() {
		setupEscenario0();

		// En el supermercado no hay clientes
		assertNull(supermercado.darListaClientes());
	}

	/**
	 * Prueba la b�squeda en el supermercado. <br>
	 * <b> M�todos a probar: </b> <br>
	 * buscarcliente. <br>
	 * <b> Objetivo: </b> Probar que el m�todo buscarcliente() sea capaz de
	 * encontrar un cliente dado en el supermercado. <br>
	 * <b> Resultados esperados: </b> <br>
	 * 1. Al buscar un cliente previamente adicionado, �ste debe ser encontrado.
	 * <br>
	 * 2. Al busca un cliente que no exista se debe retornar null.
	 */
	@Test
	public void testBuscarcliente() {
		setupEscenario2();

		// Busca un cliente que existe
		ClienteSupermercado c = supermercado.buscarCliente(nombre1);

		// Busca un cliente que no existe
		c = supermercado.buscarCliente("Petronila");

	}

	/**
	 * Prueba el m�todo para agregar clientes en el supermercado. <br>
	 * <b> M�todos a probar: </b> <br>
	 * agregarcliente, buscarcliente. <br>
	 * <b> Objetivo: </b> Probar que el m�todo agregarcliente() sea capaz de
	 * agregar correctamente un cliente al supermercado. <br>
	 * <b> Resultados esperados: </b> <br>
	 * 1. Al agregar un cliente este debe quedar en el �rbol de clientes. <br>
	 * 2. Al buscar un cliente previamente adicionado, �ste debe ser encontrado.
	 */
	@Test
	public void testAgregarcliente() {
		setupEscenario1();

		try {
			supermercado.agregarClienteSupermercado("nombre2", "tel2", "dir2", "email2");
		} catch (ClientesSupermercadoException e) {
		}
		ClienteSupermercado cliente = supermercado.buscarCliente("nombre2");
	}

}
