/*
* Names: Arya Afsheen & Vivian Peng
* Date: Wednesday, January 18, 2023
* Class: ICS3U7
* Teacher: Ms. Strelkovska
* Work Description: This class creates an up-to-date scoreboard of every game played. 
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Statistics extends JPanel implements ActionListener {
	private ImageIcon background;
	
	private ImageIcon homeBIcon;
	private JButton homeB;
	
	private static int wins;
    private static int losses;
    
    public Statistics() {
    	background = new ImageIcon("Images/scoreboard.png");
    	
    	homeBIcon = new ImageIcon("Images/homeButton.png");
    	homeB = new JButton(homeBIcon);
     
        readScoreboard();
        
        this.setLayout(null);
        
        // Text JPanel
        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false);
        textPanel.setBounds(185, 200, 603, 204); // Positions panel in second row of the table image
        this.add(textPanel);
        textPanel.setLayout(new GridLayout(1, 2)); // Sets textPanel to GridLayout to add text in even columns
        
        // Wins text
        JLabel winsText = new JLabel(Integer.toString(wins));
        winsText.setForeground(new Color(231, 150, 136)); // Custom colour
        winsText.setFont(new Font("Arial", Font.PLAIN, 65));
        textPanel.add(winsText);
        
        // Losses text
        JLabel lossesText = new JLabel(Integer.toString(losses));
        lossesText.setForeground(new Color(231, 150, 136)); // Custom colour
        lossesText.setFont(new Font("Arial", Font.PLAIN, 70));
        textPanel.add(lossesText);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(5, 25, 150, 72); // Positions panel in correct position
        this.add(buttonPanel);
        buttonPanel.setLayout(new BorderLayout());
        
    	homeB.setContentAreaFilled(false);
    	homeB.setBorderPainted(false);
    	homeB.setFocusPainted(false);
    	homeB.addActionListener(this);
    	buttonPanel.add(homeB, BorderLayout.WEST);
    }
	public static void addWin() {
    	wins++; // Increments wins
    	writeScoreboard(); // Updates scoreboard file
    }
    public static void addLoss() {
    	losses++; // Increments losses
    	writeScoreboard(); // Updates scoreboard file
    }
    
    public void readScoreboard() {
    	try {
        	File file = new File("statistics.txt");
            Scanner sc = new Scanner(file);
            
            wins = Integer.parseInt(sc.next()); // Reads/initializes total wins
            losses = Integer.parseInt(sc.next()); // Reads/initializes total losses
            
            sc.close();
        }
        catch(FileNotFoundException e) {
        	e.printStackTrace();
        }
    }
    
    public static void writeScoreboard() {
    	try {
        	FileWriter fileWriter = new FileWriter("statistics.txt");
            
            fileWriter.write(wins + " " + losses); // Writes updated total wins and losses
            fileWriter.close(); 
        }
        catch(IOException e) {
        	e.printStackTrace();
        }
    }
    
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	writeScoreboard(); // Updates the scores
        g.drawImage(background.getImage(), 0, 0, 700, 500, null); // Draws background table image
    }
    
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == homeB) { // Opens home panel
			Main.clear();
			Main.openHome(); 
		}
	}
}
