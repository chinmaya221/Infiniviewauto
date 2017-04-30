package com.infiniviewAutomation.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.infiniview.WebPages.Loginpage;
import com.relevantcodes.extentreports.LogStatus;

import utils.ExcelUtils;
import utils.TestBase;
import utils.WebDriverUtils;


/**
 * @author TEChinnmaya41
 * This is the test class for login page in Infiniview
 *
 */
public class LoginpageTest extends TestBase
{
	
	public WebDriverUtils wait=null;
	public String validUsername=null;
	public String validPassword=null;
	public String InvalidUsername=null;
	public String InvalidPassword=null;
	
	
	public WebElement element;
	
	/**
	 * @author TEChinnmaya41
	 * This test case is to verify the application url.
	 */
	
	@Test(priority=0)
	public void verifyLaunchedApplicationURL_TC_01() throws Exception
	{
		logger=report.startTest("verifyLaunchedApplicationURL_TC_01");
		logger.log(LogStatus.INFO,"Browser started");
		ExcelUtils.setExcelFile("LoginPage");
		try{
		Assert.assertEquals(webdriver.getCurrentUrl(),WebDriverUtils.INFINIVIEW_URL);
		System.out.println("Current launched URL -----"+webdriver.getCurrentUrl());
		logger.log(LogStatus.INFO,"application URL verified");
		ExcelUtils.setCellData("Pass", 1,4);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			ExcelUtils.setCellData("Fail", 1,4);
			logger.log(LogStatus.INFO,"launching application failed");
			System.out.println("Current launched URL -----"+webdriver.getCurrentUrl());
		}
		
	}
	
	/**
	 * @author TEChinnmaya41
	 * This test case is to login to the Infiniview application with valid credentials
	 */
	
	@Test(priority=1)
	public void testLoginPagewithValidcredentials_TC_02() throws Exception
	{
		logger=report.startTest("testLoginPagewithValidcredentials_TC_02");
		logger.log(LogStatus.INFO,"testLoginPagewithValidcredentials test case started");
		webdriver.navigate().refresh();
		ExcelUtils.setExcelFile("LoginPage");
		validUsername=ExcelUtils.getCellData(2,2);
		validPassword=ExcelUtils.getCellData(2,3);
		Loginpage login= new Loginpage();
		login.logon(validUsername, validPassword);
		logger.log(LogStatus.INFO,"logged in to application");
		WebDriverWait wait = new WebDriverWait(webdriver,30);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id='core-container']/div/div[1]/img")));
		
		if(webdriver.getTitle()== "InifiniView Login")
		{
		try{
		Assert.assertEquals(webdriver.getTitle(),"InifiniView");
		ExcelUtils.setCellData("Pass", 2,4);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		}else{
			ExcelUtils.setCellData("Fail", 2,4);
		}
		logger.log(LogStatus.INFO,"testLoginPagewithValidcredentials test case completed");
		
		logout();
		
		
	}
	
	//@Test(priority=02)
    public void testLoginPagewithBlanckcredentials_TC_03() throws Exception
	{
    	logger=report.startTest("testLoginPagewithBlanckcredentials_TC_03");
		webdriver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		logger.log(LogStatus.INFO,"");
		webdriver.navigate().refresh();
		ExcelUtils.setExcelFile("LoginPage");
		InvalidUsername=ExcelUtils.getCellData(3,2);
		InvalidPassword=ExcelUtils.getCellData(3,3);
		Loginpage login= new Loginpage();
		login.logon(InvalidUsername, InvalidPassword);
		WebDriverWait wait = new WebDriverWait(webdriver,30);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id='username']")));
		try{
		Assert.assertEquals(webdriver.getTitle(),"InifiniView Login");
		ExcelUtils.setCellData("Pass", 3,4);
		System.out.println("Failed to login to application");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			ExcelUtils.setCellData("Fail", 3,4);
			System.out.println("Successfully Login to the application");
		}
		
	}
    
    /**
     * @author TEChinnmaya41
     * This test case is to verify the login button functionality in Login page.
     * @throws Exception 
     */
    
    @Test(priority=3)
    public void testloginButtonFunctionalty_TC_04() throws Exception
    {
    	logger=report.startTest("testloginButtonFunctionalty_TC_04");
    	webdriver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		logger.log(LogStatus.INFO,"loggin button test case started");
		webdriver.navigate().refresh();
		
		ExcelUtils.setExcelFile("LoginPage");
		Loginpage login= new Loginpage();
		logger.log(LogStatus.INFO,"enter into username field");
		login.type(Loginpage.TEXTBOX_USERNAME,ExcelUtils.getCellData(4,2));
		boolean submitBtn=Loginpage.BTN_SUBMIT.isDisplayed();
		try{
		Assert.assertEquals(submitBtn,true);
		logger.log(LogStatus.INFO,"login button is not displaying");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		if(submitBtn=false)
		{
			ExcelUtils.setCellData("pass",4,4);
		}
		else
		{
			ExcelUtils.setCellData("Fail",4,4);
		}
		
		
    	logger.log(LogStatus.INFO,"Test case executed succesfully");
    }
}
