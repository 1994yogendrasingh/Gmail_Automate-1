package testcases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.bdhillon.Pages.Login;
import com.bdhillon.Pages.NewEmail;
import com.bdhillon.TestBase.TestBase;

public class SendEmailTest extends TestBase{

	public static Assert asrt;
	
	
	@Test(dataProvider = "getData")
	public void sendNewMessage(Hashtable<String, String> data) {
	
		try {
			if (data.get("RunMode").equalsIgnoreCase("N")) {
				logger.info("Test Case is Skipped");
				throw new SkipException("Test Case Skipped");
			}
			
			logger = extent.createTest(" TC02_SendNewMessage " + data);
			
			Login doLogin=new Login();
			doLogin.DoLogin(data.get("Browser"), 
							data.get("Username"),
							data.get("Password"));
			
			NewEmail nEmail=new NewEmail();
			
			nEmail.sendEmail(data.get("Subject"),
							 data.get("SendTo"), 
							 data.get("MessageBody"));
			
			
		}catch(Exception e){
			e.printStackTrace();
			logger.info(" Exception Occured." );
			TakeScreenshot();
			logger.fail("Message Not Sent Successfully");
			asrt.fail("Exception Occured");
		}
		
		
	}
	
	
	
	
	@DataProvider
	public Object[][] getData() {
		Object[][] data = com.bdhillon.ReadExcel.DataUtil.getData(reader,
				"SendNewMessage");
		return data;
	}
	
	@AfterMethod
	public void quite() {

		if (extent != null) {
			logger.info(" Closing Driver ");
			extent.flush();
			driver.quit();

		}

	}

	
	@BeforeTest
	public void init() {

		if (prop == null) {
			TestBase tb = new TestBase();
			tb.init();
		}
	}
}
