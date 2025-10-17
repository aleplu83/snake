package main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;

public class Window {

	private final JFrame mainWindow;
	private final Field field;
	
	public Window() {
            mainWindow = new JFrame("Snake");
            field = new Field(new Dimension(800,600));

            //mainWindow.setSize(800,600);
            mainWindow.setLocation(400, 200);
            mainWindow.setLayout(new FlowLayout());

            mainWindow.add(field);

            mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainWindow.pack();
            mainWindow.setVisible(true);
            mainWindow.setResizable(false);
	}
	
	public static void main(String[] args) {
            new Window();
	}

}
