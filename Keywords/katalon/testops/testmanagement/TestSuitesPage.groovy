package katalon.testops.testmanagement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class TestSuitesPage  extends BasePage<TestSuitesPage> {

	public TestSuitesPage clickTestSuitesTab() {
		WebUI.click(title('Test Suites'))
		return this
	}

	public TestSuitesPage verifyDirectoryViewVisible() {
		WebUI.verifyElementVisible(text('cloudian-automation-1'))
		return this
	}

	public TestSuitesPage verifySearchTestSuiteVisible() {
		WebUI.click(id('search-input'))
		return this
	}

	public TestSuitesPage verifyUploadedDataFolderVisible() {
		WebUI.verifyElementVisible(id('root_uploaded_data'))
		return this
	}

	public TestSuitesPage verifyTestSuiteButtonClickable() {
		WebUI.click(xpath('//*[(@class="add-btn")]'))
		return this
	}
}
