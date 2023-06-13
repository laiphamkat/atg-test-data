package katalon.testops.autonomous

import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor

import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage


public class AppicationUnderTestDetailPage extends BasePage<AppicationUnderTestDetailPage>{

	def tableHeaderTitle = { table, column -> return xpath("//table[@data-test-id=${table}]//th[text() = '${column}']") }
	def inputLabel = { value -> return xpath("//label[text() = '${value}']/parent::div//input") }

	public AppicationUnderTestDetailPage clickBack() {
		WebUI.click(xpath("//button[@data-test-id='back']"))
		return this
	}

	public AppicationUnderTestDetailPage verifyAUTNameIsDisplayed(String name) {
		WebUI.verifyElementText(xpath("//div[@class = 'application-title']"), name)
		return this
	}


	public AppicationUnderTestDetailPage verifyEditAUTNameButtonIsClickabled() {
		WebUI.verifyElementClickable(xpath('//div[@data-testid="view-text"]//button'))
		return this
	}

	public AppicationUnderTestDetailPage verifyEditAUTNameNotEmpty() {
		String autName = WebUI.getText(xpath('//div[@data-test-id="aut-name"]'))
		WebUI.verifyGreaterThan(autName.length(), 1)
		return this
	}

	public AppicationUnderTestDetailPage verifyApplicationDomainTableHeader() {
		WebUI.verifyElementVisible(tableHeaderTitle('domains-table','Application Domain(s)'))
		WebUI.verifyElementVisible(tableHeaderTitle('domains-table','Activation Date'))
		WebUI.verifyElementVisible(tableHeaderTitle('domains-table','Last Data Received Date'))
		return this
	}

	public AppicationUnderTestDetailPage verifyApplicationDomainsTableNotEmpty() {
		List<WebElement> elements = WebUI.findWebElements(xpath("//table[@data-test-id='domains-table']//tbody//tr"), 10)
		WebUI.verifyGreaterThanOrEqual(elements.size(), 1)
		return this
	}

	public AppicationUnderTestDetailPage verifyApplicationDomainsTableIsEmpty() {
		WebUI.verifyElementVisible(xpath("//table[@data-test-id='domains-table']//tr/td[text()='No data to display.']"))
		return this
	}

	public AppicationUnderTestDetailPage clickToLearnMore() {
		WebUI.click(xpath("//a[text() = 'Learn More']"))
		return this
	}
	
	public AppicationUnderTestDetailPage verifyAUTNameDisplayed(String autName) {
		WebUI.verifyElementVisible(xpath("//div[@data-test-id='aut-name'][text() = '${autName}']"))
		return this
	}
	
	public AppicationUnderTestDetailPage verifyAUTDescriptionDisplayed(String description) {
		WebUI.verifyElementVisible(xpath("//div[@data-test-id='aut-description'][text() = '${description}']"))
		return this
	}

	// Installation Steps for Katalon AI

	public AppicationUnderTestDetailPage verifyInstallationStepsTitleDisplays() {
		WebUI.verifyElementVisible(xpath("//div[@data-test-id='install-steps'][text() = 'Installation Steps for Katalon AI']"))
		return this
	}

	public AppicationUnderTestDetailPage verifyInstallationStepsDescriptionsDisplays() {
		WebUI.verifyElementVisible(xpath("//div[@data-test-id='installation-description'][text() = 'Please complete following steps for Katalon AI to generate user journey map and test cases.']"))
		return this
	}

	public AppicationUnderTestDetailPage verifyActiveTraficAgentTitleDisplays() {
		WebUI.verifyElementVisible(xpath("//p[@data-test-id='activate-traffic-title'][text() = 'Activate Traffic Agent']"))
		return this
	}

	public AppicationUnderTestDetailPage verifyCodeSnipetGuidelineDisplays() {
		WebUI.verifyElementVisible(xpath('//div[@data-test-id="code-snippet-guideline"]//div[text() = "You need to add a tracking code to your AUT to allow sending user interaction data to Katalon. Simply copy & paste the snippet into the <HEAD> element into your application’s code."]'))
		WebUI.verifyElementVisible(xpath('//div[@data-test-id="code-snippet-guideline"]//div[text() = "When the code is built and deployed to the production environment, Katalon AI will discover and model user journeys and generate test cases for the top most-traffic flows. "]'))
		WebUI.verifyElementVisible(xpath('//div[@data-test-id="code-snippet-guideline"]//span[text() = "We will notify you via email when the new test cases are ready for review."]'))
		return this
	}

