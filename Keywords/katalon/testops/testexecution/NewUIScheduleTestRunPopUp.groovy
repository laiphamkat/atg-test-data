package katalon.testops.testexecution
import java.util.regex.Matcher
import java.util.regex.Pattern

import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.lib.BasePage

public class NewUIScheduleTestRunPopUp extends BasePage<NewUIScheduleTestRunPopUp> {
	def platform = { value -> return xpath("//div[@role='button']//span[text()='${value}'] | //div[contains(@class, 'cascader-menus') and not(contains(@class, 'hidden'))]//li[@title='${value}']") }
	def browser = { value -> return xpath("//div[@role='button']//span[text()='${value}'] | //div[contains(@class, 'cascader-menus') and not(contains(@class, 'hidden'))]//li[@title='${value}']") }
	def version = { value -> return xpath("//div[@role='button' and text()='${value}'] | //div[contains(@class, 'cascader-menus') and not(contains(@class, 'hidden'))]//li[@title='${value}']") }
	def deviceType = { value -> return xpath("//div[@role='button']//span[text()='${value}'] | //div[contains(@class, 'cascader-menus') and not(contains(@class, 'hidden'))]//li[@title='${value}']") }
	def device = { value -> return xpath("//div[@role='button' and text()='${value}'] | //div[contains(@class, 'cascader-menus') and not(contains(@class, 'hidden'))]//li[@title='${value}']") }
	def dropdownOption = { value -> xpath("//div[contains(@id, 'option')]//div[. = '${value}'] | //div[contains(@id, 'option') and text()='${value}'] | //div[contains(@id, 'option') and contains(., '${value}')]") }
	def tscTCEnv = { rowIndex -> xpath("//div[@data-rowindex='${rowIndex - 1}']//div[@id='testCloudType']") }
	String localAgentElement = "(//input[@value='LOCAL_AGENT']/following::div[contains(@class, 'react-select-container')])[1]"
	String kubernetAgentElement = "(//input[@value='K8S_AGENT']/following::div[contains(@class, 'react-select-container')])[1]"
	String circleCIElement = "(//input[@value='CIRCLE_CI_AGENT']/following::div[contains(@class, 'react-select-container')])[1]"
	String invalidIntervalTime = 'Value must be between 1 and 999.'
	String invalidIntervalTimeMinutes = 'Value must be between 10 and 999.'

	TestObject moreOptionLink = xpath("//li[contains(text(), 'More options')]")
	TestObject advancedSettingsLink = id('go-to-advance-tab')
	TestObject profileDropdown = id('executionProfileList')
	TestObject nativeAppTab = btn('Mobile Native App')
	TestObject tscNativeAppDropdown = id('mobileNativeApp')
	TestObject supportAppInstruction = xpath("//small[text()='Currently we only support one app for all mobile environments.']")
	TestObject uploadAppInstruction = xpath("//small[.='Can’t find your preferred App from the list? Click here to upload app.']")
	TestObject listEnvAtTSC = xpath("//div[@class='chip-text-truncate']/following-sibling::span[@title]")
	
	public NewUIScheduleTestRunPopUp waitForModalCompleteLoading() {
		WebUI.waitForElementNotPresent(css('span.MuiCircularProgress-root'), 8, FailureHandling.OPTIONAL)
		return this
	}

	public NewUIScheduleTestRunPopUp inputTestRunName(String value) {
		clearTextAndSendKeysByActions(id('name'), value)
		return this
	}

	public NewUIScheduleTestRunPopUp clickScriptRepo() {
		WebUI.click(id('testProjectId'))
		return this
	}

	public NewUIScheduleTestRunPopUp selectScriptRepo(String value) {
		WebUI.click(text(value))
		return this
	}

	public NewUIScheduleTestRunPopUp clickTestSuiteTab() {
		WebUI.click(btn('Test Suite'))
		return this
	}

	public NewUIScheduleTestRunPopUp clickTSCTab() {
		WebUI.click(btn('Test Suite Collection'))
		return this
	}

