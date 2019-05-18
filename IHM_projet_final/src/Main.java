import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
	public static void main(String[] args) {
		JFrame fenetre = new JFrame("GeoCreator");
		  fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  fenetre.setLayout(new BorderLayout());
		  
		  JPanel contenant = (JPanel) (fenetre.getContentPane());
		  VueGeoCreator crea = new VueGeoCreator();
		  crea.setPreferredSize(new Dimension(600, 550));
		  fenetre.getContentPane().add(crea, BorderLayout.CENTER); 
		  fenetre.pack();
		  fenetre.setVisible(true);
		  crea.setFocusable(true);
		  crea.requestFocus();
	}
}
