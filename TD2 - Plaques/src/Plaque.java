import javax.swing.*;
import java.awt.*;


public class Plaque extends JPanel {

	String num;
	String dep;
	
	public Plaque(int e,String num,String dep) {
		int epaisseur = e;
		this.num = num;
		this.dep = dep;
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int h = getHeight();
		int w = getWidth();

		g.setColor(Color.white);
		g.fillRect(0, 0, w, h);
		g.setColor(Color.blue);
		g.fillRect(10, 10, w / 10, h - 15);
		g.fillRect((w - w / 10) - 10, 10, w / 10, h - 15);
		g.setColor(Color.black);
		g.drawRect(2, 2, w - 3, h - 3);
		g.setColor(Color.white);
		g.drawRect((w - w / 10) - 5, 14, (w / 11) - 3, h / 3);
		g.drawOval(12, 15, w / 11, h / 3);
		g.setColor(Color.black);
		int fontSize = 100;
		Font f = new Font("Arial", Font.BOLD, fontSize);
		g.setFont(f);
		g.drawString(this.num, w / 6, h - h / 3);
		g.setColor(Color.white);
		int fontSize1 = 60;
		Font f2 = new Font("Arial", Font.BOLD, fontSize1);
		g.setFont(f2);
		g.drawString("F", w / 25, h - h / 6);
		g.setColor(Color.white);
		int fontSize2 = 60;
		Font f3 = new Font("Arial", Font.BOLD, fontSize2);
		g.setFont(f3);
		g.drawString(this.dep, w - w / 10, h - h / 6);
		
	}
}