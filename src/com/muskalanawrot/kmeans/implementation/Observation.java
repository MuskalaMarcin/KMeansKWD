package com.muskalanawrot.kmeans.implementation;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Class for one of observation.
 *
 */
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

    /**
     * Generates new random observation with following prerequisites:
     * @param min min value 
     * @param max max value
     * @param valuesNumber number of values (vector length)
     * @return new random Observation.
     */
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

    /**
     * Static method calcualting euclidean distance between two observations.
     * @param obs1 observation1
     * @param obs2 observation2
     * @return double distance
     */
    public static double getDistance(Observation obs1, Observation obs2)
    {
	Double sum = 0D;
	List<Double> values1 = obs1.getValues();
	List<Double> values2 = obs2.getValues();
	for (int i = 0; i < values1.size(); i++)
	{
	    sum += Math.pow((values1.get(i) - values2.get(i)), 2);
	}
	Double sqrt = Math.sqrt(sum);
	return sqrt.isNaN() ? 0 : sqrt;
    }
}
