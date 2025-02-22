package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

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
		mainWindow.setLocationByPlatform(true);
		fieldPanel = new JPanel();
		fieldPanel.setPreferredSize(new Dimension(600,600));
		field = new Field(new Dimension(600,600));
		fieldPanel.add(field);
		statsPanel = new JPanel(new FlowLayout());
		lblStats = new JLabel();
		lblStats.setBorder(BorderFactory.createTitledBorder("Level"));
		lblStats.setVerticalTextPosition(SwingConstants.TOP);
		lblStats.setHorizontalTextPosition(SwingConstants.LEFT);
		lblStats.setText("Points: ");
		mainWindow.setLayout(new BorderLayout());
		mainWindow.add(fieldPanel,BorderLayout.WEST);
		statsPanel.add(lblStats,FlowLayout.LEFT);
		mainWindow.add(statsPanel,BorderLayout.SOUTH);
		mainWindow.setSize(800,700);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//mainWindow.pack();
		mainWindow.setVisible(true);
		mainWindow.setResizable(false);
		
	}
	
	public static void main(String[] args) {
		new Window();
	}

}
