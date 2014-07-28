package com.dsideal.jbp.login;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class LoginDialog extends Dialog{

	private Text userText;
	private Text passwordText;
	private Label errorMessageLabel;
	private String user = "";
	private String password = "";

	protected Composite loginComposite;
	private final Shell shell;
	private Image titleImage;
	private ImageDescriptor imageDescriptor;

	public LoginDialog() {
		this(Display.getCurrent().getActiveShell());
	}

	public LoginDialog(Shell parentShell) {
		super(parentShell);
		this.shell = parentShell;
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("登录");
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		parent.setBackgroundMode(SWT.INHERIT_DEFAULT);
		Composite control = createContentArea(parent);
//		control.setData("org.eclipse.e4.ui.css.id", "LoginDialog");
		Rectangle controlRect = control.getBounds();
		
		// looks strange in multi monitor environments
		// Rectangle displayBounds = shell.getDisplay().getBounds();
		
		Monitor primary = shell.getDisplay().getPrimaryMonitor();
	    Rectangle displayBounds = primary.getBounds();
	    
		int x = (displayBounds.width - controlRect.width) / 2;
		int y = (displayBounds.height - controlRect.height) / 2;
		shell.setBounds(x, y, controlRect.width, controlRect.height);

		return control;
	}

	protected Composite createContentArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		gridLayout.marginHeight = 0;
		gridLayout.marginWidth = 0;
		composite.setLayout(gridLayout);

		if (imageDescriptor == null) {
			imageDescriptor = imageDescriptorFromURI(URI.createURI("platform:/plugin/com.dsideal.jbp.login/icons/juno.png"));
		}
		if (imageDescriptor != null) {
			titleImage = imageDescriptor.createImage();
			Label imageLabel = new Label(composite, SWT.NONE);

			GridData data = new GridData();
			data.horizontalAlignment = GridData.FILL;
			data.verticalAlignment = GridData.BEGINNING;
			data.horizontalSpan = 1;
			imageLabel.setLayoutData(data);
			imageLabel.setImage(titleImage);
		}
		
		Composite userPasswordComposite = new Composite(composite, SWT.NONE);
//		userPasswordComposite.setData("org.eclipse.e4.ui.css.id", "LoginDialog");
		GridLayout gridLayout2 = new GridLayout(2,false);
		gridLayout2.marginHeight = 0;
		gridLayout2.marginWidth = 30;
		userPasswordComposite.setLayout(gridLayout2);
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		userPasswordComposite.setLayoutData(gridData);
		
		//输入提示
		Label l = new Label(userPasswordComposite, SWT.NONE);
		l.setText("");
		errorMessageLabel = new Label(userPasswordComposite, SWT.NONE);
		errorMessageLabel.setText("");
		gridData = new GridData(GridData.GRAB_HORIZONTAL
                | GridData.HORIZONTAL_ALIGN_FILL);
		errorMessageLabel.setLayoutData(gridData);
		
		Label userLabel = new Label(userPasswordComposite, SWT.NONE);
		userLabel.setText("用户名: ");
		userText = new Text(userPasswordComposite, SWT.MULTI | SWT.WRAP);
		userText.setText(user);
		gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		userText.setLayoutData(gridData);
		userText.addTraverseListener(new TraverseListener() {
		    public void keyTraversed(TraverseEvent e) {
				if (e.detail == SWT.TRAVERSE_TAB_NEXT || e.detail == SWT.TRAVERSE_TAB_PREVIOUS) {
				  e.doit = true;
				}
		    }
		});
		
		Label passwordLabel = new Label(userPasswordComposite, SWT.NONE);
		passwordLabel.setText("密   码: ");
		passwordText = new Text(userPasswordComposite, SWT.PASSWORD);
		passwordText.setText(password);
		
		gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		passwordText.setLayoutData(gridData);
		
		//添加监听
//		addListeners();
		
		return composite;
	}
	
	private void addListeners(){
//		userText.addFocusListener(new FocusListener() {
//			@Override
//			public void focusLost(FocusEvent e) {
//				
//			}
//			
//			@Override
//			public void focusGained(FocusEvent e) {
//				errorMessageLabel.setText("");
//			}
//		});
		userText.addTraverseListener(new TraverseListener() {
			
			@Override
			public void keyTraversed(TraverseEvent e) {
				if (e.detail == SWT.TRAVERSE_TAB_NEXT || e.detail == SWT.TRAVERSE_TAB_PREVIOUS) {
			          e.doit = true;
		        }
			}
		});
//		passwordText.addFocusListener(new FocusListener() {
//			@Override
//			public void focusLost(FocusEvent e) {
//				
//			}
//			
//			@Override
//			public void focusGained(FocusEvent e) {
//				errorMessageLabel.setText("");
//			}
//		});
//		passwordText.addTraverseListener(new TraverseListener() {
//			
//			@Override
//			public void keyTraversed(TraverseEvent e) {
//				if (e.detail == SWT.TRAVERSE_TAB_NEXT || e.detail == SWT.TRAVERSE_TAB_PREVIOUS) {
//					e.doit = true;
//		        }
//			}
//		});
	};
	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		// create OK and Cancel buttons by default
		parent.setBackgroundMode(SWT.INHERIT_DEFAULT);
		createButton(parent, IDialogConstants.OK_ID, "登录",
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				"取消", false);
	}

	@Override
	public boolean close() {
		if (titleImage != null) {
			titleImage.dispose();
		}
		return super.close();
	}
	
	@Override
	protected void okPressed() {
		user = userText.getText();
		password = passwordText.getText();
		if("".equals(user)){
			//MessageDialog.open(MessageDialog.WARNING, shell, "提示", "用户名不能为空！", SWT.NONE);//MessageDialog不能调整大小
			//InputDialog inputDialog = new InputDialog(shell, "提示", "用户名不能为空！", "", new MyValidator());
			//inputDialog.open();
			errorMessageLabel.setText("请输入用户名.");
			return;
		}
		if("".equals(password)){
			errorMessageLabel.setText("请输入密码.");
			return;
		}
		if(!"admin".equals(user) && !"admin".equals(password)){
			errorMessageLabel.setText("用户名和密码不正确.");
			return;
		}
		super.okPressed();
	}

	public ImageDescriptor imageDescriptorFromURI(URI iconPath) {
		try {
			return ImageDescriptor.createFromURL(new URL(iconPath.toString()));
		} catch (MalformedURLException e) {
			return ImageDescriptor.getMissingImageDescriptor();
		}
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
