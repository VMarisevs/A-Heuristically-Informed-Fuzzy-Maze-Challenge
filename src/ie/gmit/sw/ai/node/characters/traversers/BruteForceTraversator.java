package ie.gmit.sw.ai.node.characters.traversers;

import java.util.Deque;
import java.util.LinkedList;

import ie.gmit.sw.ai.node.Node;
import ie.gmit.sw.ai.node.NodeType;
import ie.gmit.sw.ai.node.characters.Monster;

public class BruteForceTraversator implements Traversator{

	
	@Override
	public Node traverse(Node[][] maze, Node node, Monster me) {

		Deque<Node> queue = new LinkedList<Node>();
		queue.offer(node);
		
		while (!queue.isEmpty()){
			
			node = queue.poll();
			node.setVisited(true);
			
			if (node.getPlayer() != null){
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
					queue.addLast(children[i]);
				}
			}
		}
		
		return null;		
	}

}
