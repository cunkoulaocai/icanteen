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

public class ShoppingCartUpdateServlet extends HttpServlet {

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
		String cid = request.getParameter("customerid");
	    int customerid = 0;
		String mid = request.getParameter("merchantid");
	    int merchantid = 0;
		String fid = request.getParameter("foodid");
	    int foodid = 0;
		String num = request.getParameter("number");
	    int number = 0;
	    String flag = request.getParameter("flag");
	    response.setContentType("text/html;charset=utf-8");
	    
	    if (cid == null || cid.equals("")) {
			System.out.println("用户ID错误");
			return;
		} else {
			customerid = Integer.parseInt(cid);
		}
	    
	    if (mid == null || mid.equals("")) {
			System.out.println("商家ID错误");
			return;
		} else {
			merchantid = Integer.parseInt(mid);
		}
	    
	    if (fid == null || fid.equals("")) {
			System.out.println("食品ID错误");
			return;
		} else {
			foodid = Integer.parseInt(fid);
		}
	    
	    if (num == null || num.equals("")) {
			System.out.println("食品数量错误");
			return;
		} else {
			number = Integer.parseInt(num);
		}
	    
	    ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAOImpl();
	    ShoppingCart shoppingCart = new ShoppingCart();
	    shoppingCart.setCustomerId(customerid);
	    shoppingCart.setMerchantId(merchantid);
	    shoppingCart.setFoodId(foodid);
	    shoppingCart.setNumber(number);
	    
	    if(flag != null && flag.equals("add")) {
	    	shoppingCartDAO.add(shoppingCart);
		}
		else if(flag != null && flag.equals("update")){
			String sid = request.getParameter("shid");
			int shid = 0;
			if (sid == null || sid.equals("")) {
				System.out.println("食物ID错误");
				return;
			} else {
				shid = Integer.parseInt(sid);
			}
			
			if(shoppingCartDAO.get(shid) == null) {
				System.out.println("食物ID错误");
				return;
			}
			shoppingCart.setId(shid);	
			shoppingCartDAO.update(shoppingCart);
		}
	}
	
}
