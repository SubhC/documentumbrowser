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

import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.IDfAttr;
import com.documentum.fc.common.IDfTime;

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
 * <TD>File Name</TD><TD>::</TD><TD>QueryUtil.java</TD>
 * </TR>
 * <TR>
 * <TD>Created on</TD><TD>::</TD><TD>Sep 22, 2008 11:24:17 AM</TD>
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
public class QueryUtil
{
	/**
     * The default date format is 'MM/dd/yyyy H:mm:ss'.
     * Date format pattern used to convert a <code>java.util.Date</code> 
     * to a <code>String</code>. 
     */
    public static final String DEFAULT_DATE_PATTERN = "MM/dd/yyyy hh:mm:ss";


    /**
     * @author       :: Subhasish Chattopadhyay
     * Introduced on :: Mar 2, 2008 1:16:51 AM
     * Purpose       ::
     *
     * @version      :: 
     * @param aSession
     * @param aQuery
     * @return
     * @throws DfException
     */
    public static IDfCollection query(IDfSession aSession, String aQuery)
        throws DfException
    {
        return createAndRunQuery(aSession, aQuery, IDfQuery.DF_QUERY);
    }

    /**
     * @author       :: Subhasish Chattopadhyay
     * Introduced on :: Mar 2, 2008 1:16:44 AM
     * Purpose       ::
     *
     * @version      :: 
     * @param aSession
     * @param aQuery
     * @return
     * @throws DfException
     */
    public static IDfCollection readQuery(IDfSession aSession, String aQuery)        
    throws DfException
    {
        return createAndRunQuery(aSession, aQuery, IDfQuery.DF_READ_QUERY);
    }

    /**
     * @author       :: Subhasish Chattopadhyay
     * Introduced on :: Mar 2, 2008 1:16:36 AM
     * Purpose       ::
     *
     * @version      :: 
     * @param aSession
     * @param aQuery
     * @return
     * @throws DfException
     */
    public static IDfCollection cacheQuery(IDfSession aSession, String aQuery)
        throws DfException
    {
        return createAndRunQuery(aSession, aQuery, IDfQuery.DF_CACHE_QUERY);
    }

    
    /**
     * @author       :: Subhasish Chattopadhyay
     * Introduced on :: Mar 2, 2008 1:16:10 AM
     * Purpose       ::
     *
     * @version      :: 
     * @param aSession
     * @param aQuery
     * @return
     * @throws DfException
     */
    public static IDfCollection execQuery(IDfSession aSession, String aQuery)
        throws DfException
    {
        return createAndRunQuery(aSession, aQuery, IDfQuery.DF_EXEC_QUERY);
    }

    /**
     * @author       :: Subhasish Chattopadhyay
     * Introduced on :: Mar 2, 2008 1:16:00 AM
     * Purpose       ::
     *
     * @version      :: 
     * @param aSession
     * @param aQuery
     * @throws DfException
     */
    public static void execQueryQuiet(IDfSession aSession, String aQuery)
        throws DfException
    {
        IDfCollection lColl = createAndRunQuery(aSession, aQuery,IDfQuery.DF_EXEC_QUERY);
        closeCollection(lColl);
    }

   
	/**
	 * @author       :: Subhasish Chattopadhyay
	 * Introduced on :: Mar 2, 2008 12:06:59 AM
	 * Purpose       ::
	 *
	 * @version      :: 
	 * @param aSession
	 * @param aDQLQuery
	 * @param aQueryType
	 * @return
	 * @throws DfException
	 */
	public static IDfCollection createAndRunQuery(IDfSession aSession,String aDQLQuery, int aQueryType) throws DfException
	{
		if(isEmpty(aDQLQuery))
			throw new DfException("Empty Query.");
		IDfCollection	resultSet					=	null;
		IDfQuery 		dqlQuery					= 	new DfQuery();
		//set the query
		dqlQuery.setDQL(aDQLQuery);
		//execute and get the result
		resultSet 					=	dqlQuery.execute(aSession,aQueryType);
		dqlQuery					=	null;
		return resultSet;
	}

	/**
	 * @author       :: Subhasish Chattopadhyay
	 * Introduced on :: Mar 2, 2008 12:07:18 AM
	 * Purpose       ::
	 *
	 * @version      :: 
	 * @param aCollection
	 * @param colName
	 * @param aColumnType
	 * @return
	 * @throws DfException
	 */
	public static String getColumnAsString(IDfCollection aCollection,String colName,int aColumnType)throws DfException
	{
		String 	colValue			=	"NULL";
		switch(aColumnType)
		{
			case IDfAttr.DM_BOOLEAN 	: 	colValue			=	 String.valueOf(aCollection.getBoolean(colName));break;
			case IDfAttr.DM_TIME 		: 	colValue			=	 ((IDfTime)aCollection.getTime(colName))+"";     break;
			case IDfAttr.DM_DOUBLE 		:	colValue			=	 String.valueOf(aCollection.getDouble(colName)); break;	
			case IDfAttr.DM_ID 			:	colValue			=	 String.valueOf(aCollection.getId(colName));	   break;
			case IDfAttr.DM_INTEGER 	:	colValue			=	 String.valueOf(aCollection.getInt(colName));    break;
			case IDfAttr.DM_STRING 		:	colValue			=	 aCollection.getString(colName).trim();          break;
			case IDfAttr.DM_UNDEFINED 	:  																		   break;
		}
		return colValue;
	}

	/**
	 * @author       :: Subhasish Chattopadhyay
	 * Introduced on :: Mar 2, 2008 12:07:28 AM
	 * Purpose       ::
	 *
	 * @version      :: 
	 * @param aCollection
	 */
	public static void closeCollection(IDfCollection aCollection)
	{
		if(aCollection == null) return;
		try {	aCollection.close();} catch (DfException E){}
	}

}
