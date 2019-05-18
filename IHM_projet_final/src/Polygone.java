import java.awt.*;
import java.util.*;

public class Polygone extends figureGeom{
	private Color color;
	private boolean filled;
	private ArrayList<Point> coord;
	private ArrayList<Point> construct;
	
	/**
	 * Default constructor
	 * @param pts : ArrayList of points
	 */
	public Polygone(Polygone pol) {
		color = pol.getColor();
		filled = pol.getFill();
		coord = pol.getMemo();
		construct = pol.getConstruct();
	}
	
	/**
	 * Constructor
	 * @param col : a Color for the form
	 * @param fill : is the form filled or not?
	 * @param pts : ArrayList of points
	 */
	public Polygone(Color col, boolean fill, ArrayList<Point> pts, ArrayList<Point> constru) {
		super(col, fill, pts, constru);
		
		
	}
}
