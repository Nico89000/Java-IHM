import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Pgm{
	
	public static void main(String[] args)
	{

		int newx, newy;
		Scanner sc = new Scanner(System.in);
		
		JFrame window= new JFrame("Graph");
		window.setSize(600, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Chess echec = new Chess(0,0);
		echec.setPreferredSize(new Dimension(600, 600));
		window.setContentPane(echec);
		window.pack();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
		Point newLocation = new Point(middle.x + (window.getWidth() / 2), 
		                              middle.y - (window.getHeight() / 2));
		window.setLocation(newLocation);
		
		window.setVisible(true);
		
		while(true) {
			System.out.println("Saisissez x, puis y");
			newx= sc.nextInt();
			newy=sc.nextInt();
			echec.modify(newx, newy);
			echec.repaint();
		}
		
		}
	}