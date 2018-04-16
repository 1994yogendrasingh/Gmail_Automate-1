package com.bdhillon.Pages;

import org.testng.SkipException;

import com.bdhillon.TestBase.TestBase;

public class NewEmail extends TestBase{

	public void sendEmail(String Subject,String SendTo,String MessageBody){
		
		String title=getTitle();
		
		if(!title.contains("Inbox")){
			throw new SkipException("Not on the login Page");
				
		}
		
		getElement("Compose_xpath").click();;
		fluentWait("ToField_xpath");
		getElement("ToField_xpath").sendKeys(SendTo);
		getElement("Subject_xpath").sendKeys(Subject);
		getElement("MessageBody_css").sendKeys(MessageBody);
		TakeScreenshot();
		getElement("Sendbtn_xpath").click();
	
		fluentWait("ViewMessageLink_css");
		
		String text=getElement("ViewMessageLink_css").getText();
		logger.info("The Text : "+ text);
		
		asrt.assertEquals(true, text.contains("view"));
		
		
		
		
	}
	
}
