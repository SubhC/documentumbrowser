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

import java.util.ArrayList;
import java.util.List;

import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.client.IDfTypedObject;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.IDfAttr;
import com.documentum.fc.common.IDfTime;


/**
 * <TABLE BORDER="1" WIDTH="100%">
 * <TR>
 * <TH>Perticulars</TH><TH>::</TH><TH>Details</TH>
 * </TR>
 * <TR>
 * <TD>Project Name</TD><TD>::</TD><TD>Query Builder D-SIX</TD>
 * </TR>
 * <TR>
 * <TD>File Name</TD><TD>::</TD><TD>DQLUtil.java</TD>
 * </TR>
 * <TR>
 * <TD>Created on</TD><TD>::</TD><TD>Sep 22, 2008 11:50:23 AM</TD>
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
public class DQLUtil
{
	public static IDfCollection executeQuery(IDfSession aSession, String aDQLQuery) throws DfException
	{
		IDfCollection resultSet = null;
		IDfQuery dqlQuery = new DfQuery();
		//set the query
		dqlQuery.setDQL(aDQLQuery);
		//execute and get the result
		resultSet = dqlQuery.execute(aSession, DfQuery.DF_READ_QUERY);
		return resultSet;
	}

	/**
	 * @param resultSet
	 * @param colName
	 * @param type
	 * @return
	 * @throws DfException
	 */
	public static String getColumnAsString(IDfCollection resultSet, String colName, int type) throws DfException
	{
		String colValue = "NULL";
		switch (type)
		{
			case IDfAttr.DM_BOOLEAN:
				colValue = resultSet.getBoolean(colName) + "";
				break;
			case IDfAttr.DM_TIME:
				colValue = ((IDfTime) resultSet.getTime(colName)) + "";
				break;
			case IDfAttr.DM_DOUBLE:
				colValue = resultSet.getDouble(colName) + "";
				break;
			case IDfAttr.DM_ID:
				colValue = resultSet.getId(colName) + "";
				break;
			case IDfAttr.DM_INTEGER:
				colValue = resultSet.getInt(colName) + "";
				break;
			case IDfAttr.DM_STRING:
				colValue = resultSet.getString(colName) + "";
				break;
			case IDfAttr.DM_UNDEFINED:
				break;
		}
		return colValue;
	}

	public static String getColumnAsString(IDfTypedObject typeObj, String colName, int attrDataType) throws DfException
	{
		String colValue = "<NULL>";
		switch (attrDataType)
		{
			case IDfAttr.DM_BOOLEAN:
				colValue = String.valueOf(typeObj.getBoolean(colName));
				break;
			case IDfAttr.DM_TIME:
				colValue = ((IDfTime) typeObj.getTime(colName)) + "";
				break;
			case IDfAttr.DM_DOUBLE:
				colValue = String.valueOf(typeObj.getDouble(colName));
				break;
			case IDfAttr.DM_ID:
				colValue = String.valueOf(typeObj.getId(colName));
				break;
			case IDfAttr.DM_INTEGER:
				colValue = String.valueOf(typeObj.getInt(colName));
				break;
			case IDfAttr.DM_STRING:
				colValue = typeObj.getString(colName).trim();
				break;
			case IDfAttr.DM_UNDEFINED:
				break;
		}
		return colValue;
	}

	public static String getValueAsString(IDfSysObject sysObject, String colName, int attrDataType) throws DfException
	{
		String colValue = "NULL";
		switch (attrDataType)
		{
			case IDfAttr.DM_BOOLEAN:
				colValue = String.valueOf(sysObject.getBoolean(colName));
				break;
			case IDfAttr.DM_TIME:
				colValue = ((IDfTime) sysObject.getTime(colName)) + "";
				break;
			case IDfAttr.DM_DOUBLE:
				colValue = String.valueOf(sysObject.getDouble(colName));
				break;
			case IDfAttr.DM_ID:
				colValue = String.valueOf(sysObject.getId(colName));
				break;
			case IDfAttr.DM_INTEGER:
				colValue = String.valueOf(sysObject.getInt(colName));
				break;
			case IDfAttr.DM_STRING:
				colValue = sysObject.getString(colName);
				break;
			case IDfAttr.DM_UNDEFINED:
				break;
		}
		return colValue;
	}

	//	public static String getRepeatingValueAsString(IDfSysObject sysObject,String colName,int attrDataType)throws DfException
	//	{
	//		String 	colValue			=	colName ;
	//		int countRepeat				=	0;
	//		switch(attrDataType)
	//		{
	//		case IDfAttr.DM_BOOLEAN 	: 	
	//			countRepeat			=	sysObject.getValueCount(colName);
	//			for(int indx = 0, max = countRepeat;indx < max ; indx++)
	//				colValue			+=	Project.LINE_SEPARATOR + "         [" +indx+"] : " + String.valueOf(sysObject.getRepeatingBoolean(colName,indx));	
	//		break;
	//		case IDfAttr.DM_TIME 		: 	
	//			countRepeat				=	sysObject.getValueCount(colName);
	//			for(int indx = 0, max = countRepeat;indx < max ; indx++)
	//				colValue			+=	Project.LINE_SEPARATOR + "         [" +indx+"] : " + sysObject.getRepeatingTime(colName,indx);
	//		break;	
	//		case IDfAttr.DM_DOUBLE 		:
	//			countRepeat				=	sysObject.getValueCount(colName);
	//			for(int indx = 0, max = countRepeat;indx < max ; indx++)
	//				colValue			+=	Project.LINE_SEPARATOR + "         [" +indx+"] : " + sysObject.getRepeatingDouble(colName,indx);
	//		break;	
	//		case IDfAttr.DM_ID 			:
	//			countRepeat				=	sysObject.getValueCount(colName);
	//			for(int indx = 0, max = countRepeat;indx < max ; indx++)
	//				colValue			+=	Project.LINE_SEPARATOR + "         [" +indx+"] : " + sysObject.getRepeatingId(colName,indx);
	//		break;
	//		case IDfAttr.DM_INTEGER 	:    
	//			countRepeat				=	sysObject.getValueCount(colName);
	//			for(int indx = 0, max = countRepeat;indx < max ; indx++)
	//				colValue			+=	Project.LINE_SEPARATOR + "         [" +indx+"] : " + sysObject.getRepeatingInt(colName,indx);
	//		break;
	//		case IDfAttr.DM_STRING 		:
	//			countRepeat				=	sysObject.getValueCount(colName);
	//			for(int indx = 0, max = countRepeat;indx < max ; indx++)
	//				colValue			+=	Project.LINE_SEPARATOR + "         [" +indx+"] : " + sysObject.getRepeatingString(colName,indx);
	//		break;
	//		case IDfAttr.DM_UNDEFINED 	:
	//		break;
	//		}
	//		return colValue;
	//	}

	/**
	 * @param typeObject
	 * @param colName
	 * @param attrDataType
	 * @return
	 * @throws DfException
	 */
	public static List getRepeatingValueAsList(IDfTypedObject typeObject, String colName, int attrDataType)
			throws DfException
	{
		int countRepeat = 0;
		List valueList = new ArrayList();
		countRepeat = typeObject.getValueCount(colName);
		switch (attrDataType)
		{
			case IDfAttr.DM_BOOLEAN:
				for (int indx = 0, max = countRepeat; indx < max; indx++)
					valueList.add(String.valueOf(typeObject.getRepeatingBoolean(colName, indx)));
				break;
			case IDfAttr.DM_TIME:
				for (int indx = 0, max = countRepeat; indx < max; indx++)
					valueList.add(String.valueOf(typeObject.getRepeatingTime(colName, indx)));
				break;
			case IDfAttr.DM_DOUBLE:
				for (int indx = 0, max = countRepeat; indx < max; indx++)
					valueList.add(String.valueOf(typeObject.getRepeatingDouble(colName, indx)));
				break;
			case IDfAttr.DM_ID:
				for (int indx = 0, max = countRepeat; indx < max; indx++)
					valueList.add(String.valueOf(typeObject.getRepeatingId(colName, indx)));
				break;
			case IDfAttr.DM_INTEGER:
				for (int indx = 0, max = countRepeat; indx < max; indx++)
					valueList.add(String.valueOf(typeObject.getRepeatingInt(colName, indx)));
				break;
			case IDfAttr.DM_STRING:
				for (int indx = 0, max = countRepeat; indx < max; indx++)
					valueList.add(typeObject.getRepeatingString(colName, indx));
				break;
			case IDfAttr.DM_UNDEFINED:
				break;
		}
		return valueList;
	}

	//	public static void listPropertiesToConsole(final String docId,final IDfSession session)
	//	{
	//		final String attrName				=	"attr_name";
	//		final String attrQueryOne			=	"SELECT attr_name from dm_type where name=\'";
	//		final String attrQueryTwo			=	"\'";
	//		String 			docType			=	Constant.EMPTY_STRING;
	//		Project			currentProject	=	Project.getProject();
	//		
	//		if(StringUtil.isNullAndEmpty(docId))
	//		{
	//			currentProject.setMeFree();
	//			return;
	//		}
	//		IDfCollection collection 	= 	null;
	//		List		sysAttrNames	=	new ArrayList();
	//		List	internalAttrNames	=	new ArrayList();
	//		List		appAttrNames	=	new ArrayList();
	//		List		userAttrNames	=	new ArrayList();
	//		String  attrNameValue		=	Constant.EMPTY_STRING;
	//		try
	//		{
	//			//session = node.getManager().getSession(node.getDocbase());
	//			IDfSysObject sysObject	=	(IDfSysObject)session.getObject(new DfId(docId));
	//			docType					=	sysObject.getTypeName();
	//			if(StringUtil.isNullAndEmpty(docType))
	//				docType				=	DfTypeUtils.getTypeFromId(docId);
	//			String dqlQuery			=	attrQueryOne + docType + attrQueryTwo;
	//			collection = executeQuery(session, dqlQuery);			
	//			while (collection.next()) 
	//			{
	//				attrNameValue		 = 	collection.getString(attrName);
	//				if(!StringUtil.isNullAndEmpty(attrNameValue))
	//				{
	//					if(attrNameValue.startsWith("r_"))
	//						sysAttrNames.add(attrNameValue);
	//					else if(attrNameValue.startsWith("i_"))
	//						internalAttrNames.add(attrNameValue);
	//					else if(attrNameValue.startsWith("a_"))
	//						appAttrNames.add(attrNameValue);
	//					else
	//						userAttrNames.add(attrNameValue);
	//				}
	//			}
	//			//Close the Collection
	//			SessionUtil.close(collection);
	//			//sort all attrs
	//			Collections.sort(sysAttrNames);
	//			Collections.sort(internalAttrNames);
	//			Collections.sort(appAttrNames);
	//			Collections.sort(userAttrNames);
	//			dqlQuery				=	"SELECT ";
	//			int attrDataType	=	0;
	//			currentProject.setToConsole("Exploring Properties [r_object_id] : ["+ docId+"]");
	//			currentProject.appendToConsole("======================================================");
	//			currentProject.appendToConsole("                             User Attribute");
	//			currentProject.appendToConsole("======================================================");
	//			for(int indx = 0, max = userAttrNames.size(); indx < max ; indx++)
	//			{
	//				attrNameValue	=	(String)userAttrNames.get(indx);
	//				attrDataType	=	sysObject.getAttrDataType(attrNameValue);
	//				if(!sysObject.isAttrRepeating(attrNameValue))
	//				{
	//					String 	colValue			=	DQLUtil.getValueAsString(sysObject,attrNameValue,attrDataType);
	//					currentProject.appendToConsole(attrNameValue+" : " + colValue);
	//				}
	//				else
	//				{
	//					String 	colValue			=	DQLUtil.getRepeatingValueAsString(sysObject,attrNameValue,attrDataType);
	//					currentProject.appendToConsole(colValue);
	//				}
	//			}
	//			attrDataType	=	0;
	//			currentProject.appendToConsole("======================================================");
	//			currentProject.appendToConsole("                             System Attribute");
	//			currentProject.appendToConsole("======================================================");
	//			for(int indx = 0, max = sysAttrNames.size(); indx < max ; indx++)
	//			{
	//				attrNameValue	=	(String)sysAttrNames.get(indx);
	//				attrDataType	=	sysObject.getAttrDataType(attrNameValue);
	//				if(!sysObject.isAttrRepeating(attrNameValue))
	//				{
	//					String 	colValue			=	DQLUtil.getValueAsString(sysObject,attrNameValue,attrDataType);
	//					currentProject.appendToConsole(attrNameValue+" : " + colValue);
	//				}
	//				else
	//				{
	//					String 	colValue			=	DQLUtil.getRepeatingValueAsString(sysObject,attrNameValue,attrDataType);
	//					currentProject.appendToConsole(colValue);
	//				}
	//			}
	//			attrDataType	=	0;
	//			currentProject.appendToConsole("======================================================");
	//			currentProject.appendToConsole("                        Application Attribute");
	//			currentProject.appendToConsole("======================================================");
	//			for(int indx = 0, max = appAttrNames.size(); indx < max ; indx++)
	//			{
	//				attrNameValue	=	(String)appAttrNames.get(indx);
	//				attrDataType	=	sysObject.getAttrDataType(attrNameValue);
	//				if(!sysObject.isAttrRepeating(attrNameValue))
	//				{
	//					String 	colValue			=	DQLUtil.getValueAsString(sysObject,attrNameValue,attrDataType);
	//					currentProject.appendToConsole(attrNameValue+" : " + colValue);
	//				}
	//				else
	//				{
	//					String 	colValue			=	DQLUtil.getRepeatingValueAsString(sysObject,attrNameValue,attrDataType);
	//					currentProject.appendToConsole(colValue);
	//				}
	//			}
	//			attrDataType	=	0;
	//			currentProject.appendToConsole("======================================================");
	//			currentProject.appendToConsole("                           Internal Attribute");
	//			currentProject.appendToConsole("======================================================");
	//			for(int indx = 0, max = internalAttrNames.size(); indx < max ; indx++)
	//			{
	//				attrNameValue	=	(String)internalAttrNames.get(indx);
	//				attrDataType	=	sysObject.getAttrDataType(attrNameValue);
	//				if(!sysObject.isAttrRepeating(attrNameValue))
	//				{
	//					String 	colValue			=	DQLUtil.getValueAsString(sysObject,attrNameValue,attrDataType);
	//					currentProject.appendToConsole(attrNameValue+" : " + colValue);
	//				}
	//				else
	//				{
	//					String 	colValue			=	DQLUtil.getRepeatingValueAsString(sysObject,attrNameValue,attrDataType);
	//					currentProject.appendToConsole(colValue);
	//				}
	//			}
	//			currentProject.setMeFree();
	//		}
	//		catch (DfException DFE) 
	//		{
	//			currentProject.printStackTrace(DFE);
	//		} 
	//		finally 
	//		{
	//			SessionUtil.close(collection);
	//		}
	//	}
}
