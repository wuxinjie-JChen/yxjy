package com.yc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.yc.bean.StudentBean;
import com.yc.bean.TeacherBean;
import com.yc.commons.DbHelper;
import com.yc.dao.TeacherDAO;

public class TeacherDaoImpl implements TeacherDAO {

	TeacherBean bean=new TeacherBean();
	DbHelper db=new DbHelper();
	@Override
	public List<TeacherBean> find(TeacherBean bean,Integer pageNum, Integer pagesize) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select tid,tname,tpwd,tsex,ttel,tstate from teacher where tstate=0");
		if(bean!=null){
			if(bean.getTname()!=null){
				sb.append(" and tname like '%"+bean.getTname()+"%'");
			}
		}
		if(null!=pageNum &&null!=pagesize){
			sb.append(" limit "+(pageNum-1)*pagesize+","+pagesize);
		}
		return db.findMutil(sb.toString(), null, TeacherBean.class);
	}
	@Override
	public int findByPageTotal(TeacherBean bean) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select count(*) from teacher where tstate=0");
		if(bean!=null){
			if(bean.getTname()!=null){
				sb.append(" and tname like '%"+bean.getTname()+"%'");
			}
		}
		return (int) db.getPolymer(sb.toString(), null);
	}
	@Override
	public int delete(TeacherBean bean) throws Exception {
		String sql="update teacher set tstate=1 where tid=?";
		return db.update(sql, bean.getTid());
	}
	@Override
	public TeacherBean login(TeacherBean bean) throws Exception {
		String sql="select tid,tname,tpwd,tsex,ttel,tstate from teacher where tstate=0 and tname=? and tpwd=? ";
		List params=new ArrayList();
		params.add(bean.getTname());
		params.add(bean.getTpwd());
		return db.findSingle(sql,params ,TeacherBean.class);
	}
	@Override
	public int addTea(TeacherBean bean) throws Exception {
		String sql="insert into teacher values(null,?,'123456',?,?,0)";
		return db.update(sql, bean.getTname(),bean.getTsex(),bean.getTtel());
	}
	@Override
	public int update(TeacherBean bean) throws Exception {
		String sql="update teacher set tpwd=? where tname=?";
		return db.update(sql, bean.getTpwd(),bean.getTname());
	}

}
