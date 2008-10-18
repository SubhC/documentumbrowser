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

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang.StringUtils.isEmpty;
import static org.apache.commons.lang.StringUtils.EMPTY;

import com.documentum.com.DfClientX;
import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.IDfClient;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfDocbaseMap;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.client.IDfTypedObject;
import com.documentum.fc.client.IDfUser;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfId;
import com.documentum.fc.common.IDfId;
import com.documentum.registry.IDfClientRegistry;

/**
 * <TABLE BORDER="1" WIDTH="100%">
 * <TR>
 * <TH>Perticulars</TH><TH>::</TH><TH>Details</TH>
 * </TR>
 * <TR>
 * <TD>Project Name</TD><TD>::</TD><TD>Query Builder D-SIX</TD>
 * </TR>
 * <TR>
 * <TD>File Name</TD><TD>::</TD><TD>DocbaseUtils.java</TD>
 * </TR>
 * <TR>
 * <TD>Created on</TD><TD>::</TD><TD>Sep 22, 2008 11:23:18 AM</TD>
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
public class DocbaseUtils
{

	private static String	DM_FOLDER	= "0b";
	private static String	DM_CABINET	= "0c";
	private static String	DM_DOCUMENT	= "09";

	/**
	 * @param aObjectId
	 * @return
	 */
	public static String getDocbaseNameFromObjectId(String aObjectId)
	{
		String lDocbaseName = EMPTY;

		if (isEmpty(aObjectId))
			return lDocbaseName;

		IDfId lObjectId = new DfId(aObjectId);
		if (lObjectId.isObjectId())
		{
			lDocbaseName = lObjectId.getDocbaseId();
		}
		return lDocbaseName;
	}

	/**
	 * @param aDfSession
	 * @param aObjectId
	 * @return
	 */
	public static boolean isObjectExist(IDfSession aDfSession, String aObjectId) throws DfException
	{
		boolean isExists = false;

		if (isEmpty(aObjectId) || aDfSession == null || !aDfSession.isConnected())
			return isExists;

		aDfSession.getObject(new DfId(aObjectId));
		isExists = true;
		return isExists;
	}

	/**
	 * @param aDfSession
	 * @param aObjectId
	 * @return
	 */
	public static boolean isReference(IDfSession aDfSession, String aObjectId) throws DfException
	{
		boolean isReference = false;
		if (isEmpty(aObjectId) || aDfSession == null || !aDfSession.isConnected())
			return isReference;
		IDfSysObject lTempObj = (IDfSysObject) aDfSession.getObject(new DfId(aObjectId));
		if (lTempObj != null && lTempObj.isReference())
			isReference = true;
		return isReference;
	}

	/**
	 * @param aDfSession
	 * @param aObjectId
	 * @return
	 */
	public static boolean isReplica(IDfSession aDfSession, String aObjectId) throws DfException
	{
		boolean isReplica = false;
		if (isEmpty(aObjectId) || aDfSession == null || !aDfSession.isConnected())
			return isReplica;
		IDfSysObject lTempObj = (IDfSysObject) aDfSession.getObject(new DfId(aObjectId));
		if (lTempObj != null && lTempObj.isReplica())
			isReplica = true;
		return isReplica;
	}

	/**
	 * @param aObjectId
	 * @return
	 */
	public static boolean isForeign(IDfSession aDfSession, String aObjectId) throws DfException
	{
		boolean isForeign = false;
		if (isEmpty(aObjectId))
			return isForeign;

		String lDocbaseName = getDocbaseNameFromObjectId(aObjectId);
		if (!isEmpty(lDocbaseName) && !lDocbaseName.equals(aDfSession.getDocbaseName()))
		{
			isForeign = true;
		}
		return isForeign;
	}

	/**
	 * @param aObjectId
	 * @return
	 */
	public static boolean isValidId(String aObjectId)//throws DfException
	{
		if (isEmpty(aObjectId))
			return false;

		IDfId lObjectId = new DfId(aObjectId);
		return lObjectId.isObjectId();
	}

	/**
	 * @param aObjectId
	 * @return
	 */
	public static boolean isValidFolderId(String aObjectId)//throws DfException
	{
		if (isEmpty(aObjectId))
			return false;

		IDfId lObjectId = new DfId(aObjectId);
		if (!isValidId(aObjectId))
			return false;
		return lObjectId.getTypePart() == 0x0b;
	}

	/**
	 * @param aObjectId
	 * @return
	 */
	public static boolean isValidCabinetId(String aObjectId)//throws DfException
	{
		if (isEmpty(aObjectId))
			return false;

		IDfId lObjectId = new DfId(aObjectId);
		if (!isValidId(aObjectId))
			return false;
		return lObjectId.getTypePart() == 0x0c;
	}

	/**
	 * @param aObjectId
	 * @return
	 */
	public static boolean isValidDocumentId(String aObjectId)//throws DfException
	{
		if (isEmpty(aObjectId))
			return false;

		IDfId lObjectId = new DfId(aObjectId);
		if (!isValidId(aObjectId))
			return false;
		return lObjectId.getTypePart() == 0x09;
	}

	/**
	 * @param aObjectId
	 * @return
	 */
	public static boolean isValidTypeId(String aObjectId, int aTypeNumber)//throws DfException
	{
		if (isEmpty(aObjectId))
			return false;

		IDfId lObjectId = new DfId(aObjectId);
		if (!isValidId(aObjectId))
			return false;
		return lObjectId.getTypePart() == aTypeNumber;
	}

	/**
	 * @param aDfSession
	 * @param aObjectId
	 * @return
	 * @throws DfException
	 */
	public static boolean canUserChangePermissions(IDfSession aDfSession, String aObjectId) throws DfException
	{
		IDfSysObject lSysObject = null;
		String lUserName = EMPTY;
		IDfUser lDfUser = null;
		int changePermission = 0x20000;

		if (isEmpty(aObjectId) || aDfSession == null || !aDfSession.isConnected())
			return false;
		lSysObject = (IDfSysObject) aDfSession.getObject(new DfId(aObjectId));
		lUserName = aDfSession.getLoginUserName();
		lDfUser = aDfSession.getUser(lUserName);

		if (lSysObject.isReplica() || lSysObject.isImmutable() || lSysObject.isFrozen()
				|| (lSysObject.isCheckedOut() && !lSysObject.isCheckedOutBy(lUserName)))
			return false;
		if ((lSysObject.getXPermit(lUserName) & changePermission) != changePermission)
			return false;
		if (!lSysObject.getOwnerName().equals(lUserName) && !lDfUser.isSuperUser())
			return false;
		return true;
	}

	/**
	 * @param aDfSession
	 * @param aSysObject
	 * @return
	 * @throws DfException
	 */
	public static String getCurrentObjectIdOfVersionTree(IDfSession aDfSession, IDfSysObject aSysObject)
			throws DfException
	{
		String lCurrentObjectId = EMPTY;
		DfQuery lQuery = new DfQuery();
		String lChronicleId = aSysObject.getChronicleId().toString();
		StringBuffer sbQuery = new StringBuffer(64);
		sbQuery.append("SELECT r_object_id from dm_sysobject where i_chronicle_id_i = '").append(lChronicleId).append(
				"'");
		lQuery.setDQL(sbQuery.toString());
		IDfCollection lCollection = null;
		lCollection = lQuery.execute(aDfSession, IDfQuery.READ_QUERY);

		if (lCollection.next())
			lCurrentObjectId = lCollection.getString("r_object_id");

		if (lCollection != null)
			lCollection.close();
		return lCurrentObjectId;
	}

	/**
	 * @return
	 * @throws DfException
	 */
	public static IDfClient getLocalClient() throws DfException
	{
		return new DfClientX().getLocalClient();
	}

	/**
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List getDocbaseList() throws DfException
	{
		List lDocbaseNames = new ArrayList();
		IDfDocbaseMap lDocbaseMap = getLocalClient().getDocbaseMap();
		/*System.out.println("Docbases for Docbroker: " + myMap.getHostName());
		    System.out.println("Total number of Docbases: " + myMap.getDocbaseCount());*/
		for (int indx = lDocbaseMap.getDocbaseCount() - 1; indx >= 0; indx--)
		{
			lDocbaseNames.add(lDocbaseMap.getDocbaseName(indx));
		}
		return lDocbaseNames;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public static IDfDocbaseMap getDocbaseMap() throws DfException
	{
		return getLocalClient().getDocbaseMap();
	}

	/**
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static String getDocBrokerName() throws DfException
	{
		return getDocbaseMap().getHostName();
	}

	/**
	 * @return
	 */
	public static String getDFCVersion()
	{
		return new DfClientX().getDFCVersion();
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public static IDfClientRegistry getClientRegistry() throws DfException
	{
		return new DfClientX().getClientRegistry();
	}

	/**
	 * Retrieve the SMTP server from the dm_server_config.
	 * 
	 * @param dfSession
	 * @return the SMTP Server name
	 */
	public static String getSmtpServer(IDfSession aDfSession) throws DfException
	{
		if (aDfSession == null)
			throw new DfException("Invalid Session.");

		IDfTypedObject lServerConfig = aDfSession.getServerConfig();
		return lServerConfig.getString("smtp_server");
	}

	public static boolean isFolder(String id)
	{
		return check(id, DM_FOLDER);
	}

	public static boolean isCabinet(String id)
	{
		return check(id, DM_CABINET);
	}

	public static boolean isDocument(String id)
	{
		return check(id, DM_DOCUMENT);
	}

	private static boolean check(String id, String prefix)
	{
		if (id == null || id.length() != 16)
		{
			return false;
		}
		return id.toLowerCase().startsWith(prefix);
	}
}
