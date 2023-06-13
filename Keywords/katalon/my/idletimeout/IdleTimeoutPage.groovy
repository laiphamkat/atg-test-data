package katalon.my.idletimeout

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class IdleTimeoutPage extends BasePage<IdleTimeoutPage> {
	
	public IdleTimeoutPage verifyTitleIdleTimeoutPage() {
		WebUI.verifyElementPresent(title("Idle Timeout"), 5)
		return this
	}
	
	public IdleTimeoutPage verifyIdleTimeoutSection() {
		WebUI.verifyElementPresent(text("Idle Timeout (Katalon Studio Enterprise)"), 0)
		WebUI.verifyElementPresent(text("Enable Timeout"), 0)
		WebUI.verifyElementPresent(id("kse-timeout"), 0)
		return this
	}
}
