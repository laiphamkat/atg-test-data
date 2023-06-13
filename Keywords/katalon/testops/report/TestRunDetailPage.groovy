package katalon.testops.report

import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.lib.BasePage
import katalon.testops.testexecution.NewUIScheduleTestRunPopUp
import katalon.testops.testexecution.TestRunListPage

public class TestRunDetailPage extends BasePage<TestRunDetailPage>{
	private def allSessionsStatus = xpath("//div[contains(@class, 'card') and contains(., 'Sessions')]//tr//td[1]/span[@title]")
	private def sessionPassed = { int rowIndex -> return xpath("//div[contains(@class, 'card') and contains(., 'Sessions')]//tr[${rowIndex}]//td[1]/span[@title='Success']") }
	private def sessionQueued = { int rowIndex -> return xpath("//div[contains(@class, 'card') and contains(., 'Sessions')]//tr[${rowIndex}]//td[1]/span[@title='Queued']") }
	private def sessionCanceled = { int rowIndex -> return xpath("//div[contains(@class, 'card') and contains(., 'Sessions')]//tr[${rowIndex}]//td[1]/span[@title='Canceled']") }
	private def sessionRunning = { int rowIndex -> return xpath("//div[contains(@class, 'card') and contains(., 'Sessions')]//tr[${rowIndex}]//td[1]/span[@title='Running']") }
	private def sessionQueuedOrRunning = { int rowIndex -> return xpath("//div[contains(@class, 'card') and contains(., 'Sessions')]//tr[${rowIndex}]//td[1]/span[@title='Queued' or @title='Running']") }
	private def allScriptRepos = xpath("//div[contains(@class, 'card') and contains(., 'Sessions')]//tr//td[3]//a")
	private def sessionScriptRepo = { int rowIndex -> return xpath("//div[contains(@class, 'card') and contains(., 'Sessions')]//tr[${rowIndex}]//td[3]//a") }
	private def allSessionTestEnvs = xpath("//div[contains(@class, 'card') and contains(., 'Sessions')]//tr//td[4]//a")
	private def sessionTestEnv = { int rowIndex -> return xpath("//div[contains(@class, 'card') and contains(., 'Sessions')]//tr[${rowIndex}]//td[4]//a") }
	private def sessionId = { int rowIndex -> return xpath("//div[contains(@class, 'card') and contains(., 'Sessions')]//tr[${rowIndex}]//td[2]//a") }
	private def tsStatusPassed = { rowIndex -> return xpath("//div[contains(@class, 'card') and .//div[text() = 'Test Suites']]//tbody//tr[${rowIndex}]//td[1]//span[title='Success']") }
	private def tsName = { int rowIndex -> return xpath("//div[contains(@class, 'card') and .//div[text() = 'Test Suites']]//tbody//tr[${rowIndex}]//td[2]//a") }
	private def tsProfile = { int rowIndex -> return xpath("//div[contains(@class, 'card') and .//div[text() = 'Test Suites']]//tbody//tr[${rowIndex}]//td[3]") }
	private def tsPlatformOS = { int rowIndex -> return xpath("//div[contains(@class, 'card') and .//div[text() = 'Test Suites']]//tbody//tr[${rowIndex}]//td[4]//span[@title][1]") }
	private def tsPlatformBrowser = { int rowIndex -> return xpath("//div[contains(@class, 'card') and .//div[text() = 'Test Suites']]//tbody//tr[${rowIndex}]//td[4]//span[@title][2]") }
	private def tsTotal = { int rowIndex -> return xpath("//div[contains(@class, 'card') and .//div[text() = 'Test Suites']]//tbody//tr[${rowIndex}]//td[6]") }
	private def tsPassed = { int rowIndex -> return xpath("//div[contains(@class, 'card') and .//div[text() = 'Test Suites']]//tbody//tr[${rowIndex}]//td[7]") }
	private def tsFailed = { int rowIndex -> return xpath("//div[contains(@class, 'card') and .//div[text() = 'Test Suites']]//tbody//tr[${rowIndex}]//td[8]") }
	private def tsError = { int rowIndex -> return xpath("//div[contains(@class, 'card') and .//div[text() = 'Test Suites']]//tbody//tr[${rowIndex}]//td[9]") }
	private def tsIncomplete = { int rowIndex -> return xpath("//div[contains(@class, 'card') and .//div[text() = 'Test Suites']]//tbody//tr[${rowIndex}]//td[10]") }
	private def tsSkipped = { int rowIndex -> return xpath("//div[contains(@class, 'card') and .//div[text() = 'Test Suites']]//tbody//tr[${rowIndex}]//td[11]") }
	private def allTSName = { int rowIndex -> return xpath("//div[contains(@class, 'card') and .//div[text() = 'Test Suites']]//tbody//tr//td[2]//a") }
	private def allTSProfile = { int rowIndex -> return xpath("//div[contains(@class, 'card') and .//div[text() = 'Test Suites']]//tbody//tr//td[3]") }
	private def allTSPlatformOS = { int rowIndex -> return xpath("//div[contains(@class, 'card') and .//div[text() = 'Test Suites']]//tbody//tr//td[4]//span[@title][1]") }
	private def allTSPlatformBrowser = { int rowIndex -> return xpath("//div[contains(@class, 'card') and .//div[text() = 'Test Suites']]//tbody//tr//td[4]//span[@title][2]") }
	private def allTSTotal = { int rowIndex -> return xpath("//div[contains(@class, 'card') and .//div[text() = 'Test Suites']]//tbody//tr//td[6]") }
	private def allTSPassed = { int rowIndex -> return xpath("//div[contains(@class, 'card') and .//div[text() = 'Test Suites']]//tbody//tr//td[7]") }
	private def allTSFailed = { int rowIndex -> return xpath("//div[contains(@class, 'card') and .//div[text() = 'Test Suites']]//tbody//tr//td[8]") }
	private def allTSError = { int rowIndex -> return xpath("//div[contains(@class, 'card') and .//div[text() = 'Test Suites']]//tbody//tr//td[9]") }
	private def allTSIncomplete = { int rowIndex -> return xpath("//div[contains(@class, 'card') and .//div[text() = 'Test Suites']]//tbody//tr//td[10]") }
	private def allTSSkipped = { int rowIndex -> return xpath("//div[contains(@class, 'card') and .//div[text() = 'Test Suites']]//tbody//tr//td[11]") }
	private TestObject allFileNames = css('tbody a.d-block')
	private def fileNameWPartial = { String partialFileName -> return xpath("//tbody//td[1]//a[contains(text(), '${partialFileName}')]") }
	private TestObject filesFilter = id('search-input')
	
