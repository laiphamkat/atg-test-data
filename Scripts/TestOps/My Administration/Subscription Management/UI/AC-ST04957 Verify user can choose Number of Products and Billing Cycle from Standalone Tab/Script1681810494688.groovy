import external.services.PricingCalculationService
import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.enums.Subscriptions
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.my.account.AccountHomePage
import katalon.my.account.CreateNewAccountPopUp
import katalon.my.subscriptionmanagement.SubscriptionManagementPage

'Data Setup'
Credential user = Page.nav(Credential)
	.getCredentials()
	.withRole('Owner')
	.inAccount('CE New Customer')
	.getFirst()

'User signs in to administration page'
Page.nav(MySignInPage).enterCredential(user.email, user.pwd).clickSignIn().verifySuccessfullySignIn()
Page.nav(WelcomePage).selectAccount(user.accountId)

'Navigate to Subscription Manangement Page'					
Page.nav(AccountHomePage).clickSubscribeToKatalonProducts()
						 .sleepALittleTime()
									
'User wants to choose the Katalon Platform - KSE'
Page.nav(SubscriptionManagementPage).clickStandaloneTabButton()
									.verifyCheckoutButtonIsDisable()
									.inputKSEQuota("1")		
									.setKSEBillingCycle("Annual")
 									.verifyKSEListedPrice("\$${Page.nav(PricingCalculationService).getListedPrice(Subscriptions.KSE_PERUSER_ANNUAL)} /per License")
									.verifyKSECheckoutPrice("\$${Page.nav(PricingCalculationService).getPreviewPrice(user.accountId, Subscriptions.KSE_PERUSER_ANNUAL, 1)}")

'User wants to choose the Katalon Platform - KRE'
Page.nav(SubscriptionManagementPage).inputKREQuota("1")
									.setKREBillingCycle("Annual")
									.verifyKREListedPrice("\$${Page.nav(PricingCalculationService).getListedPrice(Subscriptions.KRE_FLOATING_ANNUAL)} /per License")
									.verifyKRECheckoutPrice("\$${Page.nav(PricingCalculationService).getPreviewPrice(user.accountId, Subscriptions.KRE_FLOATING_ANNUAL, 1)}")
									.verifyTotalCheckoutPrice("\$${Page.nav(PricingCalculationService).getTotalPrice()}")
									.verifyCheckoutButtonIsEnable()
									
									
