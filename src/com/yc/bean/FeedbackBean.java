package com.yc.bean;

import java.io.Serializable;

public class FeedbackBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3930103415073877251L;
	private Integer fid;
	private String fname;
	private String fcon;
	
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getFcon() {
		return fcon;
	}
	public void setFcon(String fcon) {
		this.fcon = fcon;
	}
	@Override
	public String toString() {
		return "FeedbackBean [fid=" + fid + ", fname=" + fname + ", fcon=" + fcon + "]";
	}
	
	
}
