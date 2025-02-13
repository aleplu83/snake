package main;

import java.awt.Point;

public class Fruit {
	
	private Point pos;

	public Fruit(Point[] excluded) {
		int rndX=0;
		int rndY=0;
		
		for (int i=0;i<excluded.length;i++) { // make sure we do not place the fruit on the snake's body :-)
			do {
				
				rndX=getRnd(10,490);
				rndY=getRnd(10,490);
				
			} while (excluded[i].x == rndX || excluded[i].y == rndY);
			
		}
		
		pos=new Point(rndX,rndY);
	}

	/**
	 * @return the pos
	 */
	public Point getPos() {
		return pos;
	}
	
	private int getRnd(int min,int max) {
		int rndNumber;
		do {
			rndNumber=((int) (Math.random() * 500));
		} while (rndNumber < min || rndNumber > max);
		
		return (int) (Math.ceil(rndNumber / 10.0)*10);
	}
}
