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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.tree.DefaultTreeCellRenderer;
import org.eshark.dctm.gui.tree.factory.CustomDocumentumNodeFactoryImpl;
import org.eshark.dctm.gui.tree.listeners.CustomTreeSelectionDumpListener;
import org.eshark.dctm.gui.tree.model.CustomDocumentumTreeModel;
import org.eshark.dctm.mgr.AppSessionManager;
import static org.eshark.dctm.gui.MainMDIView.TREE_DUMP_VIEW;

/**
 * <TABLE BORDER="1" WIDTH="100%">
 * <TR>
 * <TH>Perticulars</TH><TH>::</TH><TH>Details</TH>
 * </TR>
 * <TR>
 * <TD>Project Name</TD><TD>::</TD><TD>Query Builder D-SIX</TD>
 * </TR>
 * <TR>
 * <TD>File Name</TD><TD>::</TD><TD>FolderTreeDumpView.java</TD>
 * </TR>
 * <TR>
 * <TD>Created on</TD><TD>::</TD><TD>Sep 22, 2008 11:34:31 AM</TD>
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
public class FolderTreeDumpView extends javax.swing.JInternalFrame
{
	private static final long	serialVersionUID	= -6731969643177251940L;

	/** Creates new form FolderTreeView */
	public FolderTreeDumpView()
	{
		try
		{
			CustomDocumentumNodeFactoryImpl.SHOW_DOCUMENTS = true;
			CustomDocumentumTreeModel model = new CustomDocumentumTreeModel(AppSessionManager.getInstance()
					.getDocbase());
			initComponents();
			docbaseTree.setModel(model);
			docbaseTree.addTreeSelectionListener(new CustomTreeSelectionDumpListener());
			DefaultTreeCellRenderer lRenderer = (DefaultTreeCellRenderer) docbaseTree.getCellRenderer();
			lRenderer.setLeafIcon(CustomDocumentumNodeFactoryImpl.DOCUMENT_ICON);
			docbaseTree.fireTreeCollapsed(null);
		}
		catch (DfException ex)
		{
			Logger.getLogger(FolderTreeDumpView.class.getName()).log(Level.SEVERE, null, ex);
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
		dumpScrollPane = new javax.swing.JScrollPane();
		dumpTextPane = new javax.swing.JTextPane();

		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setResizable(true);
		org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(
				org.eshark.dctm.MainApplication.class).getContext().getResourceMap(FolderTreeDumpView.class);
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

		windowSplitter.setDividerLocation(200);
		windowSplitter.setDividerSize(10);
		windowSplitter.setContinuousLayout(true);
		windowSplitter.setName("windowSplitter"); // NOI18N
		windowSplitter.setOneTouchExpandable(true);

		treeScrollPane.setName("treeScrollPane"); // NOI18N

		docbaseTree.setName("docbaseTree"); // NOI18N
		treeScrollPane.setViewportView(docbaseTree);

		windowSplitter.setLeftComponent(treeScrollPane);

		dumpScrollPane.setName("dumpScrollPane"); // NOI18N

		dumpTextPane.setName("dumpTextPane"); // NOI18N
		dumpScrollPane.setViewportView(dumpTextPane);

		windowSplitter.setRightComponent(dumpScrollPane);

		getContentPane().add(windowSplitter, java.awt.BorderLayout.CENTER);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void closingFrame(javax.swing.event.InternalFrameEvent evt)
	{//GEN-FIRST:event_closingFrame
		TREE_DUMP_VIEW = null; 
	}//GEN-LAST:event_closingFrame

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JTree			docbaseTree;
	private javax.swing.JScrollPane		dumpScrollPane;
	public static javax.swing.JTextPane	dumpTextPane;
	private javax.swing.JScrollPane		treeScrollPane;
	private javax.swing.JSplitPane		windowSplitter;
	// End of variables declaration//GEN-END:variables

}
