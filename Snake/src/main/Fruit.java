package main;

import java.awt.Color;
import java.awt.Point;

public class Fruit {

	private Point pos;
	private Color color;
	private int value;
	private int timeout=-1; // if value greater than 0 this fruit will disappear after 'value' seconds have passed
	
	/**
	 * @param pos position on the field
	 * @param color colour of the fruit
	 * @param value how many points is this fruit 
	 * @param timeout timeout in seconds before the fruit disappears
	 */
	public Fruit(Point pos, Color color, int value, int timeout) {
		super();
		this.pos = pos;
		this.color = color;
		this.value = value;
		this.timeout = timeout;
	}
	
	public Fruit() {
		
	}

	/**
	 * @return returns Point object with the position of this fruit
	 */
	public Point getPos() {
		return pos;
	}

	/**
	 * @param pos the pos to set
	 */
	public void setPos(Point pos) {
		this.pos = pos;
	}

	/**
	 * @return the colour
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the colour to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * @return the timeout
	 */
	public int getTimeout() {
		return timeout;
	}

	/**
	 * @param timeout the timeout to set
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	
	
	
}
