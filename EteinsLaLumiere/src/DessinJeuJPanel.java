import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.*;

public class DessinJeuJPanel extends JFrame implements ActionListener {

	public static void main(String[] args) {
		DessinJeuJPanel lumiere = new DessinJeuJPanel();
		lumiere.setVisible(true);

	}

	private JButton[][] cases;
	private JButton configurer;
	private JButton aleatoire;
	private JButton quitter;
	private JLabel gagne;
	private JLabel deplacements;
	private int nombreDeVictoires;
	private int nombreDeDeplacements = 0 / 25;

	public DessinJeuJPanel() {
		nombreDeVictoires = 0;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		setTitle("Eteins La Lumiere !");
		setSize(1000, 1000);

		JPanel PanelMain = new JPanel();

		PanelMain.setLayout(new BorderLayout());

		// Bouttons
		JPanel buttonPanel = new JPanel();
		cases = new JButton[5][5];
		buttonPanel.setLayout(new GridLayout(5, 5));

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				int aleatoire = (int) (Math.random() * 5);
				JButton button = new JButton();
				cases[i][j] = button;
				button.setName("" + i + j);
				button.setBackground(Color.GREEN);
				if (aleatoire == 2) {
					backgroundColor(button);
				}
				button.addActionListener(this);
				buttonPanel.add(button);

			}
		}

		PanelMain.add(buttonPanel, "Center");

		aleatoire = new JButton("Aleatoire");
		aleatoire.setName("aleatoire");
		aleatoire.addActionListener(this);
		configurer = new JButton("Configurer");
		configurer.setName("Manuel");
		configurer.addActionListener(this);
		quitter = new JButton("Quitter");
		quitter.addActionListener(this);

		JPanel controls = new JPanel();
		controls.setLayout(new GridLayout(4, 2));
		controls.add(configurer, "West");
		controls.add(aleatoire, "West");

		PanelMain.add(controls, "West");

		// Labels
		JPanel label = new JPanel();
		label.setLayout(new GridLayout(2, 2));
		label.add(gagne = new JLabel());
		gagne.setText("<html><div style =\"text-align:center\"><span style = \"font-size : large\">Parties Gagnées : "
				+ nombreDeVictoires + "</span></div></html>");
		PanelMain.add(label, "North");

		JPanel labeldeplacements = new JPanel();
		labeldeplacements.setLayout(new GridLayout(1, 1));
		labeldeplacements.add(deplacements = new JLabel());
		deplacements.setText(
				"<html><div style =\"text-align:center\">Nombre de deplacements<br><span style = \"font-size : large\">"
						+ nombreDeDeplacements + "</span></div></html>");
		controls.add(labeldeplacements, "West");

		controls.add(quitter, "West");
		setContentPane(PanelMain);
	}

	public void actionPerformed(ActionEvent e) {

		JButton button = (JButton) e.getSource();
		String location = button.getName();
		if (location.equals("aleatoire")) {
			optionAleatoire();
			return;
		}
		if (location.equals("Sortir du mode Configurer")) {
			configurer.setText("Configurer");
			configurer.setName("Manuel");
			return;
		}
		if (location.equals("Manuel")) {
			configurer.setText("Quitter Configurer");
			configurer.setName("Sortir du mode Configurer");
			return;
		}
		if (configurer.getName().equals("Sortir du mode Configurer")) {
			optionConfigurer(button);
			return;
		}
		/*if (location.equals("Quitter")) {
			Quitter();
			return;
		}
		*/
		char colChar = location.charAt(0);
		char rowChar = location.charAt(1);
		int col = Character.getNumericValue(colChar);
		int row = Character.getNumericValue(rowChar);

		JButton bouttonSelect = new JButton();
		JButton bouttonHaut = new JButton();
		JButton bouttonGauche = new JButton();
		JButton bouttonDroite = new JButton();
		JButton bouttonBas = new JButton();

		bouttonSelect = cases[col][row];
		backgroundColor(bouttonSelect);
		try {
			bouttonHaut = cases[col - 1][row];
			backgroundColor(bouttonHaut);
		} catch (ArrayIndexOutOfBoundsException i) {

		}

		try {
			bouttonGauche = cases[col][row - 1];
			backgroundColor(bouttonGauche);
		} catch (ArrayIndexOutOfBoundsException i) {

		}
		try {
			bouttonDroite = cases[col][row + 1];
			backgroundColor(bouttonDroite);
		} catch (ArrayIndexOutOfBoundsException i) {

		}
		try {
			bouttonBas = cases[col + 1][row];
			backgroundColor(bouttonBas);
		} catch (ArrayIndexOutOfBoundsException i) {

		}
		NbDeplacements();
		Gagne();
	}

	private void backgroundColor(JButton b) {
		if (b.getBackground() == Color.GREEN) {
			b.setBackground(Color.RED);
		} else {
			b.setBackground(Color.GREEN);
		}
	}

	private void optionAleatoire() {
		for (JButton b[] : cases) {
			for (JButton c : b) {
				int random = (int) (Math.random() * 8);
				if (random == 3) {
					backgroundColor(c);
				}

			}
		}
	}

	private void optionConfigurer(JButton b) {
		JButton butt = new JButton();
		butt = b;
		backgroundColor(butt);
	}

	/*private void Quitter() {
				if(nombreDeDeplacements >= 0)
					nombreDeDeplacements = 0;
					deplacements.setText(
							"<html><div style =\"text-align:center\">Nombre de deplacements<br><span style = \"font-size : large\">"
									+ nombreDeDeplacements+ "</span></div></html>");
				
				reinitialiser();
	}*/

	private void Gagne() {
		int count = 0;
		for (JButton b[] : cases) {
			for (JButton c : b) {
				if (c.getBackground() == Color.RED)

				{
					count++;
				}
			}
		}
		if (count == 25) {
			JOptionPane.showMessageDialog(this, "Felicitations tu as gagné");
			nombreDeVictoires++;
			gagne.setText(
					"<html><div style =\"text-align:center\"><span style = \"font-size : large\">Parties Gagnées : "
							+ nombreDeVictoires + "</span></div></html>");
			reinitialiser();
		}
	}

	private void NbDeplacements() {
		for (JButton b[] : cases) {
			for (JButton a : b) {
				if (a.getBackground() == Color.RED || a.getBackground() == Color.GREEN)

				{
					nombreDeDeplacements++;
					deplacements.setText(
							"<html><div style =\"text-align:center\">Nombre de deplacements<br><span style = \"font-size : large\">"
									+ nombreDeDeplacements / 25 + "</span></div></html>");

				}
			}
		}

	}

	private void reinitialiser() {
		for (JButton b[] : cases) {
			for (JButton a : b) {
				int random = (int) (Math.random() * 4);
				if (random == 2) {
					backgroundColor(a);
				}

			}
		}
	}
}