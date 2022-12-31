package univerzum;

import java.awt.Color;
import java.awt.Graphics;

public class Letelica extends Objekat {
	
	private int sirina;
	private int visina;
	
	public Letelica(int x, int y,int sirina,int duzina) {
		super(x, y, Color.CYAN);
		this.sirina = sirina;
		this.visina = duzina;
	}
	public boolean preklapanje(NebeskoTelo nt) {
		double rast = this.rastojanje(nt.x, nt.y);
		if(rast < nt.r + visina/2 ) return true;
		else return false;
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(boja);
		int nizX [] = {x,x-sirina/2,x+sirina/2};
		int nizY [] = {y-visina/2, y+visina/2,y+visina/2};
		g.fillPolygon(nizX, nizY, 3);
	}

}
