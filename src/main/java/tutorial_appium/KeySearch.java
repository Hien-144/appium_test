package tutorial_appium;
//khởi tạo đối tượng search

public class KeySearch {
	private String keySearch;
	private String expect;
	public KeySearch(String keySearch, String expect) {
		super();
		this.keySearch = keySearch;
		this.expect = expect;
	}
	public String getKeySearch() {
		return keySearch;
	}
	public void setKeySearch(String keySearch) {
		this.keySearch = keySearch;
	}
	public String getExpect() {
		return expect;
	}
	public void setExpect(String expect) {
		this.expect = expect;
	}
	
	
	
}
