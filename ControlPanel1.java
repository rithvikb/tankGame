import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JTable;

public class ControlPanel1 extends JPanel
{
	public ControlPanel1()
	{
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		Object [] columnNames = {"Movement", "Key/Button"};
		
		Object [][] rowData = {{"                       Movement", "                       Key/Button"}, {"                       Move Left", "                   Left Arrow Key"}, {"                       Move Right", "                   Right Arrow Key"}, {"                   Move Barrel Up", "                   Up Arrow Key"}, {"                   Move Barrel Down", "                   Down Arrow Key"}, {"                          Shoot", "                    Space Button"}};
		
		JTable table = new JTable(rowData, columnNames); 
		table.setRowHeight(100);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		add(table, gbc);
	}

}
