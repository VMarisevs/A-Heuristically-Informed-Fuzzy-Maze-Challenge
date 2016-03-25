package ie.gmit.sw.ai.node.items.clue;

import java.util.*;

import ie.gmit.sw.ai.node.Node;
import ie.gmit.sw.ai.node.items.Clue;

public class AStarExit {
	
	private Node goal;
	private Node[][] maze;
	private Clue clue;
	
	private Deque<Node> result;
	
	public AStarExit(Node[][] maze,Clue clue, Node goal) {
		this.goal = goal;
		this.maze = maze;
		this.clue = clue;
		
		result = new LinkedList<Node>();
	}
	
	public Node[] traverse(Node node){
		PriorityQueue<Node> open = new PriorityQueue<Node>(20, (Node current, Node next)-> (current.getPathCost() + current.getHeuristic(goal)) - (next.getPathCost() + next.getHeuristic(goal)));
		java.util.List<Node> closed = new ArrayList<Node>();
		
		open.offer(node);
		node.setPathCost(0);
		
		while(!open.isEmpty()){
			node = open.poll();
			closed.add(node);
			node.setVisited(clue,true);
			
			if (node.isExit()){
				// found exit
				return calculatePath(node);
			}
			
			Node[] children = node.getChildren(maze);
			
			for (int i = 0; i < children.length; i++){
				Node child = children[i];
				
				int score = node.getPathCost() + 1 + child.getHeuristic(goal);
				int existing = child.getPathCost() + child.getHeuristic(goal);
				
				if ((open.contains(child) || closed.contains(child)) && existing < score){
					continue;
				} else {
					open.remove(child);
					closed.remove(child);
					child.setParent(clue, node);
					child.setPathCost(node.getPathCost() + 1);
					open.add(child);
				}
			}
			
		}
		
		// no exit found
		return null;
		
	}


	private Node[] calculatePath(Node node){
		/*
		 * looping back using parents
		 * 
		 */
		
		while(node.getParent(clue) != null){
			node = node.getParent(clue);
			result.addFirst(node);
		}
		
		return result.toArray(new Node[result.size()]);
	}
}