	public TestRunDetailPage verifyPageDisplayed() {
		TestObject sessionTitle = xpath("//div[@class='card-header-title' and text()='Sessions']")
		WebUI.waitForElementVisible(sessionTitle, 10)
		WebUI.verifyElementVisible(sessionTitle)
		return this
	}
	
	public TestRunDetailPage clickFilesTab() {
		WebUI.click(css('.page-tab__nav__nav-link:nth-child(3)'))
		return this
	}
	
	public TestRunDetailPage verifyFilesExist(List<String> expectedFiles) {
		List<WebElement> filesElements = WebUI.findWebElements(allFileNames, 10)
		List<String> actualFiles = filesElements.collect { it.getText() }
		WebUI.verifyEqual(actualFiles.containsAll(expectedFiles), true)
		return this
	}
	
	public TestRunDetailPage filterFile(String fileName) {
		WebUI.setText(filesFilter, fileName)
		WebUI.sendKeys(filesFilter, Keys.chord(Keys.ENTER))
		return this
	}
	
	public TestRunDetailPage clearFilter() {
		clearTextAndSendKeysByActions(filesFilter, Keys.chord(Keys.ENTER))
		return this
	}
	
	public TestRunDetailPage clickFileWithPartialName(String partialName) {
		WebUI.click(fileNameWPartial(partialName))
		return this
	}

	public TestRunDetailPage clickTagsInputField() {
		WebUI.click(id("tagEntity"))
		return this
	}

	// Custom Fields
	public TestRunDetailPage clickAddNewCustomField() {
		scrollToAndClick(text('Add new'))
		return this
	}

	public TestRunDetailPage inputCustomFieldDisplayName(String displayName) {
		WebUI.click(txt('Display Name'))
		WebUI.sendKeys(txt('Display Name'), displayName)
		return this
	}

