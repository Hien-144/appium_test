package object;
//khởi tạo đối tượng thông tin đặt hàng 
//verify thông tin khách hàng đặt hàng

public class BookRoomTwo {
	private String nameHotel;
	private String firstName;
	private String name;
	private String email;
	private String country;
	private String phone;
	private String createPass;
	private String expect;
	
	public BookRoomTwo(String nameHotel, String firstName, String name, String email, String country, String phone, String createPass, String expect) {
		super();
		this.nameHotel = nameHotel;
		this.firstName = firstName;
		this.name = name;
		this.email = email;
		this.country = country;
		this.phone = phone;
		this.createPass = createPass;
		this.expect = expect;
	}

	public String getNameHotel() {
		return nameHotel;
	}

	public void setNameHotel(String nameHotel) {
		this.nameHotel = nameHotel;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	

	public String getCreatePass() {
		return createPass;
	}

	public void setCreatePass(String createPass) {
		this.createPass = createPass;
	}

	public String getExpect() {
		return expect;
	}

	public void setExpect(String expect) {
		this.expect = expect;
	}
	

}
