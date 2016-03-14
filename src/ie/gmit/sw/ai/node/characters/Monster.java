package ie.gmit.sw.ai.node.characters;

import java.awt.Component;
import java.util.Random;

import ie.gmit.sw.ai.node.Node;
import ie.gmit.sw.ai.node.NodeType;

public class Monster implements Runnable {

	private Node[][] maze; // knowledge of maze
	private Node current;
	private boolean alive = true;
	private boolean pause = false;
	private Component view;
	
	public Monster(Node[][] maze, Component view) {
		this.maze = maze;
		this.view = view;
		setMonster();
	}
	
	@Override
	public void run() {
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
			
			if (maze[randRow][randCol].getType() == NodeType.Empty){
				
				maze[randRow][randCol].setType(NodeType.Monster);
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
				Node[] stepsAvailable = current.getChildren(maze);
				
				Random generator = new Random();
				
				boolean moved = false;
					
				while(!moved){
					/*
					 * Generating random number from child nodes
					 * * child nodes = all available moves
					 * 
					 */
					int random = generator.nextInt(stepsAvailable.length);
					
					Node next = stepsAvailable[random];
					
					/*
					 *  if next cell empty making a movement
					 *  repainting and waiting for 1 sec 
					 */
					if (next.getType() == NodeType.Empty){
						
						next.setType(NodeType.Monster);
						next.setMonster(this);
						
						current.setType(NodeType.Empty);
						current.setMonster(null);
						
						current = next;
						next = null;
						moved = true;
					}
				}
				
				view.repaint();
			}
				
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// kill thread if fight is won
		System.out.println("Monster poisoned...");
	}
}
