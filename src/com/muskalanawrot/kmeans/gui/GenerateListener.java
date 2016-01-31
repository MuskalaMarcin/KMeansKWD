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
	Integer observationsNumber = 0;
	Integer vectorLength = 0;
	Integer min = 0;
	Integer max = 0;
	try
	{
	    try
	    {
		observationsNumber = Integer.parseInt(mainPanel.getTextField_1().getText());
	    }
	    catch (NumberFormatException e2)
	    {
		mainPanel.write("Podaj ilosc obserwacji do wygenerowania!");
		throw e2;
	    }
	    try
	    {
		vectorLength = Integer.parseInt(mainPanel.getTextField_5().getText());
	    }
	    catch (NumberFormatException e2)
	    {
		mainPanel.write("Podaj dlugosc wektorow!");
		throw e2;
	    }
	    try
	    {
		min = Integer.parseInt(mainPanel.getTextField_3().getText());
	    }
	    catch (NumberFormatException e2)
	    {
		mainPanel.write("Podaj minimalna wartosc przedzialu!");
		throw e2;
	    }
	    try
	    {
		max = Integer.parseInt(mainPanel.getTextField_4().getText());
	    }
	    catch (NumberFormatException e2)
	    {
		mainPanel.write("Podaj maksymalna wartosc przedzialu!");
		throw e2;
	    }

	    GenerateObservations task = new GenerateObservations(mainPanel, observationsNumber, vectorLength, min, max);
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
	    mainPanel.write("Wypelnij wszystkie wymagane pola!");
	}
    }
}