	public NewUIScheduleTestRunPopUp clickKatalonCmdTab() {
		WebUI.click(btn('Katalon Command'))
		return this
	}

	public NewUIScheduleTestRunPopUp clickGenericCommandTab() {
		WebUI.click(btn('Generic Command'))
		return this
	}

	public NewUIScheduleTestRunPopUp clickTestSuiteObjectName() {
		WebUI.click(id('testSuiteId'))
		return this
	}

	public NewUIScheduleTestRunPopUp clickTSCObjectName() {
		WebUI.click(id('testSuiteCollectionId'))
		return this
	}

	public NewUIScheduleTestRunPopUp selectTestSuite(String value) {
		WebUI.click(text(value))
		return this
	}

	public NewUIScheduleTestRunPopUp selectTSC(String value) {
		WebUI.click(text(value))
		return this
	}

	public NewUIScheduleTestRunPopUp clickRefreshTS() {
		WebUI.click(xpath("(//a[contains(., 'refresh')])[1]"))
		return this
	}

	public NewUIScheduleTestRunPopUp clickRefreshTSC() {
		WebUI.click(xpath("(//a[contains(., 'refresh')])[2]"))
		return this
	}

	public NewUIScheduleTestRunPopUp clickTSEnvDropdown() {
		WebUI.click(xpath("(//label[@for='environment'])[1]/following-sibling::div"))
		return this
	}

	public NewUIScheduleTestRunPopUp clickKatalonCmdEnvDropdown() {
		WebUI.click(xpath("(//label[@for='environment'])[3]/following-sibling::div"))
		return this
	}

	public NewUIScheduleTestRunPopUp clickGenericCmdEnvDropdown() {
		WebUI.click(xpath("(//label[@for='environment'])[4]/following-sibling::div"))
		return this
	}

	public NewUIScheduleTestRunPopUp clickTSCConfigLink() {
		WebUI.click(text('Configure environments for Test Suites'))
		return this
	}

	public NewUIScheduleTestRunPopUp clickTSCTestCloudEnv(int rowIndex) {
		scrollToAndClick(tscTCEnv(rowIndex))
		return this
	}

	public NewUIScheduleTestRunPopUp selectSuggestedEnvironment(String os, String browser, String version) {
		WebUI.click(xpath("//div[contains(@id, 'option') and contains(., '${os} ${browser} ${version}')]"))
		return this
	}

	public NewUIScheduleTestRunPopUp verifySuggestedEnvironments(List<String> expectedEnvs) {
		List<WebElement> elements = WebUI.findWebElements(xpath("//li/following-sibling::div//div[contains(@class, 'option')]"), 10)
		List<String> suggestedEnvs = elements.collect {
			it.getText().replaceAll("[\\r|\\n|\\r\\n]", "").replaceAll(" +", " ").replaceAll(" Created with Sketch. Created with Sketch. ", " ") // On Safari, it will return Created with Sketch. 
		}
		WebUI.verifyEqual(suggestedEnvs, expectedEnvs)
		return this
	}

	public NewUIScheduleTestRunPopUp clickMoreOptions() {
		WebUI.click(moreOptionLink)
		return this
	}

	public NewUIScheduleTestRunPopUp verifyMoreOptionsNotDisplayed() {
		WebUI.verifyElementNotPresent(moreOptionLink, 3)
		return this
	}

	public NewUIScheduleTestRunPopUp clickTestCloudRadio() {
		WebUI.click(css("input[value='TEST_CLOUD_AGENT']"))
		return this
	}

	public NewUIScheduleTestRunPopUp clickLocalRadio() {
		WebUI.click(css("input[value='LOCAL_AGENT']"))
		return this
	}

	public NewUIScheduleTestRunPopUp clickKubernetesRadio() {
		WebUI.click(css("input[value='K8S_AGENT']"))
		return this
	}

	public NewUIScheduleTestRunPopUp clickCircleCIRadio() {
		WebUI.click(css("input[value='CIRCLE_CI_AGENT']"))
		return this
	}
	
