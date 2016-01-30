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
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
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
    }

    public static void main(String args[])
    {
	new Thread(new Main()).start();
    }

    public void readFromFile(File file)
    {
	this.points = new LinkedList<Point>();
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

	    DateFormat formatter = new SimpleDateFormat("mm:ss:SSS");
	    String[] dateFormatted = formatter.format(new Date(System.currentTimeMillis() - startTime)).split(":");
	    write("Wczytano: " + points.size() + " obserwacji z pliku w czasie: " + dateFormatted[0] + " min "
		    + dateFormatted[1] + " s " + dateFormatted[2] + " ms.");
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

    public void generatePoints(int pointsNumber)
    {
	this.points = new LinkedList<Point>();
	long startTime = System.currentTimeMillis();
	mainPanel.getProgressBar().setMaximum(pointsNumber);
	for (int i = 0; i < pointsNumber; i++)
	{
	    points.add(Point.generateRandomPoint(0, 100));
	    mainPanel.getProgressBar().setValue(i + 1);
	    mainPanel.validate();
	    mainPanel.repaint();
	}
	DateFormat formatter = new SimpleDateFormat("mm:ss:SSS");
	String[] dateFormatted = formatter.format(new Date(System.currentTimeMillis() - startTime)).split(":");
	write("Wygenerowano: " + pointsNumber + " obserwacji w czasie: " + dateFormatted[0] + " min "
		+ dateFormatted[1] + " s " + dateFormatted[2] + " ms.");
    }

    public void calculateKmeans()
    {
	if (mainPanel.getRdbtnWygenerujAutomatycznie().isSelected())
	{
	    try
	    {
		generatePoints(Integer.parseInt(mainPanel.getTextField_1().getText()));
	    }
	    catch (NumberFormatException e)
	    {
		write("Podaj ilosc obserwacji do wygenerowania!");
	    }
	}
	if (!points.isEmpty())
	{

	}
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
