package com.muskalanawrot.kmeans;

import java.util.List;

import javax.swing.JFrame;

import com.muskalanawrot.kmeans.gui.MainPanel;
import com.muskalanawrot.kmeans.implementation.Cluster;
import com.muskalanawrot.kmeans.implementation.Observation;

public class Main implements Runnable
{
    private MainPanel mainPanel;
    private JFrame mainFrame;
    private List<Observation> observations;
    private List<Cluster> clusters;

    public Main()
    {
	this.mainPanel = new MainPanel(this);
	this.mainFrame = new JFrame();
    }

    public static void main(String args[])
    {
	new Thread(new Main()).start();
    }

    @Override
    public void run()
    {
	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	mainFrame.setBounds(100, 100, 505, 525);
	mainFrame.setTitle("Algorytm centroidów - Muska³a, Nawrot");
	mainFrame.setContentPane(mainPanel);
	mainFrame.setResizable(false);
	mainFrame.setVisible(true);
    }

    public List<Observation> getObservations()
    {
	return observations;
    }

    public void setObservations(List<Observation> observations)
    {
	this.observations = observations;
    }

    public List<Cluster> getClusters()
    {
	return clusters;
    }

    public void setClusters(List<Cluster> clusters)
    {
	this.clusters = clusters;
    }
}
