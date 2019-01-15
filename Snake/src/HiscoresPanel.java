import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

//TODO
//Center the labels
//Allow deleting all scores
//Display date of score

/**
 * JPanel in which the high scores are displayed.
 */
public class HiscoresPanel extends JPanel
{
	private SnakeFrame parent;
	private Hiscores hiscores;
	
	public HiscoresPanel(SnakeFrame parent, Hiscores hiscores)
	{
		this.parent = parent;
		this.hiscores = hiscores;
		
		
		JLabel title = LabelFactory.createLabel("High Scores", new Font("Arial", Font.BOLD, 64), Color.WHITE);
		add(title);
		
		writeScores();
		
		JLabel esc = LabelFactory.createLabel("Press Escape To Return To Menu", new Font("Arial", Font.ITALIC, 16), Color.WHITE);
		add(esc);
		
		addKeyListener(new EscListener());
		
		setPreferredSize(new Dimension(400, 480));
		setBackground(Color.BLACK);
		setFocusable(true);
	}
	
	/**
	 * Displays the scores on screen.
	 */
	private void writeScores()
	{
		JLabel[] scores = new JLabel[hiscores.getScores().length];
		Font scoreFont = new Font("Arial", Font.PLAIN, 24);
		for (int i = 0; i < scores.length; i++)
		{
			int score = hiscores.getScores()[i];
			String text = (i+1) + "                            ";
			if (score != 0)
			{
				text += score;
			}
			else
			{
				text += "---";
			}
			scores[i] = LabelFactory.createLabel(text, scoreFont, Color.WHITE);
			add(scores[i]);
		}
	}
	
	private class EscListener implements KeyListener
	{
		public void keyPressed(KeyEvent event)
		{
			if (event.getKeyCode() == KeyEvent.VK_ESCAPE)
			{
				parent.returnToMenu();
			}
		}

		public void keyReleased(KeyEvent event) {}
		public void keyTyped(KeyEvent event) {}
		
	}
}
