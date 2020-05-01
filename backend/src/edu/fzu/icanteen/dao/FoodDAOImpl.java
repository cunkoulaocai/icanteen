package edu.fzu.icanteen.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import edu.fzu.icanteen.pojo.Food;
import edu.fzu.icanteen.util.DBUtil;

public class FoodDAOImpl implements FoodDAO {

    @Override
	public int getTotal(int merchantId) {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
        	String sql = null;
        	if(merchantId == 0)    //不按类别
        		sql = "select count(*) from Food where 1 = 1 " ;
        	else 
        		sql = "select count(*) from Food where merchantId = " + merchantId;

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
	public void add(Food bean) {

        String sql = "insert into Food values(null,?,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, bean.getName());
            ps.setInt(2, bean.getMerchantId());
            ps.setString(3, bean.getDescription());
            ps.setDouble(4, bean.getPrice());
            ps.setInt(5, bean.getQuantity());
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
	public void update(Food bean) {

        String sql = "update Food set name =  ?, merchantid = ?, description = ?,price = ?,quantity = ?, where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

        	ps.setString(1, bean.getName());
            ps.setInt(2, bean.getMerchantId());
            ps.setString(3, bean.getDescription());
            ps.setDouble(4, bean.getPrice());
            ps.setInt(5, bean.getQuantity());
            ps.setInt(6, bean.getId());
            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    @Override
	public void delete(int id) {

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {

            String sql = "delete from Food where id = " + id;

            s.execute(sql);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
	public Food get(int id) {
        Food bean = new Food();

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {

            String sql = "select * from Food where id = " + id;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {

                String name = rs.getString("name");
                int merchantId = rs.getInt("merchantId");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                
                bean.setName(name);
                bean.setMerchantId(merchantId);
                bean.setDescription(description);
                bean.setPrice(price);
                bean.setQuantity(quantity);
                bean.setId(id);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return bean;
    }

    @Override
	public List<Food> list(int merchantId) {
        return list(merchantId, 0, Short.MAX_VALUE);
    }

    @Override
	public List<Food> list(int mId, int start, int count) {
        List<Food> beans = new ArrayList<Food>();
        String sql = "select * from Food where merchantId = ? order by id desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, mId);
            ps.setInt(2, start);
            ps.setInt(3, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Food bean = new Food();
                int id = rs.getInt(1);
                String name = rs.getString("name");
                int merchantId = rs.getInt("merchantId");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
               
                bean.setName(name);
                bean.setMerchantId(merchantId);
                bean.setDescription(description);
                bean.setPrice(price);
                bean.setQuantity(quantity);
                bean.setId(id);
                beans.add(bean);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return beans;
    }

    @Override
	public List<Food> list() {
        return list(0, Short.MAX_VALUE);
    }

    @Override
	public List<Food> list(int start, int count) {
        List<Food> beans = new ArrayList<Food>();

        String sql = "select * from Food limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, start);
            ps.setInt(2, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Food bean = new Food();
                int id = rs.getInt(1);
                String name = rs.getString("name");
                int merchantId = rs.getInt("merchantId");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
               
                bean.setName(name);
                bean.setMerchantId(merchantId);
                bean.setDescription(description);
                bean.setPrice(price);
                bean.setQuantity(quantity);
                bean.setId(id);

                beans.add(bean);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return beans;
    }

    @Override
	public List<Food> search(String keyword, int start, int count) {
        List<Food> beans = new ArrayList<Food>();

        if (null  ==  keyword || 0  ==  keyword.trim().length())
            return beans;
        String sql = "select * from Food where name like ? limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword.trim() + "%");
            ps.setInt(2, start);
            ps.setInt(3, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Food bean = new Food();
                int id = rs.getInt(1);
                String name = rs.getString("name");
                int merchantId = rs.getInt("merchantId");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
               
                bean.setName(name);
                bean.setMerchantId(merchantId);
                bean.setDescription(description);
                bean.setPrice(price);
                bean.setQuantity(quantity);
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
	public void add1(Food bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isExist(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Food get(String name) {
		// TODO Auto-generated method stub
		return null;
	}



}