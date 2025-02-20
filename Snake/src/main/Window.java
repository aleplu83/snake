package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Window {

	private JFrame mainWindow;
	private JPanel fieldPanel,statsPanel;
	private Field field;
	private JLabel Level,Points;
	
	public Window() {
		mainWindow = new JFrame("Snake");
		field = new Field();
		fieldPanel = new JPanel();
		statsPanel = new JPanel();
		Level = new JLabel();
		Level.setBorder(BorderFactory.createTitledBorder("Level"));
		Level.setSize(new Dimension(100, 600));
		Level.setMinimumSize(new Dimension(100, 600));
		Level.setMaximumSize(new Dimension(100, 600));
		Level.setText("Level "+field.getLevel());
		fieldPanel.add(field);
		mainWindow.setLayout(new BorderLayout());
		mainWindow.add(fieldPanel,BorderLayout.WEST);
		mainWindow.add(statsPanel,BorderLayout.EAST);
		mainWindow.setSize(700,500);
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
