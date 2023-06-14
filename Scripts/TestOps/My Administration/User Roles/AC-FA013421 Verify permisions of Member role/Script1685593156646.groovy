
import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.my.account.AccountHomePage

/*
 * This script is for verify permission of Member role on Administration
 * 1. Member role can't go to Account Homepage so he/she can't see the icon > on Welcome page
 * 
 * */

'Find a user to login'
Credential user = Page.nav(Credential)
					.getCredentials()
					.withRole("User")
					.getFirst()

'User sign in to administration page'
Page.nav(MySignInPage).enterCredential(user.email, user.pwd).clickSignIn().verifySuccessfullySignIn()

'User selects the account'
Page.nav(WelcomePage).selectAccountMember(user.accountId).verifyAccessDeniedPopupPresent()

