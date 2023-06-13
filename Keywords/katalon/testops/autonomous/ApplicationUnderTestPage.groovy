package katalon.testops.autonomous

import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.lib.BasePage


public class ApplicationUnderTestPage extends BasePage<ApplicationUnderTestPage> {
	def tableHeaderTitle = { column -> return xpath("//table//th[text() = '${column}']") }

	public ApplicationUnderTestPage navigateTo(String orgId) {
		WebUI.navigateToUrl("${GlobalVariable.adminUrl}organization/${orgId}/teams/autonomous-testing?")
		return this
	}

	public ApplicationUnderTestPage verifyAutonomousTestingTitleIsDisplayed() {
		WebUI.verifyElementPresent(xpath("//div[@class = 'object-title']//div[text() = 'Autonomous Testing']"), 10)
		return this
	}

	public ApplicationUnderTestPage verifyApplicationTestTabIsDisplayed() {
		WebUI.verifyElementPresent(xpath("//div[@class = 'object-tab-info']//a[text() = 'Application Under Test']"), 5)
		return this
	}
	
	public ApplicationUnderTestPage verifyAUTTitleIsDisplayed() {
		WebUI.verifyElementPresent(xpath('//div[@data-test-id="application-under-test-title"]'), 10)
		return this
	}
	
	public ApplicationUnderTestPage waitForAUTListingPageLoaded() {
		WebUI.waitForElementVisible(xpath('//div[@data-test-id="application-under-test-title"]'), 30)
		return this
	}


	public ApplicationUnderTestPage hoverOnInfomrationIcon() {
		WebUI.mouseOver(xpath("//div[@id = 'katalon-autonomous']//i"))
		return this
	}

	public ApplicationUnderTestPage verifyTooltipTestGenDisplayed() {
		String expectedInfo = 'Katalon generates autonomous test cases from actual user interactions with an application under test (AUT). You need to add a tracking code to your AUT to allow sending user interaction data to Katalon.'
		String actualInfo = WebUI.getText(xpath("//div[@role = 'tooltip']/div[@slot = 'small']"))
		WebUI.verifyEqual(expectedInfo, actualInfo.trim())
		return this
	}

	public ApplicationUnderTestPage clickAddAUTtBtnOnEmpty() {
		WebUI.click(xpath("//button[@data-test-id='add-aut-on-empty']"))
		return this
	}

	public ApplicationUnderTestPage verifyEmptyAUTListPageIsDisplayed() {
		WebUI.verifyElementVisible(text("You will find your list of Application Under Tests here"))
		return this
	}

	public ApplicationUnderTestPage verifyAUTTableHeader() {
		WebUI.verifyElementVisible(tableHeaderTitle('ID'))
		WebUI.verifyElementVisible(tableHeaderTitle('Name'))
		WebUI.verifyElementVisible(tableHeaderTitle('Activation Date'))
		WebUI.verifyElementVisible(tableHeaderTitle('Last Data Received Date'))
		return this
	}

	public ApplicationUnderTestPage verifyAUTTableNotEmpty() {
		List<WebElement> elements = WebUI.findWebElements(xpath("//tbody//tr"), 10)
		WebUI.verifyGreaterThanOrEqual(elements.size(), 1)
		return this
	}

	public ApplicationUnderTestPage clickToAUTIdAtRow(int row) {
		WebUI.click(xpath('//tbody//tr[${rowIndex}]//td[1]//a'))
		return this
	}
	
	public ApplicationUnderTestPage clickToAUTIdOfAUT(int autName) {
		WebUI.click(xpath("//table//tr[./td[text() = '${autName}']]//td[1]//a"))
		return this
	}

	public ApplicationUnderTestPage clickAddAUT() {
		WebUI.click(id('add-application-under-test'))
		return this
	}

	// Application Under Test Add popup form

	public ApplicationUnderTestPage verifyAddAUTFormTitle() {
		WebUI.verifyElementVisible(xpath("//form//h2[text() = 'Add Application Under Test']"))
		return this
	}

	public ApplicationUnderTestPage inputName(String name) {
		scrollToAndSendKeys(id("application-name"), name)
		return this
	}

	public ApplicationUnderTestPage verifyApplicationDomainsDescription() {
		String expectedDesc = "These are the domains that your application is deployed and operates. Only user interactions from these domains will be captured and leveraged in test case generation."
		String actualDesc = WebUI.getText(xpath("//label[text() = 'Application Domain(s)']/parent::div/p[contains(@class, 'text-description')]"))
		WebUI.verifyEqual(actualDesc, expectedDesc)
		return this
	}

	public ApplicationUnderTestPage inputApplicationDomains(List<String> lstDomains) {
		for (String domain: lstDomains) {
			WebUI.sendKeys(xpath("//label[text() = 'Application Domain(s)']/parent::div//input"), Keys.chord(domain, Keys.ENTER))
		}
		return this
	}

	public ApplicationUnderTestPage inputDescription(String description) {
		WebUI.setText(id("application-description"), description)
		return this
	}

	public ApplicationUnderTestPage clickAdd() {
		WebUI.click(id('button-add-application-under-test'))
		return this
	}

	public ApplicationUnderTestPage verifyAddButtonIsDisabled() {
		WebUI.verifyElementNotClickable(id('submit-create-aut'))
		return this
	}

	public ApplicationUnderTestPage verifyAddButtonIsClickabled() {
		WebUI.verifyElementClickable(id('submit-create-aut'))
		return this
	}

	public ApplicationUnderTestPage clickCancel() {
		WebUI.click(id('cancel-dialog'))
		return this
	}
}
