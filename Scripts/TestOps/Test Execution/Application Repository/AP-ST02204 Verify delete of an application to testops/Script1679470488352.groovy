import com.kms.katalon.core.util.KeywordUtil

import internal.GlobalVariable
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.testexecution.ApplicationRepositoryPage
import katalon.testops.testexecution.NewUIScheduleTestRunPopUp
import katalon.testops.testexecution.ScheduleTestRunPopUp
import katalon.testops.testexecution.TestRunCalendarPage

KeywordUtil.markFailedAndStop("This test case should be executed manually")

'Pre-condition: Find a user to log in'
Credential user = Page.nav(Credential)
	.getCredentials()
	.getFirst()
	
'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential(user.email, user.pwd).clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()

'User navigates to Test Run Calendar page'
Page.nav(TestRunCalendarPage).navigateTo(user.teamId, user.projectId)

'User click on Application Repository tab'
Page.nav(TestRunCalendarPage).clickApplicationRepositoryTab()

'User clicks the “Delete” icon and clicks the “Delete” button on the Delete Application Confirmation popup'
Page.nav(ApplicationRepositoryPage).clickDeleteApplication()
								   .clickConfirmDeleteApplication()
								   
'Verify the app is deleted successfully'
Page.nav(ApplicationRepositoryPage).verifyDeleteSuccessMessage('API Demos')
								   .placeholderForEmptyList()
								   
'Verify the app is not displayed in the “Mobile Native App” drop-down at the Schedule Test Run popup'
Page.nav(TestRunCalendarPage).clickTestRunListTab()
							 .clickScheduleTestRun()

if (GlobalVariable.isNewUIScheduleEnabled) {
	Page
	.nav(NewUIScheduleTestRunPopUp)
	.clickEnvironmentDropdown()
	.clickMoreOptions()
	.clickMobileNativeAppTab()
	.clickAppDropdown()
	.verifyAppNotExist('API Demos')
} else {
	Page
	.nav(ScheduleTestRunPopUp)
	.clickBrowserType()
	.selectBrowserType('Mobile Native App')
	.clickMobileNativeApp()
	.verifyMobielNativeApp('API Demos')
}