	public TestRunDetailPage selectCustomFieldDisplayName(String displayName) {
		inputCustomFieldDisplayName(displayName)
		WebUI.click(option(displayName))
		return this
	}

	public TestRunDetailPage selectCustomFieldDisplayValue(String value) {
		scrollToAndSendKeys(txt('Value'), value)
		WebUI.click(xpath("(//*[contains(text(),'${value.substring(1,13)}')]/ancestor::li[@role='option'])[1]"))
		return this
	}

	public TestRunDetailPage clickAssignCustomField() {
		WebUI.click(title('Add'))
		return this
	}

	public TestRunDetailPage clickCancelAddCustomField() {
		WebUI.click(title('Cancel'))
		return this
	}

	public TestRunDetailPage verifyCustomFieldDisplay(def key, def value, int position = 1) {
		WebUI.verifyElementText(xpath("(//div[contains(@class, 'custom-field-filter')]//div[contains(@class, 'chip-group')][${position}]//span)[1]"), key)
		WebUI.verifyElementText(xpath("(//div[contains(@class, 'custom-field-filter')]//div[contains(@class, 'chip-group')][${position}]//span)[2]"), value)
		return this
	}

	public TestRunDetailPage clickUnassignCustomField(String value) {
		WebUI.click(svg(value))
	}

	public TestRunDetailPage verifyDisplayNameNotPresent(String displayName) {
		WebUI.verifyElementNotPresent(xpath("(//*[@title='${displayName}']/ancestor::li[@role='option'])[1]"), GlobalVariable.smallSleepTime)
		return this
	}

	public TestRunDetailPage verifyCustomFieldValueFieldIsDisabled() {
		WebUI.verifyElementHasAttribute(txt('Value'), 'disabled', GlobalVariable.smallSleepTime)
		return this
	}

	public TestRunDetailPage verifyAssignCustomFieldButtonIsDisabled() {
		WebUI.verifyElementHasAttribute(title('Add'), 'disabled', GlobalVariable.smallSleepTime)
		return this
	}

	public TestRunDetailPage verifyCustomFieldInfoIsEmpty() {
		WebUI.verifyElementNotPresent(xpath("(//span[contains(@class, 'custom-field')])[1]"), GlobalVariable.smallSleepTime)
		return this
	}

	//TAGS
	public TestRunDetailPage createNewTag(String nameOfTag) {
		WebUI.sendKeys(id("tagEntity"), nameOfTag)
		return this
	}

	public TestRunDetailPage searchExistingTagAndAssign(String nameOfTag) {
		WebUI.setText(id("tagEntity"), nameOfTag)
		scrollToAndClick(title(nameOfTag))
		return this
	}

	public TestRunDetailPage verifyCreateNewTag(String nameOfTag) {
		WebUI.verifyElementVisible(title(nameOfTag + ' (new tag)'))
		return this
	}

	public TestRunDetailPage assignTag(String nameOfTag) {
		WebUI.sendKeys(id("tagEntity"), Keys.chord(Keys.ENTER))
		return this
	}

	public TestRunDetailPage unassignTag(String nameOfTag) {
		WebUI.click(xpath("(.//*[normalize-space(text()) and normalize-space(.)='${nameOfTag}'])[1]/following::*[name()='svg'][1]"))
		return this
	}

	public TestRunDetailPage verifyTagIsVisible(String nameOfTag) {
		WebUI.verifyElementText(text(nameOfTag), nameOfTag)
		return this
	}

	public TestRunDetailPage verifyTagNotPresent(String tag) {
		WebUI.verifyElementNotPresent(xpath("(//*[@title='${tag}']/ancestor::li[@role='option'])[1]"), GlobalVariable.smallSleepTime)
		return this
	}

	public TestRunDetailPage verifyNumberOfSessions(int expected) {
		List<WebElement> elements = WebUI.findWebElements(allSessionsStatus, 10)
		WebUI.verifyEqual(elements.size(), expected)
		return this
	}

	public TestRunDetailPage verifySessionPassed(int rowIndex) {
		WebUI.verifyElementVisible(sessionPassed(rowIndex))
		return this
	}