	public NewUIScheduleTestRunPopUp verifyConfigTSWithTCTabs(List<String> expectedTabs) {
		List<WebElement> tabs =  WebUI.findWebElements(css('.test-environment-dialog-content button[role=tab]'),10)
		List<String> actual = tabs.collect() { it.getText() }
		WebUI.verifyEqual(actual, expectedTabs)
		return this
	}

	public NewUIScheduleTestRunPopUp clickMobileBrowsersTab() {
		WebUI.click(btn('Mobile Browsers'))
		return this
	}

	public NewUIScheduleTestRunPopUp clickMobileNativeAppTab() {
		WebUI.click(nativeAppTab)
		return this
	}

	public NewUIScheduleTestRunPopUp verifyMobileNativeAppTabNotDisplayed() {
		WebUI.verifyElementNotPresent(nativeAppTab, 5)
		return this
	}

	public NewUIScheduleTestRunPopUp clickDesktopBrowsersTab() {
		WebUI.click(btn('Desktop Browsers'))
		return this
	}

	public NewUIScheduleTestRunPopUp clickWebServicesTab() {
		WebUI.click(btn('Web Services'))
		return this
	}

	public NewUIScheduleTestRunPopUp clickProfile() {
		WebUI.click(profileDropdown)
		return this
	}

	public NewUIScheduleTestRunPopUp verifyProfileNotDisplayed() {
		WebUI.verifyElementNotPresent(profileDropdown, 3)
		return this
	}

	public NewUIScheduleTestRunPopUp selectProfile(String value) {
		scrollToAndClick(dropdownOption(value))
		return this
	}

	public NewUIScheduleTestRunPopUp clickTestCloudTunnelEnable() {
		WebUI.click(id('testcloud-tunnel-enable'))
		return this
	}

	public NewUIScheduleTestRunPopUp clickTSAppDropdown() {
		WebUI.click(id('mobileNative'))
		return this
	}

	public NewUIScheduleTestRunPopUp clickTSCAppDropdown() {
		WebUI.click(tscNativeAppDropdown)
		return this
	}

	public NewUIScheduleTestRunPopUp verifyTSCAppDropdownNotDisplayed() {
		WebUI.verifyElementNotPresent(tscNativeAppDropdown, 5)
		return this
	}

	public NewUIScheduleTestRunPopUp selectApp(String value) {
		scrollToAndClick(dropdownOption(value))
		return this
	}

	public NewUIScheduleTestRunPopUp verifyAppNotExist(String value) {
		WebUI.verifyTextNotPresent(value, false)
		return this
	}

	public NewUIScheduleTestRunPopUp verifyTSCNativeAppInstructionsDisplayed() {
		WebUI.verifyElementVisible(supportAppInstruction)
		WebUI.verifyElementVisible(uploadAppInstruction)
		return this
	}

	public NewUIScheduleTestRunPopUp verifyTSCNativeAppInstructionsNotDisplayed() {
		WebUI.verifyElementNotPresent(supportAppInstruction, 5)
		WebUI.verifyElementNotPresent(uploadAppInstruction, 5)
		return this
	}

	public NewUIScheduleTestRunPopUp selectPlatform(String value) {
		scrollToAndClick(platform(value))
		return this
	}

	public NewUIScheduleTestRunPopUp selectBrowser(String value) {
		scrollToAndClick(browser(value))
		return this
	}

	public NewUIScheduleTestRunPopUp selectVersion(String value) {
		scrollToAndClick(version(value))
		return this
	}

	public NewUIScheduleTestRunPopUp selectVersions(List<String> versions) {
		versions.each {
			selectVersion(it)
		}
		return this
	}

	public NewUIScheduleTestRunPopUp selectDeviceType(String value) {
		scrollToAndClick(deviceType(value))
		return this
	}

	public NewUIScheduleTestRunPopUp selectDevice(String value) {
		scrollToAndClick(device(value))
		return this
	}

	public NewUIScheduleTestRunPopUp clickLocalTestEnvDropdown() {
		WebUI.click(xpath(localAgentElement))
		return this
	}

