package com.dsideal.jbp.login;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class TextTraverseListenerTab {

	  public static void main(String[] args) {
	    Display display = new Display();
	    Shell shell = new Shell(display);
	    shell.setBounds(10, 10, 200, 200);
	    
	    
	    Text text1 = new Text(shell, SWT.MULTI | SWT.WRAP);
	    text1.setBounds(10, 10, 150, 50);
	    text1.setText("Tab will traverse out from here.");
	    text1.addTraverseListener(new TraverseListener() {
	      public void keyTraversed(TraverseEvent e) {
	        if (e.detail == SWT.TRAVERSE_TAB_NEXT || e.detail == SWT.TRAVERSE_TAB_PREVIOUS) {
	          e.doit = true;
	        }
	      }
	    });
	    
	    
	    Text text2 = new Text(shell, SWT.MULTI | SWT.WRAP);
	    text2.setBounds(10, 100, 150, 50);
	    text2.setText("But Tab will NOT traverse out from here (Ctrl+Tab will).");
	    
	    shell.open();
	    while (!shell.isDisposed()) {
	      if (!display.readAndDispatch())
	        display.sleep();
	    }
	    display.dispose();
	  }
	  
	}