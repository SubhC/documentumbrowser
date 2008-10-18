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

import javax.swing.Icon;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * <TABLE BORDER="1" WIDTH="100%">
 * <TR>
 * <TH>Perticulars</TH><TH>::</TH><TH>Details</TH>
 * </TR>
 * <TR>
 * <TD>Project Name</TD><TD>::</TD><TD>Query Builder D-SIX</TD>
 * </TR>
 * <TR>
 * <TD>File Name</TD><TD>::</TD><TD>IconNode.java</TD>
 * </TR>
 * <TR>
 * <TD>Created on</TD><TD>::</TD><TD>Sep 22, 2008 11:31:05 AM</TD>
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
public class IconNode extends DefaultMutableTreeNode
{

	private static final long	serialVersionUID	= 8493993604982532601L;
	protected Icon				icon;

	// protected String iconName;

	public IconNode()
	{
		this(null);
	}

	public IconNode(Object userObject)
	{
		this(userObject, true, null);
	}

	public IconNode(Object userObject, boolean allowsChildren, Icon icon)
	{
		super(userObject, allowsChildren);
		this.icon = icon;
	}

	public void setIcon(Icon icon)
	{
		this.icon = icon;
	}

	public Icon getIcon()
	{
		return icon;
	}

	//    
	//    public String getIconName() {
	////        if (iconName != null) {
	////            return iconName;
	////        } else {
	////            String str = userObject.toString();
	////            int index = str.lastIndexOf(".");
	////            if (index != -1) {
	////                return str.substring(++index);
	////            } else {
	////                return null;
	////            }
	////        }
	//        System.out.println(((ImageIcon)icon).toString());
	//        return ((ImageIcon)icon).toString();
	//    }
	//
	//    public void setIconName(String name) {
	//        iconName = name;
	//    }
}
