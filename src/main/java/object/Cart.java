	package object;
//khởi tạo đối tượng login trang booking.com
	//verify giỏ hàng
public class Cart {
	private String mail;
	private String passw;
	private String expect;
	public Cart(String mail, String passw, String expect) {
		super();
		this.mail = mail;
		this.passw = passw;
		this.expect = expect;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassw() {
		return passw;
	}
	public void setPassw(String passw) {
		this.passw = passw;
	}
	public String getExpect() {
		return expect;
	}
	public void setExpect(String expect) {
		this.expect = expect;
	}
	
}
