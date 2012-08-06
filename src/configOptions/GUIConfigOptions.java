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
package configOptions;

import gui.GUI;

import java.util.ArrayList;

import javab.bling.BGUI.BGUI;
import javab.bling.BGUI.BGUIExitingListener;
import javab.ootil.Ootil;
import javab.ootil.config.ConfigurationOptions;
import javab.ootil.config.LookAndFeelConfigurationOptions;
import javab.ootil.config.ConfigEditor.ConfigReader;
import javab.ootil.config.ConfigEditor.ConfigSection;
import javab.ootil.config.ConfigEditor.ConfigWriter;

import javax.swing.UIManager;

/**
 * A class representing the configuration options for the GUI.
 * 
 * @author Brett Geren
 */
public class GUIConfigOptions extends ConfigurationOptions implements LookAndFeelConfigurationOptions, BGUIExitingListener{
    private String lookAndFeel;
    
    //ConfigOptions key used in the ConfigSection map
    public final static String LOOK_AND_FEEL = "LOOK_AND_FEEL";
    public final static String SHOW_SECONDS = "SHOW_SECONDS";
    public final static String SHOW_MILLIS = "SHOW_MILLIS";
    public final static String SHOW_NOTIFICATIONS = "SHOW_NOTIFICATIONS";    
    public final static String FONT = "FONT";
    public final static String WIDTH = "WINDOW_WIDTH";
    public final static String HEIGHT = "WINDOW_HEIGHT";
    
    //config file section number
    protected final static int CONFIG_FILE_SECTION_NUMBER = 0;

    /**
     * The ONLY GUIConfigOptions that should ever exist
     */
    public final static GUIConfigOptions CONFIG_OPTIONS = new GUIConfigOptions();

    private GUIConfigOptions(){}

    public void initialize(BGUI gui){
	gui.addBGUIExitingListener(this);
	openConfig();
    }
    
    /**
     * It is assumed that this method is called BEFORE any GUI objects are made
     * @return - A ConfigOptions object that contains all the options in the config
     * file. WILL NOT BE NULL, default will be loaded.
     */
    private void openConfig(){
	ConfigReader edit = makeConfigReader();
	ArrayList<ConfigSection> sections = edit.makeArrayOfSections();	
	if(sections == null){
	    configSection = ConfigSection.makeEmptyConfigSection();
	}else if(sections.size() <= CONFIG_FILE_SECTION_NUMBER){
	    configSection = ConfigSection.makeEmptyConfigSection();
	    showGeneralConfigError();
	}else{
	    configSection = sections.get(CONFIG_FILE_SECTION_NUMBER);
	}

	configSectionMap = configSection.getMap();

	parseLookAndFeel();
    }

    private void saveConfig(){
	ConfigWriter edit = makeConfigWriter();

	ArrayList<ConfigSection> sections = new ArrayList<ConfigSection>();
	sections.add(getConfigSection());

	edit.saveConfigSections(sections);
	edit.releaseResources();
    }

    private void parseLookAndFeel(){
	String ret = configSectionMap.get(LOOK_AND_FEEL);
	if(ret != null){
	    Ootil.setLF(ret);
	}

	lookAndFeel = Ootil.removeClassFromClassName(
		UIManager.getLookAndFeel()
		.getClass().toString());
    }
    
    public ConfigSection getConfigSection() {
	configSectionMap.put(LOOK_AND_FEEL, getLookAndFeel());
	
	return configSection;
    }

    protected String getProgramTitle() {
	return GUI.TITLE;
    }

    public String getLookAndFeel() {
	return lookAndFeel == null ?
		Ootil.removeClassFromClassName(
			UIManager.getLookAndFeel()
			.getClass().toString()) : lookAndFeel;
    }

    public void setLookAndFeel(String lookAndFeel) {
	notifyListeners(LOOK_AND_FEEL, this.lookAndFeel, lookAndFeel);
	this.lookAndFeel = lookAndFeel;
    }

    public void BGUIExiting(BGUI gui) {	
	saveConfig();
    }
}
