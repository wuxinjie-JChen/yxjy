package com.yc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.yc.bean.StudentBean;
import com.yc.commons.DbHelper;
import com.yc.dao.StudentDAO;

public class StudentDaoImpl implements StudentDAO {

	StudentBean bean=new StudentBean();
	DbHelper db=new DbHelper();
	@Override
	public List<StudentBean> find(StudentBean bean,Integer pageNum, Integer pagesize) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select sid,sname,spwd,ssex,stel,sstate,qq from student where sstate=0");
		if(bean!=null){
			if(bean.getSname()!=null){
				sb.append(" and sname like '%"+bean.getSname()+"%'");
			}
		}
		if(null!=pageNum &&null!=pagesize){
			sb.append(" limit "+(pageNum-1)*pagesize+","+pagesize);
		}
		return db.findMutil(sb.toString(),null, StudentBean.class);
	}
	@Override
	public int findByPageTotal(StudentBean bean) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select count(*) from student where sstate=0");
		if(bean!=null){
			if(bean.getSname()!=null){
				sb.append(" and sname like '%"+bean.getSname()+"%'");
			}
		}
		return (int) db.getPolymer(sb.toString(), null);
	}
	@Override
	public int delete(StudentBean bean) throws Exception {
		String sql="update student set sstate=1 where sid=?";
		return db.update(sql, bean.getSid());
	}
	@Override
	public StudentBean login(StudentBean bean) throws Exception {
		String sql="select sid,sname,spwd,ssex,stel,sstate,qq from student where sstate=0 and sname=? and spwd=? ";
		List params=new ArrayList();
		params.add(bean.getSname());
		params.add(bean.getSpwd());
		return db.findSingle(sql,params ,StudentBean.class);
	}
	@Override
	public int update(StudentBean bean) throws Exception {
		String sql="update student set spwd=? where sname=?";
		return db.update(sql, bean.getSpwd(),bean.getSname());
	}
	@Override
	public int reg(StudentBean bean) throws Exception {
		String sql="insert into student(sid,sname,spwd,sstate) values(null,?,?,0)";
		return db.update(sql, bean.getSname(),bean.getSpwd());
	}
	@Override
	public int perfact(StudentBean bean) throws Exception {
		String sql="update student set qq=?,stel=?,ssex=? where sname=?";
		return db.update(sql, bean.getQq(),bean.getStel(),bean.getSsex(),bean.getSname());
	}

}
