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

import static org.eshark.dctm.util.JXLUtils.XLS_COLOR_BLUE;
import static org.eshark.dctm.util.JXLUtils.XLS_COLOR_NONE;
import static org.eshark.dctm.util.JXLUtils.createWritableCell;
import static org.eshark.dctm.util.JXLUtils.createWritableSheet;
import static org.eshark.dctm.util.JXLUtils.createWritableWorkbook;

import java.io.File;

import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.jdesktop.application.ResourceMap;
import org.eshark.dctm.MainApplication;
import org.eshark.dctm.gui.filter.DynamicFileFilter;
import org.eshark.dctm.util.JXLUtils;

/**
 * <TABLE BORDER="1" WIDTH="100%">
 * <TR>
 * <TH>Perticulars</TH><TH>::</TH><TH>Details</TH>
 * </TR>
 * <TR>
 * <TD>Project Name</TD><TD>::</TD><TD>Query Builder D-SIX</TD>
 * </TR>
 * <TR>
 * <TD>File Name</TD><TD>::</TD><TD>ExportTableTask.java</TD>
 * </TR>
 * <TR>
 * <TD>Created on</TD><TD>::</TD><TD>Sep 22, 2008 11:38:38 AM</TD>
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
public class ExportTableTask extends org.jdesktop.application.Task<String, Void>
{

	private TableModel	mTableToExport	= null;
	private String		mTitle			= "";

	public ExportTableTask(org.jdesktop.application.Application app, TableModel aTableToExport, String aExportTitle)
	{
		super(app);
		mTableToExport = aTableToExport;
		mTitle = aExportTitle;
	}

	@Override
	protected String doInBackground() throws Exception
	{
		ResourceMap resourceMap = MainApplication.getApplication().getContext().getResourceMap(MainApplication.class);
		File lFileToWrite = null;
		JFileChooser lFileChooser = new JFileChooser(new File("."));
		//lFileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
		lFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		lFileChooser.setFileFilter(new DynamicFileFilter("xls", "Microsoft Excel Files"));
		int ret = lFileChooser.showSaveDialog(MainApplication.getApplication().getMainFrame());

		if (ret != JFileChooser.APPROVE_OPTION)
			return "canceled";

		lFileToWrite = lFileChooser.getSelectedFile();
		if (!lFileToWrite.getName().contains("."))
			lFileToWrite = new File(lFileToWrite.getParentFile(), lFileToWrite.getName() + ".xls");
		if (!lFileToWrite.getName().endsWith("xls"))
			throw new Exception(resourceMap.getString("ExportTableTask.invalidfile"));
		if (mTableToExport != null)
		{
			//CREATE THE XLS FILE
			WritableWorkbook lWritableWorkbook = createWritableWorkbook(lFileToWrite);
			WritableSheet lWritableSheet = createWritableSheet(lWritableWorkbook, "Page 1", 1);

			int lColCount = mTableToExport.getColumnCount();
			createWritableCell(lWritableSheet, mTitle, 2, 0, JXLUtils.XLS_COLOR_YELLOW);
			createWritableCell(lWritableSheet, "Column Names", 1, 1, JXLUtils.XLS_COLOR_YELLOW);

			for (int i = 0; i < lColCount; i++)
			{
				createWritableCell(lWritableSheet, mTableToExport.getColumnName(i), 2 + i, 1, JXLUtils.XLS_COLOR_YELLOW);
			}

			int lRowCount = mTableToExport.getRowCount();
			for (int row = 0; row < lRowCount; row++)
			{
				createWritableCell(lWritableSheet, row + 1 + "", 1, 2 + row, JXLUtils.XLS_COLOR_YELLOW);
				for (int col = 0; col < lColCount; col++)
				{
					createWritableCell(lWritableSheet, mTableToExport.getValueAt(row, col) + "", 2 + col, 2 + row,
							XLS_COLOR_NONE, XLS_COLOR_BLUE);
				}
			}
			lWritableWorkbook.write();
			lWritableWorkbook.close();
		}
		else
			throw new Exception(resourceMap.getString("ExportTableTask.invalidModel"));

		mTableToExport = null;
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
