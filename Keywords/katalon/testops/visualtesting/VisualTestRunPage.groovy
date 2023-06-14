package katalon.testops.visualtesting

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.lib.BasePage
import katalon.fw.lib.Page
import katalon.testops.services.LoginService

import org.openqa.selenium.WebElement

public class VisualTestRunPage extends BasePage<VisualTestRunPage> {
	def LoginService loginAPI = new LoginService()
	def email = internal.GlobalVariable.vst_pro_email
	def password = internal.GlobalVariable.vst_pro_password
	def pageLocators = [ TITLE_BASELINECOLLECTIONTAB: 'Visual Baseline Collections',
		TITLE_VISUALTESTRUNTAB: 'Visual Test Runs',
		TITLE_VISUALTESTING: 'Visual Testing',
		LABEL_VISUALTESTING_PRO: '//span[contains(@class,"badge-pro")]',
		TABLE_VISUALTESTING: '//div[@class="card-body"]'
	]

	public VisualTestRunPage accessDetailsVSTProject(String teamId, String projectId) {
		String token = internal.GlobalVariable.encodedToken
		String vstUrl = internal.GlobalVariable.testOpsUrl + 'team/' + teamId + '/project/' + projectId + '?katone_access_token=' + token

		WebUI.navigateToUrl(vstUrl)
		WebUI.delay(5)
		return this
	}

	public VisualTestRunDetail accessDetailsTestRunById(int id) {
		String detailsTestRun = WebUI.getUrl();
		WebUI.navigateToUrl(detailsTestRun + "/${id}")
		return Page.nav(VisualTestRunDetail)
	}


	public VisualTestRunPage verifyDisableDropDown() {
		TestObject element = id('react-select-2')
		String c = WebUI.getAttribute(element, 'class')
		WebUI.verifyEqual(c.contains('react-select--is-disabled'), true)
		return this
	}

	public VisualTestRunPage navOrganization(String urlOrganization) {
		WebUI.navigateToUrl(urlOrganization)
		return this
	}

	public VisualTestRunPage selectTab(String xpathTab) {
		WebUI.click(xpath(xpathTab))
		return this
	}

	public VisualTestRunPage selectVerticalEllipsis(String xpathEllipsis) {
		WebUI.click(xpath(xpathEllipsis))
		return this
	}

	public VisualTestRunPage selectTestOpsModule(String nameModule) {
		WebUI.click(title(nameModule))
		return this
	}

	public VisualTestRunPage selectVisualTestingModule() {
		WebUI.click(title(pageLocators['TITLE_VISUALTESTING']))
		return this
	}

	public VisualTestRunPage selectVisualRunsTab() {
		WebUI.click(title(pageLocators['TITLE_VISUALTESTRUNTAB']))
		return this
	}

	public VisualTestRunPage selectVisualTestRun(String stringCSS) {
		WebUI.click(css(stringCSS))
		return this
	}

	public VisualBaselineCollectionPage selectVisualBaselineCollectionsTab() {
		click(title(pageLocators['TITLE_BASELINECOLLECTIONTAB']))
		return Page.nav(VisualBaselineCollectionPage)
	}

	public VisualTestRunPage selectThumbnailImage(String cssThumnailImage) {
		WebUI.click(css(cssThumnailImage))
		return this
	}

	public VisualTestRunPage clickMismatchImage() {
		click(xpath(pageLocators['xpath_mismatch_thumbnails']))
		WebUI.delay(20)
		return this
	}

