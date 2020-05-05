package edu.fzu.icanteen.dao;

import java.util.List;

import edu.fzu.icanteen.pojo.ShoppingCart;

public interface ShoppingCartDAO{
	
	int getTotal(int cid);

	void add(ShoppingCart bean);
	
	void update(ShoppingCart bean);

	void delete(int id);

	ShoppingCart get(int id);

	int getTotal();

	void add1(ShoppingCart bean);

	boolean isExist(String name);

	ShoppingCart get(String name);

	List<ShoppingCart> list(int cid);

	List<ShoppingCart> list(int cid, int start, int count);

	List<ShoppingCart> search(String keyword, int start, int count);


}