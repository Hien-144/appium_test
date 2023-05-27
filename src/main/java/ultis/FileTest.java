package ultis;
//đọc ghi dữ liệu từ file 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import object.BookActivity;
import object.BookPeopleOne;
import object.BookRoomOne;
import object.BookRoomTwo;
import object.Cart;
import object.KeySearch;
import object.Rated;
import object.UserLogin;

public class FileTest {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFCell cell;
	private WebDriver driver;
	private String url = "https://www.facebook.com/";
	private ArrayList<UserLogin> userLogin;//khai báo
	private ArrayList<KeySearch> keySearch;//khai báo
	private ArrayList<BookRoomOne> bookRoomOne ;//khai báo

	private ArrayList<BookPeopleOne> readBookPeopleOne;//khai báo
	private ArrayList<BookRoomTwo> readBookRoomTwo;

	private ArrayList<Cart> readCart;
	private ArrayList<BookActivity> readBookActivity ;
	private ArrayList<Rated> readRated;

	public FileTest() {
		super();
		userLogin = new ArrayList<UserLogin>();//khởi tạo 1 đối tượng để chứa danh sách các tài khoản ngừoi dùng
		keySearch = new ArrayList<KeySearch>();
		bookRoomOne = new ArrayList<BookRoomOne>();
		readBookPeopleOne = new ArrayList<BookPeopleOne>();
		readBookRoomTwo = new ArrayList<BookRoomTwo>();
		readCart = new ArrayList<Cart>();
		readBookActivity= new ArrayList<BookActivity>();
		readRated = new ArrayList<Rated>();
		
		
	}

	public void TestSetup() {
		driver = new FirefoxDriver();
		driver.get(url);
	}

