package edu.fzu.icanteen.pojo;


public class FoodImage {
	//自增主键
    private int id;
    //食物
    private Food food;
    //URL
    private String url;
    

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
}
