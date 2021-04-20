package com.yc.dao;

import com.yc.bean.AdminBean;

public interface AdminDAO {

	public AdminBean login(AdminBean bean) throws Exception;
	
	public int update(AdminBean bean) throws Exception;
}
