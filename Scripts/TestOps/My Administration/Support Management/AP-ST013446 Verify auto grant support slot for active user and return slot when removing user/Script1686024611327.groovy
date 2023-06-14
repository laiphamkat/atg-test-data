
import katalon.common.AdminHeaderBar
import katalon.common.HeaderBar
import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.my.supportmanagement.SupportManagementPage
import katalon.my.usermanagement.AcceptInvitationPage
import katalon.services.UserManagementServices
import katalon.testops.services.LoginService

"""
Pre-condition:
Org has Platform Premium -> quota = 1
Invite a new user

Steps:
1. New user login to accept invitation > logout
2. Login as Org Owner
3. Click Setting icon > select 'Support Management'
4. Check available support slot -1
5. Check new Member is assigned slot automatically
6. Click 'User Management' > go to User Managment page
7. Select new Member > Click Remove
8. Come back to Support Management page
9. Check available support slot +1

Validation points:
After user accept invitation, available support slot decrease by 1 and user is assigned slot
After user is removed, available support slot increase by 1 and not show the user in the list"
"""

Credential user = Page.nav(Credential)
					.getCredentials()
					.inAccount("Admin")
					.withRole("Owner")
					.getFirst()

'User login to get token'
Page.nav(LoginService).loginWithEncryptedPwd(user.email, user.pwd).verifyStatusCode(200).getToken()

'User invites 1 email and get the invitation link'
Page.nav(UserManagementServices).removeUser(invitee, user.orgId)
String invitationToken = Page.nav(UserManagementServices).invite(invitee, user.orgId).invitationToken

'Member access the link and accept the invitation'
Page.nav(AcceptInvitationPage)
	.goToAcceptPage(invitationToken)
	.login(invitee, user.pwd)
	.clickJoin()
	.sleepALittleTime()

'Log out from Member role'	
Page.nav(HeaderBar).clickAvatar().clickSwitchAccount()
Page.nav(WelcomePage).clickTryAnotherEmailLink()

'Sign in to administration page with Owner role'
Page.nav(MySignInPage).enterCredential(user.email, user.pwd).clickSignIn().verifySuccessfullySignIn()

'User selects the account'
Page.nav(WelcomePage).selectAccount(user.accountId)

'Click Settings on Header Navigator > Go to Support Management'
Page.nav(AdminHeaderBar).clickSettingIcon().clickSupportManagement()

'Verify invited user is assigned support slot'
int noOfSelectedUsers = Page.nav(SupportManagementPage).verifyUserSelected(invitee).countNoOfSelectedUsers()
Page.nav(SupportManagementPage).verifyExpectedAssignedSlots(noOfSelectedUsers)

'Clear up data'
Page.nav(UserManagementServices).initRequestObject().removeUser(invitee, user.orgId)





