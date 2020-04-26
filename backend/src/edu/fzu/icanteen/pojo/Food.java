package edu.fzu.icanteen.pojo;

public class Food {
	//自增主键
    private int id;
    //食品名
    private String name;
    //商家id
    private int merchantId;
    //描述
    private String description;
    //金额
    private double price;
    //数量
    private int quantity;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public int getMerchantId() {
        return merchantId;
    }

    public void setPonit(int merchantId) {
        this.merchantId = merchantId;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
