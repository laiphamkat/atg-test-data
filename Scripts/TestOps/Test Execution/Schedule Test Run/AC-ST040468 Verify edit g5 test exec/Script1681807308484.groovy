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

'User save test run with G5 test suite'
Page.nav(NewUIScheduleTestRunPopUp)
	.waitForModalCompleteLoading()
	.inputTestRunName("edit-g5-public-site-${unique}")
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
Page.nav(TestRunListPage).filterByName("edit-g5-public-site-${unique}")

String expectedEnvG5 = """TestCloud Linux Chrome ${BrowserVersion.G5_CHROME_LINUX_LATEST}"""

'Verify all test run information of the first row on the list is correct'
Page.nav(TestRunListPage)
	.verifyTestRunName(1, "edit-g5-public-site-${unique}")
	.verifyTestRunRelease(1, '')
	.verifyTestRunScriptRepo(1, 'cloudian-automation-1')
	.verifyTestRunTestEnv(1, expectedEnvG5)
	
'User clicks the test run name of the first row to go to the test run details page'
Page.nav(TestRunListPage).clickTestRunName(1)

'User click Schedule Test Run button'
Page.nav(TestRunsPage).clickEdit()

'Verify the information of the last schedule test run'
Page.nav(NewUIScheduleTestRunPopUp)
	.waitForModalCompleteLoading()
	.verifyTestRunName("edit-g5-public-site-${unique}")
	.verifyScriptRepo('cloudian-automation-1')
	.verifyObjectNameAtTS('test-suites/public/WA TS with 1 test passed')
	
'Edit the schedule test run with G5 test suite'
Page.nav(NewUIScheduleTestRunPopUp)
	.inputTestRunName("edit-g5-public-site-${unique}-update")
	.clickScriptRepo()
	.selectScriptRepo('cloudian-automation-1')
	.clickTestSuiteObjectName()
	.selectTestSuite('test-suites/public/WA TS with 2 test passed')
	.clickTSEnvDropdown()
	.selectSuggestedEnvironment('Linux', 'Firefox', BrowserVersion.G5_FIREFOX_LINUX_LATEST)
	.clickRepeatEnable()
	.clickScheduleBtnArrow()
	.clickSaveConfigOption()

'User filters test run by name'
Page.nav(TestRunListPage).filterByName("edit-g5-public-site-${unique}-update")

String expectedEnvAfterEdit = """TestCloud Linux Chrome ${BrowserVersion.G5_CHROME_LINUX_LATEST}
TestCloud Linux Firefox ${BrowserVersion.G5_FIREFOX_LINUX_LATEST}"""

'Verify all test run information of the first row on the list is correct'
Page.nav(TestRunListPage)
	.verifyTestRunName(1, "edit-g5-public-site-${unique}-update")
	.verifyTestRunRelease(1, '')
	.verifyTestRunScriptRepo(1, 'cloudian-automation-1')
	.verifyTestRunTestEnv(1, expectedEnvAfterEdit)

@com.kms.katalon.core.annotation.SetUp
def setUp() {
	unique = new DateTimeUtility().getCurrentDateTime()
}
