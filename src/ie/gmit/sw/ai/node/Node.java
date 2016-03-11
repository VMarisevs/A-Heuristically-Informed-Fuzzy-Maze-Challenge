package ie.gmit.sw.ai.node;

import java.util.ArrayList;
import java.util.List;


public class Node {
	private NodePassage passage;
	private NodeType type;
	private boolean visited;
	
	private int row = -1;
	private int col = -1;
	
	
	// Constructor
	public Node(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	// get child nodes
	public Node[] getChildren(Node[][] maze){
		
		List<Node> children = new ArrayList<Node>();
		
		if ((col - 1 >= 0) && (passage == NodePassage.West))
			children.add(maze[row][col-1]);
		if ((col + 1 < maze[row].length) && (maze[row][col+1].getPassage() == NodePassage.West))
			children.add(maze[row][col+1]);
		if ((row - 1 >= 0) && (passage == NodePassage.North))
			children.add(maze[row-1][col]);
		if ((row + 1 < maze.length) && maze[row+1][col].getPassage() == NodePassage.North)
			children.add(maze[row+1][col]);
		
		// return max 4 children if there is no walls between them 
		return (Node[])children.toArray(new Node[children.size()]);
	}
	
	// getters and setters
	public NodePassage getPassage() {
		return passage;
	}
	public void setPassage(NodePassage passage) {
		this.passage = passage;
	}
	public NodeType getType() {
		return type;
	}
	public void setType(NodeType type) {
		this.type = type;
	}
	public boolean isVisited() {
		return visited;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	public String toString() {
		if (passage == NodePassage.North){
			return "N ";
		}else{
			return "W ";
		}
	}
	
}
