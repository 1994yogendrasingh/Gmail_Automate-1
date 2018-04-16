package com.bdhillon.Pages;

import org.testng.SkipException;

import com.bdhillon.TestBase.TestBase;

public class Login extends TestBase{

	
	public void DoLogin(String Browser,String Username,String Password){
		
		selectBrowser(Browser);
		
		fluentWait("LoginEmail_tagName");

		getElement("LoginEmail_tagName").sendKeys(Username);
		
		getElement("nxtbtn_css").click();
		
		fluentWait("LoginPassword_css");
		waitinSec(3);
		
		getElement("LoginPassword_css").sendKeys(Password);
		
		waitinSec(2);
		
		getElement("SignBtn_css").click();
		
		fluentWait("waitingElement_xpath");
		
		TakeScreenshot();
		
		String title = getTitle();
		boolean cond = title.contains("Inbox");
		
		if(!cond){
			throw new SkipException("Login Failed ,Skipping this test cases.");
		}
				
	}
}
