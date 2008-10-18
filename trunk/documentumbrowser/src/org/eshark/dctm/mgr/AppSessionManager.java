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

package org.eshark.dctm.mgr;

import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import static org.eshark.dctm.core.DocbaseSessionUtils.getDocbaseSession;
import static org.eshark.dctm.core.DocbaseSessionUtils.releaseSessionUsingManager;
import static org.apache.commons.lang.StringUtils.EMPTY;

/**
 * <TABLE BORDER="1" WIDTH="100%">
 * <TR>
 * <TH>Perticulars</TH><TH>::</TH><TH>Details</TH>
 * </TR>
 * <TR>
 * <TD>Project Name</TD><TD>::</TD><TD>Query Builder D-SIX</TD>
 * </TR>
 * <TR>
 * <TD>File Name</TD><TD>::</TD><TD>AppSessionManager.java</TD>
 * </TR>
 * <TR>
 * <TD>Created on</TD><TD>::</TD><TD>Sep 22, 2008 11:37:35 AM</TD>
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
public class AppSessionManager
{

	private AppSessionManager()
	{
	}

	public String getDocbase()
	{
		return mDocbase;
	}

	public void setDocbase(String mDocbase)
	{
		this.mDocbase = mDocbase;
	}

	public void setDomain(String aDomain)
	{
		mDomain = aDomain;
	}

	public String getDomain()
	{
		return mDomain;
	}

	public String getPassword()
	{
		return mPassword;
	}

	public void setPassword(String mPassword)
	{
		this.mPassword = mPassword;
	}

	public String getUser()
	{
		return mUser;
	}

	public void setUser(String mUser)
	{
		this.mUser = mUser;
	}

	public static AppSessionManager getInstance()
	{
		if (SESSION_MGR == null)
			SESSION_MGR = new AppSessionManager();
		return SESSION_MGR;
	}

	public IDfSession getSession() throws DfException
	{
		return getDocbaseSession(mDocbase, mUser, mPassword, mDomain);
	}

	public void releaseSession(IDfSession aSession)
	{
		releaseSessionUsingManager(aSession);
	}

	private static AppSessionManager	SESSION_MGR	= null;
	private String						mUser		= EMPTY;
	private String						mPassword	= EMPTY;
	private String						mDocbase	= EMPTY;
	private String						mDomain		= EMPTY;
}
