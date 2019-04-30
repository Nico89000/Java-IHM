import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")

public class Scribble extends JPanel implements MouseListener, MouseMotionListener {
	/** Classe interne Trait. */
	private static class Trait {
		private static int indice = 0;
		private int debx;
		private int deby;
		private int finx;
		private int finy;
		private Color couleur;
		
		public Trait(int x0, int y0, int x1, int y1, Color c) {
			Trait.indice++;
			this.debx = x0;
			this.deby = y0;
			this.finx = x1;
			this.finy = y1;
			this.couleur = c;
		}
		
		public static int getIndice(){
			return Trait.indice;
		}
		
		public static void setIndice (int i){
			Trait.indice = 0;
		}

		public int getDebx() {
			return debx;
		}

		public void setDebx(int debx) {
			this.debx = debx;
		}

		public int getDeby() {
			return deby;
		}

		public void setDeby(int deby) {
			this.deby = deby;
		}

		public int getFinx() {
			return finx;
		}

		public void setFinx(int finx) {
			this.finx = finx;
		}

		public int getFiny() {
			return finy;
		}

		public void setFiny(int finy) {
			this.finy = finy;
		}

		public Color getCouleur() {
			return couleur;
		}

		public void setCouleur(Color couleur) {
			this.couleur = couleur;
		}

	}
	
	/** Méthodes des interfaces MouseListener et MouseMotionListener non utilisées. */
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}

	/** Abscisse d'un clic de souris. */
	private int last_x;

	/** Ordonnée d'un clic de souris. */
	private int last_y;

	/** Couleur du trait à dessiner. */
	private Color couleur_trait;

	/** Contexte graphique. */
	private Graphics gc;
	
	/** Dimension maximum du tableau de traits. */
	private static int MAX_DIM = 10000;
	
	/** Tableau des traits. */
	private Trait tableau_traits[];

	/**
	 * Constructeur de la classe.
	 */
	public Scribble(){
		this.couleur_trait = Color.BLACK;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.tableau_traits = new Trait[Scribble.MAX_DIM];
	}

	/**
	 * Méthode débutant le trace à main levée.
	 * @param e événement clic de souris.
	 */
	public void mousePressed(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			last_x=e.getX();
			last_y=e.getY();
		} else if (SwingUtilities.isRightMouseButton(e)) {
			((JComponent)(e.getSource())).removeMouseListener(this);
			((JComponent)(e.getSource())).removeMouseMotionListener(this);
			Trait.setIndice(0);
			((JComponent)(e.getSource())).repaint();
		} else if (SwingUtilities.isMiddleMouseButton(e)) {
			Trait.setIndice(0);
			((JComponent)(e.getSource())).repaint();
		}
	}

	/**
	 * Méthode effectuant le trace à main levée.
	 * 
	 * @param e événement clic de souris.
	 */
	public void mouseDragged(MouseEvent e) {
		gc = this.getGraphics();
		gc.setColor(this.couleur_trait);
		gc.drawLine(this.last_x, this.last_y, e.getX(), e.getY());
		tableau_traits[Trait.getIndice()] = new Trait(this.last_x, this.last_y,
				                                      e.getX(), e.getY(),
				                                      this.couleur_trait);
		last_x=e.getX();
		last_y=e.getY();
	}

	/**
	 * Méthode paintComponent.
	 * 
	 * @param g Contexte graphique.
	 */
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for (int i=0; i < Trait.getIndice()-1; i++){
			g.setColor(tableau_traits[i].getCouleur());
			g.drawLine(tableau_traits[i].getDebx(), tableau_traits[i].getDeby(),
					   tableau_traits[i].getFinx(), tableau_traits[i].getFiny());
		}
	}

	/**
	 * Méthode pour changer la couleur su trait.
	 * 
	 * @param c nouvelle couleur du trait.
	 */
	public void setColor(Color c){
		this.couleur_trait = c;
	}
}

