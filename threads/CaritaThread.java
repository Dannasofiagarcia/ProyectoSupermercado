package threads;

import ui.FarmaciaController;

public class CaritaThread extends Thread {

	private FarmaciaController fc;

	public CaritaThread(FarmaciaController fc) {
		this.fc = fc;
	}

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
