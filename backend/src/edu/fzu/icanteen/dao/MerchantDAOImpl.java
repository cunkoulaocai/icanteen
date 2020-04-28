package edu.fzu.icanteen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.fzu.icanteen.pojo.Customer;
import edu.fzu.icanteen.pojo.Merchant;
import edu.fzu.icanteen.util.DBUtil;

public class MerchantDAOImpl implements MerchantDAO {
	private Connection connection = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	@Override
	public int getTotal() {
		int total = 0;
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
			String sql = "select count(*) from merchant";
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
	public void add(Merchant bean) {
		// TODO Auto-generated method stub
		String sql = "insert into merchant values(null ,? ,? ,? ,?)";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {			
			ps.setString(1, bean.getName());
			ps.setString(2, bean.getPassword());
			ps.setString(3, bean.getPhoneNumber());
			ps.setInt(4, bean.getState());
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
	public void update(Merchant bean) {
		// TODO Auto-generated method stub
		String sql = "update merchant set password=? where id=?";
		try {
			connection = DBUtil.getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, bean.getPassword());
			pstmt.setInt(2, bean.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstmt, connection);
		}

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Merchant get(int id) {
		// TODO Auto-generated method stub
		Merchant bean = null;
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
			String sql = "select * from merchant where id = " + id;
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {
				bean = new Merchant();
				String name = rs.getString("name");
				bean.setName(name);
				String password = rs.getString("password");
				bean.setPassword(password);
				String phoneNumber = rs.getString("phoneNumber");
				bean.setPhoneNumber(phoneNumber);
				int state = rs.getInt("state");
				bean.setState(state);
				bean.setId(id);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}

	@Override
	public List<Merchant> list() {
		// TODO Auto-generated method stub
		List<Merchant> merchants = new ArrayList<>();
		String sql = "SELECT * FROM merchant WHERE 1=1 ";
		// 获取连接；创建PreparedSatemant对象
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery(); // 得到结果集
			// 遍历
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Merchant merchant = new Merchant();
				merchant.setId(id);
				merchant.setName(name);				
				merchants.add(merchant);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return merchants;
	}

	@Override
	public List<Merchant> list(int start, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isExist(String name) {
		// TODO Auto-generated method stub
		Merchant merchant = get(name);
		return merchant != null;
	}

	@Override
	public Merchant get(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Merchant get(String name, String password) {
		// TODO Auto-generated method stub
		return null;
	}
}
