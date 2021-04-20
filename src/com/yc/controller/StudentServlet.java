package com.yc.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.bean.StudentBean;
import com.yc.commons.MyProperties;
import com.yc.dao.StudentDAO;
import com.yc.dao.impl.StudentDaoImpl;
import com.yc.util.SendMailUtil;
import com.yc.util.StringUtil;
import com.yc.util.WebUtil;


@WebServlet("/student.do")
public class StudentServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	StudentDAO studentDao=new StudentDaoImpl();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WebUtil.setEncodings(request, response);
		String op=request.getParameter("op");
		if("find".equals(op)){
			doFind(request,response);
		}else if(op.equals("delete")){
			doDeletes(request,response);
		}else if(op.equals("modify")){
			doModify(request,response);
		}else if(op.equals("yzm")){
			doYzm(request,response);
		}else if(op.equals("perfact")){
			doPerfact(request,response);
		}else if(op.equals("reg")){
			doReg(request,response);
		}
	}


	private void doReg(HttpServletRequest request, HttpServletResponse response) {
		StudentBean bean=parseRequest(request, StudentBean.class);
		try {
			int a=studentDao.reg(bean);
			toPrintJson(response, a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	private void doPerfact(HttpServletRequest request, HttpServletResponse response) {
		StudentBean bean=parseRequest(request, StudentBean.class);
		try {
			int a=studentDao.perfact(bean);
			toPrintJson(response, a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	private void doYzm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		StudentBean student=(StudentBean) session.getAttribute("student");
		StringBuilder builder = new StringBuilder();
		/*StringBuffer url = new StringBuffer();
		String contextPath = request.getContextPath(); //
		InetAddress addr = InetAddress.getLocalHost();
		String rUrl = addr.getHostAddress();// 获得服务器地址 192.168.20.159
		url.append("http://" + rUrl); // http://服务器地址:
		url.append(":" + request.getServerPort());
		url.append(contextPath);
		url.append("/resuser.action?op=toChangePwd");*/ // http://192.168.20.159:8080/yc3637foods/resuser/resuser.action?op=toChangePwd
		Integer validacode = (int) ((Math.random() * 9 + 1) * 1000); // 验证码随机数
		request.getSession().setAttribute("validacode", validacode);
		  // 邮件正文  
        builder.append("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" </head><body>");  
        builder.append("<br/><span>验证码为："+validacode+"</span>");
        builder.append("</body></html>"); 
        String smtp=MyProperties.getInstance().getProperty("smtp");
		String sender=MyProperties.getInstance().getProperty("sender"); //发送方
		String mail=student.getQq();    //接收方
		String uname=MyProperties.getInstance().getProperty("uname");  //发送方名字
		String pwd=MyProperties.getInstance().getProperty("pwd");     //密码
		String title="一心教育修改密码";
        
		SendMailUtil.sendMail(smtp, sender, mail, uname, pwd, title, builder.toString());
		//发送邮件时的时间
		long time=System.currentTimeMillis();
		request.getSession().setAttribute("time", time);
		//request.getRequestDispatcher("/WEB-INF/pages/mail.jsp").forward(request, response);
		toPrintJson(response, 1);
	}


	private void doModify(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StudentBean bean=parseRequest(request, StudentBean.class);
		
		String oldPwd=request.getParameter("oldPwd");
		String newPwd=request.getParameter("newPwd");
		String yzm=request.getParameter("yzm");
		String validacode=(Integer) request.getSession().getAttribute("validacode")+"";
		StudentBean student=(StudentBean) request.getSession().getAttribute("student");
		bean.setSpwd(newPwd);
		if(!yzm.equals(validacode)){
			toPrintJson(response, -1);
		}else if(!oldPwd.equals(student.getSpwd())){
			toPrintJson(response, 2);
		}else{
			try {
				int a=studentDao.update(bean);
				if(a==1){
					StudentBean students=studentDao.login(bean);
					request.getSession().setAttribute("student", students);
				}
				toPrintJson(response, a);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}


	private void doDeletes(HttpServletRequest request, HttpServletResponse response) {
		StudentBean bean=super.parseRequest(request, StudentBean.class);
		try {
			int a=studentDao.delete(bean);
			toPrintJson(response, a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void doFind(HttpServletRequest request, HttpServletResponse response) {
		String pagestr=request.getParameter("page");
		String rowStr=request.getParameter("rows");
		StudentBean bean=parseRequest(request, StudentBean.class);
		try {
			int total=studentDao.findByPageTotal(bean);
			List<StudentBean> list=null;
			if(StringUtil.isNotNull(rowStr)&&StringUtil.isNotNull(pagestr)){
				list=studentDao.find(bean, Integer.parseInt(pagestr), Integer.parseInt(rowStr));
				toPrintJson(response, list,total);
			}else{
				list=studentDao.find(bean,null, null);
				toPrintJson(response, list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
