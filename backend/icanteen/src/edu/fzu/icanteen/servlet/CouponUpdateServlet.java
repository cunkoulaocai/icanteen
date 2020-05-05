package edu.fzu.icanteen.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.fzu.icanteen.dao.CouponDAO;
import edu.fzu.icanteen.dao.CouponDAOImpl;
import edu.fzu.icanteen.pojo.Coupon;


public class CouponUpdateServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("request--->"+request.getRequestURL()+"===="+request.getParameterMap().toString());
		String cid = request.getParameter("customerid");
		int customerid = 0;
		String mid = request.getParameter("merchantid");
		int merchantid = 0;		
		String tld = request.getParameter("threshold");
		double threshold = 0;
		String ver = request.getParameter("voucher");
		double voucher = 0;		
		java.util.Date utilDate = new java.util.Date();
	    Calendar calendar = new GregorianCalendar(); 
	    calendar.setTime(utilDate); 
	    calendar.add(Calendar.DATE,7);
	    utilDate=calendar.getTime();  
	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	    Date servicelife = new Date(sqlDate.getTime());
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
		
		if (tld == null || tld.equals("")) {
			System.out.println("使用门槛错误");
			return;
		} else {
			threshold = Double.parseDouble(tld);
		}
		
		if (ver == null || ver.equals("")) {
			System.out.println("用户ID错误");
			return;
		} else {
			voucher = Double.parseDouble(ver);
		}
		
		CouponDAO couponDAO = new CouponDAOImpl();
		Coupon coupon = new Coupon();
		coupon.setCustomerId(customerid);
		coupon.setMerchantId(merchantid);
		coupon.setThreshold(threshold);
		coupon.setVoucher(voucher);
		coupon.setServicelife(servicelife);
		if(flag != null && flag.equals("add")) {
			couponDAO.add(coupon);
		}
		else if(flag != null && flag.equals("update")) {
			String couid = request.getParameter("id");
			int couponid = 0;
			if (couid == null || couid.equals("")) {
				System.out.println("代金券ID错误");
				return;
			} else {
				couponid = Integer.parseInt(couid);
			}
			
			if(couponDAO.get(couponid) == null) {
				System.out.println("代金券ID错误");
				return;
			}
			coupon.setId(couponid);
			couponDAO.update(coupon);
		}
	}
	
}