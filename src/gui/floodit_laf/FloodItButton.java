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
package gui.floodit_laf;

import game.Gameboard;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;

import javax.swing.plaf.basic.BasicButtonUI;

import javab.bling.BButton;

/**
 * @author Brett Geren
 *
 */
public class FloodItButton extends BButton{

    private final static int HOVER_SPACE_AMOUNT = 1;
    
    public FloodItButton(String text, ActionListener listener) {
	super(text, listener);
	setUI(new BasicButtonUI());
	setBackground(Gameboard.BLUE);
	setForeground(Color.WHITE);
    }

    protected void paintComponent(Graphics g) {
	g.setColor(Gameboard.BLUE);
	g.fillRect(0, 0, getWidth(), getHeight());
	super.paintComponent(g);
	if(getModel().isRollover()){
	    g.setColor(Color.WHITE);
	    //I don't know why the -1 is needed >.>
	    g.drawRect(HOVER_SPACE_AMOUNT, HOVER_SPACE_AMOUNT,
		    getWidth() - HOVER_SPACE_AMOUNT * 2 - 1,
		    getHeight() - HOVER_SPACE_AMOUNT * 2 - 1);
	}
    }
}
