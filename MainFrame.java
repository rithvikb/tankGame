import java.awt.CardLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainFrame extends JFrame
{
	public JFrame frame = this;
	
	public MainFrame()
	{
		CardLayout cl = new CardLayout();
		JPanel overall= new JPanel();
		overall.setLayout(cl);
		
		overall.add(new WelcomePanel(), "WelcomePanel");
		overall.add(new ControlsPanel(), "ControlsPanel");
		overall.add(new ControlPanel1(), "ControlPanel1");
		
		cl.show(overall, "WelcomePanel");
		
		JMenuBar menu = new JMenuBar();
		
		JMenu homeMenu = new JMenu("Home");
		
		JMenuItem openMenuItem = new JMenuItem("Home Screen");
		openMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				cl.show(overall, "WelcomePanel");
			}
			
		});
		homeMenu.add(openMenuItem);
		menu.add(homeMenu);
		
		JMenu helpMenu = new JMenu("Controls");
		
		JMenuItem openMenuItem1 = new JMenuItem("Player 1");
		
		openMenuItem1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				cl.show(overall, "ControlsPanel");
			}
			
		});
		
		JMenuItem openMenuItem2 = new JMenuItem("Player 2");
		
		openMenuItem2.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				cl.show(overall, "ControlPanel1"); 
			}
			
		});
		
		helpMenu.add(openMenuItem1);
		helpMenu.add(openMenuItem2);
		menu.add(helpMenu);
		
		add(overall);
		
		frame.setJMenuBar(menu);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) 
	{
		new MainFrame();
	}

}
