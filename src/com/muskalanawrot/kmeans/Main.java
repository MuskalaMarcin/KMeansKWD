package com.muskalanawrot.kmeans;

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
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.swing.JFrame;

import com.muskalanawrot.kmeans.gui.MainPanel;
import com.muskalanawrot.kmeans.implementation.Point;

public class Main implements Runnable
{
    private MainPanel mainPanel;
    private JFrame mainFrame;
    private List<Point> points;

    public Main()
    {
	this.mainPanel = new MainPanel(this);
	this.mainFrame = new JFrame();
	this.points = new LinkedList<Point>();
    }

    public static void main(String args[])
    {
	new Thread(new Main()).start();
    }

    public void readFromFile(File file)
    {
	long startTime = System.currentTimeMillis();
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
		write("Plik " + file.getName() + " jest pusty.");
	    }
	    bfrReader.close();
	    Date date = new Date(System.currentTimeMillis() - startTime);
	    DateFormat formatter = new SimpleDateFormat("mm:ss:SSS");
	    String dateFormatted = formatter.format(date);
	    write(dateFormatted);
	}
	catch (FileNotFoundException e)
	{
	    write("Plik " + file.getName() + " nie zostal odnaleziony.");
	    e.printStackTrace();
	}
	catch (IOException e)
	{
	    write("Podczas odczytu pliku wystapil blad.");
	    e.printStackTrace();
	}
    }

    public void generatePoints()
    {
	long startTime = System.currentTimeMillis();
    }

    public void calculateKmeans()
    {

    }

    @Override
    public void run()
    {
	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	mainFrame.setBounds(100, 100, 505, 430);
	mainFrame.setTitle("Algorytm centroidów - Muska³a, Nawrot");
	mainFrame.setContentPane(mainPanel);
	mainFrame.setResizable(false);
	mainFrame.setVisible(true);
    }

    public void write(String text)
    {
	mainPanel.getTextArea().append(text + "\n");
    }

}
