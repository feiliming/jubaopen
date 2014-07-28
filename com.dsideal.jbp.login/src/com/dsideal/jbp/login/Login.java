package com.dsideal.jbp.login;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.osgi.service.prefs.BackingStoreException;

/**
 * 登录.
 * @author feilm220
 *
 */
public class Login {

	@PostContextCreate
	public void login(@Preference IEclipsePreferences preferences,
			@Preference(value = "user") String userPreference,
			@Preference(value = "password") String passwordPreference){
		
		final Shell shell = new Shell(SWT.INHERIT_NONE);
		final LoginDialog dialog = new LoginDialog(shell);
		if (userPreference != null) {
			dialog.setUser(userPreference);
		}
		if (passwordPreference != null) {
			dialog.setPassword(passwordPreference);
		}
		dialog.create();
		
		if (dialog.open() != Window.OK) {
			// we don't have a workbench yet...
			System.exit(0);
		} else {
			preferences.put("user", dialog.getUser());
			preferences.put("password", dialog.getPassword());
			try {
				preferences.flush();
			} catch (BackingStoreException e) {
				e.printStackTrace();
			}
		}
	}
}
