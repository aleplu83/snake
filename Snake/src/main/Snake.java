package main;

import java.awt.Point;

public class Snake {

	private Direction direction = Direction.EAST;
	private Point[] body;
	private Point[] newBody;
	private Point position = new Point(80,80);
	private int size=4; // start length of the snake. 4 blocks of 10px

	public Snake() {
		super();
		create();
	}
	
	public void create() {
		body = new Point[size];
		body[0]=position;
		switch (direction) {
		case Direction.EAST:
			for (int i=1;i<size;i++) {
				body[i]=new Point(position.x-(i*10),position.y);
			}
			break;
		case Direction.WEST:
			for (int i=1;i<size;i++) {
				body[i]=new Point(position.x+(i*10),position.y);
			}
			break;
		case Direction.NORTH:
			for (int i=1;i<size;i++) {
				body[i]=new Point(position.x,position.y+(i*10));
			}
			break;
		case Direction.SOUTH:
			for (int i=1;i<size;i++) {
				body[i]=new Point(position.x,position.y-(i*10));
			}
			break;
		}
	}
	
	public void grow() {
		size++;
		switch (direction) {
		case Direction.EAST:
			newBody = new Point[size];
			System.arraycopy(body, 0, newBody,0,body.length);
			body= new Point[size];
			body=newBody;
			body[size-1] = new Point(position.x+((size-1)*10),position.y);
		break;
		case Direction.WEST:	
			newBody = new Point[size];
			System.arraycopy(body, 0, newBody,0,body.length);
			body= new Point[size];
			body=newBody;
			body[size-1] = new Point(position.x-((size-1)*10),position.y);
		break;
		case Direction.NORTH:	
			newBody = new Point[size];
			System.arraycopy(body, 0, newBody,0,body.length);
			body= new Point[size];
			body=newBody;
			body[size-1] = new Point(position.x,position.y+((size-1)*10));
		break;
		case Direction.SOUTH:	
			newBody = new Point[size];
			System.arraycopy(body, 0, newBody,0,body.length);
			body= new Point[size];
			body=newBody;
			body[size-1] = new Point(position.x,position.y-((size-1)*10));
		break;
		}
	
		
	}
	
	public void goNorth() {
		if (!(direction == Direction.SOUTH))
			direction=Direction.NORTH;
	}
	
	public void goSouth() {
		if (!(direction == Direction.NORTH))
			direction=Direction.SOUTH;
	}
	
	public void goWest() {
		if (!(direction == Direction.EAST))
			direction=Direction.WEST;
	}
	
	public void goEast() {
		if (!(direction == Direction.WEST))
			direction=Direction.EAST;
	}
	
	/**
	 * @return the body
	 */
	public Point[] getBody() {
		return body;
	}

	public void move() {
		for (int i=body.length-1;i!=0;i--) {
			body[i].x=body[i-1].x;
			body[i].y=body[i-1].y;
		}
		switch (direction) {
		case Direction.EAST:
			body[0].x+=10;
			break;
		case Direction.WEST:
			body[0].x-=10;
			break;
		case Direction.NORTH:
			body[0].y-=10;
			break;
		case Direction.SOUTH:
			body[0].y+=10;
			break;
		}
	}
	
	
	
}
