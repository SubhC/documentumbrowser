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

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;

/**
 * <TABLE BORDER="1" WIDTH="100%">
 * <TR>
 * <TH>Perticulars</TH><TH>::</TH><TH>Details</TH>
 * </TR>
 * <TR>
 * <TD>Project Name</TD><TD>::</TD><TD>Query Builder D-SIX</TD>
 * </TR>
 * <TR>
 * <TD>File Name</TD><TD>::</TD><TD>MemoryManager.java</TD>
 * </TR>
 * <TR>
 * <TD>Created on</TD><TD>::</TD><TD>Sep 22, 2008 11:37:50 AM</TD>
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
public class MemoryManager
{
	private Timer			mMemorytimer	= null;
	private JProgressBar	mProgressBar	= null;

	public MemoryManager(JProgressBar aProgressBar)
	{
		mProgressBar = aProgressBar;
		mMemorytimer = new Timer(true);
		mMemorytimer.schedule(new ManageMemory(), 0, 60000);
	}

	class ManageMemory extends TimerTask
	{

		@Override
		public void run()
		{

			dubious();
			double totalMemory = Runtime.getRuntime().totalMemory();
			double usedMemory = totalMemory - Runtime.getRuntime().freeMemory();
			int uses = (int) ((usedMemory * 100) / totalMemory);
			//System.out.println("Calling Task " + uses);
			mProgressBar.setValue(uses);
			mProgressBar.setToolTipText(uses + "%");
			mProgressBar.setString(uses + "%");
		}

		private void dubious()
		{
			try
			{
				Runtime.getRuntime().gc();
				Thread.sleep(10);
				Runtime.getRuntime().gc();
				Thread.sleep(10);
			}
			catch (InterruptedException ex)
			{
				Logger.getLogger(MemoryManager.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
}
