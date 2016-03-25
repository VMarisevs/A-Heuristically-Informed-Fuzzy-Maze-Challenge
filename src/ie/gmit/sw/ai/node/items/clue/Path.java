package ie.gmit.sw.ai.node.items.clue;

import ie.gmit.sw.ai.UpdateView;
import ie.gmit.sw.ai.node.Node;
import ie.gmit.sw.ai.node.items.Clue;
import ie.gmit.sw.resources.Resources;

public class Path implements Runnable {

	private Node[][] maze;
	private Node start;
	private Clue clue;
	private Node goal;
	
	private static final int STEPS_TO_SHOW = 5;
	
	public Path(Node[][] maze, Node start, Node goal, Clue clue) {
		this.maze = maze;
		this.start = start;
		this.goal = goal;
		this.clue = clue;
	}
	
	@Override
	public void run() {
		AStarExit astar = new AStarExit(maze, clue, goal);
		
		Node[] path = astar.traverse(start);
		
		for (int i = 0; i < STEPS_TO_SHOW; i++){
			
			if (path.length <= i)
				break;
			
			path[i].setImage(Resources.getInstance().getClue());
		}
		UpdateView.getInstance().repaint();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < STEPS_TO_SHOW; i++){
			
			if (path.length <= i)
				break;
			
			path[i].setImage(null);
		}
		UpdateView.getInstance().repaint();
		
	}

}
