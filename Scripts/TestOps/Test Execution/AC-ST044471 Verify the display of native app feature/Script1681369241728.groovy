import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.common.PageNotFoundPage
import katalon.testops.testexecution.ApplicationRepositoryPage
import katalon.testops.testexecution.NewUIScheduleTestRunPopUp
import katalon.testops.testexecution.TestRunCalendarPage
import katalon.testops.testexecution.TestRunListPage

'Find a user in org with mobile native app enabled to log in'
Credential userEnabled = Page.nav(Credential)
	.getCredentials()
	.withRole(role)
	.withTestCloudMobileNativeAppEnabledFlag(true)
	.getFirst()
	
'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential(userEnabled.email, userEnabled.pwd).clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()

'User navigates to Test Run Calendar page'
Page.nav(TestRunCalendarPage).navigateTo(userEnabled.teamId, userEnabled.projectId)
	
'User clicks Application Repository tab'
Page.nav(HeaderBar).clickApplicationRepository()
	
'Verify Upload Application button still works'
Page.nav(ApplicationRepositoryPage)
	.clickUploadApplication()
	.verifyChooseFileDisplayed()
	.closeUploadAppDialog()
		
'Verify can still click delete app'
Page.nav(ApplicationRepositoryPage)
	.clickDeleteApplication(1)
	.clickCancelDeleteApplication()

'User goes to the Test Run List page'
Page.nav(HeaderBar).clickTestRunList()
Page.nav(TestRunListPage).waitUntilPageDisplayed()

'User should still see the Application Repository link'
Page.nav(HeaderBar).verifyApplicationRepositoryDisplayed()

'User opens the Schedule Test Run modal'
Page.nav(TestRunListPage).clickScheduleTestRun()

'Verify user should be able to see the native app feature'
Page.nav(NewUIScheduleTestRunPopUp)
	.clickTSEnvDropdown()
	.clickMoreOptions()
	.clickMobileNativeAppTab()
	.clickTSAppDropdown()
	.clickCancelEnv()
	.clickTSCTab()
	.clickTSCConfigLink()
	.clickTSCAppDropdown()
	.verifyTSCNativeAppInstructionsDisplayed()
	.clickCancelTSCEnv()
	.closeScheduleDialog()
	
'User logs out from TestOps'
Page.nav(HeaderBar).clickAvatar().clickSignOut()
	
'Find a user in org with mobile native app disabled to log in'
Credential userDisabled = Page.nav(Credential)
	.getCredentials()
	.withRole(role)
	.withTestCloudMobileNativeAppEnabledFlag(false)
	.getFirst()
	
'User navigates to TestOps Page'
Page.nav(SignInPage).login(userEnabled.email, userEnabled.pwd).clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()

'User navigates to Test Run Calendar page'
Page.nav(TestRunCalendarPage).navigateTo(userDisabled.teamId, userDisabled.projectId).verifyPageDisplayed()

'User should not see the Application Repository link'
Page.nav(HeaderBar).verifyApplicationRepositoryNotDisplayed()

'User goes to the Test Run List page'
Page.nav(HeaderBar).clickTestRunList()
Page.nav(TestRunListPage).waitUntilPageDisplayed()

'User should not see the Application Repository link'
Page.nav(HeaderBar).verifyApplicationRepositoryNotDisplayed()

'User opens the Schedule Test Run modal'
Page.nav(TestRunListPage).clickScheduleTestRun()

'Verify user should not be able to see the native app feature'
Page.nav(NewUIScheduleTestRunPopUp)
	.clickTSEnvDropdown()
	.clickMoreOptions()
	.verifyMobileNativeAppTabNotDisplayed()
	.clickCancelEnv()
	.clickTSCTab()
	.clickTSCConfigLink()
	.verifyTSCAppDropdownNotDisplayed()
	.verifyTSCNativeAppInstructionsNotDisplayed()

'User should not be able to use direct link to go to the page'
Page.nav(ApplicationRepositoryPage).navigateTo(userDisabled.teamId, userDisabled.projectId)
Page.nav(PageNotFoundPage).verifyPageDisplayed()