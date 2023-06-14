import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW

import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.my.usermanagement.AcceptInvitationPage
import katalon.services.UserManagementServices
import katalon.testops.dashboard.NoProjectsPage
import katalon.testops.services.LoginService

Credential user = Page.nav(Credential)
					.getCredentials()
					.inAccount("Admin")
					.withRole("Admin")
					.getFirst()

Credential invitee = Page.nav(Credential)
					.getCredentials()
					.getFirst()

'User login to get token'
Page.nav(LoginService).loginWithEncryptedPwd(user.email, user.pwd).verifyStatusCode(200).getToken()

'User invites 1 email and get the invitation link'
String invitationToken = Page.nav(UserManagementServices).invite(invitee.email, user.orgId).invitationToken

'User access the link and accept the invitation'
Page.nav(AcceptInvitationPage)
	.goToAcceptPage(invitationToken)
	.login(invitee.email, invitee.pwd)
	.clickJoin()
	.sleepSomeTime()

'Verify after accept invitation, redirect user to TestOps Homepage without project'		
Page.nav(NoProjectsPage).verifyThereAreNoProjectTitlePresent()

'Clear up data'
Page.nav(UserManagementServices).initRequestObject().removeUser(invitee.email, user.orgId)

