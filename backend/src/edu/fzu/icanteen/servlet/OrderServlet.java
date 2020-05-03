package edu.fzu.icanteen.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.fzu.icanteen.dao.OrderDAO;
import edu.fzu.icanteen.dao.OrderDAOImpl;
import edu.fzu.icanteen.dao.OrderItemDAO;
import edu.fzu.icanteen.dao.OrderItemDAOImpl;
import edu.fzu.icanteen.pojo.Order;
import edu.fzu.icanteen.pojo.OrderItem;

public class OrderServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("request--->"+request.getRequestURL()+"===="+request.getParameterMap().toString());
		String customerid = request.getParameter("customerid");
		int cid = 1;
		response.setContentType("text/html;charset=utf-8");
		OrderDAO orderDAO = new OrderDAOImpl();
		if (customerid == null || customerid.equals("")) {
			System.out.println("用户不存在");
			return;
		} else {
			cid = Integer.parseInt(customerid);
		}
		List<Order> orders = orderDAO.list(cid);
		OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
		List<OrderItem> orderItem;
//		for(Order order:orders) {
//			orderItem = orderItemDAO.list(order);
//			order.setOrderItems(orderItem);
//		}
		BaseBean data = new BaseBean(); 
		if (orders != null) {
			//判断用户是否存在
			data.setCode(1);
			data.setData(orders);
			data.setMsg("订单查找成功");
		} else {
			data.setMsg("订单查找错误");
		}
		Gson gson = new Gson();
		String json = gson.toJson(data);
		 //将对象转化成json字符串
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