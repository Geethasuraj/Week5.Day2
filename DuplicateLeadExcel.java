package week5.day1assignment;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DuplicateLeadExcel extends BaseLead {
	@Test(dataProvider="getdata")
	public  void runDuplicateLead(String email) throws InterruptedException {
		driver.findElementByLinkText("Leads").click();
		driver.findElementByLinkText("Find Leads").click();
		driver.findElementByLinkText("Email").click();
		driver.findElement(By.name("emailAddress")).sendKeys(email);
		driver.findElementByXPath("(//button[@class='x-btn-text'])[7]").click();
		Thread.sleep(20000);
		WebElement Name = driver.findElementByXPath("(//div[@class='x-grid3-cell-inner x-grid3-col-firstName'])/a");
		String text1 = Name.getText();
		Name.click();
		driver.findElementByXPath("(//div[@class='frameSectionExtra'])[2]/a[1]").click();
		System.out.println("Title is:" + driver.getTitle());
		String text2 = driver.findElement(By.id("createLeadForm_firstName")).getAttribute("value");
		driver.findElementByClassName("smallSubmit").click();
		if (text1.equals(text2)) {
			System.out.println("The lead is duplicate");
		}
		
	}

	@DataProvider	
	public String[][] getdata() throws IOException{
		
		return ReadExcel.readdata("DuplicateLead");
}

}
