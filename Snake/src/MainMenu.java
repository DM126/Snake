import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainMenu extends JPanel
{
	private JLabel title;
	private JLabel game;
	private JLabel scores;
	private JLabel exit;
	private JLabel[] selections;
	private SnakeFrame parentFrame;
	private int currentSelection; //the selected option. (0 to the number of labels in the array)
	
	private static final Color UNSELECTED = Color.WHITE;
	private static final Color SELECTED = Color.GREEN;
	
	public MainMenu(SnakeFrame parent)
	{
		this.parentFrame = parent;
		
		currentSelection = 0;
		
		title = LabelFactory.createLabel("Snake", new Font(SnakeFrame.FONT_NAME, Font.BOLD, 64), UNSELECTED);
		
		Font selectionFont = new Font(SnakeFrame.FONT_NAME, Font.PLAIN, 32);
		game = LabelFactory.createLabel("Play Snake", selectionFont, SELECTED);
		scores = LabelFactory.createLabel("High Scores", selectionFont, UNSELECTED);
		exit = LabelFactory.createLabel("Exit", selectionFont, UNSELECTED);
		
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
	
	@Override
	public void paintComponent(Graphics page)
	{
		super.paintComponent(page);
		
		JLabel current = selections[currentSelection];
		page.setColor(SELECTED);
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
			parentFrame.close();
		}
	}
	
	/**
	 * Changes the selected option.
	 * 
	 * @param move the distance to move the selection. (-1 to move up, 1 to move down)
	 */
	private void changeSelection(int move)
	{
		int newSelection = currentSelection + move;
		if (newSelection >= 0 && newSelection < selections.length)
		{
			selections[currentSelection].setForeground(UNSELECTED);
			currentSelection = newSelection;
			selections[currentSelection].setForeground(SELECTED);
			repaint();
		}
	}
	
	private class SelectionListener implements KeyListener
	{
		public void keyPressed(KeyEvent event)
		{
			if (event.getKeyCode() == KeyEvent.VK_ENTER || event.getKeyCode() == KeyEvent.VK_SPACE)
			{
				JLabel current = selections[currentSelection];
				
				if (current == game)
				{
					parentFrame.startGame();
				}
				else if (current == scores)
				{
					parentFrame.hiscores();
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