	public ArrayList<UserLogin> Read() {//hàm đọc login
		// Import file excel
		File src = new File("/home/hien144/eclipse-workspace/test/test.xlsx");//đường dẫn trỏ đến file
		FileInputStream fis = null;//để đọc dữ liệu từ một tệp dưới dạng chuỗi byte
		
		try {
			fis = new FileInputStream(src);//khởi tạo biến FileInputStream
			workbook = new XSSFWorkbook(fis);//khởi tạo biến để đọc file xlsx
			sheet = workbook.getSheet("Sheet1");
//			System.out.println(sheet.getLastRowNum());
			for (int i = 1; i <= 8; i++) {
//				System.out.println(sheet.getRow(i).getCell(5) + "ccc" + i);
				String username = sheet.getRow(i).getCell(4).toString();
				String password = sheet.getRow(i).getCell(5).toString();
				String expect = sheet.getRow(i).getCell(6).toString();
				userLogin.add(new UserLogin(username, password, expect )); //khởi tạo 1 đ	ối tượng UserLogin và thêm vào trong ds các đối tượng tài khoản người dùng
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userLogin;
	}
	
	public ArrayList<KeySearch> ReadSearch() {//hàm đọc searchsearch
		// Import file excel
		File src = new File("/home/hien144/eclipse-workspace/test/test.xlsx");//đường dẫn trỏ đến file
		FileInputStream fis = null;//để đọc dữ liệu từ một tệp dưới dạng chuỗi byte
		
		try {
			fis = new FileInputStream(src);//khởi tạo biến FileInputStream
			workbook = new XSSFWorkbook(fis);//khởi tạo biến để đọc file xlsx
			sheet = workbook.getSheet("Sheet1");
//			System.out.println(sheet.getLastRowNum());
			for (int i = 9; i <= 15; i++) {
//				System.out.println(sheet.getRow(i).getCell(5) + "ccc" + i);
				String search= sheet.getRow(i).getCell(4) != null ? sheet.getRow(i).getCell(4).toString() : "";
				String expect = sheet.getRow(i).getCell(6)!= null ? sheet.getRow(i).getCell(6).toString() : "";
				keySearch.add(new KeySearch(search, expect )); //khởi tạo 1 đ	ối tượng UserLogin và thêm vào trong ds các đối tượng tài khoản người dùng
			}
			for (int i = 73; i <= 79; i++) {
//				System.out.println(sheet.getRow(i).getCell(5) + "ccc" + i);
				String search= sheet.getRow(i).getCell(4) != null ? sheet.getRow(i).getCell(4).toString() : "";
				String expect = sheet.getRow(i).getCell(6)!= null ? sheet.getRow(i).getCell(6).toString() : "";
				keySearch.add(new KeySearch(search, expect )); //khởi tạo 1 đ	ối tượng UserLogin và thêm vào trong ds các đối tượng tài khoản người dùng
			}
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return keySearch;
	}
	
	public ArrayList<BookRoomOne> ReadBookRoomOne () {//hàm đọc lọc dữ liệu chọn ngàyngày
		// Import file excel
		File src = new File("/home/hien144/eclipse-workspace/test/test.xlsx");//đường dẫn trỏ đến file
		FileInputStream fis = null;//để đọc dữ liệu từ một tệp dưới dạng chuỗi byte
		
		try {
			fis = new FileInputStream(src);//khởi tạo biến FileInputStream
			workbook = new XSSFWorkbook(fis);//khởi tạo biến để đọc file xlsx
			sheet = workbook.getSheet("Sheet1");
//			System.out.println(sheet.getLastRowNum());
			for (int i = 17; i <= 20; i++) {
//				System.out.println(sheet.getRow(i).getCell(5) + "ccc" + i);
				String dayFrom = sheet.getRow(i).getCell(4).toString();
				String [] arrayDayFrom= dayFrom.split(",");
				String from = arrayDayFrom[0];
				String monthFrom = arrayDayFrom[1];
				
				String dayTo = sheet.getRow(i).getCell(5).toString();
				String [] arrayDayTo = dayTo.split(",");
				String to = arrayDayTo[0];
				String monthTo = arrayDayTo[1];
				String expect = sheet.getRow(i).getCell(6).toString();
				bookRoomOne.add(new BookRoomOne(from ,to, monthFrom, monthTo, expect)); //khởi tạo 1 đ	ối tượng UserLogin và thêm vào trong ds các đối tượng tài khoản người dùng
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookRoomOne;
	}
	
	public ArrayList<BookPeopleOne> readBookPeopleOne () {//hàm đọc lọc dữ liệu chọn phòng và người
		// Import file excel
		File src = new File("/home/hien144/eclipse-workspace/test/test.xlsx");//đường dẫn trỏ đến file
		FileInputStream fis = null;//để đọc dữ liệu từ một tệp dưới dạng chuỗi byte
		
		try {
			fis = new FileInputStream(src);//khởi tạo biến FileInputStream
			workbook = new XSSFWorkbook(fis);//khởi tạo biến để đọc file xlsx
			sheet = workbook.getSheet("Sheet1");
//			System.out.println(sheet.getLastRowNum());
			for (int i = 22; i <= 23; i++) {//chọn roomroom
				String room= sheet.getRow(i).getCell(4).toString();
				String expect = sheet.getRow(i).getCell(6).toString();
				boolean exp = expect.equalsIgnoreCase("Disable") ? false : true ;
				readBookPeopleOne.add(new BookPeopleOne(room, "",  "", expect, exp)); //khởi tạo 1 đ	ối tượng UserLogin và thêm vào trong ds các đối tượng tài khoản người dùng
			}
			for (int i = 24; i <= 25; i++) {//chọn nguoi lonlon
//				
				String people = sheet.getRow(i).getCell(4).toString();
				String expect = sheet.getRow(i).getCell(6).toString();
				boolean exp = expect.equalsIgnoreCase("Disable") ? false : true ;
				readBookPeopleOne.add(new BookPeopleOne("", people,  "", expect, exp)); //khởi tạo 1 đ	ối tượng UserLogin và thêm vào trong ds các đối tượng tài khoản người dùng
			}
			for (int i = 26; i <= 27; i++) {//chọn baby 
				String baby= sheet.getRow(i).getCell(4).toString();
				String expect = sheet.getRow(i).getCell(6).toString();
				boolean exp = expect.equalsIgnoreCase("Disable") ? false : true ;
				readBookPeopleOne.add(new BookPeopleOne("", "", baby, expect, exp)); //khởi tạo 1 đ	ối tượng UserLogin và thêm vào trong ds các đối tượng tài khoản người dùng
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return readBookPeopleOne;
	}
	
	public BookRoomOne readLine (int line) {//hàm đọc ddawth lạilại
		// Import file excel
		File src = new File("/home/hien144/eclipse-workspace/test/test.xlsx");//đường dẫn trỏ đến file
		FileInputStream fis = null;//để đọc dữ liệu từ một tệp dưới dạng chuỗi byte
		BookRoomOne bro = new BookRoomOne();
		
		try {
			fis = new FileInputStream(src);//khởi tạo biến FileInputStream
			workbook = new XSSFWorkbook(fis);//khởi tạo biến để đọc file xlsx
			sheet = workbook.getSheet("Sheet1");
			String dayFrom = sheet.getRow(line).getCell(4).toString();
			String [] arrayDayFrom= dayFrom.split(",");
			String from = "";
			String monthFrom = "";
			String monthTo = "";
			String to = "";
			if (arrayDayFrom.length > 1) {
				from = arrayDayFrom[0];
				monthFrom = arrayDayFrom[1];
			}			
			String dayTo = sheet.getRow(line).getCell(5).toString();
			String [] arrayDayTo = dayTo.split(",");
			if (arrayDayTo.length > 1) {
				to = arrayDayTo[0];
				monthTo = arrayDayTo[1];
			}
			String expect = sheet.getRow(line).getCell(6).toString();
			bro.setFrom(from);
			bro.setMonthFrom(monthFrom);
			bro.setTo(to);
			bro.setMonthTo(monthTo);
			bro.setExpect(expect);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bro;
	}
	public ArrayList<BookRoomTwo> readBookRoomTwo() {//hàm đọc đặt phòngphòng
		// Import file excel
		File src = new File("/home/hien144/eclipse-workspace/test/test.xlsx");//đường dẫn trỏ đến file
		FileInputStream fis = null;//để đọc dữ liệu từ một tệp dưới dạng chuỗi byte
		
		try {
			fis = new FileInputStream(src);//khởi tạo biến FileInputStream
			workbook = new XSSFWorkbook(fis);//khởi tạo biến để đọc file xlsx
			sheet = workbook.getSheet("Sheet1");
//			System.out.println(sheet.getLastRowNum());
//			String nameHotel= sheet.getRow(i).getCell(4) != null ? sheet.getRow(i).getCell(4).toString() : "";
			String firstName= "";
			String name= "";
			String email= "";
			String country= "";
			String phone= "";
			String createPass = "";
			String expect= "";
			for (int i = 32; i <= 62; i++) {	
				if (i <= 36) {
					firstName= sheet.getRow(i).getCell(4)!= null ? sheet.getRow(i).getCell(4).toString() : "";
//					System.out.println("firstname "+ i + " " + firstName);
				} else if (i <= 41) {
					name= sheet.getRow(i).getCell(4)!= null ? sheet.getRow(i).getCell(4).toString() : "";
//					System.out.println("firstname "+ i + " " + name);					
				} else if (i <= 47) {
					email= sheet.getRow(i).getCell(4)!= null ? sheet.getRow(i).getCell(4).toString() : "";
					
				} else if (i == 48 || i == 52) {
					country= sheet.getRow(i).getCell(4)!= null ? sheet.getRow(i).getCell(4).toString() : "";
//					System.out.println(country);
				} else if (i <= 56 && i != 48 && i != 52) {
					phone= sheet.getRow(i).getCell(4)!= null ? sheet.getRow(i).getCell(4).toString() : "";
				}else if (i <= 62 & i>=57) {
					createPass= sheet.getRow(i).getCell(4)!= null ? sheet.getRow(i).getCell(4).toString() : "";
				}
				
				expect = sheet.getRow(i).getCell(6)!= null ? sheet.getRow(i).getCell(6).toString() : "";
				readBookRoomTwo.add(new BookRoomTwo("", firstName, name, email, country, phone, createPass, expect)); //khởi tạo 1 đ	ối tượng UserLogin và thêm vào trong ds các đối tượng tài khoản người dùng
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return readBookRoomTwo;
	}
	
	public ArrayList<Cart> readCart() {//hàm đọc giỏ hàng 
		// Import file excel
		File src = new File("/home/hien144/eclipse-workspace/test/test.xlsx");//đường dẫn trỏ đến file
		FileInputStream fis = null;//để đọc dữ liệu từ một tệp dưới dạng chuỗi byte
		
		try {
			fis = new FileInputStream(src);//khởi tạo biến FileInputStream
			workbook = new XSSFWorkbook(fis);//khởi tạo biến để đọc file xlsx
			sheet = workbook.getSheet("Sheet1");
//			System.out.println(sheet.getLastRowNum());
//			String nameHotel= sheet.getRow(i).getCell(4) != null ? sheet.getRow(i).getCell(4).toString() : "";
			String mail= "";
			String passw= "";
			String expect ="";
	
			for (int i = 63; i <= 66; i++) {	
				if (i <= 64) {
					mail= sheet.getRow(i).getCell(4)!= null ? sheet.getRow(i).getCell(4).toString() : "";
//					System.out.println("firstname "+ i + " " + firstName);
				} else if (i <= 66) {
					passw = sheet.getRow(i).getCell(4)!= null ? sheet.getRow(i).getCell(4).toString() : "";
//					System.out.println("firstname "+ i + " " + name);					
				} 
				expect = sheet.getRow(i).getCell(6)!= null ? sheet.getRow(i).getCell(6).toString() : "";
				
				readCart.add(new Cart(mail, passw, expect)); //khởi tạo 1 đối tượng UserLogin và thêm vào trong ds các đối tượng tài khoản người dùng
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return readCart;
	}

	public ArrayList<BookActivity> ReadBookActivity () {//hàm đọc lọc dữ liệu chọn ngày book hoạt động
		// Import file excel
		File src = new File("/home/hien144/eclipse-workspace/test/test.xlsx");//đường dẫn trỏ đến file
		FileInputStream fis = null;//để đọc dữ liệu từ một tệp dưới dạng chuỗi byte
		
		try {
			fis = new FileInputStream(src);//khởi tạo biến FileInputStream
			workbook = new XSSFWorkbook(fis);//khởi tạo biến để đọc file xlsx
			sheet = workbook.getSheet("Sheet1");
//			System.out.println(sheet.getLastRowNum());
			for (int i = 68; i <= 69; i++) {
//				System.out.println(sheet.getRow(i).getCell(5) + "ccc" + i);
				String daymonth = sheet.getRow(i).getCell(4).toString();
				String [] arrayDay= daymonth.split(",");
				String day = arrayDay[0];
				String month  = arrayDay[1];
				String expect = sheet.getRow(i).getCell(6).toString();
				readBookActivity.add(new BookActivity(day, month, "", expect));
				}
			for (int i = 70; i <= 71; i++) {//chọn nguoi 
//				
				String people = sheet.getRow(i).getCell(4).toString();
				String expect = sheet.getRow(i).getCell(6).toString();
				boolean exp = expect.equalsIgnoreCase("Disable") ? false : true ;
				readBookActivity.add(new BookActivity("", "", people, expect)); //khởi tạo 1 đ	ối tượng UserLogin và thêm vào trong ds các đối tượng tài khoản người dùng
			}
			String people = sheet.getRow(72).getCell(4).toString();
			String expect = sheet.getRow(72).getCell(6).toString();
			boolean exp = expect.equalsIgnoreCase("Disable") ? false : true ;
			readBookActivity.add(new BookActivity("", "", people, expect));
					
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return readBookActivity;
	}
	
	
	public ArrayList<Rated> readRated() {//hàm đọc đánh giá
		// Import file excel
		File src = new File("/home/hien144/eclipse-workspace/test/test.xlsx");//đường dẫn trỏ đến file
		FileInputStream fis = null;//để đọc dữ liệu từ một tệp dưới dạng chuỗi byte
		
		try {
			fis = new FileInputStream(src);//khởi tạo biến FileInputStream
			workbook = new XSSFWorkbook(fis);//khởi tạo biến để đọc file xlsx
			sheet = workbook.getSheet("Sheet1");
//			System.out.println(sheet.getLastRowNum());
//			String nameHotel= sheet.getRow(i).getCell(4) != null ? sheet.getRow(i).getCell(4).toString() : "";
			String vote= "";
			String type = "";
			String rated= "";
			String heading= "";
			String expect ="";
	
			for (int i = 81; i <= 94; i++) {	
				if (i <= 86) {
					vote= sheet.getRow(i).getCell(4)!= null ? sheet.getRow(i).getCell(4).toString() : "";
//					System.out.println("firstname "+ i + " " + firstName);
				} else if (i <= 88) {
					type = sheet.getRow(i).getCell(4)!= null ? sheet.getRow(i).getCell(4).toString() : "";
//					System.out.println("firstname "+ i + " " + name);					
				}else if (i <= 91) {
					rated = sheet.getRow(i).getCell(4)!= null ? sheet.getRow(i).getCell(4).toString() : "";
//					System.out.println("firstname "+ i + " " + name);					
				} else if (i <= 94){
					heading= sheet.getRow(i).getCell(4)!= null ? sheet.getRow(i).getCell(4).toString() : "";
				}
				expect = sheet.getRow(i).getCell(6)!= null ? sheet.getRow(i).getCell(6).toString() : "";
				
				readRated.add(new Rated(vote, type, rated, heading, expect)); //khởi tạo 1 đối tượng UserLogin và thêm vào trong ds các đối tượng tài khoản người dùng
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return readRated;
	}
	
	public void write(int row, int col, String result) {
		FileOutputStream fos = null;
		File src = new File("/home/hien144/eclipse-workspace/test/test.xlsx");//đường dẫn trỏ đến file
		try {
			fos = new FileOutputStream(src);
//			workbook = new XSSFWorkbook();//khởi tạo biến để đọc file xlsx
//			sheet = workbook.getSheet("Sheet1");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Create cell where data needs to be written.
		// finally write content
		sheet.getRow(row).createCell(col).setCellValue(result);
		try {
			workbook.write(fos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
