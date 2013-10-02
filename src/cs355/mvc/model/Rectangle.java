package cs355.mvc.model;

import java.awt.Color;

public class Rectangle extends Shape {
	
	private int height;
	private int width;

	public Rectangle(int x, int y, int height, int width, Color color) {
		super(x + width/2, y + height/2, color);
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
