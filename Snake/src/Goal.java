import java.awt.Color;
import java.util.Random;

/**
 * Represents the goal which the player wants to retrieve.
 *
 */
public class Goal 
{
	private int x;
	private int y;
	private Random rand;
	private final Color color = Color.RED;
	
	public Goal() 
	{
		rand = new Random();
		
		setNewPosition();
	}
	
	public void setNewPosition() 
	{
		x = rand.nextInt(25);
		y = rand.nextInt(25);
	}
	
	public int getX() 
	{
		return x;
	}
	
	public int getY() 
	{
		return y;
	}
	
	public Color getColor()
	{
		return color;
	}
}
