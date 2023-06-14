import com.kms.katalon.util.CryptoUtil

import internal.GlobalVariable
import katalon.fw.lib.Page
import katalon.my.account.OrgHomePage
import katalon.my.usermanagement.AcceptInvitationPage
import katalon.services.UserManagementServices
import katalon.testops.services.LoginService

'User login to get token'
Page.nav(LoginService).login(GlobalVariable.owner_mail, CryptoUtil.decode(CryptoUtil.getDefault(GlobalVariable.owner_pass))).verifyStatusCode(200).getToken()

'User invites 1 email and get the invitation link'
String invitationToken = Page.nav(UserManagementServices).invite(email, orgId).invitationToken

'User access the link and decline the invitation'
Page.nav(AcceptInvitationPage)
	.goToAcceptPage(invitationToken)
	.login(email, userPwd)
	.clickDecline()
	.sleepSomeTime()

'Verify redirect user to Org Homepage of his/her own org'
Page.nav(OrgHomePage).verifyUserIsOwnerRole()
		
'Verify user is not in the org'	
Page.nav(UserManagementServices).initRequestObject().verifyUserNotExistInOrg(email, orgId)