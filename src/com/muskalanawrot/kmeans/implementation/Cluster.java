package com.muskalanawrot.kmeans.implementation;

import java.util.LinkedList;
import java.util.List;

/**
 * Class representing cluster.
 *
 */
public class Cluster
{
    private Integer id;
    private Observation centroid;
    private List<Observation> observations;

    /**
     * Cluster class constructor assigning id and initializing centroid value and observations list.
     * 
     * @param id number of cluster
     */
    public Cluster(Integer id)
    {
	this.id = id;
	this.centroid = null;
	this.observations = new LinkedList<Observation>();
    }

    public Integer getId()
    {
	return id;
    }

    public Observation getCentroid()
    {
	return centroid;
    }

    public List<Observation> getObservations()
    {
	return observations;
    }

    public void setCentroid(Observation centroid)
    {
	this.centroid = centroid;
    }

    public void setObservations(List<Observation> observations)
    {
	this.observations = observations;
    }

    public void clear()
    {
	observations.clear();
    }
}
