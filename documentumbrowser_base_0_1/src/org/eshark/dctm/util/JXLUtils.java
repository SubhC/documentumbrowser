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

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


/**
 * <TABLE BORDER="1" WIDTH="100%">
 * <TR>
 * <TH>Perticulars</TH><TH>::</TH><TH>Details</TH>
 * </TR>
 * <TR>
 * <TD>Project Name</TD><TD>::</TD><TD>Query Builder D-SIX</TD>
 * </TR>
 * <TR>
 * <TD>File Name</TD><TD>::</TD><TD>JXLUtils.java</TD>
 * </TR>
 * <TR>
 * <TD>Created on</TD><TD>::</TD><TD>Sep 22, 2008 11:51:03 AM</TD>
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
public class JXLUtils
{
	public static final int	XLS_COLOR_NONE		= 0;
	public static final int	XLS_COLOR_RED		= 1;
	public static final int	XLS_COLOR_BLUE		= 2;
	public static final int	XLS_COLOR_GREEN		= 3;
	public static final int	XLS_COLOR_YELLOW	= 4;

	/**
	 * @param aFile
	 * @return
	 * @throws BiffException
	 * @throws IOException
	 */
	public static Workbook getWorkbook(File aFile) throws BiffException, IOException
	{
		return Workbook.getWorkbook(aFile);
	}

	/**
	 * @param aFile
	 * @return
	 * @throws BiffException
	 * @throws IOException
	 */
	public static WritableWorkbook getWritableWorkbook(File aFile) throws BiffException, IOException
	{
		return Workbook.createWorkbook(aFile, Workbook.getWorkbook(aFile));
	}

	/**
	 * @param aFile
	 * @return
	 * @throws BiffException
	 * @throws IOException
	 */
	public static WritableWorkbook createWritableWorkbook(File aFile) throws BiffException, IOException
	{
		WorkbookSettings ws = new WorkbookSettings();
		ws.setLocale(new Locale("en", "EN"));
		return Workbook.createWorkbook(aFile, ws);
	}

	/**
	 * @param aWorkbook
	 * @param sheetIndex
	 * @return Sheet
	 */
	public static Sheet getSheet(Workbook aWorkbook, int sheetIndex)
	{
		return aWorkbook.getSheet(sheetIndex);
	}

	/**
	 * @param aWorkbook
	 * @param sheetIndex
	 * @return
	 */
	public static WritableSheet getWritableSheet(WritableWorkbook aWorkbook, int sheetIndex)
	{
		return aWorkbook.getSheet(sheetIndex);
	}

	/**
	 * @param aWorkbook
	 * @param aName
	 * @param aSheetIndex
	 * @return
	 */
	public static WritableSheet createWritableSheet(WritableWorkbook aWorkbook, String aName, int aSheetIndex)
	{
		return aWorkbook.createSheet(aName, aSheetIndex);
	}

	/**
	 * @param aSheet
	 * @param column
	 * @param row
	 * @return
	 */
	public static String getCellContent(Sheet aSheet, int column, int row)
	{
		return aSheet.getCell(column, row).getContents();
	}

	/**
	 * @param aSheet
	 * @param cellAddress
	 * @return
	 */
	public static String getCellContent(Sheet aSheet, String cellAddress)
	{
		return aSheet.getCell(cellAddress).getContents();
	}

	/**
	 * @param aSheet
	 * @param cellAddress
	 * @throws WriteException 
	 * @throws ComparatorException
	 */
	public static void markFound(WritableSheet aSheet, String cellAddress) throws WriteException
	{

		WritableCell tempCell = aSheet.getWritableCell(cellAddress);
		WritableCellFormat newFormat = new WritableCellFormat(tempCell.getCellFormat());
		// Change the background of cell to red
		newFormat.setBackground(Colour.GREEN);
		tempCell.setCellFormat(newFormat);
	}

	/**
	 * @param aCell
	 * @param colour
	 * @throws WriteException
	 */
	public static void setBackground(WritableCell aCell, int colour) throws WriteException
	{
		if (colour == XLS_COLOR_NONE)
			return;
		WritableCellFormat newFormat = new WritableCellFormat(aCell.getCellFormat());
		// Change the background of cell to red
		switch (colour)
		{
			case XLS_COLOR_RED:
				newFormat.setBackground(Colour.RED);
				break;
			case XLS_COLOR_BLUE:
				newFormat.setBackground(Colour.BLUE);
				break;
			case XLS_COLOR_GREEN:
				newFormat.setBackground(Colour.GREEN);
				break;
			case XLS_COLOR_YELLOW:
				newFormat.setBackground(Colour.YELLOW);
				break;
			default:
				break;
		}
		aCell.setCellFormat(newFormat);
	}

	/**
	 * @param aCell
	 * @param colour
	 * @throws WriteException
	 */
	public static void setForeground(WritableCell aCell, int colour) throws WriteException
	{
		if (colour == XLS_COLOR_NONE)
			return;
		WritableCellFormat newFormat = new WritableCellFormat(aCell.getCellFormat());
		WritableFont newFont = new WritableFont(WritableFont.ARIAL, WritableFont.DEFAULT_POINT_SIZE,
				WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);

		// Change the foreground of cell to red
		switch (colour)
		{
			case XLS_COLOR_RED:
				newFont.setColour(Colour.RED);
				break;
			case XLS_COLOR_BLUE:
				newFont.setColour(Colour.BLUE);
				break;
			case XLS_COLOR_GREEN:
				newFont.setColour(Colour.GREEN);
				break;
			case XLS_COLOR_YELLOW:
				newFont.setColour(Colour.YELLOW);
				break;
			default:
				break;
		}
		newFormat.setFont(newFont);
		aCell.setCellFormat(newFormat);
	}

	/**
	 * @param aSheet
	 * @param valueToWrite
	 * @param cellAddress
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 * @throws ComparatorException
	 */
	public static void writeToCell(WritableSheet aSheet, Object valueToWrite, String cellAddress)
			throws RowsExceededException, WriteException
	{
		WritableCell tempCell = aSheet.getWritableCell(cellAddress);
		Label label = null;
		if (CellType.EMPTY == tempCell.getType())
		{
			label = new Label(tempCell.getColumn(), tempCell.getRow(), valueToWrite + "");
			aSheet.addCell(label);
		}
		else if (CellType.LABEL == tempCell.getType())
		{
			label = (Label) tempCell;
			label.setString(valueToWrite + "");
		}
	}

	/**
	 * @param aSheet
	 * @param valueToWrite
	 * @param column
	 * @param row
	 * @param colour
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	public static void createWritableCell(WritableSheet aSheet, String valueToWrite, int column, int row, int bgColour)
			throws RowsExceededException, WriteException
	{
		Label label = new Label(column, row, valueToWrite);
		aSheet.addCell(label);
		setBackground(aSheet.getWritableCell(column, row), bgColour);
	}

	/**
	 * @param aSheet
	 * @param valueToWrite
	 * @param column
	 * @param row
	 * @param bgColour
	 * @param fontColour
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	public static void createWritableCell(WritableSheet aSheet, String valueToWrite, int column, int row, int bgColour,
			int fontColour) throws RowsExceededException, WriteException
	{
		Label label = new Label(column, row, valueToWrite);
		aSheet.addCell(label);
		setBackground(aSheet.getWritableCell(column, row), bgColour);
		setForeground(aSheet.getWritableCell(column, row), fontColour);
	}

	/**
	 * @param aWorkbook
	 */
	public static void close(Workbook aWorkbook)
	{
		aWorkbook.close();
	}

	/**
	 * @param aWorkbook
	 * @throws WriteException
	 * @throws IOException
	 */
	public static void close(WritableWorkbook aWorkbook) throws WriteException, IOException
	{
		aWorkbook.close();
	}

}
