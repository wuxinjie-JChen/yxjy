package com.yc.vo;

import java.io.Serializable;
import java.util.Date;

public class CourseVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1833798414710587699L;

	private Integer scid;
	private Integer fid;
	private Integer cid;
	private Integer tid;
	private Integer sid;
	private String scdaytime;
	private String scqtime;
	private String scotime;
	private String scstime;
	private Integer scstate;
	private String sname;
	private String tname;
	private String cname;
	private String ctype;
	private String lcon;
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	public String getLcon() {
		return lcon;
	}
	public void setLcon(String lcon) {
		this.lcon = lcon;
	}
	public Integer getScid() {
		return scid;
	}
	public void setScid(Integer scid) {
		this.scid = scid;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getScdaytime() {
		return scdaytime;
	}
	public void setScdaytime(String scdaytime) {
		this.scdaytime = scdaytime;
	}
	public String getScqtime() {
		return scqtime;
	}
	public void setScqtime(String scqtime) {
		this.scqtime = scqtime;
	}
	public String getScotime() {
		return scotime;
	}
	public void setScotime(String scotime) {
		this.scotime = scotime;
	}
	public String getScstime() {
		return scstime;
	}
	public void setScstime(String scstime) {
		this.scstime = scstime;
	}
	public Integer getScstate() {
		return scstate;
	}
	public void setScstate(Integer scstate) {
		this.scstate = scstate;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
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
		return "CourseVO [scid=" + scid + ", cid=" + cid + ", tid=" + tid + ", sid=" + sid + ", scdaytime=" + scdaytime
				+ ", scqtime=" + scqtime + ", scotime=" + scotime + ", scstime=" + scstime + ", scstate=" + scstate
				+ ", sname=" + sname + ", tname=" + tname + ", cname=" + cname + ", ctype=" + ctype + "]";
	}
	
	
}
