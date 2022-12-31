package univerzum;

import java.awt.*;
import java.awt.event.*;

public class Simulator extends Frame {
	
	private Svemir svemir = new Svemir();
	private Generator generator = new Generator(svemir);
	private Panel donjiPanel = new Panel();
	private Button pokreni = new Button("Pokreni!");
	private Button zaustavi = new Button("Zaustavi");
	private Button nastavi = new Button("Nastavi");
	Label labela = new Label();

	public Simulator() {
		setBounds(700,500,200,400);
		setResizable(true);
		
		popuni();
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				generator.stani();
				svemir.stani();
				dispose();
			}
		});
		setVisible(true);
	}
	private void popuni() {
		svemir.postaviOwnera(this);
		pokreni.addActionListener((ae)->{
			pokreni.setEnabled(false);
			nastavi.setEnabled(false);
			zaustavi.setEnabled(true);
			generator.kreni();
			svemir.kreni(true);
		});
		zaustavi.addActionListener((ae)->{
			zaustavi.setEnabled(false);
			nastavi.setEnabled(true);
			generator.zaustavi();
			svemir.zaustavi();
		});
		nastavi.addActionListener((ae)->{
			nastavi.setEnabled(false);
			zaustavi.setEnabled(true);
			svemir.nastavi();
			generator.nastavi();
		});
		zaustavi.setEnabled(false);
		nastavi.setEnabled(false);
		
		labela.setAlignment(Label.CENTER);
		labela.setForeground(Color.WHITE);
		labela.setBackground(Color.BLACK);
		add(labela,BorderLayout.NORTH);
		
		donjiPanel.setLayout(new GridLayout(1,3));
		donjiPanel.add(pokreni);
		donjiPanel.add(zaustavi);
		donjiPanel.add(nastavi);
		add(donjiPanel,BorderLayout.SOUTH);
		add(svemir);
	}
	public static void main(String[] args) {
		new Simulator();
	}
}
