package katalon.testops.testexecution

import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.lib.BasePage

public class TestRunListPage extends BasePage<TestRunListPage> {
	private TestObject pageTitle = css('div[title="Test Run List"]')
	private TestObject filterBoxObj = id('search-input')
	private def testRunNameObj = { int rowIndex -> return xpath("//tr[${rowIndex}]//td[1]//a") }
	private def testRunReleaseObj = { int rowIndex -> return xpath("//tr[${rowIndex}]//td[2]") }
	private def testRunScriptRepoObj = { int rowIndex -> return xpath("//tr[${rowIndex}]//td[3]") }
	private def testRunTestEnvObj = { int rowIndex -> return xpath("//tr[${rowIndex}]//td[4]") }
	private def testRunLastRunObj = { int rowIndex -> return xpath("//tr[${rowIndex}]//td[5]") }
	private def testRunLastRunLink = { int rowIndex -> return xpath("//tbody//tr//td[5]//a[${rowIndex}]") }
	private def testRunNextRunObj = { int rowIndex -> return xpath("//tr[${rowIndex}]//td[6]") }
	private def testRunExecuteBtn = { int rowIndex -> return xpath("//tbody//tr[${rowIndex}]//button[@data-trackid='execute-plan']") }
	private def sortOption = { String option -> return xpath("//li[@role='menuitem']//span[text()='${option}']") }
	private def page = { int pageIndex -> return css("ul.pagination li:nth-child(${pageIndex + 1})") }

	public TestRunListPage navigateTo(String teamId, String projectId) {
		WebUI.navigateToUrl("${GlobalVariable.testOpsUrl}team/${teamId}/project/${projectId}/grid")
		return this
	}

	public TestRunListPage waitUntilPageDisplayed() {
		WebUI.waitForElementVisible(pageTitle, 15)
		WebUI.verifyElementVisible(pageTitle)
		return this
	}

	public TestRunListPage clickTestRunCalendarTab() {
		WebUI.click(title('Test Run Calendar'))
		return this
	}

	public TestRunListPage clickTestRunListTab() {
		WebUI.click(title('Test Run List'))
		return this
	}

	public TestRunListPage clickApplicationRepositoryTab() {
		WebUI.click(title('Application Repository'))
		return this
	}

	public TestRunListPage waitUntilTestRunListLoadedCompletely( ) {
		WebUI.waitForElementNotPresent(css('.table-loading'), 15)
		return this
	}

	public TestRunListPage clickScheduleTestRun() {
		WebUI.click(btn('Schedule Test Run'))
		return this
	}

	public TestRunListPage clickScheduleSampleTestRun() {
		WebUI.click(btn('Schedule Sample Test Run'))
		return this
	}

	public TestRunListPage filterByName(String name) {
		WebUI.waitForElementVisible(filterBoxObj, 60)
		WebUI.sendKeys(filterBoxObj, name)
		WebUI.sendKeys(filterBoxObj, Keys.chord(Keys.ENTER))
		return this
	}

	public TestRunListPage clearFilter() {
		clearTextAndSendKeysByActions(filterBoxObj, Keys.chord(Keys.ENTER))
		return this
	}

	public TestRunListPage verifyTestRunName(int rowIndex, String expectedValue) {
		WebUI.verifyElementText(testRunNameObj(rowIndex), expectedValue)
		return this
	}

	public TestRunListPage verifyTestRunRelease(int rowIndex, String expectedValue) {
		WebUI.verifyElementText(testRunReleaseObj(rowIndex), expectedValue)
		return this
	}

	public TestRunListPage verifyTestRunScriptRepo(int rowIndex, String expectedValue) {
		WebUI.verifyElementText(testRunScriptRepoObj(rowIndex), expectedValue)
		return this
	}

	public TestRunListPage verifyTestRunTestEnv(int rowIndex, String expected) {
		String actual = WebUI.getText(testRunTestEnvObj(rowIndex))
		List<String> actualEnv = actual.split('\n').sort()
		List<String> expectedEnv = expected.split('\n').sort()
		WebUI.verifyEqual(actualEnv.equals(expectedEnv), true)
		return this
	}

	/**
	 * Verify the test environment column of a test run executed with TestCloud.
	 * For example, you input 'Windows', 'Chrome', '101, 102, 103'
	 * It will verify the actual matches the expected:
	 * TestCloud Windows Chrome 101
	 * TestCloud Windows Chrome 102
	 * TestCloud Windows Chrome 103
	 * @param rowIndex the index of the test run you want to verify
	 * @param platform Windows/Linux/MacOS
	 * @param browser Chrome/Firefox/Safari/etc.
	 * @param browserVersions 101/102/103/etc.
	 * @return
	 */
	public TestRunListPage verifyTestRunTCTestEnvs(int rowIndex, String platform, String browser, List<String> browserVersions) {
		browser = browser.replace('(', '').replace(')', '') // In case of headless, we need to remove '(' and ')'
		String expected = browserVersions.collect { "TestCloud ${platform} ${browser} ${it}" }.join('\n')
		verifyTestRunTestEnv(rowIndex, expected)
		return this
	}

