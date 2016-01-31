package com.muskalanawrot.kmeans.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.ExecutionException;

import com.muskalanawrot.kmeans.Main;
import com.muskalanawrot.kmeans.implementation.GenerateObservations;

public class GenerateListener implements ActionListener
{
    Main main;
    MainPanel mainPanel;

    public GenerateListener(Main main, MainPanel mainPanel)
    {
	this.main = main;
	this.mainPanel = mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
	try
	{
	    GenerateObservations task = new GenerateObservations(mainPanel,
		    Integer.parseInt(mainPanel.getTextField_1().getText()));
	    mainPanel.getProgressBar().setValue(0);
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
			    main.setObservations(task.get());
			}
			catch (ExecutionException | InterruptedException e)
			{
			    mainPanel.write("Blad podczas generowania punktow, sprobuj ponownie!");
			}
		    }
		}
	    });
	    task.execute();
	}
	catch (NumberFormatException e2)
	{
	    mainPanel.write("Podaj ilosc obserwacji do wygenerowania!");
	}
    }
}
