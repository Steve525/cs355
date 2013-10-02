package cs355.mvc.model;

import java.awt.Color;

public class Circle extends Shape {

	// The Circle should store its radius
	private int radius;

	public Circle(int x, int y, int radius, Color color) {
		super(x, y, color);
		this.radius = radius;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

}
