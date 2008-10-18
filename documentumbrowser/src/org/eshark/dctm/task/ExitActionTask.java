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

import org.jdesktop.application.Application;
import org.jdesktop.application.Task;

/**
 * <TABLE BORDER="1" WIDTH="100%">
 * <TR>
 * <TH>Perticulars</TH><TH>::</TH><TH>Details</TH>
 * </TR>
 * <TR>
 * <TD>Project Name</TD><TD>::</TD><TD>Query Builder D-SIX</TD>
 * </TR>
 * <TR>
 * <TD>File Name</TD><TD>::</TD><TD>ExitActionTask.java</TD>
 * </TR>
 * <TR>
 * <TD>Created on</TD><TD>::</TD><TD>Sep 22, 2008 11:38:19 AM</TD>
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
public class ExitActionTask extends Task<String, Void>
{

	/**
	 * Construct a LoadTextFileTask.
	 *
	 * @param file the file to load from.
	 */
	public ExitActionTask(Application application)
	{
		super(application);
	}

	/**
	 * Load the file into a String and return it.  The
	 * {@code progress} property is updated as the file is loaded.
	 * <p>
	 * If this task is cancelled before the entire file has been
	 * read, null is returned.
	 *
	 * @return the contents of the {code file} as a String or null
	 */
	@Override
	protected String doInBackground() throws Exception
	{
		//            if(AppSessionManager.getInstance().isActive())
		//            {
		//                ResourceMap resourceMap = QueryBuilderApp.getApplication().getContext().getResourceMap(QueryBuilderApp.class);
		//                int userChoice  =  JOptionPane.showConfirmDialog(null,resourceMap.getString("ExitActionTask.isSessionActive.msg"), resourceMap.getString("Application.msg"),JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
		//                if(userChoice == JOptionPane.OK_OPTION)
		//                    System.exit(1);
		//            }
		System.exit(1);
		return "succeeded";
	}

}
