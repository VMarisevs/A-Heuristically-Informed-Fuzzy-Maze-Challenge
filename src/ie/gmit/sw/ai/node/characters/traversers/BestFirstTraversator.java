package ie.gmit.sw.ai.node.characters.traversers;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

import ie.gmit.sw.ai.node.Node;
import ie.gmit.sw.ai.node.NodeType;
import ie.gmit.sw.ai.node.characters.Monster;

public class BestFirstTraversator implements Traversator {

	private Node player;
	
	public BestFirstTraversator(Node player) {
		this.player = player;
	}

	@Override
	public Node traverse(Node[][] maze, Node node, Monster me) {
		
		LinkedList<Node> queue = new LinkedList<Node>();
		queue.addFirst(node);
		
		while (!queue.isEmpty()){
			
			node = queue.poll();
			node.setVisited(true);
			
			if (node.getType() == NodeType.Player){
				// we reached the goal!
				// roll back to next step
								
				while (node.getParent().getMonster() != me){
					node = node.getParent();
				}
				//System.out.println("type:" + node.getType());
				return node;
			}
			
			Node[] children = node.getChildren(maze);
			
			for (int i = 0; i < children.length; i++){
				if (children[i] != null && !children[i].isVisited()){
					children[i].setParent(node);
					queue.addFirst(children[i]);
				}
			}
			
			// sort the queue
			Collections.sort(queue,(Node current, Node next) -> current.getHeuristic(player) - next.getHeuristic(player));		
		}
		
		return null;
	}
}
