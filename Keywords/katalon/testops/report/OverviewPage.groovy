package katalon.testops.report

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class OverviewPage extends BasePage<OverviewPage>{
	
	public OverviewPage verifyShowDataProfileIsVisible() {
		WebUI.verifyElementVisible(text("Showing data for"))
		return this
	}
	
	public OverviewPage verifyExecutionTrendIsVisible() {
		WebUI.verifyElementVisible(cls("overview-execution-trend"))
		return this
	}
	
	public OverviewPage verifyProfileCoverageIsVisible() {
		WebUI.verifyElementVisible(text("Profile Coverage"))
		return this
	}
	
	public OverviewPage verifyPlatformCoverageIsVisible() {
		WebUI.verifyElementVisible(text("Platform Coverage"))
		return this
	}
	
	public OverviewPage verifyUploadReportButtonIsEnable() {
		WebUI.verifyElementClickable(css("#main-header-tool-bar a"))
		return this
	}

	public OverviewPage verifyRefreshButtonIsEnable() {
		WebUI.verifyElementClickable(id("refresh-btn"))
		return this
	}
	
}
