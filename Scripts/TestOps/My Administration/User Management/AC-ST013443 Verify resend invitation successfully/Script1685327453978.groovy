import katalon.common.AdminHeaderBar
import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.my.usermanagement.ResendInvitationPopup
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
def dataResult = Page.nav(UserManagementServices).invite(inviteeMail, user.orgId)
String invitationToken1 = dataResult.invitationToken
String idInvitation = dataResult.id

'User signs in to administration page'
Page.nav(MySignInPage).enterCredential(user.email, user.pwd).clickSignIn().verifySuccessfullySignIn()

'User selects the account'
Page.nav(WelcomePage).selectAccount(user.accountId)

'Click Settings on Header Navigator > Go to User Management'
Page.nav(AdminHeaderBar).clickSettingIcon().clickUserManagement()

'Click Pending Invitation tab > select a invitation > click Revoke'
Page.nav(UserManagementPage).clickPendingInvitation()
							.selectInvitation(inviteeMail)
							.clickResendInvitation()
							
'Verify the description and Resend button is clickable on popup'
'Verify Resend button is disabled if there is no email on the popup'
Page.nav(ResendInvitationPopup).verifyResendBtnClickable()
								.verifyDescription("The following users will receive a new invitation link. Previous invitation links will be deactivated.")
								.removeEmail(inviteeMail)
								.verifyResendBtnDisabled()
								.clickCancel()
								
								
'User click Revoke button'
Page.nav(UserManagementPage).clickResendInvitation()

'User confirm Resend invitation '
Page.nav(ResendInvitationPopup).clickResend().sleepALittleTime()

'Get invitation token after resending'
String invitationToken2 = Page.nav(UserManagementServices).getUserInvitation("""
													{
													    "filters": [
													        {
													            "field": "id",
													            "operator": "EQ",
													            "value": $idInvitation
													        }
													    ]
													}
													""").data[0].invitationToken

'Verify resend invitation successfully'													
Page.nav(UserManagementPage).verifyResendInvitationSuccessfully(invitationToken2, invitationToken1)