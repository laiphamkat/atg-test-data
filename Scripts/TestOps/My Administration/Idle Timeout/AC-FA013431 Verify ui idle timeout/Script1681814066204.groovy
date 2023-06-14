import katalon.common.AdminSideBar
import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.my.account.AccountHomePage
import katalon.my.idletimeout.IdleTimeoutPage

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

'User goes to Account Setting page'
Page.nav(AccountHomePage).clickGetTheAccountID()

'User goes to Idle Timeout page'
Page.nav(AdminSideBar).clickIdleTimeout()

'Verify UI of Idle Timeout page'
Page.nav(IdleTimeoutPage).verifyTitleIdleTimeoutPage()
						.verifyIdleTimeoutSection()