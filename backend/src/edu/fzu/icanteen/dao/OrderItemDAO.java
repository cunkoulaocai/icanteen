package edu.fzu.icanteen.dao;

import java.util.List;

import edu.fzu.icanteen.pojo.Order;
import edu.fzu.icanteen.pojo.OrderItem;

public interface OrderItemDAO {

	String type_single = "type_single";
	String type_detail = "type_detail";

	int getTotal();

	void add(OrderItem bean);

	void update(OrderItem bean);

	void delete(int id);

	OrderItem get(int id);

	List<OrderItem> list(Order order);

	List<OrderItem> list(Order order, int start, int count);

}