package edu.fzu.icanteen.pojo;

public class Administraor {
	//��������
    private int id;
    //����
    private String name;
    //����
    private String password;
    //���۹���Ȩ��
    private int commentManage;
    //�û�����Ȩ��
    private int userManage;
    //�̼ҹ���Ȩ��
    private int merchantManage;
    //Ȩ�޹���Ȩ��
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
