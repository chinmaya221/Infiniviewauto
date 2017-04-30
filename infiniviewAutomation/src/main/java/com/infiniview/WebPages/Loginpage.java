package com.infiniview.WebPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Page;

public class Loginpage extends Page
{
	@FindBy(id = "username")
	public static WebElement TEXTBOX_USERNAME;
	@FindBy(id = "password")
	public static WebElement TEXTBOX_PASSWORD;
	@FindBy(xpath = "//button[@type='submit']")
	public static WebElement BTN_SUBMIT;
	@FindBy(xpath = "//button[@ng-click='vm.logoutUser()']")
	public static WebElement BTN_LOGOUT;
	@FindBy(xpath = "//img[@src='images/logo.png']")
	public static WebElement HOME_PAGE_LOGO;

	public Loginpage() throws Exception {
		super();
		PageFactory.initElements(webdriver, this);
	}
	
	
	public void logon(String userName, String password) throws Exception {
		type(TEXTBOX_USERNAME, userName);
		type(TEXTBOX_PASSWORD, password);
		click(BTN_SUBMIT);
	}

	public void logout() throws Exception {
		click(HOME_PAGE_LOGO);
		Thread.sleep(1 * 10000);
		click(BTN_LOGOUT);
	}
	

}
