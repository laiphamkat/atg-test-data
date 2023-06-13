package katalon.testops.dashboard
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class DashboardPage extends BasePage<DashboardPage> {

	public DashboardPage selectOrg(String orgName) {
		// INFO: Because the project is saved cache if we use KOK to perform manual test.
		// So in this case, I just cover the edge of big picture.
		boolean isOrgPresent = WebUI.waitForElementPresent(xpath("//span[@class='name' and text()='$orgName']"), 10, FailureHandling.CONTINUE_ON_FAILURE)
		if(!isOrgPresent) {
			WebUI.click(cls("name"))
			WebUI.click(xpath("(//a[text()='$orgName']//parent::div/following-sibling::div)[1]"))
		}
		return this
	}

	public DashboardPage selectProject (String projectName) {
		WebUI.click(xpath("//div[@class='projectList']//span[text()='$projectName']"))
		return this
	}

	public DashboardPage selectConfigurationTab () {
		WebUI.click(title("Configurations"))
		return this
	}

	public DashboardPage verifyDashboardTitleDisplayed() {
		WebUI.verifyElementVisible(xpath("//*[@class='title-text']"))
		return this
	}

	public DashboardPage verifyRefreshButtonDisplayed() {
		WebUI.verifyElementVisible(id('refresh-btn-project-dashboard'))
		return this
	}

	public DashboardPage verifyRefreshButtonClickable() {
		WebUI.verifyElementClickable(id('refresh-btn-project-dashboard'))
		return this
	}

	public DashboardPage verifyLocalTestEnvironmentTitleDisplayed() {
		WebUI.verifyElementVisible(text("Local Test Environment"))
		return this
	}

	public DashboardPage verifyRequirementCoverageTitleDisplayed() {
		WebUI.verifyElementVisible(text("Requirement Coverage"))
		return this
	}

	public DashboardPage verifyTestActivitiesTitleDisplayed() {
		WebUI.verifyElementVisible(text("Test Activities"))
		return this
	}

	public DashboardPage verifyReleaseReadinessTitleDisplayed() {
		WebUI.verifyElementVisible(text("Release Readiness"))
		return this
	}

	public DashboardPage verifyProductivityTitleDisplayed() {
		WebUI.verifyElementVisible(text("Productivity"))
		return this
	}

	public DashboardPage verifyQualityTitleDisplayed() {
		WebUI.verifyElementVisible(text("Quality"))
		return this
	}

	public DashboardPage verifyPlatformCoverageTitleDisplayed() {
		WebUI.verifyElementVisible(text("Platform Coverage"))
		return this
	}

	public DashboardPage verifyOnboardingSectionIsNotDisplayed() {
		verifyElementNotVisible(xpath("//span[text()='Getting started!']"))
		return this
	}
}

