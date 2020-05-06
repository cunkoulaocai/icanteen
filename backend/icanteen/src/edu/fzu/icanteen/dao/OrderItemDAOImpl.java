package edu.fzu.icanteen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.fzu.icanteen.pojo.Order;
import edu.fzu.icanteen.pojo.OrderItem;
import edu.fzu.icanteen.util.DBUtil;

public class OrderItemDAOImpl implements OrderItemDAO {

    @Override
	public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {

            String sql = "select count(*) from OrderItem";

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
	public void add(OrderItem bean) {


        String sql = "insert into OrderItem values(null,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, bean.getOrder().getId());
            ps.setInt(2, bean.getFoodId());
            ps.setInt(3, bean.getNumber());
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
	public void update(OrderItem bean) {

    }

    @Override
	public void delete(int id) {

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {

            String sql = "delete from orderitem where id = " + id;

            s.execute(sql);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
	public OrderItem get(int id) {
        OrderItem bean = new OrderItem();


        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {

            String sql = "select * from OrderItem where id = " + id;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                int orderId = rs.getInt("orderid");
                int foodId = rs.getInt("foodId");
                int number = rs.getInt("number");
                Order order = new OrderDAOImpl().get(orderId);
                bean.setOrder(order);
                bean.setFoodId(foodId);
                bean.setNumber(number);
                bean.setId(id);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return bean;
    }

    @Override
	public List<OrderItem> list(Order order) {
        return list(order, 0, Short.MAX_VALUE);
    }

    @Override
	public List<OrderItem> list(Order order, int start, int count) {
        List<OrderItem> beans = new ArrayList<OrderItem>();

        String sql = "select * from OrderItem where orderId  = ? order by id desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, order.getId());
            ps.setInt(2, start);
            ps.setInt(3, count);


            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                OrderItem bean = new OrderItem();
                int id = rs.getInt(1);
                int foodId = rs.getInt("foodId");
                int number = rs.getInt("number");
                
                bean.setOrder(order);
                bean.setFoodId(foodId);
                bean.setNumber(number);
                bean.setId(id);

                beans.add(bean);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return beans;
    }
    
    @Override
   	public List<OrderItem> list(int orderId) {
           return list(orderId, 0, Short.MAX_VALUE);
       }

    @Override
   	public List<OrderItem> list(int orderId, int start, int count) {
           List<OrderItem> beans = new ArrayList<OrderItem>();

           String sql = "select * from OrderItem where orderId  = ? order by id desc limit ?,? ";

           try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

               ps.setInt(1, orderId);
               ps.setInt(2, start);
               ps.setInt(3, count);


               ResultSet rs = ps.executeQuery();

               while (rs.next()) {

                   OrderItem bean = new OrderItem();
                   int id = rs.getInt(1);
                   int foodId = rs.getInt("foodId");
                   int number = rs.getInt("number");
                   Order order = new OrderDAOImpl().get(orderId);
                   
                   
                   
                   bean.setOrder(order);
                   bean.setFoodId(foodId);
                   bean.setNumber(number);
                   bean.setId(id);

                   beans.add(bean);
               }
           } catch (SQLException e) {

               e.printStackTrace();
           }
           return beans;
       }

}