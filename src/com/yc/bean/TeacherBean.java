package com.yc.bean;

import java.io.Serializable;

public class TeacherBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5093157325306886375L;

	private Integer tid;
	private String tname;
	private String tpwd;
	private String tsex;
	private String ttel;
	private Integer tstate;
	public Integer getTstate() {
		return tstate;
	}
	public void setTstate(Integer tstate) {
		this.tstate = tstate;
	}
	
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTpwd() {
		return tpwd;
	}
	public void setTpwd(String tpwd) {
		this.tpwd = tpwd;
	}
	public String getTsex() {
		return tsex;
	}
	public void setTsex(String tsex) {
		this.tsex = tsex;
	}
	public String getTtel() {
		return ttel;
	}
	public void setTtel(String ttel) {
		this.ttel = ttel;
	}
	@Override
	public String toString() {
		return "TeacherBean [tid=" + tid + ", tname=" + tname + ", tpwd=" + tpwd + ", tsex=" + tsex + ", ttel=" + ttel
				+ "]";
	}
	
	
}
