import katalon.common.AdminHeaderBar
import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.my.account.ChangePasswordPopup
import katalon.my.account.DeleteProfilePopup
import katalon.my.account.ProfilePage

'Find a user to login'
Credential user = Page.nav(Credential)
					.getCredentials()
					.inAccount("Admin")
					.inOrg("Org_Admin")
					.withRole("Owner")
					.getFirst()

'User sign in to administration page'
Page.nav(MySignInPage).enterCredential(user.email, user.pwd).clickSignIn().verifySuccessfullySignIn()

'User selects the account'
Page.nav(WelcomePage).selectAccount(user.accountId).sleepALittleTime()

'Go to Profile page'
Page.nav(AdminHeaderBar).clickAvatar().clickViewProfile()

'Verify Profile page'
Page.nav(ProfilePage).verifyKatalonLogoPresent()
					.verifyLogOutBtnClickable()
					.verifyChangePasswordBtnClickable()
					.verifyDeleteProfileBtnClickable()
					.verifyBasicInfoPresent()
					.clickChangePasswordLink()

'Verify UI Change Password Popup'
Page.nav(ChangePasswordPopup).verifyUIChangePasswordPopup()
							.verifyCancelChangePassBtnClickable()
							.verifyChangePassBtnClickable()
							.clickCancelBtn()

'Open Delete profile popup'
Page.nav(ProfilePage).clickDeleteProfile()

'Verify UI Delete profile Popup'
Page.nav(DeleteProfilePopup).verifyUIDeleteAccountPopup()
							.verifyReasonFieldPresent()
							.verifyPassFieldPresent()
							.verifyDeleteClickable()
							.verifyCancelDeleteClickable()
							.clickCancelBtn()

'Go to Profile page and click Go to Welcome Page'
Page.nav(ProfilePage).clickGoToWelcomePage().sleepALittleTime()

'Verify Welcome page present'
Page.nav(WelcomePage).verifyWelcomePagePresent()


