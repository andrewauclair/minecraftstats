package stats.ui.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import stats.ui.actions.OpenSaveAction;
import stats.ui.view.dialog.PlayerStatsDialog;

public class Menubar extends JMenuBar {

	// File menu with Open, Save, Save As?
	JMenu m_fileMenu = new JMenu("File");
	OpenSaveAction m_openSave = new OpenSaveAction("Open Save...");
	JMenuItem m_exit = new JMenuItem("Exit");
	
	JMenu m_statsMenu = new JMenu("Stats");
	JMenuItem m_players = new JMenuItem("Players...");
	
	JMenu m_helpMenu = new JMenu("Help");
	
	// menu items to open the dialogs for maps, heatmaps, player data, etc.
	// Help -> About, Manual(?)
	public Menubar() {
		
		m_fileMenu.add(m_openSave);
		m_fileMenu.add(m_exit);
		
		add(m_fileMenu);
		add(m_statsMenu);
		add(m_helpMenu);
	}
}
