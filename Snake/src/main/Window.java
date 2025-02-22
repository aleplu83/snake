package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Window {

	private JFrame mainWindow;
	private JPanel fieldPanel,statsPanel;
	private Field field;
	private JLabel lblStats;
	
	public Window() {
		mainWindow = new JFrame("Snake");
		field = new Field();
		fieldPanel = new JPanel(new FlowLayout());
		statsPanel = new JPanel(new FlowLayout());
		lblStats = new JLabel();
		lblStats.setBorder(BorderFactory.createTitledBorder("Level"));
		lblStats.setVerticalTextPosition(SwingConstants.TOP);
		lblStats.setHorizontalTextPosition(SwingConstants.LEFT);
		lblStats.setText("Points: ");
		fieldPanel.add(field);
		mainWindow.setLayout(new BorderLayout());
		mainWindow.add(fieldPanel,BorderLayout.WEST);
		statsPanel.add(lblStats);
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
