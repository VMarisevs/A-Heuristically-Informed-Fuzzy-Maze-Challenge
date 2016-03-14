package ie.gmit.sw.ai;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ie.gmit.sw.ai.node.Node;
import ie.gmit.sw.ai.node.NodePassage;
import ie.gmit.sw.ai.node.NodeType;

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
        		int x2 = (col + 1) * size;
        		int y2 = (row + 1) * size;
        		
        		// painting square with color
        		g2.setColor(maze[row][col].getColor());
   				g2.fillRect(x1, y1, size, size);
        		   				
        		g2.setColor(Color.RED);
        		g2.drawLine(x1, y1, x2, y1); //N
        		g2.drawLine(x1, y2, x2, y2); //S
        		g2.drawLine(x2, y1, x2, y2); //E
        		g2.drawLine(x1, y1, x1, y2); //W
        		
        		g2.setColor(maze[row][col].getColor());
        		if (maze[row][col].getPassage() == NodePassage.North){
        			g2.drawLine(x1 + 1, y1, x2 - 1, y1); //N
        		}else{
        			g2.drawLine(x1, y1 + 1, x1, y2 -1); //W
        		}
        	}
        }
        
        g2.setColor(Color.GRAY);
        g2.fillRect(0,MazeView.DEFAULT_VIEW_SIZE,MazeView.DEFAULT_VIEW_SIZE,100);
	}

	
}
