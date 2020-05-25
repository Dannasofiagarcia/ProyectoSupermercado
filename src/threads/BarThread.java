package threads;

import application.FarmaciaController;
import javafx.application.Platform;
import javafx.scene.shape.Rectangle;

public class BarThread extends Thread {

	private Rectangle element;
	private FarmaciaController gui;

	private boolean active;

	public BarThread(FarmaciaController gui) {
		this.gui = gui;
		active = true;
	}

	@Override
	public void run() {

		while (active == true) {

			Platform.runLater(new Thread() {
				@Override
				public void run() {
					gui.updateBar();
				}
			});

			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public void desactivate() {
		active = false;
	}

}
