package org.eshark.dctm.task;

import com.documentum.fc.common.DfException;
import java.beans.PropertyVetoException;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import org.jdesktop.application.ResourceMap;
import org.eshark.dctm.MainApplication;
import org.eshark.dctm.gui.LoginView;
import static org.eshark.dctm.gui.MainMDIView.SIGN_IN_VIEW;


/**
 * <TABLE BORDER="1" WIDTH="100%">
 * <TR>
 * <TH>Perticulars</TH><TH>::</TH><TH>Details</TH>
 * </TR>
 * <TR>
 * <TD>Project Name</TD><TD>::</TD><TD>Query Builder D-SIX</TD>
 * </TR>
 * <TR>
 * <TD>File Name</TD><TD>::</TD><TD>ShowSignInActionTask.java</TD>
 * </TR>
 * <TR>
 * <TD>Created on</TD><TD>::</TD><TD>Sep 22, 2008 11:49:17 AM</TD>
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
public class ShowSignInActionTask extends org.jdesktop.application.Task<String, Void>
{
	public ShowSignInActionTask(org.jdesktop.application.Application app)
	{
		super(app);
	}

	@Override
	protected String doInBackground() throws DfException, PropertyVetoException
	{
		JDesktopPane desktop = MainApplication.getApplication().getDesktopPane();
		if (SIGN_IN_VIEW == null)
		{
			SIGN_IN_VIEW = new LoginView();
			desktop.add(SIGN_IN_VIEW);
			SIGN_IN_VIEW.pack();
			SIGN_IN_VIEW.show();
			SIGN_IN_VIEW.setSelected(true);
			SIGN_IN_VIEW.validate();
		}
		return "succeeded"; // return your result
	}

	@Override
	protected void failed(Throwable aException)
	{
		JDesktopPane desktop = MainApplication.getApplication().getDesktopPane();
		ResourceMap resourceMap = MainApplication.getApplication().getContext().getResourceMap(MainApplication.class);
		JOptionPane.showInternalMessageDialog(desktop, aException.getMessage(),
				resourceMap.getString("Applicaion.err"), JOptionPane.ERROR_MESSAGE);
	}
}