package katalon.fw.lib

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class BasePage <T> extends BaseElement {
	WebDriver driver = DriverFactory.getWebDriver()

	protected WebDriver getDriver() {
		return driver;
	}

	protected T resetDriver() {
		driver = DriverFactory.getWebDriver()
	}

	protected T sleep(int seconds) {
		WebUI.delay(seconds)
		return this
	}

	/**
	 * Try to stop a little time
	 * Return page object class
	 */
	protected T sleepALittleTime() {
		WebUI.delay(GlobalVariable.smallSleepTime)
		return this
	}

	/**
	 * Try to stop a some time, it is longer than sleepALittleTIme
	 * Return page object class
	 */
	protected T sleepSomeTime() {
		WebUI.delay(GlobalVariable.sleepSomeTime)
		return this
	}

	/**
	 * Try to stop a some time, it is longer than sleepSomeTime 10s
	 * Return page object class
	 */
	protected T sleepTime() {
		WebUI.delay(GlobalVariable.sleepSomeTime+10)
	}
	
	private By getSeleniumByFrom(TestObject to) {
		TestObjectProperty property = to.activeProperties.first()

		switch (property.name) {
			case 'id':
				return By.id(property.value)
				
			case 'css':
				return By.cssSelector(property.value)

			default:
				return By.xpath(property.value)
		}
	}

	public T navigateToUrl(String url) {
		WebUI.navigateToUrl(url)
		return this
	}

	public T saveTestExecUrl() {
		GlobalVariable.testExecUrl = WebUI.getUrl()
		return this
	}

	protected T refreshPage() {
		WebUI.refresh()
		return this
	}

	protected T back() {
		WebUI.back()
		return this
	}

	protected clearTextAndSendKeys(TestObject to, String value) {
		WebUI.clearText(to)
		WebUI.sendKeys(to, value)
	}

	protected clearTextAndSendKeysByActions(TestObject to, String value) {
		resetDriver()
		WebElement el = driver.findElement(getSeleniumByFrom(to))
		new Actions(driver)
				.click(el)
				.sendKeys(Keys.END)
				.keyDown(Keys.SHIFT)
				.sendKeys(Keys.HOME)
				.keyUp(Keys.SHIFT)
				.sendKeys(Keys.BACK_SPACE)
				.sendKeys(value)
				.perform()
	}

	protected scrollToAndSendKeys(TestObject to, String value) {
		WebUI.scrollToElement(to, 0)
		WebUI.clearText(to)
		WebUI.sendKeys(to, value)
	}

	protected scrollToAndClick(TestObject to) {
		WebUI.waitForElementVisible(to, 10, FailureHandling.OPTIONAL)
		WebUI.scrollToElement(to, 10)
		WebUI.click(to)
	}

	protected boolean verifyElementNotVisible(TestObject to) {
		try {
			WebUI.verifyElementNotVisible(to)
		} catch (StepFailedException e) {
			return true
		}
		return false
	}

	protected boolean verifyTextPresent(String text, boolean isRegex = false) {
		return WebUI.verifyTextPresent(text, isRegex)
	}

	protected List<TestObject> findTestObjects(String locator){
		List<WebElement> els = driver.findElements(By.xpath(locator))
		List<TestObject> listTO = new ArrayList<>();
		for (WebElement element: els) {
			TestObject convertedTO = WebUI.convertWebElementToTestObject(element);
			listTO.add(convertedTO);
		}
		return listTO;
	}

	protected List<String> getTextElements(TestObject testObj, int waitingTime = 30) {
		List<String> results = new ArrayList<>()
		List<WebElement> elements = WebUI.findWebElements(testObj, waitingTime)
		for (WebElement element in elements) {
			results.add(element.getText())
		}
		return results
	}
	
	protected click(TestObject to) {
		WebUI.waitForElementPresent(to, 10, FailureHandling.OPTIONAL)
		WebUI.click(to,FailureHandling.CONTINUE_ON_FAILURE)
	}

	protected waitForElementDisplay(TestObject to) {
		WebUI.waitForElementPresent(to, 100, FailureHandling.STOP_ON_FAILURE)
	}

	public T switchTab(int tabIndex = 0) {
		WebUI.switchToWindowIndex(tabIndex)
		return this
	}

	public T closeTab(int tabIndex = 0) {
		WebUI.closeWindowIndex(tabIndex)
		return this
	}

	public T verifyCurrentURL(String expectedUrl) {
		def actualResult = WebUI.getUrl()
		WebUI.verifyEqual(actualResult, expectedUrl)
		return this
	}
	
	public T waitUntilElementVisibleWithWebDriverWait(TestObject to, long timeOut) {
		resetDriver()
		WebDriverWait wait = new WebDriverWait(driver, timeOut)
		wait.until(ExpectedConditions.visibilityOfElementLocated(getSeleniumByFrom(to)))
		return this
	}
	
	public T waitUntilElementInvisibleWithWebDriverWait(TestObject to, long timeOut) {
		resetDriver()
		WebDriverWait wait = new WebDriverWait(driver, timeOut)
		wait.until(ExpectedConditions.invisibilityOfElementLocated(getSeleniumByFrom(to)))
		return this
	}
	
	public T waitUntilElementClickableWithWebDriverWait(TestObject to, long timeOut) {
		resetDriver()
		WebDriverWait wait = new WebDriverWait(driver, timeOut)
		wait.until(ExpectedConditions.elementToBeClickable(getSeleniumByFrom(to)))
		return this
	}
	
	protected T verifyCurrentUrl(String expectedUrl) {
		String currentUrl = WebUI.getUrl()
		WebUI.verifyEqual(currentUrl, expectedUrl)
		return this
	}
	
	protected T switchToWindowTitle(String title) {
		WebUI.delay(1) // Avoid flaky test
		WebUI.switchToWindowTitle(title)
		return this
	}
	
	protected T switchToWindowIndex(Object windowIndex) {
		WebUI.switchToWindowIndex(windowIndex)
		return this
	}
	
	protected String getWindowTitle() {
		return WebUI.getWindowTitle()
	}
	
	protected T closeCurrentWindow() {
		Object currentWindowIndex = WebUI.getWindowIndex()
		WebUI.closeWindowIndex(currentWindowIndex)
		WebUI.delay(1) //Avoid flaky test
		return this
	}
	
	public T clickByJS(TestObject to) {
		resetDriver()
		WebElement el = driver.findElement(getSeleniumByFrom(to))
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", el)
		return this
	}
}
