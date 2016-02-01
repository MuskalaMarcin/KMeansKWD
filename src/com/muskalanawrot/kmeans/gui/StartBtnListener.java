package com.muskalanawrot.kmeans.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.ExecutionException;

import com.muskalanawrot.kmeans.Main;
import com.muskalanawrot.kmeans.implementation.KMeans;

/**
 * Listener for start button.
 */
public class StartBtnListener implements ActionListener
{
    private Main main;
    private MainPanel mainPanel;

    /**
     * Constructor for start button listener.
     * 
     * @param main reference to main class
     * @param mainPanel reference
     */
    public StartBtnListener(Main main, MainPanel mainPanel)
    {
	this.main = main;
	this.mainPanel = mainPanel;
    }

    /**
     * Main method performing kmeans computation in new thread from KMeans class and returning
     * calculated clusters.
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
	try
	{
	    KMeans task = new KMeans(main.getObservations(), Integer.parseInt(mainPanel.getTextField_2().getText()),
		    mainPanel);

	    mainPanel.getBtnPokaWykres().setEnabled(false);
	    mainPanel.getBtnStart().setEnabled(false);
	    mainPanel.getBtnWybierzPlik().setEnabled(false);
	    mainPanel.getBtnWygeneruj().setEnabled(false);
	    mainPanel.getBtnZapiszWynik().setEnabled(false);
	    mainPanel.getRdbtnWczytajZPliku().setEnabled(false);
	    mainPanel.getRdbtnWygenerujAutomatycznie().setEnabled(false);

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
			    if (main.getObservations().get(0).getValues().size() == 2)
			    {
				mainPanel.getBtnPokaWykres().setEnabled(true);
			    }
			    else
			    {
				mainPanel.getBtnPokaWykres().setEnabled(false);
			    }
			    mainPanel.getBtnZapiszWynik().setEnabled(true);
			    mainPanel.getBtnStart().setEnabled(true);
			    mainPanel.getBtnWybierzPlik().setEnabled(true);
			    mainPanel.getBtnWygeneruj().setEnabled(true);
			    mainPanel.getRdbtnWczytajZPliku().setEnabled(true);
			    mainPanel.getRdbtnWygenerujAutomatycznie().setEnabled(true);
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
