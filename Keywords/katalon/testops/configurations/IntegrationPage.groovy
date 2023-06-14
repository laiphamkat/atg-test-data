package katalon.testops.configurations

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class IntegrationPage extends BasePage<IntegrationPage> {
	private List<String> integrationTypesOwnerRole = ['AWS Codebuild', 'Azure DevOps Pipelines', 'CLI', 'Circle CI', 'Docker', 'Github Action', 'Gitlab CI', 'Jasmine', 'Jest', 'Jira/Xray', 'Katalon Studio', 'Kobiton', 'Mocha', 'Protractor Jasmine', 'Protractor Mocha', 'Pytest', 'Robot Framework - Python', 'Slack']
	private List<String> integrationTypesUserRole = ['AWS Codebuild', 'Azure DevOps Pipelines', 'CLI', 'Circle CI', 'Docker', 'Github Action', 'Gitlab CI', 'Jasmine', 'Jest', 'Katalon Studio', 'Mocha', 'Protractor Jasmine', 'Protractor Mocha', 'Pytest', 'Robot Framework - Python']
	
	public IntegrationPage clickActiveButton() {
		WebUI.click(cls("custom-control-label"))
		return this
	}

	public IntegrationPage verifyIntegrationTypeIsClickable() {
		WebUI.verifyElementClickable(id("integrationType"))   
		return this
	}
	
	public IntegrationPage verifyIntergrationTypesList(String userRole) {
		WebUI.click(id('integrationType'))
		
		List<String> actualIntegrationTypes = new ArrayList<>()
		List<WebElement> elements = WebUI.findWebElements(css(".react-select__option"), 20)
		for (WebElement element in elements) {
			actualIntegrationTypes.add(element.getText())
		}
		
		if(userRole.toLowerCase() == 'user')
			assert integrationTypesUserRole.sort() == actualIntegrationTypes.sort()
		else
			assert integrationTypesOwnerRole.sort() == actualIntegrationTypes.sort()
		
		return this
	}
	
	
	public IntegrationPage verifyIntegrationTitle(String title) {
		WebUI.waitForElementVisible(cls("integration-type-title"), 30)
		WebUI.verifyElementVisible(cls("integration-type-title"))
		return this
	}
	
	public IntegrationPage verifyLabelIsCorrect(String label) {
		WebUI.verifyElementVisible(text("$label"))
		return this
	}
	
	public IntegrationPage verifyIntegrationTypeDefaultValue() {
		WebUI.getText(xpath("//div[contains(@class, 'singleValue') and text()='Katalon Studio']"))
		return this
	}
	
	public IntegrationPage selectIntegration(String value) {
		clearTextAndSendKeys(xpath('//div[@id="integrationType"]//input'), value + Keys.chord( Keys.ENTER))
		return this
	}

	public IntegrationPage inputUrl(String url) {
		WebUI.sendKeys(byName("serverUrl"), url)
		return this
	}

	public IntegrationPage inputUsername(String username) {
		WebUI.sendKeys(byName("username"), username)
		return this
	}

	public IntegrationPage inputPassword(String password) {
		WebUI.sendKeys(byName("password"), password)
		return this
	}

	public IntegrationPage clickTestConnectionBtn() {
		WebUI.click(xpath("//button[@data-trackid='test-jira-connection']"))
		return this
	}

	public IntegrationPage selectProject(String projectName) {
		WebUI.click(xpath("(//div[contains(@class, 'value-container')])[2]"))
		WebUI.click(xpath("//div[text()='$projectName']"))
		return this
	}

	public IntegrationPage clickSaveBtn() {
		WebUI.click(xpath("//button[@type='button' and (text()='Save' or .='Save')]"))
		return this
	}

	public IntegrationPage clickBackBtn() {
		WebUI.click(xpath("//button[text()='Back']"))
		return this
	}

	public IntegrationPage clickCreateWebhookBtn() {
		WebUI.click(id("create-new-webhook"))
		return this
	}


	//==================== VERIFICATION =======================//
	public IntegrationPage verifyCreateWebhookBtnIsDisable() {
		WebUI.verifyElementNotClickable(id('create-new-webhook'))
		return this
	}

	public IntegrationPage verifyCreateWebhookBtnIsEnable() {
		WebUI.verifyElementClickable(id('create-new-webhook'))
		return this
	}

	public IntegrationPage verifyCreateWebhookBtnIsHidden() {
		WebUI.verifyElementNotPresent(id('create-new-webhook'), 5)
		return this
	}

	public IntegrationPage verifyUrlTextBoxIsShown() {
		WebUI.verifyElementVisible(byName("serverUrl"))
		return this
	}

	public IntegrationPage verifyUrlTextBoxIsHidden() {
		WebUI.verifyElementNotPresent(byName("serverUrl"), 5)
		return this
	}

	public IntegrationPage verifySaveBtnIsHidden() {
		WebUI.verifyElementNotPresent(xpath("//button[@data-trackid='update-jira-settings']"), 5)
		return this
	}

	public IntegrationPage verifyCreateWebhookPopUpIsShown() {
		WebUI.verifyElementVisible(xpath("//div[text()='Create Webhook for Jira Integration']"))
		return this
	}

}
