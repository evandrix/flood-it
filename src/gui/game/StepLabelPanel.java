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
import gui.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.Map;

import javab.bling.BImageButton;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Brett Geren
 *
 */
public class StepLabelPanel extends JPanel implements ColorButtonPanelListener{
    private JLabel countLabel;
    private int count;
    private Gameboard board;
    
    private final static Font LABEL_FONT = makeLabelFont();
    
    private final static String STEPS_TEXT = "Step ";
    private final static String STEPS_DIVIER = " / ";
    
    public StepLabelPanel(GUI gui, ColorButtonPanel panel){
	setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
	setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
	setOpaque(false);
	
	makeMain(gui);
	panel.addColorButtonPanelListener(this);
    }

    @SuppressWarnings("unchecked")
    private static Font makeLabelFont(){
	//Arial kind of works as well
	Font f = new Font("Tahoma", Font.BOLD, 16);
	Color c = new Color(73, 73, 73);

	Map<TextAttribute, Object> map = (Map<TextAttribute, Object>) f.getAttributes();
	map.put(TextAttribute.FOREGROUND, c);

	return new Font(map);
    }

    private void makeMain(GUI gui){
	countLabel = new JLabel("0 / 22");
	countLabel.setFont(LABEL_FONT);

	BImageButton button = new BImageButton("about", "About", gui);
	button.setBorder(BorderFactory.createEmptyBorder());
	button.setAlignmentY(Component.CENTER_ALIGNMENT);    
	
	add(countLabel);
	add(Box.createHorizontalGlue());
	add(button);
    }

    public void colorButtonPressed(Color c) {
	count++;
	
	fixLabel();
    }

    public void newGame(Gameboard board){
	this.board = board;
	this.count = 0;
	
	fixLabel();
    }

    private void fixLabel(){
	countLabel.setText(STEPS_TEXT + count + 
		STEPS_DIVIER + board.getMaxSteps());
    }
}
