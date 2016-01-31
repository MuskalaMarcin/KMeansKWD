package com.muskalanawrot.kmeans.implementation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.SwingWorker;

import com.muskalanawrot.kmeans.gui.MainPanel;

public class GenerateObservations extends SwingWorker<List<Point>, Integer>
{
    MainPanel mainPanel;
    Integer pointsNumber;

    public GenerateObservations(MainPanel panel, Integer observationsNumber)
    {
	this.mainPanel = panel;
	this.pointsNumber = observationsNumber;
    }

    @Override
    protected List<Point> doInBackground() throws Exception
    {
	long startTime = System.currentTimeMillis();
	mainPanel.getBtnWygeneruj().setEnabled(false);
	try
	{
	    List<Point> points = new LinkedList<Point>();

	    for (int i = 0; i < pointsNumber; i++)
	    {
		points.add(Point.generateRandomPoint(0, 100));
		setProgress(Math.round((float) i / pointsNumber * 100F));
	    }

	    return points;
	}
	finally
	{
	    DateFormat formatter = new SimpleDateFormat("mm:ss:SSS");
	    String[] dateFormatted = formatter.format(new Date(System.currentTimeMillis() - startTime)).split(":");
	    mainPanel.write("Wygenerowano: " + pointsNumber + " obserwacji w czasie: " + dateFormatted[0] +
			    " min " + dateFormatted[1] + " s " + dateFormatted[2] + " ms.");
	    mainPanel.getBtnWygeneruj().setEnabled(true);
	    mainPanel.getBtnStart().setEnabled(true);
	}
    }

}