	public TestRunDetailPage verifySessionQueued(int rowIndex) {
		WebUI.verifyElementVisible(sessionQueued(rowIndex))
		return this
	}

	public TestRunDetailPage verifyAllSessionsQueuedOrRunning() {
		List<WebElement> elements = WebUI.findWebElements(allSessionsStatus, 10)
		List<String> statuses = elements.collect { it.getAttribute('title') }
		statuses.each { WebUI.verifyEqual(it == 'Queued' || it == 'Running', true) }
		return this
	}

	public TestRunDetailPage verifySessionQueuedOrRunning(int rowIndex) {
		WebUI.verifyElementVisible(sessionQueuedOrRunning(rowIndex))
		return this
	}

	public TestRunDetailPage waitUntilSessionRunning(int rowIndex, int timeOut) {
		WebUI.waitForElementVisible(sessionRunning(rowIndex), timeOut)
		return this
	}

	public TestRunDetailPage verifySessionRunning(int rowIndex) {
		WebUI.verifyElementVisible(sessionRunning(rowIndex))
		return this
	}

	public TestRunDetailPage verifySessionCanceled(int rowIndex) {
		WebUI.verifyElementVisible(sessionCanceled(rowIndex))
		return this
	}

	public TestRunDetailPage verifyAllSessionsScriptRepo(String expecteValue) {
		List<WebElement> elements = WebUI.findWebElements(allScriptRepos, 10)
		elements.each { WebUI.verifyEqual(it.getText(), expecteValue) }
		return this
	}

	public TestRunDetailPage verifySessionScriptRepo(int rowIndex, String expecteValue) {
		WebUI.verifyElementVisible(sessionScriptRepo(rowIndex))
		WebUI.verifyElementText(sessionScriptRepo(rowIndex), expecteValue)
		return this
	}

	public TestRunDetailPage verifySessionTestEnv(int rowIndex, String expecteValue) {
		WebUI.verifyElementVisible(sessionTestEnv(rowIndex))
		WebUI.verifyElementText(sessionTestEnv(rowIndex), expecteValue)
		return this
	}

	/**
	 * Verify the test environment column of all the sessions executed with TestCloud.
	 * For example, you input 'Windows', 'Chrome', '101, 102, 103'
	 * It will verify the actual matches the expected:
	 * TestCloud Windows Chrome 101
	 * TestCloud Windows Chrome 102
	 * TestCloud Windows Chrome 103
	 * @param rowIndex the index of the test run you want to verify
	 * @param platform Windows/Linux/MacOS
	 * @param browser Chrome/Firefox/Safari/etc.
	 * @param browserVersions 101/102/103/etc.
	 * @return
	 */
	public TestRunDetailPage verifySessionsTCTestEnv(String platform, String browser, List<String> browserVersions) {
		browser = browser.replace('(', '').replace(')', '') // In case of headless, we need to remove '(' and ')'
		List<String> expected = browserVersions.collect { "TestCloud ${platform} ${browser} ${it}" }
		List<WebElement> elements = WebUI.findWebElements(allSessionTestEnvs, 10)
		List<String> actual = elements.collect { it.getText() }
		WebUI.verifyEqual(actual.sort(), expected.sort())
		return this
	}

	public TestRunDetailPage clickSessionTestEnv(int rowIndex) {
		WebUI.click(sessionTestEnv(rowIndex))
		return this
	}

	public TestRunDetailPage clickSessionId(int rowIndex) {
		WebUI.click(sessionId(rowIndex))
		return this
	}

	public TestRunDetailPage getSessionId(int rowIndex) {
		return WebUI.getText(sessionId(rowIndex))
	}

	public TestRunDetailPage verifyTSStatusPassed(int rowIndex) {
		WebUI.verifyElementVisible(tsStatusPassed(rowIndex))
		return this
	}

	public TestRunDetailPage verifyTSName(int rowIndex, String expecteValue) {
		WebUI.verifyElementVisible(tsName(rowIndex))
		WebUI.verifyElementText(tsName(rowIndex), expecteValue)
		return this
	}

	public TestRunDetailPage verifyTSProfile(int rowIndex, String expecteValue) {
		WebUI.verifyElementVisible(tsProfile(rowIndex))
		WebUI.verifyElementText(tsProfile(rowIndex), expecteValue)
		return this
	}