	public NewUIScheduleTestRunPopUp selectLocalTestEnv(String value) {
		scrollToAndClick(dropdownOption(value))
		return this
	}

	public NewUIScheduleTestRunPopUp clickCircleCIEnvDropdown() {
		WebUI.click(xpath(circleCIElement))
		return this
	}

	public NewUIScheduleTestRunPopUp selectCircleCIEnv(String value) {
		scrollToAndClick(dropdownOption(value))
		return this
	}

	public NewUIScheduleTestRunPopUp clickKubernetesEnvDropdown() {
		WebUI.click(xpath(kubernetAgentElement))
		return this
	}

	public NewUIScheduleTestRunPopUp selectKubernetesEnv(String value) {
		scrollToAndClick(dropdownOption(value))
		return this
	}

	public NewUIScheduleTestRunPopUp clickLocalRunWithDropdown() {
		WebUI.click(xpath("//input[@value='LOCAL_AGENT']/following::*[@id='browserType']"))
		return this
	}

	public NewUIScheduleTestRunPopUp clickK8sRunWithDropdown() {
		WebUI.click(xpath("//input[@value='K8S_AGENT']/following::*[@id='browserType']"))
		return this
	}

	public NewUIScheduleTestRunPopUp clickCircleCIRunWithDropdown() {
		WebUI.click(xpath("//input[@value='CIRCLE_CI_AGENT']/following::*[@id='browserType']"))
		return this
	}

	public NewUIScheduleTestRunPopUp selectRunWith(String value) {
		scrollToAndClick(dropdownOption(value))
		return this
	}

	public NewUIScheduleTestRunPopUp clickBackMoreOptions() {
		WebUI.click(xpath("//div[./following-sibling::div[contains(text(),'Assign Test Environment')]]//button"))
		return this
	}

	public NewUIScheduleTestRunPopUp inputGenericCommand(String value) {
		WebUI.clearText(id('genericCommand'))
		WebUI.sendKeys(id('genericCommand'), value)
		return this
	}

	public NewUIScheduleTestRunPopUp clickRepeatEnable() {
		WebUI.click(id('repeat-enable'))
		return this
	}

	public NewUIScheduleTestRunPopUp inputStart(String time) {
		clearTextAndSendKeysByActions(id('startTime'), time)
		return this
	}

	public NewUIScheduleTestRunPopUp clearStartTime() {
		WebUI.clearText(id('startTime'))
		return this
	}

	public NewUIScheduleTestRunPopUp inputEnd(String time) {
		WebUI.setText(id('endTime'), time)
		return this
	}

	public NewUIScheduleTestRunPopUp inputIntervalTime(String interval) {
		WebUI.setText(id('interval'), interval)
		return this
	}

	public NewUIScheduleTestRunPopUp inputIntervalUnit(String intervalUnit) {
		WebUI.click(id('intervalUnit'))
		WebUI.scrollToElement(text(intervalUnit), 3)
		WebUI.click(text(intervalUnit))
		return this
	}

	public NewUIScheduleTestRunPopUp clickAdvancedSettings() {
		WebUI.click(advancedSettingsLink)
		return this
	}

	public NewUIScheduleTestRunPopUp verifyAdvancedSettingsNotDisplayed() {
		WebUI.verifyElementNotPresent(advancedSettingsLink, 3)
		return this
	}

	public NewUIScheduleTestRunPopUp clickVisualTesting() {
		WebUI.click(css("label[for='baselineCollection'] + div"))
		return this
	}

	public NewUIScheduleTestRunPopUp selectVisualTesting(String value) {
		WebUI.click(dropdownOption(value))
		return this
	}

	public NewUIScheduleTestRunPopUp clickKRE() {
		WebUI.click(css("label[for='ksVersion'] + div div.react-select__value-container"))
		return this
	}

	public NewUIScheduleTestRunPopUp clickKREVersionRadio() {
		WebUI.click(id("chooseKsVersion"))
		return this
	}

