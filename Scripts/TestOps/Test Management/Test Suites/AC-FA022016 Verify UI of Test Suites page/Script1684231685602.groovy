import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.testmanagement.TestSuitesPage


'Pre-condition: Find a user to log in'
Credential user = Page.nav(Credential)
	.getCredentials()
	.withRole(role)
	.getFirst()

'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential(user.email, user.pwd).clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()

'User select Org'
Page.nav(HeaderBar).clickOrganization()
Page.nav(HomePage).selectOrg(user.orgName)

'At Home page, user select Project'
Page.nav(HomePage).selectProject(user.projectName)

'Click on the Tests menu'
Page.nav(HeaderBar).clickTests()

'Navigate to the Test Suites subtab.'
Page.nav(TestSuitesPage).clickTestSuitesTab()

'The Directory View should be displayed.'
Page.nav(TestSuitesPage).verifyDirectoryViewVisible()

'The Search Test Suite field should be displayed.'
Page.nav(TestSuitesPage).verifySearchTestSuiteVisible()

'The Uploaded data folder should be displayed.'
Page.nav(TestSuitesPage).verifyUploadedDataFolderVisible()

'The Test Suite button should be displayed and the user able to click the Test Suite button.'
Page.nav(TestSuitesPage).verifyTestSuiteButtonClickable()

