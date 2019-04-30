import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class ControleCouleur extends KeyAdapter{

	public void keyPressed(KeyEvent e) {
		char c = e.getKeyChar();

		switch(c){
		case 'r':
			((Scribble)e.getSource()).setColor(Color.RED);
			break;
		case 'b':
			((Scribble)e.getSource()).setColor(Color.BLUE);
			break;
		case 'v':
			((Scribble)e.getSource()).setColor(Color.GREEN);
			break;
		case 'j':
			((Scribble)e.getSource()).setColor(Color.YELLOW);
			break;
		case 'z':
			((Scribble)e.getSource()).setColor(Color.PINK);
			break;
		case 'n':
			((Scribble)e.getSource()).setColor(Color.BLACK);
			break;
		case 'g':
			((Scribble)e.getSource()).setColor(Color.GRAY);
			break;
		case 'm':
			((Scribble)e.getSource()).setColor(Color.MAGENTA);
			break;
		}   
	}

}
