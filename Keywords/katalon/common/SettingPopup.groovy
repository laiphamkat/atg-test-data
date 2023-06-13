package katalon.common

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class SettingPopup extends BasePage<SettingPopup> {

	public SettingPopup clickAutonomousTesting() {
		scrollToAndClick(text('Autonomous Testing'))
		return this
	}

	public SettingPopup verifyTestOpsAdminDisplayed() {
		WebUI.verifyElementVisible(text('TESTOPS ADMIN'))
		return this
	}

	public SettingPopup verifyProductionUtilizationDisplayed() {
		WebUI.verifyElementVisible(text('Product Utilization'))
		return this
	}

	public SettingPopup verifyProductionUtilizationClickable() {
		WebUI.verifyElementClickable(text('Product Utilization'))
		return this
	}

	public SettingPopup verifyLicenseManagementDisplayed() {
		WebUI.verifyElementVisible(text('License Management'))
		return this
	}

	public SettingPopup verifyLicenseManagementClickable() {
		WebUI.verifyElementClickable(text('License Management'))
		return this
	}

	public SettingPopup verifyUserManagementDisplayed() {
		WebUI.verifyElementVisible(text('User Management'))
		return this
	}

	public SettingPopup verifyUserManagementClickable() {
		WebUI.verifyElementClickable(text('User Management'))
		return this
	}

	public SettingPopup verifyOrganizationManagementDisplayed() {
		WebUI.verifyElementVisible(text('Organization Management'))
		return this
	}

	public SettingPopup verifyOrganizationManagementClickable() {
		WebUI.verifyElementClickable(text('Organization Management'))
		return this
	}

	public SettingPopup verifySupportManagementDisplayed() {
		WebUI.verifyElementVisible(text('Support Management'))
		return this
	}

	public SettingPopup verifySuportManagementClickable() {
		WebUI.verifyElementClickable(text('Support Management'))
		return this
	}

	public SettingPopup verifyTestOpsSettingsDisplayed() {
		WebUI.verifyElementVisible(text('TESTOPS SETTINGS'))
		return this
	}

	public SettingPopup verifyTeamManagementDisplayed() {
		WebUI.verifyElementVisible(text('Team Management'))
		return this
	}

	public SettingPopup verifyTeamManagementClickable() {
		WebUI.verifyElementClickable(text('Team Management'))
		return this
	}

	public SettingPopup verifyProjectManagementDisplayed() {
		WebUI.verifyElementVisible(text('Project Management'))
		return this
	}

	public SettingPopup verifyProjectManagementClickable() {
		WebUI.verifyElementClickable(text('Project Management'))
		return this
	}

	public SettingPopup verifyAutonomousTestingDisplayed() {
		WebUI.verifyElementVisible(text('Autonomous Testing'))
		return this
	}
	
	public SettingPopup verifyAutonomousTestingNotDisplayed() {
		WebUI.verifyElementNotPresent(text('Autonomous Testing'), 3)
		return this
	}

	public SettingPopup verifyAutonomousTestingClickable() {
		WebUI.verifyElementClickable(text('Autonomous Testing'))
		return this
	}

	public SettingPopup verifyProjectSettingsDisplayed() {
		WebUI.verifyElementVisible(text('Project Settings'))
		return this
	}

	public SettingPopup verifyProjectSettingsClickable() {
		WebUI.verifyElementClickable(text('Project Settings'))
		return this
	}

	public SettingPopup  verifyTestOpsHomepageDisplayed() {
		WebUI.verifyElementVisible(text('TestOps Homepage'))
		return this
	}

	public SettingPopup verifyTestOpsHomepageClickable() {
		WebUI.verifyElementClickable(text('TestOps Homepage'))
		return this
	}
}
