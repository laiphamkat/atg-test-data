import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.testmanagement.TestCasesPage


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

'Navigate to the Test Cases subtab.'
Page.nav(TestCasesPage).verifyTestCasesTabVisible()

'The Directory View should be displayed'
Page.nav(TestCasesPage).verifyDirectoryViewVisible()

'The Search Test Case field should be displayed.'
Page.nav(TestCasesPage).verifySearchTestCaseFieldVisible()

'The Uploaded data folder should be displayed.'
Page.nav(TestCasesPage).verifyUploadDataFolderVisible()

'The Test Case data table should be displayed.'
Page.nav(TestCasesPage).verifyTestCaseDataTableVisible()