	public NewUIScheduleTestRunPopUp clickPreinstallKRERadio() {
		WebUI.click(id("chooseKsLocation"))
		return this
	}

	public NewUIScheduleTestRunPopUp inputPreinstallKRE(String location) {
		WebUI.setText(id("ksLocation"), location)
		return this
	}

	public NewUIScheduleTestRunPopUp selectKREVersion(String value) {
		WebUI.click(dropdownOption(value))
		return this
	}

	public NewUIScheduleTestRunPopUp clickExecutionMode() {
		WebUI.click(css("label[for='executionMode'] + div"))
		return this
	}

	public NewUIScheduleTestRunPopUp selectExecutionMode(String value) {
		WebUI.click(dropdownOption(value))
		return this
	}

	public NewUIScheduleTestRunPopUp clickKobitonToggle() {
		WebUI.click(id("kobiton-enable"))
		return this
	}

	public NewUIScheduleTestRunPopUp inputKobitonDeviceId(String value) {
		WebUI.setText(id("kobitonDeviceId"), value)
		return this
	}

	//TAGS
	public NewUIScheduleTestRunPopUp clickTagInputField() {
		WebUI.click(id("tagEntity"))
		return this
	}

	public NewUIScheduleTestRunPopUp createNewTag(String nameOfTag) {
		WebUI.sendKeys(id("tagEntity"), nameOfTag)
		return this
	}

	public NewUIScheduleTestRunPopUp searchTag(String nameOfTag) {
		WebUI.setText(id("tagEntity"), nameOfTag)
		return this
	}

	public NewUIScheduleTestRunPopUp searchExistingTagAndAssign(String nameOfTag) {
		searchTag(nameOfTag)
		scrollToAndClick(title(nameOfTag))
		return this
	}

	public NewUIScheduleTestRunPopUp verifyCreateNewTag(String nameOfTag) {
		WebUI.verifyElementVisible(title(nameOfTag + ' (new tag)'))
		return this
	}

	public NewUIScheduleTestRunPopUp assignTag(String nameOfTag) {
		WebUI.sendKeys(id("tagEntity"), Keys.chord(Keys.ENTER))
		return this
	}

	public NewUIScheduleTestRunPopUp unassignTag(String nameOfTag) {
		WebUI.click(xpath("(.//*[normalize-space(text()) and normalize-space(.)='${nameOfTag}'])[1]/following::*[name()='svg'][1]"))
		return this
	}

	public NewUIScheduleTestRunPopUp verifyTagIsVisible(String nameOfTag) {
		WebUI.verifyElementText(text(nameOfTag), nameOfTag)
		return this
	}

	public NewUIScheduleTestRunPopUp verifyTagNotPresent(String tag) {
		WebUI.verifyElementNotPresent(xpath("(//*[@title='${tag}']/ancestor::li[@role='option'])[1]"), GlobalVariable.smallSleepTime)
		return this
	}

	//CUSTOM FIELDS
	public NewUIScheduleTestRunPopUp verifyCustomFieldDisplay(String key, String value) {
		WebUI.verifyElementPresent(text(key), GlobalVariable.smallSleepTime)
		WebUI.verifyElementPresent(text(value), GlobalVariable.smallSleepTime)
		return this
	}

	public NewUIScheduleTestRunPopUp verifyCustomFieldDoesNotDisplay(def displayName, def value) {
		WebUI.verifyElementNotPresent(text(displayName), GlobalVariable.smallSleepTime)
		WebUI.verifyElementNotPresent(text(value), GlobalVariable.smallSleepTime)
		return this
	}
	public NewUIScheduleTestRunPopUp clickAddNewCustomField() {
		scrollToAndClick(text('Add new'))
		return this
	}

	public NewUIScheduleTestRunPopUp inputCustomFieldDisplayName(String displayName) {
		WebUI.click(txt('Display Name'))
		WebUI.sendKeys(txt('Display Name'), displayName)
		return this
	}

	public NewUIScheduleTestRunPopUp selectCustomFieldDisplayName(String displayName) {
		inputCustomFieldDisplayName(displayName)
		WebUI.click(option(displayName))
		return this
	}

