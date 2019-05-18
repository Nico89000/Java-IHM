import java.awt.*;
import java.util.*;

public class Rectangle extends figureGeom{
	private Color color;
	private boolean filled;
	private ArrayList<Point> coord;
	private ArrayList<Point> construct;
	private int height;
	
	/**
	 * Default constructor
	 * @param pts : ArrayList of points
	 */
	public Rectangle(Rectangle rect) {
		color = rect.getColor();
		filled = rect.getFill();
		coord = rect.getMemo();
		construct = rect.getConstruct();
	}
	
	/**
	 * Constructor
	 * @param col : a Color for the form
	 * @param fill : is the form filled or not?
	 * @param pts : ArrayList of points
	 */
	public Rectangle(Color col, boolean fill, ArrayList<Point> pts, ArrayList<Point> constru) {
		super(col, fill, pts, constru);
	}
}
