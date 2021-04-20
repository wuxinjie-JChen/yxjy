package com.yc.bean;

import java.io.Serializable;

public class QjBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8609739908453183898L;

	private Integer lid;
	private Integer scid;
	private String lcon;
	public Integer getLid() {
		return lid;
	}
	public void setLid(Integer lid) {
		this.lid = lid;
	}
	public Integer getScid() {
		return scid;
	}
	public void setScid(Integer scid) {
		this.scid = scid;
	}
	public String getLcon() {
		return lcon;
	}
	public void setLcon(String lcon) {
		this.lcon = lcon;
	}
	@Override
	public String toString() {
		return "LeaveBean [lid=" + lid + ", scid=" + scid + ", lcon=" + lcon + "]";
	}
	
	
}
