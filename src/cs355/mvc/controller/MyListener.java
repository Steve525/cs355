package cs355.mvc.controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.MouseInputAdapter;

import cs355.GUIFunctions;
import cs355.mvc.model.Circle;
import cs355.mvc.model.Ellipse;
import cs355.mvc.model.Line;
import cs355.mvc.model.Rectangle;
import cs355.mvc.model.Shape;
import cs355.mvc.model.ShapeManager;
import cs355.mvc.model.Square;
import cs355.mvc.model.Triangle;

public class MyListener extends MouseInputAdapter {
	
	private CS355ControllerImpl controller;
	private ShapeManager shapeManager;
	ButtonSelected buttonSelected;
	
	private Point origin;
	private Shape selectedShape;
	
	private List<Point> trianglePoints;
	
	public MyListener() {
		controller = CS355ControllerImpl.getInstance();
		shapeManager = ShapeManager.getInstance();
		trianglePoints = new ArrayList<Point>();
	}
	
	public void mousePressed(MouseEvent e) {
		buttonSelected = controller.buttonSelected;
		if (buttonSelected == null || buttonSelected == ButtonSelected.TRIANGLE)
			return;
		if (buttonSelected == ButtonSelected.SELECT) {
			selectedShape = select(e.getX(), e.getY());
			if (selectedShape != null) {
				System.out.println("Creating outline");
				shapeManager.createOutline(selectedShape);
			}
		}
		trianglePoints.clear();
		origin = e.getPoint();
		System.out.println("About to refresh...");
		GUIFunctions.refresh();
	}
	
	public void mouseDragged(MouseEvent e) {
		if (buttonSelected == null || buttonSelected == ButtonSelected.TRIANGLE
				|| buttonSelected == ButtonSelected.SELECT)
			return;
		updateSize(e, false);
	}
	
	public void mouseReleased(MouseEvent e) {
		if (buttonSelected == null)
			return;
		if (buttonSelected == ButtonSelected.SELECT) {
			System.out.println("RELEASED!");
			refresh();
			return;
		}
		if (buttonSelected == ButtonSelected.TRIANGLE) {
			if (trianglePoints.size() < 3)
				trianglePoints.add(e.getPoint());
			if (trianglePoints.size() == 3)
				updateSize(e, true);
		}
		else
			updateSize(e, true);
	}
	
	private void refresh() {
		GUIFunctions.refresh();
	}

	private void updateSize(MouseEvent e, boolean finishedDrawing) {
		switch (buttonSelected) {
		case RECTANGLE:
			updateRectangle(e, finishedDrawing);
			break;
		case SQUARE:
			updateSquare(e, finishedDrawing);
			break;
		case LINE:
			updateLine(e, finishedDrawing);
			break;
		case ELLIPSE:
			updateEllipse(e, finishedDrawing);
			break;
		case CIRCLE:
			updateCircle(e, finishedDrawing);
			break;
		case TRIANGLE:
			updateTriangle(e, finishedDrawing);
			break;
		default:
			break;
		}
		GUIFunctions.refresh();
	}
	
	private void updateRectangle(MouseEvent e, boolean finishedDrawing) {
		int x = e.getX();
		int y = e.getY();
		int width = x - origin.x;
		int height = y - origin.y;
		int ul_x = origin.x;
		int ul_y = origin.y;
		if (width < 0) {
			width = 0 - width;
			ul_x = x;
		}
		if (height < 0) {
			height = 0 - height;
			ul_y = y;
		}
		Shape r = new Rectangle(ul_x, ul_y, height, width, controller.colorSelected);
		if (finishedDrawing)
			shapeManager.addShape(r);
		else
			shapeManager.addCurrentDrawing(r);
	}
	
