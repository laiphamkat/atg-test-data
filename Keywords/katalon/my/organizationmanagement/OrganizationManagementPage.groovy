package katalon.my.organizationmanagement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class OrganizationManagementPage extends BasePage<OrganizationManagementPage> {
	
	public OrganizationManagementPage verifyTitleOrgManagement() {
		WebUI.verifyElementPresent(title("Organization Management"), 5)
		return this
	}
	
	public OrganizationManagementPage verifyTextOrgProfileSection() {
		WebUI.verifyElementPresent(text("Organization profile"), 5)
		WebUI.verifyElementPresent(xpath("//form[@data-trackid='edit-organization']//label[text()='ID']"), 5)
		return this
	}
	
	public OrganizationManagementPage verifyOrgNameFieldPresent() {
		WebUI.verifyElementPresent(id("orgName"), 5)
		return this
	}
	
	public OrganizationManagementPage verifyAvatarPresent() {
		WebUI.verifyElementPresent(xpath("//div[@class='image-profile']/div[@class='avatar']"), 5)
		return this
	}
	
	public OrganizationManagementPage verifyUpdateNameBtnClickable (){
		WebUI.verifyElementPresent(xpath("//form[@data-trackid='edit-organization']//button[text()='Update']"), 5)
		WebUI.verifyElementClickable(xpath("//form[@data-trackid='edit-organization']//button[text()='Update']"))
		return this
	}
	
	public OrganizationManagementPage clickUpdateNameBtn (){
		click(xpath("//form[@data-trackid='edit-organization']//button[text()='Update']"))
		return this
	}
	
	public OrganizationManagementPage inputOrgName (String orgName){
		WebUI.clearText(id("orgName"))
		WebUI.sendKeys(id("orgName"), orgName)
		return this
	}
	
	public OrganizationManagementPage verifyTextSessionTimeoutSection() {
		WebUI.verifyElementPresent(text("Session Timeout"), 5)
		return this
	}
	
	public OrganizationManagementPage verifyUpdateTimeoutBtnClickable() {
		WebUI.verifyElementPresent(xpath("//form[@data-trackid='edit-session-timeout']//button[text()='Update']"), 5)
		WebUI.verifyElementClickable(xpath("//form[@data-trackid='edit-session-timeout']//button[text()='Update']"))
		return this
	}
	
	public OrganizationManagementPage verifyTimeoutValueFieldPresent() {
		WebUI.verifyElementPresent(id("sessionTimeOut"), 5)
		return this
	}
	
	public OrganizationManagementPage verifyCustomDomainPresent() {
		WebUI.verifyElementPresent(text("Custom Domain"), 5)
		WebUI.verifyElementPresent(id("domain"), 5)
		WebUI.verifyElementClickable(xpath("(//button[@type='submit'])[2]"))
		return this
	}
	
	public OrganizationManagementPage verifySSOSettingsPresent() {
		WebUI.verifyElementPresent(text("Single Sign-On (SSO) Settings"), 5)
		WebUI.verifyElementPresent(text("Enable SSO"), 5)
		WebUI.verifyElementPresent(id("sso"), 5)
		WebUI.verifyElementPresent(text("Metadata"), 5)
		WebUI.verifyElementPresent(id("metadata"), 5)
		WebUI.verifyElementClickable(xpath("//form[@data-trackid='sso-organization']//button[text()='Update']"))
		return this
	}
	
	public OrganizationManagementPage verifyIPRestrictionsPresent() {
		WebUI.verifyElementPresent(text("IP Address Restrictions"), 5)
		WebUI.verifyElementPresent(text("These settings can prevent unauthorized access from external sources."), 5)
		WebUI.verifyElementPresent(id("whitelist-ip"), 5)
		WebUI.verifyElementClickable(xpath("//form[@data-trackid='white-list-organization']//button[text()='Update']"))
		return this
	}
	
	public OrganizationManagementPage verifyUpdateOrgNameSuccessfully() {
		WebUI.verifyElementPresent(xpath("//div[@class='toast-title' and text()='Done!']"), 5)
		return this
	}
	
}
