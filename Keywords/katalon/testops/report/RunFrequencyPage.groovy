package katalon.testops.report

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class RunFrequencyPage extends BasePage<RunFrequencyPage>{
 
	public RunFrequencyPage verifyNoticeLabelIsVisible() {
		WebUI.verifyElementVisible(text('How often tests are run and their daily performance.'))
		return this
	}
	
	public RunFrequencyPage verifyDateTimeIsVisible() {
		WebUI.verifyElementVisible(id("react-select-2"))
		return this
	}
	
	public RunFrequencyPage verifyPassedFailedLabelIsVisible() {
		WebUI.verifyElementVisible(xpath("//div[contains(text(), 'Failed')]"))
		return this
	}
	
	public RunFrequencyPage verifyRunFrequencyLabelIsVisible() {
		WebUI.verifyElementVisible(xpath("//div[@class='card-header-title' and text()='Run Frequency']"))
		return this
	}

	public RunFrequencyPage verifyUploadReportButtonIsEnable() {
		WebUI.verifyElementClickable(css("#main-header-tool-bar a"))
		return this
	}

	public RunFrequencyPage verifyCollapseButtonIsEnable() {
		WebUI.verifyElementClickable(css(".mr-1.btn.btn-link"))
		return this
	}

	public RunFrequencyPage verifyResultTableIsVisable() {
		WebUI.verifyElementVisible(text("Monday"))
		return this
	}
}
