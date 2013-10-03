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
import cs355.mvc.model.Square;
import cs355.mvc.model.Triangle;
import cs355.mvc.model.manage.HandleSelectorManager;
import cs355.mvc.model.manage.ShapeManager;

public class ViewRefresherImpl implements ViewRefresher {
	
	private ShapeManager shapeManager;
	private HandleSelectorManager handleManager;
	private CS355ControllerImpl controller;

	public ViewRefresherImpl() {
		shapeManager = ShapeManager.getInstance();
		handleManager = HandleSelectorManager.getInstance();
		controller = CS355ControllerImpl.getInstance();
	}

	@Override
	public void refreshView(Graphics2D g2d) {		
		List<Shape> shapes = shapeManager.getShapes();
		for (Shape s : shapes) {
			if (s instanceof Rectangle)
				drawRectangle(g2d, (Rectangle)s);
			if (s instanceof Square)
				drawSquare(g2d, (Square)s);
			if (s instanceof Line)
				drawLine(g2d, (Line)s);
			if (s instanceof Ellipse)
				drawEllipse(g2d, (Ellipse)s);
			if (s instanceof Circle)
				drawCircle(g2d, (Circle)s);
			if (s instanceof Triangle)
				drawTriangle(g2d, (Triangle)s);
		}
		
		if (controller.buttonSelected == ButtonSelected.SELECT
				&& handleManager.isSomethingSelected()) {
			drawSquare(g2d, handleManager.getUpperLeft());
			drawSquare(g2d, handleManager.getUpperRight());
			drawSquare(g2d, handleManager.getBottomLeft());
			drawSquare(g2d, handleManager.getBottomRight());
		}
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
		g2d.fillRect(0, 0, width, width);
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
