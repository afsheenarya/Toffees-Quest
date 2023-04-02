/*
* Names: Arya Afsheen & Vivian Peng
* Date: Wednesday, January 18, 2023
* Class: ICS3U7
* Teacher: Ms. Strelkovska
* Work Description: This is the Home class. We display the home screen panel here. 
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends JPanel implements ActionListener {
	  private ImageIcon background;
    
    private JPanel buttonPanel;
    private JPanel subButtonPanel;
    
    private ImageIcon playBIcon;
    private JButton playB;
    
    private ImageIcon statisticsBIcon;
    private JButton statisticsB;
    
    private ImageIcon instructionsBIcon;
    private JButton instructionsB;
    
	public Home() {
        background = new ImageIcon("Images/homeScreen.png");
        
        buttonPanel = new JPanel();
        subButtonPanel = new JPanel();
        
        playBIcon = new ImageIcon("Images/playButton.png");
        playB = new JButton(playBIcon);
        
        statisticsBIcon = new ImageIcon("Images/statsButton.png");
        statisticsB = new JButton(statisticsBIcon);
        
        // Instructions button
        instructionsBIcon = new ImageIcon("Images/howToPlayButton.png");
        instructionsB = new JButton(instructionsBIcon);
        
        this.setLayout(new BorderLayout()); // Sets container layout to BorderLayout
        
        buttonPanel.setLayout(new BorderLayout()); // Sets buttonPanel layout to BorderLayout
        buttonPanel.setOpaque(false);
        this.add(buttonPanel, BorderLayout.WEST); // Adds buttonPanel to container WEST
        
        subButtonPanel.setLayout(new GridLayout(2, 1)); // Sets subButtonPanel layout to GridLayout
        subButtonPanel.setOpaque(false);
        buttonPanel.add(subButtonPanel, BorderLayout.NORTH); // Adds subButtonPanel to buttonPanel NORTH
        
        // Play button
        playB.setContentAreaFilled(false);
        playB.setBorderPainted(false);
        playB.setFocusPainted(false);
        subButtonPanel.add(playB);
        
        // Statistics button
        statisticsB.setContentAreaFilled(false);
        statisticsB.setBorderPainted(false);
        statisticsB.setFocusPainted(false);
        subButtonPanel.add(statisticsB);
        
        // Instructions button
        instructionsB.setContentAreaFilled(false);
        instructionsB.setBorderPainted(false);
        instructionsB.setFocusPainted(false);
        subButtonPanel.add(instructionsB);
        
        // Adds ActionListeners
        playB.addActionListener(this);
        statisticsB.addActionListener(this);
        instructionsB.addActionListener(this);
    }
    
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
        g.drawImage(background.getImage(), 0, 0, 700, 500, null); // Draws background
    }
    
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource() == playB) { // Opens game panel
        	  Main.clear();
              Main.openGame();
        }
        else if(e.getSource() == instructionsB) { // Opens instructions panel
        	  Main.clear();
              Main.openInstructions();
        }
        if(e.getSource() == statisticsB) { // Opens statistics panel
        	Main.clear();
            Main.openStatistics();
        }
    }
}
