package snake;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Snake implements ActionListener, KeyListener {
	
	private JFrame snakeFrame;
	private static RenderPanel panel;
	protected static Snake snake;
	private Timer timer = new Timer(70, this);
	protected ArrayList<Point> parts = new ArrayList<Point> ();
	private int miniumum = 5;
	private static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
	protected static final int SCALE = 20;
	private int ticks = 0, direction = DOWN;
	protected int score, time;
	protected int snakeLength = 10;
	protected Point head, apple;
	private Random random;
	protected Boolean end = false, paused = false;
	private Dimension dim;
	protected int screenWidth = 800;
	protected int screenHeight = 600;
	
	public Snake() {
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		snakeFrame = new JFrame("snake");
		snakeFrame.setVisible(true);
		snakeFrame.setSize(screenWidth, screenHeight);
		snakeFrame.setResizable(false);;
		snakeFrame.setLocation(dim.width / 2 - snakeFrame.getWidth() / 2, dim.height / 2 - snakeFrame.getHeight() / 2);
		snakeFrame.add(panel = new RenderPanel());
		snakeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		snakeFrame.addKeyListener(this);
		startGame();
	}
	
	private void startGame() {
		end = false;
		paused = false;
		time = 0;
		score = 0;
		ticks = 0;
		snakeLength = 5;
		direction = DOWN;
		head = new Point(5, 5);
		random = new Random();
		parts.clear();
		apple = new Point(miniumum + random.nextInt(30), miniumum +  random.nextInt(20));
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		panel.repaint();
		ticks++;

		if (ticks % 2 == 0 && head != null && !end && !paused) {
			time++;
			
			parts.add(new Point(head.x, head.y));
			
			if (direction == DOWN) {
				if (head.y + 1 < 25 && checkTail(head.x, head.y + 1)) {
					head = new Point(head.x, head.y+1);
				} else {
					end = true;
				}
			}
			
			if (direction == UP) {
				if (head.y - 1 >= 0 && checkTail(head.x, head.y - 1)) {
					head = new Point(head.x, head.y-1);
				} else {
					end = true;
				}
			}	
			
			if (direction == LEFT) {
				if (head.x - 1 >= 0 && checkTail(head.x - 1, head.y)){
					head = new Point(head.x-1, head.y);
				} else {
					end = true;
				}
			}
			
			if (direction == RIGHT) {
				if (head.x + 1 < 35 && checkTail(head.x + 1, head.y)){
					head = new Point(head.x+1, head.y);
				} else {
					end = true;
				}
			}
	
			Collision();
			
			if (parts.size() > snakeLength) {
				parts.remove(0);
			}
		}

	}
	
	public void Collision() {
		if (apple != null) {
			if (head.equals(apple)) {
				snakeLength++;
				score+=5;
				apple = null;
				apple = new Point(miniumum + random.nextInt(30), miniumum +  random.nextInt(20));
			}
		}
		
		if (head.x < 0 || head.x > 600 || head.y < 0 || head.y > 400) {
			end = true;
		}
		
		for (Point point : parts) {
			if (head.x == point.x && head.y == point.y) {
				end = true;
			}
		}
		
	}
	
	public Boolean checkTail(int x, int y) {
			for (Point point : parts) {
				if (point.equals(new Point(x, y))) {
					return false;
				}
			}
		return true;
	}
	
	public static void main(String[] args) {
		snake = new Snake();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if ((key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) && direction != RIGHT ) {
			direction = LEFT;
		}

		if ((key == KeyEvent.VK_W || key == KeyEvent.VK_UP) && direction != DOWN ) {
			direction = UP;
		}

		if ((key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) && direction != LEFT ) {
			direction = RIGHT;
		}

		if ((key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) && direction != UP ) {
			direction = DOWN;
		}
		
		if (key == KeyEvent.VK_SPACE) {
			if (end) {
				startGame();
			} else {
				if (paused) {
					paused = false;
				} else {
					paused = true;
				}
			}
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}


}
