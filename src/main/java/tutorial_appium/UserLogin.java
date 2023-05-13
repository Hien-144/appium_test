package tutorial_appium;
//class để khởi tạo đối tượng logic 
public class UserLogin {
	//khai báo thuộc tính
	private String username;
	private String password;	
	private String expect;
	
	
	//constructor
	
	public UserLogin(String username, String password, String expect) {
		super();
		//thêm giá trị vào thuộc tính
		this.username = username;
		this.password = password;
		this.expect = expect;
	
	}
	public UserLogin() {
		super();
	}
	//get lấy giá trị, set gán giá trị cho thuộc tính 
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getExpect() {
		return expect;
	}
	public void setExpect(String expect) {
		this.expect = expect;
	}

	
}
//modify,class object, oop trong java
