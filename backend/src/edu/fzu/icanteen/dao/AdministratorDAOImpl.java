package edu.fzu.icanteen.dao;

import edu.fzu.icanteen.pojo.Administrator;
import edu.fzu.icanteen.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdministratorDAOImpl implements AdministratorDAO {
	private Connection connection = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	@Override
	public int getTotal() {
		int total = 0;
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
			String sql = "select count(*) from administrator";
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
	public void add(Administrator bean) {
		String sql = "insert into administrator values(null ,? ,? ,? ,? ,? ,?)";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, bean.getName());
			ps.setString(2, bean.getPassword());
			ps.setInt(3, bean.getCommentManage());
			ps.setInt(4, bean.getUserManage());
			ps.setInt(5, bean.getMerchantManage());
			ps.setInt(6, bean.getPrivilegeManage());
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
	public void update(Administrator bean) {
		String sql = "update administrator set password = ? where id = ?";
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
	public Administrator get(int id) {
		Administrator bean = null;
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
			String sql = "select * from Administrator where id = " + id;
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {
				bean = new Administrator();
				String name = rs.getString("name");
				bean.setName(name);
				String password = rs.getString("password");
				bean.setPassword(password);
				int commentManage = rs.getInt("commentmanage");
				bean.setCommentManage(commentManage);
				int userManage = rs.getInt("usermanage");
				bean.setUserManage(userManage);
				int merchantManage = rs.getInt("merchantmanage");
				bean.setMerchantManage(merchantManage);
				int privilegeManage = rs.getInt("privilegemanage");
				bean.setPrivilegeManage(privilegeManage);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}

	@Override
	public List<Administrator> list() {
		List<Administrator> administrators = new ArrayList<>();
		String sql = "SELECT * FROM administrator WHERE 1 = 1 ";
		// 获取连接；创建PreparedSatemant对象
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery(); // 得到结果集
			// 遍历
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				// String password = rs.getString("password");
				Administrator administrator = new Administrator();
				administrator.setId(id);
				administrator.setName(name);
				// administrator.setPassword(password);
				administrators.add(administrator);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return administrators;

	}

	@Override
	public List<Administrator> list(int start, int count) {
		return null;
	}

	@Override
	public boolean isExist(String name) {
		Administrator administrator = get(name);
		return administrator !=  null;
	}

	@Override
	public Administrator get(String name) {
		return null;
	}

	public Administrator get(String name, String password) {
		return null;
	}

}