package katalon.common

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.lib.BasePage

public class HomePage extends BasePage<HomePage> {
	
	public HomePage navigateToOrgHome(String orgId) {
		WebUI.navigateToUrl("${GlobalVariable.testOpsUrl}organization/${orgId}/home")
		WebUI.waitForPageLoad(2)
		return this
	}

	public HomePage selectOrg(String orgName) {
		WebUI.click(title(orgName))
		return this
	}

	public HomePage selectProject(String projectName) {
		WebUI.click(title(projectName))
		return this
	}

	public HomePage selectRecentProject(String projectName) {
		WebUI.click(xpath("//div[@title='${projectName}' and @class='project-header']"))
		return this
	}

	public HomePage waitUntilPageDisplayed() {
		WebUI.waitForElementVisible(text('Recent Projects'), 30)
		return this
	}
}
