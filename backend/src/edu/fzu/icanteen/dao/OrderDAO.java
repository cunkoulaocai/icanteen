package edu.fzu.icanteen.dao;

import java.util.List;

import edu.fzu.icanteen.pojo.Order;

public interface OrderDAO{
	
	int getTotal(int customerId);

	void add(Order bean);
	
	void update(Order bean);

	void delete(int id);

	Order get(int id);

	List<Order> list(int cId);

	List<Order> list(int cId, int start, int count);

	List<Order> list();

	List<Order> list(int start, int count);

	List<Order> search(String keyword, int start, int count);

	int getTotal();

	void add1(Order bean);

	boolean isExist(String name);

	Order get(String name);


	

}