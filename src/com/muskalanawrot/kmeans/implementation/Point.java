package com.muskalanawrot.kmeans.implementation;

import java.util.Random;

public class Point
{
    private double x;
    private double y;
    private int clusterNumber;

    public Point(double x, double y)
    {
	this.x = x;
	this.y = y;
	this.clusterNumber = -1;
    }

    public double getX()
    {
	return x;
    }

    public double getY()
    {
	return y;
    }

    public int getClusterNumber()
    {
	return clusterNumber;
    }

    public void setClusterNumber(int clusterNumber)
    {
	this.clusterNumber = clusterNumber;
    }

    public static Point generateRandomPoint(int min, int max)
    {
	Random random = new Random();
	return new Point(min + (max - min) * random.nextDouble(), min + (max - min) * random.nextDouble());
    }
}
