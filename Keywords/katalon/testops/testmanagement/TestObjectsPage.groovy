package katalon.testops.testmanagement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class TestObjectsPage  extends BasePage<TestObjectsPage> {

	public TestObjectsPage clickTestObjectsTab() {
		WebUI.click(title('Test Objects'))
		return this
	}

	public TestObjectsPage verifyTestObjectsCardVisible() {
		WebUI.verifyElementVisible(xpath('//*[(@class="card-header-title")]'))
		WebUI.verifyElementVisible(text('This Week'))
		return this
	}

	public TestObjectsPage verifyTestObjectTableVisible() {
		WebUI.click(text('ID'))
		WebUI.click(text('Name'))
		return this
	}
}
