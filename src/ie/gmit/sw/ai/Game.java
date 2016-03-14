package ie.gmit.sw.ai;

import java.awt.*;
import java.awt.event.*;

import javax.management.monitor.MonitorSettingException;
import javax.swing.JFrame;

import ie.gmit.sw.ai.node.*;

public class Game implements KeyListener{
	private Node[][] maze;
	private Player player;
	private Component mazeview;
	private boolean gameOver = false;
	private Thread[] monsters = new Thread[3];
	private Monster[] mons = new Monster[3];
	
	public Game(int rows, int cols){
		maze = new Maze(rows, cols).getMaze();
		
		// set player node at position 0,0
		player = new Player(maze[0][0], this);	
		
		
		displayMaze();
		
		// spawn a monster
		for (int i = 0; i < monsters.length; i++){
			mons[i] = new Monster(maze, mazeview);
			monsters[i] = new Thread(mons[i]);
			monsters[i].start();
		}
		
	}

	private void displayMaze(){
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
		
	public void killMonster(Node inNode){
		
	}
	
	public Node[][] getMaze() {
		return maze;
	}
	

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
		
		if (this.gameOver){
			for (int i = 0; i < mons.length; i++){
				mons[i].setAlive(false);
			}
		} else{
			for (int i = 0; i < mons.length; i++){
				mons[i].setAlive(true);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		/*
		 * move up 		-> w || up arrow
		 * move down	-> s || down arrow
		 * move left	-> a || left arrow
		 * move right	-> d || right arrow
		 */
		if (!gameOver)
			switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
				case KeyEvent.VK_W:
					System.out.println("move up");
					player.move(Direction.Up);
					break;
				case KeyEvent.VK_DOWN:
				case KeyEvent.VK_S:
					System.out.println("move down");
					player.move(Direction.Down);
					break;
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_A:
					System.out.println("move left");
					player.move(Direction.Left);
					break;
				case KeyEvent.VK_RIGHT:
				case KeyEvent.VK_D:
					System.out.println("move right");
					player.move(Direction.Right);
					break;
			}
		mazeview.repaint();
		
	}

	@Override
	public void keyReleased(KeyEvent e){}
	@Override
	public void keyTyped(KeyEvent e){}
}
