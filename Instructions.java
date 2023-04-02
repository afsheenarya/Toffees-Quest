/*
* Names: Arya Afsheen & Vivian Peng
* Date: Wednesday, January 18, 2023
* Class: ICS3U7
* Teacher: Ms. Strelkovska
* Work Description: This is the Instructions class. It explains how to play the game.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Instructions extends JPanel implements ActionListener {
	private ImageIcon background;
    
    private ImageIcon homeBIcon;
    private JButton homeB;
    
    private JPanel buttonPanel;
    
    public Instructions() {
    	background = new ImageIcon("Images/instructionsBg.png");
        
        this.setLayout(new BorderLayout()); // Sets container to BorderLayout
     	
        // Button panel
        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BorderLayout()); // Sets buttonPanel to BorderLayout
        this.add(buttonPanel, BorderLayout.NORTH);
        
        // Home button
        homeBIcon = new ImageIcon("Images/homeButton.png");
        homeB = new JButton(homeBIcon);
        homeB.setFocusPainted(false);
        homeB.setContentAreaFilled(false);
        homeB.setBorderPainted(false);
        buttonPanel.add(homeB, BorderLayout.WEST); // Adds home button to BorderLayout WEST
        homeB.addActionListener(this);
    }
    
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
        
        g.drawImage(background.getImage(), 0, 0, background.getIconWidth(), background.getIconHeight(), null); // Draws background image
    }
    
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource() == homeB) { // Sets to home panel
        	Main.clear();
            Main.openHome(); 
        }
    }
}
