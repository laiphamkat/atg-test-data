import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.constants.BrowserVersion
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.testexecution.NewUIScheduleTestRunPopUp
import katalon.testops.testexecution.TestRunCalendarPage
import katalon.testops.testexecution.TestRunListPage
import katalon.testops.testexecution.TestRunsPage
import katalon.utility.DateTimeUtility as DateTimeUtility

'Pre-condition: Find a user to log in'
Credential user = Page.nav(Credential)
	.getCredentials()
	.withRole(role)
	.getFirst()

'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential(user.email, user.pwd).clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()

'User navigates to Test Run Calendar page'
Page.nav(TestRunCalendarPage).navigateTo(user.teamId, user.projectId)

'User click Schedule Test Run button'
Page.nav(TestRunCalendarPage).clickScheduleTestRun()

'User starts inputting the information on the Schedule test run popup'
Page.nav(NewUIScheduleTestRunPopUp)
	.inputTestRunName("edit-the-test-execution-${unique}")
	.clickScriptRepo()
	.selectScriptRepo('cloudian-automation-1')
	.clickTestSuiteObjectName()
	.selectTestSuite('Test Suites/Web App/Test Suites/Public/WA TS with 1 test passed')
	.clickTSEnvDropdown()
	.selectSuggestedEnvironment('Linux', 'Chrome', BrowserVersion.CHROME_LINUX_LATEST)
	.clickRepeatEnable()
	.clickScheduleBtnArrow()
	.clickSaveConfigOption()

'User filters test run by name'
Page.nav(TestRunListPage).filterByName("edit-the-test-execution-${unique}")

'Verify all test run information of the first row on the list is correct'
Page.nav(TestRunListPage)
	.verifyTestRunName(1, "edit-the-test-execution-${unique}")
	.verifyTestRunRelease(1, '')
	.verifyTestRunScriptRepo(1, 'cloudian-automation-1')
	.verifyTestRunTestEnv(1, "TestCloud Linux Chrome ${BrowserVersion.CHROME_LINUX_LATEST}")

'User clicks the test run name of the first row to go to the test run details page'
Page.nav(TestRunListPage).clickTestRunName(1)

'User clicks edit button on the test run page'
Page.nav(TestRunsPage).clickEdit()

'Verify the last test schedule is correct and edit the test run'
Page.nav(NewUIScheduleTestRunPopUp)
	.verifyTestRunName("edit-the-test-execution-${unique}")
	.verifyScriptRepo('cloudian-automation-1')
	.verifyObjectNameAtTS('Test Suites/Web App/Test Suites/Public/WA TS with 1 test passed')
	.clickTSEnvDropdown()
	.verifyEnvIsSelectedAtTS("TestCloud Linux Chrome ${BrowserVersion.CHROME_LINUX_LATEST}")
	.inputTestRunName("edit-the-test-execution-${unique}-update")
	.clickScriptRepo()
	.selectScriptRepo('cloudian-automation-1')
	.clickTestSuiteObjectName()
	.selectTestSuite('Test Suites/Verify test passed with markPassed')
	.clickTSEnvDropdown()
	.removeEnvAtTSTab()
	.selectSuggestedEnvironment('Windows', 'Chrome', BrowserVersion.CHROME_WINDOWS_LATEST)
	.clickRepeatEnable()
	.clickScheduleBtnArrow()
	.clickSaveConfigOption()

'User filters test run by name'
Page.nav(TestRunListPage).filterByName("edit-the-test-execution-${unique}-update")
	
'Verify all test run information of the first row on the list is correct'
Page.nav(TestRunListPage)
	.verifyTestRunName(1, "edit-the-test-execution-${unique}-update")
	.verifyTestRunRelease(1, '')
	.verifyTestRunScriptRepo(1, 'cloudian-automation-1')
	.verifyTestRunTestEnv(1, "TestCloud Windows Chrome ${BrowserVersion.CHROME_WINDOWS_LATEST}")

