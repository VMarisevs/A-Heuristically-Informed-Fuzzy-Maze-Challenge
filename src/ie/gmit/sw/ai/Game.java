package ie.gmit.sw.ai;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;

import ie.gmit.sw.ai.node.*;
import ie.gmit.sw.ai.node.characters.*;

public class Game implements KeyListener{
	private Node[][] maze;
	private Player player;
	private Node goal;
	private MazeView mazeview;
	private boolean gameOver = false;
	private boolean pause = false;
	private Monster[] monsters = new Monster[5];
	
	public Game(int rows, int cols){
		
		Maze mz = new Maze(rows, cols);
		maze = mz.getMaze();
		
		goal = mz.generateExit();
		
		// set player node at position 0,0
		player = new Player(this);
		
		
		displayMaze();
		
		// spawn a monster
		spawnMonster();
		
	}

	private void spawnMonster(){
		for (int i = 0; i < monsters.length; i++){
			if (monsters[i] == null){
				monsters[i] = new Monster(maze, player, this);
				new Thread(monsters[i]).start();
			}
				
		}
	}
	
	public void spawnMonster(Monster monster){
		for (int i = 0; i < monsters.length; i++){
			if (monsters[i] == monster){
				monsters[i] = new Monster(maze, player, this);
				new Thread(monsters[i]).start();
			}
				
		}
	}
	
	private void displayMaze(){
		// display maze
		mazeview = new MazeView(maze, player);
		Dimension dimension = new Dimension(MazeView.DEFAULT_WINDOW_WIDTH, MazeView.DEFAULT_WINDOW_HEIGHT);
    	mazeview.setPreferredSize(dimension);
    	mazeview.setMinimumSize(dimension);
    	mazeview.setMaximumSize(dimension);
    	
    	JFrame frame = new JFrame("GMIT - B.Sc. in Computing (Software Development)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());
        frame.add(mazeview);
        frame.addKeyListener(this);
        frame.setSize(1000,1000);
        frame.setLocation(200,100);
        frame.pack();
        frame.setVisible(true);
	}
	
	public Node[][] getMaze() {
		return maze;
	}
	
	
	
	
	public boolean isPause() {
		return pause;
	}

	public Node getGoal() {
		return goal;
	}

	public void setGoal(Node goal) {
		this.goal = goal;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
		
		if (this.gameOver){
			for (int i = 0; i < monsters.length; i++){
				monsters[i].setPause(true);
			}
		} else{
			for (int i = 0; i < monsters.length; i++){
				monsters[i].setPause(false);
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
				case KeyEvent.VK_Z:
					if (mazeview.isZoomOut())
						mazeview.setZoomOut(false);
					else
						mazeview.setZoomOut(true);
					break;
				case KeyEvent.VK_OPEN_BRACKET:
					player.setPreviousCurrentItem();
					break;
				case KeyEvent.VK_CLOSE_BRACKET:
					player.setNextCurrentItem();
					break;
				case KeyEvent.VK_E:
					player.plantBomb();
					break;
				case KeyEvent.VK_P:
					if (this.pause)
						this.pause = false;
					else
						this.pause = true;
					break;
			}
		mazeview.repaint();
		
	}

	@Override
	public void keyReleased(KeyEvent e){}
	@Override
	public void keyTyped(KeyEvent e){}
}
