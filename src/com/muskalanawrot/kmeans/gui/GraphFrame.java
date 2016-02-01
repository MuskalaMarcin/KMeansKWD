package com.muskalanawrot.kmeans.gui;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.muskalanawrot.kmeans.Main;
import com.muskalanawrot.kmeans.implementation.Cluster;
import com.muskalanawrot.kmeans.implementation.Observation;

/**
 * Class generating frame with graph.
 *
 */
public class GraphFrame extends JFrame
{
    private static final long serialVersionUID = 1L;

    private Main main;

    /**
     * Constructor generating graph.
     * 
     * @param main reference to Main class
     */
    public GraphFrame(Main main)
    {
	super("Algorytm centroidow - wykres");
	this.main = main;
	JFreeChart scatterPlotChart = ChartFactory.createScatterPlot(
		"Wynik dzialania algorytmu KMeans",
		"x",
		"y",
		createDataset(),
		PlotOrientation.VERTICAL,
		true, true, false);

	ChartPanel chartPanel = new ChartPanel(scatterPlotChart);
	chartPanel.setPreferredSize(new java.awt.Dimension(600, 600));
	setContentPane(chartPanel);
    }

    /**
     * Method creating datasets to be rendered on graph.
     * 
     * @return XYDataset dataset.
     */
    private XYDataset createDataset()
    {
	final XYSeriesCollection dataset = new XYSeriesCollection();
	for (Cluster cluster : main.getClusters())
	{
	    final XYSeries newSeries = new XYSeries("Klaster " + cluster.getId());
	    for (Observation obs : cluster.getObservations())
	    {
		newSeries.add(obs.getValues().get(0), obs.getValues().get(1));
	    }
	    dataset.addSeries(newSeries);
	}
	return dataset;
    }
}
