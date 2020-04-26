package edu.fzu.icanteen.pojo;

public class Merchant {
	//自增ID
    private int id;
    //商家姓名
    private String name;
    //密码
    private String password;
    //电话号码
    private String phoneNumber;
    //状态
    private int state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
