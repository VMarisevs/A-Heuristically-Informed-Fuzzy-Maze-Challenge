package ie.gmit.sw.ai;

import java.awt.Component;
import java.util.Random;

import ie.gmit.sw.ai.node.Node;
import ie.gmit.sw.ai.node.NodeType;

public class Monster implements Runnable {

	private Node[][] maze; // knowledge of maze
	private Node monster;
	private boolean alive = true;
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
			int randRow = generator.nextInt(maze.length);
			int randCol = generator.nextInt(maze[0].length);
			
			if (maze[randRow][randCol].getType() == NodeType.Empty){
				maze[randRow][randCol].setType(NodeType.Monster);
				monster = maze[randRow][randCol];
				
				spawned = true;
			}
			
		}
		
	}
	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	private void move(){
		while(alive){
			Node[] stepsAvailable = monster.getChildren(maze);
			
			Random generator = new Random();
			
			boolean moved = false;
				
			while(!moved){
				int random = generator.nextInt(stepsAvailable.length);
				
				Node next = stepsAvailable[random];
				
				if (next.getType() == NodeType.Empty){
					next.setType(NodeType.Monster);
					monster.setType(NodeType.Empty);
					
					monster = next;
					next = null;
					moved = true;
				}
			}
			
			view.repaint();
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
