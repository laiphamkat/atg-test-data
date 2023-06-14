package katalon.testops.configurations

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class TestCloudEnvDetailsPage extends BasePage<TestCloudEnvDetailsPage> {
	TestObject agentDetailsHeader = xpath("//div[text()='Agent Details']")

	public TestCloudEnvDetailsPage verifyPageDisplayed() {
		WebUI.waitForElementVisible(agentDetailsHeader, 0)
		WebUI.verifyElementVisible(agentDetailsHeader)
		return this
	}

	public TestCloudEnvDetailsPage clickCancel(int sessionId) {
		WebUI.click(xpath("//tbody//tr[.//td[2][.='${sessionId}']]//button[@title='Cancel']"))
		return this
	}

	public TestCloudEnvDetailsPage verifySatusCanceled(int sessionId) {
		WebUI.verifyElementVisible(xpath("//tbody//tr[.//td[2][.='${sessionId}']]//td[1]//span[@title='Canceled']"))
		return this
	}
}
