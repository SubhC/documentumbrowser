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

package org.eshark.dctm.gui;

import org.eshark.dctm.task.ShowDQLQueryActionTask;
import org.eshark.dctm.task.ShowSystemPropertytActionTask;
import org.eshark.dctm.*;
import org.eshark.dctm.gui.AboutDialog;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import org.jdesktop.application.Task;
import org.eshark.dctm.mgr.MemoryManager;
import org.eshark.dctm.task.ExitActionTask;
import org.eshark.dctm.task.ShowActiveJobsActionTask;
import org.eshark.dctm.task.ShowActiveUsersActionTask;
import org.eshark.dctm.task.ShowActiveWorkflowsActionTask;
import org.eshark.dctm.task.ShowConnectedUserActionTask;
import org.eshark.dctm.task.ShowCountSysObjectActionTask;
import org.eshark.dctm.task.ShowFolderTreeDumpActionTask;
import org.eshark.dctm.task.ShowFolderTreeListActionTask;
import org.eshark.dctm.task.ShowListACLsActionTask;
import org.eshark.dctm.task.ShowListTypesActionTask;
import org.eshark.dctm.task.ShowSignInActionTask;
import org.eshark.dctm.task.ShowTypeTreeActionTask;
import static org.eshark.dctm.util.Factory.ICON_CONNECTED;
import static org.eshark.dctm.util.Factory.ICON_DIS_CONNECTED;

