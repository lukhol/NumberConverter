package com.numberconverter;

import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MainFrame extends JFrame{
	public static void main(String[] args){
		JFrame fr = new MainFrame();
	}
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu fileMenu = new JMenu("Plik");
	private JMenuItem helpMJ = new JMenuItem("Pomoc");
	private JMenuItem exitMJ = new JMenuItem("WyjdŸ");
	private Color myColor = new Color(255, 255, 255);
	
	public MainFrame(){
		FrontPanel panel = new FrontPanel();
		setTitle("Konwerter systemów liczbowych");
		setSize(500,600);
		add(panel);
		
		try {
			BufferedImage image = ImageIO.read(getClass().getResourceAsStream("testIcon.png"));
			this.setIconImage(image);
			
		} catch (IOException e) {
			System.out.println("Brak obrazka background_test2.png");
			e.printStackTrace();
		}
		
		this.setJMenuBar(menuBar);
		menuBar.setBackground(new Color(150,150,150));
		menuBar.add(fileMenu);
		helpMJ.setBackground(new Color(150,150,150));
		fileMenu.add(helpMJ);
		
		helpMJ.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				JOptionPane.showMessageDialog(null, 
			"Program ten pozwala na konwersjê liczb miêdzy\n"+
			"ró¿nymi systemami libowymi. Je¿eli wpiszesz liczbê\n"+ 
			"b³êdnie zostanie ona skonwertowana na napis 'B³¹d'.");
			}
		});
		
		fileMenu.addSeparator();
		fileMenu.add(exitMJ);
		exitMJ.setBackground(new Color(150,150,150));
		
		exitMJ.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				System.exit(1);
			}
		});
		
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
