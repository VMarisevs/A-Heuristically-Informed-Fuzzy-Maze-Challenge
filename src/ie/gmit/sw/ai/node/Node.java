package ie.gmit.sw.ai.node;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import ie.gmit.sw.ai.node.characters.Monster;
import ie.gmit.sw.ai.node.characters.Player;


public class Node {
	
	private NodeType type;
	private Monster monster;
	private Player player;
	
	private boolean visited;
	
	private Color color = Color.BLACK;
	private int row = -1;
	private int col = -1;
	
	
	// Constructor
	public Node(int row, int col) {
		this.row = row;
		this.col = col;
		this.type = NodeType.Empty;
	}
	
	// get child nodes
	public Node[] getChildren(Node[][] maze){
		
		List<Node> children = new ArrayList<Node>();
		
		if ((col - 1 >= 0) && maze[row][col - 1].getType() != NodeType.Wall)
			children.add(maze[row][col - 1]);
		if ((col + 1 < maze[row].length) && maze[row][col + 1].getType() != NodeType.Wall)
			children.add(maze[row][col + 1]);
		if ((row - 1 >= 0) && maze[row - 1][col].getType() != NodeType.Wall)
			children.add(maze[row - 1][col]);
		if ((row + 1 < maze.length) && maze[row + 1][col].getType() != NodeType.Wall)
			children.add(maze[row + 1][col]);

		// return max 4 children if there is no walls between them 
		return (Node[])children.toArray(new Node[children.size()]);
	}
	
	public NodeType getType() {
		return type;
	}
	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public void setType(NodeType type) {
		this.type = type;
		switch (type){
			case Empty:
				this.color = Color.BLACK;
				break;
			case Player:
				this.color = Color.GREEN;
				break;
			case Item:
				this.color = Color.YELLOW;
				break;
			case Monster:
				this.color = Color.RED;
				break;
			case Wall:
				this.color = Color.LIGHT_GRAY;
				break;
			case Exit:
				this.color = Color.CYAN;
				break;
		}
	}
	
	public void setMonster(Monster monster){
		this.monster = monster;
	}
	
	public Monster getMonster(){
		return monster;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public boolean isVisited() {
		return visited;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	public Color getColor() {
		return color;
	}

	
	public String toString() {
		switch(type){
			case Wall:
				return "w";
			case Player:
				return "p";
			case Exit:
				return "e";
			default:
				return " ";
		}
		/*
		if (passage == NodePassage.North){
			return "N ";
		}else{
			return "W ";
		}*/
	}
	
}
