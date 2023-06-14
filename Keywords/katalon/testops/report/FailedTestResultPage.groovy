package katalon.testops.report

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class FailedTestResultPage extends BasePage<FailedTestResultPage>{

	public FailedTestResultPage verifyFailedTestResultLabelIsVisible() {
		WebUI.verifyElementVisible(xpath("//div[@class='failed-test-result-header-title' and text()='Failed Test Results']"))
		return this
	}
	
	public FailedTestResultPage verifyTop10FailuretLabelIsVisible() {
		def element = xpath("//div[@class='failed-test-result-header-title' and text()='Top 10 Exceptions from all Test Results']")
		WebUI.verifyElementVisible(element)
		return this
	}

	public FailedTestResultPage verifyUploadReportButtonIsEnable() {
		WebUI.verifyElementClickable(css("#main-header-tool-bar a"))
		return this
	}
	
	public FailedTestResultPage verifyRefreshButtonIsEnable() {
		WebUI.verifyElementClickable(text("Refresh"))
		return this
	}
}
