package katalon.my.account

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage



public class OrgHomePage extends BasePage<OrgHomePage>{
	
	public OrgHomePage verifyUserIsMemberRole() {
		WebUI.verifyElementPresent(link("Go to TestOps Homepage"), 10)
		WebUI.verifyElementPresent(text("You are logged in as a "), 10)
		WebUI.verifyElementPresent(text("Member"), 10)
		return this
	}
	
	public OrgHomePage verifyUserIsOwnerRole() {
		WebUI.verifyElementNotPresent(link("Go to TestOps Homepage"), 10)
		WebUI.verifyElementPresent(text("You are logged in as an "), 10)
		WebUI.verifyElementPresent(text("Organization Owner"), 10)
		return this
	}
}
