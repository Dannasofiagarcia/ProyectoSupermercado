package supermercado;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import modelo.CargoSupermercado;
import modelo.EmpleadoSupermercado;

public class CargoSupermercadoTest {

	/**
	 * Es la clase donde se haran las pruebas
	 */
	private CargoSupermercado cargo;

	/**
	 * Crear un cargo sin empleado asociado
	 * 
	 */
	private void setupEscenario1() {
		cargo = new CargoSupermercado("cargo1", null);
	}

	/**
	 * Crear una jerarquia de cargos de tres niveles sin empleados asociados
	 * 
	 */
	private void setupEscenario2() {
		cargo = new CargoSupermercado("cargo1", null);
		int numCargos = 20;
		int numCargosJerarquia = 1;

		for (int cont = 0; cont < numCargos; cont++) {
			numCargosJerarquia++;
			cargo.agregarCargo("cargo");

		}

		ArrayList subalternos = cargo.darSubAlternos();

		for (int cont = 0; cont < subalternos.size(); cont++) {
			CargoSupermercado c = (CargoSupermercado) subalternos.get(cont);

			for (int cont2 = 0; cont2 < numCargos; cont2++) {
				numCargosJerarquia++;
				c.agregarCargo("cargo");
			}
		}

		for (int cont = 0; cont < subalternos.size(); cont++) {
			CargoSupermercado c = (CargoSupermercado) subalternos.get(cont);

			for (int cont2 = 0; cont2 < numCargos; cont2++) {
				numCargosJerarquia++;
				c.agregarCargo("cargo");
			}
		}
	}

	/**
	 * Crear una jerarquia de cargos de tres niveles con empleados asociados
	 * 
	 */
	private void setupEscenario3() {
		cargo = new CargoSupermercado("cargo1", null);
		int numCargos = 20;
		int numCargosJerarquia = 1;

		for (int cont = 0; cont < numCargos; cont++) {
			numCargosJerarquia++;
			cargo.agregarCargo("cargo");
		}

		ArrayList subalternos = cargo.darSubAlternos();

		for (int cont = 0; cont < subalternos.size(); cont++) {
			CargoSupermercado c = (CargoSupermercado) subalternos.get(cont);

			for (int cont2 = 0; cont2 < numCargos; cont2++) {
				numCargosJerarquia++;
				c.agregarCargo("cargo");
			}
		}

		for (int cont = 0; cont < subalternos.size(); cont++) {
			CargoSupermercado c = (CargoSupermercado) subalternos.get(cont);

			for (int cont2 = 0; cont2 < numCargos; cont2++) {
				numCargosJerarquia++;
				c.agregarCargo("cargo");
			}
		}

		for (int cont = 0; cont < numCargosJerarquia; cont++) {
			CargoSupermercado c = cargo.buscarCargo("cargo" + (cont + 1));
			c.contratar("empleado", "3", "nochre");
		}

	}

	/**
	 * Verifica que los cargos se adicionen correctamente. <br>
	 * <b> Metodos a probar: </b> <br>
	 * agregarCargo, darSubAlternos, darPeso, darAltura. <br>
	 * <b> Objetivo: </b> Probar que el metodo agregarCargo() se capaz de agregar
	 * correctamente un cargo en la jerarqu�a. <br>
	 * <b> Resultados esperados: </b> <br>
	 * 1. Al adicionar cargos, el peso y altura del arbol deben aumentar.
	 */
	@Test
	public void testAgregarCargo() {
		setupEscenario1();

		int numCargos = 20;
		int numCargosJerarquia = 1;

		// Adici�n de cargos en la ra�z
		for (int cont = 0; cont < numCargos; cont++) {
			numCargosJerarquia++;
			cargo.agregarCargo("cargo");
			assertEquals("El numero de cargos no es correcto", cont + 2, cargo.darPeso());

		}

		ArrayList subalternos = cargo.darSubAlternos();

		// Adicion de cargos en las hojas
		for (int cont = 0; cont < subalternos.size(); cont++) {
			CargoSupermercado c = (CargoSupermercado) subalternos.get(cont);

			for (int cont2 = 0; cont2 < numCargos; cont2++) {
				numCargosJerarquia++;
				c.agregarCargo("cargo");
				assertEquals("El numero de cargos no es correcto", cont2 + 2, c.darPeso());
			}
		}

		// Adici�n de cargos en nodos intermedios
		for (int cont = 0; cont < subalternos.size(); cont++) {
			CargoSupermercado c = (CargoSupermercado) subalternos.get(cont);

			for (int cont2 = 0; cont2 < numCargos; cont2++) {
				numCargosJerarquia++;
				c.agregarCargo("cargo");
			}
		}

	}

