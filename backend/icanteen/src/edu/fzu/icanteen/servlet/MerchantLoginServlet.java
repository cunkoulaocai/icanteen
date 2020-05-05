package edu.fzu.icanteen.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import edu.fzu.icanteen.pojo.Merchant;
import edu.fzu.icanteen.dao.MerchantDAO;
import edu.fzu.icanteen.dao.MerchantDAOImpl;

public class MerchantLoginServlet extends HttpServlet {

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
		String phoneNumber = request.getParameter("phoneNumber"); 
		String password = request.getParameter("password");
		response.setContentType("text/html;charset=utf-8");
		
		if (phoneNumber == null || phoneNumber.equals("") || password == null || password.equals("")) {
			System.out.println("用户名或密码为空");
			return;
		}

		MerchantDAO merchantDAO = new MerchantDAOImpl();
		Merchant merchant = merchantDAO.get(phoneNumber, password);
		BaseBean data = new BaseBean(); // 基类对象，回传给客户端的json对象
		
		if (merchant != null) {
			data.setCode(1);
			data.setData(merchant);
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