	public NewUIScheduleTestRunPopUp selectCustomFieldDisplayValue(String value) {
		scrollToAndSendKeys(txt('Value'), value)
		WebUI.click(option(value))
		return this
	}

	public NewUIScheduleTestRunPopUp clickAssignCustomField() {
		WebUI.click(title('Add'))
		return this
	}

	public NewUIScheduleTestRunPopUp clickCancelAddCustomField() {
		WebUI.click(title('Cancel'))
		return this
	}

	public NewUIScheduleTestRunPopUp clickUnassignCustomField(String value) {
		WebUI.click(xpath("(.//*[normalize-space(text()) and normalize-space(.)='${value}'])[1]/following::*[name()='svg'][1]"))
		return this
	}

	public NewUIScheduleTestRunPopUp verifyDisplayNameNotPresent(String displayName) {
		WebUI.verifyElementNotPresent(xpath("(//*[@title='${displayName}']/ancestor::li[@role='option'])[1]"), GlobalVariable.smallSleepTime)
		return this
	}

	public NewUIScheduleTestRunPopUp verifyCustomFieldValueFieldIsDisabled() {
		WebUI.verifyElementHasAttribute(txt('Value'), 'disabled', GlobalVariable.smallSleepTime)
		return this
	}

	public NewUIScheduleTestRunPopUp verifyAssignCustomFieldButtonIsDisabled() {
		WebUI.verifyElementHasAttribute(title('Add'), 'disabled', GlobalVariable.smallSleepTime)
		return this
	}

	public NewUIScheduleTestRunPopUp clickRelease() {
		WebUI.click(id("releaseId"))
		return this
	}

	public NewUIScheduleTestRunPopUp selectRelease(String value) {
		WebUI.click(dropdownOption(value))
		return this
	}

	public NewUIScheduleTestRunPopUp inputTimeout(String value) {
		WebUI.setText(css("[name=timeOut]"), value)
		return this
	}

	public NewUIScheduleTestRunPopUp clickBackAdvancedSettings() {
		WebUI.click(xpath("//div[./following-sibling::div[text()='Schedule Test Run']]//button"))
		return this
	}

	public NewUIScheduleTestRunPopUp clickRun() {
		WebUI.click(id('customized-button'))
		return this
	}

	public NewUIScheduleTestRunPopUp clickSchedule() {
		WebUI.click(btn('Schedule'))
		return this
	}

	public NewUIScheduleTestRunPopUp clickScheduleBtnArrow() {
		WebUI.click(css('[data-testid="KeyboardArrowDownIcon"]'))
		return this
	}

	public NewUIScheduleTestRunPopUp clickRunOption() {
		WebUI.click(xpath("//li[. = 'Run']"))
		return this
	}

	public NewUIScheduleTestRunPopUp clickScheduleOption() {
		WebUI.click(xpath("//li[. = 'Schedule']"))
		return this
	}

	public NewUIScheduleTestRunPopUp clickSaveConfigOption() {
		WebUI.click(xpath("//li[. = 'Save Configuration']"))
		return this
	}

	public NewUIScheduleTestRunPopUp inputKatalonCmd(String value) {
		WebUI.setText(id('command'), value)
		return this
	}

	public NewUIScheduleTestRunPopUp verifyIntervalTime(String expectedText) {
		String text = WebUI.getText(xpath("//small[contains(text(),'Start on')]"))
		Pattern pattern = Pattern.compile("every \\d* \\w+\\(s\\)");
		Matcher matcher = pattern.matcher(text);
		Boolean found = matcher.find()
		WebUI.verifyMatch(matcher.group(), expectedText, false)
		return this
	}

	public NewUIScheduleTestRunPopUp verifyInvalidIntervalTime() {
		// To make sure message displayed before verify
		WebUI.delay(1)
		WebUI.verifyTextPresent(invalidIntervalTime, false)
		return this
	}

