package object;
//khởi tạo đối tượng chức năng book hoạt động giải trí
//verify book hoạt động giải trí
public class BookActivity {

	private String date;
	private String month;
	private String people;
	private String expect;
	public BookActivity(String date,String month, String people, String expect) {
		super();
		this.date = date;
		this.people = people;
		this.expect = expect;
		this.month = month;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getPeople() {
		return people;
	}
	public void setPeople(String people) {
		this.people = people;
	}
	public String getExpect() {
		return expect;
	}
	public void setExpect(String expect) {
		this.expect = expect;
	}
	
	
}
