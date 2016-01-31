package com.muskalanawrot.kmeans.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.ExecutionException;

import com.muskalanawrot.kmeans.Main;
import com.muskalanawrot.kmeans.implementation.KMeans;

public class StartBtnListener implements ActionListener
{
    private Main main;
    private MainPanel mainPanel;

    public StartBtnListener(Main main, MainPanel mainPanel)
    {
	this.main = main;
	this.mainPanel = mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
	try
	{
	    KMeans task = new KMeans(main.getObservations(), Integer.parseInt(mainPanel.getTextField_2().getText()));
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
			    main.setClusters(task.get());
			}
			catch (ExecutionException | InterruptedException e)
			{
			    mainPanel.write("Blad podczas pracy algorytmu KMeans, sprobuj ponownie!");
			}
		    }
		}
	    });
	    task.execute();
	}
	catch (NumberFormatException e1)
	{
	    mainPanel.write("Podaj poprawna ilosc klastrow!");
	}
    }
}