	public NewUIScheduleTestRunPopUp verifyInvalidIntervalTimeNotPresent() {
		// To make sure message displayed before verify
		WebUI.delay(1)
		WebUI.verifyTextNotPresent(invalidIntervalTime, false)
		return this
	}

	public NewUIScheduleTestRunPopUp verifyInvalidIntervalTimeMinutes() {
		// To make sure message displayed before verify
		WebUI.delay(1)
		WebUI.verifyTextPresent(invalidIntervalTimeMinutes, false)
		return this
	}

	public NewUIScheduleTestRunPopUp verifyInvalidIntervalTimeMinutesNotPresent() {
		// To make sure message displayed before verify
		WebUI.delay(1)
		WebUI.verifyTextNotPresent(invalidIntervalTimeMinutes, false)
		return this
	}

	public NewUIScheduleTestRunPopUp verifyRequiredMessageDisplayed() {
		// To make sure message displayed before verify
		WebUI.delay(1)
		WebUI.verifyTextPresent("This is required", false)
		return this
	}

	public NewUIScheduleTestRunPopUp clearIntervalTime() {
		WebUI.clearText(id('interval'))
		return this
	}

	public NewUIScheduleTestRunPopUp clickSaveEnv() {
		WebUI.click(id('save-configure-ts'))
		return this
	}

	public NewUIScheduleTestRunPopUp clickSaveTSCEnv() {
		WebUI.click(id('save-configure-tsc'))
		return this
	}

	public NewUIScheduleTestRunPopUp clickCancelEnv() {
		WebUI.click(id('close-configure-ts-dialog'))
		sleep(1) // Workaround for running test on Safari to make it not failed
		return this
	}

	public NewUIScheduleTestRunPopUp clickCancelTSCEnv() {
		WebUI.click(id('close-configure-tsc-dialog'))
		return this
	}

	public NewUIScheduleTestRunPopUp verifyEnvNotSelectedAtTSC() {
		WebUI.verifyElementText(xpath("(//div[@data-field='testCloudenv' and @role!='columnheader'])[1]"), 'Select TestCloud Environment')
		return this
	}

	public NewUIScheduleTestRunPopUp verifyAppNotSelectedAtTSC(String appName) {
		WebUI.verifyElementText(id('mobileNativeApp'), appName)
		return this
	}

	public NewUIScheduleTestRunPopUp verifyTestCloudTunnelDisable() {
		WebUI.verifyElementNotChecked(xpath("//input[@id ='testcloud-tunnel-enable']"), 10)
		return this
	}

	public NewUIScheduleTestRunPopUp verifyTSEnvDropdownNotSelected() {
		WebUI.verifyElementText(xpath("(//label[@for='environment'])[1]/following-sibling::div//div[contains(@class, 'placeholder')]"), 'Select environment')
		return this
	}

	public NewUIScheduleTestRunPopUp verifyTSCLocalAgentEnvIsNotSelected() {
		WebUI.verifyElementText(xpath(localAgentElement), 'Select environment')
		return this
	}

	public NewUIScheduleTestRunPopUp verifyTSCKubernetEnvIsNotSelected() {
		WebUI.verifyElementText(xpath(kubernetAgentElement), 'Select environment')
		return this
	}

	public NewUIScheduleTestRunPopUp verifyTSCCircleCIEnvIsNotSelected() {
		WebUI.verifyElementText(xpath(circleCIElement), 'Select environment')
		return this
	}

	public NewUIScheduleTestRunPopUp closeScheduleDialog() {
		WebUI.click(css('h2.schedule-header button'))
		return this
	}

	public NewUIScheduleTestRunPopUp hoverTSNameInTSCConfig(int index) {
		TestObject testSuiteName = xpath("(//span[contains(@aria-label, 'Test Suites')])[${index}]")
		WebUI.scrollToElement(testSuiteName, 5)
		WebUI.mouseOver(testSuiteName)
		return this
	}

	public NewUIScheduleTestRunPopUp hoverEnvironmentTooltip() {
		WebUI.mouseOver(css(".test-run-label + span svg"))
		return this
	}

