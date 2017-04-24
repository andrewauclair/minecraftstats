package stats.ui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import stats.nbt.model.LevelModel;
import stats.nbt.model.PlayerDataModel;
import stats.nbt.model.tags.TAG;
import stats.nbt.model.tags.TAG_Compound;
import stats.ui.model.MinecraftSaveData;
import stats.ui.view.dialog.PlayerStatsDialog;
import stats.util.MojangAPI;

// Everything on this dialog and its actions are temporary
// This will be used to setup the dialogs and figure things out
public class MainView extends JFrame {

	public static MainView s_mainView = null;

	private static final String s_title = "Minecraft Stats";
	
	private MinecraftSaveData m_currentSave = null;
	
	DefaultListModel<String> model = new DefaultListModel<>();
	
	JList<String> m_players = new JList<>(model);
	
	JTextField m_seed = new JTextField();
	JTextField m_spawnX = new JTextField();
	JTextField m_spawnY = new JTextField();
	JTextField m_spawnZ = new JTextField();
	
	public MainView() {
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		s_mainView = this;
		
		setTitle(s_title);
		setMinimumSize(new Dimension(500, 500));
		setPreferredSize(getMinimumSize());
		
		m_players.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {

		            // Double-click detected
		            int index = list.locationToIndex(evt.getPoint());
		            
		            PlayerStatsDialog statsDialog = new PlayerStatsDialog(m_currentSave.getPlayers().get(index));
		            statsDialog.setVisible(true);
		        }
		    }
		});
		
		add(m_players, gbc);
		add(new JLabel("Seed:"), gbc);
		add(m_seed, gbc);
		gbc.gridy++;
		add(new JLabel("Spawn X:"), gbc);
		add(m_spawnX, gbc);
		add(new JLabel("Y:"), gbc);
		add(m_spawnY, gbc);
		add(new JLabel("Z:"), gbc);
		add(m_spawnZ, gbc);
		Menubar bar = new Menubar();
		
		setJMenuBar(bar);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public void exitApplication() {
		dispose();
	}
	
	public void setLoadedSave(MinecraftSaveData saveData) {
		
		m_currentSave = saveData;
		
		for (PlayerDataModel player : saveData.getPlayers()) {
			model.addElement(MojangAPI.getUserName(player.getUUID()));
		}
		
		TAG root = saveData.getLevelDatNBT().getRoot();
		
		LevelModel level = new LevelModel();
		level.readFromCompound((TAG_Compound)root);
		
		m_seed.setText(Long.toString(level.getRandomSeed()));
		m_spawnX.setText(Integer.toString(level.getSpawnX()));
		m_spawnY.setText(Integer.toString(level.getSpawnY()));
		m_spawnZ.setText(Integer.toString(level.getSpawnZ()));
		
		setTitle(s_title + " - " + level.getLevelName() + " - " + level.getVersionCompound().getName());
	}
	
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			System.out.println("Unable to set look and feel");
			e.printStackTrace();
		}
		
		MainView view = new MainView();
		
		view.setVisible(true);
	}
}
