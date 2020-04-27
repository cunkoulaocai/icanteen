package edu.fzu.icanteen.dao;

import java.util.List;

import edu.fzu.icanteen.pojo.Customer;;

public interface CustomerDAO {
    int getTotal();
    void add(Customer customer);
    void update(Customer customer);
    void delete(int id) ;
    Customer get(int id);
    List<Customer> list();
    List<Customer> list(int start, int count) ;
    boolean isExist(String name) ;
    Customer get(String name) ;
    Customer get(String name, String password) ;
}