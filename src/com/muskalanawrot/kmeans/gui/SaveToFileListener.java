package com.muskalanawrot.kmeans.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFileChooser;

import com.muskalanawrot.kmeans.Main;
import com.muskalanawrot.kmeans.implementation.SaveToFile;

/**
 * Save button listener performing saving kmeans results to file.
 *
 */
public class SaveToFileListener implements ActionListener
{
    private MainPanel mainPanel;
    private Main main;
    private JFileChooser jFileChooser;

    public SaveToFileListener(Main main, MainPanel mainPanel)
    {
	this.mainPanel = mainPanel;
	this.main = main;
	jFileChooser = new JFileChooser();
    }

    /**
     * Method starting thread saving to file.
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
	if (jFileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
	{
	    mainPanel.getBtnPokaWykres().setEnabled(false);
	    mainPanel.getBtnStart().setEnabled(false);
	    mainPanel.getBtnWybierzPlik().setEnabled(false);
	    mainPanel.getBtnWygeneruj().setEnabled(false);
	    mainPanel.getBtnZapiszWynik().setEnabled(false);

	    SaveToFile task = new SaveToFile(main, mainPanel, jFileChooser.getSelectedFile());
	    task.addPropertyChangeListener(new PropertyChangeListener()
	    {
		@Override
		public void propertyChange(PropertyChangeEvent evt)
		{
		    mainPanel.getProgressBar().setValue(task.getProgress());
		    if (task.isDone())
		    {
			mainPanel.getProgressBar().setValue(100);

			mainPanel.getBtnPokaWykres().setEnabled(true);
			mainPanel.getBtnStart().setEnabled(true);
			mainPanel.getBtnWybierzPlik().setEnabled(true);
			mainPanel.getBtnWygeneruj().setEnabled(true);
			mainPanel.getBtnZapiszWynik().setEnabled(true);
		    }
		}
	    });
	    task.execute();
	}
    }

}
