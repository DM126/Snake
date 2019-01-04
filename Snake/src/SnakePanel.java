import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//TODO
//Refactor
//Make a separate panel for the score keeping
//Show hiscores upon placing in top 10

public class SnakePanel extends JPanel
{
	private final int TILE_SIZE = 15; //pixels per tile
	private final int WIDTH = 32; //number of tiles wide
	private final int HEIGHT = 32; //number of tiles high
	private Goal goal;
	private Snake snake;
	private Timer timer;
	private Hiscores hiscores;
	private SnakeFrame parent;
	private boolean gameOver;
	//private JLabel newBestScore;
	
	public SnakePanel(SnakeFrame parent, Hiscores hiscores)
	{
		this.parent = parent;
		this.hiscores = hiscores;
		
		gameOver = false;
		//newBestScore = new JLabel("New Best Score!");
		//newBestScore.setForeground(Color.WHITE);
		//newBestScore.setVisible(false);
		//add(newBestScore);
		
		goal = new Goal();
		snake = new Snake(WIDTH / TILE_SIZE / 2, HEIGHT / TILE_SIZE / 2);
		
		SnakeListener listener = new SnakeListener();
		addKeyListener(listener);
		timer = new Timer(100, listener);
		
		setPreferredSize(new Dimension(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE));
		setBackground(Color.BLACK);
		setFocusable(true);
		timer.start();
	}
	
	public void paintComponent(Graphics page) 
	{
		super.paintComponent(page);
		
		//goal
		page.setColor(goal.getColor());
		page.fillOval(goal.getX() * TILE_SIZE, goal.getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
		
		//snake
		page.setColor(snake.getColor());
		for (int i = 0; i < snake.getLength(); i++) 
		{
			page.fillRect(snake.get(i).getX() * TILE_SIZE + 1, snake.get(i).getY() * TILE_SIZE + 1, TILE_SIZE - 2, TILE_SIZE - 2);
		}
		
		//score
		page.setColor(Color.WHITE);
		page.drawString("Score: " + (snake.getLength() - 1), 2, 12);
		int top;
		if (snake.getLength() - 1 > hiscores.getTopScore())
		{
			page.drawString("New High Score!", (WIDTH * TILE_SIZE / 2) - 50, 12);
			top = snake.getLength() - 1;
		}
		else
		{
			top = hiscores.getTopScore();
		}
		
		page.drawString("Best: " + top, (WIDTH * TILE_SIZE) - 60, 12);
	}
	
	/**
	 * @return true if the snake's head hits the boundaries or another segment
	 */
	public boolean hasCollision()
	{
		if (snake.getHeadX() < 0 || snake.getHeadX() >= WIDTH || snake.getHeadY() < 0 || snake.getHeadY() >= HEIGHT)
		{
			return true;
		}
		else if (snake.hitsBody())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Stops the timer, draws a game over message centered in the screen, and adds
	 * the score to the hiscores if applicable.
	 */
	public void gameOver()
	{
		gameOver = true;
		timer.stop();
		
		displayCenterredText("GAME OVER", getGraphics());
		
		hiscores.addScore(snake.getLength() - 1);
	}
	
	/**
	 * Displays text centered in the screen.
	 * 
	 * @param message the text to display
	 * @param g the graphics to draw on
	 */
	private void displayCenterredText(String message, Graphics g)
	{
		Font font = new Font("Arial", Font.PLAIN, 32);
		g.setFont(font);

		int stringLen = (int) g.getFontMetrics().getStringBounds(message, g).getWidth();
		int start = (WIDTH * TILE_SIZE)/2 - stringLen/2;
	    
		g.setColor(Color.WHITE);
		g.drawString(message, start, HEIGHT * TILE_SIZE / 2);
	}
	
	/**
	 * Resets the game.
	 */
	public void restart()
	{
		gameOver = false;
		//newBestScore.setVisible(false);
		goal.setNewPosition();
		snake = new Snake(WIDTH / TILE_SIZE / 2, HEIGHT / TILE_SIZE / 2);
		timer.restart();
		repaint();
	}
	
	/**
	 * Pauses the game and covers the screen.
	 */
	public void pause()
	{
		if (timer.isRunning())
		{
			timer.stop();
		}
		else
		{
			timer.start();
		}
		
		Graphics g = this.getGraphics();
		g.fillRect(0, 0, WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
		displayCenterredText("PAUSED", g);
	}
	
	private class SnakeListener implements ActionListener, KeyListener 
	{
		public void keyPressed(KeyEvent event) 
		{
			switch (event.getKeyCode()) 
			{
			case KeyEvent.VK_UP: 
			{
				if (snake.getHead().getMY() == 0) 
				{
					snake.getHead().changeDirection(0, -1);
				}
				break;
			}
			case KeyEvent.VK_DOWN: 
			{
				if (snake.getHead().getMY() == 0) 
				{
					snake.getHead().changeDirection(0, 1);
				}
				break;
			}
			case KeyEvent.VK_LEFT: 
			{
				if (snake.getHead().getMX() == 0) 
				{
					snake.getHead().changeDirection(-1, 0);
				}
				break;
			}
			case KeyEvent.VK_RIGHT: 
			{
				if (snake.getHead().getMX() == 0) 
				{
					snake.getHead().changeDirection(1, 0);
				}
				break;
			}
			case KeyEvent.VK_SPACE:
			{
				if (gameOver)
				{
					restart();
				}
				else
				{
					pause();
				}
				break;
			}
			case KeyEvent.VK_ESCAPE:
			{
				//TODO: pause the game and show a warning/confirmation before quitting.
				timer.stop();
				parent.returnToMenu();
				break;
			}
			}
		}

		public void actionPerformed(ActionEvent event) 
		{	
			snake.move();
			
			if (snake.getHeadX() == goal.getX() && snake.getHeadY() == goal.getY()) 
			{
				goal.setNewPosition();
				snake.addSegment();
				//if (snake.getLength() - 1 > hiscores.getTopScore())
				//{
				//		newBestScore.setVisible(true);
				//}
				repaint();
			}
			else if (hasCollision())
			{
				gameOver();
			}
			else
			{
				repaint();
			}
		}
		
		public void keyReleased(KeyEvent event) {}
		public void keyTyped(KeyEvent event) {}
		
	}
}