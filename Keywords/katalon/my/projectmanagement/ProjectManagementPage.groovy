package katalon.my.projectmanagement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class ProjectManagementPage extends BasePage<ProjectManagementPage> {

	public ProjectManagementPage clickActionButtonOfProject(int orderNum=1) {
		WebUI.click(xpath("//tbody/tr[$orderNum]/td[3]//button[1]"))
		return this
	}

	public ProjectManagementPage clickEditProjectName() {
		WebUI.click(text("Edit project name"))
		return this
	}

	public ProjectManagementPage verifyProjectName(String expectedName, int orderNum=1)  {
		WebUI.verifyElementText(xpath("//tbody/tr[$orderNum]/td[1]"), expectedName)
		return this
	}
	
	public ProjectManagementPage verifyCreateProjectBtnClickable() {
		WebUI.verifyElementPresent(btn("Create Project"), 5)
		WebUI.verifyElementClickable(btn("Create Project"))
		return this
	}
	
	public ProjectManagementPage verifyProjectTitle() {
		WebUI.verifyElementPresent(title("Projects"), 5)
		return this
	}
	
	public ProjectManagementPage verifyProjectTablePresent() {
		WebUI.verifyElementPresent(xpath("//th[text()='Name']"), 5)
		WebUI.verifyElementPresent(xpath("//th[text()='Team']"), 5)
		return this
	}
}
