package katalon.testops.common

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage


public class ToastMessage extends BasePage<ToastMessage> {
	private TestObject errorMessage = css('.toast-error')

	public ToastMessage verifyErrorMessageDisplayed() {
		WebUI.waitForElementVisible(errorMessage, 5)
		WebUI.verifyElementVisible(errorMessage)
		return this
	}

	public ToastMessage verifyErrorMessageTitle(String expected) {
		WebUI.verifyElementText(css('.toast-error .toast-title'), expected)
		return this
	}

	public ToastMessage verifyErrorMessageContent(String expected) {
		WebUI.verifyElementText(css('.toast-error .toast-message'), expected)
		return this
	}

	public ToastMessage clickHereOnErrorMessage() {
		WebUI.click(css('.toast-error .toast-message a'))
		return this
	}
}
