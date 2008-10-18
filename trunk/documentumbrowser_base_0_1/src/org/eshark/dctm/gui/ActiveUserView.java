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

package org.eshark.dctm.gui;

import com.documentum.fc.common.DfException;
import javax.swing.JTable;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.Task;
import org.eshark.dctm.MainApplication;
import org.eshark.dctm.gui.model.DQLTableModel;
import org.eshark.dctm.gui.model.SorterTableModel;
import org.eshark.dctm.task.ExportTableTask;
import static org.eshark.dctm.gui.MainMDIView.ACT_USER_VIEW;


/**
 * <TABLE BORDER="1" WIDTH="100%">
 * <TR>
 * <TH>Perticulars</TH><TH>::</TH><TH>Details</TH>
 * </TR>
 * <TR>
 * <TD>Project Name</TD><TD>::</TD><TD>Query Builder D-SIX</TD>
 * </TR>
 * <TR>
 * <TD>File Name</TD><TD>::</TD><TD>ActiveUserView.java</TD>
 * </TR>
 * <TR>
 * <TD>Created on</TD><TD>::</TD><TD>Sep 22, 2008 11:32:44 AM</TD>
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
@SuppressWarnings("unchecked")
public class ActiveUserView extends javax.swing.JInternalFrame
{
	private static final long	serialVersionUID	= 3312360584583813842L;

	/** Creates new form ConnectedUserView */
	public ActiveUserView()
	{
		try
		{
			ResourceMap resourceMap = MainApplication.getApplication().getContext().getResourceMap(
					MainApplication.class);
			String lDQL = resourceMap.getString(mMapKey);
			mTableModel = new SorterTableModel();
			initComponents();
			mTableModel.addMouseListenerToHeaderInTable(resultTable);
			//EXECUTE THE QUERY
			DQLTableModel lModel = new DQLTableModel();
			mTableModel.setModel(lModel);
			resultTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			lModel.executeQuery(lDQL);
		}
		catch (DfException DFE)
		{
			consoleTextArea.setText(DFE.getStackTraceAsString());
		}
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents()
	{

		resultConsoleTabbedPane = new javax.swing.JTabbedPane();
		resultPanel = new javax.swing.JPanel();
		resultScroolPane = new javax.swing.JScrollPane();
		resultTable = new javax.swing.JTable();
		consolePanel = new javax.swing.JPanel();
		consoleScrollPane = new javax.swing.JScrollPane();
		consoleTextArea = new javax.swing.JTextArea();
		exportPanel = new javax.swing.JPanel();
		exportButton = new javax.swing.JButton();

		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setResizable(true);
		org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(
				org.eshark.dctm.MainApplication.class).getContext().getResourceMap(ActiveUserView.class);
		setTitle(resourceMap.getString("Form.title")); // NOI18N
		setDoubleBuffered(true);
		setName("Form"); // NOI18N
		addInternalFrameListener(new javax.swing.event.InternalFrameListener()
		{
			public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt)
			{
			}

			public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt)
			{
			}

			public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt)
			{
				closingFrame(evt);
			}

			public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt)
			{
			}

			public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt)
			{
			}

			public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt)
			{
			}

			public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt)
			{
			}
		});

		resultConsoleTabbedPane.setName("resultConsoleTabbedPane"); // NOI18N

		resultPanel.setName("resultPanel"); // NOI18N
		resultPanel.setLayout(new java.awt.BorderLayout());

		resultScroolPane.setName("resultScroolPane"); // NOI18N

		resultTable.setModel(mTableModel);
		resultTable.setName("resultTable"); // NOI18N
		resultScroolPane.setViewportView(resultTable);

		resultPanel.add(resultScroolPane, java.awt.BorderLayout.CENTER);

		resultConsoleTabbedPane.addTab("Result", resultPanel);

		consolePanel.setName("consolePanel"); // NOI18N
		consolePanel.setLayout(new java.awt.BorderLayout());

		consoleScrollPane.setName("consoleScrollPane"); // NOI18N

		consoleTextArea.setColumns(20);
		consoleTextArea.setRows(5);
		consoleTextArea.setName("consoleTextArea"); // NOI18N
		consoleScrollPane.setViewportView(consoleTextArea);

		consolePanel.add(consoleScrollPane, java.awt.BorderLayout.CENTER);

		resultConsoleTabbedPane.addTab("Console", consolePanel);

		getContentPane().add(resultConsoleTabbedPane, java.awt.BorderLayout.CENTER);

		exportPanel.setName("exportPanel"); // NOI18N
		exportPanel.setLayout(new java.awt.BorderLayout());

		javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(
				org.eshark.dctm.MainApplication.class).getContext().getActionMap(ActiveUserView.class, this);
		exportButton.setAction(actionMap.get("exportTable")); // NOI18N
		exportButton.setMnemonic('E');
		exportButton.setText(resourceMap.getString("exportButton.text")); // NOI18N
		exportButton.setToolTipText(resourceMap.getString("exportButton.toolTipText")); // NOI18N
		exportButton.setName("exportButton"); // NOI18N
		exportPanel.add(exportButton, java.awt.BorderLayout.EAST);

		getContentPane().add(exportPanel, java.awt.BorderLayout.SOUTH);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void closingFrame(javax.swing.event.InternalFrameEvent evt)
	{//GEN-FIRST:event_closingFrame
		ACT_USER_VIEW = null;
	}//GEN-LAST:event_closingFrame

	@Action
	public Task exportTable()
	{
		return new ExportTableTask(MainApplication.getApplication(), mTableModel, getTitle());
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JPanel		consolePanel;
	private javax.swing.JScrollPane	consoleScrollPane;
	private javax.swing.JTextArea	consoleTextArea;
	private javax.swing.JButton		exportButton;
	private javax.swing.JPanel		exportPanel;
	private javax.swing.JTabbedPane	resultConsoleTabbedPane;
	private javax.swing.JPanel		resultPanel;
	private javax.swing.JScrollPane	resultScroolPane;
	private javax.swing.JTable		resultTable;
	// End of variables declaration//GEN-END:variables
	private SorterTableModel		mTableModel;
	private String					mMapKey	= "active.users";
}
