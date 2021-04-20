package com.yc.dao;

import java.util.List;

import com.yc.bean.FeedbackBean;

public interface FeedbackDAO {

	public int addFeedback(FeedbackBean bean)throws Exception;
	
	public List<FeedbackBean> find(Integer pageNum, Integer pagesize)throws Exception;

	public int findByPageTotal() throws Exception;
}
