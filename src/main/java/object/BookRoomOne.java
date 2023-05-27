package object;
// khởi tạo đối tượng chọn từ ngày đến ngày
////verify ngày đặt phòng

public class BookRoomOne {
	private String from;
	private String to;
	private String monthFrom;
	private String monthTo;
	private String expect;
	
	public BookRoomOne(String from, String to, String monthFrom ,String monthTo, String expect) {
		super();
		this.from = from;
		this.to = to;
		this.monthFrom= monthFrom;
		this.monthTo = monthTo;
		this.expect = expect;
	}
	
	public BookRoomOne() {
		// TODO Auto-generated constructor stub
	}

	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	
	public String getMonthFrom() {
		return monthFrom;
	}

	public void setMonthFrom(String monthFrom) {
		this.monthFrom = monthFrom;
	}

	public String getMonthTo() {
		return monthTo;
	}

	public void setMonthTo(String monthTo) {
		this.monthTo = monthTo;
	}

	public void setTo(String to) {
		this.to = to;
	}
	public String getExpect() {
		return expect;
	}
	public void setExpect(String expect) {
		this.expect = expect;
	}
	
}
