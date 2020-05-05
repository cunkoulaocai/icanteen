package edu.fzu.icanteen.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import edu.fzu.icanteen.pojo.Customer;
import edu.fzu.icanteen.dao.CustomerDAO;
import edu.fzu.icanteen.dao.CustomerDAOImpl;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("request--->"+request.getRequestURL()+"===="+request.getParameterMap().toString());
		// 获取客户端传过来的参数
		String studentId = request.getParameter("studentId"); 
		String password = request.getParameter("password");
		response.setContentType("text/html;charset=utf-8");
		
		if (studentId == null || studentId.equals("") || password == null || password.equals("")) {
			System.out.println("用户名或密码为空");
			return;
		}

		CustomerDAO customerDAO = new CustomerDAOImpl();
		Customer customer = customerDAO.get(studentId, password);
		BaseBean data = new BaseBean(); // 基类对象，回传给客户端的json对象
		
		if (customer != null) {
			data.setCode(1);
			data.setData(customer);
			data.setMsg("登陆成功");
		} else {
			data.setMsg("登陆失败，用户名或密码错误");
		}
		Gson gson = new Gson();
		// 将对象转化成json字符串
		String json = gson.toJson(data);
		
		try {
			// 将json数据传给客户端
			response.getWriter().println(json);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭这个流
			response.getWriter().close();
		}
	}
	
}