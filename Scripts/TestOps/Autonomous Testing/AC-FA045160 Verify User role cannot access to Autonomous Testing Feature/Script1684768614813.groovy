import internal.GlobalVariable
import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.SettingPopup
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page

'Pre-condition: Find a user to log in'
Credential user = Page.nav(Credential)
	.getCredentials()
	.withRole(role)
	.inProject('AI - TestGen Project')
	.getFirst()

'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential(user.email, user.pwd).clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()

'User navigates to Org home'
Page.nav(HomePage).navigateToOrgHome(user.orgId)

'User click to Setting icon'
Page.nav(HeaderBar).clickSettingIcon()

'Verify Autonomous Testing option is not exist'
Page.nav(SettingPopup).verifyAutonomousTestingNotDisplayed()
