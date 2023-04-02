/*
* Names: Arya Afsheen & Vivian Peng
* Date: Wednesday, January 18, 2023
* Class: ICS3U7
* Teacher: Ms. Strelkovska
* Work Description: This is the Player class. It contains values and methods necessary for player movement and collision.
*/

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Player extends Rectangle {
	private ImageIcon[] walkRight = {new ImageIcon("Images/player1.png"), new ImageIcon("Images/player2.png")};
    private ImageIcon[] walkLeft = {new ImageIcon("Images/player3.png"), new ImageIcon("Images/player4.png")};
    private ImageIcon[] jumpRight = {new ImageIcon("Images/player5.png")};
    private ImageIcon[] jumpLeft = {new ImageIcon("Images/player6.png")};
    
    private int floor;
    
    private int imageIndex; 
    private int playerDirection; 
    private int velocity;
    private boolean canMove;
	
    public Player(int x, int y, int width, int height) {
    	super(x, y, width, height);
        
        imageIndex = 0;
        playerDirection = 2; // Player spawns facing left (direction #2)
        velocity = 0;
        canMove = true; // Player can not collide with an obstacle when it spawns
        
        floor = y; // The bottom of the screen is the first floor plane
    }
    
    public void drawPlayer(Graphics g) {
    	// Right-facing movement
		if(playerDirection == 1) {
			g.drawImage(walkRight[imageIndex].getImage(), x, y, width, height, null); // Uses walkRight ImageIcons when player reaches ground
        }
        
        // Left-facing movement
        else if(playerDirection == 2) {
        	g.drawImage(walkLeft[imageIndex].getImage(), x, y, width, height, null); // Uses jumpLeft ImageIcons when player is mid-air 
        }
    }
    
    public int getDirection() {
    	return playerDirection; // Gets direction (1 is right, 2 is left)
    }
    
    public void setCanMove(boolean canMove) {
    	this.canMove = canMove; // Sets canMove
    }
    
    public int getFloor() {
    	return floor; // Gets current floor
    }
    
    public void setX(int x) {
    	this.x = x; // Sets x
    }
    
    public void setY(int y) {
    	this.y = y; // Sets y
    } 
    
    public boolean canMoveRight(int endX, int width) { // Checks if player is within right bounds 
	    if((x + 10) > (endX - width)) { // Leaves gap of 5 pixels for cleanliness
	        return false;
	    }
	    return true;
	}
	
	public boolean canMoveLeft(int startX, int extraSpace) { // Checks if player is within left bounds 
	    if((x - 10) < (startX + extraSpace)) { // Leaves gap of extraSpace pixels for cleanliness
	        return false;
	    }
	    return true;
	}
    
    public void walkRight() {
		if(canMoveRight(700, walkRight[0].getIconWidth()) && canMove) { // Checks if player will remain in the frame
            x += 10;
        }
		playerDirection = 1; // Sets direction to right
		imageIndex = (imageIndex + 1) % walkRight.length; // Increments walkRight ImageIcon array index by 1
	}
	
	public void walkLeft() {
		if(canMoveLeft(0, 5) && canMove) { // Checks if player will remain in the frame
            x -= 10;
        }
		playerDirection = 2; // Sets direction to left
		imageIndex = (imageIndex + 1) % walkLeft.length; // Increments walkLeft ImageIcon array index by 1 
	}
	
	public void jump() {
		velocity = -15; // Decrements velocity 
		
		if(playerDirection == 1) {
			jumpRight(); 
		}
		
		else {
			jumpLeft();
		}
	}
    
    public void jumpRight() {
		imageIndex = (imageIndex + 1) % jumpRight.length; // Increments jumpRight ImageIcon array index by 1
		
		if(canMoveRight(700, walkRight[0].getIconHeight()) && canMove) { // Checks if player will remain in the frame
			x += 20;
		}
	}
	
	public void jumpLeft() {
		imageIndex = (imageIndex + 1) % jumpLeft.length; // Increments jumpRight ImageIcon array index by 1
		
		if(canMoveLeft(0, 5) && canMove) { // Checks if player will remain in the frame
			x -= 20;
		}
	}
    
    public void updatePosition() {
		y += velocity;
		
		if((y + walkRight[0].getIconHeight()) >= (floor + walkRight[0].getIconHeight())) {
			y = floor;
            velocity = 0; // Resets velocity when done/not jumping
        }
        
        else {
            velocity++; // Increments velocity if jumping
        }
	}
    
    public int getVelocity() {
    	return velocity;
    }
    
    public boolean checkCollision(int x, int y, int w, int h) { // Manually checks for collision 
    	if(getWidth() + getX() <= x || getHeight() + getY() <= w || getX() >= (x + w) || getY() >= (y + h)) {
    		return false;
    	}
    	return true;
    }
    
    public void changeFloor(int floor) {
		this.floor = floor; // Sets new floor value
	}
    
}
