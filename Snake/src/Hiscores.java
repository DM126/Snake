import java.io.*;
import java.util.*;

//TODO: add a date for each score
public class Hiscores
{
	private int[] scores;
	private File scoresFile;
	
	public Hiscores(String fileName) throws FileNotFoundException
	{
		scores = new int[10];
		scoresFile = new File(fileName);
		readScores();
	}
	
	/**
	 * @return the array of high scores
	 */
	public int[] getScores()
	{
		return scores;
	}
	
	/**
	 * @return the highest score on the leaderboard
	 */
	public int getTopScore()
	{
		return scores[0];
	}
	
	/**
	 * Gets previous scores from a text file. Scans each line of the file for score.
	 * 
	 * @throws FileNotFoundException if the text file of scores is not found
	 */
	private void readScores() throws FileNotFoundException
	{
		Scanner infile = new Scanner(scoresFile);
		
		try
		{
			int i = 0;
			while (infile.hasNext())
			{
				scores[i] = Integer.parseInt(infile.nextLine());
				i++;
			}
			
			//TODO: sort the list
		}
		catch (NoSuchElementException ex)
		{
			ex.printStackTrace();
		}
		
		infile.close();
	}
	
	/**
	 * Adds a score to the high scores if it is greater than any on the list.
	 * 
	 * @param score the new score to add
	 */
	public void addScore(int score)
	{
		int temp;
		for (int i = scores.length - 1; (i >= 0) && (score > scores[i]); i--)
		{
			temp = scores[i];
			scores[i] = score;
			if (i < scores.length - 1)
			{
				scores[i + 1] = temp;
			}
		}
		
		saveScores();
	}
	
	/**
	 * Save the text file containing the scores.
	 */
	public void saveScores()
	{
		try
		{
			PrintWriter printer = new PrintWriter(scoresFile);
			
			for (int s : scores)
			{
				printer.println(s);
			}
			
			printer.close();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();	
		}
	}
	
	/**
	 * Prints all the scores
	 */
	public String toString()
	{
		StringBuilder results = new StringBuilder();
		
		for (int s : scores)
		{
			results.append(s + "\n");
		}
		
		return results.toString();
	}
}
