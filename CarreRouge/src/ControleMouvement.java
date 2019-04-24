import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

public class ControleMouvement extends KeyAdapter{
	
	public void keyPressed(KeyEvent e) {
		int cx = ((Carre)e.getSource()).getPosX();
		int cy = ((Carre)e.getSource()).getPosY();

		int c = e.getKeyCode();
		switch(c){
		case KeyEvent.VK_UP:                	
			if (cy-Carre.COTE>=0)
				cy-=Carre.COTE;
			break;
		case KeyEvent.VK_DOWN:                	
			if (cy+Carre.COTE<= ((JPanel)e.getSource()).getHeight())
				cy+=Carre.COTE;
			break;
		case KeyEvent.VK_RIGHT:                	
			if (cx+Carre.COTE<=((JPanel)e.getSource()).getWidth())
				cx+=Carre.COTE;
			break;	
		case KeyEvent.VK_LEFT:                	
			if (cx-Carre.COTE>=0)
				cx-=Carre.COTE;
			break;
		}   
		//mettre à jour la position du curseur.
		((Carre)e.getSource()).setPosX(cx);
		((Carre)e.getSource()).setPosY(cy);        
		((JPanel)e.getSource()).repaint();
	}

}
