package webdriver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_2_Xpath {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		driver = new FirefoxDriver();
		

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC01_Register_with_empty_data() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.xpath("//div[@class='field_btn']/button")).click();
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
	}

	@Test
	public void TC02_Register_Email_Invalid() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//input thông tin
		driver.findElement(By.id("txtFirstname")).sendKeys("Ngô Quốc Bảo");
		driver.findElement(By.id("txtEmail")).sendKeys("crz@#$");
		driver.findElement(By.id("txtCEmail")).sendKeys("crz@#$");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0935899397");
		//click button đăng ký
		driver.findElement(By.xpath("//div[@class='field_btn']/button")).click();
		//Verify
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
		}


	@Test
	public void TC03_Register_Password() {
	driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	//input thông tin
	driver.findElement(By.id("txtFirstname")).sendKeys("Ngô Quốc Bảo");
	driver.findElement(By.id("txtEmail")).sendKeys("crznqb@gmail.com");
	driver.findElement(By.id("txtCEmail")).sendKeys("crznqb@gmail.com");
	driver.findElement(By.id("txtPassword")).sendKeys("12345");
	driver.findElement(By.id("txtCPassword")).sendKeys("12345");
	driver.findElement(By.id("txtPhone")).sendKeys("0935899397");
	//click button đăng ký
	driver.findElement(By.xpath("//div[@class='field_btn']/button")).click();
	//Verify
	Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
	Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
	
}
	@Test
	public void TC04_Register_Incorrect_Confirm_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//input thông tin
		driver.findElement(By.id("txtFirstname")).sendKeys("Ngô Quốc Bảo");
		driver.findElement(By.id("txtEmail")).sendKeys("crznqb@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("crznqb@gmail.co");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0935899397");
		//click button đăng ký
		driver.findElement(By.xpath("//div[@class='field_btn']/button")).click();
		//Verify
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
	}
	@Test
	public void TC05_Register_Invalid_Phone() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//input thông tin
		driver.findElement(By.id("txtFirstname")).sendKeys("Ngô Quốc Bảo");
		driver.findElement(By.id("txtEmail")).sendKeys("crznqb@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("crznqb@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("093589");
		//click button đăng ký
		driver.findElement(By.xpath("//div[@class='field_btn']/button")).click();
		//Verify
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("093589939777");
		//Verify
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
