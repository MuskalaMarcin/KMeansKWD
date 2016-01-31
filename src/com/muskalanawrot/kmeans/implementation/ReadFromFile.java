package com.muskalanawrot.kmeans.implementation;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.SwingWorker;

import com.muskalanawrot.kmeans.gui.MainPanel;

public class ReadFromFile extends SwingWorker<List<Point>, File>
{
    MainPanel mainPanel;
    File file;

    public ReadFromFile(MainPanel mainPanel, File file)
    {
	this.mainPanel = mainPanel;
	this.file = file;
    }

    @Override
    protected List<Point> doInBackground() throws Exception
    {
	long startTime = System.currentTimeMillis();
	List<Point> points = new LinkedList<Point>();
	mainPanel.getBtnWybierzPlik().setEnabled(false);
	mainPanel.getTextArea().append("Wczytuje dane z pliku: " + file.getName() + "\n");
	try
	{
	    FileInputStream fInptStr = new FileInputStream(file);
	    BufferedReader bfrReader = new BufferedReader(new InputStreamReader(new DataInputStream(fInptStr)));
	    String line;
	    while ((line = bfrReader.readLine()) != null)
	    {
		List<Double> dimensions = Arrays.asList(line.split(";")).stream().map(p -> Double.parseDouble(p))
			.collect(Collectors.toList());
		points.add(new Point(Double.valueOf(dimensions.get(0)), Double.valueOf(dimensions.get(1))));
	    }
	    if (!points.isEmpty())
	    {
		mainPanel.getBtnStart().setEnabled(true);
	    }
	    else
	    {
		mainPanel.write("Plik " + file.getName() + " jest pusty.");
	    }
	    bfrReader.close();

	    DateFormat formatter = new SimpleDateFormat("mm:ss:SSS");
	    String[] dateFormatted = formatter.format(new Date(System.currentTimeMillis() - startTime)).split(":");
	    mainPanel.write("Wczytano: " + points.size() + " obserwacji z pliku w czasie: " + dateFormatted[0] + " min "
		    + dateFormatted[1] + " s " + dateFormatted[2] + " ms.");
	    mainPanel.getBtnStart().setEnabled(true);
	}
	catch (FileNotFoundException e)
	{
	    mainPanel.getTextField().setText("");
	    mainPanel.write("Plik " + file.getName() + " nie zostal odnaleziony.");
	}
	catch (IOException e)
	{
	    mainPanel.getTextField().setText("");
	    mainPanel.write("Podczas odczytu pliku wystapil blad.");
	}
	catch (NumberFormatException e)
	{
	    mainPanel.getTextField().setText("");
	    mainPanel.write("Plik nie spe³nia wymaganego formatu.");
	}
	finally
	{
	    mainPanel.getBtnWybierzPlik().setEnabled(true);
	}
	return points;
    }

}
