package com.muskalanawrot.kmeans.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.muskalanawrot.kmeans.Main;

public class RadioButtonsListener implements ActionListener
{
    private Main main;
    private MainPanel panel;
    private boolean loadBtn;
    private boolean generateBtn;

    public RadioButtonsListener(Main main, MainPanel panel)
    {
	this.main = main;
	this.panel = panel;
	loadBtn = false;
	generateBtn = false;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
	if (e.getActionCommand().equals("Wczytaj z pliku"))
	{
	    if (loadBtn)
	    {
		panel.getRdbtnWczytajZPliku().setSelected(true);
	    }
	    else
	    {
		panel.getRdbtnWygenerujAutomatycznie().setSelected(false);
		loadBtn = true;
		generateBtn = false;
		panel.getTextField().setEnabled(true);
		panel.getTextField_1().setEnabled(false);
		panel.getTextField_2().setEnabled(true);
		panel.getBtnWybierzPlik().setEnabled(true);
		panel.getBtnStart().setEnabled(true);
	    }
	}
	else
	{
	    if (generateBtn)
	    {
		panel.getRdbtnWygenerujAutomatycznie().setSelected(true);
	    }
	    else
	    {
		panel.getRdbtnWczytajZPliku().setSelected(false);
		loadBtn = false;
		generateBtn = true;
		panel.getTextField().setEnabled(false);
		panel.getTextField_1().setEnabled(true);
		panel.getTextField_2().setEnabled(true);
		panel.getBtnWybierzPlik().setEnabled(false);
		panel.getBtnStart().setEnabled(true);
	    }
	}
    }

}
