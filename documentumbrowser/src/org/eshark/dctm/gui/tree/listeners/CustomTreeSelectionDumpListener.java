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

package org.eshark.dctm.gui.tree.listeners;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfId;
import org.eshark.dctm.gui.tree.nodes.CustomDocumentumNode;
import org.eshark.dctm.mgr.AppSessionManager;
import static org.eshark.dctm.gui.FolderTreeDumpView.dumpTextPane;


/**
 * <TABLE BORDER="1" WIDTH="100%">
 * <TR>
 * <TH>Perticulars</TH><TH>::</TH><TH>Details</TH>
 * </TR>
 * <TR>
 * <TD>Project Name</TD><TD>::</TD><TD>Query Builder D-SIX</TD>
 * </TR>
 * <TR>
 * <TD>File Name</TD><TD>::</TD><TD>CustomTreeSelectionDumpListener.java</TD>
 * </TR>
 * <TR>
 * <TD>Created on</TD><TD>::</TD><TD>Sep 22, 2008 11:28:50 AM</TD>
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
public class CustomTreeSelectionDumpListener implements TreeSelectionListener 
{
	public void valueChanged(TreeSelectionEvent TSE)
	{
		CustomDocumentumNode 	lNode 		= 	(CustomDocumentumNode)((JTree)TSE.getSource()).getLastSelectedPathComponent();
		
		IDfSession		lSession				=	null;
		try
		{
                         dumpTextPane.setText("Retrieving Data.");
			lSession			=	AppSessionManager.getInstance().getSession();
                        dumpTextPane.setText(lSession.getObject(new DfId(lNode.getIdentifier())).dump());
			
		}
		catch (DfException DFE) 
		{
			
		} 
		finally 
		{
			AppSessionManager.getInstance().releaseSession(lSession);
		}
	}


}
