package com.yc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.yc.bean.FeedbackBean;
import com.yc.bean.StudentBean;
import com.yc.commons.DbHelper;
import com.yc.dao.FeedbackDAO;

public class FeedbackDAOImpl implements FeedbackDAO {

	DbHelper db=new DbHelper();
	@Override
	public int addFeedback(FeedbackBean bean) throws Exception {
		String sql="insert into feedback values(null,?,?)";
		return db.update(sql,bean.getFname(),bean.getFcon());
	}

	@Override
	public List<FeedbackBean> find(Integer pageNum, Integer pagesize) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select fid,fname,fcon from feedback");
		
		if(null!=pageNum &&null!=pagesize){
			sb.append(" limit "+(pageNum-1)*pagesize+","+pagesize);
		}
		return db.findMutil(sb.toString(),null, FeedbackBean.class);
	}

	@Override
	public int findByPageTotal() throws Exception {
		String sql="select count(*) from feedback";
		return (int) db.getPolymer(sql, null);
	}

}
