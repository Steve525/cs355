package cs355.mvc.controller;

import java.awt.Color;

import cs355.CS355Controller;
import cs355.GUIFunctions;
import cs355.mvc.model.Shape;
import cs355.mvc.model.manage.ShapeManager;

public class CS355ControllerImpl implements CS355Controller {

	private static CS355ControllerImpl instance = null;
	
	public ButtonSelected buttonSelected;
	public Color colorSelected;
	public ShapeManager shapeManager;

	protected CS355ControllerImpl() {
		shapeManager = ShapeManager.getInstance();
	}
	
	public static CS355ControllerImpl getInstance() {
		if (instance == null) {
			instance = new CS355ControllerImpl();
		}
		return instance;
	}

	@Override
	public void colorButtonHit(Color c) {
		if (c != null) {
			GUIFunctions.changeSelectedColor(c);
			colorSelected = c;
		}
		if (buttonSelected == ButtonSelected.SELECT
				&& shapeManager.getSelectedShape() != null) {
			shapeManager.getSelectedShape().setColor(c);
		}
	}
	
	private void deselectCurrentShape() {
		Shape s = shapeManager.getSelectedShape();
		s = null;
	}

	@Override
	public void triangleButtonHit() {
		this.buttonSelected = ButtonSelected.DRAW_TRIANGLE;
	}

	@Override
	public void squareButtonHit() {
		this.buttonSelected = ButtonSelected.DRAW_SQUARE;
		deselectCurrentShape();
	}

	@Override
	public void rectangleButtonHit() {
		this.buttonSelected = ButtonSelected.DRAW_RECTANGLE;
	}

	@Override
	public void circleButtonHit() {
		this.buttonSelected = ButtonSelected.DRAW_CIRCLE;
	}

	@Override
	public void ellipseButtonHit() {
		this.buttonSelected = ButtonSelected.DRAW_ELLIPSE;
	}

	@Override
	public void lineButtonHit() {
		this.buttonSelected = ButtonSelected.DRAW_LINE;
	}

	@Override
	public void selectButtonHit() {
		this.buttonSelected = ButtonSelected.SELECT;
	}

	@Override
	public void zoomInButtonHit() {
		this.buttonSelected = ButtonSelected.ZOOM_IN;
	}

	@Override
	public void zoomOutButtonHit() {
		this.buttonSelected = ButtonSelected.ZOOM_OUT;
	}

	@Override
	public void hScrollbarChanged(int value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void vScrollbarChanged(int value) {
		// TODO Auto-generated method stub

	}

}
