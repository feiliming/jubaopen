package com.dsideal.jbp.bt.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * 倍投关系Model.
 * 
 * @author feilm220
 * 
 */
public class Multiple implements PropertyChangeListener {

	private Integer zhqs;//追号期数
	private Integer trzs;//投入注数
	private Integer csbs;//初始倍数
	private Integer zdbs;//最大倍数
	private Integer dzjj;//单注奖金
	
	private Integer zxsyl; //最小收益率
	private Integer q; //前多少期
	private Integer qsyl; //前多少期的收益率
	private Integer hsyl; //之后收益率
	private Integer zdsy;//最低盈利收益

	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	public Multiple() {
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		propertyChangeSupport.firePropertyChange(event);
	}

	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	public Integer getZhqs() {
		return zhqs;
	}

	public void setZhqs(Integer zhqs) {
		propertyChangeSupport.firePropertyChange("zhqs", this.zhqs, this.zhqs = zhqs);
	}

	public Integer getTrzs() {
		return trzs;
	}

	public void setTrzs(Integer trzs) {
		propertyChangeSupport.firePropertyChange("trzs", this.trzs, this.trzs = trzs);;
	}

	public Integer getCsbs() {
		return csbs;
	}

	public void setCsbs(Integer csbs) {
		propertyChangeSupport.firePropertyChange("csbs", this.csbs, this.csbs = csbs);
	}

	public Integer getZdbs() {
		return zdbs;
	}

	public void setZdbs(Integer zdbs) {
		propertyChangeSupport.firePropertyChange("zdbs", this.zdbs, this.zdbs = zdbs);
	}

	public Integer getDzjj() {
		return dzjj;
	}

	public void setDzjj(Integer dzjj) {
		propertyChangeSupport.firePropertyChange("dzjj", this.dzjj, this.dzjj = dzjj);
	}

	public Integer getZxsyl() {
		return zxsyl;
	}

	public void setZxsyl(Integer zxsyl) {
		propertyChangeSupport.firePropertyChange("zxsyl", this.zxsyl, this.zxsyl = zxsyl);
	}

	public Integer getQ() {
		return q;
	}

	public void setQ(Integer q) {
		propertyChangeSupport.firePropertyChange("q", this.q, this.q = q);
	}

	public Integer getQsyl() {
		return qsyl;
	}

	public void setQsyl(Integer qsyl) {
		propertyChangeSupport.firePropertyChange("qsyl", this.qsyl, this.qsyl = qsyl);
	}

	public Integer getHsyl() {
		return hsyl;
	}

	public void setHsyl(Integer hsyl) {
		propertyChangeSupport.firePropertyChange("hsyl", this.hsyl, this.hsyl = hsyl);
	}

	public Integer getZdsy() {
		return zdsy;
	}

	public void setZdsy(Integer zdsy) {
		propertyChangeSupport.firePropertyChange("zdsy", this.zdsy, this.zdsy = zdsy);
	}

}
