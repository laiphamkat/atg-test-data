package katalon.testops.report

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class SummaryByTestResultPage extends BasePage<SummaryByTestResultPage>{

	def uploadReportBtn = css("#main-header-tool-bar a")
	def refreshBtn = text("Refresh")
	def collapseBtn = css(".mr-1.btn.btn-link")
	def searchInput = id("search-input")
	def profileFilter = text("Profile: All")
	def testSuiteFilter = text("Test Suite: All")
	def testSuiteCollectionFilter = text("Test Suite Collection: All")


	public SummaryByTestResultPage verifyUploadReportButtonIsEnable() {
		WebUI.waitForElementClickable(uploadReportBtn, 15)
		WebUI.verifyElementClickable(uploadReportBtn)
		return this
	}

	public SummaryByTestResultPage verifyRefreshButtonIsEnable() {
		WebUI.waitForElementClickable(refreshBtn, 15)
		WebUI.verifyElementClickable(refreshBtn)
		return this
	}

	public SummaryByTestResultPage verifyCollapseButtonIsEnable() {
		WebUI.waitForElementClickable(collapseBtn, 15)
		WebUI.verifyElementClickable(collapseBtn)
		return this
	}

	public SummaryByTestResultPage verifySearchInputIsEnable() {
		WebUI.waitForElementPresent(searchInput, 15)
		WebUI.verifyElementVisible(searchInput)
		return this
	}

	public SummaryByTestResultPage verifyProfileFilterIsVisible() {
		WebUI.waitForElementPresent(profileFilter, 15)
		WebUI.verifyElementVisible(profileFilter)
		return this
	}

	public SummaryByTestResultPage verifyTestSuiteFilterIsVisible() {
		WebUI.waitForElementPresent(testSuiteFilter, 15)
		WebUI.verifyElementVisible(testSuiteFilter)
		return this
	}

	public SummaryByTestResultPage verifyTestSuiteCollectionFilterIsVisible() {
		WebUI.waitForElementPresent(testSuiteCollectionFilter, 15)
		WebUI.verifyElementVisible(testSuiteCollectionFilter)
		return this
	}
}
