package edu.fzu.icanteen.pojo;

public class FoodImage {
	//自增主键
    private int id;
    //食物id
    private int foodId;
    //URL
    private String url;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
}
