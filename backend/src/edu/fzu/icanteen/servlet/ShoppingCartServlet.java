package edu.fzu.icanteen.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.fzu.icanteen.dao.ShoppingCartDAO;
import edu.fzu.icanteen.dao.ShoppingCartDAOImpl;
import edu.fzu.icanteen.pojo.ShoppingCart;

public class ShoppingCartServlet extends HttpServlet {

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
		String customerid = request.getParameter("customerid");
		response.setContentType("text/html;charset=utf-8");
		ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAOImpl();
		int cid = Integer.parseInt(customerid);
		List<ShoppingCart> shoppingCarts = shoppingCartDAO.list(cid);
		BaseBean data = new BaseBean(); // 基类对象，回传给客户端的json对象
		
		if (shoppingCarts != null) {
			data.setCode(1);
			data.setData(shoppingCarts);
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
