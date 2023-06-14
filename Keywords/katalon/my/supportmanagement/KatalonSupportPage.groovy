package katalon.my.supportmanagement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class KatalonSupportPage extends BasePage<KatalonSupportPage> {
	
	public KatalonSupportPage verifyUserLoggedIn(String email) {
		String first = email.split('@').first()
		String last = email.split('@').last()
		WebUI.verifyElementPresent(text(first), 5)
		WebUI.verifyElementPresent(text(last), 5)
		return this
	}
	
	public KatalonSupportPage clickSubmitACase() {
		WebUI.click(btn("Submit a Case"))
		return this
	}
	
	public KatalonSupportPage verifyUserCanSumitTicket() {
		WebUI.verifyElementPresent(id("submitButton"), 5)
		WebUI.verifyElementClickable(id("submitButton"))
		return this
	}
}
