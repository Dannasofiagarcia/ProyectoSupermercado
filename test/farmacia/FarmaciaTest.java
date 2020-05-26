package farmacia;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import modelo.ClienteFarmacia;
import modelo.Farmacia;

class FarmaciaTest {

	private Farmacia lafarmacia;
	private ClienteFarmacia p;

	public void setUpEscenary1() {
		lafarmacia = new Farmacia();
		p = new ClienteFarmacia(1, "Danna", "garcia", "garcia@gmail.com", "female", "Colombia", null, "march");
	}

	public void setUpEscenary2() {
		lafarmacia = new Farmacia();
	}

	public void setUpEscenary3() {
		lafarmacia = new Farmacia();
		for (int i = 0; i < 4; i++) {
			ClienteFarmacia a = new ClienteFarmacia(i, "Danna", "garcia", "garcia@gmail.com", "female", "Colombia",
					null, "march");
			lafarmacia.agregarClienteAlArbol(a);
		}
	}

	@Test
	void testAddParticipantIntoTree() {
		setUpEscenary1();
		lafarmacia.agregarClienteAlArbol(p);
		assertTrue("The method do not add correct", lafarmacia.getRaiz() != null);
		assertTrue("The method do not add correct id", lafarmacia.getRaiz().getId() == 1);
		assertTrue("The method do not add correct first name", lafarmacia.getRaiz().getNombre().equals("Danna"));
		assertTrue("The method do not add correct last name", lafarmacia.getRaiz().getApellido().equals("garcia"));
		assertTrue("The method do not add correct email", lafarmacia.getRaiz().getEmail().equals("garcia@gmail.com"));
		assertTrue("The method do not add correct gender", lafarmacia.getRaiz().getGenero().equals("female"));
		assertTrue("The method do not add correct country", lafarmacia.getRaiz().getPais().equals("Colombia"));
		assertTrue("The method do not add correct birth", lafarmacia.getRaiz().getCumpleanios().equals("march"));
	}

	@Test
	void testBuscarEscogidos() {
		setUpEscenary1();
		lafarmacia.setRaiz(p);
		ClienteFarmacia test = lafarmacia.buscarClientesEspeciales(p.getId());
		assertTrue("The method fail", test != null);
	}

	@Test
	void testBuscarEscogidos1() {
		setUpEscenary1();
		ClienteFarmacia test = lafarmacia.buscarClientesEspeciales(p.getId());
		assertTrue("The method fail", test == null);
	}

	/**
	 * @Test void testescogerClientesAleatoriamente() { setUpEscenary3();
	 *       lafarmacia.escogerClientesAleatorios(4); ClienteFarmacia
	 *       t=lafarmacia.getPrimero(); for(int i=0;i<2;i++) { assertTrue("The
	 *       method do not add the 50% of the tree",t!=null); t.getSiguiente(); }
	 * 
	 *       }
	 */

	private Farmacia s;
	private int numeroDeProductos;

	private void setUpScenary2() {
		numeroDeProductos = 10;
	}

	private void setUpScenary3() {
		int n = 3;
		s = new Farmacia(n);
		s.generarInformacionDeLosProductosDeLaFarmacia();
	}

