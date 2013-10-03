package cs355.mvc.model.manage;

import java.awt.Color;

import cs355.mvc.model.Circle;
import cs355.mvc.model.Ellipse;
import cs355.mvc.model.Line;
import cs355.mvc.model.Rectangle;
import cs355.mvc.model.Shape;
import cs355.mvc.model.Square;
import cs355.mvc.model.Triangle;

public class HandleSelectorManager {
	
	private static HandleSelectorManager instance = null;

	private Shape upperLeft;
	private int xul, yul;
	private Shape upperRight;
	private int xur, yur;
	private Shape bottomLeft;
	private int xbl, ybl;
	private Shape bottomRight;
	private int xbr, lbr;
	
	private boolean somethingSelected = false;
	
	private ShapeManager shapeManager;
	private Shape selectedShape;
	
	private static final int HIGHLIGHTER_WIDTH = 10;
	
	public HandleSelectorManager() {
		shapeManager = ShapeManager.getInstance();
	}
	
	public static HandleSelectorManager getInstance() {
		if (instance == null) {
			instance = new HandleSelectorManager();
		}
		return instance;
	}
	
	public void shiftHandles(int shiftX, int shiftY) {
	}
	
	public void createOutline(Shape selected) {
		selectedShape = selected;
		if (selectedShape == null)
			return;
		if (selectedShape instanceof Square) {
			createOutlineForSquare((Square) selectedShape);
		}
		else if (selectedShape instanceof Rectangle) {
			
		}
		else if (selectedShape instanceof Circle) {
			
		}
		else if (selectedShape instanceof Ellipse) {
			
		}
		else if (selectedShape instanceof  Line) {
			
		}
		else if (selectedShape instanceof Triangle) {
			
		}
	}
	
	private void createOutlineForSquare (Square selectedShape) {
		int xCenter = selectedShape.getXCenter();
		int yCenter = selectedShape.getYCenter();
		int d = selectedShape.getWidth() / 2;
		upperLeft = new Square(xCenter - d - HIGHLIGHTER_WIDTH, yCenter - d - HIGHLIGHTER_WIDTH, HIGHLIGHTER_WIDTH, Color.red);
		upperRight= new Square(xCenter + d, yCenter - d - HIGHLIGHTER_WIDTH, HIGHLIGHTER_WIDTH, Color.red);
		bottomLeft = new Square(xCenter - d - HIGHLIGHTER_WIDTH, yCenter + d, HIGHLIGHTER_WIDTH, Color.red);
		bottomRight = new Square(xCenter + d, yCenter + d, HIGHLIGHTER_WIDTH, Color.red);
		xul = upperLeft.getXCenter();
		yul = upperLeft.getYCenter();
	}
	
	public Square getUpperLeft() {
		return (Square)upperLeft;
	}

	public void setUpperLeft(Square upperLeft) {
		this.upperLeft = upperLeft;
	}

	public Square getUpperRight() {
		return (Square)upperRight;
	}

	public void setUpperRight(Square upperRight) {
		this.upperRight = upperRight;
	}

	public Square getBottomLeft() {
		return (Square)bottomLeft;
	}

	public void setBottomLeft(Square bottomLeft) {
		this.bottomLeft = bottomLeft;
	}

	public Square getBottomRight() {
		return (Square)bottomRight;
	}

	public void setBottomRight(Square bottomRight) {
		this.bottomRight = bottomRight;
	}

	public void setSelectedShape(Shape selectedShape) {
		this.selectedShape = selectedShape;
	}

	public Shape getSelectedShape() {
		return selectedShape;
	}
	
	public boolean isSomethingSelected() {
		if (selectedShape != null) return true;
		return false;
	}

}
