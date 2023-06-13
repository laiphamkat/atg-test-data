package katalon.testops.report

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class TestRunCoveragePage extends BasePage<TestRunCoveragePage>{

	private String noticeLabel = 'The quality of each requirement based on the status of the corresponding test result.'

	public TestRunCoveragePage verifyNoticeLabelIsVisible() {
		WebUI.verifyElementVisible(text(noticeLabel))
		return this
	}
	
	public TestRunCoveragePage verifyResultTableIsVisible() {
		WebUI.verifyElementVisible(cls("recharts-responsive-container"))
		return this
	}
	
	public TestRunCoveragePage verifyPassedFailedLabelIsVisible() {
		WebUI.verifyElementVisible(xpath("//div[contains(text(), 'Failed')]"))
		return this
	}

	public TestRunCoveragePage verifyRefreshButtonIsEnable() {
		WebUI.verifyElementClickable(title("Refresh"))
		return this
	}
}
