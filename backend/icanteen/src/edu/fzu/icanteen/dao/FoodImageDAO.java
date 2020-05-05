package edu.fzu.icanteen.dao;

import java.util.List;

import edu.fzu.icanteen.pojo.Food;
import edu.fzu.icanteen.pojo.FoodImage;

public interface FoodImageDAO {

	String type_single = "type_single";
	String type_detail = "type_detail";

	int getTotal();

	void add(FoodImage bean);

	void update(FoodImage bean);

	void delete(int id);

	FoodImage get(int id);

	List<FoodImage> list(Food food);

	List<FoodImage> list(Food food, int start, int count);
	
	List<FoodImage> list(int foodId);

	List<FoodImage> list(int foodId, int start, int count);

}