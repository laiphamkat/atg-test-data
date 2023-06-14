import katalon.common.AdminHeaderBar
import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.my.supportmanagement.SupportManagementPage

Credential user = Page.nav(Credential)
						.getCredentials()
						.inAccount("Admin")
						.withRole(role)
						.getFirst()
						
Credential member = Page.nav(Credential)
						.getCredentials()
						.inAccount("Admin")
						.withRole("Member")
						.getFirst()
						
'User sign in to administration page'
Page.nav(MySignInPage).enterCredential(user.email, user.pwd).clickSignIn().verifySuccessfullySignIn()

'User selects the account'
Page.nav(WelcomePage).selectAccount(user.accountId)

'Click Settings on Header Navigator > Go to Support Management'
Page.nav(AdminHeaderBar).clickSettingIcon().clickSupportManagement()

'Revoke slot of an user if he/she is assigned slot for pre-condition'
boolean isSelected = Page.nav(SupportManagementPage).isUserSelected(member.email)
if(isSelected){
	Page.nav(SupportManagementPage).selectUser(member.email)
}

'Get initial quotas'
int initialAvailableSlots = Page.nav(SupportManagementPage).getAvailableSlots()
int initialAssignedSlots = Page.nav(SupportManagementPage).getAssignedSlots()

'Assign support slot for that user'
Page.nav(SupportManagementPage).selectUser(member.email)

'Verify quota after assigning slot'
Page.nav(SupportManagementPage).verifyExpectedAvailableSlots(initialAvailableSlots-1)
								.verifyExpectedAssignedSlots(initialAssignedSlots+1)

'Revoke support slot for that user'
Page.nav(SupportManagementPage).selectUser(member.email)

'Verify quota after revoking slot'
Page.nav(SupportManagementPage).verifyExpectedAvailableSlots(initialAvailableSlots)
								.verifyExpectedAssignedSlots(initialAssignedSlots)

