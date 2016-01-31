package com.muskalanawrot.kmeans.implementation;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.SwingWorker;

import com.muskalanawrot.kmeans.gui.MainPanel;

public class ReadFromFile extends SwingWorker<List<Observation>, Integer>
{
    MainPanel mainPanel;
    File file;

    public ReadFromFile(MainPanel mainPanel, File file)
    {
	this.mainPanel = mainPanel;
	this.file = file;
    }

    @Override
    protected List<Observation> doInBackground() throws Exception
    {
	long startTime = System.currentTimeMillis();
	List<Observation> observations = new LinkedList<Observation>();
	mainPanel.getBtnWybierzPlik().setEnabled(false);
	mainPanel.getTextArea().append("Wczytuje dane z pliku: " + file.getName() + "\n");
	try
	{
	    setProgress(0);
	    LineNumberReader lnr = new LineNumberReader(new FileReader(file));
	    lnr.skip(Long.MAX_VALUE);
	    int numberOfLines = lnr.getLineNumber();
	    lnr.close();

	    FileInputStream fInptStr = new FileInputStream(file);
	    BufferedReader bfrReader = new BufferedReader(new InputStreamReader(new DataInputStream(fInptStr)));
	    String line;
	    int lineNumber = 0;
	    while ((line = bfrReader.readLine()) != null)
	    {
		List<Double> dimensions = Arrays.asList(line.split(";")).stream().map(p -> Double.parseDouble(p))
			.collect(Collectors.toList());
		observations.add(new Observation(dimensions));
		setProgress(Math.round((float) lineNumber / numberOfLines * 100F));
		lineNumber++;
	    }
	    if (!observations.isEmpty())
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
	    mainPanel.write(
		    "Wczytano: " + observations.size() + " obserwacji z pliku w czasie: " + dateFormatted[0] + " min "
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
	    e.printStackTrace();
	    mainPanel.getTextField().setText("");
	    mainPanel.write("Plik nie spe³nia wymaganego formatu.");
	}
	finally
	{
	    mainPanel.getBtnWybierzPlik().setEnabled(true);
	}
	return observations;
    }

}
