package katalon.testops.dashboard

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage



public class NoProjectsPage extends BasePage<NoProjectsPage>  {
	
	public NoProjectsPage verifyThereAreNoProjectTitlePresent() {
		WebUI.verifyElementPresent(xpath("//div[@class='title' and text()='Currently, there are no projects in this organization.']"), 5)
		return this
	}
}
