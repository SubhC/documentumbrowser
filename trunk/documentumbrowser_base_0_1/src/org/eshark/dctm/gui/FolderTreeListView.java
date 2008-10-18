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

import static org.eshark.dctm.gui.MainMDIView.TREE_DUMP_VIEW;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.tree.DefaultTreeCellRenderer;

import org.eshark.dctm.gui.model.SorterTableModel;
import org.eshark.dctm.gui.tree.factory.CustomDocumentumNodeFactoryImpl;
import org.eshark.dctm.gui.tree.model.CustomDocumentumTreeModel;
import org.eshark.dctm.mgr.AppSessionManager;

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
 * <TD>File Name</TD><TD>::</TD><TD>FolderTreeListView.java</TD>
 * </TR>
 * <TR>
 * <TD>Created on</TD><TD>::</TD><TD>Sep 22, 2008 11:34:51 AM</TD>
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
public class FolderTreeListView extends javax.swing.JInternalFrame
{
	private static final long	serialVersionUID	= 8602365512605325968L;

	/** Creates new form FolderTreeView */
	public FolderTreeListView()
	{
		try
		{
			CustomDocumentumNodeFactoryImpl.SHOW_DOCUMENTS = true;
			CustomDocumentumTreeModel model = new CustomDocumentumTreeModel(AppSessionManager.getInstance()
					.getDocbase());
			mTableModel = new SorterTableModel();
			initComponents();
			docbaseTree.setModel(model);
			//docbaseTree.addTreeSelectionListener(new CustomTreeSelectionDumpListener());
			DefaultTreeCellRenderer lRenderer = (DefaultTreeCellRenderer) docbaseTree.getCellRenderer();
			lRenderer.setLeafIcon(CustomDocumentumNodeFactoryImpl.DOCUMENT_ICON);
			docbaseTree.fireTreeCollapsed(null);
			//FOR THE TABLE
			mTableModel.addMouseListenerToHeaderInTable(resultTable);
		}
		catch (DfException ex)
		{
			Logger.getLogger(FolderTreeListView.class.getName()).log(Level.SEVERE, null, ex);
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

		windowSplitter = new javax.swing.JSplitPane();
		treeScrollPane = new javax.swing.JScrollPane();
		docbaseTree = new javax.swing.JTree();
		listPanel = new javax.swing.JPanel();
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

		windowSplitter.setDividerLocation(200);
		windowSplitter.setDividerSize(10);
		windowSplitter.setContinuousLayout(true);
		windowSplitter.setName("windowSplitter"); // NOI18N
		windowSplitter.setOneTouchExpandable(true);

		treeScrollPane.setName("treeScrollPane"); // NOI18N

		docbaseTree.setName("docbaseTree"); // NOI18N
		treeScrollPane.setViewportView(docbaseTree);

		windowSplitter.setLeftComponent(treeScrollPane);

		listPanel.setName("listPanel"); // NOI18N
		listPanel.setLayout(new java.awt.BorderLayout());

		resultConsoleTabbedPane.setName("resultConsoleTabbedPane"); // NOI18N

		resultPanel.setName("resultPanel"); // NOI18N
		resultPanel.setLayout(new java.awt.BorderLayout());

		resultScroolPane.setName("resultScroolPane"); // NOI18N

		resultTable.setModel(mTableModel);
		resultTable.setName("resultTable"); // NOI18N
		resultScroolPane.setViewportView(resultTable);

		resultPanel.add(resultScroolPane, java.awt.BorderLayout.CENTER);

		org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(
				org.eshark.dctm.MainApplication.class).getContext().getResourceMap(FolderTreeListView.class);
		resultConsoleTabbedPane.addTab(resourceMap.getString("resultPanel.TabConstraints.tabTitle"), resultPanel); // NOI18N

		consolePanel.setName("consolePanel"); // NOI18N
		consolePanel.setLayout(new java.awt.BorderLayout());

		consoleScrollPane.setName("consoleScrollPane"); // NOI18N

		consoleTextArea.setColumns(20);
		consoleTextArea.setRows(5);
		consoleTextArea.setName("consoleTextArea"); // NOI18N
		consoleScrollPane.setViewportView(consoleTextArea);

		consolePanel.add(consoleScrollPane, java.awt.BorderLayout.CENTER);

		resultConsoleTabbedPane.addTab(resourceMap.getString("consolePanel.TabConstraints.tabTitle"), consolePanel); // NOI18N

		listPanel.add(resultConsoleTabbedPane, java.awt.BorderLayout.CENTER);

		exportPanel.setName("exportPanel"); // NOI18N
		exportPanel.setLayout(new java.awt.BorderLayout());

		exportButton.setMnemonic('E');
		exportButton.setText(resourceMap.getString("exportButton.text")); // NOI18N
		exportButton.setToolTipText(resourceMap.getString("exportButton.toolTipText")); // NOI18N
		exportButton.setName("exportButton"); // NOI18N
		exportPanel.add(exportButton, java.awt.BorderLayout.EAST);

		listPanel.add(exportPanel, java.awt.BorderLayout.SOUTH);

		windowSplitter.setRightComponent(listPanel);

		getContentPane().add(windowSplitter, java.awt.BorderLayout.CENTER);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void closingFrame(javax.swing.event.InternalFrameEvent evt)
	{//GEN-FIRST:event_closingFrame
		TREE_DUMP_VIEW = null;
	}//GEN-LAST:event_closingFrame

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JPanel		consolePanel;
	private javax.swing.JScrollPane	consoleScrollPane;
	private javax.swing.JTextArea	consoleTextArea;
	private javax.swing.JTree		docbaseTree;
	private javax.swing.JButton		exportButton;
	private javax.swing.JPanel		exportPanel;
	private javax.swing.JPanel		listPanel;
	private javax.swing.JTabbedPane	resultConsoleTabbedPane;
	private javax.swing.JPanel		resultPanel;
	private javax.swing.JScrollPane	resultScroolPane;
	private javax.swing.JTable		resultTable;
	private javax.swing.JScrollPane	treeScrollPane;
	private javax.swing.JSplitPane	windowSplitter;
	// End of variables declaration//GEN-END:variables
	private SorterTableModel		mTableModel;
}