/**
 * <TABLE BORDER="1" WIDTH="100%">
 * <TR>
 * <TH>Perticulars</TH><TH>::</TH><TH>Details</TH>
 * </TR>
 * <TR>
 * <TD>Project Name</TD><TD>::</TD><TD>Query Builder D-SIX</TD>
 * </TR>
 * <TR>
 * <TD>File Name</TD><TD>::</TD><TD>MainMDIView.java</TD>
 * </TR>
 * <TR>
 * <TD>Created on</TD><TD>::</TD><TD>Sep 22, 2008 11:36:17 AM</TD>
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
public class MainMDIView extends FrameView
{

	public MainMDIView(SingleFrameApplication aApplication)
	{
		super(aApplication);
		initComponents();

		((MainApplication) aApplication).setDesktopPane(desktopPane);

		// status bar initialization - message timeout, idle icon and busy animation, etc
		ResourceMap resourceMap = getResourceMap();
		int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
		messageTimer = new Timer(messageTimeout, new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				statusMessageLabel.setText("");
			}
		});
		messageTimer.setRepeats(false);
		int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
		for (int i = 0; i < busyIcons.length; i++)
		{
			busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
		}
		busyIconTimer = new Timer(busyAnimationRate, new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
				statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
			}
		});
		idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
		statusAnimationLabel.setIcon(idleIcon);
		progressBar.setVisible(false);

		// connecting action tasks to status bar via TaskMonitor
		TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
		taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener()
		{
			public void propertyChange(java.beans.PropertyChangeEvent evt)
			{
				String propertyName = evt.getPropertyName();
				if ("started".equals(propertyName))
				{
					if (!busyIconTimer.isRunning())
					{
						statusAnimationLabel.setIcon(busyIcons[0]);
						busyIconIndex = 0;
						busyIconTimer.start();
					}
					progressBar.setVisible(true);
					progressBar.setIndeterminate(true);
				}
				else if ("done".equals(propertyName))
				{
					busyIconTimer.stop();
					statusAnimationLabel.setIcon(idleIcon);
					progressBar.setVisible(false);
					progressBar.setValue(0);
				}
				else if ("message".equals(propertyName))
				{
					String text = (String) (evt.getNewValue());
					statusMessageLabel.setText((text == null) ? "" : text);
					messageTimer.restart();
				}
				else if ("progress".equals(propertyName))
				{
					int value = (Integer) (evt.getNewValue());
					progressBar.setVisible(true);
					progressBar.setIndeterminate(false);
					progressBar.setValue(value);
				}
			}
		});
		new MemoryManager(memoryProgressBar);
	}

	@Action
	public void showAboutBox()
	{
		if (aboutBox == null)
		{
			JFrame mainFrame = MainApplication.getApplication().getMainFrame();
			aboutBox = new AboutDialog(mainFrame);
			aboutBox.setLocationRelativeTo(mainFrame);

		}
		MainApplication.getApplication().show(aboutBox);
	}

	@Action
	public Task quit()
	{
		return new ExitActionTask(MainApplication.getApplication());
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents()
	{

		mainPanel = new javax.swing.JPanel();
		statusPanel = new javax.swing.JPanel();
		statusMessageLabel = new javax.swing.JLabel();
		statusAnimationLabel = new javax.swing.JLabel();
		progressBar = new javax.swing.JProgressBar();
		jPanel2 = new javax.swing.JPanel();
		connectedLabel = new javax.swing.JLabel();
		docbaseLabel = new javax.swing.JLabel();
		userLabel = new javax.swing.JLabel();
		memoryProgressBar = new javax.swing.JProgressBar();
		desktopPane = new javax.swing.JDesktopPane();
		menuBar = new javax.swing.JMenuBar();
		javax.swing.JMenu controlMenu = new javax.swing.JMenu();
		systemPropertyMenuItem = new javax.swing.JMenuItem();
		jSeparator2 = new javax.swing.JSeparator();
		signInMenuItem = new javax.swing.JMenuItem();
		jSeparator1 = new javax.swing.JSeparator();
		javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
		treeMenu = new javax.swing.JMenu();
		listTypesMenuItem = new javax.swing.JMenuItem();
		jSeparator3 = new javax.swing.JSeparator();
		dumpTreeMenuItem = new javax.swing.JMenuItem();
		folderDocumentTreeMenuItem = new javax.swing.JMenuItem();
		reportsMenu = new javax.swing.JMenu();
		aclMenuItem = new javax.swing.JMenuItem();
		typeMenuItem = new javax.swing.JMenuItem();
		jSeparator9 = new javax.swing.JSeparator();
		activeJobsMenuItem = new javax.swing.JMenuItem();
		activeWorkflowsMenuItem = new javax.swing.JMenuItem();
		actuveUserMenuItem = new javax.swing.JMenuItem();
		jSeparator10 = new javax.swing.JSeparator();
		connectedUserMenuItem = new javax.swing.JMenuItem();
		jSeparator11 = new javax.swing.JSeparator();
		countSysObjectMenuItem = new javax.swing.JMenuItem();
		queryMenu = new javax.swing.JMenu();
		dqlMenuItem = new javax.swing.JMenuItem();
		javax.swing.JMenu helpMenu = new javax.swing.JMenu();
		javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
		jPanel1 = new javax.swing.JPanel();
		jSeparator4 = new javax.swing.JSeparator();
		jSeparator6 = new javax.swing.JSeparator();

		mainPanel.setName("mainPanel"); // NOI18N
		mainPanel.setLayout(new java.awt.BorderLayout(2, 2));

		statusPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
		statusPanel.setName("statusPanel"); // NOI18N

		statusMessageLabel.setName("statusMessageLabel"); // NOI18N

		statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

		progressBar.setName("progressBar"); // NOI18N

		jPanel2.setName("jPanel2"); // NOI18N
		jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

		org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(
				org.eshark.dctm.MainApplication.class).getContext().getResourceMap(MainMDIView.class);
		connectedLabel.setIcon(resourceMap.getIcon("connectedLabel.icon")); // NOI18N
		connectedLabel.setText(resourceMap.getString("connectedLabel.text")); // NOI18N
		connectedLabel.setName("connectedLabel"); // NOI18N
		jPanel2.add(connectedLabel);

		docbaseLabel.setText(resourceMap.getString("docbaseLabel.text")); // NOI18N
		docbaseLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		docbaseLabel.setName("docbaseLabel"); // NOI18N
		jPanel2.add(docbaseLabel);

		userLabel.setText(resourceMap.getString("userLabel.text")); // NOI18N
		userLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		userLabel.setName("userLabel"); // NOI18N
		jPanel2.add(userLabel);

		memoryProgressBar.setForeground(resourceMap.getColor("memoryProgressBar.foreground")); // NOI18N
		memoryProgressBar.setMinimum(1);
		memoryProgressBar.setName("memoryProgressBar"); // NOI18N
		memoryProgressBar.setStringPainted(true);
		jPanel2.add(memoryProgressBar);

		org.jdesktop.layout.GroupLayout statusPanelLayout = new org.jdesktop.layout.GroupLayout(statusPanel);
		statusPanel.setLayout(statusPanelLayout);
		statusPanelLayout.setHorizontalGroup(statusPanelLayout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				statusPanelLayout.createSequentialGroup().add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
						651, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(
						org.jdesktop.layout.LayoutStyle.RELATED).add(statusMessageLabel).addPreferredGap(
						org.jdesktop.layout.LayoutStyle.RELATED, 17, Short.MAX_VALUE).add(progressBar,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).addPreferredGap(
						org.jdesktop.layout.LayoutStyle.RELATED).add(statusAnimationLabel).addContainerGap()));
		statusPanelLayout.setVerticalGroup(statusPanelLayout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				statusPanelLayout.createSequentialGroup().addContainerGap().add(
						statusPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE).add(
								statusMessageLabel).add(statusAnimationLabel).add(progressBar,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)).addContainerGap(16, Short.MAX_VALUE))
				.add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE));

		mainPanel.add(statusPanel, java.awt.BorderLayout.SOUTH);

		desktopPane.setName("DesktopPane"); // NOI18N
		mainPanel.add(desktopPane, java.awt.BorderLayout.CENTER);

		menuBar.setName("menuBar"); // NOI18N

		controlMenu.setMnemonic('C');
		controlMenu.setText(resourceMap.getString("controlMenu.text")); // NOI18N
		controlMenu.setName("controlMenu"); // NOI18N

		javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(
				org.eshark.dctm.MainApplication.class).getContext().getActionMap(MainMDIView.class, this);
		systemPropertyMenuItem.setAction(actionMap.get("showSystemProperty")); // NOI18N
		systemPropertyMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S,
				java.awt.event.InputEvent.ALT_MASK));
		systemPropertyMenuItem.setText(resourceMap.getString("systemPropertyMenuItem.text")); // NOI18N
		systemPropertyMenuItem.setName("systemPropertyMenuItem"); // NOI18N
		controlMenu.add(systemPropertyMenuItem);

		jSeparator2.setName("jSeparator2"); // NOI18N
		controlMenu.add(jSeparator2);

		signInMenuItem.setAction(actionMap.get("signInAction")); // NOI18N
		signInMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I,
				java.awt.event.InputEvent.ALT_MASK));
		signInMenuItem.setText(resourceMap.getString("signInMenuItem.text")); // NOI18N
		signInMenuItem.setName("signInMenuItem"); // NOI18N
		controlMenu.add(signInMenuItem);

		jSeparator1.setName("jSeparator1"); // NOI18N
		controlMenu.add(jSeparator1);

		exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
		exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X,
				java.awt.event.InputEvent.ALT_MASK));
		exitMenuItem.setName("exitMenuItem"); // NOI18N
		controlMenu.add(exitMenuItem);

		menuBar.add(controlMenu);

		treeMenu.setMnemonic('T');
		treeMenu.setText(resourceMap.getString("treeMenu.text")); // NOI18N
		treeMenu.setEnabled(false);
		treeMenu.setName("treeMenu"); // NOI18N

		listTypesMenuItem.setAction(actionMap.get("showTypeTree")); // NOI18N
		listTypesMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A,
				java.awt.event.InputEvent.ALT_MASK));
		listTypesMenuItem.setText(resourceMap.getString("listTypesMenuItem.text")); // NOI18N
		listTypesMenuItem.setName("listTypesMenuItem"); // NOI18N
		treeMenu.add(listTypesMenuItem);

		jSeparator3.setName("jSeparator3"); // NOI18N
		treeMenu.add(jSeparator3);

		dumpTreeMenuItem.setAction(actionMap.get("showFolderTreeDump")); // NOI18N
		dumpTreeMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D,
				java.awt.event.InputEvent.ALT_MASK));
		dumpTreeMenuItem.setText(resourceMap.getString("dumpTreeMenuItem.text")); // NOI18N
		dumpTreeMenuItem.setName("dumpTreeMenuItem"); // NOI18N
		treeMenu.add(dumpTreeMenuItem);

		folderDocumentTreeMenuItem.setAction(actionMap.get("showFolderTreeList")); // NOI18N
		folderDocumentTreeMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L,
				java.awt.event.InputEvent.ALT_MASK));
		folderDocumentTreeMenuItem.setText(resourceMap.getString("folderDocumentTreeMenuItem.text")); // NOI18N
		folderDocumentTreeMenuItem.setName("folderDocumentTreeMenuItem"); // NOI18N
		treeMenu.add(folderDocumentTreeMenuItem);

		menuBar.add(treeMenu);

		reportsMenu.setMnemonic('R');
		reportsMenu.setText(resourceMap.getString("reportsMenu.text")); // NOI18N
		reportsMenu.setEnabled(false);
		reportsMenu.setName("reportsMenu"); // NOI18N

		aclMenuItem.setAction(actionMap.get("showACLs")); // NOI18N
		aclMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A,
				java.awt.event.InputEvent.ALT_MASK));
		aclMenuItem.setText(resourceMap.getString("aclMenuItem.text")); // NOI18N
		aclMenuItem.setName("aclMenuItem"); // NOI18N
		reportsMenu.add(aclMenuItem);

		typeMenuItem.setAction(actionMap.get("showTypes")); // NOI18N
		typeMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T,
				java.awt.event.InputEvent.ALT_MASK));
		typeMenuItem.setText(resourceMap.getString("typeMenuItem.text")); // NOI18N
		typeMenuItem.setName("typeMenuItem"); // NOI18N
		reportsMenu.add(typeMenuItem);

		jSeparator9.setName("jSeparator9"); // NOI18N
		reportsMenu.add(jSeparator9);

		activeJobsMenuItem.setAction(actionMap.get("showActiveJobs")); // NOI18N
		activeJobsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_J,
				java.awt.event.InputEvent.ALT_MASK));
		activeJobsMenuItem.setText(resourceMap.getString("activeJobsMenuItem.text")); // NOI18N
		activeJobsMenuItem.setName("activeJobsMenuItem"); // NOI18N
		reportsMenu.add(activeJobsMenuItem);

		activeWorkflowsMenuItem.setAction(actionMap.get("showActiveWorkflows")); // NOI18N
		activeWorkflowsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W,
				java.awt.event.InputEvent.ALT_MASK));
		activeWorkflowsMenuItem.setText(resourceMap.getString("activeWorkflowsMenuItem.text")); // NOI18N
		activeWorkflowsMenuItem.setName("activeWorkflowsMenuItem"); // NOI18N
		reportsMenu.add(activeWorkflowsMenuItem);

		actuveUserMenuItem.setAction(actionMap.get("showActiveUsers")); // NOI18N
		actuveUserMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U,
				java.awt.event.InputEvent.ALT_MASK));
		actuveUserMenuItem.setText(resourceMap.getString("actuveUserMenuItem.text")); // NOI18N
		actuveUserMenuItem.setName("actuveUserMenuItem"); // NOI18N
		reportsMenu.add(actuveUserMenuItem);

		jSeparator10.setName("jSeparator10"); // NOI18N
		reportsMenu.add(jSeparator10);

		connectedUserMenuItem.setAction(actionMap.get("showConnectedUser")); // NOI18N
		connectedUserMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C,
				java.awt.event.InputEvent.ALT_MASK));
		connectedUserMenuItem.setText(resourceMap.getString("connectedUserMenuItem.text")); // NOI18N
		connectedUserMenuItem.setName("connectedUserMenuItem"); // NOI18N
		reportsMenu.add(connectedUserMenuItem);

		jSeparator11.setName("jSeparator11"); // NOI18N
		reportsMenu.add(jSeparator11);

		countSysObjectMenuItem.setAction(actionMap.get("showCountSysObject")); // NOI18N
		countSysObjectMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O,
				java.awt.event.InputEvent.ALT_MASK));
		countSysObjectMenuItem.setText(resourceMap.getString("countSysObjectMenuItem.text")); // NOI18N
		countSysObjectMenuItem.setName("countSysObjectMenuItem"); // NOI18N
		reportsMenu.add(countSysObjectMenuItem);

		menuBar.add(reportsMenu);

		queryMenu.setMnemonic('Q');
		queryMenu.setText(resourceMap.getString("queryMenu.text")); // NOI18N
		queryMenu.setEnabled(false);
		queryMenu.setName("queryMenu"); // NOI18N

		dqlMenuItem.setAction(actionMap.get("showDQLEditor")); // NOI18N
		dqlMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D,
				java.awt.event.InputEvent.ALT_MASK));
		dqlMenuItem.setMnemonic('D');
		dqlMenuItem.setText(resourceMap.getString("dqlMenuItem.text")); // NOI18N
		dqlMenuItem.setName("dqlMenuItem"); // NOI18N
		queryMenu.add(dqlMenuItem);

		menuBar.add(queryMenu);

		helpMenu.setMnemonic('H');
		helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
		helpMenu.setName("helpMenu"); // NOI18N

		aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
		aboutMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H,
				java.awt.event.InputEvent.ALT_MASK));
		aboutMenuItem.setName("aboutMenuItem"); // NOI18N
		helpMenu.add(aboutMenuItem);

		menuBar.add(helpMenu);

		jPanel1.setName("jPanel1"); // NOI18N

		org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
				.add(0, 100, Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(
				0, 100, Short.MAX_VALUE));

		jSeparator4.setName("jSeparator4"); // NOI18N

		jSeparator6.setName("jSeparator6"); // NOI18N

		setComponent(mainPanel);
		setMenuBar(menuBar);
		setStatusBar(statusPanel);
	}// </editor-fold>//GEN-END:initComponents

	@Action
	public Task signInAction()
	{
		return new ShowSignInActionTask(getApplication());
	}

	@Action
	public Task showSystemProperty()
	{
		return new ShowSystemPropertytActionTask(getApplication());
	}

	@Action
	public Task showDQLEditor()
	{
		return new ShowDQLQueryActionTask(getApplication());
	}

	@Action
	public Task showConnectedUser()
	{
		return new ShowConnectedUserActionTask(getApplication());
	}

	@Action
	public Task showACLs()
	{
		return new ShowListACLsActionTask(getApplication());
	}

	@Action
	public Task showTypes()
	{
		return new ShowListTypesActionTask(getApplication());
	}

	@Action
	public Task showActiveJobs()
	{
		return new ShowActiveJobsActionTask(getApplication());
	}

	@Action
	public Task showActiveWorkflows()
	{
		return new ShowActiveWorkflowsActionTask(getApplication());
	}

	@Action
	public Task showActiveUsers()
	{
		return new ShowActiveUsersActionTask(getApplication());
	}

	@Action
	public Task showCountSysObject()
	{
		return new ShowCountSysObjectActionTask(getApplication());
	}

	public void manageMenuOnLogInAndOut(boolean aloggedIn)
	{
		queryMenu.setEnabled(aloggedIn);
		signInMenuItem.setEnabled(!aloggedIn);
		reportsMenu.setEnabled(aloggedIn);
		treeMenu.setEnabled(aloggedIn);
		queryMenu.setEnabled(aloggedIn);
		if (aloggedIn)
			connectedLabel.setIcon(ICON_CONNECTED);
		else
			connectedLabel.setIcon(ICON_DIS_CONNECTED);
	}

	@Action
	public Task showFolderTreeDump()
	{
		return new ShowFolderTreeDumpActionTask(getApplication());
	}

	@Action
	public Task showFolderTreeList()
	{
		return new ShowFolderTreeListActionTask(getApplication());
	}

	@Action
	public Task showTypeTree()
	{
		return new ShowTypeTreeActionTask(getApplication());
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JMenuItem		aclMenuItem;
	private javax.swing.JMenuItem		activeJobsMenuItem;
	private javax.swing.JMenuItem		activeWorkflowsMenuItem;
	private javax.swing.JMenuItem		actuveUserMenuItem;
	private javax.swing.JLabel			connectedLabel;
	private javax.swing.JMenuItem		connectedUserMenuItem;
	private javax.swing.JMenuItem		countSysObjectMenuItem;
	private javax.swing.JDesktopPane	desktopPane;
	public javax.swing.JLabel			docbaseLabel;
	private javax.swing.JMenuItem		dqlMenuItem;
	private javax.swing.JMenuItem		dumpTreeMenuItem;
	private javax.swing.JMenuItem		folderDocumentTreeMenuItem;
	private javax.swing.JPanel			jPanel1;
	private javax.swing.JPanel			jPanel2;
	private javax.swing.JSeparator		jSeparator1;
	private javax.swing.JSeparator		jSeparator10;
	private javax.swing.JSeparator		jSeparator11;
	private javax.swing.JSeparator		jSeparator2;
	private javax.swing.JSeparator		jSeparator3;
	private javax.swing.JSeparator		jSeparator4;
	private javax.swing.JSeparator		jSeparator6;
	private javax.swing.JSeparator		jSeparator9;
	private javax.swing.JMenuItem		listTypesMenuItem;
	private javax.swing.JPanel			mainPanel;
	private javax.swing.JProgressBar	memoryProgressBar;
	private javax.swing.JMenuBar		menuBar;
	private javax.swing.JProgressBar	progressBar;
	private javax.swing.JMenu			queryMenu;
	private javax.swing.JMenu			reportsMenu;
	private javax.swing.JMenuItem		signInMenuItem;
	private javax.swing.JLabel			statusAnimationLabel;
	private javax.swing.JLabel			statusMessageLabel;
	private javax.swing.JPanel			statusPanel;
	private javax.swing.JMenuItem		systemPropertyMenuItem;
	private javax.swing.JMenu			treeMenu;
	private javax.swing.JMenuItem		typeMenuItem;
	public javax.swing.JLabel			userLabel;
	// End of variables declaration//GEN-END:variables

	private final Timer					messageTimer;
	private final Timer					busyIconTimer;
	private final Icon					idleIcon;
	private final Icon[]				busyIcons		= new Icon[15];
	private int							busyIconIndex	= 0;

	// frames and dialogs
	private JDialog						aboutBox;
	public static JInternalFrame		SIGN_IN_VIEW;
	public static JInternalFrame		SYS_PROP_VIEW;
	public static JInternalFrame		DQL_QUERY_VIEW;
	public static JInternalFrame		CONN_USER_VIEW;
	public static JInternalFrame		ACT_JOB_VIEW;
	public static JInternalFrame		ACT_WORK_VIEW;
	public static JInternalFrame		ACT_USER_VIEW;
	public static JInternalFrame		LIST_TYPE_VIEW;
	public static JInternalFrame		CNT_SYSOBJ_VIEW;
	public static JInternalFrame		LIST_ACL_VIEW;
	public static JInternalFrame		TREE_DUMP_VIEW;
	public static JInternalFrame		TREE_LIST_VIEW;
	public static JInternalFrame		TYPE_TREE_VIEW;
}