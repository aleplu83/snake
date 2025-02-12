package main;

import javax.swing.JFrame;

public class Window {

	private JFrame mainWindow;
	private Field snakeArea;
	
	public Window() {
		mainWindow = new JFrame("Snake");
		snakeArea = new Field();
		mainWindow.getContentPane().add(snakeArea);
		mainWindow.setSize(500,500);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setLocation(600, 200);
		mainWindow.pack();
		mainWindow.setVisible(true);
		mainWindow.setResizable(false);
		
	}
	
	public static void main(String[] args) {
		new Window();
	}

}
