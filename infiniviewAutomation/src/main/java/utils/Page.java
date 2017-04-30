package utils;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverUtils;

public class Page {

	protected static WebDriver webdriver;
	protected WebDriverUtils driverUtils;

	public Page() throws Exception {
		webdriver = WebDriverUtils.getDriver();
		driverUtils = new WebDriverUtils();
	}

	public boolean isDisplayed(By by) {
		driverUtils
				.waitForElement(by, WebDriverUtils.ELEMENT_GENERAL_WAIT_TIME);
		return webdriver.findElement(by).isDisplayed();
	}

	public Page type(WebElement element, String value) {
		driverUtils.waitForElement(element,
				WebDriverUtils.ELEMENT_GENERAL_WAIT_TIME);
		element.click();
		element.clear();
		element.sendKeys(value);
		return this;
	}

	public Page clear(WebElement element) {
		driverUtils.waitForElement(element,
				WebDriverUtils.ELEMENT_GENERAL_WAIT_TIME);
		element.clear();
		return this;
	}

	public Page click(WebElement element) {
		return click(element, WebDriverUtils.ELEMENT_GENERAL_WAIT_TIME);
	}

	public Page click(WebElement element, int timeoutInSeconds) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// Try to sleep, but don't do anything if interrupted.
		}
		driverUtils.waitForElement(element, timeoutInSeconds);
		element.click();
		return this;
	}

	public Page submit(WebElement element) {
		driverUtils.waitForElement(element,
				WebDriverUtils.ELEMENT_GENERAL_WAIT_TIME);
		element.click();
		return this;
	}

	public Page assertElementPresent(WebElement element) {
		driverUtils.waitForElement(element,
				WebDriverUtils.ELEMENT_GENERAL_WAIT_TIME);
		return this;
	}

	public String getText(WebElement element) {
		driverUtils.waitForElement(element,
				WebDriverUtils.ELEMENT_GENERAL_WAIT_TIME);
		return element.getText();
	}

	public String getValue(WebElement element) {
		driverUtils.waitForElement(element,
				WebDriverUtils.ELEMENT_GENERAL_WAIT_TIME);
		return element.getText();
	}

	public Page select(WebElement element, String value) {
		driverUtils.waitForElement(element,
				WebDriverUtils.ELEMENT_GENERAL_WAIT_TIME);
		Select drpdwn = new Select(element);
		drpdwn.deselectAll();
		drpdwn.selectByVisibleText(value);
		return this;
	}

	public boolean isDropDownLabel(WebElement element, String label) {
		driverUtils.waitForElement(element,
				WebDriverUtils.ELEMENT_GENERAL_WAIT_TIME);
		Select drpdwn = new Select(element);
		List<WebElement> existingOptions = drpdwn.getOptions();
		for (WebElement option : existingOptions) {
			if (option.getText().equals(label)) {
				return true;
			}
		}
		return false;
	}

	public List<WebElement> getSelectOptions(WebElement element) {
		driverUtils.waitForElement(element,
				WebDriverUtils.ELEMENT_GENERAL_WAIT_TIME);
		Select drpdwn = new Select(element);
		List<WebElement> existingOptions = drpdwn.getOptions();
		return existingOptions;
	}

	public Page selectDropDownByValue(WebElement element, String value) {
		driverUtils.waitForElement(element,
				WebDriverUtils.ELEMENT_GENERAL_WAIT_TIME);
		Select drpdwn = new Select(element);
		drpdwn.deselectAll();
		drpdwn.selectByValue(value);
		return this;
	}

	public String getSelectedLabel(WebElement element) {
		driverUtils.waitForElement(element,
				WebDriverUtils.ELEMENT_GENERAL_WAIT_TIME);
		return element.getText();
	}

	public Page check(WebElement element) {
		driverUtils.waitForElement(element,
				WebDriverUtils.ELEMENT_GENERAL_WAIT_TIME);
		WebElement checkbox = element;
		if (!checkbox.isSelected()) {
			checkbox.click();
		}
		return this;
	}

	public boolean isChecked(WebElement element) {
		driverUtils.waitForElement(element,
				WebDriverUtils.ELEMENT_GENERAL_WAIT_TIME);
		return element.isSelected();
	}

	public boolean isRadioButtonChecked(WebElement element, String value) {
		driverUtils.waitForElement(element,
				WebDriverUtils.ELEMENT_GENERAL_WAIT_TIME);
		return element.isSelected();
	}

	public Page uncheck(WebElement element) {
		driverUtils.waitForElement(element,
				WebDriverUtils.ELEMENT_GENERAL_WAIT_TIME);
		WebElement checkbox = element;
		if (checkbox.isSelected()) {
			checkbox.click();
		}
		return this;
	}

	public Page clickLink(WebElement element) {
		element.click();
		return this;
	}

	public Page selectRadioButton(By by, String value) {
		driverUtils
				.waitForElement(by, WebDriverUtils.ELEMENT_GENERAL_WAIT_TIME);
		List<WebElement> radios = webdriver.findElements(by);
		for (WebElement radio : radios) {
			if (radio.getText().equalsIgnoreCase(value)) {
				radio.click();
				break;
			}
		}
		return this;
	}

	public void getConfirmation() {
		webdriver.switchTo().alert().accept();
	}

	public void checkAllCheckboxes() {
		List<WebElement> checkBoxList = webdriver.findElements(By
				.xpath("//input[@type='checkbox']"));
		for (WebElement checkBox : checkBoxList) {
			checkBox.click();
			if (isAlertPresent()) {
				webdriver.switchTo().alert().accept();
			}
		}
	}

	public boolean isAlertPresent() {
		boolean presentFlag = false;
		try {
			Alert alert = webdriver.switchTo().alert();
			presentFlag = true;
			alert.accept();
		} catch (NoAlertPresentException ex) {
			ex.printStackTrace();
		}
		return presentFlag;
	}

	public String clickOkOnAlert() {
		String alertMessage = null;
		try {
			Alert alert = webdriver.switchTo().alert();
			alertMessage = webdriver.switchTo().alert().getText();
			alert.accept();
		} catch (NoAlertPresentException ex) {
			ex.printStackTrace();
		}
		return alertMessage;
	}

	public void uncheckAllCheckboxes() {
		List<WebElement> checkBoxList = webdriver.findElements(By
				.xpath("//input[@type='checkbox' and @checked='checked']"));
		for (WebElement checkBox : checkBoxList) {
			checkBox.click();
			if (isAlertPresent()) {
				webdriver.switchTo().alert().accept();
			}
		}
	}

	public boolean isEditable(By by) {
		try {
			WebDriverWait wait = new WebDriverWait(webdriver, 5);
			wait.until(ExpectedConditions.elementToBeClickable(by));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void waitTillElementClickable(By by) {
		WebDriverWait waitTillBusyIndicatorGoesAway = new WebDriverWait(
				webdriver, 5);
		waitTillBusyIndicatorGoesAway.until(ExpectedConditions
				.invisibilityOfElementLocated(by));
		WebDriverWait waitTillElementClickable = new WebDriverWait(webdriver, 5);
		waitTillElementClickable.until(ExpectedConditions
				.elementToBeClickable(by));
	}
}
