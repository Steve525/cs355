package cs355.mvc.model;

import java.awt.Color;

public abstract class Shape {
	
	protected Color color;
	protected double rotation_radians;
	protected int x_center;
	protected int y_center;

	public Shape(int x_center, int y_center, Color color) {
		this.color = color;
		rotation_radians = 0;
		this.x_center = x_center;
		this.y_center = y_center;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color c) {
		this.color = c;
	}
	
	public double getRotation() {
		return rotation_radians;
	}
	
	public void setRotation(double rotation_radians) {
		this.rotation_radians = rotation_radians; 
	}
	
	public int getXCenter() {
		return this.x_center;
	}
	
	public void setXCenter(int x_center) {
		this.x_center = x_center;
	}
	
	public int getYCenter() {
		return this.y_center;
	}
	
	public void setYCenter(int y_center) {
		this.y_center = y_center;
	}

}
