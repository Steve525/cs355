package cs355.mvc.model.manage;

import java.awt.Color;
import java.awt.Point;

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
	private Point ulCenter;
	private Shape upperRight;
	private Point urCenter;
	private Shape bottomLeft;
	private Point blCenter;
	private Shape bottomRight;
	private Point brCenter;
	
	private int whichHandleSelected = 0;
	
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
		upperLeft.setXCenter(ulCenter.x + shiftX);
		upperLeft.setYCenter(ulCenter.y + shiftY);
		upperRight.setXCenter(urCenter.x + shiftX);
		upperRight.setYCenter(urCenter.y + shiftY);
		bottomLeft.setXCenter(blCenter.x + shiftX);
		bottomLeft.setYCenter(blCenter.y + shiftY);
		bottomRight.setXCenter(brCenter.x + shiftX);
		bottomRight.setYCenter(brCenter.y + shiftY);
	}
	
	// takes in a shape and outlines it based on its type
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
	
	// 1, 2, 3 or 4 or 0 if not selected
	public boolean checkIfSelectedHandles(int qx, int qy) {
		if (selectedShape == null)
			return false;
		if (shapeManager.testInSquare((Square)upperLeft, qx, qy))
			whichHandleSelected = 1;
		else if (shapeManager.testInSquare((Square)upperRight, qx, qy))
			whichHandleSelected = 2;
		else if(shapeManager.testInSquare((Square)bottomLeft, qx, qy))
			whichHandleSelected = 3;
		else if(shapeManager.testInSquare((Square)bottomRight, qx, qy))
			whichHandleSelected = 4;
		else
			whichHandleSelected = 0;
		if (whichHandleSelected == 0)
			return false;
		return true;
	}
	
	public Point getAnchorPoint() {
		Point anchor = null;
		int xC = selectedShape.getXCenter();
		int yC = selectedShape.getYCenter();
		if (whichHandleSelected == 1) {
			int hxCenter = upperLeft.getXCenter();
			int hyCenter = upperLeft.getYCenter();
			anchor =
				new Point(xC + xC - (hxCenter + HIGHLIGHTER_WIDTH), yC + yC - (hyCenter + HIGHLIGHTER_WIDTH));
		}
		return anchor;
			
	}
	
	private void createOutlineForSquare (Square selectedShape) {
		int xCenter = selectedShape.getXCenter();
		int yCenter = selectedShape.getYCenter();
		int d = selectedShape.getWidth() / 2;
		upperLeft = new Square(xCenter - d - HIGHLIGHTER_WIDTH, yCenter - d - HIGHLIGHTER_WIDTH, HIGHLIGHTER_WIDTH, Color.red);
		upperRight= new Square(xCenter + d, yCenter - d - HIGHLIGHTER_WIDTH, HIGHLIGHTER_WIDTH, Color.red);
		bottomLeft = new Square(xCenter - d - HIGHLIGHTER_WIDTH, yCenter + d, HIGHLIGHTER_WIDTH, Color.red);
		bottomRight = new Square(xCenter + d, yCenter + d, HIGHLIGHTER_WIDTH, Color.red);
		ulCenter = new Point(upperLeft.getXCenter(), upperLeft.getYCenter());
		urCenter = new Point(upperRight.getXCenter(), upperRight.getYCenter());
		blCenter = new Point(bottomLeft.getXCenter(), bottomLeft.getYCenter());
		brCenter = new Point(bottomRight.getXCenter(), bottomRight.getYCenter());
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
