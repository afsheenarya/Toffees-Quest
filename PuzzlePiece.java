import java.awt.*;
import javax.swing.*;

/*
* Names: Arya Afsheen & Vivian Peng
* Date: Wednesday, January 18, 2023
* Class: ICS3U7
* Teacher: Ms. Strelkovska
* Work Description: This is the PuzzlePiece class. It creates puzzle pieces for the Puzzle class.
*/

public class PuzzlePiece extends Rectangle {
	private ImageIcon thisIcon; // The icon of the particular blank
    
    public PuzzlePiece(int x, int y, int width, int height, ImageIcon thisIcon) {
    	super(x, y, width, height);
        this.thisIcon = thisIcon;
    }
    
    public void setThisIcon(ImageIcon thisIcon) {
    	this.thisIcon = thisIcon; // Sets thisIcon
    }
    
    public ImageIcon getThisIcon() {
    	return thisIcon; // Gets thisIcon
    }
    
    public void drawPuzzlePiece(Graphics g) {
    	g.drawImage(thisIcon.getImage(), x, y, width, height, null); // Draws the puzzle piece icon in the correct position
    }
    
}