	@Test
	public void testgenerarInformacionDeLosProductosDeLaFarmacia1() {
		setUpScenary2();
		try {
			s = new Farmacia(numeroDeProductos);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		assertNotNull("The Farmacia Couldn't be created, its value is null", s != null);
	}

	@Test
	public void testgenerarInformacionDeLosProductosDeLaFarmacia() {
		setUpScenary2();
		try {
			s = new Farmacia(numeroDeProductos);
			s.generarInformacionDeLosProductosDeLaFarmacia();
			assertNotNull("La farmacia no pudo ser creada", s != null);
			assertNotNull("Los productos no pudieron ser creados", s.getPrimerasMarcas() != null);
			for (int i = 0; i < s.obtenerLosProductosComoArray().length; i++) {
				assertNotNull("La fecha de llegada no pudo ser creada",
						s.obtenerLosProductosComoArray()[0].getFechaDeLlegada() != null);
				assertNotNull("El tiempo no pudo ser creado", s.obtenerLosProductosComoArray()[0].getHorario() != null);
				assertTrue("El nombre de la marca no pudo ser creado",
						s.obtenerLosProductosComoArray()[0].getNombreDeLaMarca() != " ");
				assertTrue("El codigo no esta vacio",
						s.obtenerLosProductosComoArray()[0].getCodigoDelProducto() != " ");
				assertTrue("El nombre del producto no esta vacio",
						s.obtenerLosProductosComoArray()[0].getNombreDelProducto() != " ");
				assertTrue("El tipo no esta vacio", s.obtenerLosProductosComoArray()[0].getTipo() != " ");
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testOrdenamientoPorTipo() {
		setUpScenary3();
		try {
			System.out.println("Ordenamieto por tipo");
			s.ordenamientoDeInsercionPorElTipoDeProducto();
			System.out.println(s.productosEnToString());
			System.out.println("Despues");
			System.out.println(s.productosEnToString());
			assertTrue("El tipo de producto no esta vacio",
					s.obtenerLosProductosComoArray()[0].getTipo()
							.compareTo(s.obtenerLosProductosComoArray()[1].getTipo()) < 0
							|| s.obtenerLosProductosComoArray()[0].getTipo()
									.compareTo(s.obtenerLosProductosComoArray()[2].getTipo()) < 0
							|| s.obtenerLosProductosComoArray()[1].getTipo()
									.compareTo(s.obtenerLosProductosComoArray()[2].getTipo()) < 0);

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testOrdenamientoPorNombreDeLaMarca() {
		setUpScenary3();
		try {
			System.out.println("Sorted by Nombre de la marca");
			s.ordenarPorElNombreDeLaMarca();
			System.out.println(s.productosEnToString());
			assertTrue("El nombre de la marca no esta vacio",
					s.obtenerLosProductosComoArray()[0].getNombreDeLaMarca()
							.compareTo(s.obtenerLosProductosComoArray()[1].getNombreDeLaMarca()) < 0
							|| s.obtenerLosProductosComoArray()[0].getNombreDeLaMarca()
									.compareTo(s.obtenerLosProductosComoArray()[2].getNombreDeLaMarca()) < 0
							|| s.obtenerLosProductosComoArray()[1].getNombreDeLaMarca()
									.compareTo(s.obtenerLosProductosComoArray()[2].getNombreDeLaMarca()) < 0);

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void OrdenamientoPorNombreDelProducto() {
		setUpScenary3();
		try {
			System.out.println("ordenamiento por nombre de la marca");
			s.ordenarBurbujaPorElNombreDelProducto();
			System.out.println(s.productosEnToString());
			assertTrue("El nombre de la marca no esta vacio",
					s.obtenerLosProductosComoArray()[0].getNombreDelProducto()
							.compareTo(s.obtenerLosProductosComoArray()[1].getNombreDelProducto()) < 0
							|| s.obtenerLosProductosComoArray()[0].getNombreDelProducto()
									.compareTo(s.obtenerLosProductosComoArray()[2].getNombreDelProducto()) < 0
							|| s.obtenerLosProductosComoArray()[1].getNombreDelProducto()
									.compareTo(s.obtenerLosProductosComoArray()[2].getNombreDelProducto()) < 0);

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void OrenamientoPorCodigo() {
		setUpScenary3();
		try {
			System.out.println("Ordenamiento por nombre de la marca");
			s.ordenamientoDeSeleccionPorElCodigo();
			System.out.println(s.productosEnToString());
			assertTrue("The Id Airline is not empty",
					s.obtenerLosProductosComoArray()[0].getCodigoDelProducto()
							.compareTo(s.obtenerLosProductosComoArray()[1].getNombreDeLaMarca()) < 0
							|| s.obtenerLosProductosComoArray()[0].getCodigoDelProducto()
									.compareTo(s.obtenerLosProductosComoArray()[2].getCodigoDelProducto()) < 0
							|| s.obtenerLosProductosComoArray()[1].getCodigoDelProducto()
									.compareTo(s.obtenerLosProductosComoArray()[2].getCodigoDelProducto()) < 0);

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void ordenamientoPorFechaDeLlegada() {
		setUpScenary3();
		try {
			System.out.println("Ordenamiento por fecha de llegada");
			s.ordenamientoDeInsercionPorLaFechaDeLlegada();
			System.out.println(s.productosEnToString());
			assertTrue("La fecha de llegada no esta vacia",
					s.obtenerLosProductosComoArray()[0].getFechaDeLlegada()
							.compareTo(s.obtenerLosProductosComoArray()[1].getFechaDeLlegada()) < 0
							|| s.obtenerLosProductosComoArray()[0].getFechaDeLlegada()
									.compareTo(s.obtenerLosProductosComoArray()[2].getFechaDeLlegada()) < 0
							|| s.obtenerLosProductosComoArray()[1].getFechaDeLlegada()
									.compareTo(s.obtenerLosProductosComoArray()[2].getFechaDeLlegada()) < 0);

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void ordenamientoPorTiempo() {
		setUpScenary3();
		try {
			System.out.println("Ordenamiento Por Tiempo");
			s.ordenamientoDeInsercionPorElTipoDeProducto();
			System.out.println(s.productosEnToString());
			assertTrue("El tiempo no esta vacio",
					s.obtenerLosProductosComoArray()[0].getHorario()
							.compareTo(s.obtenerLosProductosComoArray()[1].getHorario()) < 0
							|| s.obtenerLosProductosComoArray()[0].getHorario()
									.compareTo(s.obtenerLosProductosComoArray()[2].getHorario()) < 0
							|| s.obtenerLosProductosComoArray()[1].getHorario()
									.compareTo(s.obtenerLosProductosComoArray()[2].getHorario()) < 0);

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

}
