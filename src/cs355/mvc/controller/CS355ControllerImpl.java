package cs355.mvc.controller;

import java.awt.Color;

import cs355.CS355Controller;
import cs355.GUIFunctions;

public class CS355ControllerImpl implements CS355Controller {

	private static CS355ControllerImpl instance = null;
	
	public ButtonSelected buttonSelected;
	public Color colorSelected;

	// empty constructor to prevent instantiation
	protected CS355ControllerImpl() {}
	
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
	}

	@Override
	public void triangleButtonHit() {
		this.buttonSelected = ButtonSelected.TRIANGLE;
	}

	@Override
	public void squareButtonHit() {
		this.buttonSelected = ButtonSelected.SQUARE;
	}

	@Override
	public void rectangleButtonHit() {
		this.buttonSelected = ButtonSelected.RECTANGLE;
	}

	@Override
	public void circleButtonHit() {
		this.buttonSelected = ButtonSelected.CIRCLE;
	}

	@Override
	public void ellipseButtonHit() {
		this.buttonSelected = ButtonSelected.ELLIPSE;
	}

	@Override
	public void lineButtonHit() {
		this.buttonSelected = ButtonSelected.LINE;
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
