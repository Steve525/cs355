package cs355.mvc.model;

import java.awt.Color;

public class Triangle extends Shape {
	
	// All relative to the center of the triangle
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private int x3;
	private int y3;

	public Triangle(int[] x, int[] y, int center_x, int center_y, Color color) {
		super(center_x, center_y, color);
		this.setX1(x[0] - center_x);
		this.setX2(x[1] - center_x);
		this.setX3(x[2] - center_x);
		this.setY1(y[0] - center_y);
		this.setY2(y[1] - center_y);
		this.setY3(y[2] - center_y);
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public int getX3() {
		return x3;
	}

	public void setX3(int x3) {
		this.x3 = x3;
	}

	public int getY3() {
		return y3;
	}

	public void setY3(int y3) {
		this.y3 = y3;
	}
	
	public int[] getRelativeX() {
		int[] allX = new int[3];
		allX[0] = x1 + this.getXCenter();
		allX[1] = x2 + this.getXCenter();
		allX[2] = x3 + this.getXCenter();
		return allX;
	}
	
	public int[] getRelativeY() {
		int[] allY = new int[3];
		allY[0] = y1 + this.getYCenter();
		allY[1] = y2 + this.getYCenter();
		allY[2] = y3 + this.getYCenter();
		return allY;
	}

}
