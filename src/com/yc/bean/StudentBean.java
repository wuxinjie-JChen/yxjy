package com.yc.bean;

import java.io.Serializable;

public class StudentBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6385708529934696L;

	private Integer sid;
	private String sname;
	private String qq;
	private String spwd;
	private String ssex;
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	private String stel;
	private Integer sstate;
	
	
	public Integer getSstate() {
		return sstate;
	}
	public void setSstate(Integer sstate) {
		this.sstate = sstate;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSpwd() {
		return spwd;
	}
	public void setSpwd(String spwd) {
		this.spwd = spwd;
	}
	public String getSsex() {
		return ssex;
	}
	public void setSsex(String ssex) {
		this.ssex = ssex;
	}
	public String getStel() {
		return stel;
	}
	public void setStel(String stel) {
		this.stel = stel;
	}
	@Override
	public String toString() {
		return "StudentBean [sid=" + sid + ", sname=" + sname + ", spwd=" + spwd + ", ssex=" + ssex + ", stel=" + stel
				+ "]";
	}
	
}
