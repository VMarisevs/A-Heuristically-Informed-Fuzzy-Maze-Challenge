package ie.gmit.sw.ai.node.items.clue;

import java.util.Deque;
import java.util.LinkedList;

import ie.gmit.sw.ai.node.Node;
import ie.gmit.sw.ai.node.items.Clue;

public class IDAStarExit {

	private Node goal;
	private Node[][] maze;
	private Clue clue;
	private Node[] path;
	
	public IDAStarExit(Node[][] maze,Clue clue, Node goal) {
		this.goal = goal;
		this.maze = maze;
		this.clue = clue;
	}
	
	public Node[] traverse(Node node){
		
		int bound = node.getHeuristic(goal);
		boolean complete = false;
		
		do{
			int result = contour(node, 0, bound);
			System.out.println(result);
			if (result == Integer.MIN_VALUE) complete = true; //Found
			if (result == Integer.MAX_VALUE) return null; //Not found				
			
			bound = result;
			

			if (!complete) {
				unvisit();					
			}
			
		}while(!complete);
		
		//unvisit();
		
		return path;
	}
	
	private int contour(Node node, int g, int bound){
		node.setVisited(clue , true);
		node.setPathCost(g);
		
		int f = g + node.getHeuristic(goal);
		if (f > bound) return f;
		if (node.isExit()){
			path = calculatePath(node);
			return Integer.MIN_VALUE; //Denotes found
		}
		
		int min = Integer.MAX_VALUE; //Denotes not found
		
		Node[] children = node.getChildren(maze);
		for (int i = 0; i < children.length; i++) {
			if (children[i] != null && !children[i].isVisited(clue)){		
				children[i].setParent(clue, node);
				int result = contour(children[i], g + 1, bound);
				if (result == Integer.MIN_VALUE) return Integer.MIN_VALUE;				
				if (result < min) min = result;
			}
		}
		return min;		
	}

	private Node[] calculatePath(Node node){
		/*
		 * looping back using parents
		 * 
		 */
		Deque<Node> result = new LinkedList<Node>();
		
		while(node.getParent(clue) != null){
			node = node.getParent(clue);
			result.addFirst(node);
		}
		System.out.println(result.size());
		return result.toArray(new Node[result.size()]);
	}
	
	private void unvisit(){
		for (int i = 0; i < maze.length; i++){
			for (int j = 0; j < maze[i].length; j++){
				maze[i][j].setVisited(clue , false);
			}
		}
	}
}
