import katalon.common.AdminHeaderBar
import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.my.usermanagement.RevokeInvitationPopup
import katalon.my.usermanagement.UserManagementPage
import katalon.services.UserManagementServices
import katalon.testops.services.LoginService

'Data setup'
Credential user = Page.nav(Credential)
					.getCredentials()
					.inAccount("Admin")
					.withRole("Admin")
					.getFirst()

'User login to get token'
Page.nav(LoginService).loginWithEncryptedPwd(user.email, user.pwd).verifyStatusCode(200).getToken()

'User invites 1 email'
Page.nav(UserManagementServices).invite(inviteeMail, user.orgId)


'User signs in to administration page'
Page.nav(MySignInPage).enterCredential(user.email, user.pwd).clickSignIn().verifySuccessfullySignIn()

'User selects the account'
Page.nav(WelcomePage).selectAccount(user.accountId)

'Click Settings on Header Navigator > Go to User Management'
Page.nav(AdminHeaderBar).clickSettingIcon().clickUserManagement()

'Click Pending Invitation tab > select a invitation > click Revoke'
Page.nav(UserManagementPage).clickPendingInvitation()
							.selectInvitation(inviteeMail)
							.clickRevoke()
							
'Verify the description and Revoke button is clickable on popup'
'Verify Revoke button is disabled if there is no email on the popup'
Page.nav(RevokeInvitationPopup).verifyRevokeBtnClickable()
								.verifyDescription("Revoking user access from this organization will also automatically revoke them from all assigned licenses and prevent them from logging into Organization ${user.orgName}. These people can not sign in to Organization.")
								.removeEmail(inviteeMail)
								.verifyRevokeBtnDisabled()
								.clickCancel()
								
'User click Revoke button'							
Page.nav(UserManagementPage).clickRevoke()

'User confirm Revoke invitation '
Page.nav(RevokeInvitationPopup).clickRevoke().sleepALittleTime()

'Verify invitation was revoked'
Page.nav(UserManagementPage).searchEmail(inviteeMail)
							.verifyEmailNotExist()


