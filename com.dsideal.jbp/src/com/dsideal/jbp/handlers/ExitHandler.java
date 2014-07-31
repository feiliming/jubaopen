package com.dsideal.jbp.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.IWorkbench;

/**
 * 退出.
 * @author feilm220
 *
 */
public class ExitHandler {

	@Execute
	public void exit(IWorkbench workbench){
		workbench.close();
	}
}
