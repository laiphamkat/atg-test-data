package katalon.common

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.lib.BasePage

public class AdminHeaderBar extends BasePage<AdminHeaderBar>  {

	public AdminHeaderBar clickAvatar() {
		WebUI.click(css('.MuiAvatar-img'))
		return this
	}

	public AdminHeaderBar clickViewProfile() {
		WebUI.click(text('View Profile'))
		return this
	}

	public AdminHeaderBar clickUserSettings() {
		WebUI.click(text('Notification Settings'))
		// Notification Setting will be changed to User Setting in later PR
		//WebUI.click(text('User Settings'))
		return this
	}

	public AdminHeaderBar clickDocumentation() {
		WebUI.click(text('Documentation'))
		return this
	}

	public AdminHeaderBar clickCommunity() {
		WebUI.click(text('Community'))
		return this
	}

	public AdminHeaderBar clickSubmitATicket() {
		WebUI.click(text('Submit a Support Case'))
		return this
	}

	public AdminHeaderBar clickSwitchAccount() {
		WebUI.click(text('Switch Account'))
		return this
	}
	
	public AdminHeaderBar clickSignOut() {
		WebUI.click(text('Sign Out'))
		return this
	}

	public AdminHeaderBar verifyUserSettingPresent() {
		WebUI.verifyEqual(WebUI.getUrl(), GlobalVariable.testOpsUrl+"user/settings?")
		return this
	}

	public AdminHeaderBar clickSettingIcon(isAdmin = false) {
		if (isAdmin) {
			WebUI.click(xpath("//button[@title='Settings']"))
		} else {
			WebUI.click(xpath("(//header//button[1])[2]"))
		}
		return this
	}

	public AdminHeaderBar clickProductUtilization() {
		WebUI.click(genDropdownItem("Product Utilization"))
		return this
	}

	public AdminHeaderBar verifyProductUtilizationPresent() {
		WebUI.verifyElementPresent(genDropdownItem("Product Utilization"), 5)
		return this
	}

	public AdminHeaderBar clickLicenseManagement() {
		WebUI.click(genDropdownItem("License Management"))
		return this
	}

	public AdminHeaderBar verifyLicenseManagementPresent() {
		WebUI.verifyElementPresent(genDropdownItem("License Management"), 5)
		return this
	}

	public AdminHeaderBar clickUserManagement () {
		WebUI.click(genDropdownItem("User Management"))
		return this
	}

	public AdminHeaderBar verifyUserManagementPresent() {
		WebUI.verifyElementPresent(genDropdownItem("User Management"), 5)
		return this
	}

	public AdminHeaderBar verifyUserManagementNotPresent() {
		WebUI.verifyElementNotPresent(genDropdownItem("User Management"), 5)
		return this
	}

	public AdminHeaderBar clickPaymentMethod () {
		WebUI.click(genDropdownItem("Payment Method"))
		return this
	}

	public AdminHeaderBar verifyPaymentMethodPresent() {
		WebUI.verifyElementPresent(genDropdownItem("Payment Method"), 5)
		return this
	}

	public AdminHeaderBar verifyPaymentMethodNotPresent() {
		WebUI.verifyElementNotPresent(genDropdownItem("Payment Method"), 5)
		return this
	}

	public AdminHeaderBar clickSubscriptionManagement () {
		WebUI.click(genDropdownItem("Subscription Management"))
		return this
	}

	public AdminHeaderBar verifySubscriptionManagementPresent () {
		WebUI.verifyElementPresent(genDropdownItem("Subscription Management"), 5)
		return this
	}

	public AdminHeaderBar verifySubscriptionManagementNotPresent () {
		WebUI.verifyElementNotPresent(genDropdownItem("Subscription Management"), 5)
		return this
	}

	public AdminHeaderBar clickOrganizationManagement () {
		WebUI.click(genDropdownItem("Organization Management"))
		return this
	}

	public AdminHeaderBar verifyOrganizationManagementPresent () {
		WebUI.verifyElementPresent(genDropdownItem("Organization Management"), 5)
		return this
	}

