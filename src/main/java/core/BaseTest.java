package core;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
 
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class BaseTest {
	
	
	
	@BeforeSuite
	public void setUp() throws InvalidFormatException, FileNotFoundException, IOException {
		System.out.println("clear result collunm");
		clearResultTest();
	}
	
	@AfterSuite
	public void tearDownSuite() {
		System.out.println("send resulst to mail");
		String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = "doanthuyhiencv144@gmail.com";
        String password = "uyklmmilpkfirjur";
 
        // message info
        String mailTo = "doanthuyhien144@gmail.com";
        String subject = "Report CI APPIUM";
        String message = "File test report";
 
        // attachments
        String[] attachFiles = new String[1];
        attachFiles[0] = System.getProperty("user.dir")+ "/test.xlsx";
       
 
        try {
            sendEmailWithAttachments(host, port, mailFrom, password, mailTo,
                subject, message, attachFiles);
            System.out.println("Email sent success");
        } catch (Exception ex) {
            System.out.println("Could not send email.");
            ex.printStackTrace();
        }
	}
	
	
	public static void clearResultTest() throws IOException, InvalidFormatException {
        String xlsxPath = System.getProperty("user.dir")+ "/test.xlsx";
        File src = new File(xlsxPath);//đường dẫn trỏ đến file
		FileInputStream fis = null;//để đọc dữ liệu từ một tệp dưới dạng chuỗi byte
    	fis = new FileInputStream(src);//khởi tạo biến FileInputStream
    	Workbook wb = new XSSFWorkbook(fis);//khởi tạo biến để đọc file xls
    

        int allRow = wb.getSheetAt(0).getLastRowNum();
        System.out.println("allRow = " + allRow);
        for (int i = 1; i < allRow -1; i++) {
            Sheet sheet = wb.getSheet("Sheet1");
            Row row = sheet.getRow(i);
            try {
                Cell cell = row.getCell(7);
                if (cell != null) {
                    cell.setCellValue("");
                }
            } catch (NullPointerException ignored) {

            }

            try (FileOutputStream fos = new FileOutputStream(xlsxPath)) {
                wb.write(fos);
            }

        }

        FileOutputStream fileOut = new FileOutputStream(xlsxPath);
        wb.write(fileOut);
        fileOut.close();
    }
	
	 public static void sendEmailWithAttachments(String host, String port,
	            final String userName, final String password, String toAddress,
	            String subject, String message, String[] attachFiles)
	            throws AddressException, MessagingException {
	        // sets SMTP server properties
	        Properties properties = new Properties();
	        properties.put("mail.smtp.host", host);
	        properties.put("mail.smtp.port", port);
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
	        properties.put("mail.user", userName);
	        properties.put("mail.password", password);
	 
	        // creates a new session with an authenticator
	        Authenticator auth = new Authenticator() {
	            public PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(userName, password);
	            }
	        };
	        Session session = Session.getInstance(properties, auth);
	 
	        // creates a new e-mail message
	        Message msg = new MimeMessage(session);
	 
	        msg.setFrom(new InternetAddress(userName));
	        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
	        msg.setRecipients(Message.RecipientType.TO, toAddresses);
	        msg.setSubject(subject);
	        msg.setSentDate(new Date());
	 
	        // creates message part
	        MimeBodyPart messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setContent(message, "text/html");
	 
	        // creates multi-part
	        Multipart multipart = new MimeMultipart();
	        multipart.addBodyPart(messageBodyPart);
	 
	        // adds attachments
	        if (attachFiles != null && attachFiles.length > 0) {
	            for (String filePath : attachFiles) {
	                MimeBodyPart attachPart = new MimeBodyPart();
	 
	                try {
	                    attachPart.attachFile(filePath);
	                } catch (IOException ex) {
	                    ex.printStackTrace();
	                }
	 
	                multipart.addBodyPart(attachPart);
	            }
	        }
	 
	        // sets the multi-part as e-mail's content
	        msg.setContent(multipart);
	 
	        // sends the e-mail
	        Transport.send(msg);
	 
	    }
	 

}
