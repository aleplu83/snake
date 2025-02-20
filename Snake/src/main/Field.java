package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Field extends JPanel implements KeyListener,Runnable {

	private Snake snake;
	private Thread thread;
	private Point fruit;
	private int fruitsTotal=0;
	private int level=0;
	private int levels[] = {15,25,40,55,70};
	private int speeds[] = {150,125,100,85,60};
	
	public Field() {
		setBorder(BorderFactory.createLineBorder(Color.BLUE));
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(500,500));
		addKeyListener(this);
		setFocusable(true);
		requestFocus();
		
		
		fruit = new Point();
		snake = new Snake();
		placeFruit();
		thread = new Thread(this);
		thread.start();
		
	}
	
	private void placeFruit()  {
		do {
			fruit.x=getRnd(10,490);
			fruit.y=getRnd(10,490);
		} while (snake.isMyBody(fruit.x, fruit.y));
	}
	
	private void gameOver() {
		level=0;
		fruitsTotal=0;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case 39: // go east
			snake.goEast();
			break;
		case 37: // go west
			snake.goWest();
			break;
		case 40: // go South
			snake.goSouth();
			break;
		case 38: // go North
			snake.goNorth();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawSnake(g);
		drawFruit(g);
	}
	
	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	private boolean isCollision() {
		
		if (snake.getBody()[0].x < 0 || snake.getBody()[0].y < 0 || snake.getBody()[0].x > 490 || snake.getBody()[0].y > 490)
			return true;
		
		
		return false;
	}
	
	private boolean gotFruit() {
		
		if (snake.getBody()[0].x == fruit.x && snake.getBody()[0].y == fruit.y) 
			return true;
		
		return false;
	}

	private void printStats() {
		System.out.println("Level: "+level);
		System.out.println("Fruits eaten: "+fruitsTotal);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (snake.move()) {
			
			repaint();
			
			try {
				Thread.sleep(speeds[level]);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (isCollision()) {
				gameOver();
			}
			
			if (gotFruit()) {
				snake.grow();
				fruitsTotal+=1;
				
				placeFruit();
				for (int i=0;i<levels.length;i++) {
					if (fruitsTotal==levels[i]) {
						level=i;
					}
				}
				printStats();
			}
		}
		
	}
	
	private int getRnd(int min,int max) {
		int rndNumber;
		do {
			rndNumber=((int) (Math.random() * 500));
		} while (rndNumber < min || rndNumber > max);
		
		return (int) (Math.ceil(rndNumber / 10.0)*10);
	}
	
	public void drawSnake(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(Color.YELLOW);
		for (int i=0;i<snake.getBody().length;i++) 
			g2d.fillRect(snake.getBody()[i].x, snake.getBody()[i].y, 10, 10);

		g2d.dispose();
	}
	
	public void drawFruit(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(Color.RED);
        g2d.fillOval(fruit.x,fruit.y,10,10);
        g2d.setColor(Color.GREEN);
        g2d.drawLine(fruit.x+5, fruit.y, fruit.x+5, fruit.y-3);
		g2d.dispose();
	}

}
