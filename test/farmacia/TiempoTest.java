package farmacia;

import static org.junit.Assert.assertNotNull;
import modelo.*;

import org.junit.jupiter.api.Test;

class TiempoTest {
	private Tiempo t;
	private void setUpScenary1() {}
	@Test
	public void testTime(){
		
		setUpScenary1();
		
		try {
			t = new Tiempo();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		assertNotNull("El tiempo no pudo ser creado, su valor es nulo", t != null);
		
	}

}
