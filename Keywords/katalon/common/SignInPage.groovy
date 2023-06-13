package katalon.common

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.lib.BasePage

public class SignInPage extends BasePage<SignInPage> {
	TestObject signInbtn = btn('Sign in')
	
	public SignInPage verifyPageDisplayed() {
		WebUI.waitForElementVisible(signInbtn, 60)
		WebUI.verifyElementVisible(signInbtn)
		return this
	}
	
	public SignInPage login(String userName=GlobalVariable.owner_mail, String password = GlobalVariable.owner_pass) {
		WebUI.sendKeys(byType('email'), userName)
		WebUI.setEncryptedText(byType('password'), password)
		return this
	}
	
	public SignInPage enterCredential(String userName=GlobalVariable.owner_mail, String password = GlobalVariable.owner_pass) {
		WebUI.navigateToUrl(GlobalVariable.testOpsUrl)
		WebUI.sendKeys(byType('email'), userName)
		WebUI.setEncryptedText(byType('password'), password)
		return this
	}

	public SignInPage clickSignIn() {
		WebUI.click(signInbtn)
		return this
	}

	public SignInPage verifySuccessfullySignIn(String org_name) {
		WebUI.verifyTextPresent('Welcome to ' + org_name, false)
		return this
	}

	public SignInPage doCheckRemberMe() {
		WebUI.click(byName('rememberMe'))
		return this
	}
}
