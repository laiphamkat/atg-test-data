import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.my.account.AccountHomePage
import katalon.my.account.AccountSettingPage as AccountSettingPage

'Find a user to login'
Credential user = Page.nav(Credential)
					.getCredentials()
					.inAccount("Admin")
					.inOrg("Org_Admin")
					.withRole("Owner")
					.getFirst()

'User signs in to administration page'
Page.nav(MySignInPage).enterCredential(user.email, user.pwd).clickSignIn().verifySuccessfullySignIn()

int noOfMembers = Page.nav(WelcomePage).getNoOfMembers(user.accountId)
int noOfOrgs = Page.nav(WelcomePage).getNoOfOrgs(user.accountId)
int noOfProjects = Page.nav(WelcomePage).getNoOfProjects(user.accountId)

'User selects the account'
Page.nav(WelcomePage).selectAccount(user.accountId)

'User goes to Account Homepage > click <Get the Account ID> link'
Page.nav(AccountHomePage).clickGetTheAccountID()

'Verify account avatar, account name, "EditName" button, Delete button and related popups are presented'
Page.nav(AccountSettingPage).verifyAccountAvatarPresent()
							.verifyAccountNamePresent(user.accountName)
							.verifyAccountIdPresent(user.accountId)
							.verifyNoOfMembers(noOfMembers)
							.verifyNoOfOrgs(noOfOrgs)
							.verifyNoOfProjects(noOfProjects)
							.verifyEditNameButtonPresent()
							.verifyDeleteButtonPresent()
							.clickEditNameButton()
							.verifyEditNamePopupPresent()
							.verifySaveEditBtnNotClickable()
							.verifyCancelEditBtnClickable()
							.clickCancel()
							.clickDeleteAccount()
							.verifyDeletePopupPresent()
							.verifyCancelDelBtnClickable()
							.verifyConfirmDelBtnNotClickable()
