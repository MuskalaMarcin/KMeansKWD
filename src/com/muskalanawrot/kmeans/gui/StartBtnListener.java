package com.muskalanawrot.kmeans.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.muskalanawrot.kmeans.Main;

public class StartBtnListener implements ActionListener
{
    private Main main;

    public StartBtnListener(Main main)
    {
	this.main = main;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
	main.calculateKmeans();
    }
}
