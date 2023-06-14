package katalon.testops.planning
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword

import internal.GlobalVariable
import katalon.fw.lib.BasePage

public class RequirementsPage extends BasePage<RequirementsPage> {

	def statusBar = { value -> return xpath("//div[text() = '${value}' and @class = 'counter__label']") }
	def statusChart = { value -> return xpath("//div[contains(@class, 'status-bar')]//div[text() = '${value}']") }
	def filterToolbar = { condition -> return xpath("//div[@class = 'filter']/button[./div[text() = '${condition}']]") }
	def tableHeaderTitle = { column -> return xpath("//table//th[text() = '${column}']") }

	public RequirementsPage navigateTo(String teamId, String projectId) {
		WebUI.navigateToUrl("${GlobalVariable.testOpsUrl}team/${teamId}/project/${projectId}/requirements")
		return this
	}

	public RequirementsPage verifyRequirementTitleDisplayed () {
		WebUI.verifyElementVisible(title('Requirements'))
		return this
	}

	public RequirementsPage verifyStatisticBarDisplayed () {
		WebUI.verifyElementPresent(statusBar('Total'), 0)
		WebUI.verifyElementPresent(statusBar('Jira Issues'), 0)
		WebUI.verifyElementPresent(statusBar('BDD Features'), 0)
		return this
	}

	public RequirementsPage verifyStatusChartDisplayed () {
		WebUI.verifyElementPresent(statusChart('Test Cases With Requirements'), 0)
		WebUI.verifyElementPresent(statusChart('Test Cases Without Requirements'), 0)
		return this
	}

	public RequirementsPage VerifyReportSessionTileDisplayed() {
		WebUI.verifyElementPresent(xpath("//div[@class = 'card-header' and text() = 'Reports']"),0)
		return this
	}
	
	public RequirementsPage verifyTestRunCoverageDisplayed() {
		WebUI.verifyElementPresent(xpath("//div[@class = 'header' and text() = 'Test Run Coverage']"), 0)
		WebUI.verifyElementPresent(xpath("//div[@class = 'description' and text() = 'The quality of each requirement based on the status of the corresponding test result.']"),0)
		return this
	}

	public RequirementsPage clickTestRunCoverageLink() {
		WebUI.click(xpath("//a[@class = 'link-card'][.//div[text() = 'Test Run Coverage']]"))
		return this
	}
	
	public RequirementsPage verifyTraceabilityMatrixDisplayed() {
		WebUI.verifyElementPresent(xpath("//div[@class = 'header' and text() = 'Test Run Coverage']"), 0)
		WebUI.verifyElementPresent(xpath("//div[@class = 'description' and text() = 'Manage the relationship across requirements, test cases, defects.']"),0)
		return this
	}

	public RequirementsPage clickTraceabilityMatrixLink() {
		WebUI.click(xpath("//a[@class = 'link-card'][.//div[text() = 'Traceability Matrix']]"))
		return this
	}
	
	public RequirementsPage VerifyRequirementsSessionTileDisplayed() {
		WebUI.verifyElementPresent(xpath("//div[@class = 'card-header-title' and text() = 'Requirements']"),0)
		return this
	}

	public RequirementsPage verifySearchInputDisplayed() {
		WebUI.verifyElementPresent(id('search-input'),3)
		return this
	}
	
	public RequirementsPage clickKeyFilter() {
		scrollToAndClick(filterToolbar('Key: All'))
		return this
	}
	
	public RequirementsPage clickFeatureFilter() {
		WebUI.click(filterToolbar('Feature: All'))
		return this
	}
	
	public RequirementsPage clickCoppyAsRestApiIcon() {
		WebUI.click(xpath("//button[@data-trackid = 'copy-api-key']"))
		return this
	}

	public RequirementsPage verifyRequirementsTableDisplayed() {
		WebUI.verifyElementPresent(tableHeaderTitle('Type'),0)
		WebUI.verifyElementPresent(tableHeaderTitle('Key'),0)
		WebUI.verifyElementPresent(tableHeaderTitle('Name'),0)
		WebUI.verifyElementPresent(tableHeaderTitle('Test Cases'),0)
		return this
	}
	
	public RequirementsPage verifyCurrentUrl(String expectedUrl) {
		String currentUrl = WebUI.getUrl()
		WebUI.verifyEqual(currentUrl, expectedUrl)
		return this
	}

}
