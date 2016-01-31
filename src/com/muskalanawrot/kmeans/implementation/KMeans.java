package com.muskalanawrot.kmeans.implementation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import javax.swing.SwingWorker;

import com.muskalanawrot.kmeans.gui.MainPanel;

public class KMeans extends SwingWorker<List<Cluster>, Integer>
{
    private List<Observation> observations;
    private List<Cluster> clusters;
    private Integer clusterNumber;
    private MainPanel mainPanel;

    public KMeans(List<Observation> observations, Integer clusterNumber, MainPanel mainPanel)
    {
	this.observations = observations;
	this.clusterNumber = clusterNumber;
	this.clusters = new LinkedList<Cluster>();
	this.mainPanel = mainPanel;
    }

    @Override
    protected List<Cluster> doInBackground() throws Exception
    {
	long startTime = System.currentTimeMillis();
	int iteration = 0;
	try
	{
	    generateClusters();
	    double firstDistance = 0D;
	    while (true)
	    {
		clearClusters();
		List<Observation> lastCentroids = getCentroids();
		assignAllObservationsToClusters();
		calculateNewCentroids();
		List<Observation> currentCentroids = getCentroids();
		double distance = 0D;
		for (int i = 0; i < lastCentroids.size(); i++)
		{
		    distance += Observation.getDistance(lastCentroids.get(i), currentCentroids.get(i));
		}
		if (firstDistance == 0D)
		{
		    firstDistance = distance;
		}
		setProgress(99 - (int) (Math.round((distance / firstDistance) * 98)));
		iteration++;
		if (distance == 0)
		{
		    return clusters;
		}
	    }
	}
	finally
	{
	    DateFormat formatter = new SimpleDateFormat("mm:ss:SSS");
	    String[] dateFormatted = formatter.format(new Date(System.currentTimeMillis() - startTime)).split(":");
	    mainPanel.write("Dojscie do rozwiazania zajelo : " + iteration + " iteracji i trwalo: " + dateFormatted[0]
		    + " min " + dateFormatted[1] + " s " + dateFormatted[2] + " ms.");
	}

    }

    private void generateClusters()
    {
	Random random = new Random();
	for (int i = 0; i < clusterNumber; i++)
	{
	    Cluster cluster = new Cluster(i);
	    Observation centroid = new Observation(
		    observations.get(random.nextInt(observations.size() - 1)).getValues());
	    cluster.setCentroid(centroid);
	    clusters.add(cluster);
	}
    }

    private void clearClusters()
    {
	clusters.forEach(c -> c.clear());
    }

    private List<Observation> getCentroids()
    {
	List<Observation> allClusters = new LinkedList<Observation>();
	clusters.forEach(c -> {
	    allClusters.add(new Observation(c.getCentroid().getValues()));
	});
	return allClusters;
    }

    private void assignAllObservationsToClusters()
    {
	observations.forEach(o -> {
	    double minDistance = Double.MAX_VALUE;
	    int cluster = -1;
	    for (int i = 0; i < clusterNumber; i++)
	    {
		double distance = Observation.getDistance(o, clusters.get(i).getCentroid());
		if (minDistance > distance)
		{
		    minDistance = distance;
		    cluster = i;
		}
	    }
	    o.setClusterNumber(cluster);
	    clusters.get(cluster).getObservations().add(o);
	});
    }

    private void calculateNewCentroids()
    {
	clusters.forEach(c -> {
	    Integer observationsNumber = c.getObservations().size();

	    List<Double> sum = new LinkedList<Double>();
	    for (int i = 0; i < observations.get(0).getValues().size(); i++)
		sum.add(0D);

	    c.getObservations().forEach(obs -> {
		ListIterator<Double> sumIterator = sum.listIterator();
		obs.getValues().forEach(v -> {
		    Double sumValue = sumIterator.next();
		    sumValue += v;
		    sumIterator.set(sumValue);
		});
	    });

	    Observation centroid = c.getCentroid();
	    List<Double> newValues = new LinkedList<Double>();
	    sum.forEach(s -> {
		newValues.add(s / observationsNumber);
	    });
	    centroid.setValues(newValues);
	});
    }

}
