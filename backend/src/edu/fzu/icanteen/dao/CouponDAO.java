package edu.fzu.icanteen.dao;

import java.util.List;

import edu.fzu.icanteen.pojo.Coupon;

public interface CouponDAO{
	
	int getTotal(int cid);

	void add(Coupon bean);
	
	void update(Coupon bean);

	void delete(int id);

	Coupon get(int id);

	int getTotal();

	void add1(Coupon bean);

	boolean isExist(String name);

	Coupon get(String name);

	List<Coupon> list(int cid);

	List<Coupon> list(int cid, int start, int count);

	List<Coupon> search(String keyword, int start, int count);


}