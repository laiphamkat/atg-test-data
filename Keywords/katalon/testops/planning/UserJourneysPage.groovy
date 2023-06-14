package katalon.testops.planning
import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.lib.BasePage

public class UserJourneysPage extends BasePage<UserJourneysPage> {
	
	def tableHeaderTitle = { table, column -> return xpath("//table[@data-test-id='${table}']//th[text() = '${column}']") }
	def timeRangeValue = { value -> return xpath("//form//label[.//span[text() = '${value}']]//span//input") }
	
	public UserJourneysPage navigateTo(String teamId, String projectId) {
		WebUI.navigateToUrl("${GlobalVariable.testOpsUrl}team/${teamId}/project/${projectId}/autonomous/user-journeys")
		return this
	}
	
	// Install and Active guideline loading section
	public UserJourneysPage verifyInstalledAndActiveAIAgentNotificationIsDisplayed() {
		WebUI.waitForElementVisible(xpath('//div[@data-test-id="generate-test-loading"]//i'),10)
		WebUI.verifyElementVisible(xpath('//div[@data-test-id="generating-description"]//div[text() = "The AI Agent must be installed and activated before generating user journey maps and autonomous test cases."]'))
		WebUI.verifyElementVisible(xpath('//div[@data-test-id="generating-description"]//div[text() = "Please contact your Katalon Organization Admin to have it set up."]'))
		
		return this
	}
	
	public UserJourneysPage verifyInstallStepsGuidelineIconIsDisplayed() {
		WebUI.verifyElementVisible(xpath('//div[@data-test-id="generating-steps"]//span[text() = "Katalon AI studies the user interactions..."]'))
		WebUI.verifyElementVisible(xpath('//div[@data-test-id="generating-steps"]//span[text() = "then generates user journeys map..."]'))
		WebUI.verifyElementVisible(xpath('//div[@data-test-id="generating-steps"]//span[text() = "and creates test cases for you!"]'))
		return this
	}
	
	public UserJourneysPage verifyInstallStepsGuidelineIsDisplayed() {
		WebUI.verifyElementVisible(xpath('//div[@data-test-id="generating-steps"]//i[contains(@class, "fa-code-branch")]'))
		WebUI.verifyElementVisible(xpath('//div[@data-test-id="generating-steps"]//i[contains(@class, "fa-robot")]'))
		WebUI.verifyElementVisible(xpath('//div[@data-test-id="generating-steps"]//i[contains(@class, "fa-folder-open")]'))
		return this
	}
	
	public UserJourneysPage verifyLoadingIconIsDisplayed() {
		WebUI.verifyElementVisible(xpath('//div[@data-test-id="generate-test-loading"]//i'))
		return this
	}
	
	public UserJourneysPage clickLearnMore() {
		WebUI.click(id('learn-more'))
		return this
	}
	
	// Already setup AI Agent
	
	public UserJourneysPage waitForUserJourneyLoaded() {
		WebUI.waitForElementVisible(xpath('//div[@data-test-id="header-title"][text() = "User Journeys"]'), 30)
		return this
	}
	
	public UserJourneysPage verifyUserJourneysTitleIsDisplayed() {
		WebUI.verifyElementVisible(xpath('//div[@data-test-id="header-title"][text() = "User Journeys"]'))
		return this
	}
	
	public UserJourneysPage verifyActiveStatusIsDisplayed() {
		WebUI.verifyElementVisible(xpath('//div[@data-test-id="aut-status"]//span[text() = "Active"]'))
		return this
	}
	
	public UserJourneysPage verifyDomainsTableIsDisplayed () {
		WebUI.verifyElementVisible(tableHeaderTitle('domains-table','Application Domain(s)'))
		WebUI.verifyElementVisible(tableHeaderTitle('domains-table','Activation Date'))
		WebUI.verifyElementVisible(tableHeaderTitle('domains-table','Last Data Received Date'))
		return this
	}
	
