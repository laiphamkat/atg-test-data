import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.constants.BrowserVersion
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.configurations.TestCloudEnvDetailsPage
import katalon.testops.report.TestRunDetailPage
import katalon.testops.testexecution.NewUIScheduleTestRunPopUp
import katalon.testops.testexecution.SessionDetailsPage
import katalon.testops.testexecution.TestRunCalendarPage
import katalon.testops.testexecution.TestRunListPage
import katalon.testops.testexecution.TestRunsPage
import katalon.utility.DateTimeUtility

'Pre-condition: Find a user to log in'
Credential user = Page.nav(Credential)
	.getCredentials()
	.forThisTestCase()
	.getFirst()
	
'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential(user.email, user.pwd).clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()

'User navigates to Test Run Calendar page'
Page.nav(TestRunCalendarPage).navigateTo(user.teamId, user.projectId)

'User click Schedule Test Run button'
Page.nav(TestRunCalendarPage).clickScheduleTestRun()

'User selects G5 test suite and everything should be displayed correctly'
Page.nav(NewUIScheduleTestRunPopUp)
	.inputTestRunName("cancel-g5-sessions-${unique}")
	.clickScriptRepo()
	.selectScriptRepo('cloudian-automation-1')
	.clickTestSuiteObjectName()
	.selectTestSuite('test-suites/public/WA TS that runs for 20 minutes')
	.clickTSEnvDropdown()
	.selectSuggestedEnvironment('Linux', 'Chrome', BrowserVersion.G5_CHROME_LINUX_LATEST)
	.selectSuggestedEnvironment('Linux', 'Firefox', BrowserVersion.G5_FIREFOX_LINUX_LATEST)
	.clickRepeatEnable()
	.clickRun()
	
'User filters test run by name'
Page.nav(TestRunListPage).filterByName("cancel-g5-sessions-${unique}")

'User clicks the test run name of the first row to go to the test runs page'
Page.nav(TestRunListPage).clickTestRunName(1)

'User clicks Run to create other test runs to verify later'
Page.nav(TestRunsPage)
	.clickRun()
	.clickRun()
	
'User goes to the first test run'
Page.nav(TestRunsPage)
	.clickTestRunId(3)
	
'Verify the second session is queued/inprogress, the first one is queued, then go back'
Page.nav(TestRunDetailPage)
	.verifyPageDisplayed()
	.verifySessionQueued(1)
	.verifySessionQueuedOrRunning(2)
	.back()

'User terminates the first test run then go to the Test Run Details page to verify'
Page.nav(TestRunsPage)
	.clickTestRunMoreOptionsIcon(3)
	.clickTerminateOption(3)
	.clickTerminate()
	.verifyTerminateSuccessfully()
	.clickTestRunId(3)
	
'Verify all the sessions are canceled then go back'
Page.nav(TestRunDetailPage)
	.verifyPageDisplayed()
	.verifySessionCanceled(1)
	.verifySessionCanceled(2)
	.back()

'User goes to the second test run'
Page.nav(TestRunsPage)
	.clickTestRunId(2)
	
'Verify the second session will be inprogress, the first one will still be queued'
Page.nav(TestRunDetailPage)
	.verifyPageDisplayed()
	.verifySessionQueued(1)
	.waitUntilSessionRunning(2, 5 * 60) // Only wait 5 minutes, longer means the previous sessions have not really been canceled
	.verifySessionRunning(2)
	
'User goes to each session to cancel and go back'
Page.nav(TestRunDetailPage).clickSessionId(2)
Page.nav(SessionDetailsPage).clickCancel().verifySatusCanceled().back()
String sessionId = Page.nav(TestRunDetailPage).getSessionId(1)
Page.nav(TestRunDetailPage).clickSessionTestEnv(1)
Page.nav(TestCloudEnvDetailsPage).clickCancel(sessionId).verifySatusCanceled(sessionId).back()

'Verify all the sessions are canceled then go back'
Page.nav(TestRunDetailPage)
	.verifyPageDisplayed()
	.verifySessionCanceled(1)
	.verifySessionCanceled(2)
	.back()

'User goes to the third test run'
Page.nav(TestRunsPage)
	.clickTestRunId(1)
	
'Verify the second session will be inprogress, the first one will still be queued'
Page.nav(TestRunDetailPage)
	.verifyPageDisplayed()
	.verifySessionQueued(1)
	.waitUntilSessionRunning(2, 5 * 60) // Only wait 5 minutes, longer means the previous sessions have not really been canceled
	.verifySessionRunning(2)
	.back()

'User terminates the test run'
Page.nav(TestRunsPage)
	.clickTestRunMoreOptionsIcon(1)
	.clickTerminateOption(1)
	.clickTerminate()
	.verifyTerminateSuccessfully()

@com.kms.katalon.core.annotation.SetUp
def setUp() {
	unique = new DateTimeUtility().getCurrentDateTime()
}