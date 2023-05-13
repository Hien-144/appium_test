package tutorial_appium;
//khởi tạo đối tượng chọn số lượng người
////verify ngày đặt phòng
public class BookPeopleOne {
	private String room;
	private String people;
	private String baby;
	private boolean expectDisable;
	private String expect;
	
	public BookPeopleOne(String room, String people, String baby, String expect, boolean expectDisable) {
		super();
		this.room = room;
		this.people = people;
		this.baby = baby;
		this.expect = expect;
		this.expectDisable = expectDisable;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

	public String getBaby() {
		return baby;
	}

	public void setBaby(String baby) {
		this.baby = baby;
	}

	public String getExpect() {
		return expect;
	}

	public void setExpect(String expect) {
		this.expect = expect;
	}

	public boolean getExpectDisable() {
		return expectDisable;
	}

	public void setExpectDisable(boolean expectDisable) {
		this.expectDisable = expectDisable;
	}
	

}
