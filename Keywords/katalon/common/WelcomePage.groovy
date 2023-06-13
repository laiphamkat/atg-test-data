package katalon.common

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.lib.BasePage

public class WelcomePage extends BasePage<WelcomePage> {

	public WelcomePage clickCreateANewAccount() {
		WebUI.click(svg('Create A New Account'))
		return this
	}

	public WelcomePage selectAccount (String accountId = GlobalVariable.defaultAccount) {
		scrollToAndClick(xpath("//a[contains(@href,'accountId=$accountId')]"))
		return this
	}
	
	public selectAccountMember(String accountId = GlobalVariable.defaultAccount) {
		scrollToAndClick(xpath("//span[text()='$accountId']/ancestor::div/following-sibling::div/*[local-name()='svg'][@id]"))
		return this
	}
	
	public WelcomePage selectAccountByName (String accountName) {
		scrollToAndClick(xpath("//h6[text()='$accountName']/following::a"))
		return this
	}

	public WelcomePage verifyAccountIsDeleted(String accountId) {
		WebUI.verifyElementNotPresent(xpath("//a[contains(@href,'accountId=$accountId')]"), 10)
		return this
	}

	public WelcomePage verifyWelcomePagePresent() {
		WebUI.verifyElementPresent(text("Welcome to Katalon"), 5)
		return this
	}
	
	public WelcomePage verifyCreateNewAccountBtnClickable() {
		WebUI.verifyElementPresent(svg('Create A New Account'), 5)
		WebUI.verifyElementClickable(svg('Create A New Account'))
		return this
	}
	
	public WelcomePage verifyKatalonLogo() {
		WebUI.verifyElementPresent(xpath("//h6[text()='Welcome to Katalon']/preceding-sibling::*[local-name()='svg']"), 5)
		return this
	}
	
	public WelcomePage verifyAccountListPresent() {
		WebUI.verifyElementPresent(xpath("//*[text()='My Accounts']/parent::div/following-sibling::div[contains(@class,'MuiBox-root')]"), 5)
		return this
	}
	
	public WelcomePage verifyTryAnotherEmailLink() {
		WebUI.verifyElementPresent(text("Try another email?"), 5)
		WebUI.verifyElementPresent(xpath("//a[@href='../logout']"), 0)
		return this
	}

	public WelcomePage verifyAccessDeniedPopupPresent(){
		WebUI.verifyElementPresent(text("Access denied"), 5)
		WebUI.verifyElementText(id("welcome_page.popup_permission_denied.content"), "You do not have permission to go to the Account Homepage. Please contact your Account Owner/ Account Administrator.")
		return this
	}
	
	public WelcomePage clickJoin(String orgId) {
		click(xpath("//h6[text()='Pending Invitations']/following-sibling::ul//p/span[text()='${orgId}']/ancestor::div/following-sibling::div/button[text()='Join']"))
		return this
	}
	
	public WelcomePage clickTryAnotherEmailLink() {
		click(text("Try another email?"))
		return this
	}
	
	public int getNoOfMembers(String accountId) {
		return WebUI.getText(xpath("//span[text()='$accountId']/parent::h6/parent::div/preceding-sibling::div[3]//span")).toInteger()
	}
	
	public int getNoOfOrgs(String accountId) {
		return WebUI.getText(xpath("//span[text()='$accountId']/parent::h6/parent::div/preceding-sibling::div[2]//span")).toInteger()
	}
	
	public int getNoOfProjects(String accountId) {
		return WebUI.getText(xpath("//span[text()='$accountId']/parent::h6/parent::div/preceding-sibling::div[1]//span")).toInteger()
	}
	
}

