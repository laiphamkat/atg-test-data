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
import katalon.utility.DateTimeUtility

'Pre-condition: Find a user to log in'
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

'User starts inputting the information on the Schedule test run popup'
Page.nav(NewUIScheduleTestRunPopUp)
	.inputTestRunName("FA-chrome-linux-public-site-${unique}")
	.clickScriptRepo()
	.selectScriptRepo('cloudian-automation-1')
	.clickTestSuiteObjectName()
	.selectTestSuite('Test Suites/Web App/Test Suites/Public/WA TS with 1 test passed')
	.clickTSEnvDropdown()
	.selectSuggestedEnvironment('Linux', 'Chrome', BrowserVersion.CHROME_LINUX_LATEST)
	.clickProfile()
	.selectProfile('Staging')
	.clickRepeatEnable()
	.clickRun()
		  
'User filters test run by name'
Page.nav(TestRunListPage).filterByName("FA-chrome-linux-public-site-${unique}")

'Verify all test run information of the first row on the list is correct'
Page.nav(TestRunListPage)
	.verifyTestRunName(1, "FA-chrome-linux-public-site-${unique}")
	.verifyTestRunRelease(1, '')
	.verifyTestRunScriptRepo(1, 'cloudian-automation-1')
	.verifyTestRunTestEnv(1, "TestCloud Linux Chrome ${BrowserVersion.CHROME_LINUX_LATEST}")
	.verifyTestRunLastRunAfterCreated(1)
	.verifyTestRunNextRun(1, '')

'User clicks the test run name of the first row to go to the test run details page'
Page.nav(TestRunListPage).clickTestRunName(1)

'Wait until the the test run is completed then verify the test run job information'
Page.nav(TestRunsPage)
	.waitUntilTestRunCompleted(1, 60 * 10)
	.verifyTestRunName(1, "FA-chrome-linux-public-site-${unique}")
	.verifyTestSuiteName(1, 'WA TS with 1 test passed')
	.verifyProfile(1, 'Staging')
	.verifyTotal(1, '1')
	.verifyNumberOfPassed(1, '1')
	.verifyNumberOfFailed(1, '0')
	.verifyNumberOfError(1, '0')
	.verifyNumberOfIncomplete(1, '0')
	.verifyNumberOfSkipped(1, '0')
	.verifyExecutedBy(1, user.abbrName)
	
'User goes to the Test Run Detail page'
Page.nav(TestRunsPage).clickTestRunId(1)
	
'Verify test run information in the Test Run Detail page is correct and the test run has been started'
Page.nav(TestRunDetailPage)
	.verifySessionScriptRepo(1, 'cloudian-automation-1')
	.verifySessionTestEnv(1, "TestCloud Linux Chrome ${BrowserVersion.CHROME_LINUX_LATEST}")
	.verifySessionPassed(1)

@com.kms.katalon.core.annotation.SetUp
def setUp() {
	unique = new DateTimeUtility().getCurrentDateTime()
}
