package ie.gmit.sw.ai;

import ie.gmit.sw.ai.node.Node;
import ie.gmit.sw.ai.node.NodeType;

public class Player {
	
	private Node position;
	private Game game;

	public Player(Node position, Game game) {
		this.position = position;
		this.position.setType(NodeType.Player);
		this.game = game;
	}
	
	public boolean move(Direction dir){
		Node[] stepAvailable = position.getChildren(game.getMaze());
		
		Node next = null;
		
		for (int i = 0; i < stepAvailable.length; i++){			
			switch (dir){
				case Up:
					// we can move up
					if ((stepAvailable[i].getCol() == position.getCol()) && (stepAvailable[i].getRow() + 1 == position.getRow()))
						next = stepAvailable[i];
					break;
				case Down:
					// we can move down
					if ((stepAvailable[i].getCol() == position.getCol()) && (stepAvailable[i].getRow() - 1 == position.getRow()))
						next = stepAvailable[i];
					break;
				case Left:
					// we can move left
					if ((stepAvailable[i].getCol() + 1 == position.getCol()) && (stepAvailable[i].getRow() == position.getRow()))
						next = stepAvailable[i];
					break;
				case Right:
					// we can move right
					if ((stepAvailable[i].getCol() - 1 == position.getCol()) && (stepAvailable[i].getRow() == position.getRow()))
						next = stepAvailable[i];
					break;
			}
		}
		
		// checking if we can move there
		if (next != null){
			
			switch(next.getType()){
				case Empty:
					next.setType(NodeType.Player);
					position.setType(NodeType.Empty);
					
					position = next;
					next = null;
					break;
				case Exit:
					next.setType(NodeType.Player);
					position.setType(NodeType.Empty);
					
					position = next;
					next = null;
					
					game.setPause(true);
					System.out.println("Well done!");
					break;
			}
			
		} else{
			System.out.println("can't move there");
			return false;
		}
		
		return true;
	}
}
