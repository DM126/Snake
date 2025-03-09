import java.awt.Color;
import java.util.LinkedList;

public class Snake
{
	private LinkedList<Segment> segments;
	private Head head;
	private static final Color COLOR = Color.GREEN;
	
	public Snake(int x, int y)
	{
		segments = new LinkedList<>();
		head = new Head(x, y);
		segments.add(head);
	}
	
	public LinkedList<Segment> getSnake()
	{
		return segments;
	}
	
	/**
	 * Checks if the head hits any body segments.
	 * 
	 * @return true if the head's x and y coordinates match any segment's
	 */
	public boolean hitsBody()
	{
		Segment s;
		
		for (int i = 1; i < segments.size(); i++)
		{
			s = segments.get(i);
			if (head.getX() == s.getX() && head.getY() == s.getY())
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Returns a segment at the position specified in the argument (head = 0).
	 * 
	 * @param i the index of the segment
	 * @return a segment of the snake
	 */
	public Segment get(int i)
	{
		return segments.get(i);
	}
	
	/**
	 * @return the number of segments on this snake.
	 */
	public int getLength()
	{
		return segments.size();
	}
	
	public void addSegment()
	{
		segments.add(new Body(segments.getLast()));
	}
	
	/**
	 * @return the last segment on the snake
	 */
	public Segment getTail()
	{
		return segments.get(segments.size() - 1);
	}
	
	public int getHeadX()
	{
		return head.getX();
	}
	
	public int getHeadY()
	{
		return head.getY();
	}
	
	public void move()
	{
		head.move();
	}
	
	public Color getColor()
	{
		return COLOR;
	}
	
	public Head getHead()
	{
		return head;
	}
}