package com.muskalanawrot.kmeans.implementation;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Observation
{
    private List<Double> values;
    private int clusterNumber;

    public Observation(List<Double> values)
    {
	this.values = values;
	this.clusterNumber = -1;
    }

    public List<Double> getValues()
    {
	return values;
    }

    public void setValues(List<Double> values)
    {
	this.values = values;
    }

    public int getClusterNumber()
    {
	return clusterNumber;
    }

    public void setClusterNumber(int clusterNumber)
    {
	this.clusterNumber = clusterNumber;
    }

    public static Observation generateRandomObservation(int min, int max, int valuesNumber)
    {
	Random random = new Random();
	List<Double> values = new LinkedList<Double>();
	for (int i = 0; i < valuesNumber; i++)
	{
	    values.add(min + (max - min) * random.nextDouble());
	}
	return new Observation(values);
    }

    public static double getDistance(Observation obs1, Observation obs2)
    {
	Double sum = 0D;
	List<Double> values1 = obs1.getValues();
	List<Double> values2 = obs2.getValues();
	for (int i = 0; i < values1.size(); i++)
	{
	    sum += Math.pow((values1.get(i) - values2.get(i)), 2);
	}
	return Math.sqrt(sum);
    }
}
