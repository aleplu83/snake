package main;

import java.awt.Color;
import java.awt.Rectangle;

public class Wall {
 
	private Rectangle[] blocks;
	private Color color;
	
	public Wall(Color color,int startX,int startY,int endX,int endY) {
		this.color=color;
	}

	/**
	 * @return the blocks
	 */
	public Rectangle[] getBlocks() {
		return blocks;
	}

	/**
	 * @param blocks the blocks to set
	 */
	public void setBlocks(Rectangle[] blocks) {
		this.blocks = blocks;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	

}
