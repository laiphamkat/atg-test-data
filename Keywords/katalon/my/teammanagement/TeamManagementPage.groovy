package katalon.my.teammanagement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class TeamManagementPage extends BasePage<TeamManagementPage> {

	public TeamManagementPage verifyCreateBtnClickable() {
		WebUI.verifyElementPresent(id("create-team"), 5)
		WebUI.verifyElementClickable(id("create-team"))
		return this
	}
	
	public TeamManagementPage verifyNameFieldPresent(){
		WebUI.verifyElementPresent(id("team-name"), 5)
		return this
	}
	
	public TeamManagementPage verifyCreateTeamTitle(){
		WebUI.verifyElementPresent(text("Create Team"), 5)
		return this
	}
	
	public TeamManagementPage verifyTeamTablePresent() {
		WebUI.verifyElementPresent(xpath("//th[text()='Name']"), 5)
		WebUI.verifyElementPresent(xpath("//th[text()='Role']"), 5)
		return this
	}
}
