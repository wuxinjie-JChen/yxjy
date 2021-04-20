package com.yc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import com.yc.bean.AdminBean;
import com.yc.bean.StudentBean;
import com.yc.bean.TeacherBean;
import com.yc.dao.AdminDAO;
import com.yc.dao.StudentDAO;
import com.yc.dao.TeacherDAO;
import com.yc.dao.impl.AdminDAOImpl;
import com.yc.dao.impl.StudentDaoImpl;
import com.yc.dao.impl.TeacherDaoImpl;
import com.yc.util.WebUtil;


@WebServlet("/user.do")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	StudentDAO studentDao=new StudentDaoImpl();
	TeacherDAO teacherDao=new TeacherDaoImpl();
	AdminDAO adminDao=new AdminDAOImpl();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WebUtil.setEncodings(request, response);
		String op=request.getParameter("op");
		if("login".equals(op)){
			doLogin(request,response);
		}else if("update".equals(op)){
			doUpdate(request,response);
		}
	}


	private void doUpdate(HttpServletRequest request, HttpServletResponse response) {
		AdminBean bean=parseRequest(request, AdminBean.class);
		String oldPwd=request.getParameter("oldPwd");
		String newPwd=request.getParameter("newPwd");
		AdminBean admin=(AdminBean) request.getSession().getAttribute("admin");
		bean.setApwd(newPwd);
		if(!oldPwd.equals(admin.getApwd())){
			try {
				toPrintJson(response, 0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				int a=adminDao.update(bean);
				if(a==1){
					request.getSession().setAttribute("student", bean);
				}
				toPrintJson(response, a);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	private void doLogin(HttpServletRequest request, HttpServletResponse response) {
		String type=request.getParameter("type");
		String name=request.getParameter("name");
		String pwd=request.getParameter("pwd");
		HttpSession session=request.getSession();
		try {
			if(type.equals("1")){
				StudentBean bean=new StudentBean();
				bean.setSname(name);
				bean.setSpwd(pwd);
				StudentBean student=studentDao.login(bean);
				if(student!=null){
					session.setAttribute("student", student);
					toPrintJson(response, 1);
				}else{
					toPrintJson(response, 0);
				}
			}else if(type.equals("2")){
				TeacherBean bean=new TeacherBean();
				bean.setTname(name);
				bean.setTpwd(pwd);
				TeacherBean teacher=teacherDao.login(bean);
				if(teacher!=null){
					session.setAttribute("teacher", teacher);
					toPrintJson(response, 2);
				}else{
					toPrintJson(response, 0);
				}
			}else if(type.equals("3")){
				AdminBean bean=new AdminBean();
				bean.setAname(name);
				bean.setApwd(pwd);
				AdminBean admin=adminDao.login(bean);
				if(admin!=null){
					session.setAttribute("admin", admin);
					toPrintJson(response, 3);
				}else{
					toPrintJson(response, 0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
