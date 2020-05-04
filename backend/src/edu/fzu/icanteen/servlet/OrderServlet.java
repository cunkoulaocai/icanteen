package edu.fzu.icanteen.servlet;

import java.io.IOException;
import java.util.ArrayList;
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
import edu.fzu.icanteen.pojo.OrderItemSql;
import edu.fzu.icanteen.pojo.OrderSql;

public class OrderServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("request--->" + request.getRequestURL() + "====" + request.getParameterMap().toString());
		response.setContentType("text/html;charset=utf-8");
		String flag = request.getParameter("flag");
		String id = request.getParameter("id");
		OrderDAO orderDAO = new OrderDAOImpl();
		OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
		List<Order> orders;
		List<OrderSql> orderSqls = new ArrayList<OrderSql>();
		List<OrderItem> orderItems;
		BaseBean data = new BaseBean();
		int cid = 0;
		int mid = 0;

		if (flag == null || flag.equals("") || id == null || id.equals("")) {
			data.setMsg("falg或者id为空");
		} else {
			if (flag.equals("customer")) {
				cid = Integer.parseInt(id);
				orders = orderDAO.list(cid);
				for (Order order : orders) {
					orderItems = orderItemDAO.list(order);
					order.setOrderItems(orderItems);
					OrderSql orderSql = orderToOrdersql(order);
					orderSqls.add(orderSql);
				}
			}
			if (flag.equals("merchant")) {
				mid = Integer.parseInt(id);
				orders = orderDAO.listByMerchant(mid);
				for (Order order : orders) {
					orderItems = orderItemDAO.list(order);
					order.setOrderItems(orderItems);
					OrderSql orderSql = orderToOrdersql(order);
					orderSqls.add(orderSql);
				}
			}
		}

		if (orderSqls.equals("")) {
			data.setMsg("没有订单信息");
		} else {
			data.setCode(1);
			data.setMsg("订单查询成功");
			data.setData(orderSqls);
		}

		Gson gson = new Gson();
		String json = gson.toJson(data);

		try {
			response.getWriter().println(json);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			response.getWriter().close();
		}
	}

	private OrderSql orderToOrdersql(Order order) {
		//把order转为ordersql;
		OrderSql orderSql = new OrderSql();
		orderSql.setId(order.getId());
		orderSql.setAppointment(order.getAppointment());
		orderSql.setCancel(order.getCancel());
		orderSql.setCloseTime(order.getCloseTime());
		orderSql.setCustomerId(order.getCustomerId());
		orderSql.setMerchantId(order.getMerchantId());
		orderSql.setOrderTime(order.getOrderTime());
		//把orderitems转为orderitemsqls
		List<OrderItemSql> orderItemSqls = new ArrayList<OrderItemSql>();
		for(OrderItem orderItem:order.getOrderItems()) {
			OrderItemSql orderItemSql = new OrderItemSql();
			orderItemSql.setId(orderItem.getId());
			orderItemSql.setFoodId(orderItem.getFoodId());
			orderItemSql.setNumber(orderItem.getNumber());
			orderItemSql.setOrder(orderItem.getOrder().getId());
			orderItemSqls.add(orderItemSql);
		}
		orderSql.setOrderItems(orderItemSqls);
		return orderSql;
	}
}