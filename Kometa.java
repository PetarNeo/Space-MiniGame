package univerzum;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Kometa extends NebeskoTelo {
	
	private double ugao;
	
	public Kometa(int x, int y, int r,double u) {
		super(x, y, Color.GRAY, r);
		ugao = u;
	}
	
	public int dohvX(double ugao) {return (int)(Math.sin(ugao)*r); }
	public int dohvY(double ugao) {return (int)(Math.cos(ugao)*r);}

	@Override
	public void paint(Graphics g) {
		g.setColor(boja);
		
		int nizX[] = new int[5];
		int nizY[] = new int[5];
		for(int i = 0;i < 5; i++) {
			nizX[i] = dohvX(ugao) + x;
			nizY[i] = dohvY(ugao) + y;
			ugao += 2*Math.PI/5;
		}
		g.fillPolygon(nizX, nizY, 5);
	}
}
