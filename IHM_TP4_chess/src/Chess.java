import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Chess extends JPanel {

	int x,y;
	public Chess(int a, int b) {
		x=a;
		y=b;
	}
	
	public void modify(int a, int b) {
		x=(a-1)*600/8;
		y=(b-1)*600/8;
	}

	public void paintComponent(Graphics g) {
		// Appel au super
		super.paintComponent(g);
		int h = getHeight();
		int w = getWidth();


		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 4; j++) {
				if ((i) % 2 == 0)
					g.fillRect(i * (w / 8), j * 2 * (w / 8), w / 8, w / 8);
			}
		}

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 4; j++) {
				if ((i) % 2 == 0)
					g.fillRect(i * (w / 8) + (w / 8), j * 2 * (w / 8) + (w / 8), w / 8, w / 8);
			}
		}
		g.setColor(Color.yellow);
		g.fillOval(this.x, this.y, w / 8, w / 8);

	}
	
	
	
	
}
