package katalon.testops.testmanagement

import org.openqa.selenium.Keys

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class TestCasesPage extends BasePage<TestCasesPage> {
	// SEARCH
	public TestCasesPage openMultiSearchPopup() {
		WebUI.click(xpath('//input[@id="search-input"]/following-sibling::div[1]'))
		return this
	}

	public TestCasesPage searchTestCase (String testCaseName) {
		clearTextAndSendKeys(id('search-input'), testCaseName)
		WebUI.sendKeys(id('search-input'), Keys.chord(Keys.ENTER))
	}

	public TestCasesPage verifyTestCasesTabVisible() {
		WebUI.verifyElementVisible(title('Test Cases'))
		return this
	}

	public TestCasesPage verifyTestCaseIsVisible(String testcase) {
		WebUI.verifyElementVisible(title(testcase))
		return this
	}

	public TestCasesPage verifyTheTotalOfTestCase(String text) {
		WebUI.verifyElementText(xpath('//div[@class="pagination-item-count"]'), text)
		return this
	}

	// FOLDER
	public TestCasesPage clickUploadedDataFolder() {
		WebUI.click(id("root_uploaded_data"))
		return this
	}

	public TestCasesPage clickSubFolder(String folderName) {
		WebUI.click(text(folderName))
		return this
	}

	public TestCasesPage clickTestCaseName(String testCaseName) {
		WebUI.click(title(testCaseName))
	}

	public TestCasesPage verifySearchTestCaseFieldVisible() {
		WebUI.verifyElementVisible(id('outlined-adornment-weight'))
		return this
	}

	public TestCasesPage verifyUploadDataFolderVisible() {
		WebUI.verifyElementVisible(id('root_uploaded_data'))
		return this
	}

	public TestCasesPage verifyTestCaseDataTableVisible() {
		WebUI.verifyElementVisible(text('Name'))
		WebUI.verifyElementVisible(text('Description'))
		WebUI.verifyElementVisible(text('Created On'))
		WebUI.verifyElementVisible(text('Last Updated'))
		return this
	}

	public TestCasesPage verifyDirectoryViewVisible() {
		WebUI.verifyElementVisible(id('root_draft_folder'))
		WebUI.verifyElementVisible(id('root_uploaded_data'))
		WebUI.verifyElementVisible(text('cloudian-automation-1'))
		return this
	}

}