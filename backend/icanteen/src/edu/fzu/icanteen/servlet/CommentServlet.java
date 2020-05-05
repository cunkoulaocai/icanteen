package edu.fzu.icanteen.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.fzu.icanteen.dao.CommentDAO;
import edu.fzu.icanteen.dao.CommentDAOImpl;
import edu.fzu.icanteen.pojo.Comment;

public class CommentServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("request--->"+request.getRequestURL()+"===="+request.getParameterMap().toString());
		String mid = request.getParameter("merchantId");
		int merchantId = 0;
		response.setContentType("text/html;charset=utf-8");
		
		if (mid == null || mid.equals("")) {
			System.out.println("商家ID错误");
			return;
		} else {
			merchantId = Integer.parseInt(mid);
		}

		CommentDAO commentDAO = new CommentDAOImpl();
		List<Comment> comments = commentDAO.list(merchantId); 
		BaseBean data = new BaseBean(); 
		
		if (comments != null) {
			// 判断用户是否存在
			data.setCode(1);
			data.setData(comments);
			data.setMsg("商家评论查找成功");
		} else {
			data.setMsg("商家ID错误");
		}
		Gson gson = new Gson();
		String json = gson.toJson(data);
		// 将对象转化成json字符串
		try {
			response.getWriter().println(json);
			// 将json数据传给客户端
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭这个流
			response.getWriter().close();
		}
	}
	
}