package edu.fzu.icanteen.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.fzu.icanteen.dao.CouponDAO;
import edu.fzu.icanteen.dao.CouponDAOImpl;
import edu.fzu.icanteen.pojo.Coupon;


public class CouponServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("request--->"+request.getRequestURL()+"===="+request.getParameterMap().toString());
		String rmc = request.getParameter("customerId"); // 获取客户端传过来的参数
		int customerId = 0;
		response.setContentType("text/html;charset=utf-8");
		
		if (rmc == null || rmc.equals("")) {
			System.out.println("用户ID错误");
			return;
		} 
		else {
			customerId = Integer.parseInt(rmc);
		}
		// 请求数据库
		// 打开数据库连接
		CouponDAO couponDAO = new CouponDAOImpl();
		List<Coupon> coupons = couponDAO.list(customerId);	
		BaseBean data = new BaseBean(); 			
			if (coupons != null) {
				// 判断代金卷是否存在
				data.setCode(1);
				data.setData(coupons);
				data.setMsg("代金卷查找成功");
			} else {
				data.setMsg("代金卷查找失败");
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