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

import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfType;
import com.documentum.fc.common.DfException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.tree.DefaultMutableTreeNode;
import org.jdesktop.application.ResourceMap;
import org.eshark.dctm.MainApplication;
import org.eshark.dctm.core.QueryUtil;
import org.eshark.dctm.gui.model.SorterTableModel;
import org.eshark.dctm.mgr.AppSessionManager;
import org.eshark.dctm.gui.tree.nodes.IconNode;
import org.eshark.dctm.gui.tree.renderer.IconNodeRenderer;
import org.eshark.dctm.util.Factory;
import static org.eshark.dctm.gui.MainMDIView.TYPE_TREE_VIEW;


/**
 * <TABLE BORDER="1" WIDTH="100%">
 * <TR>
 * <TH>Perticulars</TH><TH>::</TH><TH>Details</TH>
 * </TR>
 * <TR>
 * <TD>Project Name</TD><TD>::</TD><TD>Query Builder D-SIX</TD>
 * </TR>
 * <TR>
 * <TD>File Name</TD><TD>::</TD><TD>TypeTreeView.java</TD>
 * </TR>
 * <TR>
 * <TD>Created on</TD><TD>::</TD><TD>Sep 22, 2008 11:36:56 AM</TD>
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
public class TypeTreeView extends javax.swing.JInternalFrame
{
	private static final long	serialVersionUID	= -1587139311927257726L;

	/** Creates new form FolderTreeView */
	public TypeTreeView()
	{
		try
		{
			mResourceMap = MainApplication.getApplication().getContext().getResourceMap(MainApplication.class);
			mDQLSubTypes = mResourceMap.getString("list.subtypes");
			AppSessionManager sMgr = AppSessionManager.getInstance();
			IDfSession session = sMgr.getSession();
			mSuperParent = new IconNode("dm_sysobject", true, Factory.ICON_TYPES);
			mNullParent = new IconNode("<Null>", true, Factory.ICON_TYPES);
			createNodeTreeForParent(session, mSuperParent);
			createNodeTreeForNullParent(session, mNullParent);
			initComponents();
			sMgr.releaseSession(session);
			nullTree.setCellRenderer(new IconNodeRenderer());
			nullTree.fireTreeExpanded(null);
			superTypeTree.setCellRenderer(new IconNodeRenderer());
			superTypeTree.fireTreeExpanded(null);
		}
		catch (DfException ex)
		{
			Logger.getLogger(TypeTreeView.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void createNodeTreeForParent(IDfSession aSession, IconNode aParent) throws DfException
	{
		IDfCollection coll = QueryUtil.readQuery(aSession, mDQLSubTypes + "'" + aParent + "'");
		List<IconNode> lList = new ArrayList<IconNode>();
		IconNode tempNode = null;
		String aTempValue = "";
		while (coll.next())
		{
			aTempValue = QueryUtil.getColumnAsString(coll, "name", IDfType.DF_STRING);
			tempNode = new IconNode(aTempValue, true, Factory.ICON_TYPE);
			aParent.add(tempNode);
			lList.add(tempNode);
		}
		QueryUtil.closeCollection(coll);

		for (IconNode currNode : lList)
		{
			// System.out.println(aTempValue = currNode.toString());
			createNodeTreeForParent(aSession, currNode);
		}
	}

	public void createNodeTreeForNullParent(IDfSession aSession, IconNode aParent) throws DfException
	{
		String strQuery = mResourceMap.getString("list.nulltypes");
		IDfCollection coll = QueryUtil.readQuery(aSession, strQuery);
		List<DefaultMutableTreeNode> lList = new ArrayList<DefaultMutableTreeNode>();
		IconNode tempNode = null;
		String aTempValue = "";
		while (coll.next())
		{
			aTempValue = QueryUtil.getColumnAsString(coll, "name", IDfType.DF_STRING);
			tempNode = new IconNode(aTempValue, false, Factory.ICON_TYPE);
			aParent.add(tempNode);
			lList.add(tempNode);
		}
		QueryUtil.closeCollection(coll);
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents()
	{

		windowHorizontalSpliter = new javax.swing.JSplitPane();
		windowVerticalSplitter = new javax.swing.JSplitPane();
		jScrollPane1 = new javax.swing.JScrollPane();
		superTypeTree = new javax.swing.JTree(mSuperParent);
		jScrollPane2 = new javax.swing.JScrollPane();
		nullTree = new javax.swing.JTree(mNullParent);
		resultConsoleTabbedPane3 = new javax.swing.JTabbedPane();
		resultPanel3 = new javax.swing.JPanel();
		resultScroolPane3 = new javax.swing.JScrollPane();
		resultTable3 = new javax.swing.JTable();
		consolePanel = new javax.swing.JPanel();
		consoleScrollPane = new javax.swing.JScrollPane();
		consoleTextArea = new javax.swing.JTextArea();

		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setResizable(true);
		org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(
				org.eshark.dctm.MainApplication.class).getContext().getResourceMap(TypeTreeView.class);
		setTitle(resourceMap.getString("Form.title")); // NOI18N
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

		windowHorizontalSpliter.setDividerLocation(400);
		windowHorizontalSpliter.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
		windowHorizontalSpliter.setLastDividerLocation(400);
		windowHorizontalSpliter.setName("windowHorizontalSpliter"); // NOI18N
		windowHorizontalSpliter.setOneTouchExpandable(true);

		windowVerticalSplitter.setDividerLocation(450);
		windowVerticalSplitter.setDividerSize(10);
		windowVerticalSplitter.setContinuousLayout(true);
		windowVerticalSplitter.setLastDividerLocation(450);
		windowVerticalSplitter.setName("windowVerticalSplitter"); // NOI18N
		windowVerticalSplitter.setOneTouchExpandable(true);

		jScrollPane1.setName("jScrollPane1"); // NOI18N

		superTypeTree.setName("superTypeTree"); // NOI18N
		jScrollPane1.setViewportView(superTypeTree);

		windowVerticalSplitter.setLeftComponent(jScrollPane1);

		jScrollPane2.setName("jScrollPane2"); // NOI18N

		nullTree.setName("nullTree"); // NOI18N
		jScrollPane2.setViewportView(nullTree);

		windowVerticalSplitter.setRightComponent(jScrollPane2);

		windowHorizontalSpliter.setLeftComponent(windowVerticalSplitter);

		resultConsoleTabbedPane3.setName("resultConsoleTabbedPane3"); // NOI18N

		resultPanel3.setName("resultPanel3"); // NOI18N
		resultPanel3.setLayout(new java.awt.BorderLayout());

		resultScroolPane3.setName("resultScroolPane3"); // NOI18N

		resultTable3.setModel(mTableModel);
		resultTable3.setName("resultTable3"); // NOI18N
		resultScroolPane3.setViewportView(resultTable3);

		resultPanel3.add(resultScroolPane3, java.awt.BorderLayout.CENTER);

		resultConsoleTabbedPane3.addTab(resourceMap.getString("resultPanel3.TabConstraints.tabTitle"), resultPanel3); // NOI18N

		consolePanel.setName("consolePanel"); // NOI18N
		consolePanel.setLayout(new java.awt.BorderLayout());

		consoleScrollPane.setName("consoleScrollPane"); // NOI18N

		consoleTextArea.setColumns(20);
		consoleTextArea.setRows(5);
		consoleTextArea.setName("consoleTextArea"); // NOI18N
		consoleScrollPane.setViewportView(consoleTextArea);

		consolePanel.add(consoleScrollPane, java.awt.BorderLayout.CENTER);

		resultConsoleTabbedPane3.addTab(resourceMap.getString("consolePanel.TabConstraints.tabTitle"), consolePanel); // NOI18N

		windowHorizontalSpliter.setRightComponent(resultConsoleTabbedPane3);

		getContentPane().add(windowHorizontalSpliter, java.awt.BorderLayout.CENTER);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void closingFrame(javax.swing.event.InternalFrameEvent evt)
	{//GEN-FIRST:event_closingFrame
		TYPE_TREE_VIEW = null; // TODO add your handling code here:
	}//GEN-LAST:event_closingFrame

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JPanel		consolePanel;
	private javax.swing.JScrollPane	consoleScrollPane;
	private javax.swing.JTextArea	consoleTextArea;
	private javax.swing.JScrollPane	jScrollPane1;
	private javax.swing.JScrollPane	jScrollPane2;
	private javax.swing.JTree		nullTree;
	private javax.swing.JTabbedPane	resultConsoleTabbedPane3;
	private javax.swing.JPanel		resultPanel3;
	private javax.swing.JScrollPane	resultScroolPane3;
	private javax.swing.JTable		resultTable3;
	private javax.swing.JTree		superTypeTree;
	private javax.swing.JSplitPane	windowHorizontalSpliter;
	private javax.swing.JSplitPane	windowVerticalSplitter;
	// End of variables declaration//GEN-END:variables
	private IconNode				mSuperParent;
	private IconNode				mNullParent;
	private ResourceMap				mResourceMap;
	private String					mDQLSubTypes;
	private SorterTableModel		mTableModel	= new SorterTableModel();
}
