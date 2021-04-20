package com.yc.dao.impl;

import java.util.ArrayList;
import java.util.List;
import com.yc.commons.DbHelper;
import com.yc.dao.CourseDAO;
import com.yc.vo.CourseVO;

public class CourseDAOImpl implements CourseDAO {

	DbHelper db=new DbHelper();
	@Override
	public List<CourseVO> find(CourseVO vo, Integer pageNum, Integer pagesize) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select sc.cid,scid,sname,tname,scdaytime,scqtime,scotime,scstime,cname,ctype,scstate"
				+ " from sc inner join course"
				+ " inner join teacher"
				+ " inner join student"
				+ " where course.cid=sc.cid"
				+ " and sc.sid=student.sid"
				+ " and sc.tid=teacher.tid and status=0");
		List params=null;
		System.out.println(vo.getSname());
		if(vo!=null){
			params=new ArrayList();
			if(vo.getCname()!=null){
				sb.append(" and cname like '%"+vo.getCname()+"%'");
			}
			if(vo.getSname()!=null){
				sb.append(" and sname like '%"+vo.getSname()+"%'");
			}
			if(vo.getTname()!=null){
				sb.append(" and tname like '%"+vo.getTname()+"%'");
			}
			if(vo.getScstate()!=null){
				sb.append(" and scstate=?");
				params.add(vo.getScstate());
			}
			if(vo.getCtype()!=null){
				sb.append(" and ctype=?");
				params.add(vo.getCtype());
			}
		}
		if(null!=pageNum &&null!=pagesize){
			sb.append(" limit "+(pageNum-1)*pagesize+","+pagesize);
		}
		return db.findMutil(sb.toString(),params, CourseVO.class);
	}

	@Override
	public int findByPageTotal(CourseVO vo) throws Exception {
		List params=null;
		StringBuffer sb=new StringBuffer();
		sb.append("select count(*) from sc inner join course"
				+ " inner join teacher"
				+ " inner join student"
				+ " where course.cid=sc.cid"
				+ " and sc.sid=student.sid"
				+ " and sc.tid=teacher.tid and status=0");
		if(vo!=null){
			params=new ArrayList();
			if(vo.getSid()!=null){
				sb.append(" and sc.sid=? ");
				params.add(vo.getSid());
			}
			if(vo.getTid()!=null){
				sb.append(" and sc.tid=? ");
				params.add(vo.getTid());
			}
			if(vo.getScstate()!=null){
				sb.append(" and scstate=? ");
				params.add(vo.getScstate());
			}
			if(vo.getCtype()!=null){
				sb.append(" and ctype=? ");
				params.add(vo.getCtype());
			}
			if(vo.getCname()!=null){
				sb.append(" and cname like '%"+vo.getCname()+"%'");
			}
			if(vo.getSname()!=null){
				sb.append(" and sname like '%"+vo.getSname()+"%'");
			}
			if(vo.getTname()!=null){
				sb.append(" and tname like '%"+vo.getTname()+"%'");
			}
		}
		return (int) db.getPolymer(sb.toString(), params);
	}

	@Override
	public int delete(CourseVO vo) throws Exception {
		String sql="update sc set status=1 where scid=?";
		return db.update(sql, vo.getScid());
	}

	@Override
	public List<CourseVO> showQj(CourseVO vo, Integer pageNum, Integer pagesize) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select sc.scid,lcon,sname,tname,scdaytime,scqtime,scotime,scstime,cname,ctype,scstate"
				+ " from sc inner join course"
				+ " inner join teacher"
				+ " inner join student"
				+ " inner join qj"
				+ " where course.cid=sc.cid"
				+ " and sc.sid=student.sid"
				+ " and sc.tid=teacher.tid"
				+ " and sc.scid=qj.scid and status=0");
		List params=null;
		if(vo!=null){
			params=new ArrayList();
			if(vo.getSname()!=null){
				params.add(vo.getSname());
				sb.append(" and sname=?");
				
			}
			if(vo.getTname()!=null){
				params.add(vo.getTname());
				sb.append(" and tname=?");
			}
		}
		if(null!=pageNum &&null!=pagesize){
			sb.append(" limit "+(pageNum-1)*pagesize+","+pagesize);
		}
		return db.findMutil(sb.toString(),params, CourseVO.class);
	}

	@Override
	public int findQJTotal(CourseVO vo) throws Exception {
		List params=null;
		String sql="";
		if(vo!=null){
			if(vo.getSid()!=null){
				params=new ArrayList();
				params.add(vo.getSid());
				sql="select count(*) from qj inner join sc where sc.scid=qj.scid and sid=?";
				return (int) db.getPolymer(sql, params);
			}
			if(vo.getTid()!=null){
				params=new ArrayList();
				params.add(vo.getTid());
				sql="select count(*) from qj inner join sc where sc.scid=qj.scid and tid=?";
				return (int) db.getPolymer(sql, params);
			}
		}
		sql="select count(*) from qj inner join sc where sc.scid=qj.scid";
		return (int) db.getPolymer(sql, params);
	}

	@Override
	public int addCourse(CourseVO bean) throws Exception {
		String sql1="insert into course values(null,?,?)";
		return db.update(sql1, bean.getCname(),bean.getCtype());
	}

	@Override
	public int addSc(CourseVO bean) throws Exception {
		String sql="insert into sc values(null,?,?,?,?,?,0,now(),?,0)";
		return db.update(sql, bean.getSid(),bean.getCid(),bean.getTid(),bean.getScdaytime(),bean.getScqtime(),bean.getScstime());
	}

	@Override
	public List<CourseVO> findCourse( Integer pageNum, Integer pagesize) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select cid,cname,ctype from course");
		if(null!=pageNum &&null!=pagesize){
			sb.append(" limit "+(pageNum-1)*pagesize+","+pagesize);
		}
		return db.findMutil(sb.toString(), null, CourseVO.class);
	}

	@Override
	public int findTotal() throws Exception {
		String sql="select count(*) from course";
		return (int) db.getPolymer(sql, null);
	}

	@Override
	public int update(CourseVO bean) throws Exception {
		String sql="update sc set scstate=? where scid=?";
		return db.update(sql, bean.getScstate(),bean.getScid());
	}

	@Override
	public List<CourseVO> findCourseName(int sid) throws Exception {
		String sql="select distinct sc.cid,cname from sc inner join course where sc.cid=course.cid and sid="+sid;
		return db.findMutil(sql, null,CourseVO.class);
	}
	
}
