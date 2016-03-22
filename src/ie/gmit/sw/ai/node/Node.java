package ie.gmit.sw.ai.node;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.*;

import ie.gmit.sw.ai.node.characters.Monster;
import ie.gmit.sw.ai.node.characters.Player;
import ie.gmit.sw.ai.node.items.Bomb;
import ie.gmit.sw.ai.node.items.Item;


public class Node {
	
	private Monster monster;
	private Player player;
	private Item item;
	private boolean wall;
	private boolean exit;
	
	private BufferedImage image;
	
	private int row = -1;
	private int col = -1;
	
	// for traversator
	private Map<Monster, Node> parent;
	private Map<Monster, Boolean> visited;
	
	// for radar
	private boolean radarVisited;
	
	// for bomb
	private Map<Bomb, Boolean> bombVisited;
	
	// Constructor
	public Node(int row, int col) {
		this.row = row;
		this.col = col;
		
		parent = new HashMap<Monster, Node>();
		visited = new HashMap<Monster,Boolean>();
		bombVisited = new HashMap<Bomb,Boolean>();
	}
	
	// get child nodes
	public Node[] getChildren(Node[][] maze){
		
		List<Node> children = new ArrayList<Node>();
		
		if ((col - 1 >= 0) 
				&& !maze[row][col - 1].isWall())
			children.add(maze[row][col - 1]);
		if ((col + 1 < maze[row].length) 
				&& !maze[row][col + 1].isWall())
			children.add(maze[row][col + 1]);
		if ((row - 1 >= 0) 
				&& !maze[row - 1][col].isWall())
			children.add(maze[row - 1][col]);
		if ((row + 1 < maze.length) && 
				!maze[row + 1][col].isWall())
			children.add(maze[row + 1][col]);

		// return max 4 children if there is no walls between them 
		return (Node[])children.toArray(new Node[children.size()]);
	}
	

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public boolean isVisited(Monster me) {
		boolean visited = false;
		if (this.visited.containsKey(me))
			visited = this.visited.get(me);
		return visited;
	}

	public void setVisited(Monster me, boolean visited){
		this.visited.put(me, visited);
	}
	
	public boolean isVisited(Bomb current){
		boolean visited = false;
		if (this.bombVisited.containsKey(current))
			visited = this.bombVisited.get(current);
		return visited;
	}
	
	public void setVisited(Bomb current, boolean visited){
		this.bombVisited.put(current, visited);
	}
	
	public Node getParent(Monster me) {
		return this.parent.get(me);
	}
	public void setParent(Monster me, Node parent) {
		this.parent.put(me, parent);
	}
	
	
	public boolean isRadarVisited() {
		return radarVisited;
	}

	public void setRadarVisited(boolean radarVisited) {
		this.radarVisited = radarVisited;
	}

	
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public Color getColor() {
		Color color = Color.BLACK;
		
		if (this.isEmpty())
			color = Color.DARK_GRAY;
		
		if (this.getItem() != null){
			color = item.getColor();
		}
		
		if (this.isExit()){
			color = Color.MAGENTA;
		}
		
		if (this.monster != null)
			color = Color.RED;
		
		if (this.player != null)
			color = Color.GREEN;
		
		return color;
	}
	
	public boolean isWall() {
		return wall;
	}

	public void setWall(boolean wall) {
		this.wall = wall;
	}
	
	public boolean isExit() {
		return exit;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public boolean isEmpty(){
		// if there is no items or players or monsters or not a wall or exit
		if (this.monster == null
			&& this.player == null
			&& this.item == null			
			&& this.wall == false
			&& this.exit == false
			)return true;
		
		return false;
	}

	// This Heuristic is used for calculating path to a goal node
	public int getHeuristic(Node goal){
		double x1 = this.col;
		double y1 = this.row;
		double x2 = goal.getCol();
		double y2 = goal.getRow();
		return (int) Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
	}
	
	//public void clearVisited(){
	//	this.visited.clear();
	//}
}
