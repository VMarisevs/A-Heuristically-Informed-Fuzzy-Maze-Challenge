package ie.gmit.sw.ai.node.characters;

import java.util.Random;

import ie.gmit.sw.ai.Game;
import ie.gmit.sw.ai.node.Node;
import ie.gmit.sw.ai.node.NodeType;

public class Player {
	
	private Node current;
	private Node[][] maze;
	private Game game;

	public Player(Game game) {
		this.maze = game.getMaze();
		this.game = game;
		/*
		 * player will be spawned in left top corner
		 */
		Random generator = new Random();
		int randRow = generator.nextInt(maze.length / 5);
		int randCol = generator.nextInt(maze[0].length / 5);
		
		this.current = maze[randRow][randCol];
		this.current.setType(NodeType.Player);
		this.current.setPlayer(this);
	}
	
	public boolean move(Direction dir){
		Node[] stepAvailable = current.getChildren(maze);
		
		Node next = null;
		
		for (int i = 0; i < stepAvailable.length; i++){			
			switch (dir){
				case Up:
					// we can move up
					if ((stepAvailable[i].getCol() == current.getCol()) && (stepAvailable[i].getRow() + 1 == current.getRow()))
						next = stepAvailable[i];
					break;
				case Down:
					// we can move down
					if ((stepAvailable[i].getCol() == current.getCol()) && (stepAvailable[i].getRow() - 1 == current.getRow()))
						next = stepAvailable[i];
					break;
				case Left:
					// we can move left
					if ((stepAvailable[i].getCol() + 1 == current.getCol()) && (stepAvailable[i].getRow() == current.getRow()))
						next = stepAvailable[i];
					break;
				case Right:
					// we can move right
					if ((stepAvailable[i].getCol() - 1 == current.getCol()) && (stepAvailable[i].getRow() == current.getRow()))
						next = stepAvailable[i];
					break;
			}
		}
		
		// checking if we can move there
		if (next != null){
			
			switch(next.getType()){
				case Empty:
					makeMove(next);
					break;
				case Exit:
					makeMove(next);
					System.out.println("Well done!");
					game.setGameOver(true);
					break;
				case Monster:
					// destroy monster..
					makeMove(next);
					
					Monster monster = next.getMonster();
					monster.setPause(true);
					
					if (fight(monster)){
						// poisons the thread 
						monster.setAlive(false);
					}
					
					break;
			}
			
		} else{
			System.out.println("can't move there");
			return false;
		}
		
		return true;
	}
	
	public boolean fight(Monster monster){
		System.out.println("Let's fight!");
		/*
		 * true = win,
		 * false = lose
		 */
		return true;
	}
	
	private void makeMove(Node next){
		next.setType(NodeType.Player);
		next.setPlayer(this);
		current.setType(NodeType.Empty);
		current.setPlayer(null);
		
		current = next;
		next = null;
	}
}
