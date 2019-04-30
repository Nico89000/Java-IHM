import java.awt.*;

import javax.swing.*;
public class Principale {
	
	public static void main(String[] args) {
		JFrame fenetre=new JFrame("Traceur Forme");
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Scribble tf = new Scribble();
		tf.setPreferredSize(new Dimension(460,460));
		ControleCouleur cl = new ControleCouleur();
		tf.addKeyListener(cl);
		fenetre.setContentPane(tf);
		fenetre.pack();
		fenetre.setVisible (true);
		tf.requestFocus();
	}
}
