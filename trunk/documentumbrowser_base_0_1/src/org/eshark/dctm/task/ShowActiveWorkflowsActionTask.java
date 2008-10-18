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

package org.eshark.dctm.task;

import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import org.jdesktop.application.ResourceMap;
import org.eshark.dctm.MainApplication;
import org.eshark.dctm.gui.ActiveWorkflowsView;
import static org.eshark.dctm.gui.MainMDIView.ACT_WORK_VIEW;


/**
 * <TABLE BORDER="1" WIDTH="100%">
 * <TR>
 * <TH>Perticulars</TH><TH>::</TH><TH>Details</TH>
 * </TR>
 * <TR>
 * <TD>Project Name</TD><TD>::</TD><TD>Query Builder D-SIX</TD>
 * </TR>
 * <TR>
 * <TD>File Name</TD><TD>::</TD><TD>ShowActiveWorkflowsActionTask.java</TD>
 * </TR>
 * <TR>
 * <TD>Created on</TD><TD>::</TD><TD>Sep 22, 2008 11:39:25 AM</TD>
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
public class ShowActiveWorkflowsActionTask extends org.jdesktop.application.Task<String, Void>
{
	public ShowActiveWorkflowsActionTask(org.jdesktop.application.Application aApplication)
	{
		super(aApplication);
	}

	@Override
	protected String doInBackground() throws Exception
	{
		JDesktopPane desktop = MainApplication.getApplication().getDesktopPane();
		if (ACT_WORK_VIEW == null)
		{
			ACT_WORK_VIEW = new ActiveWorkflowsView();
			desktop.add(ACT_WORK_VIEW);
			ACT_WORK_VIEW.pack();
			ACT_WORK_VIEW.show();
			ACT_WORK_VIEW.setSelected(true);
			ACT_WORK_VIEW.validate();
		}
		return "succeeded"; // return your result
	}

	@Override
	protected void failed(Throwable aException)
	{
		JDesktopPane desktop = MainApplication.getApplication().getDesktopPane();
		ResourceMap resourceMap = MainApplication.getApplication().getContext().getResourceMap(MainApplication.class);
		JOptionPane.showInternalMessageDialog(desktop, aException.getMessage(),
				resourceMap.getString("Applicaion.err"), JOptionPane.ERROR_MESSAGE);
	}
}