	public VisualTestRunPage isVSTProlabelDisplay(boolean expectedResult) {
		def actualResult = WebUI.verifyElementPresent(xpath(pageLocators['LABEL_VISUALTESTING_PRO']),0, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(actualResult, expectedResult)
		return this
	}

	public VisualTestRunPage isVisualTestDashboardDisplay(boolean expectedResult) {
		boolean actualResult = WebUI.verifyElementPresent(xpath(pageLocators['TABLE_VISUALTESTING']),0, FailureHandling.CONTINUE_ON_FAILURE)
		assert actualResult == expectedResult
		return this
	}

	// All above code will be revamped later

	def tableHeaderTitle = { column -> return xpath("//table//th//*[text() = '${column}']") }

	public VisualTestRunPage navigateTo(String teamId, String projectId) {
		WebUI.navigateToUrl("${GlobalVariable.testOpsUrl}team/${teamId}/project/${projectId}/katalon-eyes")
		return this
	}

	public VisualTestRunPage verifyVisualTestRunsTabIsActived() {
		WebUI.waitForElementVisible(xpath("//a[contains(@class, 'active')][@title='Visual Test Runs']"), 10, FailureHandling.STOP_ON_FAILURE)
		return this
	}

	public VisualTestRunPage verifyVisualTestRunTitleDisplayed() {
		WebUI.verifyElementVisible(xpath("//div[@title = 'Visual Test Runs']"))
		return this
	}


	public VisualTestRunPage verifyVisualTestingWelcomeTextIsDisplayed() {
		WebUI.verifyElementVisible(xpath("//div[@class= 'visual-testing-welcome']//h3[text() = 'Welcome to Katalon Visual Testing!']"))
		WebUI.verifyElementVisible(xpath("//div[@class= 'visual-testing-welcome']//p[text() = 'Easily compare images captured during test executions']"))
		return this
	}

	public VisualTestRunPage clickSetupVisualTestingButton() {
		WebUI.click(xpath("//a[contains(@class, 'button-setup')]"))
		return this
	}

	public VisualTestRunPage clickLearnMoreButton() {
		WebUI.click(xpath("//a[contains(@class, 'button-learn-more')]"))
		return this
	}

	public VisualTestRunPage clickVisualBaselineCollectionsTab() {
		WebUI.click(title("Visual Baseline Collections"))
		return this
	}

	public VisualTestRunPage verifyVisualTestRunableHeaderDisplayed() {
		WebUI.verifyElementPresent(tableHeaderTitle('Status'),0)
		WebUI.verifyElementPresent(tableHeaderTitle('ID'),0)
		WebUI.verifyElementPresent(tableHeaderTitle('Name'),0)
		WebUI.verifyElementPresent(tableHeaderTitle('Baseline Collection'),0)
		WebUI.verifyElementPresent(tableHeaderTitle('P'),0) //Passed
		WebUI.verifyElementPresent(tableHeaderTitle('F'),0) //Failed
		WebUI.verifyElementPresent(tableHeaderTitle('U'),0) //Unresolved
		WebUI.verifyElementPresent(tableHeaderTitle('Time'),0)
		WebUI.verifyElementPresent(tableHeaderTitle('By'),0)
		return this
	}

	public VisualTestRunPage verifyTestRunTableNotEmpty() {
		List<WebElement> elements = WebUI.findWebElements(xpath("//tbody//tr"), 10)
		WebUI.verifyGreaterThanOrEqual(elements.size(), 1)
		return this
	}

	public VisualTestRunPage clickToMoreOptionAtRow(int rowIndex) {
		WebUI.click(xpath("//tbody//tr[${rowIndex}]//td[last()]"))
		return this
	}

	public VisualTestRunPage verifyReImportOptionIsClickabled(int rowIndex) {
		WebUI.verifyElementClickable(xpath("//tbody//tr[${rowIndex}]//td[last()]//button[@title = 'Re-import']"))
		this
	}

	public VisualTestRunPage clickToTestRunIdAtRow(int rowIndex) {
		WebUI.click(xpath("//tbody//tr[${rowIndex}]//td[2]//a"))
		return this
	}
	
	public VisualTestRunPage verifyProIconIsDisplayed() {
		WebUI.verifyElementVisible(xpath("//div[contains(@class, 'visual-testing-header')]//span[text() = 'Pro']"))
		return this
	}
	
	public VisualTestRunPage verifyProIconIsNotsDisplayed() {
		WebUI.verifyElementNotPresent(xpath("//div[contains(@class, 'visual-testing-header')]//span[text() = 'Pro']"),3)
		return this
	}


}