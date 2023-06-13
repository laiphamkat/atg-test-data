import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.my.account.CreateNewAccountPopUp

'Find a user to login'
Credential user = Page.nav(Credential)
					.getCredentials()
					.inAccount("Admin")
					.inOrg("Org_Admin")
					.withRole("Owner")
					.getFirst()

'1. User signs in to administration page'
Page.nav(MySignInPage).enterCredential(user.email, user.pwd).clickSignIn().verifySuccessfullySignIn()

'2. Verify welcome page present'
Page.nav(WelcomePage).verifyKatalonLogo()
					.verifyWelcomePagePresent()
					.verifyCreateNewAccountBtnClickable()
					.verifyTryAnotherEmailLink()
					.verifyAccountListPresent()
					.clickCreateANewAccount()

'3. Verify create new account popup present'
Page.nav(CreateNewAccountPopUp).verifyCreateNewAccountPopUpPresent()
								.verifyCancelBtnClickable()
								.verifyCreateBtnClickable()
