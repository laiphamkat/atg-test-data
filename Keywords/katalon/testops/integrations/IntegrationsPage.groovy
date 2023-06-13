package katalon.testops.integrations

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.lib.BasePage

public class IntegrationsPage extends BasePage<IntegrationsPage> {

	public IntegrationsPage enterGitCredential(String username, String accessToken) {
		scrollToAndSendKeys(id('username'), username)
		scrollToAndSendKeys(id('accessToken'), accessToken)
		return this
	}

	public IntegrationsPage clickConnect() {
		WebUI.click(text('Connect'))
		return this
	}

	public IntegrationsPage verifySuccessMessageIsDisplayed(String successMessage) {
		String msg = WebUI.getText(cls("toast-message"))
		WebUI.verifyEqual(msg.contains(successMessage), true)
		return this
	}

	public IntegrationsPage verifyUsernameIsDisplayed(String username) {
		WebUI.verifyElementPresent(id('username'), GlobalVariable.smallSleepTime)
		return this
	}

	public IntegrationsPage verifyAccessTokenIsDisplayed(String accessToken) {
		WebUI.verifyElementPresent(id('accessToken'), GlobalVariable.smallSleepTime)
		return this
	}
}