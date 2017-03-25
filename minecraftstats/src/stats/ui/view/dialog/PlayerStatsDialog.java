package stats.ui.view.dialog;

import java.awt.Dimension;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JTabbedPane;

import stats.nbt.model.PlayerDataModel;
import stats.nbt.utils.PlayerDataLoader;
import stats.ui.view.panel.player.PlayerGeneralStats;
import stats.util.MojangAPI;

public class PlayerStatsDialog extends JDialog {

	private PlayerDataModel m_playerData = null;
	
	private JTabbedPane m_tabbedPane = new JTabbedPane();
	
	private PlayerGeneralStats m_generalStats = new PlayerGeneralStats();
	
	public PlayerStatsDialog(PlayerDataModel playerData) {
	
		m_playerData = playerData;
		
		setTitle(playerData.getUserName());
		
		setMinimumSize(new Dimension(200, 200));
		setPreferredSize(getMinimumSize());
		
		add(m_tabbedPane);
		
		m_tabbedPane.add("General", m_generalStats);
	}
}
