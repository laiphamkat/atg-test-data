import katalon.common.AdminHeaderBar
import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.my.usermanagement.UserManagementPage

'Find a user to login'
Credential user = Page.nav(Credential)
					.getCredentials()
					.inAccount("Admin")
					.inOrg("Org_Admin")
					.withRole("Owner")
					.getFirst()

'1. User signs in to administration page'
Page.nav(MySignInPage).enterCredential(user.email, user.pwd).clickSignIn().verifySuccessfullySignIn()

'2. User selects the account'
Page.nav(WelcomePage).selectAccount(user.accountId)

'3.Click Settings on Header Navigator > Go to User Management'
Page.nav(AdminHeaderBar).clickSettingIcon().clickUserManagement()

'4. Verify show 3 tabs: "Active Users", "Pending Invitation" and "Removed Users"'
Page.nav(UserManagementPage)
							.verifyUserManagementPresent()
							.clickActiveUsers()
							.verifyDetailActiveUserTabPresent()
							.clickPendingInvitation()
							.verifyDetailPendingInvitationTabPresent()
							.clickRemovedUsersTab()
							.verifyDetailRemovedUsersTabPresent()