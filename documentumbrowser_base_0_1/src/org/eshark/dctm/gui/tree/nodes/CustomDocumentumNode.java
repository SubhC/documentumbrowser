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

package org.eshark.dctm.gui.tree.nodes;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import org.eshark.dctm.gui.tree.factory.CustomDocumentumNodeFactory;
import org.eshark.dctm.mgr.AppSessionManager;

import static org.eshark.dctm.core.QueryUtil.closeCollection;
import static org.eshark.dctm.core.QueryUtil.readQuery;
import static org.eshark.dctm.mgr.AppSessionManager.getInstance;

/**
 * <TABLE BORDER="1" WIDTH="100%">
 * <TR>
 * <TH>Perticulars</TH><TH>::</TH><TH>Details</TH>
 * </TR>
 * <TR>
 * <TD>Project Name</TD><TD>::</TD><TD>Query Builder D-SIX</TD>
 * </TR>
 * <TR>
 * <TD>File Name</TD><TD>::</TD><TD>CustomDocumentumNode.java</TD>
 * </TR>
 * <TR>
 * <TD>Created on</TD><TD>::</TD><TD>Sep 22, 2008 11:30:28 AM</TD>
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
public abstract class CustomDocumentumNode extends DefaultMutableTreeNode
{
	private static final long			serialVersionUID	= 1L;
	private boolean						initialized			= false;
	private String						identifier;
	private String						name;
	private CustomDocumentumNodeFactory	factory;

	public CustomDocumentumNode(String identifier, String name)
	{
		super(name);
		this.identifier = identifier;
		this.name = name;
		setAllowsChildren(true);
	}

	public void setNodeFactory(CustomDocumentumNodeFactory factory)
	{
		this.factory = factory;
	}

	protected abstract String getChildrenDocuType();

	protected abstract String getWherePart();

	@Override
	public int getChildCount()
	{
		if (getChildrenDocuType() == null)
		{
			return 0;
		}
		if (!initialized)
		{
			initializeChildren();
		}

		return super.getChildCount();
	}

	private void initializeChildren()
	{
		initialized = true;
		IDfSession session = null;
		IDfCollection collection = null;
		try
		{
			session = AppSessionManager.getInstance().getSession();
			//System.out.println("Parent Object ID : " + identifier); 
			//System.out.println("select r_object_id,r_object_type,object_name from "	+ getChildrenDocuType() + " where 1=1 " + getWherePart()+ " ORDER BY object_name");
			collection = readQuery(session, "SELECT r_object_id,r_object_type,object_name FROM "
					+ getChildrenDocuType() + " where 1=1 " + getWherePart());
			while (collection.next())
			{
				String objectId = collection.getString("r_object_id");
				String objectName = collection.getString("object_name");
				CustomDocumentumNode child = factory.create(objectId, objectName, this);
				if (child != null)
				{
					add(child);
				}
			}
		}
		catch (DfException DFE)
		{

		}
		finally
		{
			closeCollection(collection);
			getInstance().releaseSession(session);

		}
	}

	public String getIdentifier()
	{
		return identifier;
	}

	protected String getFolderPath()
	{
		TreeNode[] path = getPath();
		String folderPath = "";
		if (path != null)
		{
			TreeNode node = null;
			for (int indxPath = 0; indxPath < path.length; indxPath++)
			{
				node = path[indxPath];
				if (node instanceof CustomFolderNode)
				{
					folderPath += ("/" + ((CustomFolderNode) node).getName());

				}
			}
			/*for (TreeNode node : path) {
				if (node instanceof FolderNode) {
					folderPath += ("/" + ((FolderNode) node).getName());
				}
			}*/
		}
		return folderPath;
	}

	public String getName()
	{
		return name;
	}

	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(String identifier)
	{
		this.identifier = identifier;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

}
