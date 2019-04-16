import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Pgm{
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int[] tableauDeValeur = new int[10];
		
		for (int i = 0 ; i < 10 ; i++) {
		System.out.println("Saisir la valeur du graph : ");
		tableauDeValeur[i] = sc.nextInt();
		}
		
		JFrame window= new JFrame("Graph");
		window.setSize(680, 480);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		

		
		
		Histogramme histo = new Histogramme(tableauDeValeur);
		histo.setPreferredSize(new Dimension(680, 480));
		window.setContentPane(histo);
		window.pack();
		window.setVisible(true);
		sc.close();
		}
	}