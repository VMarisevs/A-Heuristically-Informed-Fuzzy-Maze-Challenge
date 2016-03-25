package ie.gmit.sw.ai.node.characters;

import java.awt.image.BufferedImage;
import java.util.Random;

import ie.gmit.sw.ai.Game;
import ie.gmit.sw.ai.Maze;
import ie.gmit.sw.ai.UpdateView;
import ie.gmit.sw.ai.node.Node;
import ie.gmit.sw.ai.node.characters.traversers.*;
import ie.gmit.sw.resources.Resources;

public class Monster implements Runnable {

	private Node[][] maze; // knowledge of maze
	private Node current;
	private Player player; // knowledge about player position
	private Game game;
	
	private boolean alive = true;
	private boolean pause = false;
	private int strength;
	
	private boolean previousstep = false;
	
	private BufferedImage image;
	
	public Monster(Node[][] maze,Player player, Game game) {
		this.maze = maze;
		this.player = player;
		this.game = game;
		this.strength = new Random().nextInt(11); // randomly apply strength
		setMonster();
	}
	
	@Override
	public void 
	run() {
		move();		
	}

	private void setMonster(){
		Random generator = new Random();
		boolean spawned = false;
		
		while (!spawned){
			/*
			 * Generating random position
			 * try to spawn the monster in the empty cell
			 * 
			 */
			int randRow = generator.nextInt(maze.length);
			int randCol = generator.nextInt(maze[0].length);
			
			if (maze[randRow][randCol].isEmpty()){
				
				current = maze[randRow][randCol];
				
				// let the node know about monster inside
				current.setMonster(this);
				
				spawned = true;
			}
			
		}
		
	}
	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	private void move(){
		while(alive){
			
			if (!pause){				
				boolean moved = false;
					
				while(!moved){
					/*
					 * Generating random number from child nodes
					 * * child nodes = all available moves
					 * 
					 */
					
					/*
					Node[] stepsAvailable = current.getChildren(maze);
					
					Random generator = new Random();
					
					int random = generator.nextInt(stepsAvailable.length);
					
					Node next = stepsAvailable[random];
					*/
					
					
					/*
					 *  Looking for player using Brute Force Search algorithm
					 */
					//Node next = new BruteForceTraversator().traverse(maze, current, this);
					
					
					/*
					 *  Looking for player using Best First Search algorithm
					 */
					
					Node next = new BestFirstTraversator(player.getPosition()).traverse(maze, current, this);
					
					Maze.clearMaze(maze, this);
					/*
					 *  if we have a next move
					 *  if not, put to sleep so we won't overload the processor
					 *  
					 *  if next cell empty making a movement
					 *  repainting and waiting for 1 sec 
					 */
					if (next != null){
						if (!next.isWall() && next.getMonster() == null){
							
							if (current.getRow() == next.getRow() && current.getCol() < next.getCol()){
								if (isPreviousstep())
									image = Resources.getInstance().getDevilRight0();
								else
									image = Resources.getInstance().getDevilRight1();
								
							} else if (current.getRow() == next.getRow() && current.getCol() > next.getCol()){
								if (isPreviousstep())
									image = Resources.getInstance().getDevilLeft0();
								else
									image = Resources.getInstance().getDevilLeft1();
								
							}else if (current.getRow() < next.getRow() && current.getCol() == next.getCol()){
								if (isPreviousstep())
									image = Resources.getInstance().getDevilDown0();
								else
									image = Resources.getInstance().getDevilDown1();
								
							}else if (current.getRow() > next.getRow() && current.getCol() == next.getCol()){
								if (isPreviousstep())
									image = Resources.getInstance().getDevilUp0();
								else
									image = Resources.getInstance().getDevilUp1();
							}
								
							
							/*
							 * making a move
							 */
							makeMove(next);
							moved = true;
							
							if (next.getPlayer() != null){
								pause = true;
								Player player = next.getPlayer();
								if (player.fight(this)){
									alive = false;
								}
							}
						} 
						
					} else{
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}
				
				UpdateView.getInstance().repaint();
			}
				
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// kill thread if fight is won
		System.out.println("Monster poisoned...");
		current.setMonster(null);
		
		game.spawnMonster(this);
	}
	
	private void makeMove(Node next){
		
		next.setMonster(this);
		
		current.setMonster(null);
		
		current = next;
		next = null;
	}

	private boolean isPreviousstep() {
		/*
		 * let switch between animation if player make a step into same side twice
		 */
		if (previousstep)
			previousstep = false;
		else
			previousstep = true;
		return previousstep;
	}
	
	public int getStrength() {
		return strength;
	}

	
	public BufferedImage getImage() {
		return image;
	}
	
}
