package tutorial_appium;
//khởi tạo đối tượng đánh giá 
// verify đánh giá
public class Rated {
	private String vote;
	private String type;
	private String rated;
	private String heading;
	private String expect;

	public Rated(String vote, String type, String rated, String heading, String expect) {
		super();
		this.vote = vote;
		this.type = type;
		this.rated = rated;
		this.heading = heading;
		this.expect = expect;
	}

	public String getVote() {
		return vote;
	}

	public void setVote(String vote) {
		this.vote = vote;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRated() {
		return rated;
	}
	
	public String getExpect() {
		return expect;
	}

	public void setExpect(String expect) {
		this.expect = expect;
	}

	public void setRated(String rated) {
		this.rated = rated;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}
	
	
	
}
