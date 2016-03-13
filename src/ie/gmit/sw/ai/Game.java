package ie.gmit.sw.ai;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;

import ie.gmit.sw.ai.node.Node;
import ie.gmit.sw.ai.node.NodeType;

public class Game {
	Node[][] maze;
	Node player;
	
	public Game(int rows, int cols){
		maze = new Maze(20, 20).getMaze();
		
		// set player node at position 0,0
		player = maze[0][0];
		player.setType(NodeType.Player);
		
		
		// display maze
		MazeView mazeview = new MazeView(maze);
		Dimension dimension = new Dimension(MazeView.DEFAULT_VIEW_SIZE, MazeView.DEFAULT_VIEW_SIZE);
    	mazeview.setPreferredSize(dimension);
    	mazeview.setMinimumSize(dimension);
    	mazeview.setMaximumSize(dimension);
    	
    	JFrame frame = new JFrame("GMIT - B.Sc. in Computing (Software Development)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());
        frame.add(mazeview);
        frame.setSize(1000,1000);
        frame.setLocation(100,100);
        frame.pack();
        frame.setVisible(true);
	}
}
