package com.yc.dao.impl;

import com.yc.bean.QjBean;
import com.yc.commons.DbHelper;
import com.yc.dao.QjDAO;

public class QjDAOImpl implements QjDAO {

	DbHelper db=new DbHelper();
	@Override
	public int addQj(QjBean bean) throws Exception {
		String sql="insert into qj values(null,?,?)";
		return db.update(sql, bean.getScid(),bean.getLcon());
	}

}
