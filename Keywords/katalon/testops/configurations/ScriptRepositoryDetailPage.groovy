package katalon.testops.configurations

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import katalon.fw.lib.BasePage

public class ScriptRepositoryDetailPage extends BasePage<ScriptRepositoryDetailPage> {

	public ScriptRepositoryDetailPage inputRepositoryURL(String value) {
		WebUI.sendKeys(id('repository'), value)
		return this
	}

	public ScriptRepositoryDetailPage inputUserName(String value) {
		WebUI.sendKeys(id('username'), value)
		return this
	}

	public ScriptRepositoryDetailPage inputPassword(String value) {
		WebUI.sendKeys(id('password'), value)
		return this
	}

	public ScriptRepositoryDetailPage clickConnect() {
		WebUI.click(btn('Connect'))
		return this
	}

	public ScriptRepositoryDetailPage clickCreate() {
		WebUI.click(btn('Create'))
		return this
	}
	
	public ScriptRepositoryDetailPage verifyScriptRepositoryDetailPageDisplayed() {
		WebUI.verifyElementVisible(title('Script Repository : cloudian-automation-1'))
		WebUI.verifyElementVisible(text('Description'))
		WebUI.verifyElementVisible(text('Repository URL'))
		WebUI.verifyElementVisible(text('Branch'))
		WebUI.verifyElementVisible(text('Username'))
		WebUI.verifyElementVisible(text('Test Suite Collection'))
		return this
	}
	
	public ScriptRepositoryDetailPage verifyScheduleTestRunButtonClickable() {
		WebUI.verifyElementClickable(title('Schedule Test Run'))
		return this
	}
	
	public ScriptRepositoryDetailPage verifyRefreshTestSuiteCollectionButtonClickable() {
		WebUI.verifyElementClickable(title('Refresh Test Suite Collection'))
		return this
	}
	
	public ScriptRepositoryDetailPage verifyEditButtonClickable() {
		WebUI.verifyElementClickable(title('Edit'))
		return this
	}
	
	public ScriptRepositoryDetailPage verifyDeleteButtonClickable() {
		WebUI.verifyElementClickable(title('Delete'))
		return this
	}
}
