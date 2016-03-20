package ie.gmit.sw.ai;

import java.util.Random;

import ie.gmit.sw.ai.node.Node;
import ie.gmit.sw.ai.node.NodeType;
import ie.gmit.sw.ai.node.items.*;

public class Maze {

	private Node[][] maze;
	
	public Maze(int rows, int cols){
		maze = new Node[rows][cols];
		
		init();
		buildMaze();
		
		spawnItems((int)((rows * cols) * 0.01));
		generateExit(1);
	}
	
	private void spawnItems(int count){
		Random generator = new Random();
		
		int counter = 0;
		
		while (counter < count){
			int row = generator.nextInt(maze.length);
			int col = generator.nextInt(maze[0].length);
			
			
			if (maze[row][col].getType() == NodeType.Empty){
				
				
				Item item = null;
				switch (new Random().nextInt(2)) {
				case 0:
					item = new Sword(maze[row][col]);
					break;
				case 1:
					item = new Gun(maze[row][col]);
					break;

				}
				
				maze[row][col].setType(item.getType());
				maze[row][col].setItem(item);
						
				counter++;
			}
		}
	}
	
	private void generateExit(int count){
		Random generator = new Random();
		
		int counter = 0;
		
		while (counter < count){
			int row = generator.nextInt(maze.length/2) + maze.length/2;
			int col = generator.nextInt(maze[0].length/2) + maze[0].length/2;
			
			if (maze[row][col].getType() == NodeType.Empty){
				maze[row][col].setType(NodeType.Exit);
				counter++;
			}
		}
	}
	
	private void init(){
		for (int row = 0; row < maze.length; row++){
			for (int col = 0; col < maze[row].length; col++){
				maze[row][col] = new Node(row,col);
				maze[row][col].setType(NodeType.Wall);
			}
			
		}
	}
	
	private void buildMaze(){
		for (int row = 0; row < maze.length; row++){
			
			for (int col = 0; col < maze[row].length - 1; col++){
				int num = (int) (Math.random() * 10);
				if (num >= 5 && col + 1 < maze[row].length){
					maze[row][col + 1].setType(NodeType.Empty);
					continue;
				}
				if (row + 1 < maze.length){ //Check south
					maze[row + 1][col].setType(NodeType.Empty);
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

	public static void clearMaze(Node[][] maze){
		int explored = 0;
		for (int i = 0; i < maze.length; i++){
			for (int j = 0; j < maze[i].length; j++){
				if (maze[i][j].isVisited())
					explored++;
				maze[i][j].setVisited(false);
			}
		}
		System.out.println("Nodes explored: " + explored);
	}
}
