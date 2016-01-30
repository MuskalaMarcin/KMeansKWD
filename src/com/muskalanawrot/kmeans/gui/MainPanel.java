package com.muskalanawrot.kmeans.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.muskalanawrot.kmeans.Main;

public class MainPanel extends JPanel
{
    private static final long serialVersionUID = 1L;

    private Main main;

    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextArea textArea;
    private JRadioButton rdbtnWczytajZPliku;
    private JRadioButton rdbtnWygenerujAutomatycznie;
    private JButton btnWybierzPlik;
    private JButton btnStart;
    private JProgressBar progressBar;

    /**
     * Create the panel.
     */
    public MainPanel(Main main)
    {
	this.main = main;
	this.setBorder(new EmptyBorder(5, 5, 5, 5));
	this.setBounds(10, 11, 500, 447);
	this.setLayout(null);
	this.init();
	this.setActionListeners();
    }

    public void setActionListeners()
    {
	RadioButtonsListener radioBtnLstnr = new RadioButtonsListener(main, this);
	rdbtnWczytajZPliku.addActionListener(radioBtnLstnr);
	rdbtnWygenerujAutomatycznie.addActionListener(radioBtnLstnr);
	btnWybierzPlik.addActionListener(new OpenFileListener(main, this));
	btnStart.addActionListener(new StartKmeansListener(main, this));
    }

    public void init()
    {
	textArea = new JTextArea();
	textArea.setBounds(10, 237, 480, 166);
	add(textArea);
	textArea.setEditable(false);

	rdbtnWczytajZPliku = new JRadioButton("Wczytaj z pliku");
	rdbtnWczytajZPliku.setBounds(183, 6, 127, 23);
	add(rdbtnWczytajZPliku);

	rdbtnWygenerujAutomatycznie = new JRadioButton("Wygeneruj losowo");
	rdbtnWygenerujAutomatycznie.setBounds(348, 6, 146, 23);
	add(rdbtnWygenerujAutomatycznie);

	textField = new JTextField();
	textField.setBounds(139, 69, 190, 20);
	add(textField);
	textField.setColumns(10);
	textField.setEnabled(false);

	btnWybierzPlik = new JButton("Wybierz plik");
	btnWybierzPlik.setBounds(348, 68, 125, 23);
	add(btnWybierzPlik);
	btnWybierzPlik.setEnabled(false);

	textField_1 = new JTextField();
	textField_1.setBounds(139, 134, 120, 20);
	add(textField_1);
	textField_1.setColumns(10);
	textField_1.setEnabled(false);

	btnStart = new JButton("Start");
	btnStart.setBounds(353, 178, 120, 45);
	add(btnStart);
	btnStart.setEnabled(false);

	textField_2 = new JTextField();
	textField_2.setBounds(139, 200, 120, 20);
	add(textField_2);
	textField_2.setColumns(10);
	textField_2.setEnabled(false);

	JLabel lblNewLabel = new JLabel("Dane wej\u015Bciowe:");
	lblNewLabel.setBounds(10, 3, 135, 28);
	add(lblNewLabel);
	JLabel lblNewLabel_1 = new JLabel("Wczytaj dane:");
	lblNewLabel_1.setBounds(10, 42, 146, 14);
	add(lblNewLabel_1);
	JSeparator separator = new JSeparator();
	separator.setBounds(175, 83, 1, 2);
	add(separator);
	JSeparator separator_1 = new JSeparator();
	separator_1.setBounds(0, 36, 500, 2);
	add(separator_1);
	JSeparator separator_2 = new JSeparator();
	separator_2.setBounds(0, 105, 500, 2);
	add(separator_2);
	JLabel lblWygenerujDane = new JLabel("Wygeneruj dane:");
	lblWygenerujDane.setBounds(10, 112, 146, 14);
	add(lblWygenerujDane);
	JLabel lblIloPunktw = new JLabel("Ilo\u015B\u0107 punkt\u00F3w:");
	lblIloPunktw.setBounds(36, 137, 109, 14);
	add(lblIloPunktw);
	JSeparator separator_3 = new JSeparator();
	separator_3.setBounds(0, 165, 500, 2);
	add(separator_3);
	JLabel lblNewLabel_2 = new JLabel("Algorytm centroid\u00F3w ustawienia:");
	lblNewLabel_2.setBounds(10, 178, 217, 14);
	add(lblNewLabel_2);
	JLabel lblIloKlastrw = new JLabel("Ilo\u015B\u0107 klastr\u00F3w");
	lblIloKlastrw.setBounds(36, 203, 100, 14);
	add(lblIloKlastrw);
	JLabel lblNazwaPliku = new JLabel("Nazwa pliku:");
	lblNazwaPliku.setBounds(36, 71, 83, 14);
	add(lblNazwaPliku);

	progressBar = new JProgressBar();
	progressBar.setBounds(175, 414, 146, 22);
	add(progressBar);
	progressBar.setMaximum(100);
	progressBar.setMinimum(0);
    }

    public JTextField getTextField()
    {
	return textField;
    }

    public JTextField getTextField_1()
    {
	return textField_1;
    }

    public JTextField getTextField_2()
    {
	return textField_2;
    }

    public JTextArea getTextArea()
    {
	return textArea;
    }

    public JRadioButton getRdbtnWczytajZPliku()
    {
	return rdbtnWczytajZPliku;
    }

    public JRadioButton getRdbtnWygenerujAutomatycznie()
    {
	return rdbtnWygenerujAutomatycznie;
    }

    public JButton getBtnWybierzPlik()
    {
	return btnWybierzPlik;
    }

    public JButton getBtnStart()
    {
	return btnStart;
    }

    public JProgressBar getProgressBar()
    {
	return progressBar;
    }

}