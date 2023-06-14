package katalon.testops.report

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class DefectsPage extends BasePage<DefectsPage>{

	public DefectsPage verifyDefectDemolIsVisible() {
		WebUI.switchToFrame(cls("defect-empty-state-media-iframe"), 30)
		WebUI.verifyElementVisible(id("__next"))
		WebUI.switchToDefaultContent()
		return this
	}

	public DefectsPage verifyDefectTitleIsVisible() {
		WebUI.verifyElementVisible(title("Defects"))
		return this
	}

	public DefectsPage verifyDefectLabelIsVisible(String label) {
		WebUI.verifyElementVisible(text("$label"))
		return this
	}

	public DefectsPage verifyChartSummaryIsVisible() {
		WebUI.verifyElementVisible(cls('defect-chart-summary-element'))
		return this
	}

	public DefectsPage verifyChartIsVisible() {
		WebUI.verifyElementVisible(css('.custom-overview-chart'))
		return this
	}

	public DefectsPage verifyDefectTableIsVisible() {
		WebUI.verifyElementVisible(cls('table-responsive'))
		return this
	}

	public DefectsPage verifyJiraIntegrationIsVisible() {
		WebUI.verifyElementVisible(text('Jira Integration'))
		return this
	}

	public DefectsPage verifyRefreshBtnIsClickable() {
		WebUI.verifyElementClickable(title('Refresh'))
		return this
	}

	public DefectsPage verifyPriorityFilterIsVisible() {
		WebUI.verifyElementVisible(id('priorityIn'))
		return this
	}

	public DefectsPage verifyReleaseFilterIsVisible() {
		WebUI.verifyElementVisible(id('ReleaseBuild'))
		return this
	}

	public DefectsPage verifyAssigneeFilterIsVisible() {
		WebUI.verifyElementVisible(id('assignee'))
		return this
	}

	public DefectsPage verifyChartTextItemIsVisible(String text) {
		WebUI.verifyElementVisible(xpath("//span[@class='recharts-legend-item-text' and text()='$text']"))
		return this
	}

	public DefectsPage verifyTableColumnNameIsVisible(String columnName) {
		WebUI.verifyElementVisible(xpath("//table[@class='table table-hover']//th['$columnName']"))
		return this
	}

	public DefectsPage verifySortDefaultValueIsVisible(String value) {
		WebUI.verifyElementVisible(xpath("//div[@class='filter-btn-content']//span[text()='$value']"))
		return this
	}


	public DefectsPage verifyExternalIssueIsShown(String externalIssueName) {
		WebUI.verifyElementVisible(text(externalIssueName))
		return this
	}

	public DefectsPage clickOnApplyButton() {
		WebUI.click(text('Apply'))
	}

	public DefectsPage clickOnClearButton() {
		WebUI.click(text('Clear'))
	}

	//======================================TIME RANGE========================================

	public DefectsPage verifyTimeRangeFilterIsVisible() {
		WebUI.verifyElementVisible(id('issueCreated'))
		return this
	}

	public DefectsPage clickOnTimeRange() {
		WebUI.click(css('#issueCreated button'))
	}

	public DefectsPage clickOnDailyOption() {
		WebUI.click(text('Daily'))
	}

	public DefectsPage clickOnWeeklyOption() {
		WebUI.click(text('Weekly'))
	}

	public DefectsPage clickOnMonthlyOption() {
		WebUI.click(text('Monthly'))
	}

	public DefectsPage clickOnQuaterlyOption() {
		WebUI.click(text('Quaterly'))
	}

	public DefectsPage verifyDailyOption() {
		verifyPast7DaysIsClickable()
		verifyPast30DaysIsClickable()
		verifyPast90DaysIsClickable()
		verifyPast12MonthsIsNotClickable()
		verifyCustomOptionIsClickable()
	}

	public DefectsPage verifyWeeklyOption() {
		verifyPast7DaysIsClickable()
		verifyPast30DaysIsClickable()
		verifyPast90DaysIsClickable()
		verifyPast12MonthsIsNotClickable()
		verifyCustomOptionIsClickable()
	}

	public DefectsPage verifyMonthlyOption() {
		verifyPast7DaysIsClickable()
		verifyPast30DaysIsClickable()
		verifyPast90DaysIsClickable()
		verifyPast12MonthsIsNotClickable()
		verifyCustomOptionIsClickable()
	}

	public DefectsPage verifyQuaterlyOption() {
		verifyPast7DaysIsClickable()
		verifyPast30DaysIsClickable()
		verifyPast90DaysIsClickable()
		verifyPast12MonthsIsNotClickable()
		verifyCustomOptionIsClickable()
	}

	public DefectsPage verifyPast7DaysIsClickable() {
		WebUI.verifyElementClickable(text('Past 7 days'))
	}

	public DefectsPage verifyPast7DaysIsNotClickable() {
		WebUI.verifyElementNotClickable(text('Past 7 days'))
	}

	public DefectsPage verifyPast30DaysIsClickable() {
		WebUI.verifyElementClickable(text('Past 30 days'))
	}

	public DefectsPage verifyPast30DaysIsnotClickable() {
		WebUI.verifyElementNotClickable(text('Past 30 days'))
	}

	public DefectsPage verifyPast90DaysIsClickable() {
		WebUI.verifyElementClickable(text('Past 90 days'))
	}

	public DefectsPage verifyPast90DaysIsNotClickable() {
		WebUI.verifyElementNotClickable(text('Past 90 days'))
	}

	public DefectsPage verifyPast12MonthsIsClickable() {
		WebUI.verifyElementClickable(text('Past 12 months'))
	}

	public DefectsPage verifyPast12MonthsIsNotClickable() {
		WebUI.verifyElementNotClickable(text('Past 12 months'))
	}

	public DefectsPage verifyCustomOptionIsClickable() {
		WebUI.verifyElementClickable(text('Custom'))
	}

	//======================================STATUS========================================
	public DefectsPage verifyStatusFilterIsVisible() {
		WebUI.verifyElementVisible(id('statusIn'))
		return this
	}

	public DefectsPage clickOnStatus() {
		WebUI.click(css('#statusIn button'))
	}


	public DefectsPage clickOnPriority() {
		WebUI.click(css('#priorityIn button'))
	}


	public DefectsPage clickOnRelease() {
		WebUI.click(css('#ReleaseBuild button'))
	}

	public DefectsPage clickOnAssignee() {
		WebUI.click(css('#assignee button'))
	}
}
