import internal.GlobalVariable
import katalon.common.AdminHeaderBar
import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.my.supportmanagement.KatalonSupportPage
import katalon.my.supportmanagement.SupportManagementPage

/*This test case can only be run on Staging */

'Find a user to login'
Credential user = Page.nav(Credential)
					.getCredentials()
					.inAccount("Admin")
					.inOrg("Org_Admin")
					.withRole("Owner")
					.getFirst()

'User sign in to administration page'
Page.nav(MySignInPage).enterCredential(user.email, user.pwd).clickSignIn().verifySuccessfullySignIn()

'User selects the account'
Page.nav(WelcomePage).selectAccount(user.accountId)

'Click Settings on Header Navigator > Go to Support Management'
Page.nav(AdminHeaderBar).clickSettingIcon().clickSupportManagement()
	
'Verified user was assigned slot to submit support ticket'
Page.nav(SupportManagementPage).verifyUserSelected(user.email)

'User submits a ticket'
Page.nav(AdminHeaderBar).clickAvatar().clickSubmitATicket().sleepALittleTime()

'Verify user can go to Katalon Support page and be able to submit a ticket'
Page.nav(KatalonSupportPage).verifyUserLoggedIn(user.email)
							.clickSubmitACase()
							.sleepALittleTime()
							.verifyUserCanSumitTicket()
