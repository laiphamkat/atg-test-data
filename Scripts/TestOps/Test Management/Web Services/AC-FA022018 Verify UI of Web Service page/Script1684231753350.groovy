import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.testmanagement.WebServicesPage


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

'Click Web Services tab and navigate to the Web Services page'
Page.nav(WebServicesPage).clickWebServicesTab()

'The Web Services card should be displayed.'
Page.nav(WebServicesPage).verifyWebServicesCardVisible()

'Web Services list(Name, Average Duration, Max Duration, Min Duration) should be displayed the user able to click the Name.'
Page.nav(WebServicesPage).verifyWebServicesTableVisible()




