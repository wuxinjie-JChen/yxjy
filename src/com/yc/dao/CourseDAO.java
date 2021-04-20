package com.yc.dao;

import java.util.List;
import com.yc.vo.CourseVO;

public interface CourseDAO {

	public List<CourseVO> find(CourseVO bean,Integer pageNum, Integer pagesize) throws Exception;

	public int findByPageTotal(CourseVO vo) throws Exception;
	
	public int findTotal() throws Exception;
	
	public int delete(CourseVO bean)throws Exception;
	
	public int update(CourseVO bean)throws Exception;
	
	public int addCourse(CourseVO bean)throws Exception;
	
	public int addSc(CourseVO bean)throws Exception;
	
	public List<CourseVO> findCourse(Integer pageNum, Integer pagesize) throws Exception;
	
	public List<CourseVO> showQj(CourseVO bean,Integer pageNum, Integer pagesize) throws Exception;

	public int findQJTotal(CourseVO vo) throws Exception;
	
	public List<CourseVO> findCourseName(int sid) throws Exception;
}
