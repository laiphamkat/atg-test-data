import internal.GlobalVariable
import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.constants.BrowserVersion
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.common.SurveyPopUp
import katalon.testops.testexecution.NewUIScheduleTestRunPopUp
import katalon.testops.testexecution.TestRunCalendarPage
import katalon.testops.testexecution.TestRunListPage
import katalon.utility.DateTimeUtility

'Pre-condition: Find a user to log in'
Credential user = Page.nav(Credential)
	.getCredentials()
	.inOrg('KOK 2')
	.getFirst()

'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential(user.email, user.pwd).clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()

'User navigates to Test Run Calendar page'
Page.nav(TestRunCalendarPage).navigateTo(user.teamId, user.projectId)

'User clicks Schedule Test Run button'
Page.nav(TestRunCalendarPage).clickScheduleTestRun()

'User starts a test run unsuccessfully'
Page.nav(NewUIScheduleTestRunPopUp)
	.inputTestRunName("Verify the survey not displayed when schedule a test run unsuccessfully - ${unique}")
	.clickScriptRepo()
	.selectScriptRepo(scriptRepo)
	.clickTestSuiteObjectName()
	.selectTestSuite(testSuitePath)
	.clickTSEnvDropdown()
	.clickMoreOptions()
	.clickDesktopBrowsersTab()
	.selectPlatform('Linux')
	.selectBrowser('Chrome')
	.selectVersion(BrowserVersion.CHROME_LINUX_LATEST)
	.clickTestCloudTunnelEnable()
	.clickSaveEnv()
	.clickRepeatEnable()
	.clickRun()
	
'Verify the survey is not displayed when schedule a test run unsuccessfully'
Page.nav(TestRunListPage).waitUntilTestRunListLoadedCompletely()
Page.nav(SurveyPopUp).verifySurveyNotDisplayed()

'User starts a test run successfully the first time'
Page.nav(NewUIScheduleTestRunPopUp)
	.inputTestRunName("Verify the survey displayed on the first time - ${unique}")
	.clickTSEnvDropdown()
	.clickMoreOptions()
	.clickTestCloudTunnelEnable()
	.clickSaveEnv()
	.clickRun()
	
'Verify the survey displayed on the first time'
Page.nav(SurveyPopUp).verifySurveyDisplayed()

'Select a rating star and input a message'
Page.nav(SurveyPopUp).selectStarRating(4).inputMessage('test').clickSubmit()

'User clicks Schedule Test Run button'
Page.nav(TestRunListPage).clickScheduleTestRun()

'User starts a test run successfully again'
Page.nav(NewUIScheduleTestRunPopUp)
	.inputTestRunName("Verify the survey not displayed on the second time - ${unique}")
	.clickScriptRepo()
	.selectScriptRepo(scriptRepo)
	.clickTestSuiteObjectName()
	.selectTestSuite(testSuitePath)
	.clickTSEnvDropdown()
	.selectSuggestedEnvironment('Linux', 'Chrome', BrowserVersion.CHROME_LINUX_LATEST)
	.clickRepeatEnable()
	.clickRun()
	
'Verify the survey not displayed on the second time'
Page.nav(TestRunListPage).waitUntilTestRunListLoadedCompletely()
Page.nav(SurveyPopUp).verifySurveyNotDisplayed()
	
'User logs out from TestOps'
Page.nav(HeaderBar).sleepSomeTime().clickAvatar().clickSignOut()
// There is an issue here that the system cannot be signed out after clicking on the link but it cannot be reproduced when clicking manually.
// Therefore, adding sleepSomeTime to resolve the script issue here. 
	
'User logs in again to TestOps Page'
Page.nav(SignInPage).enterCredential(user.email, user.pwd).clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()

'User navigates to Test Run Calendar page'
Page.nav(TestRunCalendarPage).navigateTo(user.teamId, user.projectId)

'User clicks Schedule Test Run button'
Page.nav(TestRunCalendarPage).clickScheduleTestRun()

'User starts a test run successfully again'
Page.nav(NewUIScheduleTestRunPopUp)
	.inputTestRunName("Verify the survey still not displayed after logging out - ${unique}")
	.clickScriptRepo()
	.selectScriptRepo(scriptRepo)
	.clickTestSuiteObjectName()
	.selectTestSuite(testSuitePath)
	.clickTSEnvDropdown()
	.selectSuggestedEnvironment('Linux', 'Chrome', BrowserVersion.CHROME_LINUX_LATEST)
	.clickRepeatEnable()
	.clickRun()
	
'Verify the survey still not displayed after logging out'
Page.nav(TestRunListPage).waitUntilTestRunListLoadedCompletely()
Page.nav(SurveyPopUp).verifySurveyNotDisplayed()

@com.kms.katalon.core.annotation.SetUp
def setUp() {
	unique = new DateTimeUtility().getCurrentDateTime()
}