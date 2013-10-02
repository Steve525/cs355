package cs355.mvc.model;

import java.awt.Color;

public class Ellipse extends Shape {

	// The Ellipse should store the height and the width
	private int height;
	private int width;

	public Ellipse(int x, int y, int height, int width, Color color) {
		super(x, y, color);
		this.setHeight(height);
		this.setWidth(width);
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}
