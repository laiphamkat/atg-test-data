package katalon.testops.report

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class PlanningPage extends BasePage<PlanningPage>{

	public PlanningPage clickRequirementPage() {
		WebUI.click(title('Requirements'))
		return this
	}
}
