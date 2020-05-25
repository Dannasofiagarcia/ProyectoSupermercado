package threads;

import application.ClienteFarmaciaController;
import application.FarmaciaController;
import application.InventarioController;
import javafx.application.Platform;
import modelo.*;

public class TiempoActualClienteThread extends Thread {

	public InventarioController iC;
	public ClienteFarmaciaController cfc;
	public FarmaciaController fc;

	private boolean activo;

	public TiempoActualClienteThread(InventarioController iC) {
		this.iC = iC;
		activo = true;
	}

	public TiempoActualClienteThread(ClienteFarmaciaController cfc) {
		this.cfc = cfc;
		activo = true;

	}

	public TiempoActualClienteThread(FarmaciaController fc) {
		this.fc = fc;
		activo = true;
	}
/**
 * este método permite cambiar el tiempo del reloj
 */
	public void run() {
		while (activo) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					cfc.cambiarTiempo();
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
