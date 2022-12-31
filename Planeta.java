package univerzum;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Planeta extends NebeskoTelo {
	
	private boolean prsten = false;
	
	public Planeta(int x, int y, int r) {
		super(x, y, Color.WHITE, r);
		int i = new Random().nextInt(100);
		if(i<25)boja = Color.GREEN;
		if(i>=25 && i < 50)boja = Color.BLUE;
		if(i>= 50 && i < 75)boja = Color.YELLOW;
		if(i>= 75 && i <= 100)boja = Color.RED;
		int j = new Random().nextInt(100);
		if(j < 25)prsten = true;
		else prsten=false;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(boja);
		g.fillOval(x, y, r, r);
		if(prsten == true) g.drawOval(x-r/2, y-r/2, 2*r, 2*r);
	}
}
