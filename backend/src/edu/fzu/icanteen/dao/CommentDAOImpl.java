package edu.fzu.icanteen.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.fzu.icanteen.pojo.Comment;
import edu.fzu.icanteen.util.DBUtil;

public class CommentDAOImpl implements CommentDAO {
	private Connection connection = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	@Override
	public int getTotal(int customerid) {
		// TODO Auto-generated method stub
		int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
        	String sql = null;
        	if(customerid == 0)    //不按类别
        		sql = "select count(*) from Order where 1 = 1 " ;
        	else 
        		sql = "select count(*) from Order where customerid = " + customerid;

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
	public void add(Comment bean) {
		// TODO Auto-generated method stub
		String sql = "insert into comment values(null ,? ,? ,? ,? , ?, ?, ?, ?, ?, ?, ?)";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, bean.getCustomerid());
			ps.setInt(2, bean.getMerchantid());
			ps.setInt(3, bean.getFoodid());
			ps.setString(4, bean.getContent());
			ps.setDate(5, bean.getSendtime());
			ps.setInt(6, bean.getOverallscore());
			ps.setInt(7, bean.getAttitudescore());
			ps.setInt(8, bean.getTastescore());
			ps.setInt(9, bean.getPricescore());
			ps.setInt(10, bean.getWaitingtime());
			ps.setInt(11, bean.getState());
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
	public void update(Comment bean) {
		// TODO Auto-generated method stub
		String sql = "update Comment set customerid = ?, merchantid = ?, foodid = ?, content = ?, sendtime = ?, overallscore = ?,attitudescore = ?,tastescore = ?,pricescore = ?,waitingtime = ?,state = ? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

        	ps.setInt(1, bean.getCustomerid());
			ps.setInt(2, bean.getMerchantid());
			ps.setInt(3, bean.getFoodid());
			ps.setString(4, bean.getContent());
			ps.setDate(5, bean.getSendtime());
			ps.setInt(6, bean.getOverallscore());
			ps.setInt(7, bean.getAttitudescore());
			ps.setInt(8, bean.getTastescore());
			ps.setInt(9, bean.getPricescore());
			ps.setInt(10, bean.getWaitingtime());
			ps.setInt(11, bean.getState());
            ps.setInt(12, bean.getId());
            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {

            String sql = "delete from Comment where id = " + id;

            s.execute(sql);

        } catch (SQLException e) {

            e.printStackTrace();
        }
	}

	@Override
	public Comment get(int id) {
		// TODO Auto-generated method stub
		Comment bean = new Comment();

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {

            String sql = "select * from Comment where id = " + id;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {

                int customerid = rs.getInt("customerid");
                int merchantid = rs.getInt("merchantid");
                int foodid = rs.getInt("foodid");
                String content = rs.getString("content");
                Date sendtime = rs.getDate("sendtime");
                int overallscore = rs.getInt("overallscore");
                int attitudescore = rs.getInt("attitudescore");
                int tastescore = rs.getInt("tastescore");
                int pricescore = rs.getInt("pricescore");
                int waitingtime = rs.getInt("waitingtime");
                int state = rs.getInt("state");
                
                
                bean.setCustomerid(customerid);
                bean.setMerchantid(merchantid);
                bean.setFoodid(foodid);
                bean.setContent(content);
                bean.setSendtime(sendtime);
                bean.setOverallscore(overallscore);
                bean.setAttitudescore(attitudescore);
                bean.setTastescore(tastescore);
                bean.setPricescore(pricescore);
                bean.setWaitingtime(waitingtime);
                bean.setState(state);
                bean.setId(id);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return bean;
	}

	@Override
	public List<Comment> list(int merchantid) {
		// TODO Auto-generated method stub
		return list(merchantid, 0, Short.MAX_VALUE);
	}

	@Override
	public List<Comment> list(int merchantid, int start, int count) {
		// TODO Auto-generated method stub
		List<Comment> beans = new ArrayList<Comment>();
        String sql = "select * from Comment where merchantid = ? order by id desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, merchantid);
            ps.setInt(2, start);
            ps.setInt(3, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            	Comment bean = new Comment();
            	int id = rs.getInt(1);
            	int customerid = rs.getInt("customerid");
                int mid = rs.getInt("merchantid");
                int foodid = rs.getInt("foodid");
                String content = rs.getString("content");
                Date sendtime = rs.getDate("sendtime");
                int overallscore = rs.getInt("overallscore");
                int attitudescore = rs.getInt("attitudescore");
                int tastescore = rs.getInt("tastescore");
                int pricescore = rs.getInt("pricescore");
                int waitingtime = rs.getInt("waitingtime");
                int state = rs.getInt("state");
               
                bean.setCustomerid(customerid);
                bean.setMerchantid(mid);
                bean.setFoodid(foodid);
                bean.setContent(content);
                bean.setSendtime(sendtime);
                bean.setOverallscore(overallscore);
                bean.setAttitudescore(attitudescore);
                bean.setTastescore(tastescore);
                bean.setPricescore(pricescore);
                bean.setWaitingtime(waitingtime);
                bean.setState(state);
                bean.setId(id);
                beans.add(bean);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return beans;
	}

	@Override
	public List<Comment> list() {
		// TODO Auto-generated method stub
		return list(0, Short.MAX_VALUE);
	}

	@Override
	public List<Comment> list(int start, int count) {
		// TODO Auto-generated method stub
		List<Comment> beans = new ArrayList<Comment>();
        String sql = "select * from Comment limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, start);
            ps.setInt(2, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            	Comment bean = new Comment();
            	int id = rs.getInt(1);
            	int customerid = rs.getInt("customerid");
                int mid = rs.getInt("merchantid");
                int foodid = rs.getInt("foodid");
                String content = rs.getString("content");
                Date sendtime = rs.getDate("sendtime");
                int overallscore = rs.getInt("overallscore");
                int attitudescore = rs.getInt("attitudescore");
                int tastescore = rs.getInt("tastescore");
                int pricescore = rs.getInt("pricescore");
                int waitingtime = rs.getInt("waitingtime");
                int state = rs.getInt("state");
               
                bean.setCustomerid(customerid);
                bean.setMerchantid(mid);
                bean.setFoodid(foodid);
                bean.setContent(content);
                bean.setSendtime(sendtime);
                bean.setOverallscore(overallscore);
                bean.setAttitudescore(attitudescore);
                bean.setTastescore(tastescore);
                bean.setPricescore(pricescore);
                bean.setWaitingtime(waitingtime);
                bean.setState(state);
                bean.setId(id);
                beans.add(bean);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return beans;
	}

	@Override
	public List<Comment> search(String keyword, int start, int count) {
		// TODO Auto-generated method stub
		List<Comment> beans = new ArrayList<Comment>();

        if (null  ==  keyword || 0  ==  keyword.trim().length())
            return beans;
        String sql = "select * from Comment where name like ? limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword.trim() + "%");
            ps.setInt(2, start);
            ps.setInt(3, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            	Comment bean = new Comment();
            	int id = rs.getInt(1);
            	int cid = rs.getInt("customerid");
                int merchantid = rs.getInt("merchantid");
                int foodid = rs.getInt("foodid");
                String content = rs.getString("content");
                Date sendtime = rs.getDate("sendtime");
                int overallscore = rs.getInt("overallscore");
                int attitudescore = rs.getInt("attitudescore");
                int tastescore = rs.getInt("tastescore");
                int pricescore = rs.getInt("pricescore");
                int waitingtime = rs.getInt("waitingtime");
                int state = rs.getInt("state");
               
                bean.setCustomerid(cid);
                bean.setMerchantid(merchantid);
                bean.setFoodid(foodid);
                bean.setContent(content);
                bean.setSendtime(sendtime);
                bean.setOverallscore(overallscore);
                bean.setAttitudescore(attitudescore);
                bean.setTastescore(tastescore);
                bean.setPricescore(pricescore);
                bean.setWaitingtime(waitingtime);
                bean.setState(state);
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
	public boolean isExist(int customerid) {
		// TODO Auto-generated method stub
		return false;
	}

}
