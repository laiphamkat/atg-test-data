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


public class ScriptRepositoryPage extends BasePage<ScriptRepositoryPage> {

	public ScriptRepositoryPage clickCreateGitScriptRepository() {
		WebUI.click(btn('Create Git Script Repository'))
		return this
	}

	public ScriptRepositoryPage clickCreateSampleScriptRepository() {
		WebUI.click(btn('Create Sample Script Repository'))
		return this
	}

	public ScriptRepositoryPage verifyScriptRepositoriesTitle() {
		WebUI.verifyElementVisible(title('Script Repositories'))
		return this
	}

	public ScriptRepositoryPage verifyConnectGitRepositoryButtonClickable() {
		WebUI.verifyElementClickable(title('Connect Git Repository'))
		return this
	}

	public ScriptRepositoryPage verifyFilterByTypeClickable() {
		WebUI.verifyElementClickable(text('Type: All'))
		return this
	}

	public ScriptRepositoryPage verifyFilterByNameInputTextDisplayed() {
		WebUI.verifyElementVisible(id('search-input'))
		return this
	}

	public ScriptRepositoryPage verifyScriptRepoTableDisplayed() {
		WebUI.verifyElementVisible(text('Name'))
		WebUI.verifyElementVisible(text('Description'))
		WebUI.verifyElementVisible(text('Type'))
		return this
	}

	public ScriptRepositoryPage clickScriptRepoName() {
		WebUI.click(text('cloudian-automation-1'))
		return this
	}
}
