package stats.ui.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import stats.ui.view.MainView;
import stats.util.MinecraftSaveLoader;

@SuppressWarnings("serial")
public class OpenSaveAction extends AbstractAction {

	public OpenSaveAction(String label) {
		super(label);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		JFileChooser chooser = new JFileChooser();
		
		chooser.setCurrentDirectory(new File(System.getenv("APPDATA") + "/.minecraft/saves"));
		
		int option = chooser.showOpenDialog(MainView.s_mainView);
		
		if (option == JFileChooser.APPROVE_OPTION) {
			try {
				MainView.s_mainView.setLoadedSave(MinecraftSaveLoader.loadSave(chooser.getSelectedFile()));
			} 
			catch (IOException e) {
				System.out.println("Error loading save.");
				e.printStackTrace();
			}
		}
	}

}
