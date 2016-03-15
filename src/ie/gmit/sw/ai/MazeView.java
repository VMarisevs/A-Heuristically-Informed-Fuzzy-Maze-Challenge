package ie.gmit.sw.ai;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ie.gmit.sw.ai.node.Node;

public class MazeView extends JPanel {
	private static final long serialVersionUID = 1L;
	public static final int DEFAULT_VIEW_SIZE = 600;	
	private Node[][] maze;
	
	private boolean zoomOut = true;
	
	public MazeView(Node[][] maze){
		this.maze = maze;
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        final int size = DEFAULT_VIEW_SIZE/maze.length;
        
        g2.drawRect(0, 0, MazeView.DEFAULT_VIEW_SIZE, MazeView.DEFAULT_VIEW_SIZE);
        
        for(int row = 0; row < maze.length; row++) {
        	for (int col = 0; col < maze[row].length; col++){  
        		int x1 = col * size;
        		int y1 = row * size;
        		
        		// painting square with color
        		g2.setColor(maze[row][col].getColor());
   				g2.fillRect(x1, y1, size, size);
        		
        	}
        }
        
        g2.setColor(Color.GRAY);
        g2.fillRect(0,MazeView.DEFAULT_VIEW_SIZE,MazeView.DEFAULT_VIEW_SIZE,100);
	}

	
}
