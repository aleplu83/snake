package main;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

public class Snake {

	private Direction direction = Direction.EAST;
        private ArrayList<Point> body;
	//private Point position = new Point(80,80);
	private Dimension fieldSize;

	public Snake(Dimension fieldSize,int startX,int startY,Direction startDirection) {
            this.fieldSize=fieldSize;
            this.direction = startDirection;
            create(startX,startY);
	}
	
	public final void create(int startX,int startY) {
            body = new ArrayList<>(4);
            body.addFirst(new Point(startX,startY));
            switch (direction) {
                case Direction.EAST -> {
                    for (int i=1;i<4;i++) {
                        body.add(i, new Point(startX-(i*10),startY));
                    }
                }
                case Direction.WEST -> {
                    for (int i=1;i<4;i++) {
                        body.add(i, new Point(startX+(i*10),startY));
                    }
                }
                case Direction.NORTH -> {
                    for (int i=1;i<4;i++) {
                        body.add(i,new Point(startX,startY+(i*10)));
                    }
                }
                case Direction.SOUTH -> {
                    for (int i=1;i<4;i++) {
                        body.add(i,new Point(startX,startY-(i*10)));
                    }
                }
            }
	}
	
	public void grow() {
            switch (direction) {
                case Direction.EAST -> {
                    body.add(new Point(body.getLast().x+10,body.getLast().y));
                }
                case Direction.WEST -> {
                    body.add(new Point(body.getLast().x-10,body.getLast().y));
                }
                case Direction.NORTH -> {
                    body.add(new Point(body.getLast().x,body.getLast().y+10));
                }
                case Direction.SOUTH -> {
                    body.add(new Point(body.getLast().x,body.getLast().y-10));
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
            for (Point p : body)
                 if (x==p.x && y==p.y) 
                    return true;
                
            return false;
	}
	
	/**
	 * @return the body
	 */
	public ArrayList<Point> getBody() {
            return body;
	}

	protected boolean move() {
            int headX=body.getFirst().x;
            int headY=body.getFirst().y;
            
            for (int i=body.size()-1;i!=0;i--) {
                    body.get(i).x=body.get(i-1).x;
                    body.get(i).y=body.get(i-1).y;
            }
            switch (direction) {
                case Direction.EAST -> {
                    if (headX == fieldSize.width-10) 
                        body.getFirst().x = -10;
                    
                    if (!isMyBody(headX+10,headY))
                        body.getFirst().x += 10;
                    else
                        return false;
                }
                case Direction.WEST -> {
                    if (headX == 0) {
                        body.getFirst().x = fieldSize.width;
                    }
                    if (!isMyBody(headX-10,headY))
                        body.getFirst().x-=10;
                    else
                        return false;
                }
                case Direction.NORTH -> {
                    if (headY == 0) {
                        body.getFirst().y = fieldSize.height;
                    }
                    if (!isMyBody(headX,headY-10))
                        body.getFirst().y-=10;
                    else
                        return false;
                }
                case Direction.SOUTH -> {
                    if (headY == fieldSize.height-10) {
                        body.getFirst().y =-10;
                    }
                    if (!isMyBody(headX,headY+10))
                        body.getFirst().y+=10;
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
