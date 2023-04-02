/*
* Names: Arya Afsheen & Vivian Peng
* Date: Wednesday, January 18, 2023
* Class: ICS3U7
* Teacher: Ms. Strelkovska
* Work Description: This is the Obstacle class. It instantiates each furniture item, serving as an obstacle.
*/

import javax.swing.*;
import java.awt.*;

public class Obstacle extends Rectangle {
	private ImageIcon obstacleIcon;
    
    public Obstacle(int x, int y, int w, int h, ImageIcon obstacleIcon) {
        super(x, y, w, h);
        this.obstacleIcon = obstacleIcon; // Sets corresponding ImageIcon
    }
    
    public void drawObstacle(Graphics g) {
        g.drawImage(obstacleIcon.getImage(), x, y, width, height, null); // Draws obstacle
    }
}
