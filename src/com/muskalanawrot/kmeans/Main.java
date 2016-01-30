package com.muskalanawrot.kmeans;

import javax.swing.JFrame;

import com.muskalanawrot.kmeans.gui.MainPanel;

public class Main implements Runnable
{
    private MainPanel mainPanel;
    private JFrame mainFrame;

    public Main()
    {
	this.mainPanel = new MainPanel(this);
	this.mainFrame = new JFrame();
	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	mainFrame.setBounds(100, 100, 505, 485);
	mainFrame.setTitle("Algorytm centroidów - Muska³a, Nawrot");
	mainFrame.setContentPane(mainPanel);
	mainFrame.setResizable(false);
    }

    public static void main(String args[])
    {
	new Thread(new Main()).start();
    }

    public void readFromFile()
    {

    }

    public void generatePoints()
    {

    }

    public MainPanel getMainPanel()
    {
	return mainPanel;
    }

    @Override
    public void run()
    {
	mainFrame.setVisible(true);
    }

}
