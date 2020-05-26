package threads;

import ui.FarmaciaController;

public class MusicThread extends Thread{
	
	private FarmaciaController fc;
	
	public MusicThread(FarmaciaController fc) {
		this.fc=fc;
	}
	
	@Override
	public void run() {
		fc.playMusic();
		try {
			sleep(195000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		run();
	}

}
