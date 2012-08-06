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
package gui.game;

import game.Gameboard;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javab.bling.BImageButton;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * @author Brett Geren
 *
 */
public class ColorButtonPanel extends JPanel implements ActionListener{
    private HashSet<ColorButtonPanelListener> listeners;

    public ColorButtonPanel(){
	listeners = new HashSet<ColorButtonPanelListener>();

	setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
	setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
	setOpaque(false);
	makeMain();
    }

    private void makeMain(){
	BImageButton purple = new BImageButton("purple", "purple", this);
	BImageButton blue = new BImageButton("blue", "blue", this);
	BImageButton green = new BImageButton("green", "green", this);
	BImageButton yellow = new BImageButton("yellow", "yellow", this);
	BImageButton red = new BImageButton("red", "red", this);
	BImageButton pink = new BImageButton("pink", "pink", this);

	add(purple);
	add(blue);
	add(green);
	add(yellow);
	add(red);
	add(pink);
    }

    public void addColorButtonPanelListener(ColorButtonPanelListener listener){
	listeners.add(listener);
    }

    public void actionPerformed(ActionEvent e) {
	Color c = null;
	if(e.getActionCommand().equals("purple")){
	    c = Gameboard.PURPLE;
	}else if(e.getActionCommand().equals("blue")){
	    c = Gameboard.BLUE;
	}else if(e.getActionCommand().equals("green")){
	    c = Gameboard.GREEN;
	}else if(e.getActionCommand().equals("yellow")){
	    c = Gameboard.YELLOW;
	}else if(e.getActionCommand().equals("red")){
	    c = Gameboard.RED;
	}else if(e.getActionCommand().equals("pink")){
	    c = Gameboard.PINK;
	}else{
	    throw new IllegalStateException("Unknown Color");
	}

	notifyListeners(c);
    }

    private void notifyListeners(Color c){
	for(ColorButtonPanelListener l: listeners){
	    l.colorButtonPressed(c);
	}
    }
}
