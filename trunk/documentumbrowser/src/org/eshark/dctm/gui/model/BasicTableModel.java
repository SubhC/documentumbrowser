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

import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;


/**
 * <TABLE BORDER="1" WIDTH="100%">
 * <TR>
 * <TH>Perticulars</TH><TH>::</TH><TH>Details</TH>
 * </TR>
 * <TR>
 * <TD>Project Name</TD><TD>::</TD><TD>Query Builder D-SIX</TD>
 * </TR>
 * <TR>
 * <TD>File Name</TD><TD>::</TD><TD>BasicTableModel.java</TD>
 * </TR>
 * <TR>
 * <TD>Created on</TD><TD>::</TD><TD>Sep 22, 2008 11:25:26 AM</TD>
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
public class BasicTableModel extends AbstractTableModel
{
	private static final long	serialVersionUID	= 5386994152301246258L;
	protected String[]			mColumnNames		= null;
	protected List<Object[]>	mRows				= null;

	public BasicTableModel(String[] aColumnNames, List<Object[]> aRows)
	{
		mRows = aRows;
		mColumnNames = aColumnNames;
	}

	public TableModel getModel()
	{
		return this;
	}

	//    public void  setModel(TableModel model) {
	//        this  = (BasicTableModel)model; 
	//        model.addTableModelListener(this); 
	//    }

	// By default, Implement TableModel by forwarding all messages 
	// to the model. 

	public Object getValueAt(int aRow, int aColumn)
	{
		// System.out.println("Value  " + mRows);
		// System.out.println("Value Size " + mRows.size());
		if (mRows == null || mRows.size() < aRow)
			return null;
		Object[] row = mRows.get(aRow);
		if (row.length < aColumn)
			return null;
		return row[aColumn];
	}

	@Override
	public Class getColumnClass(int aColumn)
	{
		return String.class;
	}

	@Override
	public void setValueAt(Object aValue, int aRow, int aColumn)
	{
		if (mRows == null)
			return;
		if (mRows.size() < aRow)
		{
			Object[] row = new Object[getColumnCount()];
			row[aColumn] = aValue;
			mRows.add(row);
		}
		else
		{
			Object[] row = mRows.get(aRow);
			row[aColumn] = aValue;
		}
	}

	public int getRowCount()
	{
		return mRows.size();
	}

	public int getColumnCount()
	{
		return mColumnNames.length;
	}

	@Override
	public String getColumnName(int aColumn)
	{
		return mColumnNames[aColumn];
	}

	@Override
	public boolean isCellEditable(int row, int column)
	{
		return false;
	}
}
