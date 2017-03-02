package com.numberconverter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Enumeration;

import gridbag.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class FrontPanel extends JPanel {
	
	private BufferedImage image;
	private JTextArea textAreaDec = new JTextArea("");
	private JTextArea textAreaHex = new JTextArea("");
	private JTextArea textAreaBin = new JTextArea("");
	private JButton convertButton = new JButton("Konwertuj");
	private ChoisePanel choisePanel = new ChoisePanel();
	private Color myColor = new Color(200, 200, 200);
	
	public FrontPanel(){
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("background_test2.png"));
		} catch (IOException e) {
			System.out.println("Brak obrazka background_test2.png");
			e.printStackTrace();
		}
		
		convertButton.setBackground(myColor);
		choisePanel.setBackground(myColor);
		
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		
		Border etched = BorderFactory.createEtchedBorder();
		Border titledBorder = BorderFactory.createTitledBorder(etched, "Decymalnie:");
		JPanel decPanel = new JPanel();
		
		JScrollPane dec_scroll = new JScrollPane(textAreaDec);
		dec_scroll.setPreferredSize(new Dimension(250, 40));
		decPanel.add(dec_scroll);
		decPanel.setBorder(titledBorder);
		decPanel.setBackground(myColor);
		
		//=======================================================
		
		Border titledBorder2 = BorderFactory.createTitledBorder(etched, "Hexadecymalnie:");
		JPanel hexPanel = new JPanel();
		
		JScrollPane hex_scroll = new JScrollPane(textAreaHex);
		hex_scroll.setPreferredSize(new Dimension(250, 40));
		hexPanel.add(hex_scroll);
		hexPanel.setBorder(titledBorder2);
		hexPanel.setBackground(myColor);
		
		//=======================================================
		
		Border titledBorder3 = BorderFactory.createTitledBorder(etched, "Binarnie:");
		JPanel binPanel = new JPanel();
		
		JScrollPane bin_scroll = new JScrollPane(textAreaBin);
		bin_scroll.setPreferredSize(new Dimension(250, 40));
		binPanel.add(bin_scroll);
		binPanel.setBorder(titledBorder3);
		binPanel.setBackground(myColor);
		
		add(decPanel, new GBC(0,0,1,1).setInsets(50,0,0,0));
		add(hexPanel, new GBC(0,2,1,1).setInsets(10,0,0,0));
		add(binPanel, new GBC(0,4,1,1).setInsets(10,0,0,0));
		add(choisePanel, new GBC(0,5,1,1).setInsets(10,0,0,0));
		add(convertButton, new GBC(0,6,1,1).setInsets(10,0,0,0));
		
		convertButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				JButton but = (JButton)event.getSource();
				FrontPanel panel = (FrontPanel)but.getParent();
				ButtonModel selectedConversion = panel.getChoisePanel().getSelectedButtonModel();
				String typeOfConversion = selectedConversion.getActionCommand();
				
				NumberConverter nc = new NumberConverter();
				
				if(typeOfConversion.equals("fromDEC")){
					String dec = panel.getTextAreaDec().getText();
					panel.getTextAreaHex().setText(nc.DECtoHEX(dec));
					panel.getTextAreaBin().setText(nc.DECtoBIN(dec));
				} else if(typeOfConversion.equals("fromHEX")){
					String hex = panel.getTextAreaHex().getText();
					panel.getTextAreaDec().setText(nc.HEXtoDEC(hex));
					panel.getTextAreaBin().setText(nc.HEXtoBIN(hex));
				} else if(typeOfConversion.equals("fromBIN")){
					String bin = panel.getTextAreaBin().getText();
					panel.getTextAreaHex().setText(nc.BINtoHEX(bin));
					panel.getTextAreaDec().setText(nc.BINtoDEC(bin));
				}
			}
		});
		
		textAreaDec.getDocument().addDocumentListener(new MyDocumentListener(textAreaDec));
		textAreaHex.getDocument().addDocumentListener(new MyDocumentListener(textAreaHex));
		textAreaBin.getDocument().addDocumentListener(new MyDocumentListener(textAreaBin));
		
		firstStart();
	}
	
	class MyDocumentListener implements DocumentListener {
	    String newline = "\n";
	    JTextArea textArea;
	    public MyDocumentListener(JTextArea textArea){
	    	super();
	    	this.textArea = textArea;
	    }
	    
	    public void insertUpdate(DocumentEvent e) {
	        runnableThing();
	    }
	    private void runnableThing(){
	    	Runnable doAssist = new Runnable(){
	    		public void run(){
	    			String text = textArea.getText();
	    	        int lastCharPosition = text.length() - 1;
	    	        
	    	        if(text.charAt(lastCharPosition) == ' ' || text.charAt(lastCharPosition) == '\n' || text.charAt(lastCharPosition) == '\t'){
	    	        	textArea.setText(text.substring(0, lastCharPosition));
	    	        }
	    	        
	    	        if(text.charAt(lastCharPosition) == '\n'){
	    	        	convertButton.doClick();
	    	        }
	    	        
	    	        if(text.charAt(lastCharPosition) == '\t'){
	    	        	ButtonGroup group = choisePanel.getButtonGroup();
	    	        	Enumeration<AbstractButton> enumeration = group.getElements();
	    	        	
	    	        	AbstractButton first = enumeration.nextElement();
	    	        	AbstractButton second = enumeration.nextElement();
	    	        	AbstractButton third = enumeration.nextElement();
	    	        	
	    	        	if(first.isSelected()){
	    	        		first.setSelected(false);
	    	        		second.setSelected(true);
	    	        		textAreaHex.requestFocus();
	    	        		choisePanel.fromHexClick();
	    	        	} else if(second.isSelected()){
	    	        		second.setSelected(false);
	    	        		third.setSelected(true);
	    	        		textAreaBin.requestFocus();
	    	        		choisePanel.fromBinClick();
	    	        	} else if(third.isSelected()){
	    	        		third.setSelected(false);
	    	        		first.setSelected(true);
	    	        		choisePanel.fromDecClick();
	    	        		textAreaDec.requestFocus();
	    	        	}
	    	        	
	    	        }
	    		}
	    	};
	    	SwingUtilities.invokeLater(doAssist);
	    }
	    
	    public void removeUpdate(DocumentEvent e) {
	        
	    }
	    public void changedUpdate(DocumentEvent e) {
	    	
	    }
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(image, 0, 0, null);
	}
	
	public ChoisePanel getChoisePanel(){
		return choisePanel;
	}
	
	public JTextArea getTextAreaDec(){
		return textAreaDec;
	}
	
	public JTextArea getTextAreaHex(){
		return textAreaHex;
	}
	
	public JTextArea getTextAreaBin(){
		return textAreaBin;
	}
	
	private void firstStart(){
		textAreaDec.setEditable(true);
		textAreaDec.setForeground(Color.RED);
		
		textAreaHex.setEditable(false);
		textAreaHex.setForeground(Color.BLACK);
		
		textAreaBin.setEditable(false);
		textAreaBin.setForeground(Color.BLACK);
	}
}
