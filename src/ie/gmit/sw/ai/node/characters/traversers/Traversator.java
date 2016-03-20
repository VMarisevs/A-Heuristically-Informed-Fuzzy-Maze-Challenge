package ie.gmit.sw.ai.node.characters.traversers;

import ie.gmit.sw.ai.node.Node;
import ie.gmit.sw.ai.node.characters.Monster;

public interface Traversator {
	public Node traverse(Node[][] maze, Node start, Monster me);
}
