import java.awt.*;
import java.util.*;

public abstract class figureGeom extends Observable{
	private Color color;
	private boolean filled;
	private ArrayList<Point> memo;
	private ArrayList<Point> construct;
	
	
	public figureGeom() {
		color = Color.black;
		filled = false;
	}
	
	public figureGeom(Color col, boolean fill, ArrayList<Point> pts, ArrayList<Point> constru) {
		color = col;
		filled = fill;
		memo = pts;
		construct = constru;
	}
	
	public void switchFill() {
		if(filled) {
			filled = false;
		}else {
			filled = true;
		}
	}
	
	public void changeColor(Color col) {
		color = col;
	}
	
	public Color getColor() {
		return color;
	}
	
	public boolean getFill() {
		return filled;
	}
	
	public ArrayList<Point> getMemo(){
		return memo;
	}
	
	public ArrayList<Point> getConstruct(){
		return construct;
	}
	
	public void updatePointConstruct(int index, Point newP){
		construct.get(index).x = (int)newP.getX();
		construct.get(index).y = (int)newP.getY();
	}
	
	public void updatePointMemo(int index, Point newP){
		memo.get(index).x = (int)newP.getX();
		memo.get(index).y = (int)newP.getY();
	}
}
