package ie.gmit.sw.ai;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import ie.gmit.sw.ai.node.Node;
import ie.gmit.sw.ai.node.NodeType;

public class Game implements KeyListener{
	Node[][] maze;
	Node player;
	Component mazeview;
	
	public Game(int rows, int cols){
		maze = new Maze(20, 20).getMaze();
		
		// set player node at position 0,0
		player = maze[0][0];
		player.setType(NodeType.Player);
		
		
		// display maze
		mazeview = new MazeView(maze);
		Dimension dimension = new Dimension(MazeView.DEFAULT_VIEW_SIZE, MazeView.DEFAULT_VIEW_SIZE + 100);
    	mazeview.setPreferredSize(dimension);
    	mazeview.setMinimumSize(dimension);
    	mazeview.setMaximumSize(dimension);
    	
    	JFrame frame = new JFrame("GMIT - B.Sc. in Computing (Software Development)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());
        frame.add(mazeview);
        frame.addKeyListener(this);
        frame.setSize(1000,1000);
        frame.setLocation(100,100);
        frame.pack();
        frame.setVisible(true);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		/*
		 * move up 		-> w || up arrow
		 * move down	-> s || down arrow
		 * move left	-> a || left arrow
		 * move right	-> d || right arrow
		 */

		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
			case KeyEvent.VK_W:
				System.out.println("move up");
				movePlayer(Direction.Up);
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S:
				System.out.println("move down");
				movePlayer(Direction.Down);
				break;
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
				System.out.println("move left");
				movePlayer(Direction.Left);
				break;
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				System.out.println("move right");
				movePlayer(Direction.Right);
				break;
		}
		mazeview.repaint();
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private void movePlayer(Direction dir){
		Node[] stepAvailable = player.getChildren(maze);
		
		Node next = null;
		
		for (int i = 0; i < stepAvailable.length; i++){			
			switch (dir){
				case Up:
					// we can move up
					if ((stepAvailable[i].getCol() == player.getCol()) && (stepAvailable[i].getRow() + 1 == player.getRow()))
						next = stepAvailable[i];
					break;
				case Down:
					// we can move down
					if ((stepAvailable[i].getCol() == player.getCol()) && (stepAvailable[i].getRow() - 1 == player.getRow()))
						next = stepAvailable[i];
					break;
				case Left:
					// we can move left
					if ((stepAvailable[i].getCol() + 1 == player.getCol()) && (stepAvailable[i].getRow() == player.getRow()))
						next = stepAvailable[i];
					break;
				case Right:
					// we can move right
					if ((stepAvailable[i].getCol() - 1 == player.getCol()) && (stepAvailable[i].getRow() == player.getRow()))
						next = stepAvailable[i];
					break;
			}
		}
		
		// checking if we can move there
		if (next != null){
			next.setType(NodeType.Player);
			player.setType(NodeType.Empty);
			
			player = next;
			next = null;
		} else{
			System.out.println("can't move there");
		}
		
		
	}
}
