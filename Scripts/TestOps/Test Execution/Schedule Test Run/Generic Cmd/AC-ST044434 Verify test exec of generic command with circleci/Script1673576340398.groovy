import com.kms.katalon.core.util.KeywordUtil

import internal.GlobalVariable
import katalon.common.HomePage
import katalon.common.SeedingData
import katalon.common.SignInPage
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
		.inputTestRunName("generic-command-CircleCI-${unique}")
		.clickScriptRepo()
		.selectScriptRepo('cloudian-automation-1')
		.clickGenericCommandTab()
		.clickGenericCmdEnvDropdown()
		.clickMoreOptions()
		.clickCircleCIRadio()
		.clickCircleCIEnvDropdown()
		.selectCircleCIEnv('cloudian-CircleCI-1')						  
		.clickSaveEnv()
		.inputGenericCommand("echo \"This message should be printed out\"")
		.clickRepeatEnable()
		.clickRun()
} else {
	KeywordUtil.markFailedAndStop('This test does not support old UI of the Schedule Test Run modal, please test it manually')
}

'User filters test run by name'
Page.nav(TestRunListPage).filterByName("generic-command-CircleCI-${unique}")

'Verify all test run information of the first row on the list is correct'
Page.nav(TestRunListPage)
	.verifyTestRunName(1, "generic-command-CircleCI-${unique}")
	.verifyTestRunRelease(1, '')
	.verifyTestRunScriptRepo(1, 'cloudian-automation-1')
	.verifyTestRunTestEnv(1, 'cloudian-CircleCI-1')
	.verifyTestRunLastRunAfterCreated(1)
	.verifyTestRunNextRun(1, '')

'User clicks the test run name of the first row to go to the test run details page'
Page.nav(TestRunListPage).clickTestRunName(1)
		
'Verify the test run job information'
Page.nav(TestRunsPage)
	.verifyTestRunName(1, "generic-command-CircleCI-${unique}")
	.verifyExecutedBy(1, user.abbrName)
	.saveTestExecUrl()
	.clickTestRunId(1)
	
'Verify test run information in the Test Run Detail page is correct and the test run has been started'
Page.nav(TestRunDetailPage)
	.verifySessionScriptRepo(1, 'cloudian-automation-1')
	.verifySessionTestEnv(1, 'cloudian-CircleCI-1')
	.verifySessionQueuedOrRunning(1)
	
@com.kms.katalon.core.annotation.SetUp
def setUp() {
	unique = new DateTimeUtility().getCurrentDateTime()
}
	
@com.kms.katalon.core.annotation.TearDownIfPassed
def tearDownIfPassed() {	
	SeedingData.saveTestExecToGSheet('TEST-EXEC-GENERIC-CMD-CIRCLE-CI-1')
}	