import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class Cercle extends figureGeom{
	private Color color;
	private boolean filled;
	private ArrayList<Point> coord;
	private ArrayList<Point> construct;
	
	/**
	 * Default constructor
	 * @param pts : ArrayList of points
	 */
	public Cercle(Cercle circ) {
		color = circ.getColor();
		filled = circ.getFill();
		coord = circ.getMemo();
		construct = circ.getConstruct();
	}
	
	/**
	 * Constructor
	 * @param col : a Color for the form
	 * @param fill : is the form filled or not?
	 * @param pts : ArrayList of points
	 */
	public Cercle(Color col, boolean fill, ArrayList<Point> pts, ArrayList<Point> constru) {
		super(col, fill, pts, constru);
	
	}
}
