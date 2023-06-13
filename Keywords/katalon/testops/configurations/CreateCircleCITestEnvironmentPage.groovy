package katalon.testops.configurations

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class CreateCircleCITestEnvironmentPage extends BasePage<CreateCircleCITestEnvironmentPage> {
	def dropdownOption = { String option -> return xpath("//div[contains(@id, 'option') and text()='${option}']") }
	def followedProjectOption = { String option -> return xpath("//div[contains(@id, 'option') and contains(., '${option}')]") }
	TestObject createBtn = btn('Create')
	TestObject updateBtn = btn('Update')

	public CreateCircleCITestEnvironmentPage setAgentName(String name) {
		WebUI.setText(id('circle-ci-name'), name)
		return this
	}

	public CreateCircleCITestEnvironmentPage setURL(String url) {
		WebUI.setText(css("label[for='circle-ci-url'] ~ input"), url)
		return this
	}

	public CreateCircleCITestEnvironmentPage setPersonalAPIToken(String token) {
		WebUI.setEncryptedText(id('circle-ci-token'), token)
		return this
	}

	public CreateCircleCITestEnvironmentPage setAPIKey(String apiKey) {
		WebUI.setText(id('apiKey'), apiKey)
		return this
	}

	public CreateCircleCITestEnvironmentPage clickAPIKeyLink() {
		WebUI.click(css('a[href="/user/apikey"]'))
		return this
	}
	
	public CreateCircleCITestEnvironmentPage clickConnect() {
		WebUI.click(btn('Connect'))
		return this
	}

	public CreateCircleCITestEnvironmentPage clickFollowedProjectDropdown() {
		WebUI.click(css('[for="circle-ci-project"] ~ div'))
		return this
	}

	public CreateCircleCITestEnvironmentPage selectFollowedProject(String project) {
		WebUI.click(followedProjectOption(project))
		return this
	}

	public CreateCircleCITestEnvironmentPage setBranch(String branch) {
		WebUI.setText(id('circle-ci-branch'), branch)
		return this
	}

	public CreateCircleCITestEnvironmentPage clickCreate() {
		WebUI.click(createBtn)
		return this
	}

	public CreateCircleCITestEnvironmentPage verifyCreateDisplayed() {
		WebUI.verifyElementVisible(createBtn)
		return this
	}

	public CreateCircleCITestEnvironmentPage clickUpdate() {
		WebUI.click(updateBtn)
		return this
	}

	public CreateCircleCITestEnvironmentPage verifyUpdateDisplayed() {
		WebUI.verifyElementVisible(updateBtn)
		return this
	}
}
