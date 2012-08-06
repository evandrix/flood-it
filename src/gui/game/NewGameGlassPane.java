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
import gui.floodit_laf.FloodItButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javab.bling.BButton;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Brett Geren
 *
 */
public class NewGameGlassPane extends JPanel implements ActionListener{
    private GUI gui;
    
    private final static Font FONT = new Font("Tahoma", Font.BOLD, 16);
    
    public NewGameGlassPane(GUI gui, String labelText){
	this.gui = gui;
	
	setOpaque(false);
	setLayout(new GridBagLayout());
	addMouseListener(new MouseConsumer());
	
	makeMain(labelText);
    }
    
    private void makeMain(String labelText){
	JPanel main = new JPanel();
	main.setLayout(new GridBagLayout());
	main.setBackground(Color.WHITE);
	main.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
	
	JPanel holder = new JPanel();
	holder.setLayout(new BoxLayout(holder, BoxLayout.X_AXIS));
	holder.setBackground(Gameboard.GREEN);
	
	JLabel label = new JLabel(labelText);
	label.setForeground(Color.WHITE);
	label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
	label.setFont(FONT);
	
	holder.add(Box.createHorizontalGlue());
	holder.add(label);
	holder.add(Box.createHorizontalGlue());
	
	GridBagConstraints gbc = new GridBagConstraints();
	gbc.fill = GridBagConstraints.BOTH;
	gbc.anchor = GridBagConstraints.CENTER;
	
	gbc.gridx = 0;
	gbc.gridy = 0;
	main.add(holder, gbc);
	
	FloodItButton newGame = new FloodItButton("New Game", this);
	
	gbc.gridx = 0;
	gbc.gridy = 1;
	gbc.insets = new Insets(15, 15, 15, 15);
	main.add(newGame, gbc);
	
	FloodItButton exit = new FloodItButton("Exit", this);
	
	gbc.gridx = 0;
	gbc.gridy = 2;
	gbc.insets = new Insets(0, 15, 15, 15);
	main.add(exit, gbc);
	
	gbc.gridx = 0;
	gbc.gridy = 0;
	gbc.insets = new Insets(0, 0, 0, 0);
	add(main, gbc);
    }

    public void actionPerformed(ActionEvent e) {
	if(e.getActionCommand().equals("newGame")){
	    gui.newGame();
	    setVisible(false);
	}else if(e.getActionCommand().equals("exit")){
	    gui.exit();
	}
    }
    
    private class MouseConsumer extends MouseAdapter {

	public void mouseClicked(MouseEvent e) {
	    e.consume();
	}

	public void mouseEntered(MouseEvent e) {
	    e.consume();
	}

	public void mouseExited(MouseEvent e) {
	    e.consume();
	}

	public void mousePressed(MouseEvent e) {
	    e.consume();
	}

	public void mouseReleased(MouseEvent e) {
	    e.consume();
	}
	
    }
}
