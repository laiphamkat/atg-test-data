package katalon.common

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class SignUpPage extends BasePage<SignUpPage> {
	
	public SignUpPage verifyPageDisplayed() {
		WebUI.verifyElementPresent(text("Create a Katalon account"), 5)
		WebUI.verifyElementPresent(xpath("//form[@class='form_sign_up']"),5)
		return this
	}
	
}
