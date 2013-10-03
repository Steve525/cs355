package cs355.mvc.model.manage;

import java.util.ArrayList;
import java.util.List;

import cs355.mvc.controller.Action;
import cs355.mvc.model.Shape;
import cs355.mvc.model.Square;


public class ShapeManager {
	
	private static ShapeManager instance = null;
	
	private List<Shape> shapes = new ArrayList<Shape>();
	
	private Shape selectedShape = null;
	private int xOriginSelected;
	private int yOriginSelected;
	
	protected ShapeManager() {
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
	
	public void setSelectedShape(Shape selected) {
		selectedShape = selected;
		if (selectedShape != null) {
			xOriginSelected = selectedShape.getXCenter();
			yOriginSelected = selectedShape.getYCenter();
		}
	}
	
	public Shape select(int qx, int qy) {
		for (int i = shapes.size() - 1; i >= 0; i--) {
			Shape shape = shapes.get(i);
			if (shape instanceof Square) {
				if (testInSquare((Square) shape, qx, qy)) {
					System.out.println("Square selected!");
					return shape;
				}
			}
			
		}
		return null;
	}
	
	public boolean testInSquare(Square square, int qx, int qy) {
		int x = square.getXCenter();
		int y = square.getYCenter();
		int d = square.getWidth() / 2;
		if (Math.abs(qx - x) <= d && Math.abs(qy - y) <= d)
			return true;
		return false;
	}
	
	public void addShape(Shape shape, Action action) {
		if (action == Action.STARTED_DRAWING) {
			shapes.add(shape);
		}
		else if (action == Action.CURRENTLY_DRAWING
				 || action == Action.FINISHED_DRAWING) {
			shapes.remove(shapes.size()-1);
			shapes.add(shape);
		}
		else if (action == Action.UPDATE_SELECTED) {
			selectedShape = shape;
		}
	}
	
	public void shiftShape(int xShift, int yShift) {
		selectedShape.setXCenter(xOriginSelected + xShift);
		selectedShape.setYCenter(yOriginSelected + yShift);
	}
	
}