	public TestRunDetailPage verifyTSPlatformOS(int rowIndex, String expecteValue) {
		WebUI.verifyElementVisible(tsPlatformOS(rowIndex))
		WebUI.verifyElementAttributeValue(tsPlatformOS(rowIndex), 'title', expecteValue, 5)
		return this
	}

	public TestRunDetailPage verifyTSPlatformBrowser(int rowIndex, String expecteValue) {
		WebUI.verifyElementVisible(tsPlatformBrowser(rowIndex))
		WebUI.verifyElementAttributeValue(tsPlatformBrowser(rowIndex), 'title', expecteValue, 5)
		return this
	}

	public TestRunDetailPage verifyTSTotal(int rowIndex, String expecteValue) {
		WebUI.verifyElementVisible(tsTotal(rowIndex))
		WebUI.verifyElementText(tsTotal(rowIndex), expecteValue)
		return this
	}

	public TestRunDetailPage verifyTSPassed(int rowIndex, String expecteValue) {
		WebUI.verifyElementVisible(tsPassed(rowIndex))
		WebUI.verifyElementText(tsPassed(rowIndex), expecteValue)
		return this
	}

	public TestRunDetailPage verifyTSFailed(int rowIndex, String expecteValue) {
		WebUI.verifyElementVisible(tsFailed(rowIndex))
		WebUI.verifyElementText(tsFailed(rowIndex), expecteValue)
		return this
	}

	public TestRunDetailPage verifyTSError(int rowIndex, String expecteValue) {
		WebUI.verifyElementVisible(tsError(rowIndex))
		WebUI.verifyElementText(tsError(rowIndex), expecteValue)
		return this
	}

	public TestRunDetailPage verifyTSIncomplete(int rowIndex, String expecteValue) {
		WebUI.verifyElementVisible(tsIncomplete(rowIndex))
		WebUI.verifyElementText(tsIncomplete(rowIndex), expecteValue)
		return this
	}

	public TestRunDetailPage verifyTSSkipped(int rowIndex, String expecteValue) {
		WebUI.verifyElementVisible(tsSkipped(rowIndex))
		WebUI.verifyElementText(tsSkipped(rowIndex), expecteValue)
		return this
	}

	public TestRunDetailPage verifyTestSuitesInformation(List<Map> expectedTSList) {
		List<WebElement> names = WebUI.findWebElements(allTSName, 10)
		List<WebElement> profiles = WebUI.findWebElements(allTSProfile, 10)
		List<WebElement> os = WebUI.findWebElements(allTSPlatformOS, 10)
		List<WebElement> browsers = WebUI.findWebElements(allTSPlatformBrowser, 10)
		List<WebElement> total = WebUI.findWebElements(allTSTotal, 10)
		List<WebElement> passed = WebUI.findWebElements(allTSPassed, 10)
		List<WebElement> failed = WebUI.findWebElements(allTSFailed, 10)
		List<WebElement> error = WebUI.findWebElements(allTSError, 10)
		List<WebElement> incomplete = WebUI.findWebElements(allTSIncomplete, 10)
		List<WebElement> skipped = WebUI.findWebElements(allTSSkipped, 10)
		List<String> actualTSList = []

		// Verify number of test suites displayed should be equal to the expected list
		WebUI.verifyEqual(names.size(), expectedTSList.size())

		for (int i = 0; i < names.size(); i++) {
			actualTSList.add([
				'TSName': names.get(i).getText(),
				'Profile': profiles.get(i).getText(),
				'OS': os.get(i).getAttribute('title'),
				'Browser': browsers.get(i).getAttribute('title'),
				'Total': total.get(i).getText(),
				'Passed': passed.get(i).getText(),
				'Failed': failed.get(i).getText(),
				'Error': error.get(i).getText(),
				'Incomplete': incomplete.get(i).getText(),
				'Skipped': skipped.get(i).getText()
			])
		}

		actualTSList.sort { m1, m2 -> m1['TSName'] <=> m2['TSName'] }
		expectedTSList.sort { m1, m2 -> m1['TSName'] <=> m2['TSName'] }

		// Verify the information of the test suites should be equal to the expected list
		WebUI.verifyEqual(actualTSList, expectedTSList)
		return this
	}
}
