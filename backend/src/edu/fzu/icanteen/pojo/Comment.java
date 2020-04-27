package edu.fzu.icanteen.pojo;

import java.sql.Date;

public class Comment {
	private int id;
	private String customerid;
	private String merchantid;
	private String foodid;
	private String content;
	private Date sendtime;
	private int overallscore;
	private int attitudescore;
	private int tastescore;
	private int pricescore;
	private int waitingtime;
	private int State;
	
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
		return State;
	}

	public void setState(int State) {
		this.State = State;
	}
	
	
}
