package katalon.common

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.lib.BasePage


public class AdminSideBar extends BasePage<AdminSideBar>  {

	public AdminSideBar clickProductUtilization() {
		WebUI.click(genItem("Product Utilization"))
		return this
	}

	public AdminSideBar verifyProductUtilizationPresent(isBilling = false) {
		WebUI.verifyElementPresent(xpath("//li[text()='Product Utilization']"), 5)
		WebUI.verifyElementPresent(genItem("Katalon Platform"), 5)
		WebUI.verifyElementPresent(genItem("TestCloud"), 5)
		
		if(isBilling) {
			WebUI.verifyElementNotPresent(genItem("Katalon Studio"), 5)
		}else {
			WebUI.verifyElementPresent(genItem("Katalon Studio"), 5)
		}
		return this
	}

	public AdminSideBar clickLicenseManagement() {
		WebUI.click(genItem("License Management"))
		return this
	}

	public AdminSideBar verifyLicenseManagementPresent() {
		WebUI.verifyElementPresent(genItem("License Management"), 5)
		return this
	}

	public AdminSideBar clickUserManagement () {
		WebUI.click(genItem("User Management"))
		return this
	}

	public AdminSideBar verifyUserManagementPresent () {
		WebUI.verifyElementPresent(genItem("User Management"), 5)
		return this
	}
	
	public AdminSideBar verifyUserManagementNotPresent () {
		WebUI.verifyElementNotPresent(genItem("User Management"), 5)
		return this
	}

	public AdminSideBar clickPaymentMethod () {
		WebUI.click(genItem("Payment Method"))
		return this
	}

	public AdminSideBar verifyPaymentMethodPresent () {
		WebUI.verifyElementPresent(genItem("Payment Method"), 5)
		return this
	}
	
	public AdminSideBar verifyPaymentMethodNotPresent () {
		WebUI.verifyElementNotPresent(genItem("Payment Method"), 5)
		return this
	}

	public AdminSideBar clickSubscriptionManagement () {
		WebUI.click(genItem("Subscription Management"))
		return this
	}

	public AdminSideBar verifySubscriptionManagementPresent () {
		WebUI.verifyElementPresent(genItem("Subscription Management"), 5)
		return this
	}
	
	public AdminSideBar verifySubscriptionManagementNotPresent () {
		WebUI.verifyElementNotPresent(genItem("Subscription Management"), 5)
		return this
	}

	public AdminSideBar clickOrganizationManagement () {
		WebUI.click(genItem("Organization Management"))
		return this
	}

	public AdminSideBar verifyOrganizationManagementPresent () {
		WebUI.verifyElementPresent(genItem("Organization Management"), 5)
		return this
	}
	
	public AdminSideBar verifyOrganizationManagementNotPresent () {
		WebUI.verifyElementNotPresent(genItem("Organization Management"), 5)
		return this
	}

	public AdminSideBar clickSupportManagement () {
		WebUI.click(genItem("Support Management"))
		return this
	}

	public AdminSideBar verifySupportManagementPresent () {
		WebUI.verifyElementPresent(genItem("Support Management"), 5)
		return this
	}
	
	public AdminSideBar verifySupportManagementNotPresent () {
		WebUI.verifyElementNotPresent(genItem("Support Management"), 5)
		return this
	}

	public AdminSideBar clickTeamManagement() {
		WebUI.click(genItem("Project Management"))
		return this
	}

	public AdminSideBar verifyTeamManagementPresent() {
		WebUI.verifyElementPresent(genItem("Project Management"), 5)
		return this
	}

	public AdminSideBar clickProjectManagement() {
		WebUI.click(genItem("Project Management"))
		return this
	}

	public AdminSideBar verifyProjectManagementPresent() {
		WebUI.verifyElementPresent(genItem("Project Management"), 5)
		return this
	}

	public AdminSideBar clickAccountSettings() {
		WebUI.click(genItem("Account Settings"))
		return this
	}

	public AdminSideBar verifyAccountSettingsPresent() {
		WebUI.verifyElementPresent(genItem("Account Settings"), 5)
		return this
	}

	public AdminSideBar clickIdleTimeout() {
		WebUI.click(genItem("Idle Timeout"))
		return this
	}

	public AdminSideBar verifyIdleTimeoutPresent() {
		WebUI.verifyElementPresent(genItem("Idle Timeout"), 5)
		return this
	}
	
	public AdminSideBar verifyIdleTimeoutNotPresent() {
		WebUI.verifyElementNotPresent(genItem("Idle Timeout"), 5)
		return this
	}

	private TestObject genItem(String itemName) {
		return xpath("//a[text()='$itemName']");
	}
}
