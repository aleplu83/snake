package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Field extends JPanel implements KeyListener,Runnable {
 
	private Snake snake;
	private Thread thread;
	private Fruit fruit;
	private int totalPoints=0;
	private int fruitsTotal=0;
	private int fruitsMissed=0;
	private int level=0;
	private int levels[] = {0,15,30,70,120,250,400};
	private int speeds[] = {350,300,260,200,150,100,80};
	private int timeouts[] = {45,40,35,30,25,20,15};
	private Dimension fieldSize;
	
	public Field(Dimension size) {
		//setBorder(BorderFactory.createLineBorder(Color.BLUE));
		this.fieldSize=size;
		setBackground(Color.BLACK);
		setPreferredSize(size);
		addKeyListener(this);
		setFocusable(true);
		requestFocus();
		
		snake = new Snake();
		fruit = new Fruit(newFruitPos(),Color.RED,speeds[level]/speeds[level]*timeouts[level],speeds[level]/speeds[level]*timeouts[level]);
		
		thread = new Thread(this);
		thread.start();
		
	}
	
	private Point newFruitPos() {
		Point p = new Point();
		do {
			p.x=getRnd(10,fieldSize.width-10);
			p.y=getRnd(10,fieldSize.height-10);
		} while (snake.isMyBody(p.x, p.y));
		return p;
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
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawSnake(g);
		if (fruit.getTimeout()!=0)
			drawFruit(g);
		
		
	}
	
	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	private boolean isCollision() {
		
		if (snake.getBody()[0].x < 0 || snake.getBody()[0].y < 0 || 
			snake.getBody()[0].x > (fieldSize.width-10) || snake.getBody()[0].y > fieldSize.height-10)
			return true;
		
		
		return false;
	}
	
	private boolean gotFruit() {
		
		if (snake.getBody()[0].x == fruit.getPos().x && snake.getBody()[0].y == fruit.getPos().y) 
			return true;
		
		return false;
	}

	private void printStats() {
		System.out.println("Level: "+level);
		System.out.println("Fruits eaten/missed: "+fruitsTotal+"/"+fruitsMissed);
		System.out.println("Total points: "+totalPoints);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (snake.move()) {
			/*System.out.println(speeds[level]/speeds[level]*5);
			System.out.println(fruit.getTimeout());*/
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
			
			if (fruit.getTimeout()!=0) {
				fruit.setTimeout(fruit.getTimeout()-1);
			} else {
				fruitsMissed+=1;
				fruit = new Fruit(newFruitPos(),Color.RED,speeds[level]/speeds[level]*timeouts[level],speeds[level]/speeds[level]*timeouts[level]);
				printStats();
			}
				
			
			if (gotFruit()) {
				snake.grow();
				fruitsTotal+=1;
				totalPoints+=fruit.getTimeout();
				
				fruit = new Fruit(newFruitPos(),Color.RED,speeds[level]/speeds[level]*timeouts[level],speeds[level]/speeds[level]*timeouts[level]);
				for (int i=0;i<levels.length;i++) {
					if (fruitsTotal==levels[i]) {
						level=i; //raise level
					}
				}
				printStats();
			}
		}
		
	}
	
	private int getRnd(int min,int max) {
		int rndNumber;
		do {
			rndNumber=((int) (Math.random() * fieldSize.width));
		} while (rndNumber < min || rndNumber > max);
		
		return (int) (Math.ceil(rndNumber / 10.0)*10);
	}
	
	public void drawSnake(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(Color.YELLOW);
		for (Point rect : snake.getBody()) {
			g2d.fill3DRect(rect.x, rect.y, 10, 10, true);
		}
		g2d.dispose();
	}
	
	public void drawFruit(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(fruit.getColor());
        g2d.fillOval(fruit.getPos().x,fruit.getPos().y,10,10);
        if (fruit.getTimeout()>30) {
        	g2d.setColor(Color.LIGHT_GRAY);
        } else if (fruit.getTimeout()>15 && fruit.getTimeout()<30) {
        	g2d.setColor(Color.YELLOW);
        } else if (fruit.getTimeout()<15) {
        	g2d.setColor(Color.RED);
        }
        	
        g2d.setFont(new Font("Tahoma", Font.PLAIN, 10));
        g2d.drawString(fruit.getTimeout()+"", fruit.getPos().x+10, fruit.getPos().y+15);
        g2d.setColor(Color.GREEN);
        g2d.drawLine(fruit.getPos().x+5, fruit.getPos().y, fruit.getPos().x, fruit.getPos().y-3);
        g2d.drawLine(fruit.getPos().x+5, fruit.getPos().y, fruit.getPos().x+10, fruit.getPos().y-3);
		g2d.dispose();
	}

	public int getFruits() {
		// TODO Auto-generated method stub
		return fruitsTotal;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
