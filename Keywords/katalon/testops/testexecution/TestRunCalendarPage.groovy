package katalon.testops.testexecution

import org.openqa.selenium.Keys

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.lib.BasePage

public class TestRunCalendarPage extends BasePage<TestRunCalendarPage> {
	TestObject pageTitle = css('#main-body [title="Test Run Calendar"]')

	public TestRunCalendarPage navigateTo(String teamId, String projectId) {
		WebUI.navigateToUrl("${GlobalVariable.testOpsUrl}team/${teamId}/project/${projectId}/test-planning")
		return this
	}

	public TestRunCalendarPage verifyPageDisplayed() {
		WebUI.waitForElementVisible(pageTitle, 10)
		WebUI.verifyElementVisible(pageTitle)
		return this
	}

	public TestRunCalendarPage clickTestRunCalendarTab() {
		WebUI.click(title('Test Run Calendar'))
		return this
	}

	public TestRunCalendarPage clickTestRunListTab() {
		WebUI.click(title('Test Run List'))
		return this
	}

	public TestRunCalendarPage clickApplicationRepositoryTab() {
		WebUI.click(title('Application Repository'))
		return this
	}

	public TestRunCalendarPage clickScheduleTestRun() {
		WebUI.waitForElementVisible(btn('Schedule Test Run'), 10)
		WebUI.click(btn('Schedule Test Run'))
		return this
	}

	public TestRunCalendarPage clickScheduleSampleTestRun() {
		WebUI.click(btn('Schedule Sample Test Run'))
		return this
	}

	public TestRunCalendarPage clearFilter() {
		clearTextAndSendKeysByActions(id('search-input'), Keys.chord(Keys.ENTER))
		return this
	}

	public TestRunCalendarPage clickTestSuiteFilter() {
		WebUI.click(xpath("//div[contains(@class, 'filter-btn-content') and contains(., 'Test Suite:')]"))
		return this
	}

	public TestRunCalendarPage setTestSuiteFilter(String tsName) {
		WebUI.setText(css("[placeholder='Filter test suites']"), tsName)
		return this
	}

	public TestRunCalendarPage selectTestSuite(String tsName) {
		WebUI.click(xpath("//ul//li//p[text()='${tsName}']"))
		return this
	}

	public TestRunCalendarPage clickTSCFilter() {
		WebUI.click(xpath("//div[contains(@class, 'filter-btn-content') and contains(., 'Test Suite Collection:')]"))
		return this
	}

	public TestRunCalendarPage setTSCFilter(String tsName) {
		WebUI.setText(css("[placeholder='Filter test suite collections']"), tsName)
		return this
	}

	public TestRunCalendarPage selectTSC(String tscName) {
		WebUI.click(xpath("//ul//li//p[text()='${tscName}']"))
		return this
	}

	public TestRunCalendarPage clickStatusFilter() {
		WebUI.click(xpath("//div[contains(@class, 'filter-btn-content') and contains(., 'Status:')]"))
		return this
	}

	public TestRunCalendarPage selectPassed() {
		WebUI.click(xpath("//ul//li//span[text()='Passed']"))
		return this
	}

	public TestRunCalendarPage selectFailed() {
		WebUI.click(xpath("//ul//li//span[text()='Failed']"))
		return this
	}

	public TestRunCalendarPage clickStartedFilter() {
		WebUI.click(xpath("//div[contains(@class, 'filter-btn-content') and contains(., 'Started:')]"))
		return this
	}

	public TestRunCalendarPage setWithinLast(int number) {
		WebUI.setText(id("within-value"), (String) number)
		return this
	}

	public TestRunCalendarPage clickProfileFilter() {
		WebUI.click(xpath("//div[contains(@class, 'filter-btn-content') and contains(., 'Profile:')]"))
		return this
	}

	public TestRunCalendarPage setProfileFilter(String profile) {
		WebUI.setText(css("[placeholder='Filter by profile']"), profile)
		return this
	}

	public TestRunCalendarPage clickByFilter() {
		WebUI.click(xpath("//div[contains(@class, 'filter-btn-content') and contains(., 'By:')]"))
		return this
	}

	public TestRunCalendarPage setUserFilter(String email) {
		WebUI.setText(css("[placeholder='Filter users']"), email)
		return this
	}

	public TestRunCalendarPage clickUser(String email) {
		WebUI.click(xpath("//ul//li//p[text()='${email}']"))
		return this
	}

	public TestRunCalendarPage clickReleaseFilter() {
		WebUI.click(xpath("//div[contains(@class, 'filter-btn-content') and contains(., 'Release:')]"))
		return this
	}

	public TestRunCalendarPage setReleaseFilter(String release) {
		WebUI.setText(css("[placeholder='Filter releases']"), release)
		return this
	}

	public TestRunCalendarPage clickRelease(String release) {
		WebUI.click(xpath("//ul//li//p[text()='${release}']"))
		return this
	}

	public TestRunCalendarPage clickUpdate() {
		WebUI.click(css('button[type="submit"]'))
		return this
	}

	public TestRunCalendarPage clickMonth() {
		WebUI.click(btn('Month'))
		return this
	}

	public TestRunCalendarPage clickWeek() {
		WebUI.click(btn('Week'))
		return this
	}

	public TestRunCalendarPage clickDay() {
		WebUI.click(btn('Day'))
		return this
	}

	public TestRunCalendarPage clickTimeline() {
		WebUI.click(btn('Timeline'))
		return this
	}

	public TestRunCalendarPage clickNext() {
		WebUI.click(css('.navigate button:nth-child(3)'))
		return this
	}

	public TestRunCalendarPage clickPrevious() {
		WebUI.click(css('.navigate button:nth-child(2)'))
		return this
	}

	public TestRunCalendarPage verifyTimeZoneDisplayed() {
		WebUI.verifyElementVisible(xpath("//div[contains(text(), 'Time zone: ')]"))
		return this
	}

	public TestRunCalendarPage verifyPresentBanner() {
		WebUI.verifyElementPresent(css('.warning-alert-content'),0)
		return this
	}
}
