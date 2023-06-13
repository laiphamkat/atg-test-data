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

'User schedule test run with G5 test suite'
Page.nav(NewUIScheduleTestRunPopUp)
	.waitForModalCompleteLoading()
	.inputTestRunName("g5-public-site-${unique}")
	.clickScriptRepo()
	.selectScriptRepo('cloudian-automation-1')
	.clickTestSuiteObjectName()
	.selectTestSuite('test-suites/public/WA TS with 1 test passed')
	.clickTSEnvDropdown()
	.selectSuggestedEnvironment('Linux', 'Chrome', BrowserVersion.G5_CHROME_LINUX_LATEST)
	.clickRepeatEnable()
	.clickScheduleBtnArrow()
	.clickSaveConfigOption()

'User filters test run by name'
Page.nav(TestRunListPage).filterByName("g5-public-site-${unique}")
	
'User clicks the test run name of the first row to go to the test run details page'
Page.nav(TestRunListPage).clickTestRunName(1)

'User deletes the schedule test run'
Page.nav(TestRunsPage)
	.clickDelete()
	.confirmDeleteTestRun()	

'User verifies the test run that was deleted'
Page.nav(TestRunListPage)
	.filterByName("g5-public-site-${unique}")
	.verifyNoTestRunsDisplayed()

@com.kms.katalon.core.annotation.SetUp
def setUp() {
	unique = new DateTimeUtility().getCurrentDateTime()
}
