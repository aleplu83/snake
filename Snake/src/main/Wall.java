package main;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

public class Wall {
 
	private Rectangle[] blocks;
	private Color color;
	
	public Wall(Color color,int startX,int startY,int length) {
		// TODO Auto-generated constructor stub
		blocks = new Rectangle[length];
		this.color=color;
		
	}

}
