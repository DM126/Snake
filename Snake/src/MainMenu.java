import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainMenu extends JPanel
{
	private JLabel title;
	private JLabel game, scores, exit;
	private JLabel[] selections;
	private SnakeFrame parent;
	private int selected; //the selected option. (0 to the number of labels in the array)
	
	public MainMenu(SnakeFrame parent)
	{
		this.parent = parent;
		
		selected = 0;
		
		title = new JLabel("Snake");
		title.setFont(new Font("Arial", Font.BOLD, 64));
		title.setForeground(Color.WHITE);
		
		Font selectionFont = new Font("Arial", Font.PLAIN, 32);
		game = new JLabel("Play Snake");
		game.setForeground(Color.GREEN);
		game.setFont(selectionFont);
		scores = new JLabel("High Scores");
		scores.setForeground(Color.WHITE);
		scores.setFont(selectionFont);
		exit = new JLabel("Exit");
		exit.setForeground(Color.WHITE);
		exit.setFont(selectionFont);
		
		selections = new JLabel[] { game, scores, exit };
		
		addKeyListener(new SelectionListener());
		
		add(title);
		add(game);
		add(scores);
		add(exit);
		
		setPreferredSize(new Dimension(230, 250));
		setBackground(Color.BLACK);
		setFocusable(true);
		
		repaint();
	}
	
	public void paintComponent(Graphics page)
	{
		super.paintComponent(page);
		
		JLabel current = selections[selected];
		page.setColor(Color.GREEN);
		page.fillOval(current.getX() - 20, current.getY() + 10, 15, 15);
	}
	
	/**
	 * Displays a confirmation dialog box and exits the program.
	 */
	private void exit()
	{
		int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION);
		
		if (choice == JOptionPane.YES_OPTION)
		{
			parent.close();
		}
	}
	
	/**
	 * Changes the selected option.
	 * 
	 * @param move the distance to move the selection. (-1 to move up, 1 to move down)
	 */
	private void changeSelection(int move)
	{
		if (selected + move >= 0 && selected + move < selections.length)
		{
			selections[selected].setForeground(Color.WHITE);
			selected += move;
			selections[selected].setForeground(Color.GREEN);
			repaint();
		}
	}
	
	private class SelectionListener implements KeyListener
	{
		public void keyPressed(KeyEvent event)
		{
			if (event.getKeyCode() == KeyEvent.VK_ENTER || event.getKeyCode() == KeyEvent.VK_SPACE)
			{
				JLabel current = selections[selected];
				
				if (current == game)
				{
					parent.startGameOriginal();
				}
				else if (current == scores)
				{
					parent.hiscores();
				}
				else if (current == exit)
				{
					exit();
				}
			}
			else if (event.getKeyCode() == KeyEvent.VK_DOWN)
			{
				changeSelection(1);
			}
			else if (event.getKeyCode() == KeyEvent.VK_UP)
			{
				changeSelection(-1);
			}
		}

		public void keyReleased(KeyEvent event) {}
		public void keyTyped(KeyEvent event) {}
	}
}
