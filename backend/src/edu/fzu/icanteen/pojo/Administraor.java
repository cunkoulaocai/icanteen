package edu.fzu.icanteen.pojo;

public class Administraor {
	//自增主键
    private int id;
    //姓名
    private String name;
    //密码
    private String password;
    //评论管理权限
    private int commentManage;
    //用户管理权限
    private int userManage;
    //商家管理权限
    private int merchantManage;
    //权限管理权限
    private int privilegeManage;

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

    public int getCommentManage() {
        return commentManage;
    }

    public void setCommentManage(int commentManage) {
        this.commentManage = commentManage;
    }
    
    public int getUserManage() {
        return userManage;
    }

    public void setUserManage(int userManage) {
        this.userManage = userManage;
    }
    
    public int getMerchantManage() {
        return merchantManage;
    }

    public void setMerchantManage(int merchantManage) {
        this.merchantManage = merchantManage;
    }
    
    public int getPrivilegeManage() {
        return privilegeManage;
    }

    public void setPrivilegeManage(int privilegeManage) {
        this.privilegeManage = privilegeManage;
    }
}
