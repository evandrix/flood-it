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

import java.awt.Color;

/**
 * @author Brett Geren
 *
 */
public class Gameboard {
    private int width, height, maxSteps;
    private Color[] squares;
    
    public static final int EASY = 30;
    public static final int MEDIUM = 25;
    public static final int HARD = 22;
    public static final int INSANE = 20;
    
    public static final Color PURPLE = new Color(99, 95, 170);
    public static final Color BLUE = new Color(70, 177, 226);
    public static final Color GREEN = new Color(126, 157, 30);
    public static final Color YELLOW = new Color(243, 246, 29);
    public static final Color RED = new Color(220, 74, 32);
    public static final Color PINK = new Color(237, 112, 161);
    public static final int NUM_OF_COLORS = 6;

    public Gameboard(int width, int height, int difficulty){
	this.width = width;
	this.height = height;
	this.maxSteps = difficulty;
	this.squares = makeSquares();
    }

    private Color[] makeSquares(){
	Color[] squares = new Color[width * height];

	for(int i = 0; i < squares.length; i++){
	    int color = (int) (Math.random() * NUM_OF_COLORS);
	    squares[i] = convertIntToColor(color);
	}

	return squares;
    }

    public int getWidth() {
	return width;
    }

    public int getHeight() {
	return height;
    }

    public int getNumberOfSquares(){
	return squares.length;
    }
    
    public Color getSquareColor(int x, int y){
	int index = convertPointToIndex(x, y);
	return getSquareColor(index);
    }

    public Color getSquareColor(int index){
	return squares[index];
    }
    
    public int getMaxSteps() {
        return maxSteps;
    }
    
    public void setSquareColor(int x, int y, Color color){
	int index = convertPointToIndex(x, y);
	setSquareColor(index, color);
    }
    
    public void setSquareColor(int index, Color color){
	checkColor(color);

	squares[index] = color;
    }
    
    private void checkColor(Color color){
	if(color != PURPLE && 
		color != BLUE &&
		color != GREEN &&
		color != YELLOW &&
		color != RED &&
		color != PINK){
	    throw new IllegalStateException("Invalid Color: " + color);
	}
    }

    public int convertPointToIndex(int x, int y){
	return y * width + x;
    }

    private Color convertIntToColor(int i){
	switch(i){
	case 0:
	    return PURPLE;
	case 1:
	    return BLUE;
	case 2:
	    return GREEN;
	case 3:
	    return YELLOW;
	case 4:
	    return RED;
	case 5:
	    return PINK;
	default:
	    throw new IllegalStateException();
	}
    }
}
