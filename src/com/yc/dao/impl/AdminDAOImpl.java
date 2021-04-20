package com.yc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.yc.bean.AdminBean;
import com.yc.bean.TeacherBean;
import com.yc.commons.DbHelper;
import com.yc.dao.AdminDAO;

public class AdminDAOImpl implements AdminDAO {

	DbHelper db=new DbHelper();
	@Override
	public AdminBean login(AdminBean bean) throws Exception {
		String sql="select aid,aname,apwd from admin where aname=? and apwd=? ";
		List params=new ArrayList();
		params.add(bean.getAname());
		params.add(bean.getApwd());
		return db.findSingle(sql,params ,AdminBean.class);
	}
	@Override
	public int update(AdminBean bean) throws Exception {
		String sql="update admin set apwd=? where aname=?";
		return db.update(sql,bean.getApwd(), bean.getAname());
	}

}
