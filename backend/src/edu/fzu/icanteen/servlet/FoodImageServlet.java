package edu.fzu.icanteen.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import edu.fzu.icanteen.pojo.Food;
import edu.fzu.icanteen.dao.FoodDAO;
import edu.fzu.icanteen.dao.FoodDAOImpl;

public class FoodImageServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("request--->"+request.getRequestURL()+"===="+request.getParameterMap().toString());
		String fId = request.getParameter("foodId");
		int foodId = 0;
		response.setContentType("text/html;charset=utf-8");
		
		if (fId == null || fId.equals("")) {
			System.out.println("菜品ID错误");
			return;
		} else {
			foodId = Integer.parseInt(fId);
		}

		FoodDAO foodDAO = new FoodDAOImpl();
		List<Food> foods = foodDAO.list(foodId); 
		BaseBean data = new BaseBean(); 
		
		if (foods != null) {
			// 判断商家是否存在
			data.setCode(1);
			data.setData(foods);
			data.setMsg("菜品图片查找成功");
		} else {
			data.setMsg("菜品ID错误");
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