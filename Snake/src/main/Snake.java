package main;

import java.awt.Dimension;
import java.awt.Point;

public class Snake {

	private Direction direction = Direction.EAST;
	private Point[] body;
	private Point[] newBody;
	//private Point position = new Point(80,80);
	private int size=4; // start length of the snake. 4 blocks of 10px
	private Dimension fieldSize;

	public Snake(Dimension fieldSize,int startX,int startY) {
            //this.size=4; //default size of 4 block
            this.fieldSize=fieldSize;
            create(startX,startY);
	}
	public Snake(int size) {
            this.size=size;
	}
	
	public final void create(int startX,int startY) {
            body = new Point[size];
            body[0] = new Point(startX,startY);
            switch (direction) {
                case Direction.EAST -> {
                    for (int i=1;i<size;i++) {
                        body[i]=new Point(startX-(i*10),startY);
                    }
                }
                case Direction.WEST -> {
                    for (int i=1;i<size;i++) {
                        body[i]=new Point(startX+(i*10),startY);
                    }
                }
                case Direction.NORTH -> {
                    for (int i=1;i<size;i++) {
                        body[i]=new Point(startX,startY+(i*10));
                    }
                }
                case Direction.SOUTH -> {
                    for (int i=1;i<size;i++) {
                        body[i]=new Point(startX,startY-(i*10));
                    }
                }
            }
	}
	
	public void grow() {
            size++;
            switch (direction) {
                case Direction.EAST -> {
                    newBody = new Point[size];
                    System.arraycopy(body, 0, newBody,0,body.length);
                    body= new Point[size];
                    body=newBody;
                    body[size-1] = new Point(body[0].x+((size-1)*10),body[0].y);
                }
                case Direction.WEST -> {
                    newBody = new Point[size];
                    System.arraycopy(body, 0, newBody,0,body.length);
                    body= new Point[size];
                    body=newBody;
                    body[size-1] = new Point(body[0].x-((size-1)*10),body[0].y);
                }
                case Direction.NORTH -> {
                    newBody = new Point[size];
                    System.arraycopy(body, 0, newBody,0,body.length);
                    body= new Point[size];
                    body=newBody;
                    body[size-1] = new Point(body[0].x,body[0].y+((size-1)*10));
                }
                case Direction.SOUTH -> {
                    newBody = new Point[size];
                    System.arraycopy(body, 0, newBody,0,body.length);
                    body= new Point[size];
                    body=newBody;
                    body[size-1] = new Point(body[0].x,body[0].y-((size-1)*10));
                }
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
	
	protected boolean isMyBody(int x,int y) {
            for (int i=0;i<body.length;i++) { 
                if (x==body[i].x && y==body[i].y) {
                    return true;
                }	
            }
            return false;
	}
	
	/**
	 * @return the body
	 */
	public Point[] getBody() {
            return body;
	}

	protected boolean move() {
            for (int i=body.length-1;i!=0;i--) {
                    body[i].x=body[i-1].x;
                    body[i].y=body[i-1].y;
            }
            switch (direction) {
                case Direction.EAST -> {
                    if (body[0].x == fieldSize.width) {
                        body[0].x = -10;
                    }
                    if (!isMyBody(body[0].x+10,body[0].y))
                        body[0].x+=10;
                    else
                        return false;
                }
                case Direction.WEST -> {
                    if (body[0].x == 0) {
                        body[0].x = fieldSize.width;
                    }
                    if (!isMyBody(body[0].x-10,body[0].y))
                        body[0].x-=10;
                    else
                        return false;
                }
                case Direction.NORTH -> {
                    if (body[0].y == 0) {
                        body[0].y = fieldSize.height;
                    }
                    if (!isMyBody(body[0].x,body[0].y-10))
                        body[0].y-=10;
                    else
                        return false;
                }
                case Direction.SOUTH -> {
                    if (body[0].y == fieldSize.height) {
                        body[0].y = -10;
                    }
                    if (!isMyBody(body[0].x,body[0].y+10))
                        body[0].y+=10;
                    else
                        return false;
                }
            }
            return true;
	}

	/**
	 * @return the direction
	 */
	public Direction getDirection() {
            return direction;
	}
}
