import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class LabelFactory
{
	/**
	 * Creates a new JLabel
	 * 
	 * @param text the text for the label to display
	 * @param font the font to make the label
	 * @param color the color to make the label
	 * @return a new JLabel with the specified text, font, and color
	 */
	public static JLabel createLabel(String text, Font font, Color color)
	{
		JLabel label = new JLabel(text);
		label.setFont(font);
		label.setForeground(color);
		return label;
	}
}
