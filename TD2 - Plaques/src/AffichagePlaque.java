import javax.swing.*;
import java.awt.*;
import java.util.*;

public class AffichagePlaque {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez entrer une plaque d'immatriculation:");
		String str = sc.nextLine();
		System.out.println("Vous avez saisi :" + str);
		
		Scanner sc2 = new Scanner(System.in);
		System.out.println("Veuillez entrer un numero de departement:");
		String dep = sc.nextLine();
		System.out.println("Vous avez saisi :" + dep);
		
		
		
		int e = 1;
		JFrame fenetre = new JFrame("plaque d'immatriculation");
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Plaque p = new Plaque(1,str,dep);
		p.setPreferredSize(new Dimension(800, 200));
		fenetre.setContentPane(p);
		fenetre.pack();
		fenetre.setVisible(true);

	}

}