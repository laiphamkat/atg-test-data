import external.services.GSheetService
import internal.GlobalVariable
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.constants.BrowserVersion
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.report.TestRunDetailPage
import katalon.testops.testexecution.NewUIScheduleTestRunPopUp
import katalon.testops.testexecution.TestRunCalendarPage
import katalon.testops.testexecution.TestRunListPage
import katalon.testops.testexecution.TestRunsPage
import katalon.utility.DateTimeUtility as DateTimeUtility

'Find a user to log in'
Credential user = Page.nav(Credential)
	.getCredentials()
	.getFirst()

'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential(user.email, user.pwd).clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()

'User navigates to Test Run Calendar page'
Page.nav(TestRunCalendarPage).navigateTo(user.teamId, user.projectId)

'User click Schedule Test Run button'
Page.nav(TestRunCalendarPage).clickScheduleTestRun()

'User save test run with G5 test suite'
Page.nav(NewUIScheduleTestRunPopUp)
	.inputTestRunName("save-g5-public-site-${unique}")
	.clickScriptRepo()
	.selectScriptRepo('cloudian-automation-1')
	.clickTestSuiteObjectName()
	.selectTestSuite('test-suites/public/WA TS with 1 test passed')
	.clickTSEnvDropdown()
	.selectSuggestedEnvironment('Linux', 'Chrome', BrowserVersion.G5_CHROME_LINUX_LATEST)
	.clickTSEnvDropdown()
	.selectSuggestedEnvironment('Linux', 'Firefox', BrowserVersion.G5_FIREFOX_LINUX_LATEST)
	.clickRepeatEnable()
	.clickScheduleBtnArrow()
	.clickSaveConfigOption()

'User filters test run by name'
Page.nav(TestRunListPage).filterByName("save-g5-public-site-${unique}")

String expectedEnvG5 = """TestCloud Linux Chrome ${BrowserVersion.G5_CHROME_LINUX_LATEST}
TestCloud Linux Firefox ${BrowserVersion.G5_FIREFOX_LINUX_LATEST}"""

'Verify all test run information of the first row on the list is correct'
Page.nav(TestRunListPage)
	.verifyTestRunName(1, "save-g5-public-site-${unique}")
	.verifyTestRunRelease(1, '')
	.verifyTestRunScriptRepo(1, 'cloudian-automation-1')
	.verifyTestRunTestEnv(1, expectedEnvG5)
	.verifyTestRunLastRun(1, '')
	.verifyTestRunNextRun(1, '')
	
'User clicks the test run name of the first row to go to the test run details page'
Page.nav(TestRunListPage).clickTestRunName(1)

'Verify no test run is created'
Page.nav(TestRunsPage)
	.verifyNoTestRunDisplayed()
	.back()

'User click Schedule Test Run button'
Page.nav(TestRunCalendarPage).clickScheduleTestRun()

'User schedule test run with G5 test suite'
Page.nav(NewUIScheduleTestRunPopUp)
	.inputTestRunName("schedule-g5-public-site-${unique}")
	.clickScriptRepo()
	.selectScriptRepo('cloudian-automation-1')
	.clickTestSuiteObjectName()
	.selectTestSuite('test-suites/public/WA TS with 1 test passed')
	.clickTSEnvDropdown()
	.selectSuggestedEnvironment('Linux', 'Chrome', BrowserVersion.G5_CHROME_LINUX_LATEST)
	.clickTSEnvDropdown()
	.selectSuggestedEnvironment('Linux', 'Firefox', BrowserVersion.G5_FIREFOX_LINUX_LATEST)
	.clearStartTime()
	.inputStart('04/11/2050 15:27')
	.inputEnd('04/11/2050 16:27')
	.clickScheduleBtnArrow()
	.clickScheduleOption()

'User filters test run by name'
Page.nav(TestRunListPage).filterByName("schedule-g5-public-site-${unique}")

'Verify all test run information of the first row on the list is correct'
Page.nav(TestRunListPage)
	.verifyTestRunName(1, "schedule-g5-public-site-${unique}")
	.verifyTestRunRelease(1, '')
	.verifyTestRunScriptRepo(1, 'cloudian-automation-1')
	.verifyTestRunTestEnv(1, expectedEnvG5)
	.verifyTestRunLastRun(1, '')
	.verifyTestRunNextRun(1, 'Apr 11, 2050')
	
'User clicks the test run name of the first row to go to the test run details page'
Page.nav(TestRunListPage).clickTestRunName(1)

'Verify no test run is created'
Page.nav(TestRunsPage)
	.verifyNoTestRunDisplayed()
	.back()

'User click Schedule Test Run button'
Page.nav(TestRunCalendarPage).clickScheduleTestRun()

'User runs test run with G5 test suite'
Page.nav(NewUIScheduleTestRunPopUp)
	.inputTestRunName("run-g5-public-site-${unique}")
	.clickScriptRepo()
	.selectScriptRepo('cloudian-automation-1')
	.clickTestSuiteObjectName()
	.selectTestSuite('test-suites/public/WA TS with 1 test passed')
	.clickTSEnvDropdown()
	.selectSuggestedEnvironment('Linux', 'Chrome', BrowserVersion.G5_CHROME_LINUX_LATEST)
	.clickTSEnvDropdown()
	.selectSuggestedEnvironment('Linux', 'Firefox', BrowserVersion.G5_FIREFOX_LINUX_LATEST)
	.clickRepeatEnable()
	.clickScheduleBtnArrow()
	.clickRunOption()

'User filters test run by name'
Page.nav(TestRunListPage).filterByName("run-g5-public-site-${unique}")

'Verify all test run information of the first row on the list is correct'
Page.nav(TestRunListPage)
	.verifyTestRunName(1, "run-g5-public-site-${unique}")
	.verifyTestRunRelease(1, '')
	.verifyTestRunScriptRepo(1, 'cloudian-automation-1')
	.verifyTestRunTestEnv(1, expectedEnvG5)
	.verifyTestRunLastRunAfterCreated(1)
	.verifyTestRunNextRun(1, '')

'User clicks the test run name of the first row to go to the test run details page'
Page.nav(TestRunListPage).clickTestRunName(1)

'Verify the test run job information'
Page.nav(TestRunsPage)
	.verifyTestRunName(1, "run-g5-public-site-${unique}")
	.verifyExecutedBy(1, user.abbrName)
	.saveTestExecUrl()
	.clickTestRunId(1)
	
'Verify test run information in the Test Run Detail page is correct and the test run has been started'
Page.nav(TestRunDetailPage)
	.verifySessionScriptRepo(1, 'cloudian-automation-1')
	.verifySessionTestEnv(1, "TestCloud Linux Firefox ${BrowserVersion.G5_FIREFOX_LINUX_LATEST}")
	.verifySessionQueuedOrRunning(1)
	.verifySessionTestEnv(2, "TestCloud Linux Chrome ${BrowserVersion.G5_CHROME_LINUX_LATEST}")
	.verifySessionQueuedOrRunning(2)

@com.kms.katalon.core.annotation.SetUp
def setUp() {
	unique = new DateTimeUtility().getCurrentDateTime()
}
	
@com.kms.katalon.core.annotation.TearDownIfPassed
def tearDownIfPassed() {	
	Page.nav(GSheetService).saveTestExecToGSheet('SCHEDULE-SAVE-RUN-G5-TS-1')
}
