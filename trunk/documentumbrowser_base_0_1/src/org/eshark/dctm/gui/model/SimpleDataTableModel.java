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

import javax.swing.table.*;
import javax.swing.event.TableModelListener;
import javax.swing.event.TableModelEvent;

/**
 * <TABLE BORDER="1" WIDTH="100%">
 * <TR>
 * <TH>Perticulars</TH><TH>::</TH><TH>Details</TH>
 * </TR>
 * <TR>
 * <TD>Project Name</TD><TD>::</TD><TD>Query Builder D-SIX</TD>
 * </TR>
 * <TR>
 * <TD>File Name</TD><TD>::</TD><TD>SimpleDataTableModel.java</TD>
 * </TR>
 * <TR>
 * <TD>Created on</TD><TD>::</TD><TD>Sep 22, 2008 11:26:46 AM</TD>
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
public class SimpleDataTableModel extends AbstractTableModel implements TableModelListener
{
	private static final long	serialVersionUID	= 1L;
	protected TableModel		model;

	public TableModel getModel()
	{
		return model;
	}

	public void setModel(TableModel model)
	{
		this.model = model;
		model.addTableModelListener(this);
	}

	/*
	 * By default, Implement TableModel by forwarding all messages
	 * to the model.
	 */

	public Object getValueAt(int aRow, int aColumn)
	{
		return model.getValueAt(aRow, aColumn);
	}

	@Override
	public void setValueAt(Object aValue, int aRow, int aColumn)
	{
		model.setValueAt(aValue, aRow, aColumn);
	}

	public int getRowCount()
	{
		return (model == null) ? 0 : model.getRowCount();
	}

	public int getColumnCount()
	{
		return (model == null) ? 0 : model.getColumnCount();
	}

	@Override
	public String getColumnName(int aColumn)
	{
		return model.getColumnName(aColumn);
	}

	@Override
	public Class getColumnClass(int aColumn)
	{
		return model.getColumnClass(aColumn);
	}

	@Override
	public boolean isCellEditable(int row, int column)
	{
		return model.isCellEditable(row, column);
	}

	/* 
	 * Implementation of the TableModelListener interface
	 * By default forward all events to all the listeners.
	 */

	public void tableChanged(TableModelEvent e)
	{
		fireTableChanged(e);
	}
}