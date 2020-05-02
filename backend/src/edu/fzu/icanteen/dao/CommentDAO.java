package edu.fzu.icanteen.dao;

import java.util.List;

import edu.fzu.icanteen.pojo.Comment;

public interface CommentDAO {
	
	int getTotal(int customerid);

	void add(Comment bean);
	
	void update(Comment bean);

	void delete(int id);

	Comment get(int id);

	List<Comment> list(int merchantid);

	List<Comment> list(int merchantid, int start, int count);

	List<Comment> list();

	List<Comment> list(int start, int count);

	List<Comment> search(String keyword, int start, int count);

	int getTotal();

	boolean isExist(int customerid);

	
}
