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
	private JLabel[] scores;
	private SnakeFrame parent;
	private Hiscores hiscores;
	
	public HiscoresPanel(SnakeFrame parent, Hiscores hiscores)
	{
		this.parent = parent;
		this.hiscores = hiscores;
		
		JLabel title = new JLabel("High Scores");
		title.setFont(new Font("Arial", Font.BOLD, 64));
		title.setForeground(Color.WHITE);
		add(title);
		
		//TODO: REFACTOR
		scores = new JLabel[hiscores.getScores().length];
		Font scoreFont = new Font("Arial", Font.PLAIN, 24);
		for (int i = 0; i < scores.length; i++)
		{
			if (hiscores.getScores()[i] == 0)
			{
				scores[i] = new JLabel((i+1) + "                            ---");
			}
			else
			{
				scores[i] = new JLabel((i+1) + "                            " + hiscores.getScores()[i]);
			}
			scores[i].setFont(scoreFont);
			scores[i].setForeground(Color.WHITE);
			add(scores[i]);
		}
		
		JLabel esc = new JLabel("Press Escape To Return To Menu");
		esc.setFont(new Font("Arial", Font.ITALIC, 16));
		esc.setForeground(Color.WHITE);
		add(esc);
		
		addKeyListener(new EscListener());
		
		setPreferredSize(new Dimension(400, 480));
		setBackground(Color.BLACK);
		setFocusable(true);
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
