import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ControlsPanel extends JPanel
{
	public ControlsPanel()
	{
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		 
		Object [] columnNames = {"Movement", "Key/Button"};
		
		Object [][] rowData = {{"                       Movement", "                       Key/Button"}, {"                       Move Left", "                              A"}, {"                       Move Right", "                              D"}, {"                   Move Barrel Up", "                              W"}, {"                Move Barrel Down", "                              S"}, {"                          Shoot", "                              E"}};
		
		JTable table = new JTable(rowData, columnNames); 
		table.setRowHeight(100);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		add(table, gbc);
	}
}
