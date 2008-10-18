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
package org.eshark.dctm.gui.tree.factory;

import javax.swing.Icon;
import org.jdesktop.application.ResourceMap;
import org.eshark.dctm.MainApplication;
import org.eshark.dctm.gui.tree.nodes.CustomDocumentNode;
import static org.eshark.dctm.core.DocbaseUtils.isFolder;
import static org.eshark.dctm.core.DocbaseUtils.isCabinet;
import static org.eshark.dctm.core.DocbaseUtils.isDocument;
import org.eshark.dctm.gui.tree.nodes.CustomDocumentumNode;
import org.eshark.dctm.gui.tree.nodes.CustomFolderNode;

/**
 * <TABLE BORDER="1" WIDTH="100%">
 * <TR>
 * <TH>Perticulars</TH><TH>::</TH><TH>Details</TH>
 * </TR>
 * <TR>
 * <TD>Project Name</TD><TD>::</TD><TD>Query Builder D-SIX</TD>
 * </TR>
 * <TR>
 * <TD>File Name</TD><TD>::</TD><TD>CustomDocumentumNodeFactoryImpl.java</TD>
 * </TR>
 * <TR>
 * <TD>Created on</TD><TD>::</TD><TD>Sep 22, 2008 11:28:05 AM</TD>
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
public class CustomDocumentumNodeFactoryImpl implements CustomDocumentumNodeFactory
{

	public static ResourceMap	RESOURCE_MAP	= MainApplication.getApplication().getContext().getResourceMap(
														MainApplication.class);
	public static Icon			CABINET_ICON	= RESOURCE_MAP.getIcon("Application.cabinet");
	public static Icon			DOCUMENT_ICON	= RESOURCE_MAP.getIcon("Application.doc");
	//public static Icon FOLDER_ICON  = RESOURCE_MAP.getIcon("Application.cabinet");

	public static boolean		SHOW_DOCUMENTS	= false;

	public CustomDocumentumNode create(String objectId, String objectName, CustomDocumentumNode parent)
	{
		CustomDocumentumNode lResult = null;
		if (isFolder(objectId) || isCabinet(objectId))
		{
			lResult = new CustomFolderNode(objectId, objectName);
			//if(isFolder(objectId))
			//lResult.set   
		}
		else if (isDocument(objectId) && SHOW_DOCUMENTS)
		{
			lResult = new CustomDocumentNode(objectId, objectName);
		}

		if (lResult != null)
		{
			lResult.setNodeFactory(this);
		}
		return lResult;
	}
}
