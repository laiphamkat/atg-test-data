package katalon.testops.report

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class TraceabilityMatrixPage extends BasePage<TraceabilityMatrixPage>{

	private String noticeLabel = 'Manage the relationship across requirements, test cases, defects.'

	public TraceabilityMatrixPage verifyNoticeLabelIsVisible() {
		WebUI.verifyElementVisible(text(noticeLabel))
		return this
	}

	public TraceabilityMatrixPage verifyResultTableIsVisible() {
		WebUI.verifyElementVisible(cls("traceability table"))
		return this
	}

	public TraceabilityMatrixPage verifyTableColumnIsVisible(String columnName) {
		WebUI.verifyElementVisible(xpath("//table[@class='traceability table']//th[text()='$columnName']"))
		return this
	}

	public TraceabilityMatrixPage verifyRefreshButtonIsEnable() {
		WebUI.verifyElementClickable(title("Refresh"))
		return this
	}
}
