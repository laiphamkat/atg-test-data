import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.testexecution.ApplicationRepositoryPage
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
WebUI.delay(10)

'User clicks “Upload Application” and Selected (or Drag and Drop) the Application file (.apk, ,.aab, .ipa) id(upload-app)'
Page.nav(ApplicationRepositoryPage).clickUploadApplication().selectFileUpload()