	public UserJourneysPage verifyDomainsTableNotEmpty() {
		List<WebElement> elements = WebUI.findWebElements(xpath("//table[@data-test-id='domains-table']//tbody//tr"), 10)
		WebUI.verifyGreaterThanOrEqual(elements.size(), 1)
		return this
	}
	
	public UserJourneysPage verifyUserJourneyMapTitleIsDisplayed() {
		WebUI.verifyElementVisible(text('User Journey Map'))
		return this
	}
	
	public UserJourneysPage verifyUserJourneyMapDescriptionIsDisplayed() {
		WebUI.verifyElementVisible(text('User journey maps show how users navigate from one page to another as they interact with your application. User behaviors can change over time, you can generate test cases reflecting the most up-to-date trends by adjusting the time period to cover the past week or month.'))
		return this
	}
	
	public UserJourneysPage verifyUserJourneyTableIsDisplayed () {
		WebUI.verifyElementVisible(tableHeaderTitle('user-journey-map-table','ID'))
		WebUI.verifyElementVisible(tableHeaderTitle('user-journey-map-table','Time Period'))
		WebUI.verifyElementVisible(tableHeaderTitle('user-journey-map-table','Test Cases'))
		return this
	}
	
	public UserJourneysPage verifyUserJourneyTableNotEmpty() {
		List<WebElement> elements = WebUI.findWebElements(xpath("//table[@data-test-id='user-journey-map-table']//tbody//tr"), 10)
		WebUI.verifyGreaterThanOrEqual(elements.size(), 1)
		return this
	}
	
	public UserJourneysPage clickGenerateNewUserJourneyMap() {
		WebUI.click(xpath('//button[@test-id="generate-new-user-journey-map"][text() = "Generate New User Journey Map"]'))
		return this
	}
	
	// Select time range form
	public UserJourneysPage verifyTimePeriodFormTitleIsDisplayed() {
		WebUI.verifyElementVisible(xpath('//form//h2[text() = "Select time period"]'))
		return this
	}
	
	public UserJourneysPage verifyTimePeriodDescriptionIsDisplayed() {
		WebUI.verifyElementVisible(xpath('//form//p[text() = "Please specify the time period of user interactions to create the new journey map. "]'))
		WebUI.verifyElementVisible(xpath('//form//b[text() = "A week of data is usually enough"]'))
		WebUI.verifyElementVisible(xpath('//form//p[text() = " to reflect how the users interact with your application."]'))
		return this
	}

	public UserJourneysPage selectLas7DaysOption() {
		WebUI.check(timeRangeValue('Last 7 days (Recommended)'))
		return this
	}
	
	public UserJourneysPage selectLas30DaysOption() {
		WebUI.check(timeRangeValue('Last 30 days'))
		return this
	}
	
	public UserJourneysPage selectLas90DaysOption() {
		WebUI.check(timeRangeValue('Last 90 days'))
		return this
	}
	
	public UserJourneysPage selectCustomOption() {
		WebUI.check(timeRangeValue('Custom'))
		return this
	}
	
	public UserJourneysPage clickStartDate() {
		WebUI.click(xpath("//label[text() = 'Start']//following-sibling::*//input"))
		return this
	}
	
	public UserJourneysPage clickEndDate() {
		WebUI.click(xpath("//label[text() = 'End']//following-sibling::*//input"))
		return this
	}
	
	public UserJourneysPage verifyRangeCalendarIsDisplayed() {
		WebUI.verifyElementVisible(xpath("//div[contains(@class, 'MuiDateRangeCalendar-root')]"))
		return this
	}
	
	public UserJourneysPage verifyGenerateButtonIsClickabled() {
		WebUI.verifyElementClickable(id("submit-generate-test-cases"))
		return this
	}
	
	public UserJourneysPage clickCancelTimePeriodForm() {
		WebUI.click(id("cancel-dialog"))
		return this
	}
}
