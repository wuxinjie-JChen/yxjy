package com.yc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.bean.StudentBean;
import com.yc.bean.TeacherBean;
import com.yc.dao.CourseDAO;
import com.yc.dao.impl.CourseDAOImpl;
import com.yc.util.StringUtil;
import com.yc.util.WebUtil;
import com.yc.vo.CourseVO;


@WebServlet("/course.do")
public class CourseServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

    CourseDAO dao=new CourseDAOImpl();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WebUtil.setEncodings(request, response);
		String op=request.getParameter("op");
		if("findSc".equals(op)){
			doFindSc(request,response);
		}else if(op.equals("delete")){
			doDeletes(request,response);
		}else if(op.equals("findByStudent")){
			doFindByStudent(request,response);
		}else if(op.equals("findByTeacher")){
			doFindByTeacher(request,response);
		}else if(op.equals("findQJ")){
			dofindQJ(request,response);
		}else if(op.equals("addCourse")){
			doAddCourse(request,response);
		}else if(op.equals("addSc")){
			doAddSc(request,response);
		}else if(op.equals("findCourse")){
			doFindCourse(request,response);
		}else if(op.equals("attendance")){
			doAttendance(request,response);
		}else if(op.equals("pj")){
			doPj(request,response);
		}
	}
	private void doPj(HttpServletRequest request, HttpServletResponse response) {
		StudentBean s=(StudentBean) request.getSession().getAttribute("student");
		try {
			List<CourseVO> list=(List<CourseVO>) dao.findCourseName(s.getSid());
			toPrintJson(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private void doAttendance(HttpServletRequest request, HttpServletResponse response) {
		CourseVO vo=parseRequest(request, CourseVO.class);
		try {
			int a=dao.update(vo);
			toPrintJson(response, a);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private void doFindCourse(HttpServletRequest request, HttpServletResponse response) {
		String pagestr=request.getParameter("page");
		String rowStr=request.getParameter("rows");
		try {
			int total=dao.findTotal();
			List<CourseVO> list=null;
			if(StringUtil.isNotNull(rowStr)&&StringUtil.isNotNull(pagestr)){
				list=dao.findCourse(Integer.parseInt(pagestr), Integer.parseInt(rowStr));
				toPrintJson(response, list,total);
			}else{
				list=dao.findCourse(null, null);
				toPrintJson(response, list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void doAddSc(HttpServletRequest request, HttpServletResponse response) {
		CourseVO vo=parseRequest(request, CourseVO.class);
		try {
			int a=dao.addSc(vo);
			toPrintJson(response, a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private void doAddCourse(HttpServletRequest request, HttpServletResponse response) {
		CourseVO vo=parseRequest(request, CourseVO.class);
		try {
			int a=dao.addCourse(vo);
			toPrintJson(response, a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private void dofindQJ(HttpServletRequest request, HttpServletResponse response) {
		CourseVO vo=parseRequest(request, CourseVO.class);
		String pagestr=request.getParameter("page");
		String rowStr=request.getParameter("rows");
		HttpSession session=request.getSession();
		TeacherBean teacher=(TeacherBean) session.getAttribute("teacher");
		StudentBean student=(StudentBean) session.getAttribute("student");
		if(teacher!=null){
			vo.setTname(teacher.getTname());
			vo.setTid(teacher.getTid());
		}
		if(student!=null){
			vo.setSname(student.getSname());
			vo.setSid(student.getSid());
		}
		try {
			int total=dao.findQJTotal(vo);
			List<CourseVO> list=null;
			if(StringUtil.isNotNull(rowStr)&&StringUtil.isNotNull(pagestr)){
				list=dao.showQj(vo, Integer.parseInt(pagestr), Integer.parseInt(rowStr));
				toPrintJson(response, list,total);
			}else{
				list=dao.showQj(vo,null, null);
				toPrintJson(response, list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void doFindByTeacher(HttpServletRequest request, HttpServletResponse response) {
		CourseVO vo=parseRequest(request, CourseVO.class);
		HttpSession session=request.getSession();
		TeacherBean teacher=(TeacherBean) session.getAttribute("teacher");
		if(teacher!=null){
			vo.setTname(teacher.getTname());
			vo.setTid(teacher.getTid());
		}
		String pagestr=request.getParameter("page");
		String rowStr=request.getParameter("rows");
		try {
			int total=dao.findByPageTotal(vo);
			List<CourseVO> list=null;
			if(StringUtil.isNotNull(rowStr)&&StringUtil.isNotNull(pagestr)){
				list=dao.find(vo, Integer.parseInt(pagestr), Integer.parseInt(rowStr));
				toPrintJson(response, list,total);
			}else{
				list=dao.find(vo,null, null);
				toPrintJson(response, list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void doFindByStudent(HttpServletRequest request, HttpServletResponse response) {
		CourseVO vo=parseRequest(request, CourseVO.class);
		HttpSession session=request.getSession();
		StudentBean student=(StudentBean) session.getAttribute("student");
		if(student!=null){
			vo.setSname(student.getSname());
			vo.setSid(student.getSid());
		}
		String pagestr=request.getParameter("page");
		String rowStr=request.getParameter("rows");
		try {
			int total=dao.findByPageTotal(vo);
			List<CourseVO> list=null;
			if(StringUtil.isNotNull(rowStr)&&StringUtil.isNotNull(pagestr)){
				list=dao.find(vo, Integer.parseInt(pagestr), Integer.parseInt(rowStr));
				toPrintJson(response, list,total);
			}else{
				list=dao.find(vo,null, null);
				toPrintJson(response, list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void doDeletes(HttpServletRequest request, HttpServletResponse response) {
		CourseVO vo=parseRequest(request, CourseVO.class);
		try {
			int a=dao.delete(vo);
			toPrintJson(response, a);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void doFindSc(HttpServletRequest request, HttpServletResponse response) {
		CourseVO vo=parseRequest(request, CourseVO.class);
		String pagestr=request.getParameter("page");
		String rowStr=request.getParameter("rows");
		try {
			int total=dao.findByPageTotal(vo);
			List<CourseVO> list=null;
			if(StringUtil.isNotNull(rowStr)&&StringUtil.isNotNull(pagestr)){
				list=dao.find(vo, Integer.parseInt(pagestr), Integer.parseInt(rowStr));
				toPrintJson(response, list,total);
			}else{
				list=dao.find(vo,null, null);
				toPrintJson(response, list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
