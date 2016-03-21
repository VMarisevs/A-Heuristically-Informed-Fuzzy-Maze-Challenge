package ie.gmit.sw.ai;

import java.awt.*;

import javax.swing.JPanel;

import ie.gmit.sw.ai.node.Node;
import ie.gmit.sw.ai.node.characters.Player;
import ie.gmit.sw.ai.node.characters.radar.DepthLimitedDFSRadar;
import ie.gmit.sw.ai.node.items.Item;

public class MazeView extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public static final int DEFAULT_WINDOW_WIDTH = 800;
	public static final int DEFAULT_WINDOW_HEIGHT = 700;
	
	public static final int DEFAULT_MAZE_SIZE = 600;
	
	
	private Node[][] maze;
	private Player player;
	
	//private boolean zoomOut = true;
	
	public MazeView(Node[][] maze, Player player){
		this.maze = maze;
		this.player = player;
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        final int size = DEFAULT_MAZE_SIZE/maze.length;
        
        g2.drawRect(0, 0, MazeView.DEFAULT_MAZE_SIZE, MazeView.DEFAULT_MAZE_SIZE);
        
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
        drawPlayersHealth(g2);
        
        drawLifeFormsRadar(g2);
        
	}

	private void drawLifeFormsRadar(Graphics2D g2){
		
		DepthLimitedDFSRadar dfs = DepthLimitedDFSRadar.getInstance(maze);
		Node[] lifeDetected = dfs.getLifeForms(player.getPosition());
		
		int size = 6;
		for (int i = 0; i < maze.length; i++){
			for (int j = 0; j < maze[i].length; j++){
				int x = i * size + DEFAULT_MAZE_SIZE;
				int y = j * size;

				g2.setColor(Color.LIGHT_GRAY);
				g2.fillRect(x, y, size, size);
			}
		}
		
		g2.setColor(Color.BLUE);
		for (int i = 0; i < lifeDetected.length; i++){
			int x = lifeDetected[i].getCol() * size + DEFAULT_MAZE_SIZE;
			int y = lifeDetected[i].getRow() * size;
			g2.fillRect(x, y, size, size);
		}
		//System.out.println("Lifes Detected: " + lifeDetected.length);
	}
	
	private void drawCollectedItems(Graphics2D g2){
		g2.setColor(Color.GRAY);
        g2.fillRect(0,MazeView.DEFAULT_MAZE_SIZE,MazeView.DEFAULT_MAZE_SIZE,100);
        
        Item[] items = player.getItems();
        final int size = DEFAULT_MAZE_SIZE/10;
        
        for (int i = 0; i < items.length && i < 8; i++){
        	int x1 = i * (size+10);
    		int y1 = MazeView.DEFAULT_MAZE_SIZE+10;
    		g2.setColor(items[i].getColor());
        	g2.fillRect(x1, y1, size, size);
        }
	}

	private void drawPlayersHealth(Graphics2D g2){
		Font f = new Font("Dialog", Font.PLAIN, 20);
		g2.setFont(f);
		g2.setColor(Color.WHITE);
		g2.drawString("HP: " + player.getHealth(), 0,DEFAULT_MAZE_SIZE + 100);
	}
}
