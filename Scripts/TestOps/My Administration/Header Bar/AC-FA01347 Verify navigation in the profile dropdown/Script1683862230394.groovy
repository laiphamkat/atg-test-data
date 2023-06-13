import katalon.common.AdminHeaderBar
import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page

'Find a user to login'
Credential user = Page.nav(Credential)
					.getCredentials()
					.inAccount("Admin")
					.inOrg("Org_Admin")
					.withRole("Owner")
					.getFirst()

'User signs in to administration page'
Page.nav(MySignInPage).enterCredential(user.email, user.pwd).clickSignIn().verifySuccessfullySignIn()

'User selects the account'
Page.nav(WelcomePage).selectAccount(user.accountId)

'Verify navigation of external pages'
Page.nav(AdminHeaderBar).clickAvatar()
						.verifyCommunityPageNavigation()
						.verifyDocumentPageNavigation()
						.verifySupportPageNavigation()

'Click View Profile button and verify navigation'
Page.nav(AdminHeaderBar).clickViewProfile()
						.sleepALittleTime()
						.verifyProfilePageNavigation()
						.back()
						.sleepALittleTime()

'Click Switch Account and verify navigation'
Page.nav(AdminHeaderBar).clickAvatar()
						.clickSwitchAccount()	
						.sleepALittleTime()
						.verifyWelcomePageNavigation()
						.back()
						.sleepALittleTime()
						
'Click User Settings and verify navigation'
Page.nav(AdminHeaderBar).clickAvatar()
						.clickUserSettings()
						.sleepALittleTime()
						.verifyUserSettingPageNavigation()
