package com.dsideal.jbp.bt.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * 计算结果.
 * 
 * @author feilm220
 *
 */
public class MultipleResult {

	private String qs;
	private String trzs;
	private String bs;
	private String dqtr;
	private String ljtr;
	private String dqsy;
	private String ylsy;
	private String syl;
	
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	public MultipleResult() {
	}

	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}
	
	public String getQs() {
		return qs;
	}
	public void setQs(String qs) {
		propertyChangeSupport.firePropertyChange("qs", this.qs, this.qs = qs);
	}
	public String getTrzs() {
		return trzs;
	}

	public void setTrzs(String trzs) {
		propertyChangeSupport.firePropertyChange("trzs", this.trzs, this.trzs = trzs);
	}

	public String getBs() {
		return bs;
	}
	public void setBs(String bs) {
		propertyChangeSupport.firePropertyChange("bs", this.bs, this.bs = bs);
	}
	public String getDqtr() {
		return dqtr;
	}
	public void setDqtr(String dqtr) {
		propertyChangeSupport.firePropertyChange("dqtr", this.dqtr, this.dqtr = dqtr);
	}
	public String getLjtr() {
		return ljtr;
	}
	public void setLjtr(String ljtr) {
		propertyChangeSupport.firePropertyChange("ljtr", this.ljtr, this.ljtr = ljtr);
	}
	public String getDqsy() {
		return dqsy;
	}
	public void setDqsy(String dqsy) {
		propertyChangeSupport.firePropertyChange("dqsy", this.dqsy, this.dqsy = dqsy);
	}
	public String getYlsy() {
		return ylsy;
	}
	public void setYlsy(String ylsy) {
		propertyChangeSupport.firePropertyChange("ylsy", this.ylsy, this.ylsy = ylsy);
	}
	public String getSyl() {
		return syl;
	}
	public void setSyl(String syl) {
		propertyChangeSupport.firePropertyChange("syl", this.syl, this.syl = syl);
	}
	
}
