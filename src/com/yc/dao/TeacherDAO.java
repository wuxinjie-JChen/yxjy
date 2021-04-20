package com.yc.dao;

import java.util.List;

import com.yc.bean.TeacherBean;

public interface TeacherDAO {

	public List<TeacherBean> find(TeacherBean bean,Integer pageNum, Integer pagesize) throws Exception;

	public int findByPageTotal(TeacherBean bean) throws Exception;
	
	public int addTea(TeacherBean bean) throws Exception;
	
	public int update(TeacherBean bean) throws Exception;
	
	public int delete(TeacherBean bean)throws Exception;
	
	public TeacherBean login(TeacherBean bean)throws Exception;
}
