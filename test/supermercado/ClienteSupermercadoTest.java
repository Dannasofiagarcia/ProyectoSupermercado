package supermercado;

import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;

import excepciones.ClientesSupermercadoException;
import modelo.ClienteSupermercado;

public class ClienteSupermercadoTest {

	/**
	 * cliente 1
	 */
	private ClienteSupermercado cliente1;
	/**
	 * Nombre del cliente 1
	 */
	private String nombre1;
	/**
	 * Tel�fono del cliente 1
	 */
	private String telefono1;
	/**
	 * Direcci�n del cliente 1
	 */
	private String direccion1;
	/**
	 * Email del cliente 1
	 */
	private String email1;

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Crea un �rbol de clientes con la informaci�n b�sica de cliente
	 */
	@Test
	private void setupEscenario1() {
		nombre1 = "Marta";
		direccion1 = "direcci�n";
		telefono1 = "1234567";
		email1 = "marta@email.com";
		cliente1 = new ClienteSupermercado(nombre1, telefono1, direccion1, email1);
	}

	/**
	 * Crea un escenario donde al cliente inicial le inserta un cliente menor
	 */
	@Test
	private void setupEscenario2() {
		setupEscenario1();

		String nombre = "Alberto";
		String direccion = "direcci�n";
		String telefono = "1234567";
		String email = "alberto@email.com";
		ClienteSupermercado cliente2 = new ClienteSupermercado(nombre, telefono, direccion, email);

		try {
			cliente1.insertar(cliente2);
		} catch (ClientesSupermercadoException e) {
		}
	}

	/**
	 * Crea un escenario donde al cliente inicial le inserta un cliente menor,
	 * teniendo el �rbol ya dos nodos
	 */
	@Test
	private void setupEscenario3() {
		setupEscenario2();

		String nombre = "Paco";
		String direccion = "direcci�n";
		String telefono = "1234567";
		String email = "paco@email.com";
		ClienteSupermercado cliente3 = new ClienteSupermercado(nombre, telefono, direccion, email);

		try {
			cliente1.insertar(cliente3);
		} catch (ClientesSupermercadoException e) {
		}
	}

	/**
	 * Crea un escenario con un �rbol de clientes de 5 nodos teniendo el �rbol ya
	 * tres nodos
	 */
	@Test
	private void setupEscenario4() {
		setupEscenario3();

		String nombre = "Luis";
		String direccion = "direcci�n";
		String telefono = "1234567";
		String email = "luis@email.com";
		ClienteSupermercado cliente = new ClienteSupermercado(nombre, telefono, direccion, email);
		try {
			cliente1.insertar(cliente);
		} catch (ClientesSupermercadoException e) {
		}

		nombre = "Xiomara";
		direccion = "direcci�n";
		telefono = "1234567";
		email = "xiomara@email.com";
		cliente = new ClienteSupermercado(nombre, telefono, direccion, email);
		try {
			cliente1.insertar(cliente);
		} catch (ClientesSupermercadoException e) {
		}
	}

	/**
	 * Prueba que al insertar un cliente repetido se genere el error. <br>
	 * <b> M�todos a probar: </b> <br>
	 * insertar. <br>
	 * <b> Objetivo: </b> Probar que el m�todo insertar() arroje excepci�n cuando se
	 * trate de adicionar un cliente repetido. <br>
	 * <b> Resultados esperados: </b> <br>
	 * 1. Al insertar un cliente previamente existente en el �rbol se debe arrojar
	 * excepci�n.
	 */
	@Test
	public void testInsertarRepetido() {
		setupEscenario4();
		String direccion = "direcci�n";
		String telefono = "1234567";
		String email = "nombre@email.com";
		ClienteSupermercado clienteR = new ClienteSupermercado(nombre1, telefono, direccion, email);
		try {
			cliente1.insertar(clienteR);
		} catch (ClientesSupermercadoException e) {
		}
	}

	/**
	 * Prueba el m�todo de b�squeda de clientes. <br>
	 * <b> M�todos a probar: </b> <br>
	 * buscar. <br>
	 * <b> Objetivo: </b> probar que el m�todo buscar() busque correctamente los
	 * clientes dados. <br>
	 * <b> Resultados esperados: </b> <br>
	 * 1. Al buscar un cliente que no existe se debe retornar null. <br>
	 * 2. Al buscar un cliente que existe �ste debe ser retornado.
	 */
	@Test
	public void testBuscar() {
		setupEscenario4();

		ClienteSupermercado c;

		// Busca un nombre que no existe
		c = cliente1.buscar("Petronila");
		assertNull(c);

		// Busca uno que si existe
		c = cliente1.buscar("Marta");
		assertNull(c);

		// Busca a la ra�z del �rbol
		c = cliente1.buscar(nombre1);
		assertNull(c);

		// Busca al mayor
		c = cliente1.buscar("Xiomara");
		assertNull(c);

		// Busca el menor
		c = cliente1.buscar("Alberto");
		assertNull(c);
	}

	/**
	 * Prueba los algoritmos que retornan el mayor y el menor del �rbol <b> M�todos
	 * a probar: </b> <br>
	 * darMenor, darMayor. <br>
	 * <b> Objetivo: </b> Probar que los m�todos darMayor() y darMenor() retornen
	 * correctamente los clientes con los nombres que sean mayor y menor
	 * (lexicograficamente) respectivamente. <br>
	 * <b> Resultados esperados: </b> <br>
	 * 1. Al pedir el elemento menor del �rbol se debe retornar el nombre menor
	 * lexicograficamente. <br>
	 * 2. Al pedir el elemento mayor del �rbol se debe retornar el nombre mayor
	 * lexicograficamente.
	 */
	@Test
	public void testMayorYMenor() {
		setupEscenario4();

		// Busca el primero
		ClienteSupermercado c = cliente1.darMenor();

		// Busca el �ltimo
		c = cliente1.darMayor();

	}

	/**
	 * Prueba la eliminaci�n de un nodo que es hoja del �rbol. <br>
	 * <b> M�todos a probar: </b> <br>
	 * eliminar. <br>
	 * <b> Objetivo: </b> Probar que el m�todo eliminar() elimine correctamente un
	 * elemento del �rbol. <br>
	 * <b> Resultados esperados: </b> <br>
	 * 1. Al eliminar un elemento del �rbol, el peso del �rbol debe disminuir en 1.
	 * <br>
	 * 2. Al eliminar un elemento del �rbol al buscarlo se debe obtener null.
	 */
	@Test
	public void testEliminarHoja() {
		setupEscenario4();

		// Elimina al �ltimo
		ClienteSupermercado c = cliente1.darMayor();
		cliente1 = cliente1.eliminar(c.getNombre());

	}

	/**
	 * Prueba el recorrido inorden del �rbol de clientes. <br>
	 * <b> M�todos a probar: </b> <br>
	 * inorden. <br>
	 * <b> Objetivo: </b> Probar que el m�todo inorden() retorne correctamente el
	 * recorrido del �rbol en inorden. <br>
	 * <b> Resultados esperados: </b> <br>
	 * 1. Al pedir el recorrido en inorden del �rbol, los elementos deben ser
	 * retornados en orden ascendente.
	 */
//    public void testInorden( )
//    {
//        setupEscenario4( );
//        Collection lista = new ArrayList( );
//        cliente1.inorden( lista );
//        assertEquals( "[Alberto, Luis, Marta, Paco, Xiomara]", lista.toString( ) );
//    }

}
