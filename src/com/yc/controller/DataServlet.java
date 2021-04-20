package com.yc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.yc.bean.DataBean;
import com.yc.bean.StudentBean;
import com.yc.commons.MyProperties;
import com.yc.dao.DataDAO;
import com.yc.dao.StudentDAO;
import com.yc.dao.impl.DataDAOImpl;
import com.yc.dao.impl.StudentDaoImpl;
import com.yc.util.SendMailUtil;
import com.yc.util.StringUtil;
import com.yc.util.WebUtil;

@WebServlet("/data.do")
public class DataServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	DataDAO dao = new DataDAOImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WebUtil.setEncodings(request, response);
		String op = request.getParameter("op");
		if ("find".equals(op)) {
			doFind(request, response);
		} else if ("add".equals(op)) {
			doAdd(request, response);
		} else if ("deletes".equals(op)) {
			doDeletes(request, response);
		}
	}

	private void doDeletes(HttpServletRequest request, HttpServletResponse response) {
		DataBean bean = parseRequest(request, DataBean.class);
		try {
			int a=dao.delete(bean);
			toPrintJson(response, a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void doFind(HttpServletRequest request, HttpServletResponse response) {
		String pagestr = request.getParameter("page");
		String rowStr = request.getParameter("rows");
		DataBean bean = parseRequest(request, DataBean.class);
		try {
			int total = dao.findByPageTotal(bean);
			List<DataBean> list = null;
			if (StringUtil.isNotNull(rowStr) && StringUtil.isNotNull(pagestr)) {
				list = dao.find(bean, Integer.parseInt(pagestr), Integer.parseInt(rowStr));
				toPrintJson(response, list, total);
			} else {
				list = dao.find(bean, null, null);
				toPrintJson(response, list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void doAdd(HttpServletRequest request, HttpServletResponse response) {
		try {
			String a = request.getRealPath("/");
			File fa = new File(a);
			String b = fa.getParent();
			System.out.println(a);
			System.out.println(b);
			response.setContentType("text/html;charset=utf-8");
			// 创建DiskFileItemFactory工厂对象
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置文件缓存目录，如果该文件夹不存在则创建一个
			File f = new File("../" + a + "yxjyimg");
			if (!f.exists()) {
				f.mkdirs();
			}
			factory.setRepository(f);
			// 创建ServletFileUpload对象
			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			// 设置字符编码
			fileUpload.setHeaderEncoding("utf-8");
			// 解析request，将form表单的各个字段封装为FileItem对象
			List<FileItem> fileItems = fileUpload.parseRequest(request);
			// 获取字符流
			PrintWriter writer = response.getWriter();
			String dtype = null;
			String dname = null;
			String dpath = null;
			// 遍历List集合
			for (FileItem fileItem : fileItems) {
				// 判断是否为普通字段
				if (fileItem.isFormField()) {
					// 获取字段名称
					String name = fileItem.getFieldName();
					if (name.equals("dtype")) {
						// 如果字段值不为空
						if (!fileItem.getString().equals("")) {
							dtype = fileItem.getString("utf-8");
							System.out.println("类型：" + dtype);
						}
					}
				} else {
					// 获取上传的文件名
					dname = fileItem.getName();
					// 处理上传文件
					if (dname != null && dname != "") {
						System.out.println("上传的文件名称是：" + dname);
						// 保持文件名唯一
						dpath = UUID.randomUUID().toString() + "_" + dname;
						String webpath = "/upload/";
						// 创建文件路径
						String filepath = b + File.separatorChar+"yxjyimg" +File.separatorChar+ dpath;

						// String filepath =
						// getServletContext().getRealPath(webpath + filename);
						System.out.println(filepath + "filePath");
						// 创建File对象
						File file = new File(filepath);
						// 创建文件夹
						file.getParentFile().mkdirs();
						// 创建文件
						file.createNewFile();
						// 获取上传文件流
						InputStream in = fileItem.getInputStream();
						// 使用 FileOutputStream打开服务器端的上传文件
						FileOutputStream out = new FileOutputStream(file);
						// 流的对拷
						byte[] bytes = new byte[1024];// 每次读取一个字节
						int len;
						// 开始读取上传文件的字节，并将其输出到服务器端的上传文件输出流中
						while ((len = in.read(bytes)) > 0)
							out.write(bytes, 0, len);
						in.close();
						out.close();
						fileItem.delete();
						System.out.println("文件上传成功！");
					}
				}
			}
			DataBean bean = new DataBean();
			bean.setDname(dname);
			bean.setDtype(dtype);
			bean.setDpath(dpath);
			int i = dao.upload(bean);
			toPrintJson(response, i);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
