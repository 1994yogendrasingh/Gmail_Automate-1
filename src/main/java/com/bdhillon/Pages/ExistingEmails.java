package com.bdhillon.Pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;

import com.bdhillon.TestBase.TestBase;

public class ExistingEmails extends TestBase {

	public void setCategory(String Category) {

		String title = getTitle();

		if (!title.contains("Inbox")) {
			throw new SkipException("Not on the login Page");

		}

		if (Category.contains("Primary")) {
			getElement("EmailCategoryPrimary_css").click();
			waitinSec(2);
		} else if (Category.contains("Social")) {
			getElement("EmailCategorySocial_css").click();
			waitinSec(2);
		} else if (Category.contains("Promotion")) {
			getElement("EmailCategoryPromotions_css").click();
			waitinSec(2);
		}

	}

	public List<LinkedHashMap> getEmails() {

		List<WebElement> emails = new ArrayList<WebElement>();

		LinkedHashMap<String, String> dataforEmail = new LinkedHashMap<String, String>();

		List<String> Columns = new LinkedList<String>();

		Columns.add("");
		Columns.add("Checked ? ");
		Columns.add("Important ?");
		Columns.add("Email Sender: ");
		Columns.add("Email Subject: ");
		Columns.add("");
		Columns.add("Time Received On : ");

		emails = driver.findElements(By.cssSelector(".Cp tbody tr"));

		List<LinkedHashMap> Email = new LinkedList<LinkedHashMap>();

		for (int i = 1; i < emails.size(); i++) {

			WebElement e = emails.get(i);

			String cssPart1 = ".Cp tbody tr:nth-child(" + i + ") td";

			List<WebElement> RowData = new LinkedList<WebElement>();
			RowData = driver.findElements(By.cssSelector(cssPart1));

			for (int j = 1; j < RowData.size(); j++) {
				if (j == 1 || j == 5 || j == 6 || j == 7 || j == 8) {
					dataforEmail.put(Columns.get(j - 1), RowData.get(j - 1)
							.getText());
				} else if (j == 2) {

					dataforEmail.put(Columns.get(j - 1), RowData.get(j - 1)
							.getAttribute("aria-checked"));

				} else if (j == 3 || j == 4) {

					dataforEmail.put(Columns.get(j - 1), RowData.get(j - 1)
							.getAttribute("aria-label"));

				}

			}
			Email.add(dataforEmail);

		}
		return Email;

	}

}
