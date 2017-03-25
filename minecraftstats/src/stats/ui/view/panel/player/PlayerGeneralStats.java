package stats.ui.view.panel.player;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

// Add menu item for displaying the unit, cm/dm/m/km, seconds/minutes/hours/days
public class PlayerGeneralStats extends JPanel {

	private DefaultTableModel m_model = new DefaultTableModel(new String[] { "Stat", "Value" }, 0);
	private JTable m_table = new JTable(m_model);
	
	public PlayerGeneralStats() {
		
		// add all of the stats to the model
	}
}