	public TestRunListPage verifyTestRunLastRun(int rowIndex, String expectedValue) {
		WebUI.verifyElementText(testRunLastRunObj(rowIndex), expectedValue)
		return this
	}

	public TestRunListPage verifyTestRunLastRunAfterCreated(int rowIndex) {
		String actual = WebUI.getText(testRunLastRunObj(rowIndex))
		WebUI.verifyMatch(actual, 'in a few secs|a few secs ago|a min ago|[2-9] mins ago', true)
		// Sometimes, the test is run so fast, it will display 'in a few secs', but when doing manually, it does not display this value
		return this
	}

	public TestRunListPage verifyTestRunNextRun(int rowIndex, String expectedValue) {
		WebUI.verifyElementText(testRunNextRunObj(rowIndex), expectedValue)
		return this
	}

	public TestRunListPage verifyExecuteButtonDisplayed(int rowIndex) {
		WebUI.verifyElementVisible(testRunExecuteBtn(rowIndex))
		return this
	}

	public TestRunListPage verifyExecuteButtonClickable(int rowIndex) {
		WebUI.verifyElementClickable(testRunExecuteBtn(rowIndex))
		return this
	}

	public TestRunListPage clickExecuteButton(int rowIndex) {
		WebUI.click(testRunExecuteBtn(rowIndex))
		return this
	}

	public TestRunListPage clickTestRunName(int rowIndex) {
		WebUI.click(testRunNameObj(rowIndex))
		return this
	}

	public TestRunListPage clickTestEnvironment(int testRunIndex, int testEnvIndex) {
		WebUI.click(xpath("//tbody//tr[${testRunIndex}]//td[4]//a[${testEnvIndex}]"))
		return this
	}

	public TestRunListPage clickLastRun(int index) {
		WebUI.click(testRunLastRunLink(index))
		return this
	}

	public TestRunListPage refreshPage() {
		WebUI.refresh()
		return this
	}

	public TestRunListPage clickScriptRepoFilter() {
		WebUI.click(css('.filter'))
		return this
	}

	public TestRunListPage selectScriptRepo(String scriptRepo) {
		WebUI.click(xpath("//li[@role='menuitem']//p[text()='${scriptRepo}']"))
		return this
	}

	public TestRunListPage clickFilterSort() {
		WebUI.click(css('.filter-sort'))
		return this
	}

	public TestRunListPage selectNameDescending() {
		WebUI.click(sortOption('Name (Descending)'))
		return this
	}

	public TestRunListPage selectNameAscending() {
		WebUI.click(sortOption('Name (Ascending)'))
		return this
	}

	public TestRunListPage clickPagination(int pageIndex) {
		WebUI.click(page(pageIndex))
		return this
	}

	public TestRunListPage clickPreviousPage() {
		WebUI.click(css('ul.pagination li.previous'))
		return this
	}

	public TestRunListPage clickNextPage() {
		WebUI.click(css('ul.pagination li.next'))
		return this
	}

	public TestRunListPage verifyPageActive(int pageIndex) {
		String classes = WebUI.getAttribute(page(pageIndex), 'class')
		WebUI.verifyEqual(classes.contains('active'), true)
		return this
	}

	public TestRunListPage verifyPageNotActive(int pageIndex) {
		String classes = WebUI.getAttribute(page(pageIndex), 'class')
		WebUI.verifyEqual(classes.contains('active'), false)
		return this
	}

	public TestRunListPage verifyTestRunsFilteredBy(String scriptRepo) {
		List<WebElement> testRunRepos = WebUI.findWebElements(css('tbody tr td:nth-child(3)'), 10)
		testRunRepos.each { WebUI.verifyEqual(it.getText(), scriptRepo) }
		return this
	}

	public TestRunListPage verifyTestRunsOrderedByNameDescending() {
		List<WebElement> testRunNames = WebUI.findWebElements(css('tbody tr td:nth-child(1)'), 10)
		List<String> names = testRunNames.collect { it.getText() }
		WebUI.verifyEqual(names, names.sort())
		return this
	}

	public TestRunListPage verifyNoTestRunsDisplayed() {
		WebUI.verifyElementNotPresent(testRunNameObj(1), 3)
		return this
	}
}
