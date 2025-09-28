package main;

import java.awt.Color;
import java.awt.Point;

public class Fruit {

	private Point pos;
	private Color color;
	
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
}
