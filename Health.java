/*
* Names: Arya Afsheen & Vivian Peng
* Date: Wednesday, January 18, 2023
* Class: ICS3U7
* Teacher: Ms. Strelkovska
* Work Description: This is the Health class. It keeps track of the player's health as it collides with bugs. 
*/

import javax.swing.*;
import java.awt.*;
    
public class Health {
	private ImageIcon fullHealth;
    private ImageIcon mediumHealth;
    private ImageIcon lowHealth;
    private ImageIcon noHealth;
    
    private static int health;
    
    public Health() {
    	
    	fullHealth = new ImageIcon("Images/healthBarFull.png");
        mediumHealth = new ImageIcon("Images/healthBar2.png");
        lowHealth = new ImageIcon("Images/healthBar1.png");
        noHealth = new ImageIcon("Images/youDied.png");
        
        health = 3; // Initializes health to the maximum (3)
    }
    
    public void subtractHealth() {
    	health--; // Decrements health up to the minimum
    }
    
    public void addHealth() {
    	if(health < 3) {
    		health++; // Increments health up to the maximum (3)
        }
    }
    
    public int getHealth() {
    	return health;
    }
    
    public void drawHealth(Graphics g) {
    	if(health == 3) {
        	g.drawImage(fullHealth.getImage(), 700 - fullHealth.getIconWidth() - 5, fullHealth.getIconHeight() + 5, fullHealth.getIconWidth(), fullHealth.getIconHeight(), null); // Draws full health image
        }
        else if(health == 2) {
        	g.drawImage(mediumHealth.getImage(), 700 - fullHealth.getIconWidth() - 5, fullHealth.getIconHeight() + 5, fullHealth.getIconWidth(), fullHealth.getIconHeight(), null); // Draws medium health image
        }
        else if(health == 1) {
        	g.drawImage(lowHealth.getImage(), 700 - fullHealth.getIconWidth() - 5, fullHealth.getIconHeight() + 5, fullHealth.getIconWidth(), fullHealth.getIconHeight(), null); // Draws low health image
        }
        else {
        	g.drawImage(noHealth.getImage(), 0, 0, noHealth.getIconWidth(), noHealth.getIconHeight(), null); // Draws no health/game over image
        }
    }
}
