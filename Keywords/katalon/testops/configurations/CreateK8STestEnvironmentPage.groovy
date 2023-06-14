package katalon.testops.configurations

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class CreateK8STestEnvironmentPage extends BasePage<CreateK8STestEnvironmentPage> {
	def dropdownOption = { String option -> return xpath("//div[contains(@id, 'option') and text()='${option}']") }
	TestObject createBtn = btn('Create')
	TestObject updateBtn = btn('Update')
	
	CreateK8STestEnvironmentPage setAgentName(String name) {
		WebUI.setText(id('k8s-name'), name)
		return this
	}
	
	CreateK8STestEnvironmentPage setURL(String url) {
		WebUI.setText(id('k8s-url'), url)
		return this
	}
	
	CreateK8STestEnvironmentPage setCertAuthority(String cert) {
		WebUI.setText(id('k8s-certificate-authority'), cert)
		return this
	}
	
	CreateK8STestEnvironmentPage setNameSpace(String namespace) {
		WebUI.setText(id('k8s-namespace'), namespace)
		return this
	}
	
	CreateK8STestEnvironmentPage setAPIKey(String apiKey) {
		WebUI.setText(id('apiKey'), apiKey)
		return this
	}

	public CreateK8STestEnvironmentPage clickAPIKeyLink() {
		WebUI.click(css('a[href="/user/apikey"]'))
		return this
	}

	public CreateK8STestEnvironmentPage clickAuthenticationTypeDropdown() {
		WebUI.click(css('[for="authenticationType"] ~ div'))
		return this
	}

	public CreateK8STestEnvironmentPage selectAuthenticationType(String type) {
		WebUI.click(dropdownOption(type))
		return this
	}
	
	CreateK8STestEnvironmentPage setUsername(String username) {
		WebUI.setText(id('k8s-username'), username)
		return this
	}
	
	CreateK8STestEnvironmentPage setPwd(String pwd) {
		WebUI.setText(id('k8s-password'), pwd)
		return this
	}
	
	CreateK8STestEnvironmentPage setToken(String token) {
		WebUI.setText(id('k8s-token'), token)
		return this
	}
	
	CreateK8STestEnvironmentPage setRegion(String region) {
		WebUI.setText(id('eks-region'), region)
		return this
	}
	
	CreateK8STestEnvironmentPage setCluster(String cluster) {
		WebUI.setText(id('eks-cluster'), cluster)
		return this
	}
	
	CreateK8STestEnvironmentPage setAccessKey(String key) {
		WebUI.setEncryptedText(id('eks-access-key'), key)
		return this
	}
	
	CreateK8STestEnvironmentPage setPrivateAccessKey(String key) {
		WebUI.setEncryptedText(id('eks-private-access-key'), key)
		return this
	}

	public CreateK8STestEnvironmentPage clickTestConnection() {
		WebUI.click(btn('Test Connection'))
		return this
	}

	public CreateK8STestEnvironmentPage clickCreate() {
		WebUI.click(createBtn)
		return this
	}

	public CreateK8STestEnvironmentPage verifyCreateDisplayed() {
		WebUI.verifyElementVisible(createBtn)
		return this
	}

	public CreateK8STestEnvironmentPage clickUpdate() {
		WebUI.click(updateBtn)
		return this
	}

	public CreateK8STestEnvironmentPage verifyUpdateDisplayed() {
		WebUI.verifyElementVisible(updateBtn)
		return this
	}
	
	public CreateK8STestEnvironmentPage verifySuccessMessageDisplayed() {
		TestObject successMesssage = css('.toast-success')
		waitUntilElementVisibleWithWebDriverWait(successMesssage, 10)
		WebUI.verifyElementVisible(successMesssage)
		return this
	}	
}
