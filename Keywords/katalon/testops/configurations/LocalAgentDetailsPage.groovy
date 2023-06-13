package katalon.testops.configurations

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class LocalAgentDetailsPage extends BasePage<LocalAgentDetailsPage> {
	TestObject agentDetailsHeader = xpath("//div[text()='Agent Details']")

	public LocalAgentDetailsPage verifyPageDisplayed() {
		WebUI.waitForElementVisible(agentDetailsHeader, 0)
		WebUI.verifyElementVisible(agentDetailsHeader)
		return this
	}
}
