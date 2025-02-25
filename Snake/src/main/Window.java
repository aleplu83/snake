package main;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Window {

	private JFrame mainWindow;
	private JPanel statsPanel;
	private Field field;
	private JLabel lblStats;
	
	public Window() {
		mainWindow = new JFrame("Snake");
		field = new Field(new Dimension(500,500));
		statsPanel = new JPanel();
		lblStats = new JLabel();
		
		mainWindow.setSize(800,600);
		mainWindow.setLocation(400, 200);
		mainWindow.setLayout(new FlowLayout());
		
		
		
		lblStats.setBorder(BorderFactory.createTitledBorder("Level"));
		lblStats.setText("Points: ");
		
		mainWindow.add(field);
		statsPanel.add(lblStats);
		mainWindow.add(statsPanel);
		
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.pack();
		mainWindow.setVisible(true);
		mainWindow.setResizable(false);
		
	}
	
	public static void main(String[] args) {
		new Window();
	}

}
