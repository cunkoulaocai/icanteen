package edu.fzu.icanteen.dao;

import java.util.List;

import edu.fzu.icanteen.pojo.Food;

public interface FoodDAO{
	
	int getTotal(int cid);

	void add(Food bean);
	
	void update(Food bean);

	void delete(int id);

	Food get(int id);

	List<Food> list(int cid);

	List<Food> list(int cid, int start, int count);

	List<Food> list();

	List<Food> list(int start, int count);

	List<Food> search(String keyword, int start, int count);

	int getTotal();

	void add1(Food bean);

	boolean isExist(String name);

	Food get(String name);


	

}