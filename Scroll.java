/*
* Names: Arya Afsheen & Vivian Peng
* Date: Wednesday, January 18, 2023
* Class: ICS3U7
* Teacher: Ms. Strelkovska
* Work Description: This is the Scroll class. It contains values and methods necessary for scroll movement and collision.
*/

import javax.swing.*;
import java.awt.*;

public class Scroll extends Player {
	private ImageIcon scrollIcon;
	private Obstacle randomObstacle;
	private int playerDirection;
	
	public Scroll(int x, int y, int width, int height) {
		super(x, y, width, height);
		
        scrollIcon = new ImageIcon("Images/scroll.png");
        
		randomObstacle = Game.getRandomObstacle();
		
		while(Game.isOccupiedObstacle(randomObstacle)) { // Avoids having multiple Players (either bugs or scrolls) on one Obstacle
            randomObstacle = Game.getRandomObstacle();
        }
		Game.addOccupiedObstacle(randomObstacle);
		
		setY((int)randomObstacle.getY() - height - 5); // Leaves a 5 pixel gap for cleanliness
		setX((int)randomObstacle.getX() + ((int)(randomObstacle.getWidth() - scrollIcon.getIconWidth())));
	
		playerDirection = 1; // Scroll spawns moving right (direction #1)
	}
	
	public void setX(int x) {
		this.x = x; // Sets x
	}
	
	public void setY(int y) {
		this.y = y; // Gets x
	}
	
	private boolean canMoveUp(){ // Checks if player is within upwards bounds
        if(y <= ((int)randomObstacle.getY() - height - 5)) { // Leaves gap of 5 pixels for cleanliness
            return true;
        }
        return false;
    }

    private boolean canMoveDown(){ // Checks if player is within downwards bounds
        if(y >= ((int)randomObstacle.getY() - height - 10)) { // Leaves gap of 10 pixels for cleanliness
            return true;
        }
        return false;
    }

    public void updatePosition() { // Overrides Player updatePosition method to make smaller steps  
        if(playerDirection == 1) { 
            if (canMoveUp()) { // Indicates the scroll has not reached the up-most point on the obstacle 
                y++;
                playerDirection = 1;
            }
            else { // Indicates the scroll has reached the up-most point on the obstacle 
                playerDirection = 2; 
            }
        }
        else if(playerDirection == 2){ 
            if (canMoveDown()) { // Indicates the scroll has not reached the down-most point on the obstacle 
            	y--;
                playerDirection = 2;
            }
            else { // Indicates the scroll has reached the down-most point on the obstacle 
                playerDirection = 1;
            }
        }
    }
    
    public void drawScroll(Graphics g){
        g.drawImage(scrollIcon.getImage(), x, y, width, height, null); // Draws scroll
    }
}