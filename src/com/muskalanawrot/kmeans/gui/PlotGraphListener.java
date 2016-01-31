package com.muskalanawrot.kmeans.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.muskalanawrot.kmeans.Main;

public class PlotGraphListener implements ActionListener
{

    private MainPanel mainPanel;
    private Main main;

    public PlotGraphListener(Main main, MainPanel mainPanel)
    {
	this.mainPanel = mainPanel;
	this.main = main;
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
	// TODO Auto-generated method stub

    }

}
