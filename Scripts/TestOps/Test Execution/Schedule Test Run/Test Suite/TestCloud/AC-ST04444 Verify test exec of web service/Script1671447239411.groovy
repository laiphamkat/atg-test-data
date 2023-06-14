import internal.GlobalVariable
import katalon.common.HomePage
import katalon.common.SeedingData
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
	.getFirst()
	
'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential(user.email, user.pwd).clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()

'User navigates to Test Run Calendar page'
Page.nav(TestRunCalendarPage).navigateTo(user.teamId, user.projectId)

'User click Schedule Test Run button'
Page.nav(TestRunCalendarPage).clickScheduleTestRun()

if (GlobalVariable.isNewUIScheduleEnabled) {
	'User starts inputting the information on the Schedule test run popup'
	Page.nav(NewUIScheduleTestRunPopUp)
		.inputTestRunName("ws-${browserName}-${browserVersion}-${os}-public-${unique}")
		.clickScriptRepo()
		.selectScriptRepo(scriptRepo)
		.clickTestSuiteObjectName()
		.selectTestSuite(testSuitePath)
		.clickTSEnvDropdown()
		.clickMoreOptions()
		.clickWebServicesTab()
		.selectPlatform(os)
		.selectBrowser(browserName)
		.selectVersion(browserVersion)
		.clickSaveEnv()
		.clickRepeatEnable()
		.clickRun()
} else {
	'User starts inputting the information on the Schedule test run popup'
	Page.nav(ScheduleTestRunPopUp)
		.inputTestRunName("ws-${browserName}-${browserVersion}-${os}-public-${unique}")
		.clickScriptRepo()
		.selectScriptRepo(scriptRepo)
		.clickTestSuite()
		.selectTestSuite(testSuitePath)
		.clickBrowserType()
		.selectBrowserType('Web Services')
		.clickTurnOffRepeat()
		.clickTestCloudEnvironment()
		.selectPlatform(os)
		.selectBrowser(browserName)
		.selectVersion(browserVersion)
		.clickRun()
}

'User filters test run by name'
Page.nav(TestRunListPage).filterByName("ws-${browserName}-${browserVersion}-${os}-public-${unique}")

'Verify all test run information of the first row on the list is correct'
Page.nav(TestRunListPage)
	.verifyTestRunName(1, "ws-${browserName}-${browserVersion}-${os}-public-${unique}")
	.verifyTestRunRelease(1, '')
	.verifyTestRunScriptRepo(1, scriptRepo)
	.verifyTestRunTestEnv(1, "TestCloud ${os} ${browserName} ${browserVersion}")
	.verifyTestRunLastRunAfterCreated(1)
	.verifyTestRunNextRun(1, '')
	
'User clicks the test run name of the first row to go to the test run details page'
Page.nav(TestRunListPage).clickTestRunName(1)
	
'Verify the test run job information'
Page.nav(TestRunsPage)
	.verifyTestRunName(1,"ws-${browserName}-${browserVersion}-${os}-public-${unique}")
	.verifyExecutedBy(1, user.abbrName)
	.saveTestExecUrl()
	.clickTestRunId(1)
	
'Verify test run information in the Test Run Detail page is correct and the test run has been started'
Page.nav(TestRunDetailPage)
	.verifySessionScriptRepo(1, scriptRepo)
	.verifySessionTestEnv(1, "TestCloud ${os} ${browserName} ${browserVersion}")
	.verifySessionQueuedOrRunning(1)

@com.kms.katalon.core.annotation.SetUp
def setUp() {
    unique = new DateTimeUtility().getCurrentDateTime()
}

@com.kms.katalon.core.annotation.TearDownIfPassed
def tearDownIfPassed() {
    SeedingData.saveTestExecToGSheet(tcSubId)
}
	
	