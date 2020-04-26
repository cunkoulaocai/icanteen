package edu.fzu.icanteen.pojo;

public class OrderItem {
	//自增主键
    private int id;
    //订单id
    private int orderId;
    //食物id
    private int foodId;
    //数量
    private int number;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getOrderId() {
        return orderId;
    }

    public void setOrder(int orderId) {
        this.orderId = orderId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }
    
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
