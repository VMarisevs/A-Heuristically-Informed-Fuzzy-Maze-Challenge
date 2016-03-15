package ie.gmit.sw.ai;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ie.gmit.sw.ai.node.Node;
import ie.gmit.sw.ai.node.characters.Player;
import ie.gmit.sw.ai.node.items.Item;

public class MazeView extends JPanel {
	private static final long serialVersionUID = 1L;
	public static final int DEFAULT_VIEW_SIZE = 600;	
	private Node[][] maze;
	private Player player;
	
	private boolean zoomOut = true;
	
	public MazeView(Node[][] maze, Player player){
		this.maze = maze;
		this.player = player;
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
        
        drawCollectedItems(g2);
        
	}

	private void drawCollectedItems(Graphics2D g2){
		g2.setColor(Color.GRAY);
        g2.fillRect(0,MazeView.DEFAULT_VIEW_SIZE,MazeView.DEFAULT_VIEW_SIZE,100);
        
        Item[] items = player.getItems();
        final int size = DEFAULT_VIEW_SIZE/10;
        for (int i = 0; i < items.length; i++){
        	int x1 = i * size;
    		int y1 = MazeView.DEFAULT_VIEW_SIZE;
    		g2.setColor(Color.YELLOW);
        	g2.fillRect(x1, y1, size, size);
        }
	}
}
