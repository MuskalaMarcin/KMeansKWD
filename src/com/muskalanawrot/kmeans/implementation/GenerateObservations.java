package com.muskalanawrot.kmeans.implementation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.SwingWorker;

import com.muskalanawrot.kmeans.gui.MainPanel;

/**
 * Class generating random list of observations.
 *
 */
public class GenerateObservations extends SwingWorker<List<Observation>, Integer>
{
    MainPanel mainPanel;
    Integer observationsNumber;
    Integer vectorLength;
    Integer min;
    Integer max;

    /**
     * Constructor for GenerateObservations class.
     * @param panel mainPanel reference
     * @param observationsNumber number of observations to generate
     * @param vectorLength length of vector
     * @param min value
     * @param max value
     */
    public GenerateObservations(MainPanel panel, Integer observationsNumber, Integer vectorLength, Integer min, Integer max)
    {
	this.mainPanel = panel;
	this.observationsNumber = observationsNumber;
	this.vectorLength=vectorLength;
	this.min=min;
	this.max=max;
    }

    /**
     * Main thread method generating given number of observations.
     */
    @Override
    protected List<Observation> doInBackground() throws Exception
    {
	long startTime = System.currentTimeMillis();
	mainPanel.getBtnWygeneruj().setEnabled(false);
	try
	{
	    List<Observation> observations = new LinkedList<Observation>();

	    for (int i = 0; i < observationsNumber; i++)
	    {
		observations.add(Observation.generateRandomObservation(min, max, vectorLength));
		setProgress(Math.round((float) i / observationsNumber * 100F));
	    }

	    return observations;
	}
	finally
	{
	    DateFormat formatter = new SimpleDateFormat("mm:ss:SSS");
	    String[] dateFormatted = formatter.format(new Date(System.currentTimeMillis() - startTime)).split(":");
	    mainPanel.write("Wygenerowano: " + observationsNumber + " obserwacji w czasie: " + dateFormatted[0] +
		    " min " + dateFormatted[1] + " s " + dateFormatted[2] + " ms.");
	    mainPanel.getBtnWygeneruj().setEnabled(true);
	    mainPanel.getBtnStart().setEnabled(true);
	    mainPanel.getBtnPokaWykres().setEnabled(false);
	    mainPanel.getBtnZapiszWynik().setEnabled(false);
	}
    }

}
