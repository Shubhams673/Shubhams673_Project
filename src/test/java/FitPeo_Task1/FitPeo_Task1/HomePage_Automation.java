package FitPeo_Task1.FitPeo_Task1;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HomePage_Automation {


	/*
	 * Task Details:
		1. Navigate to the FitPeo Homepage:
			1.1 Open the web browser and navigate to FitPeo Homepage.

		2. From the homepage, navigate to the Revenue Calculator Page.
			2.1 Navigate to the Revenue Calculator Page:

		3. Scroll Down to the Slider section:
			3.1 Scroll down the page until the revenue calculator slider is visible.

		4. Adjust the Slider:
			4.1 Adjust the slider to set its value to 820. 

		5. Update the Text Field:
			5.1 Click on the text field associated with the slider.
			5.2 Enter the value 560 in the text field. Now the slider also should change accordingly 

		6. Validate Slider Value:
 			6.1 Ensure that when the value 560 is entered in the text field, the slider's position is updated to reflect the value 560.

 		7. Select CPT Codes:
			7.1 Scroll down further and select the checkboxes for CPT-99091, CPT-99453, CPT-99454, and CPT-99474.
			
		8. Validate Total Recurring Reimbursement:
			8.1 Verify that the header displaying Total Recurring Reimbursement for all Patients Per Month: shows the value $110700.	



	 */

	// Declaring the URl for FitPeo
	public String fitPeo_URL = "https://www.fitpeo.com/";
	public String fitPeo_Rev_Cal = "https://fitpeo.com/revenue-calculator";

	// Declaring the webdriver interface object
	public WebDriver driver; 



	@BeforeTest
	public void setUp() {


		System.out.println("Before Test - setUP execution started...!");

		// Initializing the Chrome driver
		driver = new ChromeDriver(); 

		// maximizing the window
		driver.manage().window().maximize();

		/*
		1. Navigate to the FitPeo Homepage:
			1.1 Open the web browser and navigate to FitPeo Homepage.
		 */
		driver.get(fitPeo_URL);

		/* 	
		2. From the homepage, navigate to the Revenue Calculator Page.
			2.1 Navigate to the Revenue Calculator Page:
		 */
		driver.navigate().to(fitPeo_Rev_Cal);



		// implicit wait for 30 seconds given to overall RUN
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		System.out.println("Before Test - setUP execution Successful...!");


	}



	@Test (priority = 1)
	public void Rev_Cal_Page() throws InterruptedException {

		/*
		3. Scroll Down to the Slider section:
			3.1 Scroll down the page until the revenue calculator slider is visible.
		 */
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Creating the variable for Medicare Eligible Patients
		WebElement mep = driver.findElement(By.xpath("//div[@class='MuiBox-root css-79elbk']"));

		//Scrolling the Revenue Calculator page upto the Slider
		Thread.sleep(2000);
		js.executeScript("arguments[0].scrollIntoView(true);", mep);



		/*	
		4. Adjust the Slider:
			4.1 Adjust the slider to set its value to 820.
		 */

		// Creating the WebElement variable of Slider
		WebElement slider = driver.findElement(By.xpath("//span[contains(@class,'MuiSlider-thumb')]"));

		// Creating the WebElement variable of Calculating total Slider Width
		WebElement sliderWidth = driver.findElement(By.xpath("//span[@class='MuiSlider-rail css-3ndvyc']"));

		// Width of slider printed
		System.out.println("Slider Width is ; " + sliderWidth.getSize());

		Thread.sleep(3000);

		Actions act = new Actions(driver);
		act.dragAndDropBy(slider, 93, 0).build().perform(); //Scroll upto 820 - x:Axis Coordinate of slider

		// Second Method to Drag and Drop the Slider upto the need
		//act.moveToElement(slider).clickAndHold().moveByOffset(93,0).release().build().perform();



		/*
		 5. Update the Text Field:
			5.1 Click on the text field associated with the slider.
			5.2 Enter the value 560 in the text field. Now the slider also should change accordingly
		 */

		// Creating the variable to store the Slider box to have click on it and provide the input required
		WebElement slider_Box = driver.findElement(By.xpath("//input[@type='number']"));
		//WebElement slider_Box = driver.findElement(By.xpath("//input[@id=':r0:']"));

		Thread.sleep(1500);

		// Clicking on the Slider box
		slider_Box.click();

		Thread.sleep(2000);

		// Clearing out the previous stored text in the input slider box
		slider_Box.sendKeys(Keys.BACK_SPACE);
		slider_Box.sendKeys(Keys.BACK_SPACE);
		slider_Box.sendKeys(Keys.BACK_SPACE);


		Thread.sleep(1500);

		// Providing the input to the slider box
		slider_Box.sendKeys("560");
		
		Thread.sleep(1500);
	}



	@Test(priority = 2)
	public void validation1() throws InterruptedException {

		/*
	6. Validate Slider Value:
	 	6.1 Ensure that when the value 560 is entered in the text field, the slider's position is updated to reflect the value 560.
		 */

		String slider_Value_Exp = "560";

		WebElement slider_Curr_Value = driver.findElement(By.xpath("//input[contains(@aria-valuenow,'560')]"));
		System.out.println("Slider's Current Value : " + slider_Curr_Value.getAttribute("aria-valuenow"));


		String slider_Value_Act = slider_Curr_Value.getAttribute("aria-valuenow");

		//Assert.assertEquals(slider_Value_Act, slider_Value_Exp);
		// OR

		if(slider_Value_Exp.equals(slider_Value_Act))

		{
			System.out.println("Validation1 is Pass ----- > Slider is at position = 560 ");
		}
		else {
			System.out.println("Validation Failed...!");
		}

	}



	@Test(priority = 3)
	public void validation2() throws InterruptedException {

		/*
		7. Select CPT Codes:
			7.1 Scroll down further and select the checkboxes for CPT-99091, CPT-99453, CPT-99454, and CPT-99474.
		 */
		// Using the JavascriptExecutor to scroll down further
		JavascriptExecutor js2 = (JavascriptExecutor) driver;

		// Creating the WebElement variable of Slider
		WebElement slider_Box2 = driver.findElement(By.xpath("//input[@type='number']"));

		// Clearing out the previous stored text in the input slider box
		Thread.sleep(1500);
		
		slider_Box2.sendKeys(Keys.BACK_SPACE);
		slider_Box2.sendKeys(Keys.BACK_SPACE);
		slider_Box2.sendKeys(Keys.BACK_SPACE);

		Thread.sleep(2000);

		// Need to Slide the Slider to value 820 for this validation.
		slider_Box2.sendKeys("820");		

		// scrolling down to selection of the Checkboxes
		Thread.sleep(2000);
		js2.executeScript("window.scrollBy(0, 500)");

		Thread.sleep(1500);

		
		
		
		/*
		8. Validate Total Recurring Reimbursement:
			8.1 Verify that the header displaying Total Recurring Reimbursement for all Patients Per Month: shows the value $110700.
		*/
	
		// Selecting the Checkboxes for CPT-99091, CPT-99453, CPT-99454, and CPT-99474.
		
		// All Checkboxes
		List<WebElement> all_CheckBoxes = driver.findElements(By.xpath("//input[@class='PrivateSwitchBase-input css-1m9pwf3']"));
		System.out.println("Total numbers of CheckBoxes are : " + all_CheckBoxes.size());
		
		WebElement chk1 = driver.findElement(By.xpath("(//input[@class='PrivateSwitchBase-input css-1m9pwf3'])[1]"));
		chk1.click();
		
		WebElement chk2 = driver.findElement(By.xpath("(//input[@class='PrivateSwitchBase-input css-1m9pwf3'])[2]"));
		chk2.click();

		WebElement chk3 = driver.findElement(By.xpath("(//input[@class='PrivateSwitchBase-input css-1m9pwf3'])[3]"));
		chk3.click();

		WebElement chk4 = driver.findElement(By.xpath("(//input[@class='PrivateSwitchBase-input css-1m9pwf3'])[8]"));
		chk4.click();
		
		System.out.println("CPT-99091 CheckBoxe Clicked : " + chk1.isSelected());
		
		System.out.println("CPT-99453 CheckBoxe Clicked : " + chk2.isSelected());

		System.out.println("CPT-99454 CheckBoxe Clicked : " + chk3.isSelected());

		System.out.println("CPT-99474 CheckBoxe Clicked : " + chk4.isSelected());
		
		WebElement trrpm = driver.findElement(By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 inter css-hocx5c'][normalize-space()='$110700']"));
		
		System.out.println("Total Reccuring Reimbursement Per Month = " + trrpm.getText());
		
		Assert.assertEquals(trrpm.getText(),"$110700");
		
		System.out.println("Validation  2 is pass.");
	}



	@AfterTest
	public void tearDown() throws InterruptedException {

		Thread.sleep(3000);

		System.out.println("After Test - tarDown execution Started...!");

		driver.close(); // this will close only the browser tab
		driver.quit(); // this will close whole the browser

		System.out.println("After Test - tearDown execution Successful...!");
	}

}

