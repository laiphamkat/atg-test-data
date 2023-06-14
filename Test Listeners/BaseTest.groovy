import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.common.SignInPage
import katalon.fw.db.PostgreSql
import katalon.fw.lib.Page
import katalon.utility.DateTimeUtility


class BaseTest {
	/**
	 * Executes before every test case starts.
	 * @param testCaseContext related information of the executed test case.
	 */
	boolean checkPass(TestCaseContext testCaseContext) {
		return testCaseContext.getTestCaseStatus().equalsIgnoreCase("PASSED")
	}

	@BeforeTestSuite
	def setTSInfo(TestSuiteContext testSuiteContext) {
		GlobalVariable.tsStartTime = new DateTimeUtility().getCurrentDateTime('dd/MM/yyyy HH:mm:ss')
		GlobalVariable.tsName = testSuiteContext.getTestSuiteId()
	}

	@BeforeTestCase
	def setTCInfo(TestCaseContext testCaseContext) {
		GlobalVariable.tcStartTime = new DateTimeUtility().getCurrentDateTime('dd/MM/yyyy HH:mm:ss')
		GlobalVariable.tcName = testCaseContext.getTestCaseId()
	}

	@BeforeTestCase
	def openBrowser(TestCaseContext testCaseContext) {
		if (!GlobalVariable.isAPIRunning){
			WebUI.openBrowser(null)
			WebUI.maximizeWindow()
			// Temporarily workaround to resolve the issue below:
			// 		Pre-condition:
			//		- a TS with correct order up to down: 1 MC, 1 AC
			//		- Windows machine
			//
			// 		Steps:
			//		1. Execute the TS on Windows machine
			//		2. Observe result
			//
			//		Actual:
			//		- The AC will fail with error 'java.lang.NoClassDefFoundError: could not initialize class katalon.fw.lib.Page'
			//		- As investigation, this only occurs when the first test in the TS which does not have any step to initiate the Page class
			//		- It works fine on macOS
			//		- Have not known the real root-cause yet, will need to investigate more later
			Page.nav(SignInPage)
		}
	}

	@AfterTestCase
	def closeBrowser(TestCaseContext testCaseContext) {
		if (!GlobalVariable.isAPIRunning) {
			if (!checkPass(testCaseContext)) {
				WebUI.takeScreenshot()
			}
			WebUI.closeBrowser()
		}
	}

	@AfterTestCase
	def closeConnection() {
		Page.nav(PostgreSql).closeConnection();
	}
}