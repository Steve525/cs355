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
import cs355.mvc.model.Square;
import cs355.mvc.model.Triangle;
import cs355.mvc.model.manage.HandleSelectorManager;
import cs355.mvc.model.manage.ShapeManager;

public class MyListener extends MouseInputAdapter {
	
	private CS355ControllerImpl controller;
	private ShapeManager shapeManager;
	private HandleSelectorManager handleManager;
	ButtonSelected buttonSelected;
	
	private Point pointClicked;
	private int xOrigin;
	private int yOrigin;
	private Shape shapeSelected;
	private boolean initializeShape;
	
	private List<Point> trianglePoints;
	
	public MyListener() {
		controller = CS355ControllerImpl.getInstance();
		shapeManager = ShapeManager.getInstance();
		handleManager = HandleSelectorManager.getInstance();
		trianglePoints = new ArrayList<Point>();
		initializeShape = true;
	}
	
	public void mousePressed(MouseEvent e) {
		buttonSelected = controller.buttonSelected;
		if (buttonSelected == null)
			return;
		
		pointClicked = e.getPoint();
		if (buttonSelected == ButtonSelected.SELECT) {
			shapeSelected = select(e.getX(), e.getY()); // index of shape in list
			xOrigin = shapeSelected.getXCenter();
			yOrigin = shapeSelected.getYCenter();
			handleManager.createOutline(shapeSelected);	
			GUIFunctions.refresh();
		}
		else if (buttonSelected == ButtonSelected.DRAW_TRIANGLE) {
			if (trianglePoints.size() < 3)
				trianglePoints.add(e.getPoint());
			if (trianglePoints.size() == 3) {
				createOrUpdateShape(e, Action.STARTED_DRAWING);
				trianglePoints.clear();
			}
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		if (buttonSelected == null || buttonSelected == ButtonSelected.DRAW_TRIANGLE)
			return;
		if (buttonSelected == ButtonSelected.SELECT) {
			if (shapeSelected != null) {	// shape has been selected, now we're dragging
				// new center is shifted by distance between point clicked and event point
				int xShift = e.getX() - pointClicked.x;
				int yShift = e.getY() - pointClicked.y;
				System.out.println("xShift: " + xShift + " yShift: " + yShift);
				shapeSelected.setXCenter(xOrigin + xShift);
				shapeSelected.setYCenter(yOrigin + yShift);
				GUIFunctions.refresh();
			}
		}
		else {
			if (initializeShape) {
				createOrUpdateShape(e, Action.STARTED_DRAWING);
				initializeShape = false;
			}
			else {
				createOrUpdateShape(e, Action.CURRENTLY_DRAWING);
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		if (buttonSelected == null || buttonSelected == ButtonSelected.DRAW_TRIANGLE)
			return;
		if (!initializeShape) {
			createOrUpdateShape(e, Action.FINISHED_DRAWING);
			initializeShape = true;
		}
	}
	
	private void createOrUpdateShape(MouseEvent e, Action action) {
		shapeManager.addShape(updateSize(e), action);
		GUIFunctions.refresh();
	}
	
	private Shape updateSize(MouseEvent e) {
		Shape shape = null;
		switch (buttonSelected) {
		case DRAW_RECTANGLE:
			shape = updateRectangle(e);
			break;
		case DRAW_SQUARE:
			shape = updateSquare(e);
			break;
		case DRAW_LINE:
			shape = updateLine(e);
			break;
		case DRAW_ELLIPSE:
			shape = updateEllipse(e);
			break;
		case DRAW_CIRCLE:
			shape = updateCircle(e);
			break;
		case DRAW_TRIANGLE:
			shape = updateTriangle(e);
			break;
		default:
			break;
		}
		return shape;
	}
	
	private Shape updateRectangle(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int width = x - pointClicked.x;
		int height = y - pointClicked.y;
		int ul_x = pointClicked.x;
		int ul_y = pointClicked.y;
		if (width < 0) {
			width = 0 - width;
			ul_x = x;
		}
		if (height < 0) {
			height = 0 - height;
			ul_y = y;
		}
		Shape r = new Rectangle(ul_x, ul_y, height, width, controller.colorSelected);
		return r;
	}
	
	private Shape updateSquare(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int width = x - pointClicked.x;
		int height = y - pointClicked.y;
		int ul_x = pointClicked.x;
		int ul_y = pointClicked.y;
		int shortestSide = Math.min(Math.abs(width), Math.abs(height));
		if (width < 0) {
			ul_x = ul_x - shortestSide;
		}
		if (height < 0) {
			ul_y = ul_y - shortestSide;
		}
		Shape sq = new Square(ul_x, ul_y, shortestSide, controller.colorSelected);
		return sq;
	}
	
	private Shape updateLine(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		Shape ln = new Line(pointClicked.x, pointClicked.y, x, y, controller.colorSelected);
		return ln;
	}
	
	private Shape updateEllipse(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int width = x - pointClicked.x;
		int height = y - pointClicked.y;
		int ul_x = pointClicked.x;
		int ul_y = pointClicked.y;
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
		return ellipse;
	}
	
	private Shape updateCircle(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int width = x - pointClicked.x;
		int height = y - pointClicked.y;
		int ul_x = pointClicked.x;
		int ul_y = pointClicked.y;
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
		return circle;
	}
	
	private Shape updateTriangle(MouseEvent e) {
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
		trianglePoints.clear();
		return triangle;
	}
	
	private Shape select(int qx, int qy) {
		List<Shape> shapes = shapeManager.getShapes();
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
	
	// 1, 2, 3 or 4 or 0 if not selected
	private int checkIfSelectedHandles(int qx, int qy) {
		if (testInSquare(handleManager.getUpperLeft(), qx, qy))
			return 1;
		else if (testInSquare(handleManager.getUpperRight(), qx, qy))
			return 2;
		else if(testInSquare(handleManager.getBottomLeft(), qx, qy))
			return 3;
		else if(testInSquare(handleManager.getBottomRight(), qx, qy))
			return 4;
		
		return 0;
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
