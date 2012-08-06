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
package ant;

import gui.GUI;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;


/**
 * @author Brett Geren
 *
 * This class is used by the Ant build to set Ant properties 
 * that are needed.
 */
public class PropertiesTask extends Task{
    public final static String TITLE_PROPERTY = "program.name";
    public final static String ICON_LOCATION_PROPERTY = "program.icon";
    public static final String LINCENSE_NAME_PROPERTY  = "program.license.name";
    public static final String VERSION_MAJOR_PROPERTY  = "program.version.major";
    public static final String VERSION_MINOR_PROPERTY  = "program.version.minor";
    public static final String VERSION_REVISION_PROPERTY  = "program.version.revision";
    public static final String IS_PRE_RELEASE_VERSION  = "program.is.pre.release";
    public static final String PRE_RELEASE_ID = "program.pre.release.id";
    
    /**
     * Called by the project to let the task do its work. This method may be
     * called more than once, if the task is invoked more than once.
     *
     * @exception BuildException if something goes wrong with the build
     */
    public void execute()
        throws BuildException
    {
	getProject().setProperty(TITLE_PROPERTY, GUI.TITLE);
	getProject().setProperty(ICON_LOCATION_PROPERTY, GUI.ICON_LOCATION.getAbsolutePath());
        getProject().setProperty(VERSION_MAJOR_PROPERTY, "" + GUI.VERSION_MAJOR);
        getProject().setProperty(VERSION_MINOR_PROPERTY, "" + GUI.VERSION_MINOR);
        getProject().setProperty(VERSION_REVISION_PROPERTY, "" + GUI.VERSION_REVISION);
        getProject().setProperty(IS_PRE_RELEASE_VERSION, "" + GUI.IS_PRE_RELEASE_VERSION);
        getProject().setProperty(PRE_RELEASE_ID, "" + GUI.PRE_RELEASE_ID);
        getProject().setProperty(LINCENSE_NAME_PROPERTY, GUI.LICENSE_NAME);
    }
}