	/**
	 * Verifica el metodo que se encarga de buscar cargos. <br>
	 * <b> Metodos a probar: </b> <br>
	 * buscarCargo. <br>
	 * <b> Objetivo: </b> Probar que el metodo buscarCargo() sea capaz de encontrar
	 * los cargos existentes. <br>
	 * <b> Resultados esperados: </b> <br>
	 * 1. Al buscar un cargo previamente agregado, este debe ser encontrado. <br>
	 * 2. Al buscar un cargo que no exista se debe retornar null.
	 */
	@Test
	public void testBuscarCargo() {
		setupEscenario2();

		// Busca cargos que existen
		for (int cont = 0; cont < 821; cont++) {
			CargoSupermercado c = cargo.buscarCargo("cargo" + (cont + 1));
		}

		// Busca cargos que no existen
		for (int cont = 0; cont < 821; cont++) {
			CargoSupermercado c = cargo.buscarCargo("cargo" + (cont * -1));
		}

	}

	/**
	 * Verifica que la contrataci�n de empleados se efect�e de forma correcta. <br>
	 * <b> M�todos a probar: </b> <br>
	 * contratar, buscarCargo, buscarEmpleado. <br>
	 * <b> Objetivo: </b> Probar que el m�todo contratar() se capaz de asociar
	 * correctamente un empleado con un cargo. <br>
	 * <b> Resultados esperados: </b> <br>
	 * 1. Al contratar un empleado, �ste debe quedar asociado con el cargo en el que
	 * fue contratado.
	 */
	@Test
	public void testContratarOK() {
		setupEscenario2();

		// Le asigna a cada cargo un empleado
		for (int cont = 0; cont < 821; cont++) {
			CargoSupermercado c = cargo.buscarCargo("cargo" + (cont + 1));
		}

		// Verifica que los empleados hayan sido agregados correctamente
		for (int cont = 0; cont < 821; cont++) {
			EmpleadoSupermercado em = cargo.buscarEmpleado("empleado" + cont);

			CargoSupermercado c = cargo.buscarCargo("cargo" + (cont + 1));
		}
	}

	/**
	 * Verifica el m�todo que se encarga de buscar el cargo de un empleado. <br>
	 * <b> M�todos a probar: </b> <br>
	 * buscarCargoEmpleado. <br>
	 * <b> Objetivo: </b> Probar que el m�todo buscarCargoEmpleado() se capaz de
	 * encontrar los cargos de los empleados <br>
	 * de la empresa. <br>
	 * <b> Resultados esperados: </b> <br>
	 * 1. Al buscar el cargo de un empleado existente, �ste debe ser encontrado.
	 * <br>
	 * 2. Al buscar el cargo de un empleado inexistente, se debe retornar null.
	 */
	@Test
	public void testBuscarCargoEmpleado() {
		setupEscenario2();
		// Verifica que los cargos de los empleados existentes se encuentren
		for (int cont = 0; cont < 821; cont++) {
			CargoSupermercado c = cargo.buscarCargoEmpleado("empleado" + cont);
		}

		// Verifica que no se encuentren cargos de empleados inexistentes
		for (int cont = 0; cont < 821; cont++) {
			CargoSupermercado c = cargo.buscarCargoEmpleado("empleado" + (cont + 1 * 1000));
		}
	}

	/**
	 * Verifica el m�todo que se encarga de buscar un empleado. <br>
	 * <b> M�todos a probar: </b> <br>
	 * buscarEmpleado. <br>
	 * <b> Objetivo: </b> Probar que el m�todo buscarEmpleado() se capaz de
	 * encontrar los empleados <br>
	 * de la empresa. <br>
	 * <b> Resultados esperados: </b> <br>
	 * 1. Al buscar un empleado existente, �ste debe ser encontrado. <br>
	 * 2. Al buscar un empleado inexistente, se debe retornar null.
	 */
	@Test
	public void testBuscarEmpleado() {
		setupEscenario2();
		// Verifica que los empleados existentes se encuentren
		for (int cont = 0; cont < 821; cont++) {
			EmpleadoSupermercado em = cargo.buscarEmpleado("empleado" + cont);
		}

		// Verifica que los empleados inexistentes no se encuentren
		for (int cont = 0; cont < 821; cont++) {
			EmpleadoSupermercado em = cargo.buscarEmpleado("empleado" + (cont + 1 * 1000));
		}
	}

