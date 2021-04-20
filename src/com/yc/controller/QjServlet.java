package com.yc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.QjBean;
import com.yc.dao.QjDAO;
import com.yc.dao.impl.QjDAOImpl;
import com.yc.util.WebUtil;

@WebServlet("/qj.do")
public class QjServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    QjDAO dao=new QjDAOImpl();   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WebUtil.setEncodings(request, response);
		String op=request.getParameter("op");
		if("add".equals(op)){
			doAdd(request,response);
		}
	
	}

	private void doAdd(HttpServletRequest request, HttpServletResponse response) {
		QjBean bean=parseRequest(request, QjBean.class);
		try {
			int a=dao.addQj(bean);
			toPrintJson(response, a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