	public AppicationUnderTestDetailPage clickCopyCodeSnippet() {
		WebUI.click(xpath("//button[@data-test-id='copy-code-snippet']"))
		return this
	}

	public AppicationUnderTestDetailPage verifyCodeSnippeIsDisplayed() {
		String expectedCode = '<script id="katalonTrafficAgent" async defer src="https://static.qa.katalon.com/libs/traffic-agent/v1/traffic-agent.min.js" type="text/javascript"></script>\n'+
				'<script type="text/javascript">\n'+
				"    document.getElementById('katalonTrafficAgent').addEventListener('load', () => {\n"+
				'        startTrafficAgent("") });\n' +
				"</script>"

		String actualDesc = WebUI.getText(css("code"))
		WebUI.verifyEqual(actualDesc.replaceFirst(/KA(-\w+-\w+)/, "").trim(), expectedCode.trim())
		return this
	}


	public AppicationUnderTestDetailPage verifyCodeSnippetIsCoppied() {
		String expectedCode = WebUI.getText(css("code"))
		String actualCode = (String) Toolkit.getDefaultToolkit()
				.getSystemClipboard()
				.getData(DataFlavor.stringFlavor)
		WebUI.verifyEqual(actualCode, expectedCode)
		return this
	}

	// Add Test Environment

	public AppicationUnderTestDetailPage verifyAddTestEnvDescriptionsDisplays() {
		WebUI.verifyElementVisible(xpath("//div[@data-test-id='test-env-description'][text() = 'Specify where testing activities happen.']"))
		return this
	}

	public AppicationUnderTestDetailPage verifyAddTestEnvtTitleDisplays() {
		WebUI.verifyElementVisible(xpath("//p[@data-test-id='add-test-env-title'][text() = 'Add Test Environment(s)']"))
		return this
	}

	public AppicationUnderTestDetailPage clickAddTestEnvButton() {
		WebUI.click(xpath("//button[@data-test-id='add-env-button']"))
		return this
	}

	public AppicationUnderTestDetailPage verifyEnvTableHeader() {
		WebUI.verifyElementVisible(tableHeaderTitle('test-env-table','ID'))
		WebUI.verifyElementVisible(tableHeaderTitle('test-env-table','Environment Name'))
		WebUI.verifyElementVisible(tableHeaderTitle('test-env-table','Username'))
		return this
	}

	public AppicationUnderTestDetailPage verifyEnvTableNotEmpty() {
		List<WebElement> elements = WebUI.findWebElements(xpath("//table[@data-test-id='test-env-table']//tbody//tr"), 10)
		WebUI.verifyGreaterThanOrEqual(elements.size(), 1)
		return this
	}

	public AppicationUnderTestDetailPage verifyDefaultEnvironmentSign() {
		WebUI.verifyElementVisible(xpath("//table[@data-test-id='test-env-table']//tbody//tr//div[text() = 'DEFAULT']"))
		return this
	}

	public AppicationUnderTestDetailPage verifyEnvTableIsEmpty() {
		WebUI.verifyElementVisible(xpath("//table[@data-test-id='test-env-table']//tr/td[text()='No data to display.']"))
		return this
	}

	// Add Environment Form

	public AppicationUnderTestDetailPage verifyTestEnvFormTitle() {
		WebUI.verifyElementVisible(xpath("//form//h2[@data-test-id = 'env-header']//div[text() = 'Add Test Environment']"))
		return this
	}

	public AppicationUnderTestDetailPage inputEnvName(String name) {
		scrollToAndSendKeys(inputLabel("Environment Name"), name)
		return this
	}

	public AppicationUnderTestDetailPage inputEnvUrl(String url) {
		scrollToAndSendKeys(inputLabel("Environment URL"), url)
		return this
	}

	public AppicationUnderTestDetailPage inputUsername(String username) {
		scrollToAndSendKeys(inputLabel("Username"), username)
		return this
	}

	public AppicationUnderTestDetailPage inputPassword(String password) {
		scrollToAndSendKeys(inputLabel("Password"), password)
		return this
	}

	public AppicationUnderTestDetailPage verifyLoginCridentialInfoTextIsDisplayed() {
		WebUI.verifyElementVisible(xpath("//form//div[text() = 'The login credential will be used by Katalon AI to explore this environment.']"))
		return this
	}

	public AppicationUnderTestDetailPage clickPrivateSwitchBase() {
		WebUI.click(xpath("//div[contains(@class, 'MuiFormGroup')]//input[contains(@class, 'PrivateSwitchBase')]"))
		return this
	}

