import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class VueGeoCreator extends JPanel implements Observer {

	// __________________________________________________________Initialization________________________________________________________________________ //
	
	ArrayList<figureGeom> formes = new ArrayList<figureGeom>();
	// ArrayList containing all the forms to draw

	Color color;
	// Color of the form, depending of the comboBox

	Scanner sc = new Scanner(System.in);
	MouseListener ml;
	MouseMotionListener mml;

	Random rand = new Random();

	boolean fill, box, line = false;
	// Fill is used to determine if the form is filled or not
	// Box is used to display or not the color window
	// Line is used to say if you have clicked on a line of the form (used to allow
	// the move of a form)

	int draw, nClick, nClicked, selected = 0, point = 0, oldX, oldY, currentX, currentY;
	// Draw displays the mode to use
	// nClick limits the number of clicks
	// nClicked determines the number of clicks the user has made
	// Selected sets which item of the list is currently selected
	// Point is used to detect if you're on a point, and which one
	// OldX, oldY, currentX and currentY are used to track the old and current
	// position of the mouse

	ArrayList<Point> tempo = new ArrayList<Point>();
	ArrayList<Point> tempo2 = new ArrayList<Point>();
	ArrayList<Point> progress = new ArrayList<Point>();
	// A temporary ArrayList of points to create the forms

	// __________________________________________________________Constructor________________________________________________________________________ //	
	
	public VueGeoCreator() {
		currentX = 0;
		currentY = 0;
		draw = 0;
		fill = true;
		color = Color.black;
		// Default values

		// __________________________________________________________Drawing space JPanel________________________________________________________________________ //		
		
		JPanel area = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (!formes.isEmpty()) {
					// To prevent exception at the start of the program
					for (int i = 0; i < formes.size(); i++) {
						// __________________________________________Draw a rectangle_________________________________ //
						if (formes.get(i) instanceof Rectangle) {
							int X1 = formes.get(i).getMemo().get(0).x;
							int Y1 = formes.get(i).getMemo().get(0).y;
							int X2 = formes.get(i).getMemo().get(1).x;
							int Y2 = formes.get(i).getMemo().get(1).y;
							int X, Y;
							// The two memory points
							
							Point p1 = new Point(X1, Y1);
							Point p2 = new Point(X2, Y1);
							Point p3 = new Point(X1, Y2);
							int larg = (int) Math.round(p1.distance(p2));
							int haut = (int) Math.round(p1.distance(p3));
							// To the height and width

							if (X2 < X1) {
								X = X1;
								X1 = X2;
								X2 = X1;
							}
							if (Y2 < Y1) {
								Y = Y1;
								Y1 = Y2;
								Y2 = Y1;
							}

							// If you make the first point to the right

							g.setColor(formes.get(i).getColor());
							// Sets the color of the form

							if (formes.get(i).getFill()) {
								g.fillRect(X1, Y1, larg, haut);
							} else {
								g.drawRect(X1, Y1, larg, haut);
							}
							// Draw, filled or not depending of the attribute
						}

						// __________________________________________Draw a circle_________________________________ //						
						
						if (formes.get(i) instanceof Cercle) {
							g.setColor(formes.get(i).getColor());
							// Sets the color of the form

							int X1 = formes.get(i).getConstruct().get(0).x;
							int Y1 = formes.get(i).getConstruct().get(0).y;
							int X2 = formes.get(i).getConstruct().get(1).x;
							int Y2 = formes.get(i).getConstruct().get(1).y;
							// The two memory points

							Point p1 = new Point(X1, Y1);
							Point p2 = new Point(X2, Y2);
							int r = (int) Math.round(p1.distance(p2));
							// Calculate the ray

							if (formes.get(i).getFill()) {
								g.fillOval(X1 - r, Y1 - r, r * 2, r * 2);
							} else {
								g.drawOval(X1 - r, Y1 - r, r * 2, r * 2);
							}
							// Draw, filled or not depending on the attribute

							// __________________________________________Draw a polygon_________________________________ //							
							
						} else {
							// Triangle and Polygon
							int[] X = new int[formes.get(i).getConstruct().size()];
							int[] Y = new int[formes.get(i).getConstruct().size()];
							// X and Y arrays for the drawing

							g.setColor(formes.get(i).getColor());
							for (int j = 0; j < formes.get(i).getConstruct().size(); j++) {
								X[j] = formes.get(i).getConstruct().get(j).x;
								Y[j] = formes.get(i).getConstruct().get(j).y;
							}
							// Insert all points of the form

							if (formes.get(i).getFill()) {
								g.fillPolygon(X, Y, X.length);
							} else {
								g.drawPolygon(X, Y, X.length);
							}
							// Drawing the polygon or triangle, filled or not depending on the attribute
						}

					}
				}
				
				if(draw > 0) {
					for(int i=0; i<progress.size();i++) {
						g.setColor(Color.red);
						g.fillOval((int)progress.get(i).getX(), (int)progress.get(i).getY(), 5, 5);
						if((draw == 4 || draw == 2) && i != 0) {
							g.setColor(Color.black);
							g.drawLine((int)progress.get(i-1).getX(), (int)progress.get(i-1).getY(), (int)progress.get(i).getX(), (int)progress.get(i).getY());
						}
					}
					
				}
				
				// __________________________________________Draw the memo points of a selected form_________________________________ //
				
				if (selected != 0 && !formes.isEmpty()) {
					for (int j = 0; j < formes.get(selected - 1).getMemo().size(); j++) {
						int X = (int) formes.get(selected - 1).getMemo().get(j).getX();
						int Y = (int) formes.get(selected - 1).getMemo().get(j).getY();
						g.setColor(Color.red);
						g.fillRect(X - 2, Y - 3, 5, 5);
					}
				}
			}
		};
		area.setPreferredSize(new Dimension(600, 450));
		area.setBackground(Color.white);
		// Draw area

		// __________________________________________________________Drawing the two buttons JPanels________________________________________________________________________ //		
		
		JPanel buttons = new JPanel();
		buttons.setPreferredSize(new Dimension(600, 70));
		buttons.setLayout(new GridLayout(2, 4, 5, 5));
		buttons.setBackground(Color.white);
		// Top buttons
		
		JPanel buttonsTop = new JPanel();
		buttonsTop.setPreferredSize(new Dimension(600, 30));
		buttonsTop.setLayout(new GridLayout(1, 4, 5, 5));
		buttonsTop.setBackground(Color.white);
		// Forms buttons
		
		JPanel buttonsBottom = new JPanel();
		buttonsBottom.setPreferredSize(new Dimension(600, 30));
		buttonsBottom.setLayout(new GridLayout(1, 2, 50, 50));
		buttonsBottom.setBackground(Color.white);
		// Modif buttons
		buttonsBottom.setBorder(new EmptyBorder(5, 0, 0, 0));
		
		buttons.setLayout(new BorderLayout());
		buttons.add(buttonsTop, BorderLayout.NORTH);
        buttons.add(buttonsBottom, BorderLayout.SOUTH);

		JPanel options = new JPanel();
		options.setPreferredSize(new Dimension(600, 50));
		options.setLayout(new GridLayout(1, 3, 50, 50));
		options.setBackground(Color.white);
		// Bottom buttons

		// __________________________________________________________Layout of the page________________________________________________________________________ //		
		
		this.setLayout(new BorderLayout());
		this.add(buttons, BorderLayout.NORTH);
		this.add(area, BorderLayout.CENTER);
		this.add(options, BorderLayout.SOUTH);

		// __________________________________________________________Creation of all the buttons________________________________________________________________________ //		
		
		JButton tri = new JButton("Triangle");
		JButton circ = new JButton("Cercle");
		JButton rect = new JButton("Rectangle");
		JButton poly = new JButton("Polygone");
		JButton changeCol = new JButton("Couleurs");
		JButton clear = new JButton("Nettoyer");
		JButton leave = new JButton("Quitter");
		// All the buttons
		
		JTextField info = new JTextField("Informations");
		info.setEnabled(false);
		info.setFont(new Font(info.getFont().toString(), Font.BOLD, 10));
		info.setHorizontalAlignment(JTextField.CENTER);
		info.setDisabledTextColor(Color.BLACK);
		
		// __________________________________________________________Color Chooser________________________________________________________________________ //				
		
		JColorChooser chooser = new JColorChooser(Color.BLACK);
		chooser.setVisible(false);
		chooser.setBackground(Color.white);
		chooser.setPreferredSize(new Dimension(600, 450));
		area.add(chooser);
		
		// __________________________________________________________Creation of the JPopupMenu for the right click________________________________________________________________________ //				
		
		// ________________________________________Fill/Unfill________________________________________ //
		JPopupMenu pop = new JPopupMenu();
		JMenuItem p1 = new JMenuItem("Remplir/Vider");
		p1.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	             formes.get(selected-1).switchFill();
	             info.setText("Remplissage interverti");
	             repaint();
	        }
	    });
		pop.add(p1);
		
		// ________________________________________Update color________________________________________ //
		
		JMenuItem p2 = new JMenuItem("Adapter couleur");
		p2.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	formes.get(selected-1).changeColor(chooser.getColor());
	        	info.setText("Couleur mise à jour");
	        	repaint();
	        }
	    });	
		pop.add(p2);
		
		// ________________________________________Duplicate________________________________________ //
		
		JMenuItem p3 = new JMenuItem("Dupliquer");
		p3.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	// The objective is to avoid the same references to have an independant form
	        	
	        	Color col = new Color(formes.get(selected-1).getColor().getRed(), formes.get(selected-1).getColor().getGreen(), formes.get(selected-1).getColor().getBlue());
	        	// Create a color with the RVB of the selected form
	        	
	        	if(formes.get(selected-1).getFill()) {
	        		boolean fill = true;
	        	}else {
	        		boolean fill = false;
	        	}
	        	// Create a boolean based on the value of the fill attribute in the current form
	        	
	        	ArrayList<Point> pts = new ArrayList<Point>();
	        	for(int i=0;i<formes.get(selected-1).getMemo().size();i++) {
	        		int X = (int)formes.get(selected-1).getMemo().get(i).getX();
	        		int Y = (int)formes.get(selected-1).getMemo().get(i).getY();
	        		pts.add(new Point(X+50, Y+50));
	        	}
	        	// Create an ArrayList of memo points with a +50 difference
	        	
	        	ArrayList<Point> construct = new ArrayList<Point>();
	        	for(int i=0;i<formes.get(selected-1).getConstruct().size();i++) {
	        		int X = (int)formes.get(selected-1).getConstruct().get(i).getX();
	        		int Y = (int)formes.get(selected-1).getConstruct().get(i).getY();
	        		construct.add(new Point(X+50, Y+50));
	        	}
	        	// Create an ArrayList of construct points with a +50 difference
	        	
	             if(formes.get(selected-1) instanceof Rectangle) {
	            	 formes.add(new Rectangle(col, fill, pts, construct));
	             }else if(formes.get(selected-1) instanceof Cercle) {
	            	 formes.add(new Cercle(col, fill, pts, construct));
	             }else if(formes.get(selected-1) instanceof Triangle) {
	            	 formes.add(new Triangle(col, fill, pts, construct));
	             }else if(formes.get(selected-1) instanceof Polygone) {
	            	 formes.add(new Polygone(col, fill, pts, construct));
	             }
	             // Create a new form based on the instance of the current form
	             
	             info.setText("Forme dupliquée");
	             repaint();
	        }
	    });
		pop.add(p3);
		
		// ________________________________________Delete________________________________________ //
		
				JMenuItem p4 = new JMenuItem("Supprimer");
				p4.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			        	formes.remove(selected-1);
			        	info.setText("Forme supprimée");
			        	repaint();
			        	pop.setVisible(false);
			        }
			    });
				pop.add(p4);
		
		// ________________________________________Style and layout________________________________________ //
		
		pop.setBackground(Color.white);
		p1.setBackground(new Color(230, 230, 230));
		p2.setBackground(new Color(230, 230, 230));
		p3.setBackground(new Color(230, 230, 230));
		p4.setBackground(new Color(230, 230, 230));
		
		pop.setPreferredSize(new Dimension(100, 200));
		pop.setLayout(new GridLayout(4, 1, 5, 5));
		pop.setVisible(false);
		
		// __________________________________________________________Creation of the JCombobox________________________________________________________________________ //				
		

		final JComboBox d = new JComboBox(new String[] { "Filled", "Not Filled" });
		d.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (d.getSelectedIndex()) {
				case 0:
					fill = true;
					break;
				case 1:
					fill = false;
					break;
				}
			}
		});
		// To select if the form is filled or not

		// __________________________________________________________Buttons style________________________________________________________________________ //		
		
		d.setBackground(Color.black);
		d.setForeground(Color.white);
		// Style for the combo boxes

		buttonsTop.add(rect);
		buttonsTop.add(tri);
		buttonsTop.add(circ);
		buttonsTop.add(poly);
		buttonsBottom.add(changeCol);
		buttonsBottom.add(d);
		options.add(clear);
		options.add(info);
		options.add(leave);
		// Add the buttons and JComboBoxes

		JButton[] design = new JButton[7];
		design[0] = tri;
		design[1] = circ;
		design[2] = rect;
		design[3] = poly;
		design[4] = clear;
		design[5] = leave;
		design[6] = changeCol;
		Border thickBorder = new LineBorder(Color.BLACK, 2);
		for (int i = 0; i < design.length; i++) {
			design[i].setBorder(thickBorder);
			design[i].setBackground(Color.WHITE);
			design[i].setBackground(Color.black);
			design[i].setForeground(Color.white);
		}
		// Change the design of all buttons

		// __________________________________________________________Buttons action listeners________________________________________________________________________ //		
		
		leave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				System.out.println("Fin");
			}
		});
		// Leave the application

		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Réinitialisation de la zone de dessin");
				formes.clear();
				info.setText("Plateau nettoyé");
				repaint();
			}
		});
		// Clear all the board

		rect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				draw = 1;
				nClick = 2;
				nClicked = 0;
				tempo = new ArrayList<Point>();
				tempo2 = new ArrayList<Point>();
				info.setText("Veuillez placer 2 points");
			}
		});
		// Create a rectangle with 2 points of memory

		tri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				draw = 2;
				nClick = 3;
				nClicked = 0;
				tempo = new ArrayList<Point>();
				tempo2 = new ArrayList<Point>();
				System.out.println("Initialisation d'un nouveau triangle");
				info.setText("Veuillez placer 3 points");
			}
		});
		// Create a triangle with 3 points of memory

		circ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				draw = 3;
				nClick = 2;
				nClicked = 0;
				tempo = new ArrayList<Point>();
				tempo2 = new ArrayList<Point>();
				System.out.println("Initialisation d'un nouveau cercle");
				info.setText("Veuillez placer 2 points");
			}
		});
		// Create a circle with 2 points of memory

		poly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				draw = 4;
				nClicked = 0;
				nClick = 50;
				tempo = new ArrayList<Point>();
				tempo2 = new ArrayList<Point>();
				System.out.println("Initialisation d'un nouveau polygone");
				info.setText("Clic droit pour arrêter");
			}
		});
		// Right-click to stop the polygon

		changeCol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chooser.isVisible()) {
					chooser.setVisible(false);
				}else {
					chooser.setVisible(true);
				}
			}
		});
		// Display a JColorChooser

		// __________________________________________________________Mouse listeners________________________________________________________________________ //		
		
		// __________________________________________Move_________________________________ //		
		
		mml = new MouseMotionListener() {
			public void mouseMoved(MouseEvent e) {

			}

			// __________________________________________Drag_________________________________ //			
			
			public void mouseDragged(MouseEvent e) {
				if (selected - 1 >= 0 && !formes.isEmpty() && draw == 0) {
					// If you're on the line of a form

					oldX = currentX;
					oldY = currentY;
					currentX = e.getX();
					currentY = e.getY();
					// Update mouse position

					Point p1 = new Point(currentX, currentY);
					Point p2 = new Point(oldX, oldY);

					// __________________________________________Move a form_________________________________ //					
					
					if (line) {
						// If you're on a line
						for (int i = 0; i < formes.get(selected - 1).getConstruct().size(); i++) {
							// Modifying all the memo points
							int X1 = (int) formes.get(selected - 1).getConstruct().get(i).getX();
							int Y1 = (int) formes.get(selected - 1).getConstruct().get(i).getY();

							int distX, distY;
							distX = (int) currentX - oldX;
							distY = (int) currentY - oldY;
							Point modif = new Point(X1 + distX, Y1 + distY);
							// Set the new point, modified by your mouse drag

							formes.get(selected - 1).updatePointConstruct(i, modif);
							// Update the construct point

							if (formes.get(selected - 1) instanceof Rectangle) {
								// The rectangle has a different number of memo and construct points
								// So we're calculating the two new memo points
								if (i == 0) {
									formes.get(selected - 1).updatePointMemo(0, modif);
								} else if (i == 2) {
									formes.get(selected - 1).updatePointMemo(1, modif);
								}
							} else {
								formes.get(selected - 1).updatePointMemo(i, modif);
							}
						}
						
						// __________________________________________Change a form's form_________________________________ //	
						
					} else if (point - 1 >= 0) {
						// If you're on a memo point, you can change the form's size and disposition
						int X1 = (int) formes.get(selected - 1).getMemo().get(point - 1).getX();
						int Y1 = (int) formes.get(selected - 1).getMemo().get(point - 1).getY();
						int X, Y;

						int distX, distY;
						distX = (int) currentX - oldX;
						distY = (int) currentY - oldY;

						X1 += distX;
						Y1 += distY;
						Point modif = new Point(X1, Y1);
						// New memo point

						formes.get(selected - 1).updatePointMemo(point - 1, modif);
						// Update it

						if (formes.get(selected - 1) instanceof Rectangle) {
							// Same problem for the rectangle
							// We need to calculate the 4 new construct points
							if (point - 1 == 0) {
								formes.get(selected - 1).updatePointConstruct(0, modif);
								X = (int) formes.get(selected - 1).getConstruct().get(1).getX();
								modif = new Point(X, Y1);
								formes.get(selected - 1).updatePointConstruct(1, modif);
								
								Y = (int) formes.get(selected - 1).getConstruct().get(3).getY();
								modif = new Point(X1, Y);
								formes.get(selected - 1).updatePointConstruct(3, modif);
							} else {
								formes.get(selected - 1).updatePointConstruct(2, modif);
								Y = (int) formes.get(selected - 1).getConstruct().get(1).getY();
								modif = new Point(X1, Y);
								formes.get(selected - 1).updatePointConstruct(1, modif);
								X = (int) formes.get(selected - 1).getConstruct().get(3).getX();
								modif = new Point(X, Y1);
								formes.get(selected - 1).updatePointConstruct(3, modif);
							}
						} else {
							formes.get(selected - 1).updatePointConstruct(point - 1, modif);
						}

					}
					repaint();
				}
			}
		};

		ml = new MouseListener() {
			
			// __________________________________________Click_________________________________ //			
			
			public void mouseClicked(MouseEvent e) {

			}

			// __________________________________________Press_________________________________ //			
			
			public void mousePressed(MouseEvent e) {
				oldX = currentX;
				oldY = currentY;
				currentX = e.getX();
				currentY = e.getY();
				// Update mouse position

				// _________________________Right click menu_____________________________ //
				
				if(selected-1 != 0) {
					if(SwingUtilities.isRightMouseButton(e)) {
						pop.setVisible(true);
						pop.setLocation(e.getX()+20, e.getY());
					}
				}
					
				// _________________________Select a form_____________________________ //				
				
				if (draw == 0) {
					// If you're not drawing any form

					boolean found = false;
					for (int i = 0; i < formes.size(); i++) {
						// Checking all the forms to see if we are selecting one
				
						// _______________Select a circle__________________ //						
						
						if (formes.get(i) instanceof Cercle) {
							// Circle has a different method :
							// If the distance between the two memo points is
							// the same as the distance between your click and
							// the center, then you're selecting the circle

							int X1 = formes.get(i).getConstruct().get(0).x;
							int Y1 = formes.get(i).getConstruct().get(0).y;
							int X2 = formes.get(i).getConstruct().get(1).x;
							int Y2 = formes.get(i).getConstruct().get(1).y;
							Point p1 = new Point(X1, Y1);
							Point p2 = new Point(X2, Y2);
							Point p3 = new Point(e.getX(), e.getY());

							int d1 = (int) Math.round(p1.distance(p2));
							int d2 = (int) Math.round(p1.distance(p3));
							if(formes.get(i).getFill()) {
								if(d1-d2 >= 0){
									int X = (int) formes.get(i).getMemo().get(1).getX();
									int Y = (int) formes.get(i).getMemo().get(1).getY();
									if (e.getX() >= X - 3 && e.getX() <= X + 3 && e.getY() >= Y - 3 && e.getY() <= Y + 3) {
										point = 2;
									}
									// If you're on the second point, then you can change the circle's size

									if (point == 0) {
										line = true;
									}
									// Else, you are on a line and you can move the form

									selected = i + 1;
									found = true;
									// Stop the search and register which form is selected
								}
							}else if (d1 - d2 >= -1 && d1 - d2 <= 1) {
								int X = (int) formes.get(i).getMemo().get(1).getX();
								int Y = (int) formes.get(i).getMemo().get(1).getY();
								if (e.getX() >= X - 3 && e.getX() <= X + 3 && e.getY() >= Y - 3 && e.getY() <= Y + 3) {
									point = 2;
								}
								// If you're on the second point, then you can change the circle's size

								if (point == 0) {
									line = true;
								}
								// Else, you are on a line and you can move the form

								selected = i + 1;
								found = true;
								// Stop the search and register which form is selected
							}
						} else {
							// If it's not a circle, we are tracking the distances like for
							// the circle, but between each and every line of the form

							for (int j = 0; j < formes.get(i).getConstruct().size(); j++) {
								// All the lines
							
								// ___________Select a line of a polygon except the last________ //								
								
								if (j != formes.get(i).getConstruct().size() - 1 && !found) {
									int X1 = formes.get(i).getConstruct().get(j).x;
									int Y1 = formes.get(i).getConstruct().get(j).y;
									int X2 = formes.get(i).getConstruct().get(j + 1).x;
									int Y2 = formes.get(i).getConstruct().get(j + 1).y;
									Point p1 = new Point(X1, Y1);
									Point p2 = new Point(X2, Y2);
									Point p3 = new Point(e.getX(), e.getY());

									int d1 = (int) Math.round(p1.distance(p2));
									int d2 = (int) Math.round(p1.distance(p3));
									int d3 = (int) Math.round(p2.distance(p3));
									// Calculating the distances

									if(formes.get(i).getFill()) {
										int[] x = new int[formes.get(i).getConstruct().size()];
										int[] y = new int[formes.get(i).getConstruct().size()];
										for(int k=0; k<x.length;k++) {
											x[k] = (int)formes.get(i).getConstruct().get(k).getX();
											y[k] = (int)formes.get(i).getConstruct().get(k).getY();
										}
										Polygon poly = new Polygon(x, y, x.length);
										if(poly.contains(new Point(e.getX(), e.getY()))) {
											int X = (int) formes.get(i).getMemo().get(1).getX();
											int Y = (int) formes.get(i).getMemo().get(1).getY();
											if (e.getX() >= X - 3 && e.getX() <= X + 3 && e.getY() >= Y - 3 && e.getY() <= Y + 3) {
												point = 2;
											}
											// If you're on the second point, then you can change the circle's size

											if (point == 0) {
												line = true;
											}
											// Else, you are on a line and you can move the form

											selected = i + 1;
											found = true;
											// Stop the search and register which form is selected
										}
									}else if (d1 - (d2 + d3) >= -1 && d1 - (d2 + d3) <= 1) {
										// Checking the equation

										for (int k = 0; k < formes.get(i).getMemo().size(); k++) {
											int X = (int) formes.get(i).getMemo().get(k).getX();
											int Y = (int) formes.get(i).getMemo().get(k).getY();
											if (e.getX() >= X - 3 && e.getX() <= X + 3 && e.getY() >= Y - 3
													&& e.getY() <= Y + 3) {
												point = k + 1;
											}
										}
										// If you're on the point of the form, then you can change its form

										if (point == 0) {
											line = true;
										}
										// If not, then you're on a line of the form and you can move it

										selected = i + 1;
										found = true;
										// Stop the research and register the selected form

									}
								
									// ________________Last line of a polygon________ //									
					
								} else if (j == formes.get(i).getConstruct().size() - 1 && !found) {
									// Same thing with the last line of the form (prevent an exception)
									int X1 = formes.get(i).getConstruct().get(0).x;
									int Y1 = formes.get(i).getConstruct().get(0).y;
									int X2 = formes.get(i).getConstruct().get(j).x;
									int Y2 = formes.get(i).getConstruct().get(j).y;
									Point p1 = new Point(X1, Y1);
									Point p2 = new Point(X2, Y2);
									Point p3 = new Point(e.getX(), e.getY());

									int d1 = (int) Math.round(p1.distance(p2));
									int d2 = (int) Math.round(p1.distance(p3));
									int d3 = (int) Math.round(p2.distance(p3));

									if (d1 - (d2 + d3) >= -1 && d1 - (d2 + d3) <= 1) {
										for (int k = 0; k < formes.get(i).getMemo().size(); k++) {
											int X = (int) formes.get(i).getMemo().get(k).getX();
											int Y = (int) formes.get(i).getMemo().get(k).getY();
											if (e.getX() >= X - 3 && e.getX() <= X + 3 && e.getY() >= Y - 3
													&& e.getY() <= Y + 3) {
												point = k + 1;
											}
										}
										if (point == 0) {
											line = true;
										}
										selected = i + 1;
										found = true;
									}
								}
							}
						}
					}
					
					// _________Nothing selected ________ //					
					
					if (!found) {
						selected = 0;
						pop.setVisible(false);
					}
					// If nothing is found, then no form is selected yet

					repaint();
				}
			}
			// __________________________________________Release_________________________________ //
			public void mouseReleased(MouseEvent e) {
		
				// _______________________Draw a form_________________________ //						
				
				if (nClicked < nClick && draw != 0) {
					// If you are creating a form and all points aren't drawn

					progress.add(new Point(e.getX(), e.getY()));
					repaint();
					tempo.add(new Point(e.getX(), e.getY()));
					tempo2.add(new Point(e.getX(), e.getY()));
					// Add the point to the temporary memo list
					// Add the point to the temporary construct list

					// _________Polygon_________ //							
					
					if (SwingUtilities.isRightMouseButton(e) && draw == 4) {
						// Stop the creation of the polygon on right click

						System.out.println("Création du polygone");
						Polygone p1 = new Polygone(color, fill, tempo, tempo2);
						formes.add(p1);
						info.setText("Forme créée avec succès");
						progress.clear();
						draw = 0;
						repaint();
					}
					// If you right click while drawing a polygon, then you create it

					nClicked++;
					if (nClicked == nClick) {

						// ________Other forms________ //								
						
						switch (draw) {
						case 1:
							System.out.println("Création du rectangle");
							tempo2.add(1, new Point(e.getX(), (int) tempo.get(0).getY()));
							tempo2.add(new Point((int) tempo.get(0).getX(), e.getY()));
							// Add the two other construct points for the rectangle
							
							Rectangle r1 = new Rectangle(chooser.getColor(), fill, tempo, tempo2);
							formes.add(r1);
							break;
						case 2:
							System.out.println("Création du triangle");
							Triangle t1 = new Triangle(chooser.getColor(), fill, tempo, tempo2);
							formes.add(t1);
							break;
						case 3:
							System.out.println("Création du rond");
							Cercle c1 = new Cercle(chooser.getColor(), fill, tempo, tempo2);
							formes.add(c1);
							break;
						}
						info.setText("Forme créée avec succès");
						progress.clear();
						draw = 0;
						repaint();
						// Create the form and stop the drawing if you have reached the limit number of
						// clicks
					}
				}
				line = false;
				point = 0;
			}

			public void mouseEntered(MouseEvent e) {

			}

			public void mouseExited(MouseEvent e) {

			}
		};
		area.add(pop);
		area.addMouseListener(ml);
		area.addMouseMotionListener(mml);
	}

	public void update(Observable o, Object arg1) {
		repaint();
	}
}