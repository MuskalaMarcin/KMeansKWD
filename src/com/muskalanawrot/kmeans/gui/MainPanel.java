package com.muskalanawrot.kmeans.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
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
    private JLabel lblIloKlastrw;
    private JLabel lblWygenerujDane;
    private JLabel lblNazwaPliku;
    private JLabel lblNewLabel_1;
    private JLabel lblIloPunktw;
    private JButton btnWygeneruj;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JLabel lblDugoWektora;
    private JLabel lblPrzedzia;
    private JButton btnPokaWykres;
    private JButton btnZapiszWynik;

    /**
     * Create the panel.
     */
    public MainPanel(Main main)
    {
	this.main = main;
	this.setBorder(new EmptyBorder(5, 5, 5, 5));
	this.setBounds(10, 11, 500, 501);
	this.setLayout(null);
	this.init();
	this.setActionListeners();
    }

    public void setActionListeners()
    {
	RadioButtonsListener radioBtnLstnr = new RadioButtonsListener(this);
	rdbtnWczytajZPliku.addActionListener(radioBtnLstnr);
	rdbtnWygenerujAutomatycznie.addActionListener(radioBtnLstnr);
	btnWybierzPlik.addActionListener(new OpenFileListener(main, this));
	btnStart.addActionListener(new StartBtnListener(main, this));
	btnWygeneruj.addActionListener(new GenerateListener(main, this));
	btnPokaWykres.addActionListener(new PlotGraphListener(main));
	btnZapiszWynik.addActionListener(new SaveToFileListener(main, this));
    }

    public void addLoadData()
    {
	add(lblNazwaPliku);
	add(lblNewLabel_1);
	add(textField);
	add(btnWybierzPlik);

	remove(btnWygeneruj);
	remove(lblWygenerujDane);
	remove(textField_1);
	remove(lblIloPunktw);
	remove(lblPrzedzia);
	remove(lblDugoWektora);
	remove(textField_4);
	remove(textField_3);
	remove(textField_5);

	validate();
	repaint();
    }

    public void addGenerateData()
    {
	add(lblWygenerujDane);
	add(textField_1);
	add(lblIloPunktw);
	add(btnWygeneruj);
	add(lblPrzedzia);
	add(lblDugoWektora);
	add(textField_4);
	add(textField_3);
	add(textField_5);

	remove(lblNazwaPliku);
	remove(lblNewLabel_1);
	remove(textField);
	remove(btnWybierzPlik);

	validate();
	repaint();
    }

    public void init()
    {
	lblNazwaPliku = new JLabel("Nazwa pliku:");
	lblNazwaPliku.setBounds(36, 71, 83, 14);
	lblNewLabel_1 = new JLabel("Wczytaj dane:");
	lblNewLabel_1.setBounds(10, 42, 146, 14);
	textField_3 = new JTextField();
	textField_3.setBounds(122, 128, 86, 20);

	textField_3.setColumns(10);
	textField_3.setText("0");

	textField_4 = new JTextField();
	textField_4.setBounds(218, 128, 86, 20);

	textField_4.setColumns(10);
	textField_4.setText("100");

	textField_5 = new JTextField();
	textField_5.setBounds(159, 99, 120, 20);
	textField_5.setText("2");

	textField_5.setColumns(10);

	lblDugoWektora = new JLabel("D\u0142ugo\u015B\u0107 wektor\u00F3w:");
	lblDugoWektora.setBounds(36, 102, 120, 14);

	lblPrzedzia = new JLabel("Przedzia\u0142: ");
	lblPrzedzia.setBounds(36, 131, 76, 14);
	btnWygeneruj = new JButton("Wygeneruj");
	btnWygeneruj.setBounds(348, 71, 120, 77);
	add(btnWygeneruj);
	lblIloKlastrw = new JLabel("Ilo\u015B\u0107 klastr\u00F3w");
	lblIloKlastrw.setBounds(36, 196, 100, 14);
	add(lblIloKlastrw);
	lblWygenerujDane = new JLabel("Wygeneruj dane:");
	lblWygenerujDane.setBounds(10, 42, 146, 14);
	textField = new JTextField();
	textField.setBounds(139, 69, 190, 20);
	btnWybierzPlik = new JButton("Wybierz plik");
	btnWybierzPlik.setBounds(348, 67, 120, 23);
	textField.setColumns(10);

	textField_1 = new JTextField();
	textField_1.setBounds(159, 71, 120, 20);
	textField_1.setColumns(10);
	textField_1.setText("1000");

	textArea = new JTextArea();
	textArea.setBounds(10, 272, 470, 183);
	textArea.setLineWrap(true);
	textArea.setWrapStyleWord(true);

	textArea.setEditable(false);
	JScrollPane scroll = new JScrollPane(textArea);
	scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	scroll.setBounds(10, 272, 480, 183);
	add(scroll);

	rdbtnWczytajZPliku = new JRadioButton("Wczytaj z pliku");
	rdbtnWczytajZPliku.setBounds(183, 6, 127, 23);
	add(rdbtnWczytajZPliku);

	rdbtnWygenerujAutomatycznie = new JRadioButton("Wygeneruj losowo");
	rdbtnWygenerujAutomatycznie.setBounds(348, 6, 146, 23);
	add(rdbtnWygenerujAutomatycznie);
	rdbtnWygenerujAutomatycznie.setSelected(true);

	btnStart = new JButton("Start");
	btnStart.setBounds(348, 173, 120, 91);
	add(btnStart);
	btnStart.setEnabled(false);

	textField_2 = new JTextField();
	textField_2.setText("5");
	textField_2.setBounds(140, 193, 120, 20);
	add(textField_2);
	textField_2.setColumns(10);

	JLabel lblNewLabel = new JLabel("Dane wej\u015Bciowe:");
	lblNewLabel.setBounds(10, 3, 135, 28);
	add(lblNewLabel);

	JSeparator separator = new JSeparator();
	separator.setBounds(175, 83, 1, 2);
	add(separator);
	JSeparator separator_1 = new JSeparator();
	separator_1.setBounds(0, 36, 500, 2);
	add(separator_1);
	JSeparator separator_2 = new JSeparator();
	separator_2.setBounds(0, 159, 500, 2);
	add(separator_2);

	lblIloPunktw = new JLabel("Ilo\u015B\u0107 obserwacji:");
	lblIloPunktw.setBounds(36, 71, 109, 14);
	add(lblIloPunktw);
	JLabel lblNewLabel_2 = new JLabel("Algorytm centroid\u00F3w:");
	lblNewLabel_2.setBounds(10, 171, 217, 14);
	add(lblNewLabel_2);

	addGenerateData();
	progressBar = new JProgressBar();
	progressBar.setBounds(178, 466, 146, 22);
	add(progressBar);
	progressBar.setMaximum(100);
	progressBar.setMinimum(0);
	progressBar.setStringPainted(true);

	btnPokaWykres = new JButton("Poka\u017C wykres");
	btnPokaWykres.setEnabled(false);
	btnPokaWykres.setBounds(36, 238, 120, 23);
	add(btnPokaWykres);

	btnZapiszWynik = new JButton("Zapisz wynik");
	btnZapiszWynik.setEnabled(false);
	btnZapiszWynik.setBounds(183, 238, 121, 23);
	add(btnZapiszWynik);

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

    public JButton getBtnWygeneruj()
    {
	return btnWygeneruj;
    }

    public void write(String text)
    {
	getTextArea().append(text + "\n");
    }

    public JTextField getTextField_3()
    {
	return textField_3;
    }

    public JTextField getTextField_4()
    {
	return textField_4;
    }

    public JTextField getTextField_5()
    {
	return textField_5;
    }

    public JButton getBtnPokaWykres()
    {
	return btnPokaWykres;
    }

    public JButton getBtnZapiszWynik()
    {
	return btnZapiszWynik;
    }
}
