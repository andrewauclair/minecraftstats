package stats.ui.actions;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import stats.ui.view.MainView;
import stats.util.MinecraftSaveLoader;

public class OpenSaveAction extends AbstractAction {

	public OpenSaveAction(String label) {
		super(label);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		JFileChooser chooser = new JFileChooser();
		
		chooser.showOpenDialog(MainView.s_mainView);
		try {
			MinecraftSaveLoader.loadSave(chooser.getSelectedFile());
		} catch (IOException e) {
			System.out.println("Error loading save.");
			e.printStackTrace();
		}
	}

}