	public AdminHeaderBar verifyOrganizationManagementNotPresent () {
		WebUI.verifyElementNotPresent(genDropdownItem("Organization Management"), 5)
		return this
	}

	public AdminHeaderBar clickSupportManagement () {
		WebUI.click(xpath("//p[text()='Support Management']"))
		return this
	}

	public AdminHeaderBar verifySupportManagementPresent () {
		WebUI.verifyElementPresent(xpath("//p[text()='Support Management']"), 5)
		return this
	}

	public AdminHeaderBar verifySupportManagementNotPresent () {
		WebUI.verifyElementNotPresent(xpath("//p[text()='Support Management']"), 5)
		return this
	}

	public AdminHeaderBar clickTeamManagement(isAdmin = false) {
		if(isAdmin) {
			WebUI.click(genDropdownItem("Team Management"))
		} else {
			WebUI.click(xpath("//p[text()='Team Management']"))
		}
		return this
	}

	public AdminHeaderBar verifyTeamManagementPresent(isAdmin = false) {

		if (isAdmin) {
			WebUI.verifyElementPresent(genDropdownItem("Team Management"), 5)
		} else {
			WebUI.verifyElementPresent(xpath("//p[text()='Team Management']"), 5)
		}
		return this
	}

	public AdminHeaderBar clickProjectManagement(isAdmin = false) {
		if (isAdmin) {
			WebUI.click(genDropdownItem("Project Management"))
		} else {
			WebUI.click(xpath("//p[text()='Project Management']"))
		}
		return this
	}

	public AdminHeaderBar verifyProjectManagementPresent(isAdmin = false) {
		if (isAdmin) {
			WebUI.verifyElementPresent(genDropdownItem("Project Management"), 5)
		} else {
			WebUI.verifyElementPresent(xpath("//p[text()='Project Management']"), 5)
		}
		return this
	}

	public AdminHeaderBar clickTestOpsHomepage(isAdmin = fasle) {
		if (isAdmin) {
			WebUI.click(genDropdownItem("TestOps Homepage"))
		} else {
			WebUI.click(xpath("//p[text()='TestOps Homepage']"))
		}
		return this
	}

	public AdminHeaderBar verifyTestOpsHomepagePresent(isAdmin = false) {
		if (isAdmin) {
			WebUI.verifyElementPresent(genDropdownItem("TestOps Homepage"), 5)
		} else {
			WebUI.verifyElementPresent(xpath("//p[text()='TestOps Homepage']"), 5)
		}
		return this
	}

	public AdminHeaderBar verifyProfilePageNavigation() {
		WebUI.verifyEqual(WebUI.getUrl(), GlobalVariable.myUrl+"profile")
		return this
	}

	public AdminHeaderBar verifyWelcomePageNavigation() {
		WebUI.verifyEqual(WebUI.getUrl(), GlobalVariable.myUrl+"welcome")
		return this
	}
	public AdminHeaderBar verifyUserSettingPageNavigation() {
		WebUI.verifyEqual(WebUI.getUrl(), GlobalVariable.testOpsUrl+"user/settings?")
		return this
	}

	public AdminHeaderBar verifyDocumentPageNavigation() {
		WebUI.verifyElementPresent(xpath("//a[contains(@href,'docs.katalon.com')]"), 5)
		return this
	}

	public AdminHeaderBar verifyCommunityPageNavigation() {
		WebUI.verifyElementPresent(xpath("//a[contains(@href,'forum.katalon.com')]"), 5)
		return this
	}

	public AdminHeaderBar verifySupportPageNavigation() {
		WebUI.verifyElementPresent(xpath("//a[contains(@href,'katalon-inc--sc.sandbox.my.site.com/katalonhelpcenter/services/auth/sso/TestOps')]"), 5)
		return this
	}

	private TestObject genDropdownItem(String itemName) {
		return xpath("//div/span[text()='$itemName']");
	}
}