	private void updateSquare(MouseEvent e, boolean finishedDrawing) {
		int x = e.getX();
		int y = e.getY();
		int width = x - origin.x;
		int height = y - origin.y;
		int ul_x = origin.x;
		int ul_y = origin.y;
		int shortestSide = Math.min(Math.abs(width), Math.abs(height));
		if (width < 0) {
			ul_x = ul_x - shortestSide;
		}
		if (height < 0) {
			ul_y = ul_y - shortestSide;
		}
		Shape sq = new Square(ul_x, ul_y, shortestSide, controller.colorSelected);
		if (finishedDrawing)
			shapeManager.addShape(sq);
		else
			shapeManager.addCurrentDrawing(sq);
	}
	
	private void updateLine(MouseEvent e, boolean finishedDrawing) {
		int x = e.getX();
		int y = e.getY();
		Shape ln = new Line(origin.x, origin.y, x, y, controller.colorSelected);
		
		if (finishedDrawing)
			shapeManager.addShape(ln);
		else
			shapeManager.addCurrentDrawing(ln);
	}
	
	private void updateEllipse(MouseEvent e, boolean finishedDrawing) {
		int x = e.getX();
		int y = e.getY();
		int width = x - origin.x;
		int height = y - origin.y;
		int ul_x = origin.x;
		int ul_y = origin.y;
		if (width < 0) {
			width = 0 - width;
			ul_x = x;
		}
		if (height < 0) {
			height = 0 - height;
			ul_y = y;
		}
		ul_x = ul_x + width/2;
		ul_y = ul_y + height/2;
		// ul_x and ul_y are now the center of the ellipse
		Shape ellipse = new Ellipse(ul_x, ul_y, height, width, controller.colorSelected);
		if (finishedDrawing)
			shapeManager.addShape(ellipse);
		else
			shapeManager.addCurrentDrawing(ellipse);
	}
	
	private void updateCircle(MouseEvent e, boolean finishedDrawing) {
		int x = e.getX();
		int y = e.getY();
		int width = x - origin.x;
		int height = y - origin.y;
		int ul_x = origin.x;
		int ul_y = origin.y;
		int shortestSide = Math.min(Math.abs(width), Math.abs(height));
		if (width < 0) {
			ul_x = ul_x - shortestSide;
		}
		if (height < 0) {
			ul_y = ul_y - shortestSide;
		}
		int radius = shortestSide/2;
		ul_x = ul_x + radius;
		ul_y = ul_y + radius;
		// ul_x and ul_y are now the center of the circle
		Shape circle = new Circle(ul_x, ul_y, radius, controller.colorSelected);
		if (finishedDrawing)
			shapeManager.addShape(circle);
		else
			shapeManager.addCurrentDrawing(circle);
	}
	
	private void updateTriangle(MouseEvent e, boolean finishedDrawing) {
		int[] x = new int[3];
		int[] y = new int[3];
		x[0] = trianglePoints.get(0).x;
		y[0] = trianglePoints.get(0).y;
		x[1] = trianglePoints.get(1).x;
		y[1] = trianglePoints.get(1).y;
		x[2] = trianglePoints.get(2).x;
		y[2] = trianglePoints.get(2).y;
		int center_x = (x[0] + x[1] + x[2]) / 3;
		int center_y = (y[0] + y[1] + y[2]) / 3;
		Shape triangle = new Triangle(x, y, center_x, center_y, controller.colorSelected);
		shapeManager.addShape(triangle);
		shapeManager.addCurrentDrawing(null);
		trianglePoints.clear();
	}
	
	private Shape select(int x, int y) {
		List<Shape> shapes = shapeManager.getShapes();
		for (int i = shapes.size() - 1; i >= 0; i--) {
			if (shapes.get(i) instanceof Square) {
				Square selected = (Square) shapes.get(i);
				if (testInSquare(selected, x, y)) {
					System.out.println("You selected a square!");
					return shapes.get(i);
				}
			}
			
		}
		return null;
	}
	
	private boolean testInSquare(Square square, int qx, int qy) {
		int x = square.getXCenter();
		int y = square.getYCenter();
		int d = square.getWidth() / 2;
		if (Math.abs(qx - x) <= d && Math.abs(qy - y) <= d)
			return true;
		return false;
	}
	
}
