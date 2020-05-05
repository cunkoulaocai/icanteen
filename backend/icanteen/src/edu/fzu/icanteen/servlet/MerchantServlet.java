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

public class MerchantServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("request--->"+request.getRequestURL()+"===="+request.getParameterMap().toString());
		String rmc = request.getParameter("merchantId");
		int merchantId = 0;
		response.setContentType("text/html;charset=utf-8");
		
		if (rmc == null || rmc.equals("")) {
			System.out.println("商家ID错误");
			return;
		} else {
			merchantId = Integer.parseInt(rmc);
		}

		MerchantDAO merchantDAO = new MerchantDAOImpl();
		Merchant merchant = merchantDAO.get(merchantId);
		BaseBean data = new BaseBean(); 
		
		if (merchant != null) {
			// 判断商家是否存在
			data.setCode(1);
			data.setData(merchant);
			data.setMsg("商家查找成功");
		} else {
			data.setMsg("商家ID错误");
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
			// 关闭这个流，不然会发生错误的
			response.getWriter().close();
		}
	}
	
}