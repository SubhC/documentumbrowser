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

package org.eshark.dctm.gui.model;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfTypedObject;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.IDfAttr;
import static org.apache.commons.lang.StringUtils.EMPTY;
import org.eshark.dctm.mgr.AppSessionManager;
import static org.eshark.dctm.util.DQLUtil.getColumnAsString;

/**
 * <TABLE BORDER="1" WIDTH="100%">
 * <TR>
 * <TH>Perticulars</TH><TH>::</TH><TH>Details</TH>
 * </TR>
 * <TR>
 * <TD>Project Name</TD><TD>::</TD><TD>Query Builder D-SIX</TD>
 * </TR>
 * <TR>
 * <TD>File Name</TD><TD>::</TD><TD>DQLTableModel.java</TD>
 * </TR>
 * <TR>
 * <TD>Created on</TD><TD>::</TD><TD>Sep 22, 2008 11:25:47 AM</TD>
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
public class DQLTableModel extends AbstractTableModel
{
	private static final long	serialVersionUID	= 1L;
	String[]					mColumnNames		= {};
	Class[]						mColumnClasses		= {};
	Vector						mRows				= new Vector();

	/**
	 * @param aSessionManager
	 * @param aDocbase
	 * @throws DfException
	 */
	public DQLTableModel() throws DfException
	{
	}

	/**
	 * @param aDQLQuey
	 * @throws DfException
	 */
	public void executeQuery(String aDQLQuey) throws DfException
	{
		IDfQuery					lQuery = new DfQuery();
		IDfCollection				lCollection;
		IDfSession lSession = AppSessionManager.getInstance().getSession();
		if (lSession == null)
			return;
		//purge all
		mRows.clear();
		//System.out.println("DQL " + aDQLQuey);
		lQuery.setDQL(aDQLQuey);
		lCollection = lQuery.execute(lSession, IDfQuery.DF_READ_QUERY);
		int maxAttrs = lCollection.getAttrCount();
		mColumnNames = new String[maxAttrs];
		mColumnClasses = new Class[maxAttrs];
		for (int attrIndx = 0; attrIndx < maxAttrs; attrIndx++)
		{
			mColumnNames[attrIndx] = lCollection.getAttr(attrIndx).getName();
			mColumnClasses[attrIndx] = String.class;
		}
		//for each row
		Vector lRow = null;
		//For the data
		IDfTypedObject typeObject = null;
		IDfAttr typeAttr = null;
		String colValue = EMPTY;
		while (lCollection.next())
		{
			typeObject = lCollection.getTypedObject();
			//Go to next row
			lRow = new Vector();
			// process each column in a row
			maxAttrs = typeObject.getAttrCount();
			for (int attrIndx = 0; attrIndx < maxAttrs; attrIndx++)
			{
				colValue = EMPTY;
				typeAttr = typeObject.getAttr(attrIndx);
				colValue = getColumnAsString(typeObject, typeAttr.getName(), typeAttr.getDataType());
				//System.out.println(colValue);
				lRow.addElement(colValue);
			}
			mRows.addElement(lRow);
		}
		lCollection.close();
		AppSessionManager.getInstance().releaseSession(lSession);
		fireTableChanged(null); // Tell the listeners a new table has arrived.
	}

	//////////////////////////////////////////////////////////////////////////
	//
	//             Implementation of the TableModel Interface
	//
	//////////////////////////////////////////////////////////////////////////

	// MetaData

	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int column)
	{
		if (mColumnNames[column] != null)
		{
			return mColumnNames[column];
		}
		else
		{
			return EMPTY;
		}
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
	 */
	@Override
	public Class getColumnClass(int column)
	{
		return mColumnClasses[column];
	}

	@Override
	public boolean isCellEditable(int row, int column)
	{
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	public int getColumnCount()
	{
		return mColumnNames.length;
	}

	// Data methods

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount()
	{
		return mRows.size();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int aRow, int aColumn)
	{
		Vector row = (Vector) mRows.elementAt(aRow);
		return row.elementAt(aColumn);
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object, int, int)
	 */
	@Override
	public void setValueAt(Object value, int row, int column)
	{

	}
}
