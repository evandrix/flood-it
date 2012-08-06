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
package gui;

import game.GameManager;
import game.Gameboard;
import gui.floodit_laf.CurvedPanel;
import gui.floodit_laf.GradientPanel;
import gui.game.ColorButtonPanel;
import gui.game.GamePanel;
import gui.game.StepLabelPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javab.bling.BMenuItem;
import javab.bling.BGUI.BGUI;
import javab.ootil.Ootil;
import javab.ootil.config.LookAndFeelConfigurationOptions;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ant.BuildInfo;
import configOptions.GUIConfigOptions;

/**
 * The base JFrame Notecards runs in
 * 
 * @author Brett Geren
 */
public class GUI extends BGUI{
    private GamePanel panel;
    private ColorButtonPanel buttonPanel;
    private GameManager manager;
    private StepLabelPanel stepPanel;
    
    public final static File ICON_LOCATION = new File("Images/floodit.ico");
    public final static BufferedImage ICON = Ootil.createImage("floodit.png");
    public final static String TITLE = "Flood It";
    public final static String LICENSE_NAME = "openBSD";

    public static final int VERSION_MAJOR = 1;
    public static final int VERSION_MINOR = 0;
    public static final int VERSION_REVISION = 0;

    public static final boolean IS_PRE_RELEASE_VERSION  = true;
    public static final String PRE_RELEASE_ID = "alpha";

    public GUI(){
	GUIConfigOptions.CONFIG_OPTIONS.initialize(this);
	
	setLayout(new BorderLayout());
	makeGUI();
	
	manager = new GameManager(this, buttonPanel);
	
	newGame();
	pack();
	setLocationRelativeTo(null);
	setVisible(true);
    }

    private void makeGUI(){
	buttonPanel = new ColorButtonPanel();
	panel = new GamePanel(buttonPanel);
	stepPanel = new StepLabelPanel(this, buttonPanel);

	GradientPanel mainHolder = new GradientPanel(new Color(195, 195, 195), new Color(217, 217, 217));
	mainHolder.setLayout(new BorderLayout());
	mainHolder.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
	
	CurvedPanel centerPanel = new CurvedPanel();
	centerPanel.setLayout(new BorderLayout());
	centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

	centerPanel.add(stepPanel, BorderLayout.NORTH);
	centerPanel.add(panel, BorderLayout.CENTER);

	mainHolder.add(centerPanel, BorderLayout.CENTER);
	
	GradientPanel botHolder = new GradientPanel(new Color(217, 217, 217), new Color(123, 123, 123));
	botHolder.setLayout(new BorderLayout());
	botHolder.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
	
	botHolder.add(buttonPanel, BorderLayout.SOUTH);
	
	add(mainHolder, BorderLayout.CENTER);
	add(botHolder, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
	if(e.getActionCommand().equals("newGame")) newGame();
	else if(e.getActionCommand().equals("exit")) exit();
	else super.actionPerformed(e);
    }

    public void newGame(){
	Gameboard board = new Gameboard(12, 12, Gameboard.MEDIUM);
	panel.newGame(board);
	stepPanel.newGame(board);
	manager.newGame(board);
    }

    public void exit() {
	super.exit();
	System.exit(0);
    }

    public String getLicenseInformation(){
	return "<br> Copyright (c) 2009, Brett Geren" +
	"<br>All rights reserved." + 
	"<br>" +
	"<br>Redistribution and use in source and binary forms, " +
	"<br>with or without modification, are permitted " +
	"<br>provided that the following conditions are met:" +
	"<br>" +
	"<ul>" +
	"<br>   <li> Redistributions of source code must retain " +
	"<br>	     the above copyright notice, " +
	"<br>        this list of conditions and the following " +
	"<br>        disclaimer." +
	"<br>   <li> Redistributions in binary form " +
	"<br>        must reproduce the above copyright notice, " +
	"<br>        this list of conditions and the following " +
	"<br>        disclaimer in the documentation " +
	"<br>        and/or other materials provided with " +
	"<br>        the distribution." +
	"<br>   <li> Neither the name of the Brett Geren nor the " +
	"<br>        names of its contributors may be " +
	"<br>        used to endorse or promote products derived " +
	"<br>        from this software without " +
	"<br>        specific prior written permission." +
	"</ul>" +
	"<br>" +
	"<br>THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT " +
	"<br>HOLDERS AND CONTRIBUTORS \"AS IS\" AND ANY " +
	"<br>EXPRESS OR IMPLIED WARRANTIES, INCLUDING, " +
	"<br>BUT NOT LIMITED TO, THE IMPLIED WARRANTIES " +
	"<br>OF MERCHANTABILITY AND FITNESS FOR A " +
	"<br>PARTICULAR PURPOSE ARE DISCLAIMED. IN NO " +
	"<br>EVENT SHALL THE COPYRIGHT HOLDER OR " +
	"<br>CONTRIBUTORS BE LIABLE FOR ANY DIRECT, " +
	"<br>INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR " +
	"<br>CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT " +
	"<br>LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS " +
	"<br>OR SERVICES; LOSS OF USE, DATA, OR PROFITS; " +
	"<br>OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND " +
	"<br>ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, " +
	"<br>STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE " +
	"<br>OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE " +
	"<br>OF THIS SOFTWARE, EVEN IF ADVISED OF THE " +
	"<br>POSSIBILITY OF SUCH DAMAGE.";
    }

    public BufferedImage getIcon() {
	return ICON;
    }

    public String getProgramTitle() {
	return TITLE;
    }

    public LookAndFeelConfigurationOptions getConfigOptions() {
	return GUIConfigOptions.CONFIG_OPTIONS;
    }

    public void help() {
	showFeatureNotAddedMessage(this);
    }

    public int getVersionMajor(){
	return VERSION_MAJOR;
    }

    public int getVersionMinor(){
	return VERSION_MINOR;
    }

    public int getVersionRevision(){
	return VERSION_REVISION;
    }

    public boolean isPreRelease(){
	return IS_PRE_RELEASE_VERSION;
    }

    public String getPreReleaseID(){
	if(!isPreRelease()){
	    return null;
	}else{
	    return PRE_RELEASE_ID;
	}
    }

    public String getBuildNumber() {
	return BuildInfo.BUILD_NUMBER;
    }

    public boolean supportsBuildNumber() {
	return true;
    }

    public static void main(String[] args){	
	SwingUtilities.invokeLater(new Runnable(){
	    public void run() {
		new GUI();
	    }
	});
    }
}
