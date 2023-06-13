package katalon.my.account

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.lib.BasePage

public class ProfilePage extends BasePage<ProfilePage>{

	public ProfilePage navProfilePage () {
		WebUI.navigateToUrl(GlobalVariable.myUrl+"profile")
		return this
	}

	public ProfilePage clickChangePasswordLink () {
		WebUI.click(xpath("//*[text()='Change Password']/parent::*"))
		return this
	}
	
	public ProfilePage verifyKatalonLogoPresent() {
		WebUI.verifyElementPresent(xpath("//img[@alt='Katalon logo']"), 5)
		return this
	}
	
	public ProfilePage verifyChangePasswordBtnClickable() {
		WebUI.verifyElementPresent(xpath("//button/p[text()='Change Password']"), 5)
		WebUI.verifyElementClickable(xpath("//button/p[text()='Change Password']"))
		return this
	}
	
	public ProfilePage verifyDeleteProfileBtnClickable() {
		WebUI.verifyElementPresent(xpath("//button/p[text()='Delete Profile']"), 5)
		WebUI.verifyElementClickable(xpath("//button/p[text()='Delete Profile']"))
		return this
	}
	
	public ProfilePage verifyLogOutBtnClickable() {
		WebUI.verifyElementPresent(xpath("//button/p[text()='Log Out']"), 5)
		WebUI.verifyElementClickable(xpath("//button/p[text()='Log Out']"))
		return this
	}
	
	public ProfilePage verifyBasicInfoPresent() {
		WebUI.verifyElementPresent(text("Name"), 5)
		WebUI.verifyElementPresent(text("Email"), 5)
		WebUI.verifyElementPresent(text("Password"), 5)
		WebUI.verifyElementPresent(text("Job Title"), 5)
		WebUI.verifyElementPresent(text("Registered Date"), 5)
		return this
	}

	public ProfilePage clickDeleteProfile () {
		WebUI.click(text("Delete Profile"))
		return this
	}

	public ProfilePage clickGoToWelcomePage () {
		WebUI.click(id("profile.go_to_welcome"))
		return this
	}
}
