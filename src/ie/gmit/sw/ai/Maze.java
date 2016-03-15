package ie.gmit.sw.ai;

import ie.gmit.sw.ai.node.Node;
import ie.gmit.sw.ai.node.NodeType;

public class Maze {

	private Node[][] maze;
	
	public Maze(int rows, int cols){
		maze = new Node[rows][cols];
		
		init();
		buildMaze();
	}
	
	private void init(){
		for (int row = 0; row < maze.length; row++){
			for (int col = 0; col < maze[row].length; col++){
				maze[row][col] = new Node(row,col);//.setType(NodeType.Wall);
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
}
