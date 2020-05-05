package edu.fzu.icanteen.servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.fzu.icanteen.dao.CommentDAO;
import edu.fzu.icanteen.dao.CommentDAOImpl;
import edu.fzu.icanteen.pojo.Comment;

public class CommentUpdateServlet extends HttpServlet {

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
		String fid = request.getParameter("foodid");
		int foodid = 0;
        String content = request.getParameter("content");
        Date sendtime = new Date(System.currentTimeMillis());
        String oe = request.getParameter("overallscore");
        int overallscore = 0;
        String ae = request.getParameter("attitudescore");
        int attitudescore = 0;
        String te = request.getParameter("tastescore");
        int tastescore = 0;
        String pe = request.getParameter("pricescore");
        int pricescore = 0;
        String we = request.getParameter("waitingtime");
        int waitingtime = 0;
        String sae = request.getParameter("state");
        int state = 0;   
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
		
		if (oe == null || oe.equals("")) {
			System.out.println("总体评价错误");
			return;
		} else {
			overallscore = Integer.parseInt(oe);
		}
		
		if (ae == null || ae.equals("")) {
			System.out.println("态度评价错误");
			return;
		} else {
			attitudescore = Integer.parseInt(ae);
		}
		
		if (te == null || te.equals("")) {
			System.out.println("美味评价错误");
			return;
		} else {
			tastescore = Integer.parseInt(te);
		}
		
		if (pe == null || pe.equals("")) {
			System.out.println("价格评价错误");
			return;
		} else {
			pricescore = Integer.parseInt(pe);
		}
		
		if (we == null || we.equals("")) {
			System.out.println("等待时间评价错误");
			return;
		} else {
			waitingtime = Integer.parseInt(we);
		}
		
		if (sae == null || sae.equals("")) {
			System.out.println("评论状态错误");
			return;
		} else {
			state = Integer.parseInt(sae);
		}
		
		CommentDAO commentDAO = new CommentDAOImpl();
		Comment comment = new Comment();
		comment.setCustomerid(customerid);
		comment.setMerchantid(merchantid);
		comment.setFoodid(foodid);
		comment.setContent(content);
		comment.setSendtime(sendtime);
		comment.setOverallscore(overallscore);
		comment.setAttitudescore(attitudescore);
		comment.setTastescore(tastescore);
		comment.setPricescore(pricescore);
		comment.setWaitingtime(waitingtime);
		comment.setState(state);
		if(flag != null && flag.equals("add")) {
			commentDAO.add(comment);
		}
		else if(flag != null && flag.equals("update")){
			String cmid = request.getParameter("cmid");
			int commentid = 0;
			if (cmid == null || cmid.equals("")) {
				System.out.println("评论ID错误");
				return;
			} else {
				commentid = Integer.parseInt(cmid);
			}
			
			if(commentDAO.get(commentid) == null) {
				System.out.println("评论ID错误");
				return;
			}
			comment.setId(commentid);	
			commentDAO.update(comment);
		}
		
		
		
	}
	
}