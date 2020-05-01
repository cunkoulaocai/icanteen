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
		String studentId = request.getParameter("studentId"); // 获取客户端传过来的参数
		String password = request.getParameter("password");
		response.setContentType("text/html;charset=utf-8");
		
		if (studentId == null || studentId.equals("") || password == null || password.equals("")) {
			System.out.println("用户名或密码为空");
			return;
		} // 请求数据库
		// 打开数据库连接
		CustomerDAO customerDAO = new CustomerDAOImpl();
		Customer customer = customerDAO.get(studentId, password);
		BaseBean data = new BaseBean(); // 基类对象，回传给客户端的json对象
		
		if (customer != null) {
			// 判断账号是否存在
			data.setCode(1);
			data.setData(customer);
			data.setMsg("登陆成功");
		} else {
			data.setMsg("登陆失败，用户名或密码错误");
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
			response.getWriter().close(); // 关闭这个流，不然会发生错误的
		}
	}
	
}