/*
* Names: Arya Afsheen & Vivian Peng
* Date: Wednesday, January 18, 2023
* Class: ICS3U7
* Teacher: Ms. Strelkovska
* Work Description: This is the Bug class. It contains values and methods necessary for bug movement and collision.
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Bug extends Player {
	
    private ImageIcon bugIcon;
    private int playerDirection;
    private int startX;
    private int endX;
    private static Obstacle randomObstacle;
    
	public Bug(int x, int y, int width, int height) {
    	super(x, y, 32, 36); // randomObstacle coordinates are still unknown
        
        bugIcon = new ImageIcon("Images/bug.png");
        playerDirection = 1; // Bug spawns moving right (direction #1)
        
        randomObstacle = Game.getRandomObstacle();
        
        while(Game.isOccupiedObstacle(randomObstacle)) {
        	randomObstacle = Game.getRandomObstacle(); // Avoids having multiple Players (either bugs or scrolls) on one Obstacle
        }
        Game.addOccupiedObstacle(randomObstacle);
        
        this.setX((int)randomObstacle.getX());
        this.setY((int)randomObstacle.getY() - (bugIcon.getIconHeight() + 10)); // Leaves a 5 pixel gap for cleanliness
        
        startX = (int)randomObstacle.getX() + (int)randomObstacle.getWidth();
        endX = (int)randomObstacle.getX();
	}
    
    public void drawBug(Graphics g) {
    	g.drawImage(bugIcon.getImage(), x, y, width, height, null); // Draws bug
    }
    
    public void updatePosition() {
    	// Moving right
    	if(playerDirection == 1) {
	    	if(canMoveRight(startX, width)) {
	    		x += 5;
	    		playerDirection = 1;
	    	}
	    	else {
	    		playerDirection = 2;
	    	}
    	}
    	// Moving left
    	else {
    		if(canMoveLeft(endX, 0)) {
	    		x -= 5;
	    		playerDirection = 2;
	    	}
	    	else {
	    		playerDirection = 1;
	    	}
    	}
    }
}

