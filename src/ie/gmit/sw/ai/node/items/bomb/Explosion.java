package ie.gmit.sw.ai.node.items.bomb;

import ie.gmit.sw.ai.node.Node;
import ie.gmit.sw.ai.node.items.Bomb;

public class Explosion implements Runnable{

	private Node[][] maze;
	private Bomb current;
	private Node start;
	
	public Explosion(Node[][] maze, Bomb current, Node start) {
		this.maze = maze;
		this.current = current;
		this.start = start;
	}

	@Override
	public void run() {
		/*
		 * delay before explosion
		 */
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("boom!!!");
		DepthLimitedDFSBomb dlbomb = new DepthLimitedDFSBomb(maze, current);
		
		dlbomb.killLifeForms(start);
		
	}
}
