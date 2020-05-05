package edu.fzu.icanteen.dao;

import java.util.List;

import edu.fzu.icanteen.pojo.Merchant;


public interface MerchantDAO {
	int getTotal();
    void add(Merchant merchant);
    void update(Merchant merchant);
    void delete(int id);
    Merchant get(int id);
    List<Merchant> list();
    List<Merchant> list(int start, int count);
    boolean isExist(String name);
    Merchant get(String name);
    Merchant get(String name, String password);
}
