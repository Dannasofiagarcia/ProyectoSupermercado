package farmacia;

import static org.junit.Assert.assertNotNull;
import modelo.*;


import org.junit.jupiter.api.Test;

class FechaDeLlegadaTest {

	private FechaDeLlegada fll;
	private void setUpScenary1() {}
	@Test
	public void testFechaDeLlegada(){
		
		setUpScenary1();
		
		try {
			fll = new FechaDeLlegada();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		assertNotNull("La fecha de llegada no ha podido ser creada, su valor es null", fll != null);
		
	}

}
