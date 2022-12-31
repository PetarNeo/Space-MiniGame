package univerzum;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Objekat {
	
	protected  int x, y;
	protected Color boja;
	
	public Objekat(int x, int y, Color b) {
		this.x = x;
		this.y = y;
		boja = b;
	}
	
	public void pomerajX(int px) {x+=px;}
	public void pomerajY(int py) {y+=py;}
	public double rastojanje(int x, int y) {
		return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y-y, 2));
	}
	public abstract void paint(Graphics g);
}
