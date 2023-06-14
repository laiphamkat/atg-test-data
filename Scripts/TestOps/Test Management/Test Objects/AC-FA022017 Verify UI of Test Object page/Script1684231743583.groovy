import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.testmanagement.TestObjectsPage


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

'Click Test Objects tab and navigate to the Test Objects page.'
Page.nav(TestObjectsPage).clickTestObjectsTab()

'Test Objects card should be displayed.'
Page.nav(TestObjectsPage).verifyTestObjectsCardVisible()

'Test Objects list(ID and Name) should be displayed and the user able to click the ID.'
Page.nav(TestObjectsPage).verifyTestObjectTableVisible()

