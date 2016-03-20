package ie.gmit.sw.ai.node.characters;

import java.util.*;

import ie.gmit.sw.ai.Game;
import ie.gmit.sw.ai.jfuzzy.Fight;
import ie.gmit.sw.ai.node.Node;
import ie.gmit.sw.ai.node.NodeType;
import ie.gmit.sw.ai.node.items.Item;

public class Player {
	
	private Node current;
	private Node[][] maze;
	private Game game;
	private List<Item> items = new ArrayList<Item>();
	
	private int health;

	public Player(Game game) {
		this.maze = game.getMaze();
		this.game = game;
		this.health = 100;
		/*
		 * player will be spawned in left top corner
		 */
		
		boolean spawned = false;
		Random generator = new Random();
		
		while(!spawned){
			/*
			 * This fix won't let random generator to place
			 * Player on the wall or any other object
			 */
			int randRow = generator.nextInt(maze.length / 5);
			int randCol = generator.nextInt(maze[0].length / 5);
			
			if (maze[randRow][randCol].isEmpty()){
				this.current = maze[randRow][randCol];
				this.current.setPlayer(this);
				spawned = true;
			}
		}
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
			
			if (!next.isWall()){
				makeMove(next);
				
				if (next.getItem() != null){
					/*
					 * collecting item
					 */
					Item item = next.getItem();
					items.add(item);
					next.setItem(null);
					System.out.println(item.getType() + " collected. With power " + item.getPower());
				}
				
				if (next.getMonster() != null){
					Monster monster = next.getMonster();
					monster.setPause(true);
					
					if (fight(monster)){
						// poisons the thread 
						monster.setAlive(false);
					}
				}
				
				if (next.isExit()){
					System.out.println("Well done!");
					game.setGameOver(true);
				}
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
		
		Fight fight = Fight.getInstance();
		
		double victory = fight.getVictory(monster.getStrength(), getItemPower(), health);
		
		health = (int) (health * victory);
		return true;
	}
	
	private void makeMove(Node next){
		next.setPlayer(this);
		current.setPlayer(null);
		
		current = next;
		next = null;
	}
	
	private int getItemPower(){
		/*
		 * Get last element power,
		 * or if has no weapons it sets power to 0
		 */
		if (items.size() > 0){
			int power = items.get(items.size()-1).getPower();
			items.remove(items.size()-1);
			return power;
		}
		return 0;
	}
	
	public Item[] getItems(){
		return items.toArray(new Item[items.size()]);
	}

	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public Node getPosition(){
		return this.current;
	}
	
	
}
