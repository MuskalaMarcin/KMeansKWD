package com.muskalanawrot.kmeans.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.jfree.ui.RefineryUtilities;

import com.muskalanawrot.kmeans.Main;

/**
 * PlotGraphListener class after clicking plot graph button opens new frame with generated graph.
 *
 */
public class PlotGraphListener implements ActionListener
{
    private Main main;

    public PlotGraphListener(Main main)
    {
	this.main = main;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
	EventQueue.invokeLater(new Runnable()
	{
	    public void run()
	    {
		try
		{
		    GraphFrame chart = new GraphFrame(main);
		    chart.pack();
		    RefineryUtilities.centerFrameOnScreen(chart);
		    chart.setVisible(true);
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
	    }
	});
    }
}
