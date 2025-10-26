package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("serial")
public class Field extends JPanel implements KeyListener {
 
	private final Snake snake;
	private Fruit fruit;
	private int points=0;
	private int fruitsEaten=0;
	private int fruitsMissed=0;
	private int level=0;
	private final int levels[] = {0,50,100,170,220,350,400};
	private final int speeds[] = {100,210,170,110,60,10,5};
	//private Wall[] walls;
	private final Dimension fieldSize;
	
	public Field(Dimension size) {
            fieldSize=size;
            setBackground(Color.BLACK);
            setPreferredSize(size);
            addKeyListener(this);
            setFocusable(true);
            requestFocus();
          
            snake = new Snake(fieldSize,fieldSize.width/2,fieldSize.height/2,Direction.WEST);
            fruit = new Fruit(newFruitPos(),Color.RED,1,-1);
            
            startGame();
            
	}
	
	/*private void createWalls() {
		walls = new Wall[2];
	}*/
        
	private Point newFruitPos() {
            Point p = new Point();
            do {
                p.x=getRnd(10,fieldSize.width-10);
                p.y=getRnd(10,fieldSize.height-10);
            } while (snake.isMyBody(p.x, p.y));
            return p;
	}
	
	/*private void gameOver() {
		level=0;
		fruitsEaten=0;
		fruitsMissed=0;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
		}
		snake = new Snake(fieldSize);
		fruit = new Fruit(newFruitPos(),Color.RED,1,-1);
		
		thread = new Thread(this);
		thread.start();
	}*/

	@Override
	public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case 39 -> snake.goEast(); //left key
                case 37 -> snake.goWest(); //right key
                case 40 -> snake.goSouth(); //down key
                case 38 -> snake.goNorth(); //up key
            }
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

	/*private boolean isCollision() {
		return snake.getBody()[0].x < 0 || snake.getBody()[0].y < 0 || 
                        snake.getBody()[0].x > (fieldSize.width-10) || snake.getBody()[0].y > fieldSize.height-10;
	}*/
	
	private boolean gotFruit() {
            return snake.getBody().getFirst().x == fruit.getPos().x && snake.getBody().getFirst().y == fruit.getPos().y;
	}

	private void printStats() {
            System.out.println("Level: "+level);
            System.out.println("Fruits eaten/missed: "+fruitsEaten+"/"+fruitsMissed);
            System.out.println("Total points: "+points);
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
            g2d.setColor(Color.WHITE);
            for (Point rect : snake.getBody()) 
                    g2d.fillRect(rect.x, rect.y, 10, 10);
            
            g2d.dispose();
	}
	
	public void drawFruit(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(fruit.getColor());
            g2d.fillOval(fruit.getPos().x,fruit.getPos().y,10,10);
            g2d.setColor(Color.GREEN);
            g2d.drawLine(fruit.getPos().x+5, fruit.getPos().y, fruit.getPos().x, fruit.getPos().y-3);
            g2d.drawLine(fruit.getPos().x+5, fruit.getPos().y, fruit.getPos().x+10, fruit.getPos().y-3);
            g2d.dispose();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

    private void startGame() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    snake.move();
                    repaint();
                    if (gotFruit()) {
                        snake.grow();
                        fruitsEaten+=1;
                        points+=1;

                        fruit = new Fruit(newFruitPos(),Color.RED,1,-1);
                        for (int i=0;i<levels.length;i++) {
                            if (fruitsEaten==levels[i]) {
                                    level=i; //raise level
                            }
                        }
                        //printStats();
                    }
                    
                }
            };
            
            timer.scheduleAtFixedRate(timerTask, 2000, speeds[level]);	
    }

}
