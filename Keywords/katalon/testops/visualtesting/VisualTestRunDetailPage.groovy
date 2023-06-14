package katalon.testops.visualtesting

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage
import katalon.testops.planning.ReleasesPage

public class VisualTestRunDetailPage extends BasePage<VisualTestRunDetailPage> {
	
	def statusBar = { value -> return xpath("//div[text() = '${value}' and @class = 'counter__label']") }
	def statusChart = { value -> return xpath("//div[contains(@class, 'status-bar')]//div[text() = '${value}']") }
	def filterToolbar = { value -> return xpath("//div[@class = 'filter']/button[./div[text() = '${value}']]") }
	def filterStatus = { value -> return xpath("//div[@id='dropdown-filter']//li[.//span[text() = '${value}']]") }
	
	public VisualTestRunDetailPage verifyStatusBarDisplayed () {
		WebUI.verifyElementPresent(statusBar('Visual Checkpoints'), 0)
		WebUI.verifyElementPresent(statusBar('Failed'), 0)
		WebUI.verifyElementPresent(statusBar('Unresolved'), 0)
		return this
	}

	public VisualTestRunDetailPage verifyStatusChartDisplayed () {
		WebUI.verifyElementPresent(statusChart('Passed'), 0)
		WebUI.verifyElementPresent(statusChart('Failed'), 0)
		WebUI.verifyElementPresent(statusChart('Unresolved'), 0)
		return this
	}
	
	public VisualTestRunDetailPage verifyResultsTabIsActived () {
		WebUI.verifyElementPresent(xpath("//a[@aria-current='page'][text() = 'Results']"), 0)
		return this
	}
	
	
	public VisualTestRunDetailPage verifyCheckpointsSectionTitleIsDisplayed () {
		WebUI.verifyElementVisible(xpath("//div[@class='card-header-title'][text() = 'Checkpoints']"))
		return this
	}
	
	public VisualTestRunDetailPage verifySearchInputDisplayed() {
		WebUI.verifyElementVisible(id('search-input'))
		return this
	}
	
	public VisualTestRunDetailPage clickStatusFilter() {
		WebUI.click(xpath("//div[@class='filter-btn-content'][text() = 'Status']"))
		return this
	}
	
	public VisualTestRunDetailPage verifyStatusFilterListIsDisplayed() {
		WebUI.verifyElementVisible(filterStatus("Passed"))
		WebUI.verifyElementVisible(filterStatus("Failed"))
		WebUI.verifyElementVisible(filterStatus("Unresolved"))
		return this
	}
	
	public VisualTestRunDetailPage verifySaveToBaselineButtonIsDisabled() {
		WebUI.verifyElementNotClickable(title("Save to baseline"))
		return this
	}
	
	// Comments tab
	public VisualTestRunDetailPage clickToCommentsTab() {
		WebUI.click(xpath("//div[@class = 'page-tab']//a[text() = 'Comments']"))
		return this
	}
	
	public VisualTestRunDetailPage verifyCommentsTitleIsDisplayed() {
		WebUI.verifyElementVisible(xpath("//div[@class='card-header'][text() = 'Comments']"))
		return this
	}
	
	public VisualTestRunDetailPage verifySubmitButtonIsDisabled() {
		WebUI.verifyElementNotClickable(xpath("//button[text() = 'Submit']"))
		return this
	}
	
	public VisualTestRunDetailPage inputComment(String comment) {
		WebUI.sendKeys(xpath("//div[@class= 'CodeMirror-scroll']"), comment)
		return this
	}
}

