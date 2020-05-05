package edu.fzu.icanteen.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import edu.fzu.icanteen.pojo.ShoppingCart;
import edu.fzu.icanteen.util.DBUtil;

public class ShoppingCartDAOImpl implements ShoppingCartDAO {

    @Override
	public int getTotal(int cid) {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
        	String sql = null;
        	if(cid  ==  0)    //不按类别
        		sql = "select count(*) from ShoppingCart where 1 = 1 " ;
        	else 
        		sql = "select count(*) from ShoppingCart where cid = " + cid;

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
	public void add(ShoppingCart bean) {

        String sql = "insert into ShoppingCart values(null,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, bean.getCustomerId());
            ps.setInt(2, bean.getMerchantId());
            ps.setInt(3, bean.getFoodId());
            ps.setInt(4, bean.getNumber());
            
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
	public void update(ShoppingCart bean) {

        String sql = "update shoppingcart set customerid = ?, merchantid = ?, foodid = ?, number = ? where id = ??";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

        	ps.setInt(1, bean.getCustomerId());
            ps.setInt(2, bean.getMerchantId());
            ps.setInt(3, bean.getFoodId());
            ps.setInt(4, bean.getNumber());
            ps.setInt(5, bean.getId());
            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    @Override
	public void delete(int id) {

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {

            String sql = "delete from ShoppingCart where id = " + id;

            s.execute(sql);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
	public ShoppingCart get(int id) {
        ShoppingCart bean = new ShoppingCart();

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {

            String sql = "select * from ShoppingCart where id = " + id;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
            	
            	int customerId = rs.getInt("customerid");
                int merchantId = rs.getInt("merchantid");
                int foodId = rs.getInt("foodid");
                int number = rs.getInt("number");
                
                bean.setCustomerId(customerId);
                bean.setMerchantId(merchantId);
                
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
	public List<ShoppingCart> list(int cid) {
        return list(cid, 0, Short.MAX_VALUE);
    }

    @Override
	public List<ShoppingCart> list(int cid, int start, int count) {
        List<ShoppingCart> beans = new ArrayList<ShoppingCart>();
        String sql = "select * from ShoppingCart where cid = ? order by id desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, cid);
            ps.setInt(2, start);
            ps.setInt(3, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ShoppingCart bean = new ShoppingCart();
                int id = rs.getInt(1);
                
                int merchantId = rs.getInt("merchantId");
                int customerId = rs.getInt("customerid");
                int foodId = rs.getInt("foodid");
                int number = rs.getInt("number");
               
                bean.setMerchantId(merchantId);
                bean.setCustomerId(customerId);
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
	public List<ShoppingCart> search(String keyword, int start, int count) {
        List<ShoppingCart> beans = new ArrayList<ShoppingCart>();

        if (null   ==   keyword || 0   ==   keyword.trim().length())
            return beans;
        String sql = "select * from ShoppingCart where name like ? limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword.trim() + "%");
            ps.setInt(2, start);
            ps.setInt(3, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ShoppingCart bean = new ShoppingCart();
                int id = rs.getInt(1);
                
                int merchantId = rs.getInt("merchantId");
                int customerId = rs.getInt("customerid");
                int foodId = rs.getInt("foodid");
                int number = rs.getInt("number");
                
                bean.setMerchantId(merchantId);
                bean.setCustomerId(customerId);
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
	public int getTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void add1(ShoppingCart bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isExist(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ShoppingCart get(String name) {
		// TODO Auto-generated method stub
		return null;
	}



}