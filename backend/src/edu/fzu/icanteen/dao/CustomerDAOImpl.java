package edu.fzu.icanteen.dao;

import edu.fzu.icanteen.pojo.Customer;
import edu.fzu.icanteen.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath.Step;

public class CustomerDAOImpl implements CustomerDAO {
	private Connection connection = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	@Override
	public int getTotal() {
		int total = 0;
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
			String sql = "select count(*) from customer";
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
	public void add(Customer bean) {
		String sql = "insert into customer values(null ,? ,? ,? ,? ,?)";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, bean.getStudentId());
			ps.setString(2, bean.getName());
			ps.setString(3, bean.getPassword());
			ps.setInt(4, bean.getPoint());
			ps.setInt(5, bean.getState());
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
	public void update(Customer bean) {
		String sql = "update customer set password = ? where id = ?";
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

	}

	@Override
	public Customer get(int id) {
		Customer bean = null;
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
			String sql = "select * from Customer where id = " + id;
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {
				bean = new Customer();
				String studentId = rs.getString("studentId");
				bean.setStudentId(studentId);
				String name = rs.getString("name");
				bean.setName(name);
				String password = rs.getString("password");
				bean.setPassword(password);
				int point = rs.getInt("point");
				bean.setPonit(point);
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
	public List<Customer> list() {
		List<Customer> customers = new ArrayList<>();
		String sql = "SELECT * FROM customer WHERE 1 = 1 ";
		// 获取连接；创建PreparedSatemant对象
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery(); // 得到结果集
			// 遍历
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				// String password = rs.getString("password");
				Customer customer = new Customer();
				customer.setId(id);
				customer.setName(name);
				// customer.setPassword(password);
				customers.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customers;

	}

	@Override
	public List<Customer> list(int start, int count) {
		return null;
	}

	@Override
	public boolean isExist(String name,String password) {
		Customer customer = get(name,password);
		return customer !=  null;
	}

	@Override
	public Customer get(String name) {
		return null;
	}

	
	/*
	 * connection = DBUtil.getConnection(); 
	 * pstmt =connection.prepareStatement(sql);
	 * pstmt.setString(1, bean.getPassword());
	 * pstmt.setInt(2, bean.getId()); 
	 * pstmt.executeUpdate();
	 */
	public Customer get(String studentId, String password) {
		Customer customer = null;
		String sql = "SELECT * FROM customer WHERE studentid = ? and password = ?";
		// 获取连接；创建PreparedSatemant对象
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, studentId);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery(); // 得到结果集
			
			while (rs.next()) {
				customer = new Customer();
				int id = rs.getInt("id");
				String sId = rs.getString("studentid");
				String psd = rs.getString("password");
				String name = rs.getString("name");
				int point = rs.getInt("point");
				int state = rs.getInt("state");
				customer.setId(id);
				customer.setStudentId(sId);
				customer.setName(name);
				customer.setPassword(psd);
				customer.setPonit(point);
				customer.setState(state);
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}
}