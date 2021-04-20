package com.yc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.FeedbackBean;
import com.yc.bean.StudentBean;
import com.yc.bean.TeacherBean;
import com.yc.dao.FeedbackDAO;
import com.yc.dao.impl.FeedbackDAOImpl;
import com.yc.util.StringUtil;
import com.yc.util.WebUtil;


@WebServlet("/fb.do")
public class FeedbackServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

    FeedbackDAO dao=new FeedbackDAOImpl();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WebUtil.setEncodings(request, response);
		String op=request.getParameter("op");
		if("add".equals(op)){
			doAdd(request,response);
		}else if("find".equals(op)){
			doFind(request,response);
		}
	
	}


	private void doFind(HttpServletRequest request, HttpServletResponse response) {
		String pagestr=request.getParameter("page");
		String rowStr=request.getParameter("rows");
		try {
			int total=dao.findByPageTotal();
			List<FeedbackBean> list=null;
			if(StringUtil.isNotNull(rowStr)&&StringUtil.isNotNull(pagestr)){
				list=dao.find(Integer.parseInt(pagestr), Integer.parseInt(rowStr));
				toPrintJson(response, list,total);
			}else{
				list=dao.find(null, null);
				toPrintJson(response, list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	private void doAdd(HttpServletRequest request, HttpServletResponse response) {
		FeedbackBean bean=parseRequest(request, FeedbackBean.class);
		
		try {
			int a=dao.addFeedback(bean);
			toPrintJson(response, a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
