package com.yc.bean;

import java.io.Serializable;

public class DataBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9101452241954783988L;

	private Integer did;
	private String dname;
	private String dtype;
	private String dpath;
	private Integer dstatus;
	
	public String getDpath() {
		return dpath;
	}
	public void setDpath(String dpath) {
		this.dpath = dpath;
	}
	public Integer getDstatus() {
		return dstatus;
	}
	public void setDstatus(Integer dstatus) {
		this.dstatus = dstatus;
	}
	public Integer getDid() {
		return did;
	}
	public void setDid(Integer did) {
		this.did = did;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getDtype() {
		return dtype;
	}
	public void setDtype(String dtype) {
		this.dtype = dtype;
	}
	@Override
	public String toString() {
		return "DataBean [did=" + did + ", dname=" + dname + ", dtype=" + dtype + ", dpath=" + dpath + ", dstatus="
				+ dstatus + "]";
	}
	
	
}
