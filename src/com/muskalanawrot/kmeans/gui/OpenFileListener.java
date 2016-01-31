package com.muskalanawrot.kmeans.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.concurrent.ExecutionException;

import javax.swing.JFileChooser;

import com.muskalanawrot.kmeans.Main;
import com.muskalanawrot.kmeans.implementation.ReadFromFile;

public class OpenFileListener implements ActionListener
{
    private MainPanel mainPanel;
    private Main main;
    private JFileChooser jFileChooser;

    public OpenFileListener(Main main, MainPanel mainPanel)
    {
	this.mainPanel = mainPanel;
	this.main = main;
	jFileChooser = new JFileChooser();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
	if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
	{
	    File file = jFileChooser.getSelectedFile();
	    mainPanel.getTextField().setText(file.getName());
	    ReadFromFile task = new ReadFromFile(mainPanel, file);
	    task.addPropertyChangeListener(new PropertyChangeListener()
	    {
		@Override
		public void propertyChange(PropertyChangeEvent evt)
		{
		    mainPanel.getProgressBar().setValue(task.getProgress());
		    if (task.isDone())
		    {
			mainPanel.getProgressBar().setValue(100);
			try
			{
			    main.setPoints(task.get());
			}
			catch (ExecutionException | InterruptedException e)
			{
			    e.printStackTrace();
			    mainPanel.write("Blad podczas wczytywania punktow, sprobuj ponownie!");
			}
		    }
		}
	    });
	    task.execute();
	}
    }

}
