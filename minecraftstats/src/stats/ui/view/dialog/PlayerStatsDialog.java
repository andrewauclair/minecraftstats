package stats.ui.view.dialog;

import java.awt.Dimension;
import java.io.File;

import javax.swing.JDialog;

import stats.nbt.utils.PlayerDataLoader;
import stats.util.MojangAPI;

public class PlayerStatsDialog extends JDialog {

	public PlayerStatsDialog(String player, boolean isUUID) {
		
		PlayerDataLoader.LoadPlayerData(new File("1a0857f7-b10c-457b-b136-9643b4f26ab4.dat"));
		
		String username = MojangAPI.getUserName("1a0857f7b10c457bb1369643b4f26ab4");
		
		System.out.println("username: " + username);
		
		setTitle(username);
		
		setMinimumSize(new Dimension(200, 200));
		setPreferredSize(getMinimumSize());
	}
}
