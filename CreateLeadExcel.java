package week5.day1assignment;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateLeadExcel extends BaseLead {
	
		@Test(dataProvider="getdata")
		public  void runMergeLead(String fname1,String lastname,String company,String phone)  throws InterruptedException {
			
			driver.findElement(By.linkText("Create Lead")).click();
			
			driver.findElement(By.id("createLeadForm_firstName")).sendKeys(fname1);
			driver.findElement(By.id("createLeadForm_lastName")).sendKeys(lastname);
			driver.findElement(By.id("createLeadForm_companyName")).sendKeys(company);
			driver.findElement(By.id("createLeadForm_primaryPhoneNumber")).sendKeys(phone);
			driver.findElement(By.name("submitButton")).click();
			
	}
		@DataProvider
	public String[][] getdata() throws IOException{
			
			return ReadExcel.readdata("CreateLead");
			
		}

}
