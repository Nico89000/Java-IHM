import java.awt.*;
import javax.swing.*;


public class Fenetre {

	public static void main(String[] args) {
		JFrame fenetre=new JFrame("Clique-moi");
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CliqueMoi dessin=new CliqueMoi();
		dessin.setPreferredSize(new Dimension(460,460));
		fenetre.setContentPane(dessin);
		fenetre.pack();
		fenetre.setVisible (true);
	}
}
