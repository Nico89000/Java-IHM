import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Carre extends JPanel {
	public static final int COTE = 10;

	// Position du carré
	private int posX;
	private int posY;

	public Carre(int dimX, int dimY) {
		this.setPreferredSize(new Dimension(dimX, dimY));
		this.posX = dimX / 2;
		this.posY = dimY / 2;
		// Création de l'auditeur
		ControleMouvement kb = new ControleMouvement();
		this.addKeyListener(kb);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

	    g.setColor(Color.RED);
	    g.fillRect(this.getPosX(), this.getPosY(), Carre.COTE, Carre.COTE);
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
}
