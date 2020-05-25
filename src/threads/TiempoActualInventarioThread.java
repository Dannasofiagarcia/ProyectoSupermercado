package threads;

import application.ClienteFarmaciaController;
import application.FarmaciaController;
import application.InventarioController;
import javafx.application.Platform;

public class TiempoActualInventarioThread extends Thread {

	public InventarioController iC;
	public ClienteFarmaciaController cfc;
	public FarmaciaController fc;

	private boolean activo;

	public TiempoActualInventarioThread(InventarioController iC) {
		this.iC = iC;
		activo = true;
	}

	public TiempoActualInventarioThread(ClienteFarmaciaController cfc) {
		this.cfc = cfc;
		activo = true;

	}

	public TiempoActualInventarioThread(FarmaciaController fc) {
		this.fc = fc;
		activo = true;
	}

	public int tiempo() {
		int segundos = 0;
		segundos = iC.segundos() + segundos;
		return segundos;
	}

	public void run() {
		while (true) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					iC.cambiarTiempo();
				}
			});
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void desactivar() {
		activo = false;
	}

}
