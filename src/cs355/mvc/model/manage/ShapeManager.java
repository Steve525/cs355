package cs355.mvc.model.manage;

import java.util.ArrayList;
import java.util.List;

import cs355.mvc.controller.Action;
import cs355.mvc.model.Circle;
import cs355.mvc.model.Ellipse;
import cs355.mvc.model.Rectangle;
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
	
	public Shape getSelectedShape() {
		return this.selectedShape;
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
			else if (shape instanceof Circle) {
				if (testInCircle((Circle)shape, qx, qy)) {
					System.out.println("Circle selected!");
					return shape;
				}
			}
			else if (shape instanceof Rectangle) {
				if (testInRectangle((Rectangle)shape, qx, qy)) {
					System.out.println("Rectangle selected!");
					return shape;
				}
			}
			else if (shape instanceof Ellipse) {
				if (testInEllipse((Ellipse)shape, qx, qy)) {
					System.out.println("Ellipse selected");
					return shape;
				}
			}
		}
		return null;
	}
	
	public boolean testInCircle(Circle circle, int qx, int qy) {
		int x = circle.getXCenter();
		int y = circle.getYCenter();
		int radius = circle.getRadius();
		int xDist = (qx - x) * (qx - x);
		int yDist = (qy - y) * (qy - y);
		int distance = (int)Math.sqrt((double)xDist + (double)yDist);
		if (distance <= radius)
			return true;
		return false;
	}
	
	public boolean testInSquare(Square square, int qx, int qy) {
		int x = square.getXCenter();
		int y = square.getYCenter();
		int d = square.getWidth() / 2;
		if (Math.abs(qx - x) <= d && Math.abs(qy - y) <= d)
			return true;
		return false;
	}
	
	public boolean testInRectangle(Rectangle rectangle, int qx, int qy) {
		int x = rectangle.getXCenter();
		int y = rectangle.getYCenter();
		int widthHalf = rectangle.getWidth() / 2;
		int heightHalf = rectangle.getHeight() / 2;
		if (Math.abs(qx - x) <= widthHalf && Math.abs(qy-y) <= heightHalf)
			return true;
		return false;
	}
	
	public boolean testInEllipse(Ellipse ellipse, int qx, int qy) {
		int x = ellipse.getXCenter();
		int y = ellipse.getYCenter();
		double widthHalf = ellipse.getWidth() / 2;
		double heightHalf = ellipse.getHeight() / 2;
		double a = ((qx - x) * (qx - x)) / (widthHalf * widthHalf);
		double b = ((qy - y) * (qy - y)) / (heightHalf * heightHalf);
		if (a + b <= 1.0)
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
			int i = findIndexOfShape(selectedShape);
			setSelectedShape(shape);
			shapes.set(i, selectedShape);
		}
	}
	
	private int findIndexOfShape(Shape s) {
		for (int i = 0; i < shapes.size(); i++) {
			if (s == shapes.get(i))
				return i;
		}
		return -1;
	}
	
	public void shiftShape(int xShift, int yShift) {
		selectedShape.setXCenter(xOriginSelected + xShift);
		selectedShape.setYCenter(yOriginSelected + yShift);
	}
	
}
