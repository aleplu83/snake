package main;

import java.awt.Point;

public class Fruit {
	
	private Point fruitPos;

	public Fruit(Point[] snakePos) {
		do {
			fruitPos=new Point(getRnd(10,490),getRnd(10,490));
		} while (isSnakeBody(snakePos, fruitPos));
	}
	
	private boolean isSnakeBody(Point [] snakeBody,Point fruitPos) {
		for (int i=0;i<snakeBody.length;i++) { 
			if (fruitPos.x==snakeBody[i].x && fruitPos.y==snakeBody[i].y) {
				return true;
			}	
		}
		return false;
	}

	/**
	 * @return the pos
	 */
	public Point getFruitPos() {
		return fruitPos;
	}
	
	private int getRnd(int min,int max) {
		int rndNumber;
		do {
			rndNumber=((int) (Math.random() * 500));
		} while (rndNumber < min || rndNumber > max);
		
		return (int) (Math.ceil(rndNumber / 10.0)*10);
	}
}
