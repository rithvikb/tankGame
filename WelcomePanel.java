import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class WelcomePanel extends JPanel
{
	public WelcomePanel()
	{
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		JButton newGame = new JButton("New Game");
		newGame.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
		newGame.setPreferredSize(new Dimension(200, 50));
		newGame.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				String player1 = JOptionPane.showInputDialog(null, "Enter Player 1 Username: ");
				String player2 = JOptionPane.showInputDialog(null, "Enter Player 2 Username: ");
			}
			
	
		});
		add(newGame);
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Image bck = null;
		try 
		{
			bck = ImageIO.read(new File("Untitled.png"));
		} 
		
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		g.drawImage(bck,0,0,null);
	}
	
	
	
}
