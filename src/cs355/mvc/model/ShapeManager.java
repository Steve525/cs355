package cs355.mvc.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


public class ShapeManager {
	
	private static ShapeManager instance = null;
	
	private List<Shape> shapes = new ArrayList<Shape>();
	
	private Shape currentShape = null;
	
	private Shape[] topHighlighters = null;
	private Shape[] bottomHighlighters = null;
	private Shape selectedShape = null;
	private boolean isShapeSelected = false;
	
	private static final int HIGHLIGHTER_WIDTH = 10;

	protected ShapeManager() {
		// prevents instantiation
	}
	
	public static ShapeManager getInstance() {
		if (instance == null) {
			instance = new ShapeManager();
		}
		return instance;
	}
	
	public List<Shape> getShapes() {
		return shapes;
	}
	
	public void addShape(Shape shape) {
		shapes.add(shape);
		currentShape = null;
		isShapeSelected = false;
	}
	
	public void addCurrentDrawing(Shape shape) {
		currentShape = shape;
	}
	
	public Shape getCurrentDrawing() {
		return currentShape;
	}
	
	public Shape getCurrentSelected() {
		return this.selectedShape;
	}
	
	public Shape[] getTopOutline() {
		return this.topHighlighters;
	}
	
	public Shape[] getBottomOutline() {
		return this.bottomHighlighters;
	}
	
	public boolean isSomethingSelected() {
		return this.isShapeSelected;
	}
	
	public void createOutline(int i) {
		this.selectedShape = shapes.get(i);
		isShapeSelected = true;
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
		
		Shape r1 = new Square(xCenter - d - HIGHLIGHTER_WIDTH, yCenter - d - HIGHLIGHTER_WIDTH, HIGHLIGHTER_WIDTH, Color.red);
		Shape r2 = new Square(xCenter + d, yCenter - d - HIGHLIGHTER_WIDTH, HIGHLIGHTER_WIDTH, Color.red);
		Shape r3 = new Square(xCenter - d - HIGHLIGHTER_WIDTH, yCenter + d, HIGHLIGHTER_WIDTH, Color.red);
		Shape r4 = new Square(xCenter + d, yCenter + d, HIGHLIGHTER_WIDTH, Color.red);
		
		topHighlighters =  new Square[2];
		bottomHighlighters = new Square[2];
		topHighlighters[0] = r1;
		topHighlighters[1] = r2;
		bottomHighlighters[0] = r3;
		bottomHighlighters[1] = r4;
	}

}
