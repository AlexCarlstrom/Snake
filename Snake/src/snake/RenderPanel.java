package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RenderPanel extends JPanel {
	
	private static final Color GREEN = new Color(1666073);
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Snake snake = Snake.snake;
		
		g.setColor(new Color(6750054));
		g.fillRect(0, 0, snake.screenWidth, snake.screenHeight);
		g.setColor(Color.BLACK);
		g.drawRect(100, 100, snake.screenWidth-200, snake.screenHeight-200);
		g.setColor(new Color(3492351));
		
		for (Point point : snake.parts) {
			g.drawOval(point.x * Snake.SCALE, point.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		}
		
		g.fillOval(snake.head.x * Snake.SCALE, snake.head.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		
		
		String text1 = "SCORE: " + snake.score;
		String text2 = "LENGTH OF SNAKE: " + snake.snakeLength;
		String text3 = "TIME: " + snake.time / 20;
		
		g.drawString(text1, 100, 50);
		g.drawString(text2, 100, 65);
		g.drawString(text3, 100, 80);
		
		g.setColor(Color.RED);
		g.fillOval(snake.apple.x * Snake.SCALE, snake.apple.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		
		g.setFont(new Font("Serif", Font.PLAIN, 30));
		g.setColor(Color.BLACK);
		String endString = "Game Over!";
		if (snake.end) {
			g.drawString(endString, snake.screenWidth / 2 - endString.length() * 6, snake.screenHeight / 2);
		}
		String pausString = "Game Paused!";
		if (snake.paused) {
			g.drawString(pausString, snake.screenWidth / 2 - pausString.length() * 6, snake.screenHeight / 2);
		}
		
		
	}
}
