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

package org.eshark.dctm.util;

import javax.swing.Icon;
import org.jdesktop.application.ResourceMap;
import org.eshark.dctm.MainApplication;


/**
 * <TABLE BORDER="1" WIDTH="100%">
 * <TR>
 * <TH>Perticulars</TH><TH>::</TH><TH>Details</TH>
 * </TR>
 * <TR>
 * <TD>Project Name</TD><TD>::</TD><TD>Query Builder D-SIX</TD>
 * </TR>
 * <TR>
 * <TD>File Name</TD><TD>::</TD><TD>Factory.java</TD>
 * </TR>
 * <TR>
 * <TD>Created on</TD><TD>::</TD><TD>Sep 22, 2008 11:50:46 AM</TD>
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
public interface Factory
{
	public static ResourceMap	PROP_MAIN_APP		= MainApplication.getApplication().getContext().getResourceMap(
															MainApplication.class);							;
	public static Icon			ICON_CABINET		= PROP_MAIN_APP.getIcon("Application.cabinet");
	public static Icon			ICON_CABINETS		= PROP_MAIN_APP.getIcon("Application.cabinets");
	public static Icon			ICON_CONNECTED		= PROP_MAIN_APP.getIcon("Application.connected");
	public static Icon			ICON_DIS_CONNECTED	= PROP_MAIN_APP.getIcon("Application.disconnected");
	public static Icon			ICON_DQL			= PROP_MAIN_APP.getIcon("Application.dql");
	public static Icon			ICON_EXECUTE		= PROP_MAIN_APP.getIcon("Application.execute");
	public static Icon			ICON_EXPORT			= PROP_MAIN_APP.getIcon("Application.export");
	public static Icon			ICON_GROUP			= PROP_MAIN_APP.getIcon("Application.group");
	public static Icon			ICON_GROUPS			= PROP_MAIN_APP.getIcon("Application.groups");
	public static Icon			ICON_DOC			= PROP_MAIN_APP.getIcon("Application.doc");
	public static Icon			ICON_TYPE			= PROP_MAIN_APP.getIcon("Application.type");
	public static Icon			ICON_TYPES			= PROP_MAIN_APP.getIcon("Application.types");
	public static Icon			ICON_USERS			= PROP_MAIN_APP.getIcon("Application.users");
	public static Icon			ICON_TREE			= PROP_MAIN_APP.getIcon("Application.tree");
}
