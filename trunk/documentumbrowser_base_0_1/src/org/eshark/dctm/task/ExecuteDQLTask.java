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

import static org.eshark.dctm.gui.DQLQueryView.QUERY_CURR_INDX;
import static org.eshark.dctm.gui.DQLQueryView.QUERY_LIMIT;
import static org.eshark.dctm.gui.DQLQueryView.QUERY_LIST;

import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import org.apache.commons.lang.StringUtils;
import org.jdesktop.application.Application;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.Task;
import org.eshark.dctm.MainApplication;
import org.eshark.dctm.gui.model.DQLTableModel;
import org.eshark.dctm.gui.model.SorterTableModel;

import com.documentum.fc.common.DfException;

/**
 * <TABLE BORDER="1" WIDTH="100%">
 * <TR>
 * <TH>Perticulars</TH><TH>::</TH><TH>Details</TH>
 * </TR>
 * <TR>
 * <TD>Project Name</TD><TD>::</TD><TD>Query Builder D-SIX</TD>
 * </TR>
 * <TR>
 * <TD>File Name</TD><TD>::</TD><TD>ExecuteDQLTask.java</TD>
 * </TR>
 * <TR>
 * <TD>Created on</TD><TD>::</TD><TD>Sep 22, 2008 11:38:06 AM</TD>
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
public class ExecuteDQLTask extends Task<String, Void>
{
	private String	mDQL	= "";
	JTable			mTable;

	/**
	 * Construct a LoadTextFileTask.
	 *
	 * @param file the file to load from.
	 */
	public ExecuteDQLTask(Application application, String aDQL, JTable aTable)
	{
		super(application);
		mTable = aTable;
		mDQL = aDQL;
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
	protected String doInBackground() throws DfException, PropertyVetoException
	{
		JDesktopPane desktop = MainApplication.getApplication().getDesktopPane();
		ResourceMap resourceMap = MainApplication.getApplication().getContext().getResourceMap(MainApplication.class);
		if (StringUtils.isEmpty(mDQL))
		{
			JOptionPane.showInternalMessageDialog(desktop, resourceMap.getString("ExecuteDQLTask.invalidQuery"),
					resourceMap.getString("Applicaion.err"), JOptionPane.ERROR_MESSAGE);
			return "failed";
		}
		//EXECUTE THE QUERY
		DQLTableModel lModel = new DQLTableModel();
		SorterTableModel lSModel = (SorterTableModel) mTable.getModel();
		lSModel.setModel(lModel);
		mTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		lModel.executeQuery(mDQL);

		//HISTORY HANDLING
		if (QUERY_LIST.size() == QUERY_LIMIT)
		{
			//remove the first element from the Query History
			QUERY_LIST.remove(0);
			// add the current query at the end
			QUERY_LIST.add(mDQL);
			//System.out.println("Current Index (Delete): " + currentQueryIndx);
		}
		else
		{
			QUERY_LIST.add(mDQL);
		}
		QUERY_CURR_INDX = QUERY_LIST.size() - 1;
		return "succeeded"; // return your result
	}

	@Override
	protected void failed(Throwable aException)
	{
		aException.printStackTrace();
		JDesktopPane desktop = MainApplication.getApplication().getDesktopPane();
		ResourceMap resourceMap = MainApplication.getApplication().getContext().getResourceMap(MainApplication.class);
		JOptionPane.showInternalMessageDialog(desktop, aException.getMessage(),
				resourceMap.getString("Applicaion.err"), JOptionPane.ERROR_MESSAGE);
	}

}
