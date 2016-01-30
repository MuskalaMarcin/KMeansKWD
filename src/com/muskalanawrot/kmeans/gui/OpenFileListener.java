package com.muskalanawrot.kmeans.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import com.muskalanawrot.kmeans.Main;

public class OpenFileListener implements ActionListener
{
    private MainPanel mainPanel;
    private Main main;
    private JFileChooser jFileChooser;

    public OpenFileListener(Main main, MainPanel mainPanel)
    {
	this.mainPanel = mainPanel;
	this.main = main;
	jFileChooser = new JFileChooser();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
	if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
	{
	    File file = jFileChooser.getSelectedFile();
	    mainPanel.getTextField().setText(file.getName());
	    main.readFromFile(file);
	}
    }

}
