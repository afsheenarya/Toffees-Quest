/*
* Names: Arya Afsheen & Vivian Peng
* Date: Wednesday, January 18, 2023
* Class: ICS3U7
* Teacher: Ms. Strelkovska
* Work Description: This is the Game class. It is where all aspects of the first three levels function (including movement, level increments, and more).
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Game extends JPanel implements ActionListener, KeyListener {
	int[][] map = { // Keeps track of obstacle locations; 1 signifies an obstacle, while 0 signifies no obstacle
            {0, 0, 0, 0, 0, 0, 0}, // No obstacles
            {0, 0, 0, 0, 0, 0, 0}, // No obstacles 
            {1, 0, 1, 0, 1, 0, 0}, // Shelf - No obstacles on [2][6] and [2][5] to leave ladder & jumping space
            {0, 0, 0, 0, 0, 0, 0}, // No obstacles
            {0, 0, 1, 0, 1, 0, 0} // Ground - No obstacles on [3][0], [3][5] and [3][6] to leave door, spawn & ladder space
    };
	
    public static ImageIcon backgroundIcon; 
    private ImageIcon shelfIcon;
    
    // Door icon changes colour to indicate collision with player
    private ImageIcon doorIcon1;
    private ImageIcon doorIcon2;
    private ImageIcon doorIcon; //door icon being used
    
    // Ladder icon changes colour to indicate collision with player
    private ImageIcon ladderIcon1;
    private ImageIcon ladderIcon2;
    private ImageIcon ladderIcon; // Ladder icon being used
    
    private JPanel buttonPanel;
    
    private ImageIcon climbBIcon;
    private JButton climbB;
    
    private ImageIcon enterBIcon;
    private JButton enterDoorB;
    
    private static ArrayList<Obstacle> obstacles; // Holds the obstacle objects
    private static ImageIcon[] allObstacleIcons = {new ImageIcon("Images/box.png"), new ImageIcon("Images/desk.png"), new ImageIcon("Images/computer.png")};
    private static ArrayList<Obstacle> occupiedObstacles; //this keeps track of obstacles which are occupied by a Player (Scroll or Bug)
    
    private boolean hasScroll;
    private boolean hasLost;
    
    // Moving Players
    private Player player;
    private ArrayList<Bug> bugs;
    private Scroll scroll;
    
    private Health health;
    
    private int ladderX;
    private int ladderY;
    
    private int doorY;
    private int doorX;
    
    public Game() {
    	// Background/shelf floor
    	backgroundIcon = new ImageIcon("Images/mapEmpty.png");
    	shelfIcon = new ImageIcon("Images/shelf.png");
    	
    	// Door
    	doorIcon1 = new ImageIcon("Images/door.png");
    	doorIcon2 = new ImageIcon("Images/door2.png");
    	doorIcon = doorIcon1;
    	
    	// Ladder
    	ladderIcon1 = new ImageIcon("Images/ladder.png");
    	ladderIcon2 = new ImageIcon("Images/ladder2.png");
    	ladderIcon = ladderIcon1;
    	
    	// Buttons
    	buttonPanel = new JPanel();
    	
    	enterBIcon = new ImageIcon("Images/enterButton.png");
    	enterDoorB = new JButton("Enter?");
    	
    	climbBIcon = new ImageIcon("Images/climbButton.png");
    	climbB = new JButton("Climb?");
    	
    	// Obstacles
    	obstacles = new ArrayList<Obstacle>();
    	occupiedObstacles = new ArrayList<Obstacle>();
    	
    	// Bugs
    	bugs = new ArrayList<Bug>();
    	
    	hasScroll = false; // Scroll collision occurred
    	hasLost = false;
    	
    	doorX = 0;
    	doorY = backgroundIcon.getIconHeight()-doorIcon.getIconHeight();
    	
    	ladderX = backgroundIcon.getIconWidth() - backgroundIcon.getIconWidth()/7;
    	ladderY = backgroundIcon.getIconHeight()-ladderIcon.getIconHeight();
    	
    	// Clears obstacles in case it is not the first time opening the game
    	obstacles.clear();
        occupiedObstacles.clear();
        for(int i = 0; i < bugs.size(); i++) {
        	bugs.remove(i);
        }
        
        this.setLayout(new BorderLayout());
        addKeyListener(this);
        
        // Formats buttons
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.setOpaque(false);
        this.add(buttonPanel, BorderLayout.SOUTH);

        // Invisible/unclickable until collision occurrs
        enterDoorB.setFocusPainted(false);
        buttonPanel.add(enterDoorB, BorderLayout.WEST);
        enterDoorB.addActionListener(this);
        enterDoorB.setEnabled(false);
        enterDoorB.setVisible(false);

        // Invisible/unclickable until collision occurs
        climbB.setFocusPainted(false);
        buttonPanel.add(climbB, BorderLayout.EAST);
        climbB.addActionListener(this);
        climbB.setVisible(false);
        climbB.setEnabled(false);
        
        // Populates map with obstacles 
        for(int row = 0; row < map.length; row++){ //goes through array list 
            for(int col = 0; col < map[0].length; col++) { 
                if (map[row][col] == 1) { 
                    ImageIcon randomIcon = getRandomIcon(); // Generates a random obstacle image 
                    obstacles.add(new Obstacle(col * 100, row * 100 + (100 - randomIcon.getIconHeight()), randomIcon.getIconWidth(), randomIcon.getIconHeight() ,randomIcon)); // Provides appropriate dimensions for the Rectangle object 
                }
            }
        }
        
        for(int i = 0; i < Main.getLevel() + 1; i++) { 
        	bugs.add(new Bug(0, 0, 32, 36)); // Adds 2 more bugs per level 
        }
        
        scroll = new Scroll(0, 0, 52, 52); // Coordinates are dependent on randomObstacle 
        
        health = new Health();
        
        player = new Player(570, 408, 72, 92);
        
        // Checks player collisions with obstacle
        Timer obstacleCollision = new Timer(30, new ActionListener () {
            public void actionPerformed(ActionEvent e) {

                Obstacle currentObstacle = currentObst(); // Gets the obstacle closest to the player

                if(!player.intersects(currentObstacle)) { // Checks if player doesn't intersect an obstacle
                    //player is NOT on a obstacle and should go ON THE GROUND
                    if(player.getX() < currentObstacle.getX()-30 || player.getX() + player.getWidth() > currentObstacle.getX() + currentObstacle.getWidth() +30){
                        if(player.getFloor() <= 208) {
                            player.changeFloor(208);
                        }
                        else if(player.getFloor() <= 408) {
                            player.changeFloor(408);
                        }
                    }
                    player.setCanMove(true);
                }
                else { //if intersecting
                    // Checks y-collisions
                    if(((int)(player.getY())+(int)player.getHeight()) <= (int)currentObstacle.getY() + 25) { //if player foot is above obstacle
                        player.changeFloor(((int)currentObstacle.getY()) - (int)player.getHeight());
                    }


                    else {
                        // Checks x-collisions
                        if(player.getDirection() == 1 && player.getVelocity() == 0) {
                            player.setX((int)player.getX() - 2);
                        }
                        else if(player.getDirection() == 2 && player.getVelocity() == 0){
                            player.setX((int)player.getX() + 2);
                        }
                    }
                    player.setCanMove(false);
                }
                repaint();
        }
    });
    obstacleCollision.start();
        
        // Updates player movement
        Timer playerMove = new Timer(30, new ActionListener () {
            public void actionPerformed(ActionEvent e){
                // Updates player
                player.updatePosition();

                // Updates bugs
                for(int i = 0; i < bugs.size(); i++) {
                    bugs.get(i).updatePosition();
                }

                // Updates scroll
                scroll.updatePosition();
                if(player.intersects(scroll)) {
                    hasScroll = true;
                }

                // Updates door button
                if(health.getHealth() > 0) {
                    if(doorX <= player.getX() && doorX + doorIcon.getIconWidth() >= player.getX() && doorY < player.getY()) {
                    	doorIcon = doorIcon2;
                        enterDoorB.setEnabled(true);
                        enterDoorB.setVisible(true);

                    }
                    else {
                        doorIcon = doorIcon1;
                        enterDoorB.setEnabled(false);
                        enterDoorB.setVisible(false);
                    }

                    // Updates ladder button
                    if(player.checkCollision(ladderX+ladderIcon.getIconWidth(), ladderY + ladderIcon.getIconHeight(), ladderIcon.getIconWidth(), ladderIcon.getIconHeight())) { //ladder
                        ladderIcon = ladderIcon2;
                        climbB.setEnabled(true);
                        climbB.setVisible(true);
                    }
                    else{
                        ladderIcon = ladderIcon1;
                        climbB.setEnabled(false);
                        climbB.setVisible(false);
                    }
                }
                else {
                    // Disables buttons
                	climbB.setEnabled(false);
                    climbB.setVisible(false);
                    enterDoorB.setEnabled(false);
                    enterDoorB.setVisible(false);
                }
                repaint();
            }
        });
        playerMove.start();

        Timer updateHealth = new Timer(800, new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < bugs.size(); i++) {
                    if(player.intersects(bugs.get(i))) {
                        //health.subtractHealth(); //  Checks bug collision
                        if(health.getHealth() == 0) {
                            hasLost = true;
                            Statistics.addLoss();
                        }
                        else {
                        	Timer addHealth = new Timer(10000, new ActionListener () {
                        		public void actionPerformed(ActionEvent e) {
                        			health.addHealth();
                        		}
                        	});
                        	addHealth.start();
                        }
                    }
                }
                repaint();
            }
        });
       updateHealth.start();
    }
    
    public Obstacle currentObst() {
    	int[][] vals = new int[obstacles.size()][3];
        
        for(int i = 0; i < vals.length; i++) {
           vals[i][0] = Math.abs((int)player.getX() - (int)obstacles.get(i).getX());
           vals[i][1] = i;
           vals[i][2] = Math.abs((int)player.getY() - (int)obstacles.get(i).getY());
        }
        
        for (int i = 0; i < vals.length - 1; i++) {
            for (int j = 0; j < vals.length - i - 1; j++) {
                if ((vals[j][0] > vals[j + 1][0]) || vals[j][2] > vals[j + 1][2]) { // Compares y and x absolute values 
                    int[] temp = vals[j];
                    vals[j] = vals[j + 1];
                    vals[j + 1] = temp;
                }
            }
        }
        return obstacles.get(vals[0][1]); // Returns the second closest obstacle
    }
    
    public static ImageIcon getRandomIcon() { // Returns a random ImageIcon from the allObstacles array list holding obstacle images
        return allObstacleIcons[(int)(Math.random() * allObstacleIcons.length)];
    }
    
    public static Obstacle getRandomObstacle() { // Returns a random Obstacle object
    	return obstacles.get((int)(Math.random() * obstacles.size()));
    }
    
    public static void addOccupiedObstacle(Obstacle o){ // Adds occupied obstacle to occupiedObstacles array list, used in Bug and Scroll
        occupiedObstacles.add(o);
    }
    
    public static boolean isOccupiedObstacle(Obstacle o){ // Checks if the obstacle is occupied (by a Bug or Scroll)
        if(occupiedObstacles.contains(o))
            return true;
        return false;
    }
    
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	
    	g.drawImage(backgroundIcon.getImage(), 0, 0, backgroundIcon.getIconWidth(), backgroundIcon.getIconHeight(), null); // Drawing background
    	g.drawImage(doorIcon.getImage(), 0, backgroundIcon.getIconHeight()-doorIcon.getIconHeight(), doorIcon.getIconWidth(), doorIcon.getIconHeight(), null); // Drawing door
    	g.drawImage(ladderIcon.getImage(), backgroundIcon.getIconWidth() - backgroundIcon.getIconWidth()/7, backgroundIcon.getIconHeight()-ladderIcon.getIconHeight(), ladderIcon.getIconWidth(), ladderIcon.getIconHeight(), null); // Drawing ladder
        g.drawImage(shelfIcon.getImage(), 0, backgroundIcon.getIconHeight()/5*3, shelfIcon.getIconWidth(), shelfIcon.getIconHeight(), null); // Drawing shelf
    
        for(int i = 0; i < obstacles.size(); i++) {
            obstacles.get(i).drawObstacle(g); // Calls on method "drawObstacle" in Obstacle class which will draw the specified obstacle
        }
        
        for(int i = 0; i < bugs.size(); i++) {
        	bugs.get(i).drawBug(g); // Adds 2 more bugs per level
        }
        
        if(!hasScroll) { // Only draws the scroll if the player hasn't collected it
        	scroll.drawScroll(g); 
        }
        
        health.drawHealth(g);
        
        if(health.getHealth() > 0) {
        	player.drawPlayer(g);
        }
    }
    
    public void keyTyped(KeyEvent e) {
    }
    public void keyReleased(KeyEvent e) {
    }
    
    public void keyPressed(KeyEvent e) {
    	int keyCode = e.getKeyCode();
            
    	if(keyCode == KeyEvent.VK_RIGHT) {
    		player.walkRight(); // Moves right
    	}
        else if(keyCode == KeyEvent.VK_LEFT) {
        	player.walkLeft(); // Moves left
        }
        else if(keyCode == KeyEvent.VK_UP) {
        	if(player.getY() == player.getFloor()) { // Avoids double-jumping (can't jump in mid-air)
        		player.jump(); // Moves up
        	}
        }
    }

    public void nextLevel() {
    	Main.addLevel();

        Main.clear(); // Clears current level
        if(Main.getLevel() > 3) {
            Main.openPuzzle(); // Opens last level (puzzle)
        }
        else {
            Main.openGame(); // Opens new level
        }
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == enterDoorB){
            if(hasScroll) {
                nextLevel();
            }
            else {
                JOptionPane.showMessageDialog(null, "I cannot leave if I haven't collected the scroll!", "TOFFEE:", JOptionPane.PLAIN_MESSAGE);
                this.setFocusable(true);
                this.requestFocus();
            }
        }

        if(e.getSource() == climbB) {
        	if(player.getFloor() == 208)
                player.changeFloor(408); //changes floor to floor y\
            else if(player.getFloor() == 408)
                player.changeFloor(208); // Changes floor position to shelf y
            this.setFocusable(true);
            this.requestFocus();
        }
    }
}
