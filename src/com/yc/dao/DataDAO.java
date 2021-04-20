package com.yc.dao;

import java.util.List;

import com.yc.bean.DataBean;

public interface DataDAO {

	public int upload(DataBean bean) throws Exception;
	
	public List<DataBean> find(DataBean bean, Integer pageNum, Integer pagesize) throws Exception;
	
	public int delete(DataBean bean) throws Exception;
	
	public int findByPageTotal(DataBean bean) throws Exception;
}