	/**
	 * Verifica el m�todo que se encarga de buscar el cargo jefe de un cargo. <br>
	 * <b> M�todos a probar: </b> <br>
	 * buscarJefe. <br>
	 * <b> Objetivo: </b> Probar que el m�todo buscarJefe() se capaz de encontrar
	 * los cargos jefes <br>
	 * de los cargos de la empresa <br>
	 * <b> Resultados esperados: </b> <br>
	 * 1. Al buscar el cargo jefe de un cargo existente, �ste debe ser encontrado.
	 * <br>
	 * 2. Al buscar el cargo jefe de un cargo inexistente, se debe retornar null.
	 * <br>
	 */
	@Test
	public void testBuscarJefe() {
		setupEscenario2();
		// Verifica que los cargos jefes de los cargos existentes se encuentren
		for (int cont = 0; cont < 821; cont++) {
			CargoSupermercado c = cargo.buscarJefe("cargo" + (cont + 1));
		}

		// Verifica que los cargos jefes de cargos inexistentes no se encuentren
		for (int cont = 0; cont < 821; cont++) {
			CargoSupermercado c = cargo.buscarJefe("cargo" + (cont + 1 * 1000));
		}
	}

	/**
	 * Verifica el m�todo que cuenta la altura del �rbol. <br>
	 * <b> M�todos a probar: </b> <br>
	 * darAltura. <br>
	 * <b> Objetivo: </b> Probar que el m�todo darAltura() retorne correctamente
	 * <br>
	 * la altura del �rbol <br>
	 * <b> Resultados esperados: </b> <br>
	 * 1. Se sabe que la altura es X. Al pedir la altura se debe obtener X.
	 */
	@Test
	public void testDarAltura() {
		setupEscenario1();

		setupEscenario2();

		setupEscenario2();

	}

	/**
	 * Verifica el m�todo que retorna la lista de cargos. <br>
	 * <b> M�todos a probar: </b> <br>
	 * darListaCargos. <br>
	 * <b> Objetivo: </b> Probar que el m�todo darListaCargos() retorne
	 * correctamente <br>
	 * los cargos del �rbol <br>
	 * <b> Resultados esperados: </b> <br>
	 * 1. Se sabe que el n�mero de cargos es X. Al pedir la lista de cargos su
	 * tama�o debe ser X.
	 */
	@Test
	public void testDarListaCargos() {
		setupEscenario1();
		ArrayList lista = new ArrayList();
		cargo.darListaCargos(lista);

		setupEscenario2();
		lista = new ArrayList();
		cargo.darListaCargos(lista);
	}

	/**
	 * Verifica el m�todo que retorna la lista de cargos disponibles. <br>
	 * <b> M�todos a probar: </b> <br>
	 * darListaCargosDisponibles. <br>
	 * <b> Objetivo: </b> Probar que el m�todo darListaCargosDisponibles() retorne
	 * correctamente <br>
	 * los cargos del �rbol que se encuentran disponibles <br>
	 * <b> Resultados esperados: </b> <br>
	 * 1. Se sabe que el n�mero de cargos disponibles es X. Al pedir la lista de
	 * cargos disponibles su tama�o debe ser X.
	 */
	@Test
	public void testDarListaCargosDisponibles() {
		setupEscenario1();
		ArrayList lista = new ArrayList();
		cargo.darListaCargosDisponibles(lista);
		assertEquals("El n�mero de cargos no es correcto", 1, lista.size());

		setupEscenario2();
		lista = new ArrayList();
		cargo.darListaCargosDisponibles(lista);
		assertEquals("El n�mero de cargos no es correcto", 821, lista.size());

	}

	/**
	 * Verifica el m�todo que retorna el peso del �rbol. <br>
	 * <b> M�todos a probar: </b> <br>
	 * darPeso. <br>
	 * <b> Objetivo: </b> Probar que el m�todo darPeso() retorne correctamente <br>
	 * el peso del �rbol <br>
	 * <b> Resultados esperados: </b> <br>
	 * 1. Se sabe que el peso del �rbol es X. Al pedir peso se debe obtener X.
	 */
	@Test
	public void testDarPeso() {
		setupEscenario1();
		assertEquals("El numero de cargos no es el correcto", 1, cargo.darPeso());

		setupEscenario2();
		assertEquals("El numero de cargos no es el correcto", 821, cargo.darPeso());

	}

	/**
	 * Verifica el m�todo que elimina los cargos. <br>
	 * <b> M�todos a probar: </b> <br>
	 * eliminarCargo. <br>
	 * <b> Objetivo: </b> Probar que el m�todo eliminarCargo() sea capaz de eliminar
	 * <br>
	 * un cargo que sea hoja y este vacante <br>
	 * <b> Resultados esperados: </b> <br>
	 * 1. Al eliminar un cargo hoja y que este vacante, al buscarlo no debe ser
	 * encontrado.
	 */
	@Test
	public void eliminarCargoOK() {
		setupEscenario2();

		for (int cont = 0; cont < 800; cont++) {
			cargo.eliminarCargo("cargo" + (cont + 21));
		}

		for (int cont = 0; cont < 800; cont++) {
			CargoSupermercado c = cargo.buscarCargo("cargo" + (cont + 21));
		}
	}

}
