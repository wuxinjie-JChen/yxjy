package com.yc.bean;

import java.io.Serializable;
import java.util.Date;

public class ScBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3027599130682706724L;

	private Integer scid;
	private Integer cid;
	private Integer tid;
	private Integer sid;
	private String scdaytime;
	private String scqtime;
	private Date scotime;
	private Date scstime;
	private Integer scstate;
	
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

	public Date getScotime() {
		return scotime;
	}

	public void setScotime(Date scotime) {
		this.scotime = scotime;
	}

	public Date getScstime() {
		return scstime;
	}

	public void setScstime(Date scstime) {
		this.scstime = scstime;
	}

	public Integer getScstate() {
		return scstate;
	}

	public void setScstate(Integer scstate) {
		this.scstate = scstate;
	}

	@Override
	public String toString() {
		return "ScBean [scid=" + scid + ", cid=" + cid + ", tid=" + tid + ", sid=" + sid + ", scdaytime=" + scdaytime
				+ ", scqtime=" + scqtime + ", scotime=" + scotime + ", scstime=" + scstime + ", scstate=" + scstate
				+ "]";
	}
	
}
