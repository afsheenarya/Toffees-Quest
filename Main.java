/*
* Names: Arya Afsheen & Vivian Peng
* Date: Wednesday, January 18, 2023
* Class: ICS3U7
* Teacher: Ms. Strelkovska
* Work Description: This is the Main class. We draw all game panels onto the frame/container created here. 
*/

import javax.swing.*;
import java.awt.*;

public class Main {
	protected static JFrame frame;
    protected static Container container;
    private static int level;
    
    public static void main(String[] args) {
    	frame = new JFrame("Toffee's Quest");
        container = frame.getContentPane(); // Gets container
        
        frame.setSize(700, 526);
        frame.setResizable(false); // Prevents window resizing 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true); // Makes window visible 
        
        level = 1;
        Main.openHome(); // Home is the first screen shown
    }
    
    public static void clear() {
    	while(container.getComponentCount() > 0) {
        	container.remove(0); // Removes every component on the screen 
        }
        container.repaint(); // Repaints as an empty screen
    }
    
    public static void openHome() {
    	Home home = new Home();
        container.add(home); // Adds Home components to window
        
        frame.setVisible(true);
    }
    
    public static void openGame() {
    	Game game = new Game(); // Adds Game components to window
        container.add(game);
        
        game.setFocusable(true);
        game.requestFocus();
       
        frame.setVisible(true);
    }
    
    public static void openInstructions() {
    	Instructions instructions = new Instructions(); // Adds Instructions components to window
        container.add(instructions);
        
        frame.setVisible(true);
    }
    
    public static void openStatistics() {
    	Statistics statistics = new Statistics(); // Adds Statistics components to window
        container.add(statistics);
        
        frame.setVisible(true);
    }
    
    public static void openPuzzle() {
    	Puzzle puzzle = new Puzzle(); // Adds Puzzle components to window
        container.add(puzzle);
        
        puzzle.setFocusable(true);
        puzzle.requestFocus();
        
        frame.setVisible(true);
    }
    
    public static void addLevel() {
    	level++; // Increments level
    }
    public static int getLevel() {
    	return level; // Gets level 
    }
}