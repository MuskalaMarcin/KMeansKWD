package com.muskalanawrot.kmeans.implementation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.SwingWorker;

import com.muskalanawrot.kmeans.Main;
import com.muskalanawrot.kmeans.gui.MainPanel;

/**
 * Class for saving to file thread.
 *
 */
public class SaveToFile extends SwingWorker<Boolean, Integer>
{

    private MainPanel mainPanel;
    private Main main;
    private File file;

    public SaveToFile(Main main, MainPanel mainPanel, File file)
    {
	this.mainPanel = mainPanel;
	this.main = main;
	this.file = file;
    }

    /**
     * Main thread method saving results to txt file.
     */
    @Override
    protected Boolean doInBackground() throws Exception
    {
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
	    Float observationsSize = Float.valueOf(observations.size());
	    for (int i = 0; i < observations.size(); i++)
	    {
		out.write(i + "\t;\t" + observations.get(i).getClusterNumber());
		out.newLine();
		setProgress(Math.round((i + 1F) / observationsSize * 100F));
	    }
	    out.close();
	    mainPanel.write("Zapisano wyniki do pliku: " + file.getAbsolutePath());
	}
	catch (IOException e1)
	{
	    mainPanel.write("Podczas zapisu do pliku wystapil blad.");
	    return false;
	}
	return true;
    }

}
