/*******************************************************************************
 * Copyright (c) 2009, Brett Geren
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 * 
 *   * Redistributions of source code must retain the above copyright notice, 
 *   		this list of conditions and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above copyright notice, 
 *   		this list of conditions and the following disclaimer in the 
 *   		documentation and/or other materials provided with the 
 *   		distribution.
 *   * Neither the name of the Brett Geren nor the names of its contributors may
 *   		be used to endorse or promote products derived from this 
 *      	software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS 
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT 
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS 
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT 
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED 
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF 
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT 
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE 
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 ******************************************************************************/
package game;

import gui.GUI;
import gui.game.ColorButtonPanel;
import gui.game.ColorButtonPanelListener;
import gui.game.NewGameGlassPane;

import java.awt.Color;
import java.awt.Point;
import java.util.HashSet;

/**
 * @author Brett Geren
 *
 */
public class GameManager implements ColorButtonPanelListener{
    private GUI gui;
    private Gameboard board;
    private int steps;
    
    public GameManager(GUI gui, ColorButtonPanel buttons){	
	this.gui = gui;
	
	buttons.addColorButtonPanelListener(this);
    }

    public void colorButtonPressed(Color c) {
	makeColorChange(c);
    }

    public void newGame(Gameboard board){
	this.steps = 0;
	this.board = board;
    }

    private void makeColorChange(Color c){
	steps++;
	
	HashSet<Point> visited = new HashSet<Point>();
	recur(new Point(0, 0), board.getSquareColor(0, 0), c, visited);
	
	if(didWin()){
	    win();
	}else if(steps == board.getMaxSteps()){
	    loss();
	}
    }

    private boolean didWin(){
	Color c = board.getSquareColor(0);
	for(int i = 1; i < board.getNumberOfSquares(); i++){
	    if(board.getSquareColor(i) != c){
		return false;
	    }
	}
	
	return true;
    }
    
    private void win(){
	gui.setGlassPane(new NewGameGlassPane(gui, "You Won!"));
	gui.getGlassPane().setVisible(true);
    }
    
    private void loss(){
	gui.setGlassPane(new NewGameGlassPane(gui, "You Lost!"));
	gui.getGlassPane().setVisible(true);
    }
    
    private void recur(
	    Point p,  Color curColor, 
	    Color newColor, HashSet<Point> visited){
	visited.add(p);
	if(board.getSquareColor(p.x, p.y) != curColor){
	    return;
	}else{
	    board.setSquareColor(p.x, p.y, newColor);
	}

	Point north = new Point(p.x, p.y - 1);
	if(north.y >= 0 && !visited.contains(north)){
	    recur(north, curColor, newColor, visited);
	}
	
	Point west = new Point(p.x - 1, p.y);
	if(west.x >= 0 && !visited.contains(west)){
	    recur(west, curColor, newColor, visited);
	}

	Point east = new Point(p.x + 1, p.y);
	if(east.x < board.getWidth() && !visited.contains(east)){
	    recur(east, curColor, newColor, visited);
	}

	Point south = new Point(p.x, p.y + 1);
	if(south.y < board.getHeight() && !visited.contains(south)){
	    recur(south, curColor, newColor, visited);
	}
    }
}
