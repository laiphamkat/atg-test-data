package katalon.testops.testexecution

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class SessionDetailsPage extends BasePage<SessionDetailsPage> {
	TestObject debugLogContent = css('.sidebar-layout__main-content .card-body pre')
	
	public SessionDetailsPage verifyGenericCommandDisplayed(String command) {
		WebUI.verifyElementText(id('genericCommand'), command)
		return this
	}
	
	public SessionDetailsPage verifyLogContains(String expectedText) {
		String log = WebUI.getText(debugLogContent)
		WebUI.verifyEqual(log.contains(expectedText), true)
		return this
	} 
	
	public SessionDetailsPage verifyWorkingFolderOnWindows() {
		verifyLogContains('C:/selenium/tmp')
		return this
	}
	
	public SessionDetailsPage clickCancel() {
		WebUI.click(css('button[title="Cancel"]'))
		return this
	}
	
	public SessionDetailsPage verifySatusCanceled() {
		WebUI.verifyElementText(css("span.status-badge"), 'Canceled')
		return this
	}
}
