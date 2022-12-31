package univerzum;

import java.util.Random;

public class Generator implements Runnable {
	
	private Svemir Gsvemir = new Svemir();
	private Thread nit;
	private boolean zaustavljeno = false;
	
	public Generator(Svemir s) {
		Gsvemir = s;
	}
	public void kreni() {
		nit = new Thread(this);
		nit.start();
	}
	public void stani() {if(nit!=null)nit.interrupt();}
	public void zaustavi() {zaustavljeno = true;}
	public void nastavi() {zaustavljeno = false;}
	@Override
	public void run() {
		try {
			while(!Thread.interrupted() && Gsvemir.kraj == false) {
				if(zaustavljeno == false) {
					Thread.sleep(900);
					int i = new Random().nextInt(100);
					if(i < 75) {
						double ugao = new Random().nextDouble()*2*Math.PI;
						Kometa k = new Kometa(new Random().nextInt(200),0,10 + new Random().nextInt(10),ugao);
						Gsvemir.dodajTelo(k);
					}else {
						Planeta p = new Planeta(new Random().nextInt(200),0,10 + new Random().nextInt(20));
						Gsvemir.dodajTelo(p);
					}
				}
			}
		}catch(InterruptedException e) {}
	}
}
