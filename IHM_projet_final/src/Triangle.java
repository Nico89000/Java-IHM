import java.awt.*;
import java.util.*;

public class Triangle extends figureGeom{
	private Color color;
	private boolean filled;
	private ArrayList<Point> coord;
	private ArrayList<Point> construct;
	
	/**
	 * Default constructor
	 * @param pts : ArrayList of points
	 */
	public Triangle(Triangle tri) {
		color = tri.getColor();
		filled = tri.getFill();
		coord = tri.getMemo();
		construct = tri.getConstruct();
	}
	
	/**
	 * Constructor
	 * @param col : a Color for the form
	 * @param fill : is the form filled or not?
	 * @param pts : ArrayList of points
	 */
	public Triangle(Color col, boolean fill, ArrayList<Point> pts, ArrayList<Point> constru) {
		super(col, fill, pts, constru);
		
	}
}
