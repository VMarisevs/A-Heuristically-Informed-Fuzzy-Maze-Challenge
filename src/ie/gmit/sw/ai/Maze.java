package ie.gmit.sw.ai;

import java.util.Random;

import ie.gmit.sw.ai.node.Node;
import ie.gmit.sw.ai.node.characters.Monster;
import ie.gmit.sw.ai.node.items.*;

public class Maze {

	private Node[][] maze;
	
	public Maze(int rows, int cols){
		maze = new Node[rows][cols];
		
		init();
		buildMaze();
		
		spawnItems((int)((rows * cols) * 0.02));
	}
	
	private void spawnItems(int count){
		Random generator = new Random();
		
		int counter = 0;
		
		while (counter < count){
			int row = generator.nextInt(maze.length);
			int col = generator.nextInt(maze[0].length);
			
			
			if (maze[row][col].isEmpty()){
				
				
				Item item = null;
				switch (new Random().nextInt(5)) {
				case 0:
					item = new Sword();
					break;
				case 1:
					item = new Gun();
					break;
				case 2:
					item = new Grenade();
					break;
				case 3:
					item = new ABomb();
					break;
				case 4:
					item = new Clue();

				}
				
				maze[row][col].setItem(item);
						
				counter++;
			}
		}
	}
	
	public Node generateExit(){
		Random generator = new Random();
		
		boolean spawned = false;
		
		while (!spawned){
			int row = generator.nextInt(maze.length/2) + maze.length/2;
			int col = generator.nextInt(maze[0].length/2) + maze[0].length/2;
			
			if (maze[row][col].isEmpty()){
				maze[row][col].setExit(true);
				spawned = true;
				
				return maze[row][col];
			}
		}
		
		return null;
	}
	
	private void init(){
		for (int row = 0; row < maze.length; row++){
			for (int col = 0; col < maze[row].length; col++){
				maze[row][col] = new Node(row,col);
				maze[row][col].setWall(true);
			}
			
		}
	}
	
	private void buildMaze(){
		for (int row = 0; row < maze.length; row++){
			
			for (int col = 0; col < maze[row].length - 1; col++){
				int num = (int) (Math.random() * 10);
				if (num >= 5 && col + 1 < maze[row].length){
					maze[row][col + 1].setWall(false);
					continue;
				}
				if (row + 1 < maze.length){ //Check south
					maze[row + 1][col].setWall(false);
				}				
			}
		}	
	}
	
	public Node[][] getMaze(){
		return this.maze;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for (int row = 0; row < maze.length; row++){
			for (int col = 0; col < maze[row].length; col++){
				sb.append(maze[row][col]);
				if (col < maze[row].length - 1) sb.append(",");
			}
			sb.append("\n");
		}
		return sb.toString();
	}


	public static void clearMaze(Node[][] maze, Monster me){
		int explored = 0;
		for (int i = 0; i < maze.length; i++){
			for (int j = 0; j < maze[i].length; j++){
				if (maze[i][j].isVisited(me))
					explored++;
				maze[i][j].setVisited(me, false);
			}
		}
		System.out.println("Nodes explored: " + explored);
	}

}
