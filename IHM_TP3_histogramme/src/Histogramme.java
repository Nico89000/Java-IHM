import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Random;

public class Histogramme extends JPanel {

	public int[] attTab;
	public final int taillePixel = 10;

	public Histogramme(int[] tab) {
		this.attTab = tab;
	}

	Random rand = new Random();
	float re = rand.nextFloat();
	float gr = rand.nextFloat();
	float bl = rand.nextFloat();
	Color randomColor = new Color(re, gr, bl);
	int valeur = 55;
	int offset = 0;

	public void paintComponent(Graphics g) {
		// Appel au super
		super.paintComponent(g);
		// Affectation de hauteur et largeur
		int h = getHeight();
		int w = getWidth();


		
		for (int i = 0; i < 10; i++) {
			// G�n�ration de la couleur al�atoire
			re = rand.nextFloat();
			gr = rand.nextFloat();
			bl = rand.nextFloat();
			randomColor = new Color(re, gr, bl);
			g.setColor(randomColor);
			// G�n�ration du rectangle, x de d�part, y de d�part, largeur fixe, hauteur avec sa valeur stock�e dans un tableau
			g.fillRect(w / 8 + offset, (h / 2 + h / 5)-this.attTab[i]*taillePixel, h / 12, this.attTab[i]*taillePixel);
			// G�n�ration 
			g.setColor(Color.black);
			g.drawString( Integer.toString(attTab[i]),w / 8 + offset + w/50, (h / 2 + h / 5)-this.attTab[i]*taillePixel);
			offset = offset + h / 12;
		}

		g.setColor(Color.black);
		// Ligne des abscisses
		g.drawLine(w / 8, (h / 2 + h / 5), w-w/8, h / 2 + h / 5);
		// Ligne des ordonn�es, x;y puis x;y
		g.drawLine(w / 8, (h / 2 + h / 5), w / 8, h/8);


	}
}
