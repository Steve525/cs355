package cs355.mvc.model.manage;

import java.util.ArrayList;
import java.util.List;

import cs355.mvc.controller.Action;
import cs355.mvc.model.Shape;


public class ShapeManager {
	
	private static ShapeManager instance = null;
	
	private List<Shape> shapes = new ArrayList<Shape>();
	
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
	
	public void addShape(Shape shape, Action action) {
		if (action == Action.STARTED_DRAWING) {
			shapes.add(shape);
		}
		else if (action == Action.CURRENTLY_DRAWING
				 || action == Action.FINISHED_DRAWING) {
			shapes.remove(shapes.size()-1);
			shapes.add(shape);
		}
	}
	
}
