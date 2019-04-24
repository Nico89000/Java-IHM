import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;

public class CliqueMoi extends JPanel{

	private int xballe;
	private int yballe;

	public CliqueMoi(){

		this.xballe = (int)(Math.random()* 400)+30;
		this.yballe = (int)(Math.random()* 400)+30;


		MouseMotionListener mml=new MouseMotionListener() {
			public void mouseMoved(MouseEvent e) {
				int posx = e.getX();
				int posy = e.getY();

				if((posx >= xballe) && (posx < xballe + 30) &&
				   (posy >= yballe) && (posy < yballe + 30)) {

					xballe = (int)(Math.random() * 400) + 30;
					yballe = (int)(Math.random() * 400) + 30;

					repaint();
				}

			}
			
			public void mouseDragged(MouseEvent e) {
				this.mouseMoved(e);

			}
		};

		this.addMouseMotionListener(mml);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.blue);
		g.fillOval(xballe, yballe, 30, 30);
	}
}
