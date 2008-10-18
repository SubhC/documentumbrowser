/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.eshark.dctm;

import javax.swing.JDesktopPane;
import org.eshark.dctm.gui.MainMDIView;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;


/**
 * <TABLE BORDER="1" WIDTH="100%">
 * <TR>
 * <TH>Perticulars</TH><TH>::</TH><TH>Details</TH>
 * </TR>
 * <TR>
 * <TD>Project Name</TD><TD>::</TD><TD>Query Builder D-SIX</TD>
 * </TR>
 * <TR>
 * <TD>File Name</TD><TD>::</TD><TD>MainApplication.java</TD>
 * </TR>
 * <TR>
 * <TD>Created on</TD><TD>::</TD><TD>Sep 22, 2008 11:51:17 AM</TD>
 * </TR>
 * <TR>
 * <TD>@author</TD><TD>::</TD><TD><a href="mailto:subhasish@gmail.com">Subhasish Chattopadhyay</a></TD>
 * </TR>
 * <TR>
 * <TD>Purpose</TD><TD>::</TD><TD></TD>
 * </TR>
 * <TR>
 * <TD COLSPAN=3></TD>
 * </TR>
 * </TABLE>
 *
 * <TABLE BORDER="1" WIDTH="100%">
 * <CAPTION>File Change History</CAPTION>
 * <TR>
 * <TH>Date</TH><TH>Description</TH>                    |
 * </TR>
 * <TR>
 * <TD>Sep 22, 2008</TD><TD>Created</TD>
 * </TR>
 * </TABLE>
 */
public class MainApplication extends SingleFrameApplication
{
	private JDesktopPane	mDesktopPane	= null;

	/**
	 * At startup create and show the main frame of the application.
	 */
	@Override
	protected void startup()
	{
		show(new MainMDIView(this));
	}

	/**
	 * This method is to initialize the specified window by injecting resources.
	 * Windows shown in our application come fully initialized from the GUI
	 * builder, so this additional configuration is not needed.
	 */
	@Override
	protected void configureWindow(java.awt.Window root)
	{
	}

	/**
	 * A convenient static getter for the application instance.
	 * @return the instance of QueryBuilderApp
	 */
	public static MainApplication getApplication()
	{
		return Application.getInstance(MainApplication.class);
	}

	/**
	 * Main method launching the application.
	 */
	public static void main(String[] args)
	{
		launch(MainApplication.class, args);
	}

	public void setDesktopPane(JDesktopPane aDesktopPane)
	{
		this.mDesktopPane = aDesktopPane;
	}

	public JDesktopPane getDesktopPane()
	{
		return mDesktopPane;
	}

}
