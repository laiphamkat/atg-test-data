import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.testexecution.NewUIScheduleTestRunPopUp
import katalon.testops.testexecution.TestRunCalendarPage

'Pre-condition: Find a user to log in'
Credential user = Page.nav(Credential)
	.getCredentials()
	.withRole('User')
	.withTestCloudMobileNativeAppEnabledFlag(false)
	.getFirst()

'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential(user.email, user.pwd).clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()

'User navigates to Test Run Calendar page'
Page.nav(TestRunCalendarPage).navigateTo(user.teamId, user.projectId)

'User click Schedule Test Run button'
Page.nav(TestRunCalendarPage).clickScheduleTestRun()

'Verify user can see the tabs with correct order'
Page.nav(NewUIScheduleTestRunPopUp)
	.waitForModalCompleteLoading()
	.clickTSEnvDropdown()
	.clickMoreOptions()
	.verifyConfigTSWithTCTabs(['Desktop Browsers', 'Mobile Browsers', 'Web Services'])
	.clickCancelEnv()
	.closeScheduleDialog()
	
'Log out from the system'
Page.nav(HeaderBar).clickAvatar().clickSignOut()

'Pre-condition: Find a user with mobile native app enabled to log in'	
user = Page.nav(Credential)
	.getCredentials()
	.withRole('User')
	.withTestCloudMobileNativeAppEnabledFlag(true)
	.getFirst()

'User navigates to TestOps Page'
Page.nav(SignInPage).verifyPageDisplayed().login(user.email, user.pwd).clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()

'User navigates to Test Run Calendar page'
Page.nav(TestRunCalendarPage).navigateTo(user.teamId, user.projectId)

'User click Schedule Test Run button'
Page.nav(TestRunCalendarPage).clickScheduleTestRun()

'Verify user can see the tabs with correct order'
Page.nav(NewUIScheduleTestRunPopUp)
	.waitForModalCompleteLoading()
	.clickTSEnvDropdown()
	.clickMoreOptions()
	.verifyConfigTSWithTCTabs(['Desktop Browsers', 'Mobile Browsers', 'Mobile Native App', 'Web Services'])