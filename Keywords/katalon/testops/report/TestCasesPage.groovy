package katalon.testops.report

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class TestCasesPage extends BasePage<TestCasesPage>{

	public TestCasesPage clickCustomField() {
		WebUI.click(text('Custom Fields'))
		return this
	}

	public TestCasesPage clickTag() {
		WebUI.click(text('Tags'))
		return this
	}

	public TestCasesPage inputTag(String tag) {
		scrollToAndClick(id('tagEntity'))
		WebUI.sendKeys(id('tagEntity'), tag)
		WebUI.click(option(tag))
		return this
	}


	public TestCasesPage clickAddNewCustomField() {
		WebUI.click(xpath("//*[contains(text(),'Add new')]"))
		return this
	}

	public TestCasesPage inputCustomFieldDisplayName(String displayName) {
		WebUI.click(txt('Display Name'))
		WebUI.sendKeys(txt('Display Name'), displayName)
		WebUI.click(option(displayName))
		return this
	}

	public TestCasesPage inputCustomFieldDisplayValue(String value) {
		scrollToAndSendKeys(txt('Value'), value)
		WebUI.click(option(value))
		return this
	}

	public TestCasesPage clickAddCustomField() {
		WebUI.click(title('Add'))
		return this
	}

	public TestCasesPage clickUpdateBtn() {
		WebUI.click(xpath('//button[@type="submit"]'))
		return this
	}

	public TestCasesPage verifyTestCaseIsVisible(String testcase) {
		WebUI.verifyElementVisible(text(testcase))
		return this
	}
	
	//====================================FAST TEST==========================================
	public TestCasesPage verifyFilterLabelIsVisible(String filterName) {
		WebUI.verifyElementVisible(text(filterName))
		return this
	}
	
	public TestCasesPage verifySortDefaultFilterIsVisible(String defaultValue) {
		assert WebUI.getText(xpath("//div[contains(@class, 'MuiListItemText-root')]/span")) == defaultValue
		return this
	}
	
	public TestCasesPage verifyCopyButtonIsVisible() {
		WebUI.verifyElementVisible(title("Copy as REST API"))
		return this
	}

	public TestCasesPage verifySearchBoxIsVisible() {
		WebUI.verifyElementVisible(id("search-input"))
		return this
	}
	
	public TestCasesPage verifyTableColumnNameIsVisible(String columnName) {
		WebUI.verifyElementVisible(xpath("//table//th[text()='$columnName']"))
		return this
	}
	
	public Boolean isRowNumberMoreThanOrEqual30() {
		return (WebUI.findWebElements(xpath("//table//tbody//tr"), 30).size() >= 30)
	}
	
	public TestCasesPage verifyPaginationIsVisible() {
		if(isRowNumberMoreThanOrEqual30()) {
			WebUI.verifyElementVisible(cls("pagination"))
		}
		return this
	}
		
	public TestCasesPage verifyTotalTestCasesIsVisible() {
		if(isRowNumberMoreThanOrEqual30()) {
			WebUI.verifyElementVisible(cls("pagination-item-count"))
		}
		return this
	}

	public TestCasesPage verifyRefreshButtonIsEnable() {
		WebUI.verifyElementClickable(title("Refresh"))
		return this
	}
	
	public TestCasesPage verifyCollapseButtonIsClickable() {
		WebUI.verifyElementClickable(css(".btn-link"))
		return this
	}
	
	public TestCasesPage verifyTheTableTitleIsVisible(String title) {
		assert WebUI.getText(cls("card-header-title")) == title
		return this
	}
	
	public TestCasesPage verifyTheAlertIsVisible(String alert) {
		assert WebUI.getText(css(".alert")) == alert
		return this
	}
	
	public TestCasesPage verifyPassedFailedLabelIsVisible() {
		WebUI.verifyElementVisible(xpath("//div[contains(text(), 'Failed')]"))
		return this
	}
}
