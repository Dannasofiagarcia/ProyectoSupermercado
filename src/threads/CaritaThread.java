package threads;

import application.FarmaciaController;

public class CaritaThread extends Thread {

	private FarmaciaController fc;

	public CaritaThread(FarmaciaController fc) {
		this.fc = fc;
	}

	/**
	 * Este metodo permite que la Carita se pinte infinitamente de diferentes
	 * colores
	 */

	@Override
	public void run() {
		while (true) {
			try {
				fc.colorearAmarillo();
				Thread.sleep(1000);
				fc.colorearVerde();
				Thread.sleep(1000);
				fc.colorearVioleta();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
