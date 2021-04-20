package com.yc.dao;

import java.util.List;

import com.yc.bean.StudentBean;

public interface StudentDAO {

	public List<StudentBean> find(StudentBean bean,Integer pageNum, Integer pagesize) throws Exception;

	public int findByPageTotal(StudentBean bean) throws Exception;
	
	public int delete(StudentBean bean)throws Exception;
	
	public int update(StudentBean bean)throws Exception;
	
	public int reg(StudentBean bean)throws Exception;
	
	public int perfact(StudentBean bean)throws Exception;
	
	public StudentBean login(StudentBean bean)throws Exception;
}
