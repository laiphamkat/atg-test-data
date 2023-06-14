import com.kms.katalon.util.CryptoUtil

import internal.GlobalVariable
import katalon.common.MySignInPage
import katalon.common.SeedingData
import katalon.common.WelcomePage
import katalon.fw.lib.Page
import katalon.my.account.AccountHomePage
import katalon.my.usermanagement.RemoveUserPopup
import katalon.my.usermanagement.UserManagementPage
import katalon.services.MyAccountService
import katalon.services.OrganizationServices
import katalon.services.ProjectService
import katalon.services.TeamService
import katalon.services.UserManagementServices
import katalon.services.UserService

'User signs in to administration page'
Page.nav(MySignInPage).enterCredential(username, password).clickSignIn().verifySuccessfullySignIn()

'User selects the account'
Page.nav(WelcomePage).selectAccountByName(accountName)

'User goes to Account Homepage > click <Invite Users To Account> link'
Page.nav(AccountHomePage).clickInviteUsersToAccount().sleepALittleTime()

'User selects a member'
Page.nav(UserManagementPage).selectUser(invitedEmail).clickRemoveUsers()

'User removes selected member'
Page.nav(RemoveUserPopup).clickRemoveBtn()

'Verify user is deleted'
Page.nav(UserManagementPage).clickRemovedUsersTab()
							.searchEmail(invitedEmail)
							.verifyEmailExist(invitedEmail)

@com.kms.katalon.core.annotation.SetUp
def setUp() {
	
	'Create owner'
	Page.nav(UserService).initRequestObject()
						.createWithoutStatusCheck(String.format('{"email":"%s","password":"%s"}', username,
						CryptoUtil.decode(CryptoUtil.getDefault(password))))
						
	'Create invite email'
	Page.nav(UserService).initRequestObject()
						.createWithoutStatusCheck(String.format('{"email":"%s","password":"%s"}', invitedEmail,
						CryptoUtil.decode(CryptoUtil.getDefault(password))))
	
	'Login as owner'										
	Page.nav(SeedingData).setUpKatOneToken(username, password)
	
	'Create account'					
	Page.nav(MyAccountService).initRequestObject()
							.getAccountId(accountName)
							.setDeleteURL()
							.deleteWithoutStatusCheck()
							.create(String.format('{"name":"%s"}', accountName))
	
	Page.nav(OrganizationServices).initRequestObject()
									.setBearerAuthorizationHeader()
									.create(String.format('{"accountId":%s,"name":"%s"}', GlobalVariable.responseId, organizationName))
	
	String orgId = GlobalVariable.responseId
	
	Page.nav(TeamService).initRequestObject()
				   .setBearerAuthorizationHeader()
				   .create(String.format('{"organizationId":%s}', GlobalVariable.responseId))
	
	Page.nav(ProjectService).initRequestObject()
					  .setBearerAuthorizationHeader()
					  .create(String.format('{"teamId":%s,"name":"%s"}', GlobalVariable.responseId, projectName))
					  
	'Invite a user to Org'				  
	def result = Page.nav(UserManagementServices).invite(invitedEmail, orgId)
										  
	'Invitee logins'
	Page.nav(SeedingData).setUpKatOneToken(invitedEmail, password)
	
	'Accept invitation'
	Page.nav(UserManagementServices).acceptInvitation(result.id, result.invitationToken)
}
