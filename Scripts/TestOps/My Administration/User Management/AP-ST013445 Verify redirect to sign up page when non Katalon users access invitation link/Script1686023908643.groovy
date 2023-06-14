import katalon.common.SignUpPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.my.usermanagement.AcceptInvitationPage
import katalon.services.UserManagementServices
import katalon.testops.services.LoginService
import katalon.utility.DateTimeUtility

"""
invite email
user+[Date]@mail.com

click invitation link > reidrect to sign up page
input username + password > show Accept invitation page
"""


Credential user = Page.nav(Credential)
					.getCredentials()
					.inAccount("Admin")
					.withRole("Admin")
					.getFirst()

String random = new DateTimeUtility().getCurrentDateTime()
String inviteeMail = "user_${random}@mail.com"

'User login to get token'
Page.nav(LoginService).loginWithEncryptedPwd(user.email, user.pwd).verifyStatusCode(200).getToken()

'User invites 1 email and get the invitation link'
String invitationToken = Page.nav(UserManagementServices).invite(inviteeMail, user.orgId).invitationToken

'User access the link and accept the invitation'
Page.nav(AcceptInvitationPage).goToAcceptPage(invitationToken)

'Verify navigate user to Sign Up page'
Page.nav(SignUpPage).verifyPageDisplayed()
