import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.util.CryptoUtil

import internal.GlobalVariable
import katalon.common.AdminSideBar
import katalon.common.MySignInPage
import katalon.common.SeedingData
import katalon.common.WelcomePage
import katalon.fw.lib.Page
import katalon.my.account.AccountHomePage
import katalon.my.account.CreateNewAccountPopUp
import katalon.my.usermanagement.InviteUsersPopup
import katalon.my.usermanagement.UserManagementPage
import katalon.services.MyAccountService
import katalon.services.OrganizationServices
import katalon.services.ProjectService
import katalon.services.TeamService
import katalon.services.UserService
import katalon.testops.services.LoginService
import katalon.utility.CommonUtility


'Data Setup'
Page.nav(UserService).initRequestObject()
					.createWithoutStatusCheck(String.format('{"email":"%s","password":"%s"}', username,
					CryptoUtil.decode(CryptoUtil.getDefault(password))))
					
Page.nav(SeedingData).setUpKatOneToken(username, password)
					
Page.nav(MyAccountService).initRequestObject()
						.getAccountId(accountName)
						.setDeleteURL()
						.deleteWithoutStatusCheck()
						.create(String.format('{"name":"%s"}', accountName))

Page.nav(OrganizationServices).initRequestObject()
								.setBearerAuthorizationHeader()
								.create(String.format('{"accountId":%s,"name":"%s"}', GlobalVariable.responseId, organizationName))

Page.nav(TeamService).initRequestObject()
			   .setBearerAuthorizationHeader()
			   .create(String.format('{"organizationId":%s}', GlobalVariable.responseId))

Page.nav(ProjectService).initRequestObject()
				  .setBearerAuthorizationHeader()
				  .create(String.format('{"teamId":%s,"name":"%s"}', GlobalVariable.responseId, projectName))

'User signs in to administration page'
Page.nav(MySignInPage).enterCredential(username, password).clickSignIn().verifySuccessfullySignIn()

'User selects the account'
Page.nav(WelcomePage).selectAccountByName(accountName).sleepALittleTime()

String accountId = WebUI.getUrl().toString().tokenize('=').last()	
						
'User click Invite Users To Account in Quick Action'
Page.nav(AccountHomePage).clickInviteUsersToAccount()

'User clicks Invite button'
Page.nav(UserManagementPage).clickInviteUsersButton()

'User inputs valid emails'
List<String> emailList = CommonUtility.convertString2ListString(emails, ',')
Page.nav(InviteUsersPopup).inputUserEmail(emailList)
						.clickSendInvitationButton()

'Verify emails are shown on Pending Invitation tab with invitation link'
Page.nav(UserManagementPage).verifyIsAtPendingInvitationPage()
							.verifyEmailsExist(emailList)
							
'Clear up data'
Page.nav(MyAccountService).initRequestObject()
					.setBearerAuthorizationHeader()
					.setJsonContentTypeHeader()
					.setUrlWithAccountId(accountId)
					.sendDeleteRequest()
					.verifyStatusCode(200)
