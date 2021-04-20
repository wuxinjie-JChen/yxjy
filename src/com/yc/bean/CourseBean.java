package com.yc.bean;

import java.io.Serializable;

public class CourseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6120437520551809133L;

	private Integer cid;
	private String cname;
	private String ctype;
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCtype() {
		return ctype;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	@Override
	public String toString() {
		return "CourseBean [cid=" + cid + ", cname=" + cname + ", ctype=" + ctype + "]";
	}
	
}
