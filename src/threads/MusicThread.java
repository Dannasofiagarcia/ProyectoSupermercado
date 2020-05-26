package threads;

import application.FarmaciaController;

public class MusicThread extends Thread {

	private FarmaciaController fc;

	public MusicThread(FarmaciaController fc) {
		this.fc = fc;
	}

	/**
	 * este metodo permite que se escuche musica en el programa
	 */
	@Override
	public void run() {
		while (true) {
			fc.playMusic();
//		try {
//			sleep(10);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		run();
		}
	}

}
