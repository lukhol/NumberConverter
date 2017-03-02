package com.numberconverter;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gridbag.GBC;

import javax.swing.*;

public class ChoisePanel extends JPanel{
	
	private static final long serialVersionUID = 3359887859482559218L;
	private JRadioButton fromDEC, fromHEX, fromBIN;
	private ButtonGroup group;
	
	public ChoisePanel(){
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		
		group = new ButtonGroup();
		
		fromDEC = new JRadioButton("Konwertuj z DEC", true);
		fromDEC.setActionCommand("fromDEC");
		group.add(fromDEC);
		fromDEC.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				JRadioButton but = (JRadioButton)event.getSource();
				ChoisePanel choisePanel = (ChoisePanel)but.getParent();
				FrontPanel panel = (FrontPanel)choisePanel.getParent();
				
				panel.getTextAreaDec().setEditable(true);
				panel.getTextAreaDec().setForeground(Color.RED);
				
				panel.getTextAreaHex().setEditable(false);
				panel.getTextAreaHex().setForeground(Color.BLACK);
				
				panel.getTextAreaBin().setEditable(false);
				panel.getTextAreaBin().setForeground(Color.BLACK);
			}
		});
		
		fromHEX = new JRadioButton("Konwertuj z HEX", false);
		fromHEX.setActionCommand("fromHEX");
		group.add(fromHEX);
		fromHEX.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				JRadioButton but = (JRadioButton)event.getSource();
				ChoisePanel choisePanel = (ChoisePanel)but.getParent();
				FrontPanel panel = (FrontPanel)choisePanel.getParent();
				
				panel.getTextAreaDec().setEditable(false);
				panel.getTextAreaDec().setForeground(Color.BLACK);
				
				panel.getTextAreaHex().setEditable(true);
				panel.getTextAreaHex().setForeground(Color.RED);
				
				panel.getTextAreaBin().setEditable(false);
				panel.getTextAreaBin().setForeground(Color.BLACK);
			}
		});
		
		fromBIN = new JRadioButton("Konwertuj z BIN", false);
		fromBIN.setActionCommand("fromBIN");
		group.add(fromBIN);
		fromBIN.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				JRadioButton but = (JRadioButton)event.getSource();
				ChoisePanel choisePanel = (ChoisePanel)but.getParent();
				FrontPanel panel = (FrontPanel)choisePanel.getParent();
				
				panel.getTextAreaDec().setEditable(false);
				panel.getTextAreaDec().setForeground(Color.BLACK);
				
				panel.getTextAreaHex().setEditable(false);
				panel.getTextAreaHex().setForeground(Color.BLACK);
				
				panel.getTextAreaBin().setEditable(true);
				panel.getTextAreaBin().setForeground(Color.RED);
			}
		});
		
		add(fromDEC, new GBC(0,0,1,1));
		add(fromHEX, new GBC(1,0,1,1));
		add(fromBIN, new GBC(2,0,1,1));
		
		this.setBackground(Color.ORANGE);
	}
	
	public ButtonModel getSelectedButtonModel(){
		return group.getSelection();
	}
	
	public ButtonGroup getButtonGroup(){
		return group;
	}
	
	public void fromBinClick(){
		fromBIN.doClick();
	}
	
	public void fromDecClick(){
		fromDEC.doClick();
	}
	
	public void fromHexClick(){
		fromHEX.doClick();
	}
}
