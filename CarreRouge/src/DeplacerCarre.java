import javax.swing.*;


public class DeplacerCarre {

	public static void main(String[] args) {
		
		JFrame fenetre=new JFrame("Carré Rouge");
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Carre dessin=new Carre(640, 480); 
		fenetre.setContentPane(dessin);
		fenetre.pack();  
		fenetre.setResizable(false);
		fenetre.setVisible(true);
		dessin.requestFocus();
	}
}
