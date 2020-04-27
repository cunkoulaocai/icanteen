package edu.fzu.icanteen.pojo;

import java.sql.Date;

public class Comment {
	//自增主键
	private int id;
	//用户ID
	private String customerid;
	//商家ID
	private String merchantid;
	//菜品ID
	private String foodid;
	//评论内容
	private String content;
	//发送时间
	private Date sendtime;
	//总体评分
	private int overallscore;
	//态度评分
	private int attitudescore;
	//美味评分
	private int tastescore;
	//价格评分
	private int pricescore;
	//等待时间
	private int waitingtime;
	//状态
	private int state;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	
	public String getMerchantid() {
		return merchantid;
	}

	public void setMerchantid(String merchantid) {
		this.merchantid = merchantid;
	}
	
	public String getFoodid() {
		return foodid;
	}

	public void setFoodid(String foodid) {
		this.foodid = foodid;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getSendtime() {
		return sendtime;
	}

	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}
	
	public int getOverallscore() {
		return overallscore;
	}

	public void setOverallscore(int overallscore) {
		this.overallscore = overallscore;
	}
	
	public int getAttitudescore() {
		return attitudescore;
	}

	public void setAttitudescore(int attitudescore) {
		this.attitudescore = attitudescore;
	}
	
	public int getTastescore() {
		return tastescore;
	}

	public void setTastescore(int tastescore) {
		this.tastescore = tastescore;
	}
	
	public int getPricescore() {
		return pricescore;
	}

	public void setPricescore(int pricescore) {
		this.pricescore = pricescore;
	}
	
	public int getWaitingtime() {
		return waitingtime;
	}

	public void setWaitingtime(int waitingtime) {
		this.waitingtime = waitingtime;
	}
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	
}
