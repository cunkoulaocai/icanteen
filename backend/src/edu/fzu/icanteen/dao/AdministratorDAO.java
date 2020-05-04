package edu.fzu.icanteen.dao;

import java.util.List;

import edu.fzu.icanteen.pojo.Administrator;

public interface AdministratorDAO {
	
    int getTotal();
    
    void add(Administrator administrator);
    
    void update(Administrator administrator);
    
    void delete(int id);
    
    Administrator get(int id);
    
    List<Administrator> list();
    
    List<Administrator> list(int start, int count);
    
    boolean isExist(String name);
    
    Administrator get(String name);
    
    Administrator get(String name, String password);
    
}