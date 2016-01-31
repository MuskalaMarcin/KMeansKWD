package com.muskalanawrot.kmeans;

import java.util.List;

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

    public void calculateKmeans()
    {
	System.out.println("Ilosc obserwacji " + points.size());

	points.forEach(p -> System.out.println(p.getX() + " " + p.getY()));

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

    public List<Point> getPoints()
    {
	return points;
    }

    public void setPoints(List<Point> points)
    {
	this.points = points;
    }

}
