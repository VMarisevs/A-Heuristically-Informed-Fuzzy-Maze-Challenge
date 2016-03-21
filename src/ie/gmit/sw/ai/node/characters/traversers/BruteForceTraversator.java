package ie.gmit.sw.ai.node.characters.traversers;

import java.util.*;

import ie.gmit.sw.ai.node.Node;
import ie.gmit.sw.ai.node.characters.Monster;

public class BruteForceTraversator implements Traversator{

	
	@Override
	public Node traverse(Node[][] maze, Node node, Monster me) {

		Deque<Node> queue = new LinkedList<Node>();
		queue.offer(node);
		
		while (!queue.isEmpty()){
			
			node = queue.poll();
			node.setVisited(me, true);
			
			if (node.getPlayer() != null){
				// we reached the goal!
				// roll back to next step
								
				while (node.getParent(me).getMonster() != me){
					node = node.getParent(me);
				}
				return node;
			}
			
			Node[] children = node.getChildren(maze);
			
			for (int i = 0; i < children.length; i++){
				if (children[i] != null && !children[i].isVisited(me)){
					children[i].setParent(me,node);
					queue.addLast(children[i]);
				}
			}
		}
		
		return null;		
	}

}
