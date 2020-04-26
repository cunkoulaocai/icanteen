package edu.fzu.icanteen.pojo;

import java.sql.Date;

public class Coupon {
	//自增主键
    private int id;
    //商家id
    private int merchantId;
    //用户id
    private int customerId;
    //使用门槛
    private double threshold;
    //金额
    private double voucher;
    //有效期限
    private Date servicelife;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setPonit(int merchantId) {
        this.merchantId = merchantId;
    }
    
    public int getSustomerId() {
        return customerId;
    }

    public void setSustomerId(int customerId) {
        this.customerId = customerId;
    }
    
    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }
    
    public double getVoucher() {
        return voucher;
    }

    public void setVoucher(double voucher) {
        this.voucher = voucher;
    }

    public Date getServicelife() {
        return servicelife;
    }

    public void setServicelife(Date servicelife) {
        this.servicelife = servicelife;
    }
}
