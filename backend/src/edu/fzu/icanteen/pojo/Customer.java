package edu.fzu.icanteen.pojo;

public class Customer {
	//��������
    private int id;
    //ѧ��    
    private String studentId;
    //����
    private String name;
    //����
    private String password;
    //����
    private int point;
    //״̬
    private int state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    public int getPoint() {
        return point;
    }

    public void setPonit(int point) {
        this.point = point;
    }
    
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
