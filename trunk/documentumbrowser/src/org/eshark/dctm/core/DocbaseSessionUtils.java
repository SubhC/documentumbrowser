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

package org.eshark.dctm.core;

import com.documentum.com.DfClientX;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSessionManager;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfLoginInfo;
import com.documentum.fc.common.IDfLoginInfo;
import static org.apache.commons.lang.StringUtils.isEmpty;


/**
 * <TABLE BORDER="1" WIDTH="100%">
 * <TR>
 * <TH>Perticulars</TH><TH>::</TH><TH>Details</TH>
 * </TR>
 * <TR>
 * <TD>Project Name</TD><TD>::</TD><TD>Query Builder D-SIX</TD>
 * </TR>
 * <TR>
 * <TD>File Name</TD><TD>::</TD><TD>DocbaseSessionUtils.java</TD>
 * </TR>
 * <TR>
 * <TD>Created on</TD><TD>::</TD><TD>Sep 22, 2008 7:11:46 AM</TD>
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
public class DocbaseSessionUtils
{
	/**
	 * Creates a new Session Manager
	 * @param aDocbaseName
	 * @param aUser
	 * @param aPassword
	 * @return
	 * @throws Exception
	 */
	public static IDfSessionManager getSessionManager() throws DfException
	{
		//create a Session Manager object
		return DocbaseUtils.getLocalClient().newSessionManager();
	}

	/**
	 * Returns the existing Session Manager
	 * @param aSession
	 * @return The Session Manager
	 * @throws DfException
	 */
	public static IDfSessionManager getExistingSessionManager(IDfSession aSession) throws DfException
	{
		if (aSession == null)
			return null;
		//create a Session Manager object
		return aSession.getSessionManager();
	}

	public static void releaseSessionUsingManager(IDfSession aSession)
	{
		if (aSession == null)
			return;
		try
		{
			getExistingSessionManager(aSession).release(aSession);
		}
		catch (DfException e)
		{/*DO NOTHING*/
		}

	}

	public static void releaseSession(IDfSession aSession)
	{
		if (aSession == null)
			return;
		try
		{
			aSession.disconnect();
		}
		catch (DfException e)
		{/*DO NOTHING*/
		}
	}

	/**
	 * @param aDocbaseName
	 * @param aUser
	 * @param aPassword
	 * @return
	 * @throws DfException
	 */
	public static IDfSession getDocbaseSession(String aDocbaseName, String aUser, String aPassword) throws DfException
	{
		return getDocbaseSession(aDocbaseName, aUser, aPassword, null);
	}

	/**
	 * @param aDocbaseName
	 * @param aUser
	 * @param aPassword
	 * @param aDomain
	 * @return
	 * @throws DfException
	 */
	public static IDfSession getDocbaseSession(String aDocbaseName, String aUser, String aPassword, String aDomain)
			throws DfException
	{
		if (isEmpty(aDocbaseName))
			throw new DfException("Invalid Docbase Name.");

		IDfSessionManager lSessionManager = getSessionManager();
		//create an IDfLoginInfo object named loginInfoObj
		IDfLoginInfo lLoginInfoObj = getLoginInfo(aUser, aPassword, aDomain);
		//bind the Session Manager to the login info
		lSessionManager.setIdentity(aDocbaseName, lLoginInfoObj);
		//could also set identity for more than one Docbases:
		// sMgr.setIdentity( strDocbase2, loginInfoObj );
		//use the following to use the same loginInfObj for all Docbases (DFC 5.2 or later)
		return lSessionManager.getSession(aDocbaseName);
	}

	/**
	 * @param aDocbaseName
	 * @param aSessionManager
	 * @return
	 * @throws DfException
	 */
	public static IDfSession getDocbaseSession(IDfSessionManager aSessionManager, String aDocbaseName)
			throws DfException
	{
		if (isEmpty(aDocbaseName))
			throw new DfException("Invalid Docbase Name.");
		if (aSessionManager == null)
			throw new DfException("Invalid Session Manager.");
		return aSessionManager.getSession(aDocbaseName);
	}

	/**
	 * @param aUser
	 * @param aPassword
	 * @param aDomain
	 * @return
	 * @throws DfException
	 */
	public static IDfLoginInfo getLoginInfo(String aUser, String aPassword, String aDomain) throws DfException
	{
		if (isEmpty(aUser) || isEmpty(aPassword))
			throw new DfException("Invalid User Name or Password.");

		//create an IDfLoginInfo object named loginInfoObj
		IDfLoginInfo lLoginInfo = new DfClientX().getLoginInfo();
		lLoginInfo.setUser(aUser);
		lLoginInfo.setPassword(aPassword);
		lLoginInfo.setDomain(aDomain);
		return lLoginInfo;
	}

	/**
	 * Delegate the specified super user's session to the specified user.
	 * The resulting session will function as if were opened by the specified 
	 * user with regards to security, etc., without requiring the user's password.
	 *
	 * @param superUserSession
	 * @param userName
	 * @throws DfException 
	 */
	public static void delegate(IDfSession aSuperUserSession, String userName) throws DfException
	{
		if (aSuperUserSession != null && isEmpty(userName))
		{
			String ticket = aSuperUserSession.getLoginTicketForUser(userName);

			IDfLoginInfo loginInfo = new DfLoginInfo();
			loginInfo.setUser(userName);
			loginInfo.setPassword(ticket);
			aSuperUserSession.assume(loginInfo);
		}
	}
}
