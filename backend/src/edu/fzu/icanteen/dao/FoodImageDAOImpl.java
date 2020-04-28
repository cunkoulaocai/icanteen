package edu.fzu.icanteen.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import edu.fzu.icanteen.pojo.Food;
import edu.fzu.icanteen.pojo.FoodImage;
import edu.fzu.icanteen.util.DBUtil;

public class FoodImageDAOImpl implements FoodImageDAO {

    @Override
	public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {

            String sql = "select count(*) from FoodImage";

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
	public void add(FoodImage bean) {


        String sql = "insert into FoodImage values(null,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, bean.getFood().getId());
            ps.setString(2, bean.getUrl());
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
	public void update(FoodImage bean) {

    }

    @Override
	public void delete(int id) {

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {

            String sql = "delete from FoodImage where id = " + id;

            s.execute(sql);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
	public FoodImage get(int id) {
        FoodImage bean = new FoodImage();


        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {

            String sql = "select * from FoodImage where id = " + id;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                int pid = rs.getInt("pid");
                String url = rs.getString("url");
                Food food = new FoodDAOImpl().get(pid);
                bean.setFood(food);
                bean.setUrl(url);
                bean.setId(id);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return bean;
    }

    @Override
	public List<FoodImage> list(Food p, String url) {
        return list(p, url, 0, Short.MAX_VALUE);
    }

    @Override
	public List<FoodImage> list(Food p, String url, int start, int count) {
        List<FoodImage> beans = new ArrayList<FoodImage>();

        String sql = "select * from FoodImage where pid  = ? and url  = ? order by id desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, p.getId());
            ps.setString(2, url);
            ps.setInt(3, start);
            ps.setInt(4, count);


            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                FoodImage bean = new FoodImage();
                int id = rs.getInt(1);


                bean.setFood(p);
                bean.setUrl(url);
                bean.setId(id);

                beans.add(bean);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return beans;
    }

}