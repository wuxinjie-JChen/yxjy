package com.yc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.StudentBean;
import com.yc.bean.TeacherBean;
import com.yc.dao.TeacherDAO;
import com.yc.dao.impl.TeacherDaoImpl;
import com.yc.util.StringUtil;
import com.yc.util.WebUtil;


@WebServlet("/teacher.do")
public class TeacherServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    TeacherDAO teacherDao=new TeacherDaoImpl();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WebUtil.setEncodings(request, response);
		String op=request.getParameter("op");
		if("find".equals(op)){
			doFind(request,response);
		}else if(op.equals("delete")){
			doDeletes(request,response);
		}else if(op.equals("add")){
			doAdd(request,response);
		}else if(op.equals("update")){
			doUpdate(request,response);
		}
	
	}
	
	private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		TeacherBean bean=super.parseRequest(request, TeacherBean.class);
		String oldPwd=request.getParameter("oldPwd");
		String newPwd=request.getParameter("newPwd");
		TeacherBean teacher=(TeacherBean) request.getSession().getAttribute("teacher");
		bean.setTpwd(newPwd);
		if(!oldPwd.equals(teacher.getTpwd())){
			toPrintJson(response, 0);
		}else{
			try {
				int a=teacherDao.update(bean);
				if(a==1){
					request.getSession().setAttribute("teacher", bean);
				}
				toPrintJson(response, a);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	private void doAdd(HttpServletRequest request, HttpServletResponse response) {
		TeacherBean teacherBean=super.parseRequest(request, TeacherBean.class);
		try {
			int a=teacherDao.addTea(teacherBean);
			toPrintJson(response, a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private void doDeletes(HttpServletRequest request, HttpServletResponse response) {
		TeacherBean bean=super.parseRequest(request, TeacherBean.class);
		try {
			int a=teacherDao.delete(bean);
			toPrintJson(response, a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private void doFind(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TeacherBean bean=super.parseRequest(request, TeacherBean.class);
		String pagestr=request.getParameter("page");
		String rowStr=request.getParameter("rows");
		try {
			int total=teacherDao.findByPageTotal(bean);
			List<TeacherBean> list=null;
			if(StringUtil.isNotNull(rowStr)&&StringUtil.isNotNull(pagestr)){
				list=teacherDao.find(bean, Integer.parseInt(pagestr), Integer.parseInt(rowStr));
				toPrintJson(response, list,total);
			}else{
				list=teacherDao.find(bean,null, null);
				toPrintJson(response, list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	

}
