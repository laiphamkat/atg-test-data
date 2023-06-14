package katalon.common

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class HeaderBar extends BasePage<HeaderBar> {
	TestObject applicationRepoLink = title('Application Repository')

	public HeaderBar clickDashboard() {
		WebUI.click(title('Dashboard'))
		return this
	}

	public HeaderBar clickPlanning() {
		WebUI.click(title('Planning'))
		return this
	}

	public HeaderBar clickTests() {
		WebUI.click(title('Tests'))
		return this
	}

	public HeaderBar clickExecutions() {
		WebUI.click(title('Executions'))
		return this
	}

	public HeaderBar clickTestRunCalendar() {
		WebUI.click(title('Test Run Calendar'))
		return this
	}

	public HeaderBar clickTestRunList() {
		WebUI.click(title('Test Run List'))
		return this
	}

	public HeaderBar clickApplicationRepository() {
		WebUI.click(applicationRepoLink)
		return this
	}

	public HeaderBar verifyApplicationRepositoryNotDisplayed() {
		WebUI.verifyElementNotPresent(applicationRepoLink, 5)
		return this
	}

	public HeaderBar verifyApplicationRepositoryDisplayed() {
		WebUI.verifyElementVisible(applicationRepoLink)
		return this
	}

	public HeaderBar clickReports() {
		WebUI.click(title('Reports'))
		return this
	}

	public HeaderBar clickVisualTesting() {
		WebUI.click(title('Visual Testing'))
		return this
	}

	public HeaderBar clickConfigurations() {
		WebUI.click(title('Configurations'))
		return this
	}

	public HeaderBar clickTestCloudTunnels() {
		WebUI.click(css('a[title="TestCloud Tunnels"]'))
		return this
	}

	public HeaderBar clickTestEnvironments() {
		WebUI.click(css('a[title="Test Environments"]'))
		return this
	}

	public HeaderBar clickAvatar() {
		WebUI.click(css('#top-navbar .avatar'))
		return this
	}

	public HeaderBar clickSignOut() {
		WebUI.click(id('logout'))
		return this
	}
	
	public HeaderBar clickSwitchAccount() {
		WebUI.click(id('switch-account'))
		return this
	}

	public HeaderBar clickOrganization() {
		WebUI.click(id('select-organization'))
		return this
	}

	public HeaderBar selectOrg(String orgName) {
		WebUI.click(xpath("//a[text()='${orgName}']"))
		return this
	}

	public HeaderBar clickSettingIcon() {
		WebUI.click(title('Settings'))
		return this
	}

	public HeaderBar isVisualTestMenuDisplay() {
		def visualTestMenu = title('Visual Testing')
		waitForElementDisplay(visualTestMenu)
		assert visualTestMenu.getObjectId() != null
		return this
	}

	public HeaderBar verifyKatalonLogoDisplayed() {
		WebUI.verifyElementVisible(xpath("//*[@id='top-left-navbar']/a/span"))
		return this
	}

	public HeaderBar verifyDashboardTabDisplayed() {
		WebUI.verifyElementVisible(title('Dashboard'))
		return this
	}

	public HeaderBar verifyDashboardTabActived() {
		WebUI.verifyElementVisible(xpath("//*[@class = 'nav-link active']"))
		return this
	}

	public HeaderBar verifyPlanningTabDisplayed() {
		WebUI.verifyElementVisible(title('Planning'))
		return this
	}

	public HeaderBar verifyPlanningTabActived() {
		WebUI.verifyElementVisible(xpath("//*[@class = 'nav-link active']"))
		return this
	}

	public HeaderBar verifyTestsTabDisplayed() {
		WebUI.verifyElementVisible(title('Tests'))
		return this
	}

	public HeaderBar verifyTestsTabActived() {
		WebUI.verifyElementVisible(xpath("//*[@class = 'nav-link active']"))
		return this
	}

	public HeaderBar verifyExecutionsTabDisplayed() {
		WebUI.verifyElementVisible(title('Executions'))
		return this
	}

	public HeaderBar verifyExecutionsTabActived() {
		WebUI.verifyElementVisible(xpath("//*[@class = 'nav-link active']"))
		return this
	}

	public HeaderBar verifyReportsTabDisplayed() {
		WebUI.verifyElementVisible(title('Reports'))
		return this
	}

	public HeaderBar verifyReportsTabActived() {
		WebUI.verifyElementVisible(xpath("//*[@class = 'nav-link active']"))
		return this
	}

	public HeaderBar verifyVisualTestingTabDisplayed() {
		WebUI.verifyElementVisible(title('Visual Testing'))
		return this
	}

	public HeaderBar verifyVisualTestingTabActived() {
		WebUI.verifyElementVisible(xpath("//*[@class = 'nav-link active']"))
		return this
	}

	public HeaderBar verifyConfigurationsTabDisplayed() {
		WebUI.verifyElementVisible(title('Configurations'))
		return this
	}

	public HeaderBar verifyConfigurationsTabActived() {
		WebUI.verifyElementVisible(xpath("//*[@class = 'nav-link active']"))
		return this
	}

	public HeaderBar verifyRecordButtonDisplayed() {
		WebUI.verifyElementVisible(id('g5_record_test_case'))
		return this
	}

	public HeaderBar verifyRecordButtonClickable() {
		WebUI.verifyElementClickable(id('g5_record_test_case'))
		return this
	}

	public HeaderBar verifyDownloadIconDisplayed() {
		WebUI.verifyElementVisible(title('Download Katalon package'))
		return this
	}

	public HeaderBar verifyDownloadIconClickable() {
		WebUI.verifyElementClickable(title('Download Katalon package'))
		return this
	}

	public HeaderBar verifyNotificationIconDisplayed() {
		WebUI.verifyElementVisible(xpath('//button[@aria-label="notification"]'))
		return this
	}

	public HeaderBar verifyRecentWorkIconDisplayed() {
		WebUI.verifyElementVisible(title('Recent Work'))
		return this
	}

	public HeaderBar verifyRecentWorkIconClickable() {
		WebUI.verifyElementClickable(title('Recent Work'))
		return this
	}

	public HeaderBar verifySettingIconDisplayed() {
		WebUI.verifyElementVisible(title('Settings'))
		return this
	}

	public HeaderBar verifySettingIconClickable() {
		WebUI.verifyElementClickable(title('Settings'))
		return this
	}

	public HeaderBar verifyProfileIconDisplayed() {
		WebUI.verifyElementVisible(css('#top-navbar .avatar'))
		return this
	}

	public HeaderBar clickUserSettings() {
		WebUI.click(title('User Settings'))
		return this
	}
}
