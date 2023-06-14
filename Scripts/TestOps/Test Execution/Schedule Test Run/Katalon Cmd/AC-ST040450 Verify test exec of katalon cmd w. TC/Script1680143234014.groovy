import com.kms.katalon.core.util.KeywordUtil

import internal.GlobalVariable
import katalon.common.HomePage
import katalon.common.SeedingData
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

if (GlobalVariable.isNewUIScheduleEnabled) {
	'User starts inputting the information on the Schedule test run popup'
	Page.nav(NewUIScheduleTestRunPopUp)
		.inputTestRunName("Test execute with Katalon command ${unique}")
		.clickScriptRepo()
		.selectScriptRepo('cloudian-automation-1')
		.clickKatalonCmdTab()
		.inputKatalonCmd('-retry=0 -testSuitePath="Test Suites/Web App/Test Suites/Public/WA TS with 1 test passed" -browserType="Chrome" -executionProfile="default"')
		.clickKatalonCmdEnvDropdown()
		.clickMoreOptions()
		.selectPlatform('Linux')
		.selectBrowser('Chrome')
		.selectVersion(BrowserVersion.CHROME_LINUX_LATEST)
		.clickSaveEnv()
		.clickRepeatEnable()
		.clickRun()
} else { 
	KeywordUtil.markFailedAndStop("This test script does not support the old UI of the Schedule Test Run, so please test manually")
}
				
'User filters test run by name'
Page.nav(TestRunListPage).filterByName("Test execute with Katalon command ${unique}")

'Verify all test run information of the first row on the list is correct'
Page.nav(TestRunListPage)
	.verifyTestRunName(1, "Test execute with Katalon command ${unique}")
	.verifyTestRunRelease(1, '')
	.verifyTestRunScriptRepo(1, 'cloudian-automation-1')
	.verifyTestRunTestEnv(1, "TestCloud Linux Chrome ${BrowserVersion.CHROME_LINUX_LATEST}")
	.verifyTestRunLastRunAfterCreated(1)
	.verifyTestRunNextRun(1, '')

'User clicks the test run name of the first row to go to the test run details page'
Page.nav(TestRunListPage).clickTestRunName(1)

'Verify the test run job information'
Page.nav(TestRunsPage)
	.verifyTestRunName(1, "Test execute with Katalon command ${unique}")
	.verifyExecutedBy(1, user.abbrName)
	.saveTestExecUrl()
	.clickTestRunId(1)
	
'Verify test run information in the Test Run Detail page is correct and the test run has been started'
Page.nav(TestRunDetailPage)
	.verifySessionScriptRepo(1, 'cloudian-automation-1')
	.verifySessionTestEnv(1, "TestCloud Linux Chrome ${BrowserVersion.CHROME_LINUX_LATEST}")
	.verifySessionQueuedOrRunning(1)
	
@com.kms.katalon.core.annotation.SetUp
def setUp() {
	unique = new DateTimeUtility().getCurrentDateTime()
}
	
@com.kms.katalon.core.annotation.TearDownIfPassed
def tearDownIfPassed() {	
	SeedingData.saveTestExecToGSheet('TEST-EXEC-KATA-CMD-1')
}
	
	