	public AppicationUnderTestDetailPage verifyTunnelDescription() {
		WebUI.verifyElementVisible(xpath("//div[contains(@class, 'MuiFormGroup')]//span[text() = 'This environment operates within my local network.']"))
		return this
	}

	public AppicationUnderTestDetailPage checkDefaultEnv() {
		WebUI.verifyElementVisible(xpath("//span[text() = 'Set as default test environment']//preceding-sibling::span//input"))
		return this
	}

	public AppicationUnderTestDetailPage verifySetDefaultEnvIsCheckedAndDisabled() {
		String isDisabled = WebUI.getAttribute(xpath("//span[@data-test-id='env-default-checkbox']"), 'aria-disabled')
		WebUI.verifyEqual(isDisabled, "true")
		WebUI.verifyElementChecked(xpath("//span[text() = 'Set as default test environment']//preceding-sibling::span//input"), 0)
		return this
	}

	public AppicationUnderTestDetailPage clickCancelAddEnv() {
		WebUI.click(id('cancel-dialog'))
		return this
	}

	public AppicationUnderTestDetailPage clickSaveEnv() {
		WebUI.click(id('submit-create-environment'))
		return this
	}

	public AppicationUnderTestDetailPage verifySaveEnvIsDisabled() {
		WebUI.verifyElementNotClickable(id('submit-create-environment'))
		return this
	}

	// Link Projects

	public AppicationUnderTestDetailPage verifyLinkProjectTitleIsDisplayed() {
		WebUI.verifyElementVisible(xpath("//p[@data-test-id='link-project-title'][text() = 'Link Project(s)']"))
		return this
	}

	public AppicationUnderTestDetailPage verifyLinkProjectDescriptionIsDisplayed() {
		WebUI.verifyElementVisible(xpath("//div[@data-test-id='project-env-description'][text() = 'Select TestOps projects for test execution, reports and analytics.']"))
		return this
	}

	public AppicationUnderTestDetailPage verifyLinkProjectTableHeader() {
		WebUI.verifyElementVisible(tableHeaderTitle('project-env-table','Project'))
		WebUI.verifyElementVisible(tableHeaderTitle('project-env-table','Script Repository'))
		return this
	}
	
	public AppicationUnderTestDetailPage verifyLinkProjectTableNotEmpty() {
		List<WebElement> elements = WebUI.findWebElements(xpath("//table[@data-test-id='project-env-table']//tbody//tr"), 10)
		WebUI.verifyGreaterThanOrEqual(elements.size(), 1)
		return this
	}

	public AppicationUnderTestDetailPage verifyLinkProjectTableIsEmpty() {
		WebUI.verifyElementVisible(xpath("//table[@data-test-id='project-env-table']//tbody//tr//td[text() = 'No data to display.']"))
		return this
	}

	public AppicationUnderTestDetailPage clickLinkProject() {
		WebUI.click(id('add-link-projects-on-header'))
		return this
	}

	public AppicationUnderTestDetailPage verifyLinkProjectButtonIsNotPresent() {
		WebUI.verifyElementNotPresent(id('add-link-projects-on-header'),0)
		return this
	}

	// Link project form

	public AppicationUnderTestDetailPage verifyLinkProjectFormTitleIsDisplayed() {
		WebUI.verifyElementVisible(xpath('//h2[@data-test-id="link-project-header"][text() = "Link Project(s)"]'))
		return this
	}

	public AppicationUnderTestDetailPage verifyProjectDropdownIsDisplayed() {
		WebUI.verifyElementVisible(xpath('//label[@data-test-id="link-project-title"][text() = "Project"]/parent::div//input[@placeholder = "Select a project"]'))
		return this
	}

	public AppicationUnderTestDetailPage verifyScriptRepoDropdownIsDisplayed() {
		WebUI.verifyElementVisible(xpath('//label[@data-test-id="link-repository-title"][text() = "Script repository"]/parent::div//input[@placeholder = "Select a repository in the project to store AI-generated test cases"]'))
		return this
	}

	public AppicationUnderTestDetailPage verifySampleScriptDescriptionDisplayed() {
		WebUI.verifyElementVisible(xpath('//p[@data-test-id="txt-description"][text() = "Please DO NOT select a sample repository"]'))
		return this
	}

	public AppicationUnderTestDetailPage verifySaveLinkProjectIsDisabled() {
		WebUI.verifyElementNotClickable(id('submit-link-projects'))
		return this
	}

	public AppicationUnderTestDetailPage clickCancelLinkProject() {
		WebUI.click(id('cancel-dialog'))
		return this
	}

}
