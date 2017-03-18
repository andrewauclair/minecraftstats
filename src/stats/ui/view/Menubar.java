package stats.ui.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import stats.ui.view.dialog.PlayerStatsDialog;

public class Menubar extends JMenuBar {

	// File menu with Open, Save, Save As?
	JMenu m_fileMenu = new JMenu("File");
	JMenuItem m_openSave = new JMenuItem("Open Save...");
	
	JMenu m_statsMenu = new JMenu("Stats");
	JMenuItem m_players = new JMenuItem("Players...");
	
	JMenu m_helpMenu = new JMenu("Help");
	
	// menu items to open the dialogs for maps, heatmaps, player data, etc.
	// Help -> About, Manual(?)
	public Menubar() {
		JMenu fileMenu = new JMenu("Stats");
		JMenuItem playerStats =new JMenuItem("Player");
		playerStats.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				PlayerStatsDialog d = new PlayerStatsDialog("", true);
				d.setVisible(true);
			}
		});
		fileMenu.add(playerStats);
		
		this.add(fileMenu);
	}
}
