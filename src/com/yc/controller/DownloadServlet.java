package com.yc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.DataBean;
import com.yc.dao.DataDAO;
import com.yc.dao.impl.DataDAOImpl;


@WebServlet("/download.do")
public class DownloadServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	DataDAO dao = new DataDAOImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataBean bean = parseRequest(request, DataBean.class);
		System.out.println(bean);
		List<DataBean> list;
		String dpath = null;
		String dname = null;
		try {
			list = dao.find(bean, null, null);
			dpath = list.get(0).getDpath();
			dname=list.get(0).getDname();
			System.out.println(list.get(0));
			System.out.println(dname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String a = request.getRealPath("/");
		File fa = new File(a);
		String bs = fa.getParent();
		// 获得请求文件名
		// 设置文件MIME类型
		response.setContentType(getServletContext().getMimeType(dpath));
		// 设置Content-Disposition
		response.setHeader("Content-Disposition", "attachment;filename=\""+URLEncoder.encode(dname, "UTF8")+"\"");
		// 读取目标文件，通过response将目标文件写到客户端
		// 获取目标文件的绝对路径
		String fullFileName = bs +File.separatorChar+ "yxjyimg"+File.separatorChar+ dpath;
		System.out.println(fullFileName);
		// 读取文件
		InputStream in = new FileInputStream(fullFileName);
		OutputStream out = response.getOutputStream();

		// 写文件
		int b;
		while ((b = in.read()) != -1) {
			out.write(b);
		}

		in.close();
		out.close();
	}

}
