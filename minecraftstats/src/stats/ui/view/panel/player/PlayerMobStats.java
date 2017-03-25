package stats.ui.view.panel.player;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PlayerMobStats extends JPanel {

	private DefaultTableModel m_model = new DefaultTableModel(new String[] { "Mob", "Kills", "Deaths" }, 0);
	private JTable m_table = new JTable(m_model);
	
	public PlayerMobStats() {
		
		// add all of the stats to the model
		
		add(m_table);
	}
}
