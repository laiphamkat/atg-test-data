package katalon.testops.planning
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.lib.BasePage

public class ReleasesPage extends BasePage<ReleasesPage> {

	def statusBar = { value -> return xpath("//div[text() = '${value}' and @class = 'counter__label']") }
	def statusChart = { value -> return xpath("//div[contains(@class, 'status-bar')]//div[text() = '${value}']") }
	def filterToolbar = { condition -> return xpath("//div[@class = 'filter']/button[./div[text() = '${condition}']]") }
	def tableHeaderTitle = { column -> return xpath("//table//th[text() = '${column}']") }

	public ReleasesPage navigateTo(String teamId, String projectId) {
		WebUI.navigateToUrl("${GlobalVariable.testOpsUrl}team/${teamId}/project/${projectId}/releases")
		return this
	}
	
	public ReleasesPage verifyReleasesTitleDisplayed () {
		WebUI.verifyElementVisible(title('Releases'))
		return this
	}

	public ReleasesPage verifyStatusBarDisplayed () {
		WebUI.verifyElementPresent(statusBar('Total Active'), 0)
		WebUI.verifyElementPresent(statusBar('Ready'), 0)
		WebUI.verifyElementPresent(statusBar('Not Ready'), 0)
		WebUI.verifyElementPresent(statusBar('Empty'), 0)
		return this
	}

	public ReleasesPage verifyStatusChartDisplayed () {
		WebUI.verifyElementPresent(statusChart('ready'), 0)
		WebUI.verifyElementPresent(statusChart('not ready'), 0)
		WebUI.verifyElementPresent(statusChart('empty'), 0)
		return this
	}

	public ReleasesPage verifySearchInputDisplayed() {
		WebUI.verifyElementPresent(id('search-input'),0)
		return this
	}
	
	public ReleasesPage clickSortFilter() {
		WebUI.click(xpath("//div[@class = 'filter-sort']//button[./div[text() = 'Sort'] and .//span[text() = 'Release Date (Ascending)']]"))
		return this
	}
	
	public ReleasesPage clickReleaseFilter() {
		WebUI.click(xpath("//div[@class = 'filter']/button[./div[text() = 'Release'] and .//span[text() = 'Active']]"))
		return this
	}
	
	public ReleasesPage clickStatusFilter() {
		WebUI.click(filterToolbar('Status: All'))
		return this
	}
	
	public ReleasesPage clickStartDateFilter() {
		WebUI.click(filterToolbar('Start Date: All'))
		return this
	}
	
	public ReleasesPage clickReleaseDateFilter() {
		WebUI.click(filterToolbar('Release Date: All'))
		return this
	}
	
	public ReleasesPage clickBuildFilter() {
		WebUI.click(filterToolbar('Build: All'))
		return this
	}
	

	public ReleasesPage verifyReleaseTableDisplayed() {
		WebUI.verifyElementPresent(tableHeaderTitle('Status'),0)
		WebUI.verifyElementPresent(tableHeaderTitle('Name'),0)
		WebUI.verifyElementPresent(tableHeaderTitle('Total Duration'),0)
		WebUI.verifyElementPresent(tableHeaderTitle('Test Runs'),0)
		WebUI.verifyElementPresent(tableHeaderTitle('Test Cases'),0)
		WebUI.verifyElementPresent(tableHeaderTitle('Start Date'),0)
		WebUI.verifyElementPresent(tableHeaderTitle('Release Date'),0)
		return this
	}

	public ReleasesPage clickCreateReleaseButton() {
		WebUI.click(title('Create Release'))
		return this
	}

}
