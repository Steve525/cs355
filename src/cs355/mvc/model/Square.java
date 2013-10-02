package cs355.mvc.model;

import java.awt.Color;

public class Square extends Shape {

	private int width;

	public Square(int x, int y, int width, Color color) {
		super(x + width/2, y + width/2, color);
		this.setWidth(width);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}
