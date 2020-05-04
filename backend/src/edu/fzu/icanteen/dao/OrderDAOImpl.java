package edu.fzu.icanteen.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import edu.fzu.icanteen.pojo.Order;
import edu.fzu.icanteen.util.DBUtil;

public class OrderDAOImpl implements OrderDAO {

    @Override
	public int getTotal(int cid) {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
        	String sql = null;
        	if(cid == 0)    //不按类别
        		sql = "select count(*) from Orders where 1 = 1 " ;
        	else 
        		sql = "select count(*) from Orders where customerid = " + cid;

            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return total;
    }

    @Override
	public void add(Order bean) {

        String sql = "insert into Orders values(null,?,?,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, bean.getCustomerId());
            ps.setInt(2, bean.getMerchantId());
            ps.setDate(3, bean.getAppointment());
            ps.setDate(4, bean.getOrderTime());
            ps.setInt(5, bean.getCancel());
            ps.setDate(6, bean.getCloseTime());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                bean.setId(id);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
	public void update(Order bean) {

        String sql = "update Orders set customerid = ?, merchantid = ?, appointment = ?, ordertime = ?, cancel = ?, closetime = ? where id = ??";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

        	ps.setInt(1, bean.getCustomerId());
            ps.setInt(2, bean.getMerchantId());
            ps.setDate(3, bean.getAppointment());
            ps.setDate(4, bean.getOrderTime());
            ps.setInt(5, bean.getCancel());
            ps.setDate(6, bean.getCloseTime());
            ps.setInt(7, bean.getId());
            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    @Override
	public void delete(int id) {

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {

            String sql = "delete from Orders where id = " + id;

            s.execute(sql);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
	public Order get(int id) {
        Order bean = new Order();

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {

            String sql = "select * from Orders where id = " + id;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {

                int customerId = rs.getInt("customerId");
                int merchantId = rs.getInt("merchantId");
                Date appointment = rs.getDate("appointment");
                Date orderTime = rs.getDate("ordertime");
                int cancel = rs.getInt("cancel") ;
                Date closeTime = rs.getDate("closetime");
                
                bean.setCustomerId(customerId);
                bean.setMerchantId(merchantId);
                bean.setAppointment(appointment);
                bean.setOrderTime(orderTime);
                bean.setCancel(cancel);
                bean.setCloseTime(closeTime);
                bean.setId(id);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return bean;
    }

    @Override
	public List<Order> list(int cid) {
        return list(cid, 0, Short.MAX_VALUE);
    }

    @Override
	public List<Order> list(int cid, int start, int count) {
        List<Order> beans = new ArrayList<Order>();
        String sql = "select * from Orders where customerid = ? order by id desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, cid);
            ps.setInt(2, start);
            ps.setInt(3, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order bean = new Order();
                int id = rs.getInt(1);
                int customerId = rs.getInt("customerId");
                int merchantId = rs.getInt("merchantId");
                Date appointment = rs.getDate("appointment");
                Date orderTime = rs.getDate("ordertime");
                int cancel = rs.getInt("cancel") ;
                Date closeTime = rs.getDate("closetime");
               
                bean.setCustomerId(customerId);
                bean.setMerchantId(merchantId);
                bean.setAppointment(appointment);
                bean.setOrderTime(orderTime);
                bean.setCancel(cancel);
                bean.setCloseTime(closeTime);
                bean.setId(id);
                beans.add(bean);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return beans;
    }

    @Override
	public List<Order> list() {
        return list(0, Short.MAX_VALUE);
    }

    @Override
	public List<Order> list(int start, int count) {
        List<Order> beans = new ArrayList<Order>();

        String sql = "select * from Orders limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, start);
            ps.setInt(2, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order bean = new Order();
                int id = rs.getInt(1);
                int customerId = rs.getInt("customerId");
                int merchantId = rs.getInt("merchantId");
                Date appointment = rs.getDate("appointment");
                Date orderTime = rs.getDate("ordertime");
                int cancel = rs.getInt("cancel") ;
                Date closeTime = rs.getDate("closetime");
               
                bean.setCustomerId(customerId);
                bean.setMerchantId(merchantId);
                bean.setAppointment(appointment);
                bean.setOrderTime(orderTime);
                bean.setCancel(cancel);
                bean.setCloseTime(closeTime);
                bean.setId(id);
                beans.add(bean);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return beans;
    }

    @Override
	public List<Order> search(String keyword, int start, int count) {
        List<Order> beans = new ArrayList<Order>();

        if (null  ==  keyword || 0  ==  keyword.trim().length())
            return beans;
        String sql = "select * from Orders where name like ? limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword.trim() + "%");
            ps.setInt(2, start);
            ps.setInt(3, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order bean = new Order();
                int id = rs.getInt(1);
                int customerId = rs.getInt("customerId");
                int merchantId = rs.getInt("merchantId");
                Date appointment = rs.getDate("appointment");
                Date orderTime = rs.getDate("ordertime");
                int cancel = rs.getInt("cancel") ;
                Date closeTime = rs.getDate("closetime");
               
                bean.setCustomerId(customerId);
                bean.setMerchantId(merchantId);
                bean.setAppointment(appointment);
                bean.setOrderTime(orderTime);
                bean.setCancel(cancel);
                bean.setCloseTime(closeTime);
                bean.setId(id);
                beans.add(bean);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return beans;
    }

	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void add1(Order bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isExist(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Order get(String name) {
		// TODO Auto-generated method stub
		return null;
	}



}