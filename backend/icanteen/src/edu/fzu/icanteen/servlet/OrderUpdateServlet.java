package edu.fzu.icanteen.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.fzu.icanteen.dao.OrderDAO;
import edu.fzu.icanteen.dao.OrderDAOImpl;
import edu.fzu.icanteen.pojo.Order;

public class OrderUpdateServlet extends HttpServlet {

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
		String updateid = "";
		String status = "";
		String deleteid = "";
		String orderdata = "";
		OrderDAO orderDAO = new OrderDAOImpl();
		BaseBean bean = new BaseBean();
		 
		if(flag.equals("create")) {
			
			orderdata = request.getParameter("orderdata");
			Order order = new Gson().fromJson(orderdata, Order.class);
			orderDAO.add(order);
			bean.setCode(1);
			bean.setMsg("create a new order successfully");
			bean.setData(order);
			
		} else if(flag.equals("update")) {
			
			updateid = request.getParameter("updateid");
			status = request.getParameter("status");
			orderDAO.updateOrderStatus(Integer.parseInt(updateid), Integer.parseInt(status));
			bean.setCode(1);
			bean.setData("update orderStatus successfully");
			bean.setData("order:"+updateid+" status is changed into "+status);
			
		} else if(flag.equals("delete")) {
			
			deleteid = request.getParameter("deleteid");
			orderDAO.delete(Integer.parseInt(deleteid));
			bean.setCode(1);
			bean.setMsg("delete order successfully");
			bean.setData("orderid:"+deleteid);
			
		}
		
//		if(bean.getCode() != 1 && bean.getMsg() == null) {
//			bean.setCode(0);
//			bean.setMsg("flag should be correctly set");
//		}
		
		Gson gson = new Gson();
		String json = gson.toJson(bean);

		try {
			response.getWriter().println(json);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			response.getWriter().close();
		}
	}
}