package ie.gmit.sw.ai;

import ie.gmit.sw.ai.node.*;

public class Maze {

	private Node[][] maze;
	
	public Maze(int rows, int cols){
		maze = new Node[rows][cols];
		generateMaze();
	}
	
	//Binary tree algorithm for creating a maze. Adds a bias into the generated structure
	//For each node in the maze (2D array), randomly create a passage either north or west, but not both
	private void generateMaze(){
		for (int row = 0; row < maze.length; row++){
			for (int col = 0; col < maze[row].length; col++){
				maze[row][col] = new Node(row,col);
				int num = (int) (Math.random() * 10);
				
				if (col > 0 && (row == 0 || num >= 5)){
					maze[row][col].setPassage(NodePassage.West);
				}else{
					maze[row][col].setPassage(NodePassage.North);
				}
			}
		}
	}
	
	public Node[][] getMaze(){
		return maze;
	}

	public String toString(){
		StringBuffer sb = new StringBuffer();
		for (int row = 0; row < maze.length; row++){
			for (int col = 0; col < maze[row].length; col++){
				sb.append(maze[row][col]);
			}
			sb.append("\n");
		}
		return sb.toString();		
	}
	
	public static void main(String[] args) {
		System.out.println(new Maze(20,20).toString());
	}
	
}
