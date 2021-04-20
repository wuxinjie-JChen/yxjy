package com.yc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.yc.bean.DataBean;
import com.yc.commons.DbHelper;
import com.yc.dao.DataDAO;
import com.yc.vo.CourseVO;

public class DataDAOImpl implements DataDAO {

	DbHelper db=new DbHelper();
	@Override
	public int upload(DataBean bean) throws Exception {
		String sql="insert into data values(null,?,?,?,0)";
		return db.update(sql, bean.getDname(),bean.getDtype(),bean.getDpath());
	}

	@Override
	public List<DataBean> find(DataBean bean, Integer pageNum, Integer pagesize) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select did,dname,dtype,dpath,dstatus from data where dstatus=0");
		List params=null;
		if(bean!=null){
			params=new ArrayList();
			if(bean.getDname()!=null){
				sb.append(" and dname like '%"+bean.getDname()+"%'");
			}
			if(bean.getDtype()!=null){
				sb.append(" and dtype like '%"+bean.getDtype()+"%'");
			}
			if(bean.getDid()!=null){
				sb.append(" and did=? ");
				params.add(bean.getDid());
			}
			
		}
		if(null!=pageNum &&null!=pagesize){
			sb.append(" limit "+(pageNum-1)*pagesize+","+pagesize);
		}
		return db.findMutil(sb.toString(),params, DataBean.class);
	}

	@Override
	public int delete(DataBean bean) throws Exception {
		String sql="update data set dstatus=1 where did=?";
		return db.update(sql, bean.getDid());
	}

	@Override
	public int findByPageTotal(DataBean bean) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select count(*) from data where dstatus=0");
		List params=null;
		if(bean!=null){
			params=new ArrayList();
			if(bean.getDname()!=null){
				sb.append(" and dname like '%"+bean.getDname()+"%'");
			}
			if(bean.getDtype()!=null){
				sb.append(" and dtype like '%"+bean.getDtype()+"%'");
			}
			
		}
		return (int) db.getPolymer(sb.toString(), params);
	}

}
