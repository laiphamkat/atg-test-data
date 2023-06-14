import external.services.GSheetService
import internal.GlobalVariable
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.report.TestRunDetailPage
import katalon.testops.testexecution.NewUIScheduleTestRunPopUp
import katalon.testops.testexecution.ScheduleTestRunPopUp
import katalon.testops.testexecution.TestRunCalendarPage
import katalon.testops.testexecution.TestRunListPage
import katalon.testops.testexecution.TestRunsPage
import katalon.utility.DateTimeUtility

'Pre-condition: Find a user to log in'
Credential user = Page.nav(Credential)
	.getCredentials()
	.withTestCloudMobileNativeAppEnabledFlag(true)
	.getFirst()
	
'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential().clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()

'User navigates to Test Run Calendar page'
Page.nav(TestRunCalendarPage).navigateTo(user.teamId, user.projectId)

'User click Schedule Test Run button'
Page.nav(TestRunCalendarPage).clickScheduleTestRun()

'User starts inputting the information on the Schedule test run popup'
Page.nav(NewUIScheduleTestRunPopUp)
	.inputTestRunName("native-app-public-${platform}-${unique}")
	.clickScriptRepo()
	.selectScriptRepo(scriptRepo)
	.clickTestSuiteObjectName()
	.selectTestSuite(testSuitePath)
	.clickTSEnvDropdown()
	.clickMoreOptions()
	.clickMobileNativeAppTab()
	.selectPlatform(platform)
	.selectVersion(osVersion)
	.selectDeviceType(deviceType)
	.selectDevice(deviceName)
	.clickTSAppDropdown()
	.selectApp(appName)
	.clickSaveEnv()
	.clickRepeatEnable()
	.clickRun()	
	
'User filters test run by name'
Page.nav(TestRunListPage).filterByName("native-app-public-${platform}-${unique}")

'Verify all test run information of the first row on the list is correct'
Page.nav(TestRunListPage)
	.verifyTestRunName(1, "native-app-public-${platform}-${unique}")
	.verifyTestRunRelease(1, '')
	.verifyTestRunScriptRepo(1, scriptRepo)
	.verifyTestRunTestEnv(1, expectedTestEnv)
	.verifyTestRunLastRunAfterCreated(1)
	.verifyTestRunNextRun(1, '')

'User clicks the test run name of the first row to go to the test run details page'
Page.nav(TestRunListPage).clickTestRunName(1)
	
'Verify the test run job information'
Page.nav(TestRunsPage)
	.verifyTestRunName(1, "native-app-public-${platform}-${unique}")
	.verifyExecutedBy(1, user.abbrName)
	.saveTestExecUrl()
	.clickTestRunId(1)
	
'Verify test run information in the Test Run Detail page is correct and the test run has been started'
Page.nav(TestRunDetailPage)
	.verifySessionScriptRepo(1, scriptRepo)
	.verifySessionTestEnv(1, expectedTestEnv)
	.verifySessionQueuedOrRunning(1)
	
@com.kms.katalon.core.annotation.SetUp
def setUp() {
	unique = new DateTimeUtility().getCurrentDateTime()
}
	
@com.kms.katalon.core.annotation.TearDownIfPassed
def tearDownIfPassed() {	
	Page.nav(GSheetService).saveTestExecToGSheet(tcSubId)
}
	
	