	public NewUIScheduleTestRunPopUp verifyTextTooltip(String expectedText) {
		def actualText = WebUI.getText(xpath('//div[@role="tooltip"]/div'))
		WebUI.verifyEqual(actualText, expectedText)
		return this
	}

	public NewUIScheduleTestRunPopUp selectedAllTSs() {
		WebUI.click(css("input[aria-label='Select all rows']"))
		return this
	}

	public NewUIScheduleTestRunPopUp selectEnvForAllTSs() {
		WebUI.click(id("testCloudType"))
		return this
	}

	public NewUIScheduleTestRunPopUp verifyNumberOfEnvIsSelectedAtTSC(int numberOfTS) {
		List<WebElement> elements = WebUI.findWebElements(listEnvAtTSC,10)
		WebUI.verifyEqual(elements.size(), numberOfTS)
		return this
	}

	public NewUIScheduleTestRunPopUp verifyListEnvIsSelectedAtTSC(List<String> expectedEnvs) {
		List<WebElement> elements = WebUI.findWebElements(listEnvAtTSC,10)
		List<String> selectedEnv = elements.collect { it.getText().trim().replaceAll("\\s+", " ")}
		WebUI.verifyEqual(selectedEnv, expectedEnvs)
		return this
	}
    
	public NewUIScheduleTestRunPopUp verifyNumberOfRemainEnv(String expectedRemainNumber) {
		def actualResult = WebUI.getText(css("div[role='button'] span"))
		WebUI.verifyEqual(actualResult, expectedRemainNumber)
		return this
	}

	public NewUIScheduleTestRunPopUp clickRemoveAllTSEnv(){
		WebUI.click(xpath("//div[contains(@class, 'clear-indicator')]"))
		return this
	}

	public NewUIScheduleTestRunPopUp clickRemoveTSCEnv(){
		WebUI.click(css("svg[data-testid='ClearIcon']"))
		return this
	}

	public NewUIScheduleTestRunPopUp verifyNoTSCEnvSelected(){
		WebUI.verifyElementNotPresent(css("svg[data-testid='ClearIcon']"), 10)
		return this
	}

	public NewUIScheduleTestRunPopUp removeEnvAtTSTab(){
		WebUI.click(xpath("//div[contains(text(),'TestCloud') and not(@id='api-get-me')]/following-sibling::div"))
		return this
	}

	public NewUIScheduleTestRunPopUp verifyTestRunName(String expectedTestName) {
		def actualResult = WebUI.getAttribute(id('name'), 'value')
		WebUI.verifyEqual(actualResult, expectedTestName)
		return this
	}

	public NewUIScheduleTestRunPopUp verifyScriptRepo(String expectedScriptRepo){
		def actualResult = WebUI.getText(xpath("//div[@id ='testProjectId']//following-sibling::div//div[contains(@class, 'custom-select-value')]"))
		WebUI.verifyEqual(actualResult,expectedScriptRepo)
		return this
	}

	public NewUIScheduleTestRunPopUp verifyObjectNameAtTS(String expectedObjName){
		def actualResult = WebUI.getText(xpath("//div[@id ='testSuiteId']//following-sibling::div//div[contains(@class, 'custom-select-value')]"))
		WebUI.verifyEqual(actualResult,expectedObjName)
		return this
	}

	public NewUIScheduleTestRunPopUp verifyObjectNameAtTSC(String expectedObjName){
		def actualResult = WebUI.getText(xpath("//div[@id ='testSuiteCollectionId']//following-sibling::div//div[contains(@class, 'custom-select-value')]"))
		WebUI.verifyEqual(actualResult,expectedObjName)
		return this
	}
	
	public NewUIScheduleTestRunPopUp verifyEnvIsSelectedAtTS(String expectedEnvs){
		def getEnv = WebUI.getText(xpath("//label[@for='environment']/following-sibling::div//div[contains(@innerprops,'Object')]"))
		String actualResult = getEnv.trim().replaceAll("\\s+", " ")
		WebUI.verifyEqual(actualResult,expectedEnvs)
		return this
	}
  
}