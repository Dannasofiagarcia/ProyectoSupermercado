package farmacia;

import static org.junit.Assert.assertNotNull;
import modelo.*;

import org.junit.jupiter.api.Test;

class ProductoTest {

	private Producto p;
	private void setUpScenary1() {}
	@Test
	public void testProducto(){
		
		setUpScenary1();
		
		try {
			p = new Producto(new FechaDeLlegada(), new Tiempo(), "Bayer", "BY005", "Dolex");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		assertNotNull("El producto no pudo ser creado, su valor es null", p != null);
		
	}

}
