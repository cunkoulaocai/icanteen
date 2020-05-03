package edu.fzu.icanteen.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import edu.fzu.icanteen.pojo.Coupon;
import edu.fzu.icanteen.util.DBUtil;

public class CouponDAOImpl implements CouponDAO {

    @Override
	public int getTotal(int customerID) {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
        	String sql = null;
        	if(customerID  ==  0)    //不按类别
        		sql = "select count(*) from Coupon where 1 = 1 " ;
        	else 
        		sql = "select count(*) from Coupon where customerID = " + customerID;

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
	public void add(Coupon bean) {

        String sql = "insert into Coupon values(null,?,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, bean.getMerchantId());
            ps.setInt(2, bean.getCustomerId());
            ps.setDouble(3, bean.getThreshold());
            ps.setDouble(4, bean.getVoucher());
            ps.setDate(5, bean.getServiceLife());
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
	public void update(Coupon bean) {

        String sql = "update Coupon set merchantid = ?, customerid = ?, threshold = ?, voucher = ?, serviceLife = ? where id = ??";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

        	ps.setInt(1, bean.getMerchantId());
            ps.setInt(2, bean.getCustomerId());
            ps.setDouble(3, bean.getThreshold());
            ps.setDouble(4, bean.getVoucher());
            ps.setDate(5, bean.getServiceLife());
            ps.setInt(6, bean.getId());
            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    @Override
	public void delete(int id) {

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {

            String sql = "delete from Coupon where id = " + id;

            s.execute(sql);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
	public Coupon get(int id) {
        Coupon bean = new Coupon();

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {

            String sql = "select * from Coupon where id = " + id;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                
                int merchantId = rs.getInt("merchantid");
                int customerId = rs.getInt("customerid");
                double threshold = rs.getDouble("threshold");
                double voucher = rs.getDouble("voucher");
                Date serviceLife = rs.getDate("servicelife");
                
                
                bean.setMerchantId(merchantId);
                bean.setCustomerId(customerId);
                bean.setThreshold(threshold);
                bean.setVoucher(voucher);
                bean.setServicelife(serviceLife);
                bean.setId(id);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return bean;
    }

    @Override
	public List<Coupon> list(int customerID) {
        return list(customerID, 0, Short.MAX_VALUE);
    }

    @Override
	public List<Coupon> list(int customerID, int start, int count) {
        List<Coupon> beans = new ArrayList<Coupon>();
        String sql = "select * from Coupon where customerID = ? order by id desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, customerID);
            ps.setInt(2, start);
            ps.setInt(3, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Coupon bean = new Coupon();
                int id = rs.getInt(1);
                
                int merchantId = rs.getInt("merchantId");
                int customerId = rs.getInt("customerid");
                double threshold = rs.getDouble("threshold");
                double voucher = rs.getDouble("voucher");
                Date serviceLife = rs.getDate("servicelife");
               
                
                bean.setMerchantId(merchantId);
                bean.setCustomerId(customerId);
                bean.setThreshold(threshold);
                bean.setVoucher(voucher);
                bean.setServicelife(serviceLife);
                bean.setId(id);
                beans.add(bean);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return beans;
    }

    @Override
	public List<Coupon> search(String keyword, int start, int count) {
        List<Coupon> beans = new ArrayList<Coupon>();

        if (null   ==   keyword || 0   ==   keyword.trim().length())
            return beans;
        String sql = "select * from Coupon where name like ? limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword.trim() + "%");
            ps.setInt(2, start);
            ps.setInt(3, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Coupon bean = new Coupon();
                int id = rs.getInt(1);
                
                int merchantId = rs.getInt("merchantId");
                int customerId = rs.getInt("customerid");
                double threshold = rs.getDouble("threshold");
                double voucher = rs.getDouble("voucher");
                Date serviceLife = rs.getDate("servicelife");
               
                bean.setMerchantId(merchantId);
                bean.setCustomerId(customerId);
                bean.setThreshold(threshold);
                bean.setVoucher(voucher);
                bean.setServicelife(serviceLife);
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
	public void add1(Coupon bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isExist(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Coupon get(String name) {
		// TODO Auto-generated method stub
		return null;
	}



}