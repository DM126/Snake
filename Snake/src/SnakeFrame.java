import java.awt.event.*;
import java.io.FileNotFoundException;
import javax.swing.*;

public class SnakeFrame extends JFrame
{
	private MainMenu menu;
	private Hiscores hiscores;
	
	public SnakeFrame()
	{
		super("Snake");

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		WindowCloser closer = new WindowCloser();
		addWindowListener(closer);
		
		setLocationRelativeTo(null);
		setResizable(false);
		
		try
		{
			hiscores = new Hiscores("Snake/resources/Hiscores.txt");
		}
		catch (FileNotFoundException ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Hiscores.txt could not be found.\nPlease create a new text file for the high scores.", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		
		menu = new MainMenu(this);
		getContentPane().add(menu);
		pack();
		setVisible(true);
	}
	
	/**
	 * Closes the program.
	 */
	public void close()
	{	
		hiscores.saveScores();
		System.exit(0);
	}
	
	private class WindowCloser implements WindowListener
	{
		public void windowClosing(WindowEvent event) 
		{
			close();
		}
		
		//Unused methods of WindowListener
		public void windowActivated(WindowEvent event) {}
		public void windowClosed(WindowEvent event) {}
		public void windowDeactivated(WindowEvent event) {}
		public void windowDeiconified(WindowEvent event) {}
		public void windowIconified(WindowEvent event) {}
		public void windowOpened(WindowEvent event) {}
	}
	
	/**
	 * Changes the current JPanel displayed by the frame.
	 * 
	 * @param newPanel the panel to be displayed
	 */
	private void changePanels(JPanel newPanel)
	{
		getContentPane().removeAll();
		getContentPane().add(newPanel);
		pack();
		setVisible(true);
		newPanel.requestFocusInWindow();
	}
	
	/**
	 * Displays the main menu.
	 */
	public void returnToMenu()
	{
		changePanels(menu);
	}
	
	public void startGame()
	{
		changePanels(new SnakePanel(this, hiscores));
	}
	
	public void hiscores()
	{
		changePanels(new HiscoresPanel(this, hiscores));
	}
}