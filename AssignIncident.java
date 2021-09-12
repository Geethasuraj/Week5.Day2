package week5.day1assignment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class AssignIncident extends BaseClass {

	@Test(dataProvider = "sendData")
	public void assign(String Work_Notes) throws InterruptedException {
		driver.findElement(By.xpath("//div[text()='Open']")).click();
		driver.switchTo().frame("gsft_main");
		WebElement searchBox = driver.findElement(
				By.xpath("//span[contains(text(),'Press Enter from within the input to')]/following-sibling::input"));
		searchBox.sendKeys(incidentnumber, Keys.ENTER);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'INC')]")).click();
		driver.findElement(By.xpath("//button[@id='lookup.incident.assignment_group']")).click();
		Set<String> windowHandlesSet = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<String>(windowHandlesSet);
		driver.switchTo().window(windowHandlesList.get(1));
		// Assign the incident to Software group
		WebElement software = driver.findElementByXPath("(//input[@class='form-control'])[1]");
		software.sendKeys("Software");
		software.sendKeys(Keys.ENTER);
		driver.findElementByXPath("//a[text()='Software']").click();
		driver.switchTo().window(windowHandlesList.get(0));
		driver.switchTo().defaultContent();
		driver.switchTo().frame(0);
		Thread.sleep(2000);
		JavascriptExecutor scroll = (JavascriptExecutor) driver;
		scroll.executeScript("window.scrollBy(0,400)");
		// Update the incident with Work_Notes
		driver.findElementById("activity-stream-textarea").sendKeys(Work_Notes);
		// ClickKing on UPDATE
		driver.findElementByXPath("(//button[text()='Update'])[1]").click();

		WebElement Fortext1 = driver.findElementByXPath("//select[@class='form-control default-focus-outline']");
		Select drpdown = new Select(Fortext1);
		drpdown.selectByVisibleText("for text");
		Thread.sleep(2000);
		// Verify the Assignment group and Assigned for the incident
		driver.findElement(By.xpath("//a[contains(text(),'INC')]")).click();
		String assignedgrp = driver.findElement(By.xpath("//input[@id='sys_display.incident.assignment_group']"))
				.getAttribute("value");
		System.out.println(assignedgrp);
		if (assignedgrp.contains("Software")) {
			System.out.println("Incident assigned to Software Group");
		}

	}

	@DataProvider
	public String[][] sendData() throws IOException {

		return ReadExcel.readdata("AssignIncident");

	}

}
