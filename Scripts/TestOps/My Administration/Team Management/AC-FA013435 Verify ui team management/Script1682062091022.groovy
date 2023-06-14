import katalon.common.AdminHeaderBar
import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.my.teammanagement.TeamManagementPage

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

'Click Settings on Header Navigator > Go to Team Management'
Page.nav(AdminHeaderBar).clickSettingIcon().clickTeamManagement()

'Verify ui team management page'
Page.nav(TeamManagementPage).verifyCreateTeamTitle()
							.verifyNameFieldPresent()
							.verifyCreateBtnClickable()
							.verifyTeamTablePresent()
