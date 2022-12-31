package univerzum;

import java.awt.Color;

public abstract class NebeskoTelo extends Objekat {
	
	protected int r;
	
	public NebeskoTelo(int x, int y, Color b,int r) {
		super(x, y, b);
		this.r = r;
	}
}
