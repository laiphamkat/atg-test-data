import org.apache.commons.lang3.StringUtils

import external.services.GSheetService
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.report.TestRunDetailPage
import katalon.testops.testexecution.NewUIScheduleTestRunPopUp
import katalon.testops.testexecution.TestRunCalendarPage
import katalon.testops.testexecution.TestRunListPage
import katalon.testops.testexecution.TestRunsPage
import katalon.utility.DateTimeUtility as DateTimeUtility

'Pre-condition: Find a user to log in'
Credential user = Page.nav(Credential)
	.getCredentials()
	.withRole(role)
	.withTestCloudLambdaTestEnvironmentFlag(ltEnabled)
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
	.inputTestRunName(testRunName)
	.clickScriptRepo()
	.selectScriptRepo(scriptRepo)
	.clickTestSuiteObjectName()
	.selectTestSuite(testSuitePath)
	.clickTSEnvDropdown()
	.clickMoreOptions()
	.clickDesktopBrowsersTab()
	.selectPlatform(platform)
	.selectBrowser(browser)
	.selectVersions(browserVersionList)
	.clickTestCloudTunnelEnable()
	.clickSaveEnv()
	.clickRepeatEnable()
	.clickRun()

'User filters test run by name'
Page.nav(TestRunListPage).filterByName(testRunName)

'Verify all test run information of the first row on the list is correct'
Page.nav(TestRunListPage)
	.verifyTestRunName(1, testRunName)
	.verifyTestRunRelease(1, '')
	.verifyTestRunScriptRepo(1, scriptRepo)
	.verifyTestRunTCTestEnvs(1, platform, browser, browserVersionList)
	.verifyTestRunLastRunAfterCreated(1)
	.verifyTestRunNextRun(1, '')

'User clicks the test run name of the first row to go to the test run details page'
Page.nav(TestRunListPage).clickTestRunName(1)
	
'Verify the test run job information'
Page.nav(TestRunsPage)
	.verifyTestRunName(1, testRunName)
	.verifyExecutedBy(1, user.abbrName)
	.saveTestExecUrl()
	.clickTestRunId(1)
	
'Verify test run information in the Test Run Detail page is correct and the test run has been started'
Page.nav(TestRunDetailPage)
	.verifyNumberOfSessions(browserVersionList.size())
	.verifyAllSessionsQueuedOrRunning()
	.verifyAllSessionsScriptRepo(scriptRepo)
	.verifySessionsTCTestEnv(platform, browser, browserVersionList)

@com.kms.katalon.core.annotation.SetUp
def setUp() {
    unique = new DateTimeUtility().getCurrentDateTime()
	testRunName = "$browser-${StringUtils.left(browserVersions, 10)}-$platform-private-site-$unique"
	browserVersionList = browserVersions.split(',').collect { it.trim() }
}

@com.kms.katalon.core.annotation.TearDownIfPassed
def tearDownIfPassed() {
	Page.nav(GSheetService).saveTestExecToGSheet(tcSubId)
}

