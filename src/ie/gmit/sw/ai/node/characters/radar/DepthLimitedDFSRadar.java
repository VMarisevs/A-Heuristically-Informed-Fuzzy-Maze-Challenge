package ie.gmit.sw.ai.node.characters.radar;

import java.util.*;

import ie.gmit.sw.ai.node.Node;

public class DepthLimitedDFSRadar {

	public static final int RADAR_DEPTH_LIMIT = 10;
	
	private static DepthLimitedDFSRadar instance;
	
	public static DepthLimitedDFSRadar getInstance(Node[][] maze){
		if (instance == null){
			instance = new DepthLimitedDFSRadar(maze);
		}
		
		return instance;
	}
	
	private Node[][] maze;
	private List<Node> nodes;
	
	private DepthLimitedDFSRadar(Node[][] maze) {
		this.maze = maze;
		nodes = new ArrayList<Node>();
	}
	
	public Node[] getLifeForms(Node start){
		nodes.clear();
		
		depthLimitedDFS(start, 1);
		
		clearNodes();
		
		return nodes.toArray(new Node[nodes.size()] );
	}
	
	private void depthLimitedDFS(Node node, int depth){
		if (depth > RADAR_DEPTH_LIMIT) return;
		
		node.setRadarVisited(true);
		
		if (node.getMonster() != null){
			nodes.add(node);
		}
		
		Node[] children = node.getChildren(maze);
		
		for (int i = 0; i < children.length; i++){
			if (children[i] != null && !children[i].isRadarVisited()){
				depthLimitedDFS(children[i], depth +1);
			}
		}
	}

	private void clearNodes(){
		for (int i = 0; i < maze.length; i++ ){
			for (int j = 0; j < maze[i].length; j++){
				maze[i][j].setRadarVisited(false);
			}
		}
	}
}