'[TSC] User schedules test run with TSC'
Page.nav(TestRunCalendarPage).navigateTo(user.teamId, user.projectId)
Page.nav(TestRunCalendarPage).clickScheduleTestRun()
Page.nav(NewUIScheduleTestRunPopUp)
	.inputTestRunName("TSC-edit-the-test-${unique}")
	.clickScriptRepo()
	.selectScriptRepo('cloudian-automation-1')
	.clickTSCTab()
	.clickTSCObjectName()
	.selectTSC('Test Suites/TSC/TSC with 2 passed WA TS')
	.clickTSCConfigLink()
	.selectedAllTSs()
	.selectEnvForAllTSs()
	.selectPlatform('Windows')
	.selectBrowser('Chrome')
	.selectVersion(BrowserVersion.CHROME_WINDOWS_LATEST)
	.clickSaveTSCEnv()
	.clickRepeatEnable()
	.clickScheduleBtnArrow()
	.clickSaveConfigOption()
	
String expectedEnv = """TestCloud Windows Chrome ${BrowserVersion.CHROME_WINDOWS_LATEST}
TestCloud Windows Chrome ${BrowserVersion.CHROME_WINDOWS_LATEST}"""

'[TSC] User filters test run by name'
Page.nav(TestRunListPage).filterByName("TSC-edit-the-test-${unique}")

'[TSC] Verify all tests run information of the first row on the list is correct'
Page.nav(TestRunListPage)
	.verifyTestRunName(1, "TSC-edit-the-test-${unique}")
	.verifyTestRunRelease(1, '')
	.verifyTestRunScriptRepo(1, 'cloudian-automation-1')
	.verifyTestRunTestEnv(1, expectedEnv)
		
'[TSC] User clicks the test run name of the first row to go to the test run details page'
Page.nav(TestRunListPage).clickTestRunName(1)
	
'[TSC] User clicks edit button on the test run page'
Page.nav(TestRunsPage).clickEdit()

'[TSC] Edit and verify the test run in TSC'
Page.nav(NewUIScheduleTestRunPopUp)
	.verifyTestRunName("TSC-edit-the-test-${unique}")
	.verifyScriptRepo('cloudian-automation-1')
	.verifyObjectNameAtTSC('Test Suites/TSC/TSC with 2 passed WA TS')
	.verifyListEnvIsSelectedAtTSC([
		"Windows Chrome ${BrowserVersion.CHROME_WINDOWS_LATEST}",
		"Windows Chrome ${BrowserVersion.CHROME_WINDOWS_LATEST}"
	])
	.inputTestRunName("TSC-edit-the-test-${unique}-update")
	.clickScriptRepo()
	.selectScriptRepo('cloudian-automation-1')
	.clickTSCTab()
	.clickTSCObjectName()
	.selectTSC('Test Suites/TSC/TSC with 4 passed WA TS for linux (default)')
	.clickTSCConfigLink()
	.selectedAllTSs()
	.selectEnvForAllTSs()
	.selectPlatform('Linux')
	.selectBrowser('Chrome')
	.selectVersion(BrowserVersion.CHROME_LINUX_LATEST)
	.clickSaveTSCEnv()
	.verifyNumberOfEnvIsSelectedAtTSC(3)
	.verifyNumberOfRemainEnv("+1")
	.verifyListEnvIsSelectedAtTSC([
		"Linux Chrome ${BrowserVersion.CHROME_LINUX_LATEST}",
		"Linux Chrome ${BrowserVersion.CHROME_LINUX_LATEST}",
		"Linux Chrome ${BrowserVersion.CHROME_LINUX_LATEST}",
	])
	.clickRepeatEnable()
	.clickScheduleBtnArrow()
	.clickSaveConfigOption()
			
'[TSC] User filters test run by name'
Page.nav(TestRunListPage).filterByName("TSC-edit-the-test-${unique}-update")
	
String expectedEnvTSC = """TestCloud Linux Chrome ${BrowserVersion.CHROME_LINUX_LATEST}
TestCloud Linux Chrome ${BrowserVersion.CHROME_LINUX_LATEST}
TestCloud Linux Chrome ${BrowserVersion.CHROME_LINUX_LATEST}
TestCloud Linux Chrome ${BrowserVersion.CHROME_LINUX_LATEST}"""

'[TSC] Verify all test run information of the first row on the list is correct'
Page.nav(TestRunListPage)
	.verifyTestRunName(1, "TSC-edit-the-test-${unique}-update")
	.verifyTestRunRelease(1, '')
	.verifyTestRunScriptRepo(1, 'cloudian-automation-1')
	.verifyTestRunTestEnv(1, expectedEnvTSC)
	
@com.kms.katalon.core.annotation.SetUp
def setUp() {
    unique = new DateTimeUtility().getCurrentDateTime()
}


