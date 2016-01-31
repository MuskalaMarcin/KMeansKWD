package com.muskalanawrot.kmeans.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.JFileChooser;

import com.muskalanawrot.kmeans.Main;
import com.muskalanawrot.kmeans.implementation.Observation;

public class SaveToFileListener implements ActionListener
{
    private MainPanel mainPanel;
    private Main main;
    private JFileChooser jFileChooser;

    public SaveToFileListener(Main main, MainPanel mainPanel)
    {
	this.mainPanel = mainPanel;
	this.main = main;
	jFileChooser = new JFileChooser();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
	if (jFileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
	{
	    File file = jFileChooser.getSelectedFile();
	    if (!file.getName().endsWith(".txt"))
	    {
		file = new File(file.toString() + ".txt");
	    }
	    try
	    {
		BufferedWriter out = new BufferedWriter(new FileWriter(file, false));
		out.write("ObsNr\t;\tClusterNr");
		out.newLine();
		List<Observation> observations = main.getObservations();
		for (int i = 0; i < observations.size(); i++)
		{
		    out.write(i + "\t;\t" + observations.get(i).getClusterNumber());
		    out.newLine();
		}
		out.close();
		mainPanel.write("Zapisano wyniki do pliku: " + file.getAbsolutePath());
	    }
	    catch (IOException e1)
	    {
		mainPanel.write("Podczas zapisu do pliku wystapil blad.");
	    }
	}
    }

}
