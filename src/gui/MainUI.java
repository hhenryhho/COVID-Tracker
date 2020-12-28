package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.SystemFacade;
import observer.ImageRenderer;
import observer.OutputRenderer;

import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import java.awt.Color;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;

/**
 * This represents the class for the MainUI
 */
public class MainUI extends JFrame {

	private JPanel contentPane;
	private JTextField addCountryField;
	private JTextField removeCountryField;
	private SystemFacade controller;
	private JPanel outputList; 
	private JLabel labelMap;
	
	/**
	 * Create the frame.
	 */
	public MainUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1195, 669);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Area that displays selected countries------------------------------------
		JLabel labelCountryList = new JLabel("List of selected countries");
		labelCountryList.setHorizontalAlignment(SwingConstants.CENTER);
		labelCountryList.setBounds(973, 48, 187, 14);
		contentPane.add(labelCountryList);
		
		JPanel countryListPanel = new JPanel();
		countryListPanel.setBackground(UIManager.getColor("CheckBox.highlight"));
		countryListPanel.setBounds(973, 73, 187, 220);
		contentPane.add(countryListPanel);
		countryListPanel.setLayout(null);
		
		JTextArea countryListField = new JTextArea();
		countryListField.setEditable(false);
		countryListField.setText("");
		countryListField.setBounds(10, 11, 167, 198);
		countryListPanel.add(countryListField);
		//  ----------------------------------------------------------------------
		
		// Area that displays selected countries and their analyzed output-------
		JLabel labelOutput = new JLabel("Output:");
		labelOutput.setHorizontalAlignment(SwingConstants.CENTER);
		labelOutput.setBounds(973, 340, 187, 14);
		contentPane.add(labelOutput);
		
		outputList = new JPanel();
		outputList.setBackground(Color.WHITE);
		outputList.setBounds(973, 365, 187, 220);
		contentPane.add(outputList);
		outputList.setLayout(null);
		
		// Render the output field to be nothing first
		JTextArea outputListField = new JTextArea();
		outputListField.setEditable(false);
		outputListField.setText(OutputRenderer.getInstance().render());
		outputListField.setBounds(10, 11, 180, 198);
		outputList.add(outputListField);
		//  ----------------------------------------------------------------------
		
		// Section that contains "Choosing analysis method"
		JLabel labelMethodList = new JLabel("Choose analysis method:");
		labelMethodList.setHorizontalAlignment(SwingConstants.CENTER);
		labelMethodList.setBounds(305, 598, 187, 14);
		contentPane.add(labelMethodList);
		
		JComboBox<String> dropdownMethods = new JComboBox<String>();
		dropdownMethods.addItem("Confirmed Cases");
		dropdownMethods.addItem("Confirmed Cases Per Capita");
		dropdownMethods.addItem("Confirmed Male Cases");
		dropdownMethods.addItem("Confirmed Female Cases");
		dropdownMethods.setBounds(481, 594, 148, 22);
		contentPane.add(dropdownMethods);
		
		//  ----------------------------------------------------------------------
		
		// Recalculate button-----------------------------------------------------
		JButton recalculateBtn = new JButton("Recalculate");
		recalculateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setAnalysisMethod(dropdownMethods.getSelectedItem().toString());
				controller.performCompleteAnalysis();
				outputListField.setText(OutputRenderer.getInstance().render());
				labelMap.setIcon(ImageRenderer.getInstance(labelMap.getWidth(), labelMap.getHeight()).render());
			}
		});
		recalculateBtn.setBounds(1002, 304, 127, 23);
		contentPane.add(recalculateBtn);
		//  ----------------------------------------------------------------------
		
		// Section that contains functionality to add countries-------------------
		JLabel labelAddCountry = new JLabel("Add a country:");
		labelAddCountry.setHorizontalAlignment(SwingConstants.CENTER);
		labelAddCountry.setBounds(162, 15, 89, 14);
		contentPane.add(labelAddCountry);
		
		addCountryField = new JTextField();
		addCountryField.setBounds(262, 11, 161, 23);
		contentPane.add(addCountryField);
		addCountryField.setColumns(10);
		
		JButton addButton = new JButton("+");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.addCountry(addCountryField.getText());
				countryListField.setText(controller.getCountryList());
				addCountryField.setText("");
			}
		});
		addButton.setBounds(433, 11, 47, 23);
		contentPane.add(addButton);
		//  ----------------------------------------------------------------------
		
		// Section that contains functionality to remove countries---------------
		JLabel labelRemoveCountry = new JLabel("Remove a country:");
		labelRemoveCountry.setHorizontalAlignment(SwingConstants.CENTER);
		labelRemoveCountry.setBounds(521, 15, 135, 14);
		contentPane.add(labelRemoveCountry);
		
		removeCountryField = new JTextField();
		removeCountryField.setColumns(10);
		removeCountryField.setBounds(657, 11, 161, 23);
		contentPane.add(removeCountryField);	
		
		JButton removeBtn = new JButton("-");
		removeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.removeCountry(removeCountryField.getText());
				countryListField.setText(controller.getCountryList());
				removeCountryField.setText("");
			}
		});
		removeBtn.setBounds(828, 11, 47, 23);
		contentPane.add(removeBtn);
		//  ----------------------------------------------------------------------
		
		// Section that contains the map display----------------------------------
		labelMap = new JLabel("");
		labelMap.setBounds(10, 73, 954, 510);
		labelMap.setIcon(ImageRenderer.getInstance(labelMap.getWidth(), labelMap.getHeight()).render());
		contentPane.add(labelMap);
		//  ----------------------------------------------------------------------
		
		controller = new SystemFacade();
	}
	
}
