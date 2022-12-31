package univerzum;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Svemir extends Canvas implements Runnable{
	
	private ArrayList<NebeskoTelo> nebeskaTela = new ArrayList<>();
	private ArrayList<NebeskoTelo> vecudarena = new ArrayList<>();
	private Thread thread;
	private boolean kreni = false;
	private Letelica letelica = new Letelica(92,300,40,16);
	private int istrazenost = 0;
	boolean zaustavljeno = false;
	boolean kraj = false;
	private Simulator owner;
	
	public Svemir() {
		setBackground(Color.BLACK);
		
		addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if(zaustavljeno == false) {
					char c = Character.toUpperCase(e.getKeyChar());
					if(c == 'A') {
						letelica.pomerajX(-10);
						if(letelica.x <= 0)letelica.x = 0;
					}else if(c == 'D') {
						letelica.pomerajX(10);
						if(letelica.x >=175)letelica.x = 175;
					}
				}				
			}
		});
	}
	public void dodajTelo(NebeskoTelo nt) {nebeskaTela.add(nt);}
	public void postaviOwnera(Simulator s) {owner = s;}
	
	public void paint(Graphics g) {
		kreni(kreni);
		sudar();
		if(zaustavljeno == true || kraj == true) pauzirano();
	}
	private void crtajIstrazenost() {
		Graphics g = getGraphics();
		Color prev = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("Istrazenost svemira:"+istrazenost, 35, 13);
		g.setColor(prev);
	}
	private void pauzirano() {
		Graphics g = getGraphics();
		for(int i = 0;i < nebeskaTela.size();i++) {
			nebeskaTela.get(i).paint(g);
		}
		letelica.paint(g);
	}
	private void sudar() {
		for(int i = 0;i < nebeskaTela.size();i++) {
			if(letelica.preklapanje(nebeskaTela.get(i))) {
				if(!vecudarena.contains(nebeskaTela.get(i))){
					if(nebeskaTela.get(i) instanceof Kometa) {
						kraj = true;
					}else if(nebeskaTela.get(i) instanceof Planeta) {
						istrazenost += 100;
					}
					vecudarena.add(nebeskaTela.get(i));
				}
			}
		}
	}
	
	public void kreni(boolean t) {
		if(thread==null && t == true) {
			thread = new Thread(this);
			thread.start();
			kreni = true;
		}
	}
	public void stani() {if(thread != null)thread.interrupt();}
	public void zaustavi() {zaustavljeno = true;}
	public void nastavi() {zaustavljeno = false;}
	
	@Override
	public void run() {
		Graphics g = getGraphics();
		try {
			while(!Thread.interrupted()) {
				if(zaustavljeno == false && kraj == false) {
					owner.labela.setText("Istrazenost svemira:"+istrazenost);
					for(int i = 0;i < nebeskaTela.size();i++) {
						nebeskaTela.get(i).paint(g);
						nebeskaTela.get(i).pomerajY(5);
					}
					letelica.paint(g);
					istrazenost++;
					Thread.sleep(100);
					repaint();
				}
			}
		}catch(InterruptedException e) {}
	}
}
