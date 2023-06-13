import katalon.common.AdminHeaderBar
import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.my.paymentmethod.PaymentMethodPage

'Find a user to login'
Credential user = Page.nav(Credential)
					.getCredentials()
					.inAccount("Admin")
					.inOrg("Org_Admin")
					.withRole("Owner")
					.getFirst()

'User signs in to administration page'
Page.nav(MySignInPage).enterCredential(user.email, user.pwd).clickSignIn().verifySuccessfullySignIn()

'User selects the account'
Page.nav(WelcomePage).selectAccount(user.accountId)

'Click Settings on Header Navigator > Go to Payment Method'
Page.nav(AdminHeaderBar).clickSettingIcon().clickPaymentMethod().sleepSomeTime()

'Verify UI of payment method when already having data'
Page.nav(PaymentMethodPage).verifyUpdatePayMentMethodBtnClickable()
						.verifyDeletePayMentMethodBtnClickable()
						.verifyUpdateBillingInfoBtnClickable()
						.clickUpdatePaymentMethod()
						.verifyPaymentMethodElementsPresent()
						.clickUpdateBillingInfoBtn()
						.verifyBillingInfoElementsPresent()
