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

import static org.apache.commons.lang.StringUtils.isEmpty;

import com.documentum.com.DfClientX;
import com.documentum.fc.client.IDfFolder;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.IDfList;
import com.documentum.operations.IDfCancelCheckoutOperation;
import com.documentum.operations.IDfCheckinOperation;
import com.documentum.operations.IDfCheckoutOperation;
import com.documentum.operations.IDfCopyOperation;
import com.documentum.operations.IDfDeleteOperation;
import com.documentum.operations.IDfExportNode;
import com.documentum.operations.IDfExportOperation;
import com.documentum.operations.IDfFile;
import com.documentum.operations.IDfImportOperation;
import com.documentum.operations.IDfMoveOperation;
import com.documentum.operations.IDfOperation;
import com.documentum.operations.IDfOperationStep;
import com.documentum.operations.IDfValidationOperation;
import com.documentum.operations.IDfXMLTransformOperation;

/**
 * <TABLE BORDER="1" WIDTH="100%">
 * <TR>
 * <TH>Perticulars</TH><TH>::</TH><TH>Details</TH>
 * </TR>
 * <TR>
 * <TD>Project Name</TD><TD>::</TD><TD>Query Builder D-SIX</TD>
 * </TR>
 * <TR>
 * <TD>File Name</TD><TD>::</TD><TD>OperationUtils.java</TD>
 * </TR>
 * <TR>
 * <TD>Created on</TD><TD>::</TD><TD>Sep 22, 2008 11:23:55 AM</TD>
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
public class OperationUtils
{
	/**
	 * @param aDfSession
	 * @return
	 * @throws DfException
	 */
	public static IDfImportOperation getImportOperation(IDfSession aDfSession) throws DfException
	{
		IDfImportOperation lOperation = new DfClientX().getImportOperation();
		//the Import Operation requires a session
		lOperation.setSession(aDfSession);
		return lOperation;
	}

	public static IDfExportOperation getExportOperation(IDfSession aDfSession) throws DfException
	{
		IDfExportOperation lOperation = new DfClientX().getExportOperation();
		//the Import Operation requires a session
		lOperation.setSession(aDfSession);
		return lOperation;
	}

	public static IDfCancelCheckoutOperation getCancelCheckoutOperation(IDfSession aDfSession) throws DfException
	{
		IDfCancelCheckoutOperation lOperation = new DfClientX().getCancelCheckoutOperation();
		//the Checkout Operation requires a session
		lOperation.setSession(aDfSession);
		return lOperation;
	}

	/**
	 * @param aDfSession
	 * @return
	 * @throws DfException
	 */
	public static IDfCheckoutOperation getCheckoutOperation(IDfSession aDfSession) throws DfException
	{
		IDfCheckoutOperation lOperation = new DfClientX().getCheckoutOperation();
		//the Checkout Operation requires a session
		lOperation.setSession(aDfSession);
		return lOperation;
	}

	/**
	 * @param aDfSession
	 * @return
	 * @throws DfException
	 */
	public static IDfCheckinOperation getCheckinOperation(IDfSession aDfSession) throws DfException
	{
		IDfCheckinOperation lOperation = new DfClientX().getCheckinOperation();
		//the Checkout Operation requires a session
		lOperation.setSession(aDfSession);
		return lOperation;
	}

	/**
	 * @param aDfSession
	 * @return
	 * @throws DfException
	 */
	public static IDfCopyOperation getCopyOperation(IDfSession aDfSession) throws DfException
	{
		IDfCopyOperation lOperation = new DfClientX().getCopyOperation();
		//the Checkout Operation requires a session
		lOperation.setSession(aDfSession);
		return lOperation;
	}

	/**
	 * @param aDfSession
	 * @return
	 * @throws DfException
	 */
	public static IDfMoveOperation getMoveOperation(IDfSession aDfSession) throws DfException
	{
		IDfMoveOperation lOperation = new DfClientX().getMoveOperation();
		//the Checkout Operation requires a session
		lOperation.setSession(aDfSession);
		return lOperation;
	}

	/**
	 * @param aDfSession
	 * @return
	 * @throws DfException
	 */
	public static IDfDeleteOperation getDeleteOperation(IDfSession aDfSession) throws DfException
	{
		IDfDeleteOperation lOperation = new DfClientX().getDeleteOperation();
		//the Checkout Operation requires a session
		lOperation.setSession(aDfSession);
		return lOperation;
	}

	/**
	 * @param aDfSession
	 * @return
	 * @throws DfException
	 */
	public static IDfValidationOperation getValidationOperation(IDfSession aDfSession) throws DfException
	{
		IDfValidationOperation lOperation = new DfClientX().getValidationOperation();
		//the Checkout Operation requires a session
		lOperation.setSession(aDfSession);
		return lOperation;
	}

	/**
	 * @param aDfSession
	 * @return
	 * @throws DfException
	 */
	public static IDfXMLTransformOperation getXMLTransformOperation(IDfSession aDfSession) throws DfException
	{
		IDfXMLTransformOperation lOperation = new DfClientX().getXMLTransformOperation();
		//the Checkout Operation requires a session
		lOperation.setSession(aDfSession);
		return lOperation;
	}

	/**
	 * @param aDfSession
	 * @return
	 * @throws DfException
	 */
	public static void executeSteps(IDfOperation aOperation) throws DfException
	{
		if (aOperation == null)
			throw new DfException("Invalid Operation [NULL].");

		IDfList steps = aOperation.getSteps();
		for (int stepIndex = 0, stepCount = steps.getCount(); stepIndex < stepCount; stepIndex++)
		{
			IDfOperationStep step = (IDfOperationStep) steps.get(stepIndex);
			System.out.println("Executing step: " + step.getName());
			step.execute();
		}
	}

	/**
	 * @param aDfSession
	 * @return
	 * @throws DfException
	 */
	public static void executeStep(IDfOperation aOperation, int aIndex) throws DfException
	{
		if (aOperation == null)
			throw new DfException("Invalid Operation [NULL].");

		IDfList steps = aOperation.getSteps();
		int stepCount = steps.getCount();

		if (aIndex >= stepCount)
			throw new DfException("Invalid Index [" + aIndex + "] actual [" + stepCount + "].");

		IDfOperationStep step = (IDfOperationStep) steps.get(aIndex);
		System.out.println("Executing step: " + step.getName());
		step.execute();
	}

	/**
	 * @param operation
	 * @return
	 * @throws DfException
	 */
	public static IDfList executeOperation(IDfOperation operation) throws DfException
	{
		//see the operation monitor sample
		//operation.setOperationMonitor( new Progress() );

		// Execute the operation
		boolean executeFlag = operation.execute();

		// Check if any errors occured during the execution of the operation
		if (executeFlag)
			return null;
		return operation.getErrors();
	}

	/**
	 * @param aSourceFileOrDir
	 * @param aDestFolderPath
	 * @param aDfSession
	 * @return IDfList
	 * @throws DfException
	 */
	public static IDfList doImportUisngOperation(String aSourceFileOrDir, String aDestFolderPath, IDfSession aDfSession)
			throws DfException
	{
		if (isEmpty(aSourceFileOrDir))
			throw new DfException("Invalid Source Folder [NULL].");
		if (isEmpty(aDestFolderPath))
			throw new DfException("Invalid Destination Folder [NULL].");
		if (aDfSession == null || !aDfSession.isConnected())
			throw new DfException("Invalid Session.");

		IDfImportOperation lOperation = getImportOperation(aDfSession);

		IDfFolder lDestFolder = null;
		lDestFolder = aDfSession.getFolderByPath(aDestFolderPath);
		//note: the destination folder must exist- or throws nullPointerException
		if (lDestFolder == null)
			throw new DfException("Folder or cabinet " + aDestFolderPath + " does not exist in the Docbase!");

		//Add the Destination Folder
		lOperation.setDestinationFolderId(lDestFolder.getObjectId());

		//check if file or directory on file system exists
		IDfFile lSouceFile = new DfClientX().getFile(aSourceFileOrDir);
		if (lSouceFile.exists() == false)
			throw new DfException("File or directory " + aSourceFileOrDir + " does not exist in the file system!");

		//add the file or directory to the operation
		lOperation.add(lSouceFile);

		//see the Operation- Execute and Check Errors sample code
		return executeOperation(lOperation);

	}

	/**
	 * @param aSourceFile
	 * @param aDestFolderPath
	 * @param aExportName
	 * @param aExportFormat
	 * @param aDfSession
	 * @return
	 * @throws DfException
	 */
	public static IDfList doExportUisngOperation(String aSourceFile, String aDestFolderPath, String aExportName,
			String aExportFormat, IDfSession aDfSession) throws DfException
	{
		if (isEmpty(aSourceFile))
			throw new DfException("Invalid Source Path [NULL].");
		if (isEmpty(aDestFolderPath))
			throw new DfException("Invalid Destination Folder [NULL].");
		if (isEmpty(aExportName))
			throw new DfException("Invalid Export Name [NULL].");
		if (isEmpty(aExportFormat))
			throw new DfException("Invalid Export Format [NULL].");

		if (aDfSession == null || !aDfSession.isConnected())
			throw new DfException("Invalid Session.");

		IDfExportOperation lOperation = getExportOperation(aDfSession);

		//IDfClientX clientx = new DfClientX();
		IDfSysObject lSourceObject = (IDfSysObject) aDfSession.getObjectByPath(aSourceFile);
		if (lSourceObject == null)
			throw new DfException("Invalid Source Path [" + aSourceFile + "].");

		lSourceObject.setObjectName(aExportName);
		lOperation.setDestinationDirectory(aDestFolderPath);

		IDfExportNode node = (IDfExportNode) lOperation.add(lSourceObject);
		node.setFormat(aExportFormat);

		//see the Operation- Execute and Check Errors sample code
		return executeOperation(lOperation);
	}
}