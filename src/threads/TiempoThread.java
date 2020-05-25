package threads;

import application.ControllerSupermercado;
import javafx.application.Platform;

public class TiempoThread extends Thread {

	private ControllerSupermercado controller;

	private boolean active;

	public TiempoThread(ControllerSupermercado controller) {
		this.controller = controller;
		active = true;

	}

	public void run() {
		while (active) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					controller.cambiarTiempo();
				}
			});
			try {
				sleep(1000);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void deactivate() {
		active = false;
	}
}
