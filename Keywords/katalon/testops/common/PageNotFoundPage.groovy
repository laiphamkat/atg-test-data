package katalon.testops.common

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class PageNotFoundPage extends BasePage<PageNotFoundPage> {
	TestObject message = xpath("//div[text()='PAGE NOT FOUND!']")
	
	public PageNotFoundPage verifyPageDisplayed() {
		WebUI.waitForElementVisible(message, 10)
		WebUI.verifyElementVisible(message)
		return this
	}
}
