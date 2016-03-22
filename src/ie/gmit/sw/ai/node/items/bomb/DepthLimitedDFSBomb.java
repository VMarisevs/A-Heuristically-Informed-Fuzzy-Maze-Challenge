package ie.gmit.sw.ai.node.items.bomb;

import ie.gmit.sw.ai.UpdateView;
import ie.gmit.sw.ai.node.Node;
import ie.gmit.sw.ai.node.characters.Monster;
import ie.gmit.sw.ai.node.characters.Player;
import ie.gmit.sw.ai.node.items.Bomb;
import ie.gmit.sw.resources.Resources;

public class DepthLimitedDFSBomb {

	private static final int KEEP_FIRE_IMAGE = 1000;
	
	private Node[][] maze;
	private Bomb current;
	private Resources resources;
	
	public DepthLimitedDFSBomb(Node[][] maze, Bomb current) {
		this.resources = Resources.getInstance();
		this.maze = maze;
		this.current = current;
	}
	
	public void killLifeForms(Node start){
		
		depthLimitedDFS(start, 1);
		
		try {
			Thread.sleep(KEEP_FIRE_IMAGE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		clearNodes();
	}
	
	private void depthLimitedDFS(Node node, int depth){
		if (depth > current.getDepth()) return;
		
		node.setVisited(current, true);
		
		kill(node);
		
		node.setImage(resources.getExplosion());
		
		Node[] children = node.getChildren(maze);
		
		for (int i = 0; i < children.length; i++){
			if (children[i] != null && !children[i].isVisited(this.current)){
				depthLimitedDFS(children[i], depth + 1);
			}
		}
		
		UpdateView.getInstance().repaint();
	}
	
	private void kill(Node current){
		/*
		 * Kill all life forms in selected node
		 */
		if (current.getPlayer() != null){
			Player player = current.getPlayer();
			player.setHealth(0);
		}
		
		if (current.getMonster() != null){
			Monster monster = current.getMonster();
			monster.setAlive(false);
		}
	}

	private void clearNodes(){
		for (int i = 0; i < maze.length; i++ ){
			for (int j = 0; j < maze[i].length; j++){
				if (maze[i][j].isVisited(current)){
					maze[i][j].setVisited(current, false);
					maze[i][j].setImage(null);
				}
			}
		}
	}
}
