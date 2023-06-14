package katalon.testops.report

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class RequirementPage extends BasePage<RequirementPage>{

	public RequirementPage clickTraceabilityMatrixTab() {
		WebUI.click(text('Traceability Matrix'))
		return this
	}

	public RequirementPage verifyLinkedExternalIssueIsShown(String externaleIssueName) {
		WebUI.verifyElementVisible(text(externaleIssueName))
		return this
	}
}
