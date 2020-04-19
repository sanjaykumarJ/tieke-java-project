package controller.chromedriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LinkedinConnect {
	public static void main(String[] args) {
		LinkedinConnect connect= new LinkedinConnect();
		connect.establishConnection();
	}

	void establishConnection() {
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", 
				 "/Users/sanjaybalajawahar/documents/chromedriver");
				 driver=new ChromeDriver();
	}
}
