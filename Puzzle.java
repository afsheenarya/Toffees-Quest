/*
* Names: Arya Afsheen & Vivian Peng
* Date: Wednesday, January 18, 2023
* Class: ICS3U7
* Teacher: Ms. Strelkovska
* Work Description: This is the Puzzle class. It is a drag-and-drop Java syntax game, and the last level of the game.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Puzzle extends JPanel implements ActionListener, MouseListener {
	private ImageIcon background;
    private ImageIcon blank;
    
    private ImageIcon curlyBracketBIcon;
    private JButton curlyBracketB;
    
    private ImageIcon squareBracketBIcon;
    private JButton squareBracketB;
    
    private ImageIcon quotationBIcon;
    private JButton quotationB;
    
    private ImageIcon homeBIcon;
    private JButton homeB;
    
    private ImageIcon doneBIcon;
    private JButton doneB;
    
    private Icon selection;
    private JPanel selectionBar; 
    
    private ImageIcon win;
    private boolean hasWon;
    
    private PuzzlePiece piece1;
    private PuzzlePiece piece2;
    private PuzzlePiece piece3;
    
    public Puzzle() {
    	background = new ImageIcon("Images/puzzleBg.png");
        blank = new ImageIcon("Images/blank.png");
        selection = blank;
        win = new ImageIcon("Images/youWon.png");
        
        // Curly bracket (selection) button
        curlyBracketBIcon = new ImageIcon("Images/curlyBracket.png");
        curlyBracketB = new JButton(curlyBracketBIcon);
        curlyBracketB.setContentAreaFilled(false);
        curlyBracketB.setBorderPainted(false);
        curlyBracketB.addActionListener(this);
        
        // Square bracket (selection) button
        squareBracketBIcon = new ImageIcon("Images/squareBracket.png");
        squareBracketB = new JButton(squareBracketBIcon);
        squareBracketB.setContentAreaFilled(false);
        squareBracketB.setBorderPainted(false);
        squareBracketB.addActionListener(this);
        
        // Quotation (selection) button
        quotationBIcon = new ImageIcon("Images/quotes.png");
        quotationB = new JButton(quotationBIcon);
        quotationB.setContentAreaFilled(false);
        quotationB.setBorderPainted(false);
        quotationB.addActionListener(this);
        
        // Done (all selections) button
        doneBIcon = new ImageIcon("Images/doneButton.png");
        doneB = new JButton(doneBIcon);
        doneB.setContentAreaFilled(false);
        doneB.setBorderPainted(false);
        doneB.setFocusPainted(false);
        doneB.addActionListener(this);
        
        // Home button
        homeBIcon = new ImageIcon("Images/homeButton.png");
        homeB = new JButton(homeBIcon);
        homeB.setContentAreaFilled(false);
        homeB.setBorderPainted(false);
        homeB.setFocusPainted(false);
        homeB.addActionListener(this);
        
    	addMouseListener(this); 
        this.setLayout(new BorderLayout()); // Sets BorderLayout to container
        
        // Adds possible selections to the selection bar
        selectionBar = new JPanel(new FlowLayout());
        this.add(selectionBar, BorderLayout.SOUTH);
        selectionBar.setOpaque(false);
        selectionBar.add(squareBracketB);
        selectionBar.add(curlyBracketB);
        selectionBar.add(quotationB);
        selectionBar.add(doneB);
        
        // Adds home button to the container
        this.add(homeB, BorderLayout.NORTH);
        homeB.setEnabled(false);
        homeB.setVisible(false); // User can only return to the home panel if they win the game
        
        // Creates PuzzlePiece objects (function as blanks)
        piece1 = new PuzzlePiece(520, 115, blank.getIconWidth(), blank.getIconHeight(), blank);
        piece2 = new PuzzlePiece(420, 180, blank.getIconWidth(), blank.getIconHeight(), blank);
        piece3 = new PuzzlePiece(20, 300, blank.getIconWidth(), blank.getIconHeight(), blank);
    }
    
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource() == squareBracketB) {
        	selection = squareBracketB.getIcon(); // The selection becomes the square bracket 
        }
        else if(e.getSource() == curlyBracketB) {
        	selection = curlyBracketB.getIcon(); // The selection becomes the curly bracket 
        }
        else if(e.getSource() == quotationB) {
        	selection = quotationB.getIcon(); // The selection becomes the quotation  
        }
        else if(e.getSource() == doneB) {
        	if(selectionsAreCorrect()) {
            	hasWon = true;
            	Statistics.addWin();
                repaint();
                
                // Makes home button visible/clickable
                homeB.setEnabled(true);
                homeB.setVisible(true);
                
                // Makes selections and done (selections) invisible/not clickable
                squareBracketB.setEnabled(false);
                squareBracketB.setVisible(false);

                curlyBracketB.setEnabled(false);
                curlyBracketB.setVisible(false);

                quotationB.setEnabled(false);
                quotationB.setVisible(false);

                doneB.setEnabled(false);
                doneB.setVisible(false);
            }
        	else {
            	JOptionPane.showMessageDialog(null, "This is not the correct code...", "TOFFEE:", JOptionPane.PLAIN_MESSAGE); // Notifies player of incorrect selections
            }
        }
    	if(e.getSource() == homeB) {
        	Main.clear();
            Main.openHome(); // Sets to home panel
        }
    }
    
    public void mouseClicked(MouseEvent e) {
    	if(piece1.contains(e.getPoint())) {
        	if(selection == blank) {
            	JOptionPane.showMessageDialog(null, "Please choose the correct syntax!", "TOFFEE:", JOptionPane.PLAIN_MESSAGE); // Notifies player of missing selections
            }
            else {
            	piece1.setThisIcon((ImageIcon)selection);
                repaint();
            }
        }
        
        if(piece2.contains(e.getPoint())) {
        	if(selection == blank) {
            	JOptionPane.showMessageDialog(null, "Please choose the correct syntax!", "TOFFEE:", JOptionPane.PLAIN_MESSAGE); // Notifies player of missing selections
            }
            else {
            	piece2.setThisIcon((ImageIcon)selection);
                repaint();
            }
        }
        
        if(piece3.contains(e.getPoint())) {
        	if(selection == blank) {
            	JOptionPane.showMessageDialog(null, "Please choose the correct syntax!", "TOFFEE:", JOptionPane.PLAIN_MESSAGE); // Notifies player of missing selections
            }
            else {
            	piece3.setThisIcon((ImageIcon)selection);
                repaint();
            }
        }
    }
    
    public boolean selectionsAreCorrect() {
    	if((piece1.getThisIcon() == squareBracketBIcon) && (piece2.getThisIcon() == quotationBIcon) && (piece3.getThisIcon() == curlyBracketBIcon)) {
    		return true; // All selections are in the correct positions
        }
        return false; // Selections are not in the correct positions
    }
    
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
        
        g.drawImage(background.getImage(), 0, 0, background.getIconWidth(), background.getIconHeight(), null); // Draws background
        
        // Draws puzzle pieces
        piece1.drawPuzzlePiece(g);
        piece2.drawPuzzlePiece(g);
        piece3.drawPuzzlePiece(g);
        
        if(hasWon) {
        	g.drawImage(win.getImage(), 0, 0, win.getIconWidth(), win.getIconHeight(), null); // Draws winning image if player finished the puzzle successfully
        }
    }

	// Unused interface methods
	public void mousePressed(MouseEvent e) {	
	}
	public void mouseReleased(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
}
