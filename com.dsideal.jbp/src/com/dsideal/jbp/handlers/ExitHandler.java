package com.dsideal.jbp.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 退出.
 * @author feilm220
 *
 */
public class ExitHandler {

	private static Logger logger = LoggerFactory.getLogger(ExitHandler.class);
	@Execute
	public void exit(IWorkbench workbench){
		logger.info("workbench will be closed.");
		workbench.close();
	}
}
