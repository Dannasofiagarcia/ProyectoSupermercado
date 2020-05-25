package threads;

import application.ClienteFarmaciaController;
import application.FarmaciaController;
import application.InventarioController;
import javafx.application.Platform;
import modelo.*;
public class TiempoActualFarmaciaThread extends Thread  {

	public InventarioController iC;
	public ClienteFarmaciaController cfc;
	public FarmaciaController fc;
	
	private boolean activo;
	
	public TiempoActualFarmaciaThread(FarmaciaController fc) {
		this.fc=fc;
		activo=true;
	}
	
	public void run() {
		while(activo) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					fc.cambiarTiempo();
				}
			});
			try {
				sleep(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void desactivar() {
		activo=false;
	}
	
	
	
}