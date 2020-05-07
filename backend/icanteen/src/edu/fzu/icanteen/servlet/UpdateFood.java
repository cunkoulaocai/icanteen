package edu.fzu.icanteen.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.fzu.icanteen.dao.FoodDAO;
import edu.fzu.icanteen.dao.FoodDAOImpl;
import edu.fzu.icanteen.pojo.Food;

public class UpdateFood extends HttpServlet {

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
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String fprice = request.getParameter("price");
		double price = 0;
		String fquantity = request.getParameter("quantity");
		int quantity = 0;
		String flag = request.getParameter("flag");
		
		response.setContentType("text/html;charset=utf-8");
		
		if (mid == null || mid.equals("")) {
			System.out.println("商家ID错误");
			return;
		} else {
			merchantId = Integer.parseInt(mid);
		}
		
		if (fprice == null || fprice.equals("")) {
			System.out.println("价格错误");
			return;
		} else {
			price = Double.parseDouble(fprice);
		}
		
		if (fquantity == null || fquantity.equals("")) {
			System.out.println("数量错误");
			return;
		} else {
			quantity = Integer.parseInt(fquantity);
		}
		
		FoodDAO foodDAO = new FoodDAOImpl();
		Food food = new Food(); 
		food.setName(name);
		food.setMerchantId(merchantId);
		food.setDescription(description);
		food.setPrice(price);
		food.setQuantity(quantity);
		
		if(flag != null && flag.equals("add")) {
			foodDAO.add(food);
		} else if(flag != null && flag.equals("update")){
			String fId = request.getParameter("fId");
			int foodId = 0;
			if (fId == null || fId.equals("")) {
				System.out.println("菜品ID错误");
				return;
			} else {
				foodId = Integer.parseInt(fId);
			}
			
			if(foodDAO.get(foodId) == null) {
				System.out.println("菜品ID错误");
				return;
			}
			
			food.setId(foodId);
			foodDAO.update(food);
		}
		
	}
	
}