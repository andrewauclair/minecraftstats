package stats.ui.view;

import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import stats.util.MojangAPI;

public class MainView extends JFrame {

	//ArrayList<String> m_players = new ArrayList<>();
	DefaultListModel<String> model = new DefaultListModel<>();
	
	JList<String> m_players = new JList<>(model);
	
	public MainView() {
		
		setTitle("Minecraft Stats");
		setMinimumSize(new Dimension(200, 200));
		setPreferredSize(getMinimumSize());
		
		add(m_players);
		
		Menubar bar = new Menubar();
		
		setJMenuBar(bar);
		
		File folder = new File("C:\\Users\\might\\AppData\\Roaming\\.minecraft\\saves\\Droidcraft (World 2)-5\\playerdata");
		File[] files = folder.listFiles();
		
		for (File file : files) {
			if (file.isFile()) {
				String uuid = file.getName().substring(0, file.getName().indexOf('.'));
				uuid = uuid.replace("-", "");
				model.addElement(MojangAPI.getUserName(uuid));
				//m_players.add(file.getName().substring(0, file.getName().indexOf(".") - 1));
			}
		}
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
