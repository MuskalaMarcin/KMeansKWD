package com.muskalanawrot.kmeans.implementation;

import java.util.LinkedList;
import java.util.List;

import javax.swing.SwingWorker;

public class KMeans extends SwingWorker<List<Cluster>, Integer>
{
    private List<Observation> observations;
    private List<Cluster> clusters;
    private Integer clusterNumber;

    public KMeans(List<Observation> points, Integer clusterNumber)
    {
	this.observations = points;
	this.clusterNumber = clusterNumber;
	this.clusters = new LinkedList<Cluster>();
    }

    @Override
    protected List<Cluster> doInBackground() throws Exception
    {
	System.out.println(clusterNumber);
	System.out.println("Ilosc obserwacji " + observations.size());

	observations.forEach(p -> System.out.println(p.getValues().get(0) + " " + p.getValues().get(1)));
	return clusters;
    }

}
