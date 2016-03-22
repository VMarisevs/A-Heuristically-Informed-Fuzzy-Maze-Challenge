package ie.gmit.sw.ai;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import ie.gmit.sw.ai.node.Node;
import ie.gmit.sw.ai.node.characters.Player;
import ie.gmit.sw.ai.node.characters.radar.DepthLimitedDFSRadar;
import ie.gmit.sw.ai.node.items.Item;

public class MazeView extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public static final int DEFAULT_WINDOW_WIDTH = 900;
	public static final int DEFAULT_WINDOW_HEIGHT = 700;
	
	public static final int DEFAULT_MAZE_SIZE = 600;
	
	
	private Node[][] maze;
	private Player player;
	
	private boolean zoomOut = true;
	
	public MazeView(Node[][] maze, Player player){
		this.maze = maze;
		this.player = player;
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
	}
	
	
	public boolean isZoomOut() {
		return zoomOut;
	}


	public void setZoomOut(boolean zoomOut) {
		this.zoomOut = zoomOut;
	}


	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        
        if (zoomOut)
        	drawZoomOutMaze(g2);
        else
        	drawZoomInMaze(g2);
        
        drawCollectedItems(g2);
        drawPlayersHealth(g2);
        
        drawLifeFormsRadar(g2);
        
	}

	private void drawZoomInMaze(Graphics2D g2){
		final int size = DEFAULT_MAZE_SIZE/7;
				
		for (int row = -3; row < 4; row++){
			for (int col = -3; col < 4; col++){
				int x = player.getPosition().getCol() + col;
				int y = player.getPosition().getRow() + row;
				
				BufferedImage img = null;
				if ( x < 0 || y < 0 || x >= maze.length || y >= maze[0].length)
					g2.setColor(Color.BLACK);
				else{
					g2.setColor(maze[y][x].getColor());
					
					if (maze[y][x].getItem() != null)
						img = maze[y][x].getItem().getWeapon();
					
					if (maze[y][x].getImage() != null)
						img = maze[y][x].getImage();
				}
					
				
				x = x - player.getPosition().getCol()+3;
				y = y - player.getPosition().getRow()+3;
				
				x *=size;
				y *=size;
				
				g2.fillRect(x, y, size, size);
				
				if (img != null)
					g2.drawImage(img,x,y, null);
					
			
			}
		}
		
		
	}
	
	private void drawZoomOutMaze(Graphics2D g2){
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
	}
	
	private void drawLifeFormsRadar(Graphics2D g2){
		
		DepthLimitedDFSRadar dfs = DepthLimitedDFSRadar.getInstance(maze);
		Node[] lifeDetected = dfs.getLifeForms(player.getPosition());
		
		int size = 10;
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
			g2.fillOval(x, y, size, size);
		}
		
		g2.setColor(Color.GREEN);
		int x = player.getPosition().getCol() * size + DEFAULT_MAZE_SIZE;
		int y = player.getPosition().getRow() * size;
		g2.fillOval(x, y, size, size);
		//System.out.println("Lifes Detected: " + lifeDetected.length);
	}
	
	private void drawCollectedItems(Graphics2D g2){
		g2.setColor(Color.GRAY);
        g2.fillRect(0,MazeView.DEFAULT_MAZE_SIZE,MazeView.DEFAULT_MAZE_SIZE,100);
        
        Item[] items = player.getItems();
        //final int size = DEFAULT_MAZE_SIZE/10;
        
        final int size = 85;
        
        for (int i = 0; i < items.length && i < 8; i++){
        	int x1 = i * (size+10);
    		int y1 = MazeView.DEFAULT_MAZE_SIZE+10;
    		
    		if (player.getCurrentItem() == i){
    			g2.setColor(Color.ORANGE);
    			g2.fillRect(x1, y1, size, size);
    		}
    		
    		g2.drawImage(items[i].getWeapon(), x1,y1, null);
    		//g2.setColor(items[i].getColor());
        	//g2.fillRect(x1, y1, size, size);
        }
	}

	private void drawPlayersHealth(Graphics2D g2){
		Font f = new Font("Dialog", Font.PLAIN, 20);
		g2.setFont(f);
		g2.setColor(Color.WHITE);
		g2.drawString("HP: " + player.getHealth(), 0,DEFAULT_MAZE_SIZE + 100);
	}
}
