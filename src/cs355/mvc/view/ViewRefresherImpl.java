package cs355.mvc.view;

import java.awt.Graphics2D;
import java.util.List;

import cs355.ViewRefresher;
import cs355.mvc.controller.ButtonSelected;
import cs355.mvc.controller.CS355ControllerImpl;
import cs355.mvc.model.Circle;
import cs355.mvc.model.Ellipse;
import cs355.mvc.model.Line;
import cs355.mvc.model.Rectangle;
import cs355.mvc.model.Shape;
import cs355.mvc.model.ShapeManager;
import cs355.mvc.model.Square;
import cs355.mvc.model.Triangle;

public class ViewRefresherImpl implements ViewRefresher {
	
	private ShapeManager shapeManager;
	private CS355ControllerImpl controller;

	public ViewRefresherImpl() {
		shapeManager = ShapeManager.getInstance();
		controller = CS355ControllerImpl.getInstance();
	}

	@Override
	public void refreshView(Graphics2D g2d) {		
		// Update the canvas with all previously drawn shapes...
		List<Shape> shapes = shapeManager.getShapes();
		for (Shape s : shapes) {
			if (s.getClass() == Rectangle.class)
				drawRectangle(g2d, (Rectangle)s);
			if (s.getClass() == Square.class)
				drawSquare(g2d, (Square)s);
			if (s.getClass() == Line.class)
				drawLine(g2d, (Line)s);
			if (s.getClass() == Ellipse.class)
				drawEllipse(g2d, (Ellipse)s);
			if (s.getClass() == Circle.class)
				drawCircle(g2d, (Circle)s);
			if (s.getClass() == Triangle.class)
				drawTriangle(g2d, (Triangle)s);
		}
		// Update the canvas with the current shape being drawn...
		Shape currentShape = shapeManager.getCurrentDrawing();
		if (currentShape != null) {
			if (currentShape.getClass() == Rectangle.class)
				drawRectangle(g2d, (Rectangle)currentShape);
			if (currentShape.getClass() == Square.class)
				drawSquare(g2d, (Square)currentShape);
			if (currentShape.getClass() == Line.class)
				drawLine(g2d, (Line)currentShape);
			if (currentShape.getClass() == Ellipse.class)
				drawEllipse(g2d, (Ellipse)currentShape);
			if (currentShape.getClass() == Circle.class)
				drawCircle(g2d, (Circle)currentShape);
			if (currentShape.getClass() == Triangle.class)
				drawTriangle(g2d, (Triangle)currentShape);
		}
		
		if (controller.buttonSelected == ButtonSelected.SELECT) {
			Shape[] topOutline = shapeManager.getTopOutline();
			Shape[] bottomOutline = shapeManager.getBottomOutline();
			drawSquare(g2d, (Square)topOutline[0]);
			drawSquare(g2d, (Square)topOutline[1]);
			drawSquare(g2d, (Square)bottomOutline[0]);
			drawSquare(g2d, (Square)bottomOutline[1]);
		}
		System.out.println("Drawing highlights!");
	}
	
	private void drawRectangle(Graphics2D g2d, Rectangle r) {
		int height = r.getHeight();
		int width = r.getWidth();
		int x = r.getXCenter() - width/2;
		int y = r.getYCenter() - height/2;
		g2d.setColor(r.getColor());
		g2d.fillRect(x, y, width, height);
	}
	
	private void drawSquare(Graphics2D g2d, Square s) {
		int width = s.getWidth();
		int x = s.getXCenter() - width/2;
		int y = s.getYCenter() - width/2;
		g2d.setColor(s.getColor());
		g2d.fillRect(x, y, width, width);
	}
	
	private void drawLine(Graphics2D g2d, Line ln) {
		g2d.setColor(ln.getColor());
		g2d.drawLine(ln.getX1(), ln.getY1(), ln.getX2(), ln.getY2());
	}
	
	private void drawEllipse(Graphics2D g2d, Ellipse e) {
		int width = e.getWidth();
		int height = e.getHeight();
		int ul_x = e.getXCenter() - width/2;
		int ul_y = e.getYCenter() - height/2;
		g2d.setColor(e.getColor());
		g2d.fillOval(ul_x, ul_y, width, height);
	}
	
	private void drawCircle(Graphics2D g2d, Circle circ) {
		g2d.setColor(circ.getColor());
		int radius = circ.getRadius();
		int ul_x = circ.getXCenter() - radius;
		int ul_y = circ.getYCenter() - radius;
		g2d.fillOval(ul_x, ul_y, radius*2, radius*2);
	}
	
	private void drawTriangle(Graphics2D g2d, Triangle triangle) {
		g2d.setColor(triangle.getColor());
		g2d.fillPolygon(triangle.getRelativeX(), triangle.getRelativeY(), 3);
	}

}
