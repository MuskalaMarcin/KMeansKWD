package com.muskalanawrot.kmeans.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener class for radio buttons helping to choose between reading observations from txt file and
 * randomly generating it.
 *
 */
public class RadioButtonsListener implements ActionListener
{
    private MainPanel panel;
    private boolean loadBtn;
    private boolean generateBtn;

    public RadioButtonsListener(MainPanel panel)
    {
	this.panel = panel;
	loadBtn = false;
	generateBtn = true;
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
		panel.getBtnStart().setEnabled(false);
		panel.addLoadData();
		panel.getTextField().setText("");
		panel.getRdbtnWygenerujAutomatycznie().setSelected(false);
		panel.getBtnStart().setEnabled(false);
		panel.getBtnZapiszWynik().setEnabled(false);
		panel.getBtnPokaWykres().setEnabled(false);
		loadBtn = true;
		generateBtn = false;
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
		panel.getBtnStart().setEnabled(true);
		panel.addGenerateData();
		panel.getRdbtnWczytajZPliku().setSelected(false);
		panel.getBtnStart().setEnabled(false);
		panel.getBtnZapiszWynik().setEnabled(false);
		panel.getBtnPokaWykres().setEnabled(false);
		loadBtn = false;
		generateBtn = true;
	    }
	}
    }

}
