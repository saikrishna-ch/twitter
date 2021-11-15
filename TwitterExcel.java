import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TwitterExcel {

	public static void main(String[] args) throws Exception {

		FileInputStream fis = new FileInputStream("C:\\Users\\SAI KRISHNA\\Desktop\\login.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("details");
		int rowcount = sheet.getLastRowNum();
		int columncount = sheet.getRow(1).getLastCellNum();
		// System.out.println(rowcount);
		// System.out.println(columncount);
		
		
		for (int i = 1; i <= rowcount; i++) {
			XSSFCell user = sheet.getRow(i).getCell(0);
			String User = user.getStringCellValue();
			System.out.println(User);
			// user.getCellType(Cell.CELL_TYPE_STRING);
			XSSFCell pass = sheet.getRow(i).getCell(1);
			String Pass = pass.getStringCellValue();
			System.out.println(Pass);
			XSSFCell comment = sheet.getRow(i).getCell(2);
			String Comment = comment.getStringCellValue();
			System.out.println(Comment);
			WebDriver driver = new ChromeDriver();
			System.setProperty("webdriver.chrome.driver", "C:\\\\chromedriver.exe");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get("https://twitter.com/");
			try {
				driver.findElement(By.xpath("//span[contains(text(),'Sign in')]")).click();
			} catch (Exception e) {
				driver.findElement(By.xpath("//span[text()='Log in']")).click();
			}

			driver.findElement(By.xpath("//span[contains(text(),'Use phone, email or username')]")).click();
			try {
				driver.findElement(By.xpath("//input[@name='username']")).sendKeys(User);
				driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();

				driver.findElement(By.xpath("//input[@name='password']")).sendKeys(Pass);

				driver.findElement(By.xpath("//span[contains(text(),'Log in')]")).click();
			} catch (Exception e) {

				driver.findElement(By.xpath("//input[@name='text']")).sendKeys("6305555233");
				driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
				driver.findElement(By.xpath("//input[@name='password']")).sendKeys(Pass);
				driver.findElement(By.xpath("//span[contains(text(),'Log in')]")).click();
			}
			// }

			driver.findElement(By.xpath("//div[@class='public-DraftStyleDefault-block public-DraftStyleDefault-ltr']"))
					.sendKeys(Comment);
			// driver.findElement(By.xpath("//div[@class='public-DraftStyleDefault-block
			// public-DraftStyleDefault-ltr']")).click();
			// driver.

			driver.findElement(By.xpath("//html")).click();
			driver.findElement(By.xpath("(//span[text()='Tweet'])[2]")).click();
			driver.findElement(By.xpath("//div[@aria-label='Account menu']")).click();
			// driver.findElement(By.xpath("//a[@href='/logout']")).click();
			// driver.findElement(By.xpath("//span[text()=\"Log out\"]")).click();
			// driver.findElement(By.xpath("//span[text()='Log out']")).click();
			driver.close();
//			
//			
		}
		

	}

}
