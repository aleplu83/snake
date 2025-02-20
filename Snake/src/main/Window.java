package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Window {

	private JFrame mainWindow;
	private JPanel fieldPanel,statsPanel;
	private Field field;
	private JLabel lblLevel,lblFruits;
	
	public Window() {
		mainWindow = new JFrame("Snake");
		field = new Field();
		fieldPanel = new JPanel(new FlowLayout());
		statsPanel = new JPanel(new FlowLayout());
		lblLevel = new JLabel();
		lblLevel.setBorder(BorderFactory.createTitledBorder("Level"));
		lblLevel.setSize(new Dimension(100, 100));
		lblLevel.setPreferredSize(new Dimension(100,100));
		lblLevel.setMinimumSize(new Dimension(100, 100));
		lblLevel.setMaximumSize(new Dimension(100, 100));
		lblLevel.setText(field.getLevel()+"");
		lblFruits = new JLabel();
		lblFruits.setBorder(BorderFactory.createTitledBorder("Fruits"));
		lblFruits.setSize(new Dimension(100, 100));
		lblFruits.setPreferredSize(new Dimension(100,100));
		lblFruits.setMinimumSize(new Dimension(100, 100));
		lblFruits.setMaximumSize(new Dimension(100, 100));
		lblFruits.setText(field.getFruits()+"");
		fieldPanel.add(field);
		mainWindow.setLayout(new BorderLayout());
		mainWindow.add(fieldPanel,BorderLayout.WEST);
		statsPanel.add(lblLevel);
		statsPanel.add(lblFruits);
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
